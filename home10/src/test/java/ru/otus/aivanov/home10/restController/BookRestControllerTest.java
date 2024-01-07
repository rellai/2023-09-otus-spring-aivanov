package ru.otus.aivanov.home10.restController;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.aivanov.home10.dto.BookCreateDto;
import ru.otus.aivanov.home10.dto.BookDto;
import ru.otus.aivanov.home10.dto.BookUpdateDto;
import ru.otus.aivanov.home10.mapper.AuthorMapper;
import ru.otus.aivanov.home10.mapper.AuthorMapperImpl;
import ru.otus.aivanov.home10.mapper.BookMapper;
import ru.otus.aivanov.home10.mapper.BookMapperImpl;
import ru.otus.aivanov.home10.mapper.CommentMapper;
import ru.otus.aivanov.home10.mapper.CommentMapperImpl;
import ru.otus.aivanov.home10.mapper.GenreMapper;
import ru.otus.aivanov.home10.mapper.GenreMapperImpl;
import ru.otus.aivanov.home10.services.AuthorService;
import ru.otus.aivanov.home10.services.BookService;
import ru.otus.aivanov.home10.services.CommentService;
import ru.otus.aivanov.home10.services.GenreService;

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
class BookRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private CommentService commentService;

    @SpyBean(BookMapperImpl.class)
    private BookMapper bookMapper;

    @SpyBean(GenreMapperImpl.class)
    private GenreMapper genreMapper;

    @SpyBean(AuthorMapperImpl.class)
    private AuthorMapper authorMapper;

    @SpyBean(CommentMapperImpl.class)
    private CommentMapper commentMapper;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        given(bookService.create(any(BookCreateDto.class))).willReturn(new BookUpdateDto(1L, "Книга", 1L, 1L));
    }


    @Test
    void listShouldRenderBooks() throws Exception {
        val books = List.of(
                new BookDto(1L, "Book1", "Иван", "Horror"),
                new BookDto(1L, "Book2", "Иван", "Horror")
        );
        when(bookService.findAll()).thenReturn(books);


        this.mvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Book1")))
                .andExpect(content().string(containsString("Book2")));



    }

    @Test
    void editSaveShouldCallModifyMethodOfBookService() throws Exception {

        this.mvc.perform(put("/api/books/1")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(new BookUpdateDto(1L, "Книга", 1L, 1L)))

        ).andExpect(status().isOk());


        verify(bookService).update(new BookUpdateDto(1L, "Книга", 1L, 1L));
    }

    @Test
    void createSaveShouldCallCreateMethodOfBookService() throws Exception {

        this.mvc.perform(post("/api/books")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(new BookCreateDto("Книга",  1L, 1L)))
        ).andExpect(status().isCreated());


        verify(bookService).create(new BookCreateDto("Книга",  1L, 1L));
    }

    @Test
    void removeShouldCallRemoveMethodOfBookService() throws Exception {
        this.mvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());

        verify(bookService).deleteById(1);
    }

}