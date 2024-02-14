package ru.otus.aivanov.home15;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.otus.aivanov.home15.service.EmailService;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);

        EmailService emailService = ctx.getBean(EmailService.class);
        emailService.getEmail();
    }
}
