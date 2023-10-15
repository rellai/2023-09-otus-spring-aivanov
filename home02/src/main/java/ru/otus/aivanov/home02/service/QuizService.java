package ru.otus.aivanov.home02.service;

import ru.otus.aivanov.home02.domain.Student;
import ru.otus.aivanov.home02.domain.TestResult;

public interface QuizService {

    TestResult executeTestFor(Student student);

}
