package ru.otus.aivanov.home08.services;

import java.util.List;
import java.util.Optional;

import ru.otus.aivanov.home08.models.Comment;

public interface CommentService {

    public List<Comment> findAllByBook(String bookId);

    public Optional<Comment> findById(String id);

    public void deleteById(String id);

    public Comment create(String bookId, String text);

    public Comment update(String id, String text);

    public void deleteByBookId(String bookId);

}
