package ru.otus.aivanov.home11.restController;

import org.springframework.boot.test.mock.mockito.SpyBean;
import ru.otus.aivanov.home11.mapper.GenreMapper;
import ru.otus.aivanov.home11.mapper.GenreMapperImpl;
import ru.otus.aivanov.home11.repositories.GenreRepository;
import ru.otus.aivanov.home11.models.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.List;


@WebFluxTest(GenreRestController.class)
class GenreRestControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private GenreRepository genreRepository;

    @SpyBean(GenreMapperImpl.class)
    private GenreMapper genreMapper;

    @Test
    void listShouldRenderGenres() throws Exception {
        List<Genre> genres = List.of(new Genre(1L, "Genre1"), new Genre(2L, "Genre2"));

        Flux<Genre> genresFlux = Flux.fromIterable(genres);

        Mockito.when(genreRepository.findAll()).thenReturn(genresFlux);

        webTestClient.get().uri("/api/genres")
                .exchange()
                .expectStatus().isOk();
    }


}