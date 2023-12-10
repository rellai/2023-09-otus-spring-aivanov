package ru.otus.aivanov.home08.services;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.aivanov.home08.models.Author;
import ru.otus.aivanov.home08.models.Book;
import ru.otus.aivanov.home08.repositories.AuthorRepository;

import org.springframework.stereotype.Service;
import ru.otus.aivanov.home08.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(String id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
       authorRepository.deleteById(id);
        List<Book> books = bookRepository.findBooksByAuthorId(id);

        for (Book book : books) {
            bookRepository.deleteById(book.getId());
        }
    }
}
