package ru.otus.aivanov.home15.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.aivanov.home15.model.Email;
import ru.otus.aivanov.home15.model.Order;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    public Order createOrder(Email email) {
        log.info("CreateOrder - email: {}", email);

        Order order = new Order(
                Long.toString(System.currentTimeMillis()),
                email.getFrom(),
                Arrays.asList(email.getMessage().split("\\s*,\\s*"))
        );

        log.info("CreateOrder - order: {}", order);

        return order;

    }


}
