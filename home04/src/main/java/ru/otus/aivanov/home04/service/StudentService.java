package ru.otus.aivanov.home04.service;

import ru.otus.aivanov.home04.domain.Student;

public interface StudentService {

    void determineCurrentStudent();

    boolean isDeterminated();

    Student getStudent();
}
