package ru.otus.aivanov.home09.services;

import java.util.List;

import ru.otus.aivanov.home09.dto.BookCreateDto;
import ru.otus.aivanov.home09.dto.BookDto;
import ru.otus.aivanov.home09.dto.BookShowDto;
import ru.otus.aivanov.home09.dto.BookUpdateDto;

public interface BookService {
    BookDto findById(long id);

    List<BookShowDto> findAll();

    BookDto create(BookCreateDto book);

    BookDto update(BookUpdateDto book);

    void deleteById(long id);

}
