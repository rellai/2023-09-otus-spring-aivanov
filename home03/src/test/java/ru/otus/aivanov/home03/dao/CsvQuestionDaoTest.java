package ru.otus.aivanov.home03.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.aivanov.home03.config.TestFileNameProvider;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@DisplayName("Test for class QuestionDaoCsv")
class CsvQuestionDaoTest {

    @Test
    void findAll() {
        var fileNameProvider = mock(TestFileNameProvider.class);
        when(fileNameProvider.getTestFileName()).thenReturn("questionsUnitTest.csv");
        var csvQuestionDao = new CsvQuestionDao(fileNameProvider);
        assertEquals(3, csvQuestionDao.findAll().size(), "expected 3 returned returned another number");
    }
}