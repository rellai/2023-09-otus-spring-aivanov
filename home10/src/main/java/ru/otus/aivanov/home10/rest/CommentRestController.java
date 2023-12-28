package ru.otus.aivanov.home10.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.aivanov.home10.dto.CommentDto;
import ru.otus.aivanov.home10.services.CommentService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentRestController {

    private final CommentService commentService;

    @GetMapping("/api/comments")
    public List<CommentDto> getAllComments(@RequestParam("bookId") long bookId) {
        return new ArrayList<>(commentService.findAllByBook(bookId));
    }

}
