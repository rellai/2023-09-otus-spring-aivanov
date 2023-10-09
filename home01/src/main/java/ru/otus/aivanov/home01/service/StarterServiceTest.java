package ru.otus.aivanov.home01.service;

import ru.otus.aivanov.home01.exceptions.QuestionReadException;

public final class StarterServiceTest implements StarterService {
    private final QuizServiceStream quizServiceStream;

    public StarterServiceTest(QuizServiceStream quizServiceStream) {
        this.quizServiceStream = quizServiceStream;
    }

    @Override
    public  void startExam() throws QuestionReadException {
        quizServiceStream.findAll();

    }


}
