package ru.otus.aivanov.home09.dto;

import ru.otus.aivanov.home09.models.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO for {@link Book}
 */
public record BookShowDto(Long id,
                          @NotBlank(message = "Title cannot be empty") String title,
                          @NotNull(message = "Author cannot be empty") String author,
                          @NotNull(message = "Author cannot be empty") String genre) {

}