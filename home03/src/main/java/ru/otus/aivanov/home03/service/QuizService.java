package ru.otus.aivanov.home03.service;

import ru.otus.aivanov.home03.domain.Student;
import ru.otus.aivanov.home03.domain.TestResult;

public interface QuizService {

    TestResult executeTestFor(Student student);

}
