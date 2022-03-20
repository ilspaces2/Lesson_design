create table auto(
	id serial primary key,
	model varchar(50)
);

create table driver(
	id serial primary key,
	driver_name varchar(50)
);

create table drivers_autos(
	id serial primary key,
	driver_id int references driver(id),
	auto_id int references auto(id)
);

insert into auto (model) values ('Lada');
insert into auto (model) values ('VW');
insert into auto (model) values ('BMW');

insert into driver (driver_name) values ('Jon');
insert into driver (driver_name) values ('Ben');
insert into driver (driver_name) values ('Adam');

insert into drivers_autos (driver_id, auto_id) values (1,1);
insert into drivers_autos (driver_id, auto_id) values (1,2);
insert into drivers_autos (driver_id, auto_id) values (1,3);
insert into drivers_autos (driver_id, auto_id) values (2,2);
insert into drivers_autos (driver_id, auto_id) values (2,3);
insert into drivers_autos (driver_id, auto_id) values (3,3);