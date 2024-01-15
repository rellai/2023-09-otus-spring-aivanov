package ru.otus.aivanov.home11.restController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import ru.otus.aivanov.home11.dto.GenreDto;
import ru.otus.aivanov.home11.mapper.GenreMapper;
import ru.otus.aivanov.home11.repositories.GenreRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GenreRestController {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @GetMapping("/api/genres")
    public Flux<GenreDto> getAllGenres() {
        return  genreRepository.findAll()
                .map(genreMapper::toDto);

    }

}
