package ru.otus.aivanov.home09.services;

import java.util.List;

import ru.otus.aivanov.home09.dto.BookCreateDto;
import ru.otus.aivanov.home09.dto.BookEditDto;
import ru.otus.aivanov.home09.dto.BookDto;

public interface BookService {
    BookEditDto findById(long id);

    List<BookDto> findAll();

    BookEditDto create(BookCreateDto book);

    BookEditDto update(BookEditDto book);

    void deleteById(long id);

}
