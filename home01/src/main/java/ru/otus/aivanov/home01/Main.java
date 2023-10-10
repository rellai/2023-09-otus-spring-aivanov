package ru.otus.aivanov.home01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.aivanov.home01.service.StarterService;

public final class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        StarterService starter = context.getBean(StarterService.class);
        starter.startExam();
        context.close();
    }
}
