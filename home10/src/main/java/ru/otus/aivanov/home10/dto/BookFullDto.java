package ru.otus.aivanov.home10.dto;

import ru.otus.aivanov.home10.models.Book;

/**
 * DTO for {@link Book}
 */
public record BookFullDto(Long id,
                          String title,
                          String author,
                          String genre) {

}