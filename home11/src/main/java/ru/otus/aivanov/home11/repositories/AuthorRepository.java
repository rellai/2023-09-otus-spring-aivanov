package ru.otus.aivanov.home11.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.otus.aivanov.home11.models.Author;

public interface AuthorRepository extends ReactiveCrudRepository<Author, Long> {

}
