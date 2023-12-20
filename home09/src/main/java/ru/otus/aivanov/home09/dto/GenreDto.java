package ru.otus.aivanov.home09.dto;

import javax.validation.constraints.NotBlank;


/**
 * DTO for {@link ru.otus.aivanov.home09.models.Genre}
 */
public record GenreDto(Long id, @NotBlank(message = "Title cannot be empty") String name)  {

}