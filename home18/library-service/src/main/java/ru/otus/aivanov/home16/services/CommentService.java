package ru.otus.aivanov.home16.services;

import java.util.List;

import ru.otus.aivanov.home16.dto.CommentCreateDto;
import ru.otus.aivanov.home16.dto.CommentDto;
import ru.otus.aivanov.home16.dto.CommentUpdateDto;

public interface CommentService {

    public List<CommentDto> findAllByBook(Long bookId);

    public CommentDto findById(long id);

    public void deleteById(long id);

    public CommentDto create(CommentCreateDto comment);

    public CommentDto update(CommentUpdateDto comment);

    public void deleteByBookId(long bookId);

}
