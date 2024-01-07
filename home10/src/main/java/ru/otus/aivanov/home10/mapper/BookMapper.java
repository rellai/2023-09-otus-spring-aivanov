package ru.otus.aivanov.home10.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.otus.aivanov.home10.dto.BookCreateDto;
import ru.otus.aivanov.home10.dto.BookUpdateDto;
import ru.otus.aivanov.home10.dto.BookDto;
import ru.otus.aivanov.home10.models.Author;
import ru.otus.aivanov.home10.models.Book;
import ru.otus.aivanov.home10.models.Genre;

@Mapper(componentModel = "spring", uses = {CommentMapper.class})
public interface BookMapper {

    @Mappings({
            @Mapping(target = "authorId", source = "book.author.id"),
            @Mapping(target = "genreId", source = "book.genre.id")
    })
    BookUpdateDto toEditDto(Book book);

    @Mappings({
            @Mapping(target = "author", source = "book.author.name"),
            @Mapping(target = "genre", source = "book.genre.name")
    })
    BookDto toDto(Book book);

    @Mapping(target = "author", source = "author")
    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "id", ignore = true)
    Book toModel(BookCreateDto book, Author author, Genre genre);

}
