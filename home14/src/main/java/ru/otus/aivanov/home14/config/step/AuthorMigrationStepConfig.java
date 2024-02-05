package ru.otus.aivanov.home14.config.step;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import ru.otus.aivanov.home14.documents.AuthorDoc;
import ru.otus.aivanov.home14.entity.Author;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AuthorMigrationStepConfig {

    private final DataSource dataSource;

    private final JobRepository jobRepository;

    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public MongoItemReader<AuthorDoc> mongoAuthorReader(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<AuthorDoc>()
                .name("mongoAuthorReader")
                .template(mongoTemplate)
                .targetType(AuthorDoc.class)
                .jsonQuery("{}")
                .sorts(new HashMap<>())
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Author> authorInsertTempTable() {
        JdbcBatchItemWriter<Author> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO temp_author_cross_ids(id_mongo, id_postgres) " +
                "VALUES (:id, nextval('authors_id_seq'))");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public JdbcBatchItemWriter<Author> authorJdbcBatchItemWriter() {
        JdbcBatchItemWriter<Author> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO authors(id, name) " +
                "VALUES ((SELECT id_postgres FROM temp_author_cross_ids WHERE id_mongo = :id), " +
                ":Name )");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public CompositeItemWriter<Author> authorCompositeItemWriter(
            JdbcBatchItemWriter<Author> authorInsertTempTable,
            JdbcBatchItemWriter<Author> authorJdbcBatchItemWriter) {
        CompositeItemWriter<Author> writer = new CompositeItemWriter<>();
        writer.setDelegates(List.of(authorInsertTempTable, authorJdbcBatchItemWriter));
        return writer;
    }

    @Bean
    public Step authorMigrationStep(MongoItemReader<AuthorDoc> mongoAuthorReader,
            CompositeItemWriter<Author> authorCompositeItemWriter) {
        return new StepBuilder("authorMigrationStep", jobRepository)
                .<AuthorDoc, Author>chunk(1, platformTransactionManager)
                .reader(mongoAuthorReader)
                .writer(authorCompositeItemWriter)
                .allowStartIfComplete(true)
                .build();
    }

}
