package ru.otus.aivanov.home05.converters;

import org.springframework.stereotype.Component;
import ru.otus.aivanov.home05.models.Author;

@Component
public class AuthorConverter {
    public String authorToString(Author author) {
        return "Id: %d, FullName: %s".formatted(author.getId(), author.getName());
    }
}
