package ru.otus.aivanov.home11.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.aivanov.home11.models.Comment;

public interface CommentRepository  extends ReactiveCrudRepository<Comment, Long> {


    public Mono<Void> deleteByBookId(long id);

    @Query("select id, text from comments where book_id = $1")
    Flux<Comment> findAllByBookId(Long bookId);

    @Query("select id, text from comments where id = $1")
    Mono<Comment> findById(Long id);

    @Query("SELECT id, text FROM FINAL TABLE (" +
            "Merge into comments as c using " +
            "(select " +
            "COALESCE(:#{#comment.id}, 0) as id,  " +
            "COALESCE(:#{#comment.text}, '') as text, " +
            "COALESCE(:#{#comment.book.id}, 0) as book_id) uc on c.id = uc.id \n" +
            "    when matched then update set text = uc.text\n" +
            "    when not matched then insert(text, book_id) values (uc.text, uc.book_id))")
    Mono<Comment> save(@Param("comment")  Comment comment);





}


