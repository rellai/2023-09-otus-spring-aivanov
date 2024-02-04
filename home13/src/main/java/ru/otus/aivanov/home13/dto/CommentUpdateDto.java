package ru.otus.aivanov.home13.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.otus.aivanov.home13.models.Comment;


/**
 * DTO for {@link Comment}
 */
public record CommentUpdateDto(@NotNull(message = "Comment ID cannot be empty") Long id,
                               @NotBlank(message = "Comment cannot be empty") String text)  {

}