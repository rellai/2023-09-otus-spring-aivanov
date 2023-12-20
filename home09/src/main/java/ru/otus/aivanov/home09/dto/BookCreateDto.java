package ru.otus.aivanov.home09.dto;

import ru.otus.aivanov.home09.models.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO for {@link Book}
 */
public record BookCreateDto(@NotBlank(message = "Title cannot be empty") String title,
                            @NotNull(message = "Author cannot be empty") Long author,
                            @NotNull(message = "Author cannot be empty") Long genre) {

}