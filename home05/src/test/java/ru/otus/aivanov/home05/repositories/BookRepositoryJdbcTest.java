package ru.otus.aivanov.home05.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.otus.aivanov.home05.models.Author;
import ru.otus.aivanov.home05.models.Book;
import ru.otus.aivanov.home05.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

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
        dbBooks =getDbBooks();
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
        List<Author> Authors = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("select id, name from authors");

        for (Map row : rows) {
            Author author = new Author();
            author.setId(((Long) row.get("id")));
            author.setName((String) row.get("name"));
            Authors.add(author);
        }
        return Authors;

    }

    private  List<Genre> getDbGenres() {
        List<Genre> Genres = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("select id, name from genres");

        for (Map row : rows) {
            Genre genre = new Genre();
            genre.setId(((Long) row.get("id")));
            genre.setName((String) row.get("name"));
            Genres.add(genre);
        }
        return Genres;
    }

    private  List<Book> getDbBooks() {

        List<Book> Books = new ArrayList<>();
        String sql = "select b.id as b_id, b.title as b_title, a.id as a_id, a.name a_name, "
                + "g.id as g_id, g.name as g_name from books b " +
                "join authors a on b.author_id=a.id " +
                "join genres g on b.genre_id=g.id ";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Book book = new Book();

            book.setId(((Long) row.get("b_id")));
            book.setTitle((String) row.get("b_title"));
            book.setAuthor(new Author((Long) row.get("b_id"),(String) row.get("a_name")));
            book.setGenre(new Genre((Long) row.get("g_id"),(String) row.get("g_name")));

            Books.add(book);
        }

        return Books;
    }


}