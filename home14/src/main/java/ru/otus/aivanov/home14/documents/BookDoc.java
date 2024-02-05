package ru.otus.aivanov.home14.documents;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Getter
@Setter
@Document(collection = "books")
public class BookDoc {
    @Id
    private String id;

    private String title;

    private AuthorDoc author;

    private GenreDoc genre;

    public BookDoc(String id, String title, AuthorDoc author, GenreDoc genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

}
