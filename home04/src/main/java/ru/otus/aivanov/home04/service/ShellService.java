package ru.otus.aivanov.home04.service;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.aivanov.home04.domain.Student;

@ShellComponent
public class ShellService {

    private final StarterService starterService;

    private final StudentService studentService;

    private final ResultService resultService;

    private final LocalizedMessage localizedMessage;

    private Student student;

    public ShellService(StarterService starterService, StudentService studentService,
                        ResultService resultService, LocalizedMessage localizedMessage) {
        this.starterService = starterService;
        this.studentService = studentService;
        this.resultService = resultService;
        this.localizedMessage = localizedMessage;
    }

    @ShellMethod(value = "start Testing", key = {"start-testing"})
    @ShellMethodAvailability("isAutorized")
    public void startTesting() {
        starterService.startExam();
    }

    private Availability isAutorized() {
        return studentService.isDeterminated()?Availability.available():Availability.unavailable(localizedMessage.getLocalizedMessage("ShellCommand.PleaseAutorize"));
    }

    @ShellMethod(value = "Autori", key = {"start-testing"})
    @ShellMethodAvailability("isAutorized")
    public void startTesting() {
        starterService.startExam();
    }

}
