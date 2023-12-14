package ru.otus.aivanov.home08.repositories;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.aivanov.home08.models.Author;
import ru.otus.aivanov.home08.models.Book;
import ru.otus.aivanov.home08.models.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе JPA для работы с книгами ")
@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.hw.repositories"})
class BookRepositoryMongoTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    AuthorRepository authorRepository;



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
            var actualBook = bookRepository.findById(expectedBook.getId());
            assertThat(actualBook).isPresent()
                    .get()
                    .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedBook);
        }
    }


    @DisplayName("должен сохранять новую книгу")
    @Test
    void shouldSaveNewBook() {
        var expectedBook = new Book("1", "BookTitle_10500", dbAuthors.get(0), dbGenres.get(0));
        var returnedBook = bookRepository.save(expectedBook);
        assertThat(returnedBook).isNotNull()
                .matches(book -> book.getId().equals("1"))
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedBook);

    }

    @DisplayName("должен сохранять измененную книгу")
    @Test
    void shouldSaveUpdatedBook() {
        var expectedBook = new Book("1", "BookTitle_10500", dbAuthors.get(0), dbGenres.get(0));
        bookRepository.save(expectedBook);

        assertThat(bookRepository.findById(expectedBook.getId()))
                .isPresent()
                .get()
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedBook);

        var returnedBook = bookRepository.save(expectedBook);
        assertThat(returnedBook).isNotNull()
                .matches(book -> book.getId().equals("1"))
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedBook);

        assertThat(bookRepository.findById(returnedBook.getId()))
                .isPresent()
                .get()
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(returnedBook);
    }

    @DisplayName("должен удалять книгу по id ")
    @Test
    void shouldDeleteBook() {
        for (int i = 0; i < dbBooks.size(); i++) {
            var expectedBook = dbBooks.get(i);
            assertThat(bookRepository.findById(expectedBook.getId())).isPresent();
            bookRepository.deleteById(expectedBook.getId());
            assertThat(bookRepository.findById(expectedBook.getId())).isEmpty();
        }
    }

    private  List<Author> getDbAuthors() {
        return authorRepository.findAll();
    }

    private  List<Genre> getDbGenres() {
        return genreRepository.findAll();
    }

    private  List<Book> getDbBooks(List<Author> dbAuthors, List<Genre> dbGenres) {
        return bookRepository.findAll();
    }
}