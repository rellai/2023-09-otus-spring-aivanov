package ru.otus.aivanov.home12.dto;

import ru.otus.aivanov.home12.models.Comment;


/**
 * DTO for {@link Comment}
 */
public record CommentDto(Long id, String text)  {

}