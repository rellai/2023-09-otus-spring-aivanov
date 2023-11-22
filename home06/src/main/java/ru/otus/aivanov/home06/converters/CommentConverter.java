package ru.otus.aivanov.home06.converters;

import org.springframework.stereotype.Component;
import ru.otus.aivanov.home06.models.Comment;

@Component
public class CommentConverter {
    public String commentToString(Comment comment) {
        return "Id: %d, Comment: %s".formatted(comment.getId(), comment.getTxt());
    }
}
