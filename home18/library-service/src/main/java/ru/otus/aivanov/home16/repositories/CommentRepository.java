package ru.otus.aivanov.home16.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.aivanov.home16.models.Comment;
import ru.otus.aivanov.home16.projection.CommentProjection;

@RepositoryRestResource(path = "comments", excerptProjection = CommentProjection.class)
public interface CommentRepository  extends CrudRepository<Comment, Long> {

    public List<Comment> findAllByBookId(Long bookId);

    public void deleteByBookId(Long bookId);

}
