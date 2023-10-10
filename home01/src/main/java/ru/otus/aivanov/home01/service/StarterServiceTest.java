package ru.otus.aivanov.home01.service;

import ru.otus.aivanov.home01.exceptions.QuestionReadException;
import static ru.otus.aivanov.home01.utils.Const.CONSOLE_TEXT_COLOR_RED;

public final class StarterServiceTest implements StarterService {

    private final QuizServiceImpl quizServiceImpl;

    private final IOService ioService;

    public StarterServiceTest(QuizServiceImpl quizServiceImpl, IOService ioService) {
        this.quizServiceImpl = quizServiceImpl;
        this.ioService = ioService;
    }

    @Override
    public  void startExam() {

        try {
            quizServiceImpl.findAll();
        } catch (QuestionReadException e) {
            ioService.printColoredLine("Error reading test data", CONSOLE_TEXT_COLOR_RED);
        }
    }


}
