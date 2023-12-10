package ru.otus.aivanov.home08.services;

import lombok.RequiredArgsConstructor;
import ru.otus.aivanov.home08.exceptions.DependenciesFoundException;
import ru.otus.aivanov.home08.models.Genre;
import ru.otus.aivanov.home08.repositories.BookRepository;
import ru.otus.aivanov.home08.repositories.GenreRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(String id) {
        return genreRepository.findById(id);
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void deleteById(String id) {
        if (bookRepository.countBooksByGenreId(id) == 0) {
            genreRepository.deleteById(id);
        } else {
            throw new DependenciesFoundException("Found Book with genreId %s".formatted(id));
        }
    }
}
