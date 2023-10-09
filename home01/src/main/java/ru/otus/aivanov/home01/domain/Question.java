package ru.otus.aivanov.home01.domain;

import java.util.List;
import com.opencsv.bean.CsvBindByPosition;

public record Question(@CsvBindByPosition(position = 0) String text, List<Answer>  answers) {


}
