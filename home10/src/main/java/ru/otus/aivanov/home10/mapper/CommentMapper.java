package ru.otus.aivanov.home10.mapper;

import org.mapstruct.Mapper;
import ru.otus.aivanov.home10.dto.CommentDto;

import ru.otus.aivanov.home10.models.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto toDto(Comment comment);

}
