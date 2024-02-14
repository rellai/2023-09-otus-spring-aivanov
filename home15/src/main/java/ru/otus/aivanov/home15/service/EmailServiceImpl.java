package ru.otus.aivanov.home15.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Service;
import ru.otus.aivanov.home15.model.Email;
import ru.otus.aivanov.home15.utils.Util;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {


    public Email process(List<String> rawEmails) {

        return new Email(rawEmails.get(0), rawEmails.get(1), rawEmails.get(2));


    }


    public void getEmail() {
        log.info("Запуск сервиса email");

        List<List<String>> rawEmails = new ArrayList<List<String>>();

        rawEmails.add(List.of("1@1.ru", "Order", "taco,burrito"));
        rawEmails.add(List.of("2@1.ru", "Question", "Do you work today"));
        rawEmails.add(List.of("3@1.ru", "Order", "pizza"));
        rawEmails.add(List.of("4@1.ru", "Order", "pizza,cola"));
        rawEmails.add(List.of("5@1.ru", "Reminder", "Please pay your electricity bills"));
        rawEmails.add(List.of("6@1.ru", "Order", "pasta, tea"));
        rawEmails.add(List.of("7@1.ru", "Info", "Tomorrow there will be a water outage"));
        rawEmails.add(List.of("8@1.ru", "Order", "burrito, coffee"));
        rawEmails.add(List.of("9@1.ru", "Order", "pelmeni"));

        for (List<String> rawEmail : rawEmails) {
            log.info("Сырые значения: {}", rawEmail);
            Email email = process(rawEmail);
            log.info("Сформированные значения: {}", email);

            Util.delay(1000);
        }

    }

}
