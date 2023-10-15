package ru.otus.aivanov.home02.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.aivanov.home02.Main;
import ru.otus.aivanov.home02.service.StarterService;

@Configuration
@PropertySource("classpath:applicationUnitTest.properties")
@DisplayName("Test for class QuestionDaoCsv")
class CsvQuestionDaoTest {

    @Test
    @DisplayName("Test for method findAll")
    void testFindAll() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        StarterService starter = context.getBean(StarterService.class);
        starter.startExam();
        context.close();
    }
}