package ru.otus.aivanov.home16.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.aivanov.home16.repositories.BookRepository;


@Component
@RequiredArgsConstructor
public class LibraryHealthIndicator implements HealthIndicator {

    private final BookRepository bookRepository;

    @Override
    public Health health() {

        try {
            long bookCount = bookRepository.count();
            if (bookCount == 0) {
                String message = "В библиотеке отсутствуют книги";
                return Health.down()
                        .status(Status.DOWN)
                        .withDetail("message", message)
                        .build();
            } else {
                String message = "Все в порядке. Есть что почитать.";
                return Health.up()
                        .withDetail("message", message)
                        .build();
            }
        } catch (Exception e) {
            return Health.down()
                    .withException(e)
                    .build();
        }

    }
}
