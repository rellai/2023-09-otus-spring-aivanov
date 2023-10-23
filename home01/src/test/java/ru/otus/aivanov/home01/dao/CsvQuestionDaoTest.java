package ru.otus.aivanov.home01.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Test for class QuestionDaoCsv")
class CsvQuestionDaoTest {

    @Test
    @DisplayName("Test for method findAll")
    void testFindAll() {
            CsvQuestionDao dao = new CsvQuestionDao("questions4test.csv");
            dao.findAll();
            assertTrue(true);
    }
}