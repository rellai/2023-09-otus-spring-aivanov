package ru.otus.aivanov.home15.service;

import ru.otus.aivanov.home15.model.Email;
import ru.otus.aivanov.home15.model.Order;


public interface OrderService {

    Order createOrder(Email email);

}
