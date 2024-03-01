package ru.otus.aivanov.home16.restController;

import lombok.RequiredArgsConstructor;
import ru.otus.aivanov.home16.dto.GenreDto;
import ru.otus.aivanov.home16.services.GenreService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GenreRestController {

    private final GenreService genreService;

    @GetMapping("/api/genres")
    public List<GenreDto> getAllGenres() {
        return genreService.findAll();
    }

}
