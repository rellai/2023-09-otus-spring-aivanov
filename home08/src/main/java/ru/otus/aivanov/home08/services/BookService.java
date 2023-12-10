package ru.otus.aivanov.home08.services;

import java.util.List;
import java.util.Optional;

import ru.otus.aivanov.home08.models.Book;

public interface BookService {
    Optional<Book> findById(String id);

    List<Book> findAll();

    Book insert(String title, String authorId, String genreId);

    Book update(String id, String title, String authorId, String genreId);

    void deleteById(String id);

}
