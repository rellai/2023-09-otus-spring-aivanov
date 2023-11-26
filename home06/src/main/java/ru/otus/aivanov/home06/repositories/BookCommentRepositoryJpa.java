package ru.otus.aivanov.home06.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.aivanov.home06.dto.BookDto;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookCommentRepositoryJpa implements BookCommentRepository {


    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    @Override
    public void deleteByBookId(long bookId) {
        commentRepository.deleteAllByBookId(bookId);
        bookRepository.deleteById(bookId);
    }

    @Override
    public Optional<BookDto> findById(long id) {
        var book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            var comments = commentRepository.findAllByBook(id);
            return Optional.of(new BookDto(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), comments));
        }
        return Optional.empty();
    }

}
