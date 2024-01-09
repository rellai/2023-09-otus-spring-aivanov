package ru.otus.aivanov.home10.dto;

import ru.otus.aivanov.home10.models.Comment;


/**
 * DTO for {@link Comment}
 */
public record CommentDto(Long id, String text)  {

}