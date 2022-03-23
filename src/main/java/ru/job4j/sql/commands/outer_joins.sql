create table departments (
	id serial primary key,
	name varchar(100)
);
create table emploers(
	id serial primary key,
	name varchar(100),
	dep_id int references departments(id)
);

insert into departments (name) values ('Продажи');
insert into departments (name) values ('Охрана');
insert into departments (name) values ('Уборка');
insert into departments (name) values ('Отдел кадров');
insert into departments (name) values ('Разработка');

insert into emploers (name, dep_id) values ('Кассир',1);
insert into emploers (name, dep_id) values ('Старший кассир',1);
insert into emploers (name, dep_id) values ('Администратор',1);
insert into emploers (name, dep_id) values ('Уборщик',2);
insert into emploers (name, dep_id) values ('Мойщик окон',2);
insert into emploers (name, dep_id) values ('Садовник',null);
insert into emploers (name, dep_id) values ('Монтажник',null);
insert into emploers (name, dep_id) values ('Дизайнер',3);
insert into emploers (name, dep_id) values ('Разработчик',3);
insert into emploers (name, dep_id) values ('Водитель',null);


select e.name emp, d.name dep from emploers e left join departments d on e.dep_id=d.id;
select e.name emp, d.name dep from emploers e right join departments d on e.dep_id=d.id;
select e.name emp, d.name dep from emploers e full join departments d on e.dep_id=d.id;
select * from emploers cross join departments;

select d.name dep from departments d left join emploers e on e.dep_id=d.id where e.dep_id is null;

select e.name emp, d.name dep from emploers e left join departments d on e.dep_id=d.id;
select e.name emp, d.name dep from departments d right join emploers e on e.dep_id=d.id;

create table teens(
	name varchar(50),
	gender varchar(1)
);

insert into teens (name,gender) values ('Anna','W');
insert into teens (name,gender) values ('Ivan','M');
insert into teens (name,gender) values ('Petr','M');
insert into teens (name,gender) values ('Olya','W');
insert into teens (name,gender) values ('Svetlana','W');
insert into teens (name,gender) values ('Max','M');
insert into teens (name,gender) values ('Serj','M');
insert into teens (name,gender) values ('Natalia','W');


select n1.name name1, n2.name name2 from teens n1 cross join teens n2 where n1.gender!=n2.gender;






























