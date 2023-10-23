package ru.otus.aivanov.home02.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.aivanov.home02.config.TestFileNameProvider;
import ru.otus.aivanov.home02.dao.CsvQuestionDao;
import ru.otus.aivanov.home02.domain.Student;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("Test formation service")
class QuizServiceImplTest {

    @Test
    void executeTestFor() {

        var fileNameProvider = mock(TestFileNameProvider.class);
        when(fileNameProvider.getTestFileName()).thenReturn("questionsUnitTest.csv");

        var ioService = mock(IOService.class);
        var student = mock(Student.class);
        given(ioService.readIntForRange(anyInt(), anyInt(), anyString() ) )
                .willReturn(1);
        doNothing().when(ioService).printLine(anyString());
        doNothing().when(ioService).printFormattedColoredLine(anyString(),anyString());

        var questionDao = spy(new CsvQuestionDao(fileNameProvider));
        var quizService = spy(new QuizServiceImpl(questionDao, ioService));

        assertEquals(quizService.executeTestFor(student).getRightAnswersCount() , 2);
    }
}