package ru.otus.aivanov.home15.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.aivanov.home15.model.Email;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final AtomicInteger counter = new AtomicInteger(0);

    private final List<List<String>> rawEmails = List.of(
            List.of("1@1.ru", "Order", "taco,burrito"),
            List.of("2@1.ru", "Question", "Do you work today"),
            List.of("3@1.ru", "Order", "pizza"),
            List.of("4@1.ru", "Order", "pizza,cola"),
            List.of("5@1.ru", "Reminder", "Please pay your electricity bills"),
            List.of("6@1.ru", "Order", "pasta, tea"),
            List.of("7@1.ru", "Info", "Tomorrow there will be a water outage"),
            List.of("8@1.ru", "Order", "burrito, coffee"),
            List.of("9@1.ru", "Order", "pelmeni")

    );

    @Override
    public Email transformation(List<String> rawEmail) {
        return new Email(rawEmail.get(0), rawEmail.get(1), rawEmail.get(2));
    }

    public List<String> getRawEmailMessage() {
        Random rand = new Random();
        return rawEmails.get(rand.nextInt(rawEmails.size()));
    }

}
