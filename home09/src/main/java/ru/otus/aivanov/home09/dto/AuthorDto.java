package ru.otus.aivanov.home09.dto;

import ru.otus.aivanov.home09.models.Author;

import javax.validation.constraints.NotBlank;


/**
 * DTO for {@link ru.otus.aivanov.home09.models.Author}
 */
public record AuthorDto(Long id, @NotBlank(message = "Title cannot be empty") String name) {

    public Author toDomainObject() {
        return new Author(id, name);
    }


}