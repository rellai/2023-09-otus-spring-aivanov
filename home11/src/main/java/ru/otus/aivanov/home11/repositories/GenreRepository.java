package ru.otus.aivanov.home11.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.otus.aivanov.home11.models.Genre;

public interface GenreRepository extends ReactiveCrudRepository<Genre, Long> {
    
}
