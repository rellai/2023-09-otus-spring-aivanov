package ru.otus.aivanov.home02.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.aivanov.home02.Main;
import ru.otus.aivanov.home02.config.TestFileNameProvider;
import ru.otus.aivanov.home02.domain.Question;
import ru.otus.aivanov.home02.dto.QuestionDto;
import ru.otus.aivanov.home02.exceptions.QuestionReadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CsvQuestionDao implements QuestionDao {

    private final String path;

    @Autowired
    public CsvQuestionDao(TestFileNameProvider fileNameProvider) {
        this.path = fileNameProvider.getTestFileName();
    }

    private BufferedReader getFileFromResourceAsStream(String fileName) {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not Found " + fileName);
        } else {
            InputStreamReader streamReader =
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            return new BufferedReader(streamReader);
        }

    }

    @Override
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
