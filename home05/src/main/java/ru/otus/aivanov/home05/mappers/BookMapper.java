package ru.otus.aivanov.home05.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.aivanov.home05.models.Author;
import ru.otus.aivanov.home05.models.Book;
import ru.otus.aivanov.home05.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BookMapper  implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(rs.getLong("b_id"), rs.getString("b_title"),
                new Author(rs.getLong("a_id"), rs.getString("name")),
                new Genre(rs.getLong("g_id"), rs.getString("g_name"))
        );
    }
}
