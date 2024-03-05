package ru.otus.aivanov.home16.dto;


import jakarta.validation.constraints.NotBlank;
import ru.otus.aivanov.home16.models.Genre;

/**
 * DTO for {@link Genre}
 */
public record GenreDto(Long id, @NotBlank(message = "Name cannot be empty") String name)  {

}