package ru.otus.aivanov.home11.restController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.aivanov.home11.dto.CommentCreateDto;
import ru.otus.aivanov.home11.dto.CommentDto;
import ru.otus.aivanov.home11.dto.CommentUpdateDto;
import ru.otus.aivanov.home11.exceptions.NotFoundException;
import ru.otus.aivanov.home11.mapper.CommentMapper;
import ru.otus.aivanov.home11.repositories.BookRepository;
import ru.otus.aivanov.home11.repositories.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentRestController {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    private final CommentMapper commentMapper;


    @GetMapping("/api/comments")
    public Flux<CommentDto> getAllComments(@RequestParam("bookId") long bookId) {
        return commentRepository.findAllByBookId(bookId)
                .map(commentMapper::toDto);
    }

    @DeleteMapping("/api/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteComment(@PathVariable("id") long id) {
        return commentRepository.deleteById(id);
    }

    @PostMapping("/api/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CommentDto> createComment(@Valid @RequestBody CommentCreateDto comment) {

        return bookRepository.findById(comment.bookId())
                        .switchIfEmpty(Mono.error(new NotFoundException(
                                "Comment with id %d not found".formatted(comment.bookId()))
                        ))
                        .flatMap(book -> {
                              return commentRepository.save(commentMapper.toModel(comment))
                                    .map(commentMapper::toDto);
                        });
    }

    @PutMapping("/api/comments/{id}")
    public Mono<CommentDto> updateComment(@PathVariable("id") long id,
                                           @Valid @RequestBody CommentUpdateDto comment) {
          return commentRepository.findById(id)
                  .switchIfEmpty(Mono.error(() ->
                          new NotFoundException("Comment with id %d not found".formatted(id))))
                  .flatMap(selectedComment -> commentRepository.save(
                          commentMapper.toModel(comment)
                          )
                       .map(commentMapper::toDto));
    }
}
