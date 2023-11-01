package ru.otus.aivanov.home04.service;

import ru.otus.aivanov.home04.domain.Student;
import ru.otus.aivanov.home04.domain.TestResult;

public interface QuizService {

    void executeTestFor(Student student);

    TestResult getTestResult();

}
