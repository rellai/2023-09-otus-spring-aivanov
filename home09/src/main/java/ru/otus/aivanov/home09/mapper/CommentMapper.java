package ru.otus.aivanov.home09.mapper;

import org.mapstruct.Mapper;
import ru.otus.aivanov.home09.dto.CommentDto;

import ru.otus.aivanov.home09.models.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto toDto(Comment comment);

}
