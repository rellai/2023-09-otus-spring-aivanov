package ru.otus.aivanov.home04.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.aivanov.home04.domain.Student;
import ru.otus.aivanov.home04.domain.TestResult;
import ru.otus.aivanov.home04.service.LocalizedMessage;
import ru.otus.aivanov.home04.service.QuizService;
import ru.otus.aivanov.home04.service.ResultService;
import ru.otus.aivanov.home04.service.StudentService;

@ShellComponent
public class Commands {

    private final QuizService quizService;

    private final StudentService studentService;

    private final ResultService resultService;

    private final LocalizedMessage localizedMessage;

    private TestResult testResult;

    private Student student;

    public Commands(QuizService quizService, StudentService studentService,
                    ResultService resultService, LocalizedMessage localizedMessage) {
        this.quizService = quizService;
        this.studentService = studentService;
        this.resultService = resultService;
        this.localizedMessage = localizedMessage;
    }

    @ShellMethod(value = "start Testing", key = {"start-testing"})
    @ShellMethodAvailability("isAutorized")
    public void startTesting() {

        testResult = quizService.executeTestFor(student);
    }

    private Availability isAutorized() {
        return student != null ?
                Availability.available() :
                Availability.unavailable(localizedMessage.getLocalizedMessage("ShellCommand.PleaseAutorize"));
    }

    private Availability isTested() {
        if (student != null &&
                testResult != null &&
                student.equals(testResult.getStudent())) {
            return Availability.available();
        }

        return Availability.unavailable(localizedMessage.getLocalizedMessage("ShellCommand.TestNotPassed"));
    }

    @ShellMethod(value = "Determine user", key = {"determine-user"})
    public void determineCurrentStudent() {
        student = studentService.determineCurrentStudent();
    }

    @ShellMethod(value = "Show result", key = {"show-result"})
    @ShellMethodAvailability({"isTested"})
    public void showResult() {
        resultService.showResult(testResult);
    }


}
