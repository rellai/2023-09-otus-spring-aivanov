package ru.otus.aivanov.home08.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.aivanov.home08.models.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {


}
