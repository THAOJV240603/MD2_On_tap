create database qlbh;
use qlbh;

create table category(
	id int primary key auto_increment,
    name varchar(100) not null unique,
    status bit(1) default(1)
);

create TABLE product(
	id int primary key auto_increment,
    name varchar(100) unique not null,
    price float check (price >0),
    sale_price float, 
    check (sale_price < price),
    image varchar(255),
    category_id int not null,
    status bit(1) default 1,
    foreign key(category_id) references category(id)
);

select * from category;
select * from product;