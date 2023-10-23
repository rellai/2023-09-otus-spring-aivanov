package ru.otus.aivanov.home02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.aivanov.home02.service.StarterService;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        StarterService starter = context.getBean(StarterService.class);
        starter.startExam();
        context.close();
    }
}
