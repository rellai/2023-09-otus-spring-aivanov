package ru.otus.aivanov.home13.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.aivanov.home13.dto.AuthorDto;
import ru.otus.aivanov.home13.services.AuthorService;

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
