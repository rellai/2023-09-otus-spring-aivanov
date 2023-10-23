package ru.otus.aivanov.home03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.aivanov.home03.service.StarterService;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);
        StarterService starter = ctx.getBean(StarterService.class);
        starter.startExam();
    }
}
