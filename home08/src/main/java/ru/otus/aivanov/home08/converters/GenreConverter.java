package ru.otus.aivanov.home08.converters;

import org.springframework.stereotype.Component;

import ru.otus.aivanov.home08.models.Genre;

@Component
public class GenreConverter {
    public String genreToString(Genre genre) {
        return "Id: %s, Name: %s".formatted(genre.getId(), genre.getName());
    }
}
