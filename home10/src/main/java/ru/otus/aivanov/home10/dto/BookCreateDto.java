package ru.otus.aivanov.home10.dto;

import ru.otus.aivanov.home10.models.Book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link Book}
 */
public record BookCreateDto(@NotBlank(message = "Title cannot be empty") String title,
                            @NotNull(message = "Author is required") Long authorId,
                            @NotNull(message = "Genre is required") Long genreId) {

}