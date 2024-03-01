package ru.otus.aivanov.home16.restController;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import ru.otus.aivanov.home16.dto.BookCreateDto;
import ru.otus.aivanov.home16.dto.BookDto;
import ru.otus.aivanov.home16.dto.BookFullDto;
import ru.otus.aivanov.home16.restController.BookRestController;
import ru.otus.aivanov.home16.security.SecurityConfiguration;
import ru.otus.aivanov.home16.services.BookService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookRestController.class)
@Import(SecurityConfiguration.class)
class BookRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;


    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        given(bookService.create(any(BookCreateDto.class))).willReturn(new BookDto(1L, "Книга", 1L, 1L));
    }


    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void listShouldRenderBooks() throws Exception {
        val books = List.of(
                new BookFullDto(1L, "Book1", "Иван", "Horror"),
                new BookFullDto(1L, "Book2", "Иван", "Horror")
        );
        when(bookService.findAll()).thenReturn(books);

        this.mvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Book1")))
                .andExpect(content().string(containsString("Book2")));

    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void editSaveShouldCallModifyMethodOfBookService() throws Exception {

        this.mvc.perform(put("/api/books/1")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(new BookDto(1L, "Книга", 1L, 1L)))


        ).andExpect(status().isOk());


        verify(bookService).update(new BookDto(1L, "Книга", 1L, 1L));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void createSaveShouldCallCreateMethodOfBookService() throws Exception {

        this.mvc.perform(post("/api/books")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(new BookCreateDto("Книга",  1L, 1L)))
        ).andExpect(status().isCreated());
        verify(bookService).create(new BookCreateDto("Книга",  1L, 1L));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void removeShouldCallRemoveMethodOfBookService() throws Exception {
        this.mvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());

        verify(bookService).deleteById(1);
    }

    @Test
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    void removeShouldNotCallRemoveMethodOfBookService() throws Exception {
        this.mvc.perform(delete("/api/books/1"))
                .andExpect(status().isForbidden());

    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void shouldRenderBook() throws Exception {
        val book = new BookDto(1L, "Book1", 1L, 1L);
        when(bookService.findById(1L)).thenReturn(book);

        this.mvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Book1")));
    }

    @Test
    void shouldReturnNonAuthorizedUser() throws Exception {
        val book = new BookDto(1L, "Book1", 1L, 1L);
        when(bookService.findById(1L)).thenReturn(book);

        this.mvc.perform(get("/api/books/1"))
                .andExpect(status().isUnauthorized());
    }


}