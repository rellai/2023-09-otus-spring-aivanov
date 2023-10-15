package ru.otus.aivanov.home02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.aivanov.home02.domain.Student;

@Service
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;

    @Autowired
    public StudentServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Student determineCurrentStudent() {
        var firstName = ioService.readStringWithPrompt("Please input your first name");
        var lastName = ioService.readStringWithPrompt("Please input your last name");
        return new Student(firstName, lastName);
    }
}
