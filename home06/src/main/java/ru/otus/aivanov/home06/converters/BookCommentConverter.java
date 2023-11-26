package ru.otus.aivanov.home06.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.aivanov.home06.dto.BookDto;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BookCommentConverter {

    private final AuthorConverter authorConverter;

    private final GenreConverter genreConverter;

    private final CommentConverter commentConverter;

    public String bookToString(BookDto book) {
        return "Id: %d, title: %s, author: {%s}, genres: [%s], comments: [%s]".formatted(
                book.id(),
                book.title(),
                authorConverter.authorToString(book.author()),
                genreConverter.genreToString(book.genre()),
                book.comments().stream().map(commentConverter::commentToString).collect(Collectors.joining()));
    }

}
