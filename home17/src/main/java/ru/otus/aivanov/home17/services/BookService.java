package ru.otus.aivanov.home17.services;

import java.util.List;

import ru.otus.aivanov.home17.dto.BookCreateDto;
import ru.otus.aivanov.home17.dto.BookDto;
import ru.otus.aivanov.home17.dto.BookFullDto;

public interface BookService {
    BookDto findById(long id);

    List<BookFullDto> findAll();

    BookDto create(BookCreateDto book);

    BookDto update(BookDto book);

    void deleteById(long id);

}
