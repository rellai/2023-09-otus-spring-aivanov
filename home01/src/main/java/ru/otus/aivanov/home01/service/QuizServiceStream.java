package ru.otus.aivanov.home01.service;

import ru.otus.aivanov.home01.dao.QuestionDao;
import ru.otus.aivanov.home01.domain.Question;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static ru.otus.aivanov.home01.utils.Const.CONSOLE_TEXT_COLOR_PURPLE;
import static ru.otus.aivanov.home01.utils.Const.CONSOLE_TEXT_COLOR_RESET;
import static ru.otus.aivanov.home01.utils.Const.CONSOLE_TEXT_COLOR_YELLOW;

public class QuizServiceStream implements QuizService {

    private final QuestionDao dao;

    private final IOService ioService;

    public QuizServiceStream(QuestionDao dao,  IOService ioService) {
        this.dao = dao;
        this.ioService = ioService;

    }

    @Override
    public Question findAll() {
        List<Question> questions =  dao.findAll();
        printTest(questions);

        return null;
    }

    private void printTest(List<Question> questions) {
        AtomicInteger questionNumber = new AtomicInteger();
        questions.forEach(question -> {
            questionNumber.set(questionNumber.get() + 1);
            AtomicReference<Integer> answerNumber = new AtomicReference<>(0);
            printQuestion(question, questionNumber);
            printAnswersForQuestion(question, answerNumber);
            ioService.printLine("");
        });
    }

    private void printAnswersForQuestion(Question question, AtomicReference<Integer> answerNumber) {
        question.answers().forEach(answer -> {
            answerNumber.set(answerNumber.get() + 1);
            ioService.printColoredLine("  " + answerNumber + ") " + answer.text(), CONSOLE_TEXT_COLOR_RESET);
        });
    }

    private void printQuestion(Question question, AtomicInteger questionNumber) {
        ioService.printColoredLine("Question " + questionNumber.toString(), CONSOLE_TEXT_COLOR_PURPLE);
        ioService.printColoredLine("  " + question.text(), CONSOLE_TEXT_COLOR_RESET);
        ioService.printColoredLine("The following answer options are possible", CONSOLE_TEXT_COLOR_YELLOW);
    }
}

