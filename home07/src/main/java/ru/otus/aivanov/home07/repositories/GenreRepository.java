package ru.otus.aivanov.home07.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.aivanov.home07.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    
    List<Genre> findAll();

}
