package ru.otus.aivanov.home04.service;

import org.springframework.stereotype.Service;
import ru.otus.aivanov.home04.domain.Student;

@Service
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;


    public StudentServiceImpl(IOService ioService, LocalizedMessage localizedMessage) {
        this.ioService = ioService;
    }


    @Override
    public Student determineCurrentStudent() {
        var firstName = ioService.readStringWithPrompt("StudentService.input.first.name");
        var lastName = ioService.readStringWithPrompt("StudentService.input.last.name");
        return new Student(firstName, lastName);
    }

}
