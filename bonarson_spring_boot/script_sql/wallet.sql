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

 p_account_id INT,
    p_start_date TIMESTAMP,
    p_end_date TIMESTAMP

     SELECT COALESCE(SUM(CASE WHEN Type = 'salair' or type="restaurant" THEN solde ELSE -solde END), 0)
    INTO total_balance_changes
    FROM account

    END;
SELECT calculate_balance_changes(1, '2023-01-01 00:00:00', '2023-12-31 23:59:59') AS total_changes;