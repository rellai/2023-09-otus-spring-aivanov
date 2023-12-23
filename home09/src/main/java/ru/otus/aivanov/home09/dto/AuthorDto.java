package ru.otus.aivanov.home09.dto;

import jakarta.validation.constraints.NotBlank;


/**
 * DTO for {@link ru.otus.aivanov.home09.models.Author}
 */
public record AuthorDto(Long id, @NotBlank(message = "Title cannot be empty") String name) {

}