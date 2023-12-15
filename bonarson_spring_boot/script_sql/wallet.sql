create database wallet;
\c wallet 

create table customer(
    id_customer int primary key,
    name_customer varchar(100)
);

 create table account(
    IdAccount int primary key,
    AccountName varchar(20),
    solde float ,
    date_heure timestamp,
    Type varchar(50),
    id_customer int references customer(id_customer)
);

--insert CUSTOMER
insert into customer values (1,'C1');
insert into customer values (2,'C1');

--TD2
-- Ajout de la table Categories
create table categories(
    id_category serial primary key,
    category_name varchar(50) unique
);

-- Ajout de la colonne id_category à la table transaction
alter table transaction
add column id_category int references categories(id_category);