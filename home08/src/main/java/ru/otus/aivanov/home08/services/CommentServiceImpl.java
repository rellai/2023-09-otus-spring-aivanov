package ru.otus.aivanov.home08.services;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.aivanov.home08.exceptions.EntityNotFoundException;
import ru.otus.aivanov.home08.models.Book;
import ru.otus.aivanov.home08.models.Comment;
import ru.otus.aivanov.home08.repositories.BookRepository;
import ru.otus.aivanov.home08.repositories.CommentRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    private Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAllByBook(String bookId) {
        return commentRepository.findAllByBookId(bookId);
    }

    @Override
    public Optional<Comment> findById(String id) {
        return commentRepository.findById(id);
    }


    @Override
    @Transactional
    public void deleteById(String id) throws EntityNotFoundException {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByBookId(String bookId) throws EntityNotFoundException {
        commentRepository.deleteByBookId(bookId);
    }

    @Override
    @Transactional
    public Comment create(String bookId, String text) throws EntityNotFoundException {
        Comment comment = new Comment();
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException("Book with id %s not found".formatted(bookId))
        );
        comment.setText(text);
        comment.setBook(book);
        return save(comment);
    }


    @Override
    @Transactional
    public Comment update(String id, String text) throws EntityNotFoundException {
        Comment comment = findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book with id %s not found".formatted(id))
        );
        comment.setText(text);
        return save(comment);
    }


}
