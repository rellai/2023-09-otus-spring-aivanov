package ru.otus.aivanov.home11.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.otus.aivanov.home11.models.Book;

public interface BookRepository  extends ReactiveCrudRepository<Book, Long>, BookCustomRepository {

   /* @Query(" select b.id, b.title, " +
            "a.id author_id, g.id genre_id" +
            ", a.name author_name, g.name genre_name " +
            " from FINAL TABLE (insert into books (title, author_id, genre_id) VALUES ($1, $2, $3)) b " +
            " left join authors a on a.id = b.author_id " +
            " left join genres g on g.id = b.genre_id ")
    Mono<Book> save(String title, long authorId, long genreId);*/

    @Query("SELECT id, title FROM FINAL TABLE( " +
            "update books set title = $2, author_id = $3, genre_id = $4 where id = $1" +
            ")")
    Mono<Book> save(long id, String title, long authorId, long genreId);

}
