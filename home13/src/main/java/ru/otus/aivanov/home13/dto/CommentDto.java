package ru.otus.aivanov.home13.dto;

import ru.otus.aivanov.home13.models.Comment;


/**
 * DTO for {@link Comment}
 */
public record CommentDto(Long id, String text)  {

}