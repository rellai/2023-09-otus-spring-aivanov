package ru.otus.aivanov.home11.dto;

import ru.otus.aivanov.home11.models.Comment;


/**
 * DTO for {@link Comment}
 */
public record CommentDto(Long id, String text)  {

}