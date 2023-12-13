package ru.otus.aivanov.home08.services;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.aivanov.home08.models.Genre;
import ru.otus.aivanov.home08.repositories.GenreRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(String id) {
        return genreRepository.findById(id);
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
            genreRepository.deleteById(id);
    }
}
