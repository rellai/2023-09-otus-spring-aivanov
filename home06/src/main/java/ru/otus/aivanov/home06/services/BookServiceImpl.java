package ru.otus.aivanov.home06.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.aivanov.home06.exceptions.EntityNotFoundException;
import ru.otus.aivanov.home06.models.Book;
import ru.otus.aivanov.home06.repositories.AuthorRepository;
import ru.otus.aivanov.home06.repositories.BookRepository;
import ru.otus.aivanov.home06.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book insert(String title, long authorId, long genreId) {
        return save(0, title, authorId, genreId);
    }

    @Override
    @Transactional
    public Book update(long id, String title, long authorId, long genreId) {
        bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id %d not found".formatted(id)));
        return save(id, title, authorId, genreId);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        if (!bookRepository.deleteById(id)) {
            throw new EntityNotFoundException("Book with id %d not found".formatted(id));
        }
    }


    private Book save(long id, String title, long authorId, long genreId) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(authorId)));
        var genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Genre with id %d not found".formatted(genreId)));
        var book = new Book(id, title, author, genre, null);
        return bookRepository.save(book);
    }
}
