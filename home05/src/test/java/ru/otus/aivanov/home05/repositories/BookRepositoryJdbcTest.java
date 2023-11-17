package ru.otus.aivanov.home05.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.otus.aivanov.home05.exceptions.EntityNotFoundException;
import ru.otus.aivanov.home05.models.Author;
import ru.otus.aivanov.home05.models.Book;
import ru.otus.aivanov.home05.models.Genre;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jdbc для работы с книгами ")
@JdbcTest
@Import({BookRepositoryJdbc.class})
class BookRepositoryJdbcTest {



    @Autowired
    private BookRepositoryJdbc bookRepositoryJdbc;

    @Autowired
    JdbcTemplate jdbcTemplate;
    private List<Author> dbAuthors;

    private List<Genre> dbGenres;

    private List<Book> dbBooks;

    @BeforeEach
    void setUp() {
        dbAuthors = getDbAuthors();
        dbGenres = getDbGenres();
        dbBooks =getDbBooks(dbAuthors,dbGenres);
    }

    @DisplayName("должен загружать книгу по id")
    void shouldReturnCorrectBookById() {
        for (int i = 0; i < dbBooks.size(); i++) {
            var expectedBook = dbBooks.get(i);
            var actualBook = bookRepositoryJdbc.findById(expectedBook.getId());
            assertThat(actualBook).isPresent()
                    .get()
                    .isEqualTo(expectedBook);
        }
    }

    @DisplayName("должен загружать список всех книг")
    @Test
    void shouldReturnCorrectBooksList() {
        var actualBooks = bookRepositoryJdbc.findAll();
        var expectedBooks = dbBooks;

        assertThat(actualBooks).containsExactlyElementsOf(expectedBooks);
        actualBooks.forEach(System.out::println);
    }

    @DisplayName("должен сохранять новую книгу")
    @Test
    void shouldSaveNewBook() {
        var expectedBook = new Book(null, "BookTitle_10500", dbAuthors.get(0), dbGenres.get(0));
        var returnedBook = bookRepositoryJdbc.save(expectedBook);
        assertThat(returnedBook).isNotNull()
                .matches(book -> book.getId() > 0)
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedBook);

        assertThat(bookRepositoryJdbc.findById(returnedBook.getId()))
                .isPresent()
                .get()
                .isEqualTo(returnedBook);
    }

    @DisplayName("должен сохранять измененную книгу")
    @Test
    void shouldSaveUpdatedBook() {
        var expectedBook = new Book(1L, "BookTitle_10500", dbAuthors.get(2), dbGenres.get(2));

        assertThat(bookRepositoryJdbc.findById(expectedBook.getId()))
                .isPresent()
                .get()
                .isNotEqualTo(expectedBook);

        var returnedBook = bookRepositoryJdbc.save(expectedBook);
        assertThat(returnedBook).isNotNull()
                .matches(book -> book.getId() > 0)
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedBook);

        assertThat(bookRepositoryJdbc.findById(returnedBook.getId()))
                .isPresent()
                .get()
                .isEqualTo(returnedBook);
    }

    @DisplayName("должен удалять книгу по id ")
    @Test
    void shouldDeleteBook() {
        assertThat(bookRepositoryJdbc.findById(1L)).isPresent();
        bookRepositoryJdbc.deleteById(1L);
        assertThat(bookRepositoryJdbc.findById(1L)).isEmpty();
    }

    private  List<Author> getDbAuthors() {

        return jdbcTemplate.query(
                "select id, name from authors",
                (resultSet, rowNum) ->
                        new Author(
                                resultSet.getLong("id"),
                                resultSet.getString("name")
                        )
        );

    }

    private  List<Genre> getDbGenres() {
        return jdbcTemplate.query(
                "select id, name from genres",
                (resultSet, rowNum) ->
                        new Genre(
                                resultSet.getLong("id"),
                                resultSet.getString("name")
                        )
                );
    }

    private  List<Book> getDbBooks(List<Author> dbAuthors, List<Genre> dbGenres) {

        String sql = "select id, title, AUTHOR_ID, GENRE_ID from books";

        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) ->  {
                        long AUTHOR_ID = resultSet.getLong("AUTHOR_ID");
                        long  GENRE_ID = resultSet.getLong("GENRE_ID");

                        return new Book(
                                resultSet.getLong("id"),
                                resultSet.getString("title"),
                                dbAuthors.stream().filter(genre -> genre.getId() == AUTHOR_ID).findAny()
                                        .orElseThrow(() ->
                                                new EntityNotFoundException("Author with id %d not found".formatted(AUTHOR_ID))
                                        ),
                                dbGenres.stream().filter(author -> author.getId() == GENRE_ID).findAny()
                                        .orElseThrow(() ->
                                                new EntityNotFoundException("Genre with id %d not found".formatted(GENRE_ID))
                                        )
                        );
                } );
    }
}