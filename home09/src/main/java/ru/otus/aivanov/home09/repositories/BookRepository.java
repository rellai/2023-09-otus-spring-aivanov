package ru.otus.aivanov.home09.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.aivanov.home09.models.Author;
import ru.otus.aivanov.home09.models.Book;
import ru.otus.aivanov.home09.models.Genre;

public interface BookRepository  extends JpaRepository<Book, Long>  {

    @EntityGraph(value = "book-author-genre-comments-entity-graph")
    Optional<Book> findWithCommentsById(long id);

    @EntityGraph(value = "book-author-genre-entity-graph")
    Optional<Book> findById(long id);

    @EntityGraph(value = "book-author-genre-entity-graph")
    List<Book> findAll();

    @Modifying
    @Query("update Book b set b.title = :title, b.author = :author, b.genre = :genre  where b.id = :id")
    void update(@Param("id") long id,
                @Param("title") String title,
                @Param("author") Author author,
                @Param("genre") Genre genre
    );

}
