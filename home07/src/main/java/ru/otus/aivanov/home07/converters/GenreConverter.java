package ru.otus.aivanov.home07.converters;

import org.springframework.stereotype.Component;

import ru.otus.aivanov.home07.models.Genre;

@Component
public class GenreConverter {
    public String genreToString(Genre genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getName());
    }
}
