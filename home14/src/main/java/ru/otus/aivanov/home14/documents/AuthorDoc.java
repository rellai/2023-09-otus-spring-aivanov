package ru.otus.aivanov.home14.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "authors")
public class AuthorDoc {

    @Id
    private String id;

    private String name;
}
