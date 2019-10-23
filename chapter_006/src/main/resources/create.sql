create database request_system;

create table roles (
    id serial primary key,
    role_name varchar(50) not null
);

create table rules (
    id serial primary key,
    rule varchar(50) not null
);

create table role_rules (
    roles_id int references roles(id),
    rules_id int references rules(id)
);

create table users (
    id serial primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(50) not null,
    phone varchar(20) not null,
    role_id int references roles(id)
);

create table categories (
    id serial primary key,
    category_name varchar(50) not null
);

create table states (
    id serial primary key,
    state_name varchar(50) not null
);

create table items (
    id serial primary key,
    item_name varchar(50) not null,
    user_id int references users(id),
    category_id int references categories(id),
    state_id int references states(id)
);

create table attaches (
    id serial primary key,
    file varchar(200) not null,
    item_id int references items(id)
);

create table comments (
    id serial primary key,
    comment varchar(200) not null,
    item_id int references items(id)
);

insert into states (id, state_name) values (1, 'Принята'), (2, 'На рассмотрении'), (3, 'Выполнена');
insert into categories (id, category_name) values (1, 'Срочная'), (2, 'Не срочная');
insert into rules (id, rule) values (1, 'Разрешено добавлять'), (2, 'Разрешено изменять'), (3, 'Разрешено удалять');
insert into roles (id, role_name) values (1, 'Администратор'), (2, 'Пользователь');
insert into role_rules (roles_id, rules_id) values (1, 1), (1, 2), (1, 3), (2, 1), (2, 2);

insert into users (id, first_name, last_name, email, phone, role_id) values
(1, 'ivan', 'ivanov', 'ivanov@gmail.com', '8-888', 1), (2, 'semen', 'semenov', 'semenov@gmail.com', '12-345', 2);

insert into items (id, item_name, user_id, category_id, state_id) values
(1, 'item1', 1, 2, 1), (2, 'item2', 2, 1, 2), (3, 'item3', 1, 1, 1);

insert into attaches (file, item_id) values ('file1', 1), ('file2', 1), ('file3', 3);
insert into comments (comment, item_id) values ('comment1', 2), ('comment2', 2), ('comment3', 3);