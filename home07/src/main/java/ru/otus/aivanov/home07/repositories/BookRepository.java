package ru.otus.aivanov.home07.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.aivanov.home07.models.Book;

public interface BookRepository  extends JpaRepository<Book, Long>  {

    @EntityGraph(value = "book-author-genre-comments-entity-graph")
    Optional<Book> findWithCommentsById(long id);

    @EntityGraph(value = "book-author-genre-entity-graph")
    Optional<Book> findById(long id);

    @EntityGraph(value = "book-author-genre-entity-graph")
    List<Book> findAll();
    

}
