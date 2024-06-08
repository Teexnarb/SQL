ALTER TABLE student
ADD CONSTRAINT age_constraint CHECK ( age > 16 ),
ALTER COLUMN age SET DEFAULT '20',
ALTER COLUMN name SET NOT NULL,
ADD CONSTRAINT name_unique UNIQUE (name);


create table person (
id integer primary key,
name text,
age integer,
is_driver boolean,
car_id integer not null references car (id)
);


create table car(
id integer primary key,
make text,
model text,
price integer
);

SELECT student.name, student.age
FROM student
INNER JOIN faculty ON student.faculty_id = faculty.id

SELECT student.name, student.age
FROM student
INNER JOIN avatar ON student.id = avatar.id