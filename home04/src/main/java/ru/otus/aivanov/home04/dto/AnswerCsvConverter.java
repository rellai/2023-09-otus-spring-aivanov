package ru.otus.aivanov.home04.dto;

import com.opencsv.bean.AbstractCsvConverter;
import ru.otus.aivanov.home04.domain.Answer;

public class AnswerCsvConverter extends AbstractCsvConverter {

    @Override
    public Object convertToRead(String value) {
        var valueArr = value.split("%");
        return new Answer(valueArr[0], Boolean.parseBoolean(valueArr[1]));
    }
}
