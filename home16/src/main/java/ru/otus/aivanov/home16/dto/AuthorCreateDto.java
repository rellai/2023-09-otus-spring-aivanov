package ru.otus.aivanov.home16.dto;

import jakarta.validation.constraints.NotBlank;
import ru.otus.aivanov.home16.models.Author;


/**
 * DTO for {@link Author}
 */
public record AuthorCreateDto(@NotBlank(message = "Title cannot be empty") String name) {

}