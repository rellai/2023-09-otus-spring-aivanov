package ru.otus.aivanov.home05.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.aivanov.home05.models.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        return new Author(id, name);
    }

}
