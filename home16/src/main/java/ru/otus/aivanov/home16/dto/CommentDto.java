package ru.otus.aivanov.home16.dto;

import ru.otus.aivanov.home16.models.Comment;


/**
 * DTO for {@link Comment}
 */
public record CommentDto(Long id, String text)  {

}