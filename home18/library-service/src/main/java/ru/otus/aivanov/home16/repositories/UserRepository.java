package ru.otus.aivanov.home16.repositories;


import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.aivanov.home16.models.User;

import java.util.Optional;

@RepositoryRestResource(path = "users")
public interface UserRepository extends ListCrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}