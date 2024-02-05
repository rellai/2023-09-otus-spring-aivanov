package ru.otus.aivanov.home14.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.otus.aivanov.home14.documents.AuthorDoc;
import ru.otus.aivanov.home14.documents.BookDoc;
import ru.otus.aivanov.home14.documents.CommentDoc;
import ru.otus.aivanov.home14.documents.GenreDoc;


import java.util.List;

@ChangeLog
public class InitMongoDB {


    @ChangeSet(order = "001", id = "dropDB", author = "rellai", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "002", id = "initAll", author = "rellai", runAlways = true)
    public void initAll(MongockTemplate template) {
        AuthorDoc pushkin = new AuthorDoc(null, "Пушкин");
        AuthorDoc tolstoy = new AuthorDoc(null, "Толстой");
        AuthorDoc lermontov = new AuthorDoc(null, "Лермонтов");
        GenreDoc poema = new GenreDoc(null, "Поэма");
        GenreDoc roman = new GenreDoc(null, "Роман");
        GenreDoc stih = new GenreDoc(null, "Стихотворение");
        BookDoc ruslan = new BookDoc(null, "Руслан и Людмила", pushkin, poema);
        BookDoc karenina = new BookDoc(null, "Анна Каренина", tolstoy, roman);
        BookDoc parus = new BookDoc(null, "Парус", lermontov, stih);
        CommentDoc comment1 = new CommentDoc("Хорошая книга1", ruslan);
        CommentDoc comment2 = new CommentDoc("Ничего но есть особенности1", ruslan);
        CommentDoc comment3 = new CommentDoc("а мне не понравилось1", ruslan);
        CommentDoc comment4 = new CommentDoc("Хорошая книга2", karenina);
        CommentDoc comment5 = new CommentDoc("Ничего но есть особенности2", karenina);
        CommentDoc comment6 = new CommentDoc("а мне не понравилось2", karenina);
        CommentDoc comment7 = new CommentDoc("Хорошая книга3", parus);
        CommentDoc comment8 = new CommentDoc("Ничего но есть особенности3", parus);
        CommentDoc comment9 = new CommentDoc("а мне не понравилось3", parus);
        template.insertAll(List.of(pushkin, tolstoy, lermontov));
        template.insertAll(List.of(poema, roman, stih));
        template.insertAll(List.of(ruslan, karenina, parus));
        template.insertAll(List.of(comment1, comment2, comment3, comment4, comment5, comment6,
                comment7, comment8, comment9));
    }

}
