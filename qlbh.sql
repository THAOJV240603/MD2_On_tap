create database qlbh;
use qlbh;

drop database qlbh;

create table category(
	id int primary key auto_increment,
    name varchar(100) not null unique,
    status bit(1) default(1)
);

create TABLE product(
	id int primary key auto_increment,
    name varchar(100) unique not null,
    price float,
    sale_price float, 
    check (price > sale_price > 0),
    image varchar(255),
    category_id int not null,
    status bit(1) default 1,
    foreign key(category_id) references category(id)
);

select * from category;
select * from product;