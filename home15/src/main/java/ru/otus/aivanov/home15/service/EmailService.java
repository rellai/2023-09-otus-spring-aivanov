package ru.otus.aivanov.home15.service;


import ru.otus.aivanov.home15.model.Email;

import java.util.List;

public interface EmailService {

    List<String> getRawEmailMessage();

    Email transformation(List<String> rawEmail);
}
