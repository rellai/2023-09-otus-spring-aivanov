package ru.otus.aivanov.home17.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.aivanov.home17.dto.CommentCreateDto;
import ru.otus.aivanov.home17.dto.CommentDto;

import ru.otus.aivanov.home17.models.Book;
import ru.otus.aivanov.home17.models.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto toDto(Comment comment);

    @Mapping(target = "book", source = "book")
    @Mapping(target = "id", ignore = true)
    Comment toModel(CommentCreateDto comment, Book book);

}
