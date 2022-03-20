create table car(
	id serial primary key,
 	car_name varchar(50)
);

create table model(
 	id serial primary key,
 	model_name varchar(50),
 	car_id int references car(id)
);

insert into car (car_name) values ('BMW');
insert into model (model_name,car_id) values ('X5',1);
insert into model (model_name,car_id) values ('530',1);

select * from car where id in (select car_id from model);