create table if not exists authors (
    id bigserial,
    name varchar(255),
    primary key (id)
);

create table if not exists genres (
    id bigserial,
    name varchar(255),
    primary key (id)
);

create table if not exists books (
    id bigserial,
    title varchar(255),
    author_id bigint not null references authors (id) on delete cascade,
    genre_id bigint not null references genres(id) on delete cascade,
    primary key (id)
);