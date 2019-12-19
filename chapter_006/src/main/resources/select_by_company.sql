CREATE TABLE company (
  id integer NOT NULL,
  name character varying,
  CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person (
  id integer NOT NULL,
  name character varying,
  company_id integer,
  CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO  company (id, name) VALUES
(1, 'company_1'), (2, 'company_2'), (3, 'company_3'), (4, 'company_4'), (5, 'company_5');

INSERT INTO person (id, name, company_id) VALUES
(1, 'person_1', 1),
(2, 'person_2', 2), (3, 'person_3', 2),
(4, 'person_4', 3), (5, 'person_5', 3), (6, 'person_6', 3),
(7, 'person_4', 4), (8, 'person_5', 4), (9, 'person_6', 4), (10, 'person_4', 4),
(11, 'person_4', 5), (12, 'person_5', 5), (13, 'person_6', 5), (14, 'person_4', 5), (15, 'person_4', 5);

SELECT p.name, c.name
FROM person p INNER JOIN company c
ON p.company_id = c.id AND c.id != 5;

SELECT c.name, count(*)
FROM person p INNER JOIN  company c
ON p.company_id = c.id
GROUP BY c.id
ORDER BY count(*) DESC
LIMIT 1;