create database wallet;
\c wallet 

create table customer(
    id_customer int primary key,
    name_customer varchar(100)
);