package ru.otus.aivanov.home16.restController;

import lombok.val;
import ru.otus.aivanov.home16.dto.GenreDto;
import ru.otus.aivanov.home16.restController.GenreRestController;
import ru.otus.aivanov.home16.services.GenreService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GenreRestController.class)
class GenreRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GenreService genreService;

    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @Test
    void listShouldRenderGenres() throws Exception {
        val genres = List.of(
                new GenreDto(1L, "Skazka"),
                new GenreDto(1L, "Detectiv")
        );
        when(genreService.findAll()).thenReturn(genres);


        this.mvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Skazka")))
                .andExpect(content().string(containsString("Detectiv")));



    }


}