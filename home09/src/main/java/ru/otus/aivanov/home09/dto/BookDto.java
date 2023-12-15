package ru.otus.aivanov.home09.dto;

import ru.otus.aivanov.home09.models.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO for {@link ru.otus.aivanov.home09.models.Book}
 */
public record BookDto(Long id, @NotBlank(message = "Title cannot be empty") String title,
                      @NotNull(message = "Author cannot be empty") AuthorDto author,
                      @NotNull(message = "Author cannot be empty") GenreDto genre)  {

    public Book toDomainObject() {
        return new Book(id, title, author.toDomainObject(), genre.toDomainObject(), null);
    }

}