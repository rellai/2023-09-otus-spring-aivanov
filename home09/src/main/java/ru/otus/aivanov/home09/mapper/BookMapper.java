package ru.otus.aivanov.home09.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.otus.aivanov.home09.dto.BookDto;
import ru.otus.aivanov.home09.dto.BookShowDto;
import ru.otus.aivanov.home09.dto.BookUpdateDto;
import ru.otus.aivanov.home09.models.Book;

@Mapper(componentModel = "spring", uses = {CommentMapper.class})
public interface BookMapper {

    @Mappings({
            @Mapping(target = "authorId", source = "book.author.id"),
            @Mapping(target = "genreId", source = "book.genre.id")
    })
    BookDto toDto(Book book);

    @Mappings({
            @Mapping(target = "author", source = "book.author.name"),
            @Mapping(target = "genre", source = "book.genre.name")
    })
    BookShowDto toShowDto(Book book);

    BookUpdateDto toDto(BookDto book);



}
