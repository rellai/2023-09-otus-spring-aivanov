package ru.otus.aivanov.home11.restController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.aivanov.home11.dto.BookCreateDto;
import ru.otus.aivanov.home11.dto.BookDto;
import ru.otus.aivanov.home11.dto.BookFullDto;
import ru.otus.aivanov.home11.mapper.BookMapper;
import ru.otus.aivanov.home11.repositories.BookRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BookRestController {



    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @GetMapping("/api/books")
    public Flux<BookFullDto> getAllBooks() {
        return bookRepository.findAllBooks()
                .map(bookMapper::toDto);
    }

    @GetMapping("/api/books/{id}")
    public Mono<ResponseEntity<BookDto>> getBook(@PathVariable("id") long id) {
        return bookRepository.findByBookId(id)
                .map(bookMapper::toEditDto)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/api/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteBook(@PathVariable("id") long id) {
        return bookRepository.deleteById(id);
    }

    @PostMapping("/api/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BookDto> createBook(@Valid @RequestBody BookCreateDto book) {
       return bookRepository.save(book.title(), book.authorId(), book.genreId())
               .map(bookMapper::toEditDto);
    }

    @PutMapping("/api/books/{id}")
    public Mono<BookDto> updateBook(@PathVariable("id") long id,
                              @Valid @RequestBody BookDto book) {
        return bookRepository.save(book.id(), book.title(), book.authorId(), book.genreId())
                .map(bookMapper::toEditDto);
    }
}
