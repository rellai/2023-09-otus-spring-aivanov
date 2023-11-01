package ru.otus.aivanov.home04.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.aivanov.home04.config.AppProps;
import static org.junit.jupiter.api.Assertions.*;



@DisplayName("Test for class QuestionDaoCsv")
@EnableConfigurationProperties(value = AppProps.class)
@SpringBootTest
class CsvQuestionDaoTest {

    @Autowired
    CsvQuestionDao csvQuestionDao;

    @Test
    void findAll() {
        assertEquals(3, csvQuestionDao.findAll().size(), "expected 3 returned returned another number");
    }
}