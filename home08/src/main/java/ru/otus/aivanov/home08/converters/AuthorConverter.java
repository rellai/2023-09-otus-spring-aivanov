package ru.otus.aivanov.home08.converters;

import org.springframework.stereotype.Component;

import ru.otus.aivanov.home08.models.Author;

@Component
public class AuthorConverter {
    public String authorToString(Author author) {
        return "Id: %s, FullName: %s".formatted(author.getId(), author.getName());
    }
}
