package ru.otus.aivanov.home06.models;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "authors")
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String name;
}
