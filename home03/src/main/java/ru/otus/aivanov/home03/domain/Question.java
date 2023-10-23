package ru.otus.aivanov.home03.domain;

import com.opencsv.bean.CsvBindByPosition;

import java.util.List;

public record Question(@CsvBindByPosition(position = 0) String text, List<Answer>  answers) {


}
