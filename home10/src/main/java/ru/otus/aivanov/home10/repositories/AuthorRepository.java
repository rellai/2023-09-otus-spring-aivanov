package ru.otus.aivanov.home10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.aivanov.home10.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
