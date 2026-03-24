CREATE SEQUENCE IF NOT EXISTS item_seq START WITH 1 INCREMENT BY 1;

create table if not exists Item
(
    id      BIGINT      PRIMARY KEY,
    code    varchar(6)  not null,
    type    varchar(50) not null,
    name    varchar(50) not null,
    price   decimal     not null,
    itemcount int       not null
    );