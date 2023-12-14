package ru.otus.aivanov.home08.shell;

import lombok.RequiredArgsConstructor;
import ru.otus.aivanov.home08.converters.BookConverter;
import ru.otus.aivanov.home08.services.BookService;

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
    public String findBookById(String id) {
        return bookService.findById(id)
                .map(bookConverter::bookToString)
                .orElse("Book with id %s not found".formatted(id));
    }


    @ShellMethod(value = "Insert book", key = "bins")
    public String insertBook(String title, String authorId, String genreId) {
        var savedBook = bookService.insert(title, authorId, genreId);
        return bookConverter.bookToString(savedBook);
    }

    @ShellMethod(value = "Update book", key = "bupd")
    public String updateBook(String id, String title, String authorId, String genreId) {
        var savedBook = bookService.update(id, title, authorId, genreId);
        return bookConverter.bookToString(savedBook);
    }

    @ShellMethod(value = "Delete book by id", key = "bdel")
    public String updateBook(String id) {
        bookService.deleteById(id);
        return String.format("Book with id=%s has been deleted", id);
    }
}
