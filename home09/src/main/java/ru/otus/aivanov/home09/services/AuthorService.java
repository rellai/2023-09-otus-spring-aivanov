package ru.otus.aivanov.home09.services;

import java.util.List;

import ru.otus.aivanov.home09.dto.AuthorDto;

public interface AuthorService {
    List<AuthorDto> findAll();

    AuthorDto findById(long id);

    AuthorDto save(AuthorDto author);

    void deleteById(long id);
}
