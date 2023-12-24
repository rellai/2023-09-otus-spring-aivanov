package ru.otus.aivanov.home09.dto;

import ru.otus.aivanov.home09.models.Book;

/**
 * DTO for {@link Book}
 */
public record BookDto(Long id,
                      String title,
                      String author,
                      String genre) {

}