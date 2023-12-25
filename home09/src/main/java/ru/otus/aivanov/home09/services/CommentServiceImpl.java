package ru.otus.aivanov.home09.services;

import lombok.RequiredArgsConstructor;
import ru.otus.aivanov.home09.dto.CommentCreateDto;
import ru.otus.aivanov.home09.dto.CommentDto;
import ru.otus.aivanov.home09.dto.CommentUpdateDto;
import ru.otus.aivanov.home09.exceptions.NotFoundException;
import ru.otus.aivanov.home09.mapper.CommentMapper;
import ru.otus.aivanov.home09.models.Book;
import ru.otus.aivanov.home09.models.Comment;
import ru.otus.aivanov.home09.repositories.BookRepository;
import ru.otus.aivanov.home09.repositories.CommentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    private final CommentMapper commentMapper;


    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> findAllByBook(Long bookId) {
        return commentRepository.findAllByBookId(bookId).stream().map(commentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDto findById(long id) {
        return commentMapper.toDto(commentRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Book with id %d not found".formatted(id))
        ));
    }


    @Override
    @Transactional
    public void deleteById(long id) throws NotFoundException {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByBookId(long bookId) throws NotFoundException {
        commentRepository.deleteByBookId(bookId);
    }

    @Override
    @Transactional
    public CommentDto create(CommentCreateDto commentDto) throws NotFoundException {
        Book book = bookRepository.findById(commentDto.bookId())
                .orElseThrow(() -> new NotFoundException("Book with id %d not found".formatted(commentDto.bookId()))
        );
        Comment comment = new Comment(null, commentDto.text(), book);
        return commentMapper.toDto(commentRepository.save(comment));

    }


    @Override
    @Transactional
    public CommentDto update(CommentUpdateDto commentDto) throws NotFoundException {
        Comment comment = commentRepository.findById(commentDto.id()).orElseThrow(
                    () -> new NotFoundException("Book with id %d not found".formatted(commentDto.id()))
            );
            comment.setText(commentDto.text());
        return commentMapper.toDto(commentRepository.save(comment));
    }


}
