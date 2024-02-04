package ru.otus.aivanov.home13.dto;

import ru.otus.aivanov.home13.models.Book;

/**
 * DTO for {@link Book}
 */
public record BookFullDto(Long id,
                          String title,
                          String author,
                          String genre) {

}