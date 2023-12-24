package ru.otus.aivanov.home09.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.aivanov.home09.dto.AuthorDto;
import ru.otus.aivanov.home09.dto.BookDto;
import ru.otus.aivanov.home09.dto.BookShowDto;
import ru.otus.aivanov.home09.dto.GenreDto;
import ru.otus.aivanov.home09.dto.BookUpdateDto;
import ru.otus.aivanov.home09.dto.BookCreateDto;
import ru.otus.aivanov.home09.mapper.BookMapper;
import ru.otus.aivanov.home09.services.AuthorService;
import ru.otus.aivanov.home09.services.BookService;
import ru.otus.aivanov.home09.services.CommentService;
import ru.otus.aivanov.home09.services.GenreService;
import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final GenreService genreService;

    private final AuthorService authorService;

    private final CommentService commentService;

    private final BookMapper bookMapper;


    private Entities getGetEntities() {
        List<GenreDto> genres = genreService.findAll();
        List<AuthorDto> authors = authorService.findAll();
        return new Entities(genres, authors);
    }

    private record Entities(List<GenreDto> genres, List<AuthorDto> authors) {
    }

    @GetMapping("/")
    public String list(Model model) {
        List<BookShowDto> books = bookService.findAll();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/book/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        BookDto book =   bookService.findById(id);
        Entities entities = getGetEntities();
        model.addAttribute("referer", "/book/edit/" + id);
        model.addAttribute("book", book);
        model.addAttribute("genres", entities.genres());
        model.addAttribute("authors", entities.authors());
        model.addAttribute("comments", commentService.findAllByBook(id));

        return "book/edit";
    }

    @GetMapping("/book/new")
    public String edit(Model model) {
        BookDto book = new BookDto();
        Entities entities = getGetEntities();
        model.addAttribute("referer", "/book/new");
        model.addAttribute("book", book);
        model.addAttribute("genres", entities.genres);
        model.addAttribute("authors", entities.authors);
        model.addAttribute("comments", null);
        return "book/edit";
    }

    @PostMapping("/book/edit/{id}")
    public String update(@PathVariable("id") long id, @Valid @ModelAttribute("book") BookUpdateDto book,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Entities entities = getGetEntities();
            model.addAttribute("referer", "/book/edit/" + id);
            model.addAttribute("genres", entities.genres);
            model.addAttribute("authors", entities.authors);
            model.addAttribute("comments", commentService.findAllByBook(id));


            return "book/edit";
        }
        bookService.update(book);
        return "redirect:/";
    }

    @PostMapping("/book/new")
    public String create(@Valid @ModelAttribute("book") BookCreateDto book,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Entities entities = getGetEntities();
            model.addAttribute("referer", "/book/new");
            model.addAttribute("genres", entities.genres);
            model.addAttribute("authors", entities.authors);
            model.addAttribute("comments", null);
            return "book/edit";
        }
        bookService.create(book);
        return "redirect:/";
    }

    @PostMapping("/book/remove/{id}")
    public String remove(@PathVariable("id") long id) {
        bookService.deleteById(id);
        return "redirect:/";
    }



}
