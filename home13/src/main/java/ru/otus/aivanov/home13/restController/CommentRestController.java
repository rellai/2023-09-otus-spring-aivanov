package ru.otus.aivanov.home13.restController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.aivanov.home13.dto.CommentCreateDto;
import ru.otus.aivanov.home13.dto.CommentDto;
import ru.otus.aivanov.home13.dto.CommentUpdateDto;
import ru.otus.aivanov.home13.services.CommentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentRestController {

    private final CommentService commentService;

    @GetMapping("/api/comments")
    public List<CommentDto> getAllComments(@RequestParam("bookId") long bookId) {
        return commentService.findAllByBook(bookId);
    }

    @DeleteMapping("/api/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("id") long id) {
        commentService.deleteById(id);
    }

    @PostMapping("/api/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@Valid @RequestBody CommentCreateDto comment) {
        return commentService.create(comment);
    }

    @PutMapping("/api/comments/{id}")
    public CommentDto updateComment(@PathVariable("id") long id,
                                           @Valid @RequestBody CommentUpdateDto comment) {
        return commentService.update(comment);

    }
}
