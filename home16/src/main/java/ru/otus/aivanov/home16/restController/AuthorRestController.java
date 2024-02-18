package ru.otus.aivanov.home16.restController;

import lombok.RequiredArgsConstructor;
import ru.otus.aivanov.home16.dto.AuthorDto;
import ru.otus.aivanov.home16.services.AuthorService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AuthorRestController {

    private final AuthorService authorService;

    @GetMapping("/api/authors")
    public List<AuthorDto> getAllAuthors() {
        return authorService.findAll();
    }

}
