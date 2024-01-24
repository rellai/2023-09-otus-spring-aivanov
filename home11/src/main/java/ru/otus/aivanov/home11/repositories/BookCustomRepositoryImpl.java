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
    public Mono<Book> saveBook(Book book) {
        String query =
                (" select b.id, b.title, a.id author_id, g.id genre_id, a.name author_name, g.name genre_name " +
                        "FROM FINAL TABLE( " +
                        "Merge into books as b using " +
                        "(select " +
                        "COALESCE(%d, 0) as id,  " +
                        "COALESCE('%s', '') as title, " +
                        "COALESCE(%d, 0) as genre_id, " +
                        "COALESCE(%d, 0) as author_id ) ub on b.id = ub.id " +
                        "when matched then update " +
                        "set title = ub.title, genre_id = ub.genre_id, author_id = ub.author_id " +
                        "when not matched then insert(title, genre_id, author_id) " +
                        "values (ub.title, ub.genre_id, ub.author_id)" +
                        ") b " +
                        "left join authors a on a.id = b.author_id  " +
                        "left join genres g on g.id = b.genre_id ").formatted(
                        book.getId(),
                        book.getTitle(),
                        book.getGenre().getId(),
                        book.getAuthor().getId());
        return client.sql(query)
                .map(bookMapper::toModel)
                .first();
    }

}
