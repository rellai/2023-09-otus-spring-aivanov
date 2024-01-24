package ru.otus.aivanov.home11.mapper;

import org.mapstruct.Mapper;

import ru.otus.aivanov.home11.dto.CommentCreateDto;
import ru.otus.aivanov.home11.dto.CommentDto;
import ru.otus.aivanov.home11.dto.CommentUpdateDto;
import ru.otus.aivanov.home11.models.Book;
import ru.otus.aivanov.home11.models.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto toDto(Comment comment);

    default Comment toModel(CommentUpdateDto comment) {
        return new Comment (comment.id(),
                comment.text(),
                new Book());
    }

    default Comment toModel(CommentCreateDto comment) {
        return new Comment (null,
                comment.text(),
                new Book(comment.bookId(), null, null, null));
    }

}
