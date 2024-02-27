package ru.otus.aivanov.home17.services;

import lombok.RequiredArgsConstructor;
import ru.otus.aivanov.home17.dto.GenreCreateDto;
import ru.otus.aivanov.home17.dto.GenreDto;
import ru.otus.aivanov.home17.exceptions.NotFoundException;
import ru.otus.aivanov.home17.mapper.GenreMapper;
import ru.otus.aivanov.home17.repositories.GenreRepository;

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
        return genreMapper.toDto(genreRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Genre with id %d not found".formatted(id))
        ));
    }

    @Override
    @Transactional
    public GenreDto create(GenreCreateDto genre) {
        return genreMapper.toDto(genreRepository.save(genreMapper.toModel(genre)));
    }

    @Override
    @Transactional
    public GenreDto update(GenreDto genre) {
        genreMapper.toDto(genreRepository.findById(genre.id()).
                orElseThrow(() -> new NotFoundException("Genre with id %d not found".formatted(genre.id()))
                ));
        return genreMapper.toDto(genreRepository.save(genreMapper.toModel(genre)));
    }


    @Override
    @Transactional
    public void deleteById(long id) {
        genreRepository.deleteById(id);
    }
}
