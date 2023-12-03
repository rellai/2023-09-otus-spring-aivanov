package ru.otus.aivanov.home06.converters;

import org.springframework.stereotype.Component;
import ru.otus.aivanov.home06.models.Genre;

@Component
public class GenreConverter {
    public String genreToString(Genre genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getName());
    }
}
