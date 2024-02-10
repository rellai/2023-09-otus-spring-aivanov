package ru.otus.aivanov.home14.config.step;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import ru.otus.aivanov.home14.documents.BookDoc;
import ru.otus.aivanov.home14.dto.BookDto;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BookMigrationStepConfig {

    private final DataSource dataSource;

    private final JobRepository jobRepository;

    private final PlatformTransactionManager transactionManager;

    @Bean
    public MongoItemReader<BookDoc> bookMongoItemReader(MongoTemplate mongoTemplate) {
        return new MongoItemReaderBuilder<BookDoc>()
                .name("mongoBookReader")
                .template(mongoTemplate)
                .targetType(BookDoc.class)
                .jsonQuery("{}")
                .sorts(new HashMap<>())
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<BookDto> bookInsertTempTable() {
        JdbcBatchItemWriter<BookDto> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO temp_book_cross_ids(id_mongo, id_postgres) " +
                "VALUES (:id, nextval('books_id_seq'))");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public BookWriter bookWriter() {
        return new BookWriter(dataSource);
    }

    @Bean
    public CompositeItemWriter<BookDto> bookCompositeItemWriter(
            JdbcBatchItemWriter<BookDto> bookInsertTempTable,
            ItemWriter<BookDto> bookWriter) {
        CompositeItemWriter<BookDto> writer = new CompositeItemWriter<>();
        writer.setDelegates(List.of(bookInsertTempTable, bookWriter));
        return writer;
    }

    @Bean
    public Step bookMigrationStep(MongoItemReader<BookDoc> bookMongoItemReader,
                                  CompositeItemWriter<BookDto> bookCompositeItemWriter) {
        return new StepBuilder("bookMigrationStep", jobRepository)
                .<BookDoc, BookDto>chunk(10, transactionManager)
                .reader(bookMongoItemReader)
                .processor(new ItemProcessor<BookDoc, BookDto>() {
                    @Override
                    public BookDto process(BookDoc item) throws Exception {
                        return new BookDto(item.getId(), item.getTitle(),
                                item.getAuthor().getId(),
                                item.getGenre().getId());
                    }
                })
                .writer(bookCompositeItemWriter)
                .allowStartIfComplete(true)
                .build();
    }

    class BookWriter implements ItemWriter<BookDto> {

        private JdbcTemplate jdbcTemplate;

        public BookWriter(DataSource dataSource) {
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        }

        @Override
        public void write(Chunk<? extends BookDto> items) {
            for (BookDto book : items) {
                jdbcTemplate.update("INSERT INTO books(title, id, genre_id,  author_id) " +
                                "VALUES (?, " +
                                "(SELECT id_postgres FROM temp_book_cross_ids WHERE id_mongo = ? limit 1), " +
                                "(SELECT id_postgres FROM temp_genre_cross_ids WHERE id_mongo = ? limit 1), " +
                                "(SELECT id_postgres FROM temp_author_cross_ids WHERE id_mongo = ? limit 1))",
                        book.getTitle(), book.getId(), book.getGenresId(), book.getAuthorId());
            }
        }
    }

}
