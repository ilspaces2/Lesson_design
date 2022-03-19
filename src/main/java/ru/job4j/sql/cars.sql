create table allcars (
    id serial primary key,
    model varchar(30),
    available boolean,
    price float(3)
);
insert into allcars (model,available,price) values ('Dodge',true ,50000.23);
insert into allcars (model,available,price) values ('Lada',true ,1123.50);
update allcars set available=false;
delete from allcars;