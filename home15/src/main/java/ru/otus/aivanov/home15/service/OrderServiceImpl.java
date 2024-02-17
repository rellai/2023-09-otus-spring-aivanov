package ru.otus.aivanov.home15.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.aivanov.home15.model.Email;
import ru.otus.aivanov.home15.model.Order;

import java.util.Arrays;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    public Order createOrder(Email email) {

        Order order = new Order(
                Long.toString(System.currentTimeMillis()),
                email.getFrom(),
                Arrays.asList(email.getMessage().split("\\s*,\\s*"))
        );
        log.info("Created Order: {}", order);

        return order;

    }


}
