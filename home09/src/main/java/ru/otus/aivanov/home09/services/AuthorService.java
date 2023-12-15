package ru.otus.aivanov.home09.services;

import java.util.List;
import java.util.Optional;

import ru.otus.aivanov.home09.models.Author;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(long id);

    Author save(Author genre);

    void deleteById(long id);
}
