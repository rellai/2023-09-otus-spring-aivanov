package ru.otus.aivanov.home02.service;

import ru.otus.aivanov.home02.domain.Student;
import ru.otus.aivanov.home02.domain.TestResult;

public interface QuizService {
    void findAll();

    TestResult executeTestFor(Student student);

}
