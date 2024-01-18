package ru.otus.aivanov.home11.mapper;

import io.r2dbc.spi.Readable;
import org.mapstruct.Mapper;

import ru.otus.aivanov.home11.dto.CommentDto;
import ru.otus.aivanov.home11.models.Book;
import ru.otus.aivanov.home11.models.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto toDto(Comment comment);

    default Comment toModel(Readable row) {
        return new Comment (
                row.get("id", Long.class),
                row.get("text", String.class),
                new Book(row.get("book_id", Long.class),row.get("book_title", String.class), null, null)
        );
    }

}
