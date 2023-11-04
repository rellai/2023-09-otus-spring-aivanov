package ru.otus.aivanov.home04.domain;

import java.util.ArrayList;
import java.util.List;

public class TestResult {
    private final Student student;

    private final List<Question> answeredQuestions;

    private int rightAnswersCount;

    public TestResult(Student student) {
        this.student = student;
        this.answeredQuestions = new ArrayList<>();
    }

    public void applyAnswer(Question question, boolean isRightAnswer) {
        answeredQuestions.add(question);
        if (isRightAnswer) {
            rightAnswersCount++;
        }
    }

    public Student getStudent() {
        return this.student;
    }

    public List<Question> getAnsweredQuestions() {
        return this.answeredQuestions;
    }

    public int getRightAnswersCount() {
        return this.rightAnswersCount;
    }

    public void setRightAnswersCount(int rightAnswersCount) {
        this.rightAnswersCount = rightAnswersCount;
    }

   protected boolean canEqual(final Object other) {
        return other instanceof TestResult;
    }

       public String toString() {
        return "TestResult(student=" +
                this.getStudent() +
                ", answeredQuestions=" +
                this.getAnsweredQuestions() +
                ", rightAnswersCount=" +
                this.getRightAnswersCount() +
                ")";
    }
}
