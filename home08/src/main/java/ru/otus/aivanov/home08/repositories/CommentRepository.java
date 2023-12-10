package ru.otus.aivanov.home08.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.aivanov.home08.models.Comment;

public interface CommentRepository  extends MongoRepository<Comment, String> {

    public List<Comment> findAllByBookId(String bookId);
 
    public void deleteByBookId(String id);

}
