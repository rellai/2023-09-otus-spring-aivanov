package ru.otus.aivanov.home16.services;

import lombok.RequiredArgsConstructor;
import ru.otus.aivanov.home16.models.User;
import ru.otus.aivanov.home16.repositories.UserRepository;
import ru.otus.aivanov.home16.security.UserPrincipal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatabaseUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));;
        return new UserPrincipal(user);
    }
}
