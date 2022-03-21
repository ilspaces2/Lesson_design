insert into role (name_role) values ('admin');
insert into role (name_role) values ('moderator');
insert into role (name_role) values ('user');

insert into users (name, phone, role_id) values ('Karl','+99891231212',1);
insert into users (name, phone, role_id) values ('Ben','+90991222222',2);
insert into users (name, phone, role_id) values ('Joe','+9999123333',3);
insert into users (name, phone, role_id) values ('Max','+8999124444',3);

insert into rules (description_rules) values ('Delete');
insert into rules (description_rules) values ('Update, Edit');
insert into rules (description_rules) values ('Write');

insert into rules_role (role_id, rules_id) values (1,1);
insert into rules_role (role_id, rules_id) values (1,2);
insert into rules_role (role_id, rules_id) values (1,3);
insert into rules_role (role_id, rules_id) values (2,2);
insert into rules_role (role_id, rules_id) values (2,3);
insert into rules_role (role_id, rules_id) values (3,3);

insert into state (info_state) values ('done');
insert into state (info_state) values ('not performed');

insert into category (info_category) values ('error');
insert into category (info_category) values ('help');
insert into category (info_category) values ('all');

insert into item (info, users_id, state_id, category_id) values ('Need help',3,2,2);
insert into item (info, users_id, state_id, category_id) values ('Broken page',2,2,1);

insert into comments (comment, item_id) values ('Help with phone',1);
insert into comments (comment, item_id) values ('Repear page on website',2);

insert into files (url, item_id) values ('screanshot.jpeg',1);
insert into files (url, item_id) values ('https://www.one.com/32',2);
