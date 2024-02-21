package ru.otus.aivanov.home16.projection;


import org.springframework.data.rest.core.config.Projection;
import ru.otus.aivanov.home16.models.Comment;

@Projection(name = "comment-view", types = Comment.class)
public interface CommentProjection {

    Long getId();

    String getText();




}
