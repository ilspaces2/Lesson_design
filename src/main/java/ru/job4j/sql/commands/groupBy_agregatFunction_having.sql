create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name,price) values ('Phone',2500.00);
insert into devices (name,price) values ('Tv',3000.550);
insert into devices (name,price) values ('Computer',5600.52);
insert into devices (name,price) values ('Watch',500.10);

insert into people (name) values ('Ivan');
insert into people (name) values ('Anna');
insert into people (name) values ('Vasya');
insert into people (name) values ('Max');
insert into people (name) values ('Viktoria');
insert into people (name) values ('Julia');

insert into devices_people (device_id,people_id) values (1,1);
insert into devices_people (device_id,people_id) values (2,1);
insert into devices_people (device_id,people_id) values (2,2);
insert into devices_people (device_id,people_id) values (4,3);
insert into devices_people (device_id,people_id) values (4,4);
insert into devices_people (device_id,people_id) values (3,4);
insert into devices_people (device_id,people_id) values (1,5);
insert into devices_people (device_id,people_id) values (2,5);
insert into devices_people (device_id,people_id) values (3,5);
insert into devices_people (device_id,people_id) values (4,5);
insert into devices_people (device_id,people_id) values (3,6);

select avg(price) from devices;

select p.name, avg(d.price) from devices_people dp 
join people p on p.id=dp.people_id
join devices d on d.id=dp.device_id
group by p.name;

select p.name, avg(d.price) from devices_people dp 
join people p on p.id=dp.people_id
join devices d on d.id=dp.device_id
group by p.name
having avg(d.price)>5000; 
