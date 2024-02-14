package ru.otus.aivanov.home15.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Email {

    private String from;

    private String title;

    private String message;

}
