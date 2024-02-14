package ru.otus.aivanov.home15.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;

import org.springframework.integration.dsl.*;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.PollableChannel;
import ru.otus.aivanov.home15.model.Order;
import ru.otus.aivanov.home15.service.OrderService;


@Configuration
@Slf4j
public class IntegrationConfig {

    @Bean
    public PollableChannel queueChannel() {
        return new QueueChannel(10);
    }

    @Bean
    public MessageChannelSpec<?, ?> orderChannel() {
        return MessageChannels.publishSubscribe();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers
                .fixedRate(1000)
                .getObject();
        }

    @Bean
    public IntegrationFlow ordersFlow(OrderService orderService) {
        return
                IntegrationFlow.from(queueChannel())
                .handle(orderService, "createOrder")
                .channel("orderChannel")
                .handle(msg -> {
                     Order order = (Order) msg.getPayload();
                     log.info("Order: {}", order);
                })
                .get();

    }



}
