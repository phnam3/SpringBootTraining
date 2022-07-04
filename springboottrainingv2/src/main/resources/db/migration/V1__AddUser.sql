create table new_application.school
(
    id   bigserial primary key,
    name varchar(255)
);

insert into new_application.school(name)
values ('Australia'),
       ('Vietnam'),
       ('Singapore'),
       ('USA'),
       ('India'),
       ('Germany'),
       ('England');

create table new_application.user
(
    id   bigserial primary key,
    name varchar(255),
    gender varchar(1),
    school_id bigint
);

insert into new_application.user(name,gender,school_id)
values ('Giang','h', '1'),
       ('Khoa','h', '2'),
       ('Vu','h', '3'),
       ('Nguyen','h', '4'),
       ('Trung','h', '5'),
       ('Tu','h', '6'),
       ('Thinh','h', '1'),
       ('Duc','h', '7'),
       ('Dat','h', '7');