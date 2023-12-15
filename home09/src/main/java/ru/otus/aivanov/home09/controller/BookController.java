package ru.otus.aivanov.home09.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.aivanov.home09.dto.BookDto;
import ru.otus.aivanov.home09.models.Author;
import ru.otus.aivanov.home09.models.Book;
import ru.otus.aivanov.home09.models.Genre;
import ru.otus.aivanov.home09.repositories.AuthorRepository;
import ru.otus.aivanov.home09.repositories.BookRepository;
import ru.otus.aivanov.home09.exceptions.EntityNotFoundException;
import ru.otus.aivanov.home09.repositories.GenreRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    private final GenreRepository genreRepository;

    private final AuthorRepository authorRepository;

    @GetMapping("/")
    public String listBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "listBooks";
    }

    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") long id, Model model) {
        Book book = bookRepository.findWithCommentsById(id).orElseThrow(EntityNotFoundException::new);
        List<Genre> genres = genreRepository.findAll();
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "editBook";
    }

    @PostMapping("/editBook/{id}")
    public String saveBook(@PathVariable("id") long id, @Valid @ModelAttribute("book") BookDto book,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "editBook";
        }
        bookRepository.save(book.toDomainObject());
        return "redirect:/";
    }

}
