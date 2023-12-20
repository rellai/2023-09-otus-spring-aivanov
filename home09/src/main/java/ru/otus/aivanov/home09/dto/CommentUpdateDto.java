package ru.otus.aivanov.home09.dto;

import ru.otus.aivanov.home09.models.Comment;


/**
 * DTO for {@link Comment}
 */
public record CommentUpdateDto(Long id, String text)  {

}