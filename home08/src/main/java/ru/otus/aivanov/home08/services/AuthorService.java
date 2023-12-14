package ru.otus.aivanov.home08.services;

import java.util.List;
import java.util.Optional;

import ru.otus.aivanov.home08.models.Author;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(String id);

    Author save(Author genre);

    void deleteById(String id);
}
