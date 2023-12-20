package ru.otus.aivanov.home09.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO for {@link ru.otus.aivanov.home09.models.Book}
 */
public record BookDto(Long id,
                      @NotBlank(message = "Title cannot be empty") String title,
                      @NotNull(message = "Author cannot be empty") Long author,
                      @NotNull(message = "Author cannot be empty") Long genre,
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