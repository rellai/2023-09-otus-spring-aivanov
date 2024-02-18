package ru.otus.aivanov.home16.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.aivanov.home16.models.Genre;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "genres")
public interface GenreRepository extends CrudRepository<Genre, Long> {

    List<Genre> findAll();

    Optional<Genre> findById(long id);
    
}
