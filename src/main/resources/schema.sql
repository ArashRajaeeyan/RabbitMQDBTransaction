drop table student IF EXISTS;
create table student
(
   id integer not null,
   name varchar(255) not null,
   primary key(id)
);