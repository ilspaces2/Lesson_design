/*one-to-one (с однонаправленной связью(unidirectional) */

create table vehicle_document(
	id serial primary key,
	vehicle_type varchar(50),
	model varchar(50),
	vin_number varchar(50)
);

create table vehicle(
	id serial primary key,
	vehicle_number varchar(50),
	document_id int references vehicle_document(id) unique
);

insert into vehicle_document (vehicle_type,model,vin_number) values ('Bus','Liaz','ZZ1QW2');
insert into vehicle (vehicle_number,document_id) values ('A777AA77',1);
