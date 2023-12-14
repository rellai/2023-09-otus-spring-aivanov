package ru.otus.aivanov.home08.models;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Getter
@Setter
@Document
public class Book {
    @Id
    private String id;

    private String title;

    private Author author;

    private Genre genre;

    public Book(String id, String title, Author author, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

}
