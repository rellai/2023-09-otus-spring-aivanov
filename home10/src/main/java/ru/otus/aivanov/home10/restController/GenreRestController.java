package ru.otus.aivanov.home10.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.aivanov.home10.dto.GenreDto;
import ru.otus.aivanov.home10.services.GenreService;

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
