package ru.otus.aivanov.home12.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.aivanov.home12.security.SecurityConfiguration;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
@Import(SecurityConfiguration.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void listShouldRenderTemplateList() throws Exception {

        this.mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("List of all books")));

    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void listShouldRenderTemplateEdit() throws Exception {

        this.mvc.perform(get("/book/edit/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Title")));

    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void listShouldRenderTemplateEdit4New() throws Exception {

        this.mvc.perform(get("/book/new"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Title")));

    }


}