package ru.otus.aivanov.home06.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.aivanov.home06.exceptions.EntityNotFoundException;
import ru.otus.aivanov.home06.models.Book;
import ru.otus.aivanov.home06.models.Comment;
import ru.otus.aivanov.home06.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookService bookService;

    private Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAllByBook(Long bookId) {
        return commentRepository.findAllByBook(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }


    @Override
    @Transactional
    public void deleteById(long id) throws EntityNotFoundException {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Comment create(long bookId, String text) throws EntityNotFoundException {
        Comment comment = new Comment();
        Book book = bookService.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException("Book with id %d not found".formatted(bookId))
        );
        comment.setTxt(text);
        comment.setBook(book);
        return save(comment);
    }


    @Override
    @Transactional
    public Comment update(long id, String text) throws EntityNotFoundException {
        Comment comment = findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book with id %d not found".formatted(id))
        );
        comment.setTxt(text);
        return save(comment);
    }


}
