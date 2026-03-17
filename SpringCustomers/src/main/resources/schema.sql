CREATE SEQUENCE IF NOT EXISTS customer_seq START WITH 1 INCREMENT BY 1;

create table if not exists Customer
(
    id      BIGINT      PRIMARY KEY,
    name    varchar(50) not null,
    surname varchar(50) not null,
    age     int         not null,
    city    varchar(20) not null,
    ccnumber bigint not null,
    ccexpiration varchar(10) not null,
    cccvv int not null
);