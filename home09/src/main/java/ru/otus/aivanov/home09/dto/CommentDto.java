package ru.otus.aivanov.home09.dto;

import ru.otus.aivanov.home09.models.Comment;


/**
 * DTO for {@link Comment}
 */
public record CommentDto(Long id, String text)  {

}