package ru.otus.aivanov.home13.dto;

import jakarta.validation.constraints.NotBlank;
import ru.otus.aivanov.home13.models.Author;


/**
 * DTO for {@link Author}
 */
public record AuthorDto(Long id, @NotBlank(message = "Title cannot be empty") String name) {

}