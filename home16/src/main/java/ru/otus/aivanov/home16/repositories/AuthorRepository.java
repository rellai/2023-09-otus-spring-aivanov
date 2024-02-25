package ru.otus.aivanov.home16.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.aivanov.home16.models.Author;


@RepositoryRestResource(path = "authors")
public interface AuthorRepository extends ListCrudRepository<Author, Long> {


}
