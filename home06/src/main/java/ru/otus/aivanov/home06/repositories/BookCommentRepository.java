package ru.otus.aivanov.home06.repositories;

import ru.otus.aivanov.home06.dto.BookDto;

import java.util.Optional;

public interface BookCommentRepository {

    void deleteByBookId(long bookId);

    Optional<BookDto> findById(long id);
}
