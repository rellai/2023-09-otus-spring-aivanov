package ru.otus.aivanov.home07.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.aivanov.home07.models.Comment;

public interface CommentRepository  extends JpaRepository<Comment, Long> {

    public List<Comment> findAllByBookId(Long bookId);
 
    public void deleteByBookId(long id);

}
