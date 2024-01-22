package ru.otus.aivanov.home12.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.aivanov.home12.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
