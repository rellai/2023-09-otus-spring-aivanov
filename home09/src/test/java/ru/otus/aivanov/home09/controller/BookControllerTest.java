package ru.otus.aivanov.home09.controller;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.aivanov.home09.dto.AuthorDto;
import ru.otus.aivanov.home09.dto.BookDto;
import ru.otus.aivanov.home09.dto.BookShowDto;
import ru.otus.aivanov.home09.dto.GenreDto;
import ru.otus.aivanov.home09.dto.BookUpdateDto;
import ru.otus.aivanov.home09.dto.BookCreateDto;
import ru.otus.aivanov.home09.mapper.AuthorMapper;
import ru.otus.aivanov.home09.mapper.AuthorMapperImpl;
import ru.otus.aivanov.home09.mapper.BookMapper;
import ru.otus.aivanov.home09.mapper.BookMapperImpl;
import ru.otus.aivanov.home09.mapper.CommentMapper;
import ru.otus.aivanov.home09.mapper.CommentMapperImpl;
import ru.otus.aivanov.home09.mapper.GenreMapper;
import ru.otus.aivanov.home09.mapper.GenreMapperImpl;
import ru.otus.aivanov.home09.services.AuthorService;
import ru.otus.aivanov.home09.services.BookService;
import ru.otus.aivanov.home09.services.GenreService;

import java.util.List;

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

    @SpyBean(BookMapperImpl.class)
    private BookMapper bookMapper;

    @SpyBean(GenreMapperImpl.class)
    private GenreMapper genreMapper;

    @SpyBean(AuthorMapperImpl.class)
    private AuthorMapper authorMapper;

    @SpyBean(CommentMapperImpl.class)
    private CommentMapper commentMapper;

    @Test
    void listShouldRenderBooks() throws Exception {
        val books = List.of(
                new BookShowDto(1L, "Book1", "Иван", "Horror"),
                new BookShowDto(1L, "Book2", "Иван", "Horror")
        );
        when(bookService.findAll()).thenReturn(books);

        this.mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Book1")))
                .andExpect(content().string(containsString("Book2")));
    }

    @Test
    void editShouldRenderBookWithValidSelectedOptions() throws Exception {
        val book = new BookDto(1L, "Book1", 2L, 2L, java.util.Collections.emptyList());
        when(bookService.findFullById(1L)).thenReturn(book);
        when(authorService.findAll()).thenReturn(List.of(
                new AuthorDto(1L, "Петр"),
                new AuthorDto(2L, "Иван"),
                new AuthorDto(3L, "Вадим")
        ));
        when(genreService.findAll()).thenReturn(List.of(
                new GenreDto(1L, "Научпоп"),
                new GenreDto(2L, "Horror"),
                new GenreDto(3L, "Сказки")
        ));


        this.mvc.perform(get("/book/edit/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Book1")))
                .andExpect(content().string(containsString(
                        "<option value=\"2\" selected=\"selected\">Иван</option>")))
                .andExpect(content().string(containsString(
                        "<option value=\"2\" selected=\"selected\">Horror</option>")));
    }

    @Test
    void editSaveShouldCallModifyMethodOfBookService() throws Exception {
        this.mvc.perform(post("/book/edit/1")
                .param("id", "1")
                .param("title", "Книга")
                .param("genreId", "1")
                .param("authorId", "1")
        ).andExpect(status().is(302));

        verify(bookService).update(new BookUpdateDto(1L, "Книга", 1L, 1L));
    }

    @Test
    void createSaveShouldCallCreateMethodOfBookService() throws Exception {
        this.mvc.perform(post("/book/new")
                .param("title", "Книга")
                .param("genreId", "1")
                .param("authorId", "1")
        ).andExpect(status().is(302));

        verify(bookService).create(new BookCreateDto("Книга",  1L, 1L));
    }

    @Test
    void removeShouldCallRemoveMethodOfBookService() throws Exception {
        this.mvc.perform(post("/book/remove/1"))
                .andExpect(status().is(302));

        verify(bookService).deleteById(1);
    }
}