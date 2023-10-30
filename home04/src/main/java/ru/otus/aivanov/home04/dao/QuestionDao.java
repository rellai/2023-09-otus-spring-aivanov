package ru.otus.aivanov.home04.dao;

import ru.otus.aivanov.home04.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
