package ru.otus.aivanov.home18.client.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.aivanov.home18.client.dto.BookDto;


import java.util.Collections;
import java.util.List;


@FeignClient(value = "library-service")
public interface BookServiceClient {

    @CircuitBreaker(name = "library-service", fallbackMethod = "fallbackGetAll")
    @GetMapping("/api/books")
    List<BookDto> getAll(Sort sort);

    default List<BookDto> fallbackGetAll(Throwable throwable) {
        return Collections.emptyList();
    }
}

