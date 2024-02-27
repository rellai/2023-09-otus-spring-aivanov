merge into authors as dst
    using ( select 1 as id, 'author_1' as name
            union all
            select 2 as id, 'author_2' as name
            union all
            select 3 as id, 'author_3' as name) as src on dst.id = src.id
        when not matched then
        insert (id, name) values (src.id, src.name)
        when matched then update set name = src.name;

merge into genres as dst
    using ( select 1 as id, 'Genre_1' as name
            union all
            select 2 as id, 'Genre_2' as name
            union all
            select 3 as id, 'Genre_3' as name) as src on dst.id = src.id
when not matched then
    insert (id, name) values (src.id, src.name)
when matched then update set name = src.name;


merge into books as dst
    using (select 1 as id, 'BookTitle_1' as title, 1 as author_id, 1 as genre_id
           union all
           select 2 as id, 'BookTitle_2' as title, 2 as author_id, 2 as genre_id
           union all
           select 3 as id, 'BookTitle_3' as title, 3 as author_id, 3 as genre_id) as src on dst.id = src.id
when not matched then
    insert (id, title, author_id, genre_id)
    values (src.id, src.title, src.author_id, src.genre_id)
when matched then
    update set title = src.title,
               author_id = src.author_id,
               genre_id = src.genre_id;


merge into comments as dst
    using ( select 1 as id, 'comment_1_book_1' as text, 1 as book_id
            union all
            select 2 as id, 'comment_1_book_2' as text, 2 as book_id
            union all
            select 3 as id, 'comment_1_book_3' as text, 3 as book_id) as src on dst.id = src.id
when not matched then
    insert (id, text, book_id)
    values (src.id, src.text, src.book_id)
when matched then
    update set text = src.text,
               book_id = src.book_id;

merge into users as dst
    using
        (
            select 'admin' as username, '$2a$12$53QbNpfZbl.LZR0dKtP5Wun7Sn6.4szJ2i3c678eNnckt0Q3E14LW' as password, 'ROLE_ADMIN' as role union all
            select 'user' as username, '$2a$12$5epbQWWJrtSSyJbGfoChiO3QDqM6IbLboCijE9j1wuVxlu9t0nBr6' as password, 'ROLE_USER' as role
        ) src ON src.username = dst.username
when not matched then
    insert (username, password, role)
    values (src.username, src.password, src.role)
when matched then
    update
    set username = src.username,
        password = src.password,
        role = src.role;