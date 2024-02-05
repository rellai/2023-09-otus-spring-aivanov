package ru.otus.aivanov.home14.config.step;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import ru.otus.aivanov.home14.documents.CommentDoc;
import ru.otus.aivanov.home14.dto.CommentDto;


import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CommentMigrationStepConfig {

    private final DataSource dataSource;

    private final JobRepository jobRepository;

    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public MongoItemReader<CommentDoc> mongoCommentReader(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<CommentDoc>()
                .name("mongoCommentReader")
                .template(mongoTemplate)
                .targetType(CommentDoc.class)
                .jsonQuery("{}")
                .sorts(new HashMap<>())
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<CommentDto> commentInsertTempTable() {
        JdbcBatchItemWriter<CommentDto> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO temp_comment_cross_ids(id_mongo, id_postgres) " +
                "VALUES (:id, nextval('comments_id_seq'))");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public JdbcBatchItemWriter<CommentDto> commentJdbcBatchItemWriter() {
        JdbcBatchItemWriter<CommentDto> writer = new JdbcBatchItemWriter<>();
        writer.setItemPreparedStatementSetter((comment, statement) -> {
            statement.setString(1, comment.getId());
            statement.setString(2, comment.getText());
            statement.setString(3, comment.getBookId());
        });
        writer.setSql("INSERT INTO comments(id, text, book_id) " +
                "VALUES ((SELECT id_postgres FROM temp_comment_cross_ids WHERE id_mongo = ?), " +
                "?, (SELECT id_postgres FROM temp_book_cross_ids WHERE id_mongo = ?))");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public CompositeItemWriter<CommentDto> commentCompositeItemWriter(
            JdbcBatchItemWriter<CommentDto> commentInsertTempTable,
            JdbcBatchItemWriter<CommentDto> commentJdbcBatchItemWriter) {
        CompositeItemWriter<CommentDto> writer = new CompositeItemWriter<>();
        writer.setDelegates(List.of(commentInsertTempTable, commentJdbcBatchItemWriter));
        return writer;
    }

    @Bean
    public Step commentMigrationStep(MongoItemReader<CommentDoc> mongoCommentReader,
                                     CompositeItemWriter<CommentDto> commentCompositeItemWriter) {
        return new StepBuilder("commentMigrationStep", jobRepository)
                .<CommentDoc, CommentDto>chunk(1, platformTransactionManager)
                .reader(mongoCommentReader)
                .processor(new ItemProcessor<CommentDoc, CommentDto>() {
                    @Override
                    public CommentDto process(CommentDoc item) throws Exception {
                        return new CommentDto(item.getId(), item.getText(), item.getBook().getId());
                    }
                })
                .writer(commentCompositeItemWriter)
                .allowStartIfComplete(true)
                .build();
    }

}
