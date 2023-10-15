package ru.otus.aivanov.home02.dao;

import ru.otus.aivanov.home02.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
