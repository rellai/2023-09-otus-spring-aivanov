package ru.otus.aivanov.home01.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ru.otus.aivanov.home01.dto.QuestionDto;
import com.opencsv.bean.CsvToBeanBuilder;
import ru.otus.aivanov.home01.domain.Question;
import ru.otus.aivanov.home01.exceptions.QuestionReadException;
import static ru.otus.aivanov.home01.service.GetFileFromResourceService.getFileFromResourceAsStream;

public class QuestionDaoCsv implements QuestionDao {

    private final String path;

    public QuestionDaoCsv(String text) {
        this.path = text;

    }

    @Override
    public String toString() {
        return "Вопросы{" +
                "Путь к вопросам='" + path + '\'' +
                '}';
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
