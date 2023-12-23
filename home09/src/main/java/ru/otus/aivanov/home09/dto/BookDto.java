package ru.otus.aivanov.home09.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * DTO for {@link ru.otus.aivanov.home09.models.Book}
 */
public record BookDto(Long id,
                      @NotBlank(message = "Title cannot be empty") String title,
                      @NotNull(message = "Author is required") Long authorId,
                      @NotNull(message = "Genre is required") Long genreId,
                      List<CommentDto> comments) {

    public BookDto() {

            this(null,
                    "",
                    null,
                    null,
                    null
            );

    }

}