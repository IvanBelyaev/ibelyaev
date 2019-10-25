create database car_database;

create table car_bodies (
  id serial primary key,
  name varchar(50) not null
);

create table engines (
  id serial primary key,
  name varchar(50) not null
);

create table gearboxes (
  id serial primary key,
  name varchar(50) not null
);

create table cars (
	id serial primary key,
	car_name varchar(50) not null,
	body_id int references car_bodies(id),
engine_id int references engines(id),
gearbox_id int references gearboxes(id)
);

insert into car_bodies (id, name) values
(1, 'седан'), (2, 'хэтчбек'), (3, 'универсал'), (4, 'купе'), (5, 'кабриолет');

insert into engines (id, name) values (1, 'бензиновый двигатель'), (2, 'дизельный двигатель'), (3, 'газовый двигатель');
insert into gearboxes (id, name) values (1, 'ручная коробка'), (2, 'автоматическая коробка'), (3, 'коробка полуавтомат');

insert into cars (car_name, body_id, engine_id, gearbox_id) values
('машина_1', 1, 1, 2), ('машина_2', 2, 3, 3);

-- 1. Вывести список всех машин и все привязанные к ним детали.
select c.car_name, cb.name as body_name, e.name as engine_name, g.name as gearbox_name
from cars c
  inner join car_bodies cb on c.body_id = cb.id
  inner join engines e on c.engine_id = e.id
  inner join gearboxes g on c.gearbox_id = g.id;

-- 2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
select cb.name
from car_bodies cb left outer join cars c
  on cb.id = c.body_id
where c.id is null
union
select e.name
from engines e left outer join cars c
  on e.id = c.engine_id
where c.id is null
union
select g.name
from gearboxes g left outer join cars c
  on g.id = c.gearbox_id
where c.id is null;