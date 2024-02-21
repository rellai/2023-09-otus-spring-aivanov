package ru.otus.aivanov.home16.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.aivanov.home16.models.Book;
import ru.otus.aivanov.home16.projection.BookProjection;

@RepositoryRestResource(path = "books", excerptProjection = BookProjection.class)
public interface BookRepository  extends CrudRepository<Book, Long> {

    @Override
    @EntityGraph(value = "book-author-genre-entity-graph")
    Optional<Book> findById(Long id);

    @Override
    @EntityGraph(value = "book-author-genre-entity-graph")
    List<Book> findAll();

}
