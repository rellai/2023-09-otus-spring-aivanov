package ru.otus.aivanov.home06.repositories;

import ru.otus.aivanov.home06.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    List<Genre> findAll();

    Optional<Genre> findById(long id);

    Genre save(Genre genre);

    boolean deleteById(long id);
}
