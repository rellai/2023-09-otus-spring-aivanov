package ru.otus.aivanov.home02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.aivanov.home02.exceptions.QuestionReadException;

import static ru.otus.aivanov.home02.utils.Const.CONSOLE_TEXT_COLOR_RED;

@Service
public final class StarterServiceTest implements StarterService {

    private final QuizService quizService;

    private final IOService ioService;

    private final StudentService studentService;

    private final ResultService resultService;

    @Autowired
    public StarterServiceTest(QuizService quizService,
                              IOService ioService,
                              StudentService studentService,
                              ResultService resultService) {
        this.quizService = quizService;
        this.ioService = ioService;
        this.studentService = studentService;
        this.resultService = resultService;
    }

    @Override
    public  void startExam() {
        try {

            var student = studentService.determineCurrentStudent();
            var testResult = quizService.executeTestFor(student);
            resultService.showResult(testResult);

        } catch (QuestionReadException e) {
            ioService.printColoredLine("Error reading test data",
                    CONSOLE_TEXT_COLOR_RED);
        }
    }


}
