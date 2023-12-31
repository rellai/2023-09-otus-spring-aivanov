package ru.otus.aivanov.home05.repositories;

import ru.otus.aivanov.home05.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    List<Genre> findAll();

    Optional<Genre> findById(long id);

    Genre save(Genre genre);

    long deleteById(long id);
}
