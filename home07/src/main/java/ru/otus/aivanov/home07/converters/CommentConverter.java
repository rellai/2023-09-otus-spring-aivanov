package ru.otus.aivanov.home07.converters;

import org.springframework.stereotype.Component;

import ru.otus.aivanov.home07.models.Comment;

@Component
public class CommentConverter {
    public String commentToString(Comment comment) {
        return "Id: %d, Comment: %s".formatted(comment.getId(), comment.getText());
    }
}
