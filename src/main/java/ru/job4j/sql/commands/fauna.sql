create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name,avg_age,discovery_date) values ('whole',40000,'01.01.1700');
insert into fauna (name,avg_age,discovery_date) values ('shark',35000,'01.01.1900');
insert into fauna (name,avg_age,discovery_date) values ('fish',20000,'01.01.1500');
insert into fauna (name,avg_age,discovery_date) values ('turtle',50000,null);
insert into fauna (name,avg_age,discovery_date) values ('snake',12000,'11.01.1970');
insert into fauna (name,avg_age,discovery_date) values ('lion',15000,'11.01.1999');
insert into fauna (name,avg_age,discovery_date) values ('plankton',5000,null);
insert into fauna (name,avg_age,discovery_date) values ('creazyfish',null,null);

select * from fauna where name like '%fish';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date <'01.01.1950';
