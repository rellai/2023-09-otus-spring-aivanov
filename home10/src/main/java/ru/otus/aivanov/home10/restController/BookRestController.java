package ru.otus.aivanov.home10.restController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.aivanov.home10.dto.BookCreateDto;
import ru.otus.aivanov.home10.dto.BookDto;
import ru.otus.aivanov.home10.dto.BookFullDto;
import ru.otus.aivanov.home10.services.BookService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/api/books")
    public List<BookFullDto> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/api/books/{id}")
    public BookDto getAllBooks(@PathVariable("id") long id) {
        return bookService.findById(id);
    }

    @DeleteMapping("/api/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("id") long id) {
        bookService.deleteById(id);
    }

    @PostMapping("/api/books")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@Valid @RequestBody BookCreateDto book) {
        return bookService.create(book);
    }

    @PutMapping("/api/books/{id}")
    public BookDto updateBook(@PathVariable("id") long id,
                              @Valid @RequestBody BookDto book) {
            return bookService.update(book);
    }
}
