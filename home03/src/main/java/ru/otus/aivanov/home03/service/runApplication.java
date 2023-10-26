package ru.otus.aivanov.home03.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class runApplication implements CommandLineRunner {

    private final StarterService starter;

    public runApplication(StarterService starter) {
        this.starter = starter;
    }

    @Override
    public void run(String... args) throws Exception {
               starter.startExam();
    }
}
