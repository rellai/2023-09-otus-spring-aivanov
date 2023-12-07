package ru.otus.aivanov.home07.shell;

import lombok.RequiredArgsConstructor;
import ru.otus.aivanov.home07.converters.BookConverter;
import ru.otus.aivanov.home07.services.BookService;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class BookCommands {

    private final BookService bookService;

    private final BookConverter bookConverter;

    @ShellMethod(value = "Find all books", key = "ab")
    public String findAllBooks() {
        return bookService.findAll().stream()
                .map(bookConverter::bookToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "Find book by id", key = "bbid")
    public String findBookById(long id) {
        return bookService.findById(id)
                .map(bookConverter::bookToString)
                .orElse("Book with id %d not found".formatted(id));
    }


    @ShellMethod(value = "Find book with comments by id", key = "bcbid")
    public String findBookWCommentsById(long id) {
        return bookService.findFullById(id)
                .map(bookConverter::bookWithCommentsToString)
                .orElse("Book with id %d not found".formatted(id));
    }

    @ShellMethod(value = "Insert book", key = "bins")
    public String insertBook(String title, long authorId, long genreId) {
        var savedBook = bookService.insert(title, authorId, genreId);
        return bookConverter.bookToString(savedBook);
    }

    @ShellMethod(value = "Update book", key = "bupd")
    public String updateBook(long id, String title, long authorId, long genreId) {
        var savedBook = bookService.update(id, title, authorId, genreId);
        return bookConverter.bookToString(savedBook);
    }

    @ShellMethod(value = "Delete book by id", key = "bdel")
    public String updateBook(long id) {
        bookService.deleteById(id);
        return String.format("Book with id=%d has been deleted", id);
    }
}
