package ru.otus.aivanov.home16.services;

import java.util.List;

import ru.otus.aivanov.home16.dto.GenreCreateDto;
import ru.otus.aivanov.home16.dto.GenreDto;

public interface GenreService {
    List<GenreDto> findAll();

    GenreDto findById(long id);

    GenreDto create(GenreCreateDto genre);

    GenreDto update(GenreDto genre);


    void deleteById(long id);
}
