package ru.otus.aivanov.home11.repositories;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.aivanov.home11.models.Book;

public interface BookCustomRepository {

    Flux<Book> findAllBooks();

    Mono<Book> findByBookId(Long id);

    Mono<Book> saveBook(Book book);
}
