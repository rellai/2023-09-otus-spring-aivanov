package ru.otus.aivanov.home09.dto;

import ru.otus.aivanov.home09.models.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link ru.otus.aivanov.home09.models.Book}
 */
public record BookDto(Long id,
                      @NotBlank(message = "Title cannot be empty")
                      String title,
                      @NotNull(message = "Author cannot be empty") Long author,
                      @NotNull(message = "Author cannot be empty") Long genre,
                      List<CommentDto> comments) {

    public static BookDto toDTO(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor().getId(),
                book.getGenre().getId(),
                book.getComments().stream().filter(Objects::nonNull).map(CommentDto::toDto).toList());
    }

}