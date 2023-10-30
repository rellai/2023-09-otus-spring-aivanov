package ru.otus.aivanov.home04.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.aivanov.home04.config.TestFileNameProvider;
import ru.otus.aivanov.home04.dao.CsvQuestionDao;
import ru.otus.aivanov.home04.domain.Student;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("Test formation service")
class QuizServiceImplTest {

    @Test
    void executeTestFor() {

        var fileNameProvider = mock(TestFileNameProvider.class);
        when(fileNameProvider.getTestFileName()).thenReturn("questionsUnitTest.csv");

        var ioService = Mockito.mock(IOService.class);
        var student = mock(Student.class);
        given(ioService.readIntForRange(anyInt(), anyInt(), anyString() ) )
                .willReturn(1);
        doNothing().when(ioService).printLine(anyString());
        doNothing().when(ioService).printFormattedColoredLine(anyString(),anyString());

        var questionDao = Mockito.spy(new CsvQuestionDao(fileNameProvider));
        var quizService = spy(new QuizServiceImpl(questionDao, ioService));

        assertEquals(quizService.executeTestFor(student).getRightAnswersCount() , 2);
    }
}