package ru.otus.aivanov.home17.dto;

import ru.otus.aivanov.home17.models.Comment;


/**
 * DTO for {@link Comment}
 */
public record CommentDto(Long id, String text)  {

}