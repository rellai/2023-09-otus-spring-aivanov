package ru.otus.aivanov.home09.services;

import lombok.RequiredArgsConstructor;
import ru.otus.aivanov.home09.dto.BookCreateDto;
import ru.otus.aivanov.home09.dto.BookDto;
import ru.otus.aivanov.home09.dto.BookUpdateDto;
import ru.otus.aivanov.home09.exceptions.NotFoundException;
import ru.otus.aivanov.home09.mapper.BookMapper;
import ru.otus.aivanov.home09.models.Book;
import ru.otus.aivanov.home09.repositories.AuthorRepository;
import ru.otus.aivanov.home09.repositories.BookRepository;
import ru.otus.aivanov.home09.repositories.CommentRepository;
import ru.otus.aivanov.home09.repositories.GenreRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final CommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public BookUpdateDto findById(long id) {
        return bookMapper.toEditDto(bookRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Book with id %d not found".formatted(id))
        ));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).collect(Collectors.toList());
    }




    @Override
    @Transactional
    public BookUpdateDto create(BookCreateDto bookDto) {
        var author = authorRepository.findById(bookDto.authorId())
                .orElseThrow(() -> new NotFoundException("Author with id %d not found".formatted(bookDto.authorId())));
        var genre = genreRepository.findById(bookDto.genreId())
                .orElseThrow(() -> new NotFoundException("Genre with id %d not found".formatted(bookDto.genreId())));

        var book = new Book(null, bookDto.title(), author, genre);

        return bookMapper.toEditDto(bookRepository.save(book));

    }

    @Override
    @Transactional
    public BookUpdateDto update(BookUpdateDto bookDto) {
        Book book = bookRepository.findById(bookDto.id())
                .orElseThrow(() -> new NotFoundException("Book with id %d not found".formatted(bookDto.id())));

        var author = authorRepository.findById(bookDto.authorId())
                .orElseThrow(() -> new NotFoundException("Author with id %d not found".formatted(bookDto.authorId())));
        var genre = genreRepository.findById(bookDto.genreId())
                .orElseThrow(() -> new NotFoundException("Genre with id %d not found".formatted(bookDto.genreId())));

        book.setTitle(bookDto.title());
        book.setAuthor(author);
        book.setGenre(genre);

        return bookMapper.toEditDto(bookRepository.save(book));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        commentRepository.deleteByBookId(id);
        bookRepository.deleteById(id);
    }

    private BookUpdateDto save(long id, String title, long authorId, long genreId) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author with id %d not found".formatted(authorId)));
        var genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new NotFoundException("Genre with id %d not found".formatted(genreId)));

            var book = new Book(id, title, author, genre);

        return bookMapper.toEditDto(bookRepository.save(book));
    }




}
