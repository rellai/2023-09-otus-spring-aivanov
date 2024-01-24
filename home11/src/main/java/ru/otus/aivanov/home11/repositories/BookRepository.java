package ru.otus.aivanov.home11.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.otus.aivanov.home11.models.Book;

public interface BookRepository  extends ReactiveCrudRepository<Book, Long>, BookCustomRepository {

}
