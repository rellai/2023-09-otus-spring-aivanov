package ru.otus.aivanov.home14.documents;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@NoArgsConstructor
@Document(collection = "comments")
public class CommentDoc {

    @Id
    private String id;

    private String text;

    @DBRef
    private BookDoc book;

    public CommentDoc(String text, BookDoc book) {
        this.text = text;
        this.book = book;
    }
}
