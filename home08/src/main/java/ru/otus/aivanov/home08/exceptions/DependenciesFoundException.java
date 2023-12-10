package ru.otus.aivanov.home08.exceptions;

public class DependenciesFoundException extends RuntimeException {
    public DependenciesFoundException(String message) {
        super(message);
    }
}