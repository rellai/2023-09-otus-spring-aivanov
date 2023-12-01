package ru.otus.aivanov.home07.services;

import java.util.List;
import java.util.Optional;

import ru.otus.aivanov.home07.models.Book;

public interface BookService {
    Optional<Book> findById(long id);

    List<Book> findAll();

    Book insert(String title, long authorId, long genreId);

    Book update(long id, String title, long authorId, long genreId);

    void deleteById(long id);

    Optional<Book> findFullById(long id);
}
