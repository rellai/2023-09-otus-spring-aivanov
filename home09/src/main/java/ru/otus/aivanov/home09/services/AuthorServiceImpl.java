package ru.otus.aivanov.home09.services;

import lombok.RequiredArgsConstructor;
import ru.otus.aivanov.home09.dto.AuthorDto;
import ru.otus.aivanov.home09.exceptions.NotFoundException;
import ru.otus.aivanov.home09.mapper.AuthorMapper;
import ru.otus.aivanov.home09.repositories.AuthorRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper authorMapper;

    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> findAll() {
        return authorRepository.findAll().stream().map(authorMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDto findById(long id) {
        return authorMapper.toDto(authorRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Book with id %d not found".formatted(id))
        ));
    }

    @Override
    public AuthorDto save(AuthorDto author) {
        return authorMapper.toDto(authorRepository.save(authorMapper.toModel(author)));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
       authorRepository.deleteById(id);
    }
}
