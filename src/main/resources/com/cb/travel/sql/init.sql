# 用户表
create table t_user(
	id int(6) primary key auto_increment,
	username varchar(60),
	password varchar(60),
	email varchar(60)
);

# 省份表
create table t_province(
	id int(6) primary key auto_increment,
	name varchar(60),
	tags varchar(60),
	placecounts int(4)
);

# 景点表
create table t_place(
	id int(6) primary key auto_increment,
	name varchar(60),
	picpath varchar(100),
	hottime TIMESTAMP,
	hottickets double(7, 2),
	dimtickets double(7, 2),
	placedes varchar(300),
	provinceid int(6) REFERENCES t_province(id)
);