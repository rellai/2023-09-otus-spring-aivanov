package ru.otus.aivanov.home09.controller;

import jakarta.servlet.http.HttpServletRequest;
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
import ru.otus.aivanov.home09.dto.GenreDto;
import ru.otus.aivanov.home09.models.Book;
import ru.otus.aivanov.home09.exceptions.EntityNotFoundException;
import ru.otus.aivanov.home09.services.AuthorService;
import ru.otus.aivanov.home09.services.BookService;
import ru.otus.aivanov.home09.services.GenreService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final GenreService genreService;

    private final AuthorService authorService;

    private final HttpServletRequest request;

    @GetMapping("/")
    public String list(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/book/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        BookDto book =   BookDto.toDTO(bookService.findFullById(id).orElseThrow(EntityNotFoundException::new)) ;
        List<GenreDto> genres = genreService.findAll().stream().map(GenreDto::toDto).toList();
        List<AuthorDto> authors = authorService.findAll().stream().map(AuthorDto::toDto).toList();
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        model.addAttribute("referer", "/book/edit/" + id);
        return "book/edit";
    }

    @GetMapping("/book/new")
    public String edit(Model model) {
        BookDto book = new BookDto(null, "", null, null, null);
        List<GenreDto> genres = genreService.findAll().stream().map(GenreDto::toDto).toList();
        List<AuthorDto> authors = authorService.findAll().stream().map(AuthorDto::toDto).toList();
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        model.addAttribute("referer", "/book/new");
        return "book/edit";
    }

    @PostMapping("/book/edit/{id}")
    public String update(@PathVariable("id") long id, @Valid @ModelAttribute("book") BookDto book,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "book/edit";
        }
        bookService.update(book.id(),book.title(), book.author(), book.genre());
        return "redirect:/";
    }

    @PostMapping("/book/new")
    public String create(@Valid @ModelAttribute("book") BookDto book,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "book/edit";
        }
        bookService.insert(book.title(), book.author(), book.genre());
        return "redirect:/";
    }

    @PostMapping("/book/remove/{id}")
    public String remove(@PathVariable("id") long id) {
        bookService.deleteById(id);
        return "redirect:/";
    }

}
