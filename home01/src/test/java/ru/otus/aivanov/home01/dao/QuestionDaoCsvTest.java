package ru.otus.aivanov.home01.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.aivanov.home01.exceptions.QuestionReadException;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Test for class QuestionDaoCsv")
class QuestionDaoCsvTest {

    @Test
    @DisplayName("Test for method findAll")
    void testFindAll() {
        try {
            QuestionDaoCsv dao = new QuestionDaoCsv("questions.csv");
            dao.findAll();
            assertTrue(true);
        } catch (QuestionReadException e) {
            fail("method threw an exception");
        }

    }
}