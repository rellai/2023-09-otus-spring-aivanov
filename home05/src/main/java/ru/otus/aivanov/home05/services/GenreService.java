package ru.otus.aivanov.home05.services;

import ru.otus.aivanov.home05.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<Genre> findAll();

    Optional<Genre> findById(long id);

    Genre save(Genre genre);

    long deleteById(long id);
}
