package ru.otus.aivanov.home07.services;

import java.util.List;
import java.util.Optional;

import ru.otus.aivanov.home07.models.Genre;

public interface GenreService {
    List<Genre> findAll();

    Optional<Genre> findById(long id);

    Genre save(Genre genre);

    void deleteById(long id);
}
