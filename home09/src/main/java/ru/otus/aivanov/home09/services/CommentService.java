package ru.otus.aivanov.home09.services;

import java.util.List;

import ru.otus.aivanov.home09.dto.CommentCreateDto;
import ru.otus.aivanov.home09.dto.CommentDto;
import ru.otus.aivanov.home09.dto.CommentUpdateDto;

public interface CommentService {

    public List<CommentDto> findAllByBook(Long bookId);

    public CommentDto findById(long id);

    public void deleteById(long id);

    public CommentDto create(CommentCreateDto comment);

    public CommentDto update(CommentUpdateDto comment);

    public void deleteByBookId(long bookId);

}
