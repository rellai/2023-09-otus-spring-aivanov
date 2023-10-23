package ru.otus.aivanov.home02.service;

import org.springframework.stereotype.Service;
import ru.otus.aivanov.home02.config.TestConfig;
import ru.otus.aivanov.home02.domain.TestResult;

import static ru.otus.aivanov.home02.utils.Const.CONSOLE_TEXT_COLOR_RESET;

@Service
public class ResultServiceImpl implements ResultService {

    private final TestConfig testConfig;

    private final IOService ioService;

    public ResultServiceImpl(TestConfig testConfig, IOService ioService) {
        this.testConfig = testConfig;
        this.ioService = ioService;
    }

    @Override
    public void showResult(TestResult testResult) {
        ioService.printLine("");
        ioService.printLine("Test results: ");
        ioService.printFormattedColoredLine("Student: %s",
                CONSOLE_TEXT_COLOR_RESET,
                testResult.getStudent().getFullName());
        ioService.printFormattedColoredLine("Answered questions count: %d",
                CONSOLE_TEXT_COLOR_RESET,
                testResult.getAnsweredQuestions().size());
        ioService.printFormattedColoredLine("Right answers count: %d",
                CONSOLE_TEXT_COLOR_RESET,
                testResult.getRightAnswersCount());

        if (testResult.getRightAnswersCount() >= testConfig.getRightAnswersCountToPass()) {
            ioService.printLine("Congratulations! You passed test!");
            return;
        }
        ioService.printLine("Sorry. You fail test.");
    }
}
