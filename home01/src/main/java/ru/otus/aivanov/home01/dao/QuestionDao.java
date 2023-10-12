package ru.otus.aivanov.home01.dao;

import ru.otus.aivanov.home01.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
