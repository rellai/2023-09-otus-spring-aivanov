package ru.otus.aivanov.home11.restController;

import org.springframework.boot.test.mock.mockito.SpyBean;
import ru.otus.aivanov.home11.mapper.AuthorMapper;
import ru.otus.aivanov.home11.mapper.AuthorMapperImpl;
import ru.otus.aivanov.home11.repositories.AuthorRepository;
import ru.otus.aivanov.home11.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.List;



@WebFluxTest(AuthorRestController.class)
class AuthorRestControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AuthorRepository authorRepository;

    @SpyBean(AuthorMapperImpl.class)
    private AuthorMapper authorMapper;

    @Test
    void listShouldRenderAuthors() throws Exception {
        List<Author> authors = List.of(new Author(1L, "Author1"), new Author(2L, "Author2"));

        Flux<Author> authorsFlux = Flux.fromIterable(authors);

        Mockito.when(authorRepository.findAll()).thenReturn(authorsFlux);

        webTestClient.get().uri("/api/authors")
                .exchange()
                .expectStatus().isOk();
    }

}
