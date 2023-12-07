package ru.otus.aivanov.home07.shell;

import lombok.RequiredArgsConstructor;
import ru.otus.aivanov.home07.converters.CommentConverter;
import ru.otus.aivanov.home07.exceptions.EntityNotFoundException;
import ru.otus.aivanov.home07.services.CommentService;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class CommentCommands {

    private final CommentService commentService;

    private final CommentConverter commentConverter;

    @ShellMethod(value = "Find all comments by book", key = "ac")
    public String findAllCommentsByBook(Long bookId) {
        return commentService.findAllByBook(bookId).stream()
                .map(commentConverter::commentToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "Delete comment", key = "dc")
    public String deleteComment(long id) {
        try {
          commentService.deleteById(id);
          return String.format("Comment with id=%d has been deleted", id);
        } catch (EntityNotFoundException error) {
            return error.getMessage();
        }
    }

    @ShellMethod(value = "Delete comment by Book id", key = "dcbbid")
    public String deleteCommentByBookId(long bookId) {
        try {
            commentService.deleteByBookId(bookId);
            return String.format("All comment with bookId=%d has been deleted", bookId);
        } catch (EntityNotFoundException error) {
            return error.getMessage();
        }
    }

    @ShellMethod(value = "Find comment by id", key = "fc")
    public String findById(long id) {
        return commentService.findById(id)
                .map(commentConverter::commentToString)
                .orElse(String.format("All comments with bookId = %s not found", id));
    }

    @ShellMethod(value = "New comment", key = "nc")
    public String newComment(long bookId, String text) {
        try {
            return commentConverter.commentToString(commentService.create(bookId, text));
        } catch (EntityNotFoundException error) {
            return error.getMessage();
        }
    }

    @ShellMethod(value = "Update comment", key = "uc")
    public String updateComment(long id, String text) {
        try {
            return commentConverter.commentToString(commentService.update(id, text));
        } catch (EntityNotFoundException error) {
            return error.getMessage();
        }
    }
}
