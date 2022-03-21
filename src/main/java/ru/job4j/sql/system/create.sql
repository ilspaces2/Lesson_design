create table role(
	id serial primary key,
	name_role varchar(50)
);

create table users(
	id serial primary key,
	name varchar(50),
	phone varchar(30),
	role_id int references role(id)
);

create table rules(
	id serial primary key,
	description_rules text
);

create table rules_role(
	id serial primary key,
	role_id int references role(id),
	rules_id int references rules(id)
);

create table state(
	id serial primary key,
	info_state varchar(50)
);

create table category(
	id serial primary key,
	info_category varchar(100)
);

create table item(
	id serial primary key,
	info text,
	users_id int references users(id),
	state_id int references state(id),
	category_id int references category(id)
);

create table comments(
	id serial primary key,
	comment text,
	item_id int references item(id)
);

create table files(
	id serial primary key,
	url text,
	item_id int references item(id)
);
