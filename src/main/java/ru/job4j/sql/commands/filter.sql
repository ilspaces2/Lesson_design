create table type(
	id serial primary key,
	name varchar(30)
);

create table product(
	id serial primary key,
	name varchar(30),
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type (name) values ('СЫР');
insert into type (name) values ('МОЛОКО');
insert into type (name) values ('МУКА');
insert into type (name) values ('ВОДА');

insert into product (name, type_id, expired_date, price) values ('Сыр плавленный', 1, '2022.04.20',334);
insert into product (name, type_id, expired_date, price) values ('Сыр российский', 1, '2022.07.15',242);
insert into product (name, type_id, expired_date, price) values ('Сыр моцарелла', 1, '2022.09.21',523);

insert into product (name, type_id, expired_date, price) values ('Йогурт', 2, '2022.10.20',433);
insert into product (name, type_id, expired_date, price) values ('Творог 5%', 2, '2021.01.08',112.53);
insert into product (name, type_id, expired_date, price) values ('Мороженное простое', 2, '2022.10.20',343.32);
insert into product (name, type_id, expired_date, price) values ('Ряженка', 2, '2022.10.20',113.32);
insert into product (name, type_id, expired_date, price) values ('Творог 9%', 2, '2022.10.20',123.32);
insert into product (name, type_id, expired_date, price) values ('Вишневое мороженное', 2, '2022.10.20',23.32);
insert into product (name, type_id, expired_date, price) values ('Коктэйль', 2, '2022.10.20',73.32);
insert into product (name, type_id, expired_date, price) values ('Сливки', 2, '2020.10.20',63.32);
insert into product (name, type_id, expired_date, price) values ('Черничное мороженное', 2, '2022.10.20',13.32);
insert into product (name, type_id, expired_date, price) values ('Сырок творожный', 2, '2022.10.20',43.32);
insert into product (name, type_id, expired_date, price) values ('Торт творожный', 2, '2022.10.20',34.32);
insert into product (name, type_id, expired_date, price) values ('Сметана', 2, '2022.10.20',31.32);

insert into product (name, type_id, expired_date, price) values ('Хлеб', 3, '2022.08.20',60.90);
insert into product (name, type_id, expired_date, price) values ('Булка', 3, '2021.08.20',53.60);
insert into product (name, type_id, expired_date, price) values ('Хлеб белый', 3, '2023.08.20',23.22);
insert into product (name, type_id, expired_date, price) values ('Хлеб черный', 3, '2023.06.2',42.22);
insert into product (name, type_id, expired_date, price) values ('Булка с корицей', 3, '2023.04.24',52.2);
insert into product (name, type_id, expired_date, price) values ('Булка с сгущенкой', 3, '2023.03.15',42.22);
insert into product (name, type_id, expired_date, price) values ('Чипсы', 3, '2023.02.22',12.22);
insert into product (name, type_id, expired_date, price) values ('Кекс', 3, '2023.01.14',64.23);
insert into product (name, type_id, expired_date, price) values ('Рулет', 3, '2023.12.01',23);
insert into product (name, type_id, expired_date, price) values ('Багет', 3, '2020.11.22',55.72);
insert into product (name, type_id, expired_date, price) values ('Сочник', 3, '2023.10.30',76.22);
insert into product (name, type_id, expired_date, price) values ('Трубочка', 3, '2023.09.19',90);

insert into product (name, type_id, expired_date, price) values ('С газом', 4, '2023.08.20',14);
insert into product (name, type_id, expired_date, price) values ('Без газа', 4, '2023.09.01',22);
insert into product (name, type_id, expired_date, price) values ('Сок', 4, '2021.08.20',100);
insert into product (name, type_id, expired_date, price) values ('Нектар', 4, '2021.08.20',119);
insert into product (name, type_id, expired_date, price) values ('Вода детская', 4, '2022.08.20',55);


select * from product where type_id=1;

select * from product where lower(name) like ('%мороженное');

select * from product where expired_date < current_date;

select * from product where price in (select max(price) from product);

select t.name as type_name, count(p.type_id)
from product p join type t
on p.type_id=t.id
group by t.name;

select t.name type_product, p.name 
from product p join type t 
on p.type_id=t.id and (t.name='СЫР' or t.name='МОЛОКО');

select t.name type_name, count(p.type_id) quantity 
from product p join type t 
on p.type_id =t.id
group by t.name
having count(p.type_id) <10;

select t.name type_name, p.name, p.expired_date, p.price from product p join type t on p.type_id=t.id; 
