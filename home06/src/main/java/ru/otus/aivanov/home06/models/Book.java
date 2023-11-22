package ru.otus.aivanov.home06.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "books-comments-entity-graph",
        attributeNodes = {@NamedAttributeNode("comments"),@NamedAttributeNode("author"),@NamedAttributeNode("genre")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String title;

    @OneToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @OneToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.REMOVE, mappedBy = "book", orphanRemoval = true)
    /*@Fetch(FetchMode.JOIN)*/
    private List<Comment> comments;
}
