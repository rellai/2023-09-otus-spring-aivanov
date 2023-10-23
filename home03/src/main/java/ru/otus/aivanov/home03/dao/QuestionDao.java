package ru.otus.aivanov.home03.dao;

import ru.otus.aivanov.home03.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
