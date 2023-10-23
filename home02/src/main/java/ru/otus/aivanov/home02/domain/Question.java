package ru.otus.aivanov.home02.domain;

import com.opencsv.bean.CsvBindByPosition;

import java.util.List;

public record Question(@CsvBindByPosition(position = 0) String text, List<Answer>  answers) {


}
