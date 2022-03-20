/*one-to-one (с двунаправленной связью(bidirectional) */

create table vehicle_document(
	id serial primary key,
	vehicle_type varchar(50),
	model varchar(50),
	vin_number varchar(50)
);

create table vehicle(
	id serial primary key,
	vehicle_number varchar(50)
);

create table vehicle_info(
	id serial primary key,
	vehicle_id int references vehicle(id) unique,
	vehicle_document_id int references vehicle_document(id) unique
);

insert into vehicle_document (vehicle_type, model, vin_number) values ('Bus','Liaz','ZZ1QW2');
insert into vehicle (vehicle_number) values ('A777AA77');
insert into vehicle_info (vehicle_id, vehicle_document_id) values (1,1);

