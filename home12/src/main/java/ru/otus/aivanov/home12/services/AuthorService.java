package ru.otus.aivanov.home12.services;

import java.util.List;

import ru.otus.aivanov.home12.dto.AuthorCreateDto;
import ru.otus.aivanov.home12.dto.AuthorDto;

public interface AuthorService {
    List<AuthorDto> findAll();

    AuthorDto findById(long id);

    AuthorDto update(AuthorDto author);

    AuthorDto create(AuthorCreateDto author);

    void deleteById(long id);
}
