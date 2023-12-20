package ru.otus.aivanov.home09.services;

import java.util.List;

import ru.otus.aivanov.home09.dto.GenreDto;

public interface GenreService {
    List<GenreDto> findAll();

    GenreDto findById(long id);

    GenreDto save(GenreDto genre);

    void deleteById(long id);
}
