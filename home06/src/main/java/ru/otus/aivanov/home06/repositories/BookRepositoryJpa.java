package ru.otus.aivanov.home06.repositories;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.aivanov.home06.models.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Book> findById(long id) {
        EntityGraph<?> entityGraph = em.getEntityGraph("books-comments-entity-graph");
        Map<String, Object> properties = new HashMap<>();
        properties.put(FETCH.getKey(), entityGraph);
        return Optional.ofNullable(em.find(Book.class, id, properties));
    }

    @Override
    public List<Book> findAll() {
       TypedQuery<Book> query = em.createQuery("select b from Book b " +
               "join fetch b.author " +
               "join fetch b.genre", Book.class);
       return query.getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            em.persist(book);
            return (book);
        }
        return em.merge(book);
    }

    @Override
    public boolean deleteById(long id) {
        Book book = em.find(Book.class, id);
        if (book != null) {
            em.remove(book);
            return true;
        }
        return false;
    }

}

