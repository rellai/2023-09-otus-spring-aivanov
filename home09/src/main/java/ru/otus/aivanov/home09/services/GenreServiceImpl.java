package ru.otus.aivanov.home09.services;

import lombok.RequiredArgsConstructor;
import ru.otus.aivanov.home09.dto.GenreDto;
import ru.otus.aivanov.home09.exceptions.NotFoundException;
import ru.otus.aivanov.home09.mapper.GenreMapper;
import ru.otus.aivanov.home09.repositories.GenreRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Override
    @Transactional(readOnly = true)
    public List<GenreDto> findAll() {
        return genreRepository.findAll().stream().map(genreMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public GenreDto findById(long id) {
        return genreMapper.toDto(genreRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Book with id %d not found".formatted(id))
        ));
    }

    @Override
    public GenreDto save(GenreDto genre) {
        return genreMapper.toDto(genreRepository.save(genreMapper.toModel(genre)));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        genreRepository.deleteById(id);
    }
}
