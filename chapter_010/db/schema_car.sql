CREATE TABLE IF NOT EXISTS engine (
    id serial PRIMARY KEY,
    name varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS car (
    id serial PRIMARY KEY,
    name varchar NOT NULL,
    engine_id bigint NOT NULL UNIQUE REFERENCES engine(id)
);

CREATE TABLE IF NOT EXISTS driver (
    id serial PRIMARY KEY,
    name varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS history_owner (
    driver_id bigint NOT NULL REFERENCES driver(id),
    car_id bigint NOT NULL REFERENCES car(id),
    primary key (driver_id, car_id)
);

drop table history_owner;
drop table driver;
drop table car;
drop table engine;