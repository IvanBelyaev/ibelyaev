create database producs;

create table type (
  id serial primary key,
  name varchar(50) not null
);

create table product (
	id serial primary key,
	name varchar(50) not null,
	type_id int references type(id),
expired_date date not null,
price numeric(5, 2) not null
);

insert into type (id, name) values
(1, 'сыр'), (2, 'молоко'), (3, 'колбаса'), (4, 'творог'), (5, 'кефир'), (6, 'мороженое');

insert into product (name, type_id, expired_date, price) values
('российский сыр', 1, '2019-10-23', 500),
('костромской сыр', 1, '2019-11-23', 550),
('молоко коровье', 2, '2020-10-23', 60),
('докторская колбаса', 3, '2019-10-30', 700),
('творог фермерский', 4, '2019-10-27', 800),
('кефир натуральный', 5, '2019-10-28', 100),
('мороженое в стаканчике1', 6, '2019-11-02', 50),
('мороженое в стаканчике2', 6, '2019-11-02', 50),
('мороженое в стаканчике3', 6, '2019-11-02', 50),
('мороженое в стаканчике4', 6, '2019-11-02', 50),
('мороженое в стаканчике5', 6, '2019-11-02', 50),
('мороженое в стаканчике6', 6, '2019-11-02', 50),
('мороженое в стаканчике7', 6, '2019-11-02', 50),
('мороженое в стаканчике8', 6, '2019-11-02', 50),
('мороженое в стаканчике9', 6, '2019-11-02', 50),
('мороженое в стаканчике10', 6, '2019-11-02', 50),
('мороженое в стаканчике11', 6, '2019-12-02', 50),
('мороженое в стаканчике12', 6, '2020-01-02', 50);

-- 1. Написать запрос получение всех продуктов с типом "СЫР"
select p.name
from product p inner join type t
  on p.type_id = t.id
where t.name = 'сыр';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select p.name
from product p
where p.name like '%мороженое%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select p.name
from product p
where extract(month from p.expired_date) = extract(month from now() + interval '1 month');

-- 4. Написать запрос, который выводит самый дорогой продукт.
select p.name
from product p
where p.price = (select max(price) from product);

-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.
select t.name, count(*)
from product p inner join type t
  on p.type_id = t.id
group by t.name;

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.name
from product p inner join type t
  on p.type_id = t.id
where t.name in('сыр', 'молоко');

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name
from product p inner join type t
  on p.type_id = t.id
group by t.name
having count(*) < 10;

-- 8. Вывести все продукты и их тип.
select p.id, p.name, t.name as type, p.expired_date, p.price
from product p inner join type t
  on p.type_id = t.id;