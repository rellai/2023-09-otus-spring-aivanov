package ru.otus.aivanov.home09.controller;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.aivanov.home09.dto.BookDto;
import ru.otus.aivanov.home09.exceptions.EntityNotFoundException;
import ru.otus.aivanov.home09.models.Author;
import ru.otus.aivanov.home09.models.Genre;
import ru.otus.aivanov.home09.models.Book;
import ru.otus.aivanov.home09.services.AuthorService;
import ru.otus.aivanov.home09.services.BookService;
import ru.otus.aivanov.home09.services.GenreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Test
    void listShouldRenderBooks() throws Exception {
        val books = List.of(
                new Book(1L, "Book1", new Author(2L, "Иван"), new Genre(2l, "Horror"), null),
                new Book(1L, "Book2", new Author(2L, "Вадим"), new Genre(2l, "Сказки"), null)
        );
        when(bookService.findAll()).thenReturn(books);

        this.mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Book1")))
                .andExpect(content().string(containsString("Book2")));
    }

    @Test
    void editShouldRenderBookWithValidSelectedOptions() throws Exception {
        val book = Optional.of(new Book(1L, "Book1", new Author(2L, "Иван"), new Genre(2L, "Horror"), java.util.Collections.emptyList()));
        when(bookService.findFullById(1L)).thenReturn(book);
        when(authorService.findAll()).thenReturn(List.of(
                new Author(1L, "Петр"),
                new Author(2L, "Иван"),
                new Author(3L, "Вадим")
        ));
        when(genreService.findAll()).thenReturn(List.of(
                new Genre(1L, "Научпоп"),
                new Genre(2L, "Horror"),
                new Genre(3L, "Сказки")
        ));


        this.mvc.perform(get("/book/edit/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Book1")))
                .andExpect(content().string(containsString("<option value=\"2\" selected=\"selected\">Иван</option>")))
                .andExpect(content().string(containsString("<option value=\"2\" selected=\"selected\">Horror</option>")));
    }

    @Test
    void editSaveShouldCallModifyMethodOfBookService() throws Exception {
        this.mvc.perform(post("/book/edit/1")
                .param("id", "1")
                .param("title", "Книга")
                .param("genre", "1")
                .param("author", "1")
        ).andExpect(status().is(302));

        verify(bookService).update(1L, "Книга", 1L, 1L);
    }

    @Test
    void createSaveShouldCallCreateMethodOfBookService() throws Exception {
        this.mvc.perform(post("/book/new")
                .param("title", "Книга")
                .param("genre", "1")
                .param("author", "1")
        ).andExpect(status().is(302));

        verify(bookService).insert("Книга",  1L, 1L);
    }

    @Test
    void removeShouldCallRemoveMethodOfBookService() throws Exception {
        this.mvc.perform(post("/book/remove/1"))
                .andExpect(status().is(302));

        verify(bookService).deleteById(1);
    }
}