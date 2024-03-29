package ru.otus.aivanov.home11.restController;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.aivanov.home11.dto.BookCreateDto;
import ru.otus.aivanov.home11.dto.BookDto;
import ru.otus.aivanov.home11.mapper.BookMapper;
import ru.otus.aivanov.home11.mapper.BookMapperImpl;
import ru.otus.aivanov.home11.models.Author;
import ru.otus.aivanov.home11.models.Book;
import ru.otus.aivanov.home11.models.Genre;
import ru.otus.aivanov.home11.repositories.AuthorRepository;
import ru.otus.aivanov.home11.repositories.BookRepository;
import ru.otus.aivanov.home11.repositories.GenreRepository;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@WebFluxTest(BookRestController.class)
public class BookRestControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private AuthorRepository authorRepository;


    @SpyBean(BookMapperImpl.class)
    private BookMapper bookMapper;


    @Test
    void shouldReturnCorrectBooksList() throws Exception {
        List<Book> books = List.of(new Book(1L, "BookTitle1", new Author(1L, "AuthorName1"),
                        new Genre(1L, "genre1")),
                new Book(2L, "BookTitle2", new Author(2L, "Author2"),
                        new Genre(2L, "genre2")));

        Flux<Book> booksFlux = Flux.fromIterable(books);

        Mockito.when(bookRepository.findAllBooks()).thenReturn(booksFlux);

        webTestClient.get().uri("/api/books")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void shouldDeleteBook() {
        Mono<Void> voidReturn = Mono.empty();

        Mockito.when(bookRepository.deleteById(1L))
                .thenReturn(voidReturn);

        webTestClient.delete().uri("/api/books/{id}", 1)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void shouldCreateBook() {
        String title = "BookTitle1";

        Genre genre = new Genre(1L, "genre1");
        Mockito.when(genreRepository.findById(1L)).thenReturn(Mono.just(genre));

        Author author = new Author(1L, "AuthorName1");
        Mockito.when(authorRepository.findById(1L)).thenReturn(Mono.just(author));

        Book book = new Book(1L, title, author, genre);
        Mockito.when(bookRepository.saveBook(any(Book.class))).thenReturn(Mono.just(book));


        webTestClient.post()
                .uri("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(new BookCreateDto(title,  1L, 1L)))
                .exchange()
                .expectStatus().isCreated().expectBody(BookDto.class).isEqualTo(bookMapper.toEditDto(book));
    }

    @Test
    public void shouldEditBook() {

        Genre genre = new Genre(1L, "genre1");
        Mockito.when(genreRepository.findById(1L)).thenReturn(Mono.just(genre));

        Author author = new Author(1L, "AuthorName1");
        Mockito.when(authorRepository.findById(1L)).thenReturn(Mono.just(author));

        String title = "BookTitle1";
        Book book = new Book(1L, title, new Author(1L, "AuthorName1"),
                new Genre(1L, "genre1"));

        Mockito.when(bookRepository.saveBook(book)).thenReturn(Mono.just(book));
        Mockito.when(bookRepository.findById(1L)).thenReturn(Mono.just(book));

        webTestClient.put()
                .uri("/api/books/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(new BookDto(1L, title, 1L, 1L)))
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(bookRepository, times(1)).saveBook(book);
    }
}
