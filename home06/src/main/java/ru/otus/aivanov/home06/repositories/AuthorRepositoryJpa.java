package ru.otus.aivanov.home06.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import ru.otus.aivanov.home06.models.Author;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author as a", Author.class);
        return query.getResultList();
    }

    @Override
    public Optional<Author> findById(long id) {
        try {
            return Optional.of(em.find(Author.class, id));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Author save(Author genre) {
        if (genre.getId() == null) {
            em.persist(genre);
            return (genre);
        }
        return em.merge(genre);
    }

    @Override
    public boolean deleteById(long id) {
        Author author = em.find(Author.class, id);
        if (author != null) {
            em.remove(author);
            return true;
        }
        return false;
    }

}

