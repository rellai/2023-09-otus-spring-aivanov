package ru.otus.aivanov.home08.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.aivanov.home08.models.Book;

public interface BookRepository  extends MongoRepository<Book, String> {

    List<Book> findBooksByAuthorId(String authorId);

    long countBooksByGenreId(String genreId);


}
