package ru.otus.aivanov.home13.dto;


import jakarta.validation.constraints.NotBlank;
import ru.otus.aivanov.home13.models.Genre;

/**
 * DTO for {@link Genre}
 */
public record GenreCreateDto(@NotBlank(message = "Name cannot be empty") String name)  {

}