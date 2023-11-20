package ru.otus.aivanov.home06.services;

import ru.otus.aivanov.home06.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(long id);

    Author save(Author genre);

    long deleteById(long id);
}
