package ru.otus.aivanov.home03.service;

import org.springframework.stereotype.Service;
import ru.otus.aivanov.home03.dao.QuestionDao;
import ru.otus.aivanov.home03.domain.Question;
import ru.otus.aivanov.home03.domain.Student;
import ru.otus.aivanov.home03.domain.TestResult;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.otus.aivanov.home03.utils.Const.CONSOLE_TEXT_COLOR_YELLOW;
import static ru.otus.aivanov.home03.utils.Const.CONSOLE_TEXT_COLOR_PURPLE;
import static ru.otus.aivanov.home03.utils.Const.CONSOLE_TEXT_COLOR_RESET;


@Service
public class QuizServiceImpl implements QuizService {

    private final QuestionDao questionDao;

    private final IOService ioService;

    public QuizServiceImpl(QuestionDao questionDao, IOService ioService) {
        this.questionDao = questionDao;
        this.ioService = ioService;

    }

    private void printTest(List<Question> questions) {
        AtomicInteger questionNumber = new AtomicInteger();
        questions.forEach(question -> {
            questionNumber.set(questionNumber.get() + 1);
            AtomicInteger answerNumber = new AtomicInteger();
            printQuestion(question, questionNumber);
            printAnswersForQuestion(question, answerNumber);
            ioService.printLine("");
        });
    }

    private void printAnswersForQuestion(Question question, AtomicInteger answerNumber) {
        question.answers().forEach(answer -> {
            answerNumber.set(answerNumber.get() + 1);
            ioService.printColoredLine("  " + answerNumber + ") " + answer.text(), CONSOLE_TEXT_COLOR_RESET);
        });
    }

    private int printAnswersForQuestionAndReturnRightAnswer(Question question, AtomicInteger answerNumber) {
        AtomicInteger rightAnswer = new AtomicInteger();
        question.answers().forEach(answer -> {
            answerNumber.set(answerNumber.get() + 1);
            ioService.printColoredLine("  " + answerNumber + ") " + answer.text(), CONSOLE_TEXT_COLOR_RESET);
            if (answer.isCorrect()) {
                rightAnswer.set(answerNumber.get());
            }
        });
        return rightAnswer.intValue();
    }


    private void printQuestion(Question question, AtomicInteger questionNumber) {
        ioService.printFormattedColoredLine("TestService.question",
                CONSOLE_TEXT_COLOR_PURPLE,
                questionNumber.toString());
        ioService.printColoredLine("  " + question.text(), CONSOLE_TEXT_COLOR_RESET);
        ioService.printColoredLine("TestService.answer.the.questions.posible", CONSOLE_TEXT_COLOR_YELLOW);
    }

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedColoredLine("TestService.answer.the.questions", CONSOLE_TEXT_COLOR_YELLOW);

        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        AtomicInteger questionNumber = new AtomicInteger();
        for (var question: questions) {
            questionNumber.set(questionNumber.get() + 1);
            printQuestion(question, questionNumber);
            AtomicInteger answerNumber = new AtomicInteger();
            int rightAnswer = printAnswersForQuestionAndReturnRightAnswer(question, answerNumber);
            int answer = ioService.readIntForRange(1, question.answers().size(), "invalid response");
            var isAnswerValid = rightAnswer == answer;
            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }

}

