package ru.otus.aivanov.home11.mapper;


import io.r2dbc.spi.Readable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import ru.otus.aivanov.home11.dto.BookCreateDto;
import ru.otus.aivanov.home11.dto.BookDto;
import ru.otus.aivanov.home11.dto.BookFullDto;
import ru.otus.aivanov.home11.models.Author;
import ru.otus.aivanov.home11.models.Book;
import ru.otus.aivanov.home11.models.Genre;


@SuppressWarnings("checkstyle:RightCurly")
@Mapper(componentModel = "spring", uses = {CommentMapper.class})
public interface BookMapper {

    @Mappings({
            @Mapping(target = "authorId", source = "book.author.id"),
            @Mapping(target = "genreId", source = "book.genre.id")
    })
    BookDto toEditDto(Book book);

    @Mappings({
            @Mapping(target = "author", source = "book.author.name"),
            @Mapping(target = "genre", source = "book.genre.name")
    })
    BookFullDto toDto(Book book);


    default Book toModel(Readable row) {
        return new Book (
                row.get("id", Long.class),
                row.get("title", String.class),
                new Author(row.get("author_id", Long.class),row.get("author_name", String.class)),
                new Genre(row.get("genre_id", Long.class),row.get("genre_name", String.class))
        );
    }


    @Mapping(target = "author", source = "author")
    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "id", ignore = true)
    Book toModel(BookCreateDto book, Author author, Genre genre);

}
