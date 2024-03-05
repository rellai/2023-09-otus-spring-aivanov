package ru.otus.aivanov.home16.projection;


import org.springframework.data.rest.core.config.Projection;
import ru.otus.aivanov.home16.models.Author;
import ru.otus.aivanov.home16.models.Book;
import ru.otus.aivanov.home16.models.Genre;

@Projection(name = "book-view", types = Book.class)
public interface BookProjection {

    Long getId();

    String getTitle();

    Author getAuthor();

    Genre getGenre();


}
