create table body(
	id serial primary key,
	number int
);

create table engine(
	id serial primary key,
	number int
);

create table gearbox(
	id serial primary key,
	number int,
	type varchar(30)
);

create table car ( 
	id serial primary key,
	name varchar,
	body_id int references body(id) not null,
	engine_id int references engine(id) not null,
	gearbox_id int references gearbox(id) not null
);

insert into body (number) values (123);
insert into body (number) values (113);
insert into body (number) values (345);
insert into body (number) values (666);
insert into body (number) values (234);
insert into body (number) values (212);

insert into engine (number) values (4233);
insert into engine (number) values (5232);
insert into engine (number) values (5222);
insert into engine (number) values (7455);
insert into engine (number) values (7454);
insert into engine (number) values (0923);

insert into gearbox (number, type) values (12334, 'manual');
insert into gearbox (number, type) values (52352, 'manual');
insert into gearbox (number, type) values (23536, 'auto');
insert into gearbox (number, type) values (75657, 'auto');
insert into gearbox (number, type) values (85566, 'manual');
insert into gearbox (number, type) values (98765, 'auto');

insert into car (name, body_id, engine_id, gearbox_id) values ('BMW', 1, 1, 1);
insert into car (name, body_id, engine_id, gearbox_id) values ('Lada', 2, 2, 2);
insert into car (name, body_id, engine_id, gearbox_id) values ('Opel', 3, 4, 5);
insert into car (name, body_id, engine_id, gearbox_id) values ('KIA', 4, 3, 6);

select name car_name, b.number b_num, e.number e_num, g.number g_num, g.type g_type
from car join body b on car.body_id=b.id
join engine e on car.engine_id=e.id
join gearbox g on car.gearbox_id=g.id;

select b.number body_num from car 
right join body b on car.body_id=b.id where car.body_id is null;

select e.number engine_num from car                                   
right join engine e on car.engine_id=e.id where car.engine_id is null;

select g.number gearbox_num, g.type gearbox_type from car
right join gearbox g on car.gearbox_id=g.id where car.gearbox_id is null;
