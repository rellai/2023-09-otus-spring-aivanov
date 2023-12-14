package ru.otus.aivanov.home08.services;

import java.util.List;
import java.util.Optional;

import ru.otus.aivanov.home08.models.Genre;

public interface GenreService {
    List<Genre> findAll();

    Optional<Genre> findById(String id);

    Genre save(Genre genre);

    void deleteById(String id);
}
