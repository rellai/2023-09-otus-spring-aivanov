package ru.otus.aivanov.home01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.aivanov.home01.exceptions.QuestionReadException;
import ru.otus.aivanov.home01.service.IOService;
import ru.otus.aivanov.home01.service.StarterService;

import static ru.otus.aivanov.home01.utils.Const.CONSOLE_TEXT_COLOR_RED;

public final class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        StarterService starter = context.getBean(StarterService.class);
        IOService ioService = context.getBean(IOService.class);
        try {
            starter.startExam();
        } catch (QuestionReadException e) {
            ioService.printColoredLine("Error reading test data", CONSOLE_TEXT_COLOR_RED);
        }
        context.close();
    }
}
