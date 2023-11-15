package ru.otus.aivanov.home05.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.aivanov.home05.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryJdbc implements GenreRepository {

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }

    }

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public List<Genre> findAll() {
        return jdbc.getJdbcOperations()
                .query("select g.id, g.name from genres g", new GenreMapper());
    }

    @Override
    public Optional<Genre> findById(long id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject(
                    "select id, name from genres where id=:id",
                    Map.of("id", id), new GenreMapper()));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == null) {
            return insert(genre);
        }
        return update(genre);
    }

    @Override
    public long deleteById(long id) {
        return jdbc.update(
                "delete from genres where id=:id", Map.of("id", id));
    }

    private Genre insert(Genre genre) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", genre.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(
                "insert into genres (name) values (:name)", parameterSource, keyHolder, new String[]{"id"});
        genre.setId(keyHolder.getKey().longValue());
        return genre;
    }

    private Genre update(Genre genre) {
        jdbc.update(
                "update genres g set g.name=:name where g.id=:id",
                Map.of("id", genre.getId(), "name", genre.getName()));
        return genre;
    }

}
