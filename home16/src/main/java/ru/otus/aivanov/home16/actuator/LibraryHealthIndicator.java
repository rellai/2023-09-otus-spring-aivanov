package ru.otus.aivanov.home16.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.aivanov.home16.services.BookService;

@Component
@RequiredArgsConstructor
public class LibraryHealthIndicator implements HealthIndicator {

    private final BookService bookService;

    @Override
    public Health health() {
        long bookCount = bookService.count();
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
    }
}
