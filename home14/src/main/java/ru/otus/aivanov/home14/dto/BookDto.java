package ru.otus.aivanov.home14.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookDto {

    private String id;

    private String title;

    private String authorId;

    private String genresId;

    public BookDto(String id, String title, String authorId, String genresId) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.genresId = genresId;
    }
}
