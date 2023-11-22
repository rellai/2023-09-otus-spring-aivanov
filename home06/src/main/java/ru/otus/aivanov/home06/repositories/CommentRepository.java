package ru.otus.aivanov.home06.repositories;

import ru.otus.aivanov.home06.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    public List<Comment> findAllByBook(Long bookId);

    public Optional<Comment> findById(long id);

    public Comment save(Comment comment);

    public boolean deleteById(long id);

}
