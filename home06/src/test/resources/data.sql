merge into authors(id, name) key(ID)
    values (1, 'Author_1'), (2, 'Author_2'), (3, 'Author_3');

merge into genres(id, name) key(ID)
    values (1, 'Genre_1'), (2, 'Genre_2'), (3, 'Genre_3');

merge into books(id, title, author_id, genre_id) key(ID)
    values (1, 'BookTitle_1', 1, 1), (2, 'BookTitle_2', 2, 2), (3, 'BookTitle_3', 3, 3);

merge into comments(id, text, book_id) key(ID)
    values (1, 'comment_1_book_1', 1), (2, 'comment_2_book_1', 1), (3, 'comment_3_book_1', 1),
           (4, 'comment_1_book_2', 2), (5, 'comment_2_book_2', 2), (6, 'comment_3_book_2', 2),
           (7, 'comment_1_book_3', 3), (8, 'comment_2_book_3', 3), (9, 'comment_3_book_3', 3);
