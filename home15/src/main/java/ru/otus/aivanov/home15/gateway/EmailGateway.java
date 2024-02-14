package ru.otus.aivanov.home15.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.aivanov.home15.model.Email;

import java.util.List;

@MessagingGateway
public interface EmailGateway {

    @Gateway(replyChannel = "queueChannel")
    List<Email> process(List<String> rawEmails);
}
