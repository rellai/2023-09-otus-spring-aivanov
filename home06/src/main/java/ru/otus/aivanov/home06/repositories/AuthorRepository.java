package ru.otus.aivanov.home06.repositories;

import ru.otus.aivanov.home06.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    List<Author> findAll();

    Optional<Author> findById(long id);

    Author save(Author genre);

    long deleteById(long id);
}
