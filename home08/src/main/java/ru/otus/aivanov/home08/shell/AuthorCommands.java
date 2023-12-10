package ru.otus.aivanov.home08.shell;

import lombok.RequiredArgsConstructor;
import ru.otus.aivanov.home08.converters.AuthorConverter;
import ru.otus.aivanov.home08.models.Author;
import ru.otus.aivanov.home08.services.AuthorService;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class AuthorCommands {

    private final AuthorService authorService;

    private final AuthorConverter authorConverter;

    @ShellMethod(value = "Find all authors", key = "aa")
    public String findAllAuthors() {
        return authorService.findAll().stream()
                .map(authorConverter::authorToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "Delete author", key = "da")
    public String deleteGenre(String id) {
        authorService.deleteById(id);
        return String.format("Author with id=%s has been deleted", id);
    }

    @ShellMethod(value = "Find author by id", key = "fa")
    public String findById(String id) {
        return authorService.findById(id)
                .map(authorConverter::authorToString)
                .orElse(String.format("Author with id = %s not found", id));
    }

    @ShellMethod(value = "Save author", key = "sa")
    public String saveGenre(String name) {
        Author author = new Author();
        author.setName(name);
        return authorConverter.authorToString(authorService.save(author));
    }

    @ShellMethod(value = "Update genre", key = "ua")
    public String updateGenre(String id, String name) {
        return authorConverter.authorToString(authorService.save(new Author(id, name)));
    }
}
