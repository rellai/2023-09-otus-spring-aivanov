package ru.otus.aivanov.home11.restController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import ru.otus.aivanov.home11.dto.AuthorDto;
import ru.otus.aivanov.home11.mapper.AuthorMapper;
import ru.otus.aivanov.home11.repositories.AuthorRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthorRestController {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;


    @GetMapping("/api/authors")
    public Flux<AuthorDto> getAllAuthors() {
        return authorRepository.findAll()
                .map(authorMapper::toDto);
    }

}
