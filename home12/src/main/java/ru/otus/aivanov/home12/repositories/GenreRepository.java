package ru.otus.aivanov.home12.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.aivanov.home12.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    
}
