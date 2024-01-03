package ru.otus.aivanov.home10.restController;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.aivanov.home10.dto.CommentCreateDto;
import ru.otus.aivanov.home10.dto.CommentDto;
import ru.otus.aivanov.home10.dto.CommentUpdateDto;
import ru.otus.aivanov.home10.mapper.CommentMapper;
import ru.otus.aivanov.home10.mapper.CommentMapperImpl;
import ru.otus.aivanov.home10.services.CommentService;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CommentRestController.class)
class CommentRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CommentService commentService;

    @SpyBean(CommentMapperImpl.class)
    private CommentMapper commentMapper;

    @BeforeEach
    void setUp() {
        given(commentService.create(any(CommentCreateDto.class))).willReturn(new CommentDto(1L, "Test Comment"));
    }


    @Test
    void listShouldRenderComments() throws Exception {
        val comments = List.of(
                new CommentDto(1L, "Comment1"),
                new CommentDto(1L, "Comment2")
        );
        when(commentService.findAllByBook(any(Long.class))).thenReturn(comments);

        this.mvc.perform(get("/api/comments").param("bookId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Comment1")))
                .andExpect(content().string(containsString("Comment2")));
    }

    
    @Test
    void editSaveShouldCallModifyMethodOfCommentService() throws Exception {
        this.mvc.perform(put("/api/comments/1")
        .contentType(APPLICATION_JSON)
        .content(mapper.writeValueAsString(new CommentUpdateDto(1L, "комментарий")))

        ).andExpect(status().isOk());

        verify(commentService).update(new CommentUpdateDto(1L, "комментарий"));
    }

    @Test
    void createSaveShouldCallCreateMethodOfCommentService() throws Exception {
        this.mvc.perform(post("/api/comments")
        .contentType(APPLICATION_JSON)
        .content(mapper.writeValueAsString(new CommentCreateDto(1L, "Комментарий")))
        ).andExpect(status().isCreated());

        verify(commentService).create(new CommentCreateDto(1L, "Комментарий"));
    }

    @Test
    void removeShouldCallRemoveMethodOfCommentService() throws Exception {
        this.mvc.perform(delete("/api/comments/1"))
                .andExpect(status().isOk());

        verify(commentService).deleteById(1);
    }
}