package ru.otus.aivanov.home09.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * DTO for {@link ru.otus.aivanov.home09.models.Book}
 */
public record BookUpdateDto(@NotNull(message = "Id cannot be empty") Long id,
                            @NotBlank(message = "Title cannot be empty") String title,
                            @NotNull(message = "Author is required") Long authorId,
                            @NotNull(message = "Genre is required") Long genreId) {


}