create table locations(id bigint auto_increment, name varchar(255), lat numeric, lon numeric, primary key (id));

insert into locations(name, lat, lon) values ('LOC1', 1,1);
insert into locations(name, lat, lon) values ('LOC2', 2,2);
