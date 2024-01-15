package ru.otus.aivanov.home11.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.aivanov.home11.mapper.BookMapper;
import ru.otus.aivanov.home11.models.Book;

@RequiredArgsConstructor
public class BookCustomRepositoryImpl implements BookCustomRepository {

    private final DatabaseClient client;

    private final BookMapper bookMapper;

    @Override
    public Flux<Book> findAllBooks() {
        String query =
                        " select b.id, b.title, " +
                        "a.id author_id, g.id genre_id" +
                        ", a.name author_name, g.name genre_name " +
                        " from books b " +
                        " left join authors a on a.id = b.author_id " +
                        " left join genres g on g.id = b.genre_id ";

        return client.sql(query)
                .map(bookMapper::toModel)
                .all();
    }

    @Override
    public Mono<Book> findByBookId(Long id) {
        String query =
                " select b.id, b.title, " +
                        "a.id author_id, g.id genre_id" +
                        ", a.name author_name, g.name genre_name " +
                        " from books b " +
                        " left join authors a on a.id = b.author_id " +
                        " left join genres g on g.id = b.genre_id " +
                        "where b.id = " + id.toString();

        return client.sql(query)
                .map(bookMapper::toModel)
                .first();
    }

    @Override
    public Mono<Book> save(String title, Long authorId, Long genreId) {
        String query =
                " select b.id, b.title, " +
                        "a.id author_id, g.id genre_id" +
                        ", a.name author_name, g.name genre_name " +
                        " from FINAL TABLE (insert into books (title, author_id, genre_id) " +
                        " VALUES ('" + title + "', " + authorId.toString() + ", " + genreId.toString() + ")) b " +
                        " left join authors a on a.id = b.author_id " +
                        " left join genres g on g.id = b.genre_id ";
        return client.sql(query)
                .map(bookMapper::toModel)
                .first();
    }

    @Override
    public Mono<Book> save(Long id, String title, Long authorId, Long genreId) {
        String query =
                " select b.id, b.title, " +
                        "a.id author_id, g.id genre_id" +
                        ", a.name author_name, g.name genre_name" +
                        " FROM FINAL TABLE( update books set " +
                                "  title = '" + title + "', " +
                                " author_id = " + authorId.toString() + ", " +
                                " genre_id = " + genreId.toString() +
                        " where id = " + id.toString() + ") b" +
                        " left join authors a on a.id = b.author_id " +
                        " left join genres g on g.id = b.genre_id ";
        return client.sql(query)
                .map(bookMapper::toModel)
                .first();
    }

}
