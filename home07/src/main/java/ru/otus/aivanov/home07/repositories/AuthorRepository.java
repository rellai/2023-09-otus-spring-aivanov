package ru.otus.aivanov.home07.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.aivanov.home07.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    List<Author> findAll();

}
