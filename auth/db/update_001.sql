create table employee (
    id serial primary key,
    first_name varchar(2000),
    last_name varchar(2000),
    inn varchar(2000),
    hiring_date timestamp
);

create table person (
   id serial primary key not null,
   login varchar(2000),
   password varchar(2000),
   employee_id integer not null
);

insert into employee (id, first_name, last_name, inn, hiring_date) values
(1, 'sergey', 'petrov', '123', '2021-10-10T17:50:04+000'),
(2, 'oleg', 'ivanov', '345', '2021-10-17T17:50:04+000'),
(3, 'igor', 'popov', '567', '2021-10-19T17:50:04+000');


insert into person (login, password, employee_id) values ('serg', '111', 1);
insert into person (login, password, employee_id) values ('serg2', '222', 1);
insert into person (login, password, employee_id) values ('oleg12', '333', 2);

alter sequence employee_id_seq restart with 4;
alter sequence person_id_seq restart with 4;
