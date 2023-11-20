package ru.otus.aivanov.home06.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;

    private String title;

    private Author author;

    private Genre genre;
}
