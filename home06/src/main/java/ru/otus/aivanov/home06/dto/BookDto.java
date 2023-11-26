package ru.otus.aivanov.home06.dto;

import jakarta.validation.constraints.NotNull;
import ru.otus.aivanov.home06.models.Author;
import ru.otus.aivanov.home06.models.Comment;
import ru.otus.aivanov.home06.models.Genre;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link ru.otus.aivanov.home06.models.Book}
 */
public record BookDto(@NotNull Long id, String title, @NotNull Author author,
                      @NotNull Genre genre, List<Comment> comments) implements Serializable {
}