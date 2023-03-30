CREATE SCHEMA IF NOT EXISTS employeer;

create table if not exists employeer.employee
(
    id serial primary key,
    firstname varchar,
    lastname varchar,
    email varchar,
    password varchar,
    status boolean,
    gender varchar,
    roles varchar[]
);
