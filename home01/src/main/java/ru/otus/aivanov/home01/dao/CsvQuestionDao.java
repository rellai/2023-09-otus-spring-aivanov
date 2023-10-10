package ru.otus.aivanov.home01.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ru.otus.aivanov.home01.Main;
import ru.otus.aivanov.home01.dto.QuestionDto;
import com.opencsv.bean.CsvToBeanBuilder;
import ru.otus.aivanov.home01.domain.Question;
import ru.otus.aivanov.home01.exceptions.QuestionReadException;

public class CsvQuestionDao implements QuestionDao {

    private final String path;

    public CsvQuestionDao(String text) {
        this.path = text;

    }

    private static BufferedReader getFileFromResourceAsStream(String fileName) {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not Found " + fileName);
        } else {
            InputStreamReader streamReader =
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            return new BufferedReader(streamReader);
        }

    }

    public List<Question> findAll() throws QuestionReadException {
          try (BufferedReader reader = getFileFromResourceAsStream(path)) {
              List<QuestionDto> questionDto = fillQuestionDtoFromCsvFile(reader);
              return convertFromQuestionDtoToQuestion(questionDto);
           } catch (IOException e) {
                throw new QuestionReadException("Error reading csv file",e);
            }
    }

    private static List<Question> convertFromQuestionDtoToQuestion(List<QuestionDto> questionDto) {
        List<Question> question = new ArrayList<>();
        questionDto.forEach(quest -> question.add(quest.toDomainObject()));
        return question;
    }

    private static List<QuestionDto> fillQuestionDtoFromCsvFile (BufferedReader reader) throws IOException {
        return  new CsvToBeanBuilder<QuestionDto>(reader)
                .withSkipLines(1)
                .withType(QuestionDto.class)
                .withSeparator(';')
                .build()
                .parse();
    }


}
