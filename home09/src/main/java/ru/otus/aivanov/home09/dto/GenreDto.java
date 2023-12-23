package ru.otus.aivanov.home09.dto;


import jakarta.validation.constraints.NotBlank;

/**
 * DTO for {@link ru.otus.aivanov.home09.models.Genre}
 */
public record GenreDto(Long id, @NotBlank(message = "Name cannot be empty") String name)  {

}