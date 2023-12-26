package ru.otus.aivanov.home09.services;

import java.util.List;

import ru.otus.aivanov.home09.dto.BookCreateDto;
import ru.otus.aivanov.home09.dto.BookUpdateDto;
import ru.otus.aivanov.home09.dto.BookDto;

public interface BookService {
    BookUpdateDto findById(long id);

    List<BookDto> findAll();

    BookUpdateDto create(BookCreateDto book);

    BookUpdateDto update(BookUpdateDto book);

    void deleteById(long id);

}
