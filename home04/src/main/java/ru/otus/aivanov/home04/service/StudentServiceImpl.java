package ru.otus.aivanov.home04.service;

import org.springframework.stereotype.Service;
import ru.otus.aivanov.home04.domain.Student;

@Service
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;

    private Student student;


    public StudentServiceImpl(IOService ioService, LocalizedMessage localizedMessage) {
        this.ioService = ioService;
    }


    @Override
    public void determineCurrentStudent() {
        var firstName = ioService.readStringWithPrompt("StudentService.input.first.name");
        var lastName = ioService.readStringWithPrompt("StudentService.input.last.name");
        student = new Student(firstName, lastName);
    }


    @Override
    public boolean isDeterminated() {
        return (student != null);
    }

    @Override
    public Student getStudent() {
        return student;
    }
}
