create table if not exists authors (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY (start with 4) NOT NULL,
    name varchar(255),
    primary key (id)
);

create table if not exists genres (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY (start with 4) NOT NULL,
    name varchar(255),
    primary key (id)
);

create table if not exists books (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY (start with 4) NOT NULL,
    title varchar(255),
    author_id bigint not null references authors (id) on delete cascade,
    genre_id bigint not null references genres(id) on delete cascade,
    primary key (id)
);

create table if not exists comments
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY (start with 10) NOT NULL,
    text     VARCHAR(255),
    book_id BIGINT,
    PRIMARY KEY (id)
);

ALTER TABLE comments
    ADD CONSTRAINT FK_COMMENTS_ON_BOOK FOREIGN KEY (book_id) REFERENCES books (id) on delete cascade;