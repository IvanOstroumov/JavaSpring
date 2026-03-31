CREATE SEQUENCE IF NOT EXISTS post_seq START WITH 1 INCREMENT BY 1;
create table if not exists Post
(
    id          int             PRIMARY KEY,
    title       varchar(200)    not null,
    pubdate     date            not null,
    category    varchar(50)     not null,
    author      varchar(50)     not null,
    likes       int             not null,
    content     clob            not null
    );