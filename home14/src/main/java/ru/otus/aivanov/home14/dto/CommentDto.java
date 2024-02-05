package ru.otus.aivanov.home14.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private String id;

    private String text;

    private String bookId;

    public CommentDto(String id, String text, String bookId) {
        this.id = id;
        this.text = text;
        this.bookId = bookId;
    }
}
