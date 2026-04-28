CREATE SEQUENCE IF NOT EXISTS instrument_seq START WITH 1 INCREMENT BY 1;
create table if not exists Instrument
(
    id          int             PRIMARY KEY,
    type        varchar(25)     not null,
    brand       varchar(75)     not null,
    model       varchar(100)    not null,
    price       decimal(10,2)   not null,
    stock       int             not null
    );