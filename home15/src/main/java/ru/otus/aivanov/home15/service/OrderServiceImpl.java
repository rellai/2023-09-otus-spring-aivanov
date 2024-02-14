package ru.otus.aivanov.home15.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.aivanov.home15.model.Email;
import ru.otus.aivanov.home15.model.Order;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    public Order createOrder(Email email) {
        log.info("CreateOrder: {}", email);
        return new Order("1", email.getFrom(), List.of("burrito","taco"));

    }


}
