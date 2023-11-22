package ru.otus.aivanov.home06.services;

import ru.otus.aivanov.home06.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    public List<Comment> findAllByBook(Long bookId);

    public Optional<Comment> findById(long id);

    public Comment save(Comment comment);

    public void deleteById(long id);

    public Comment create(long bookId, String text);

    public Comment update(long id, String text);

}
