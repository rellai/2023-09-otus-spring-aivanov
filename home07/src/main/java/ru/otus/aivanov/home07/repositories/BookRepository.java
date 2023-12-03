package ru.otus.aivanov.home07.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.aivanov.home07.models.Book;

public interface BookRepository  extends JpaRepository<Book, Long>  {

    @EntityGraph(attributePaths = {"author", "genre", "comments"})
    Optional<Book> findWithCommentsById(long id);

    @EntityGraph(attributePaths = {"author", "genre"})
    Optional<Book> findById(long id);

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findAll();
    

}
