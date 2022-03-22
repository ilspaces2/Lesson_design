create table equipment(
	id serial primary key,
	os varchar(30),
	ram varchar(30),
	dvd boolean
);

create table computer(
	id serial primary key,
	name varchar(30),
	equipment_id int references equipment(id)
);

insert into equipment (os,ram,dvd) values ('Windows','8Gb',true);
insert into equipment (os,ram,dvd) values ('Linux','4Gb',true);
insert into equipment (os,ram,dvd) values ('Mac','16Gb',false);
insert into equipment (os,ram,dvd) values ('MS-DOS','16Gb',false);
insert into equipment (os,ram,dvd) values ('32Gb',true);

insert into computer (name,equipment_id) values ('Acer',1);
insert into computer (name) values ('Samsung');
insert into computer (name,equipment_id) values ('Apple',3);
insert into computer (name,equipment_id) values ('HP',2);
insert into computer (name,equipment_id) values ('Asus',1);
insert into computer (name,equipment_id) values ('Lenovo',4);
insert into computer (name,equipment_id) values ('Msi',5);
insert into computer (name) values ('Benq');

select pk.name,eq.os from computer as pk join equipment as eq on eq.id=pk.equipment_id;
select pk.name as brand, eq.os, eq.ram from computer pk join equipment eq on eq.id=pk.equipment_id and eq.os  is not null;
select * from computer pk join equipment eq on pk.equipment_id=eq.id and ram='16Gb';