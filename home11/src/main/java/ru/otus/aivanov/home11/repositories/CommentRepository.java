package ru.otus.aivanov.home11.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.aivanov.home11.models.Comment;

public interface CommentRepository  extends ReactiveCrudRepository<Comment, Long> {


    public Mono<Void> deleteByBookId(long id);

    @Query("select id, text from comments where book_id = $1")
    Flux<Comment> findAllByBookId(Long bookId);

    @Query("SELECT id, text FROM FINAL TABLE (insert into comments (text, book_id) values ($1, $2))")
    Mono<Comment> save(String text, long bookId);

    @Query("SELECT id, text FROM FINAL TABLE (update comments set text = $2 where id = $1)")
    Mono<Comment> save(long id, String text);

}


