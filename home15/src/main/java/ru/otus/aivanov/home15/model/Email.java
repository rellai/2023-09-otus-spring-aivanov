package ru.otus.aivanov.home15.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Email {

    private String from;

    private String title;

    private String message;

}
