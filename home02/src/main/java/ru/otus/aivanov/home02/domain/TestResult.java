package ru.otus.aivanov.home02.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof TestResult))  {
            return false;
        }
        final TestResult other = (TestResult) o;
        if (!other.canEqual((Object) this)) {
            return false;
        }
        final Object thisStudent = this.getStudent();
        final Object otherStudent = other.getStudent();
        if (!Objects.equals(thisStudent, otherStudent)) {
            return false;
        }
        final Object thisAnsweredQuestions = this.getAnsweredQuestions();
        final Object otherAnsweredQuestions = other.getAnsweredQuestions();
        if (!Objects.equals(thisAnsweredQuestions, otherAnsweredQuestions)) {
            return false;
        }
        return this.getRightAnswersCount() == other.getRightAnswersCount();
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TestResult;
    }

    public int hashCode() {
        final int prime = 59;
        int result = 1;
        final Object student = this.getStudent();
        result = result * prime + (student == null ? 43 : student.hashCode());
        final Object answeredQuestions = this.getAnsweredQuestions();
        result = result * prime + (answeredQuestions == null ? 43 : answeredQuestions.hashCode());
        result = result * prime + this.getRightAnswersCount();
        return result;
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
