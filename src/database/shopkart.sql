create schema shopkart;

use shopkart;

create table `shopkart`.`customer` (
`id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(60) NOT NULL,
`email` VARCHAR(100) NOT NULL,
`mobile` CHAR(12) NOT NULL,
`password` VARCHAR(120) NOT NULL,
`address` VARCHAR(200) NOT NULL,
PRIMARY KEY (`id`)); 

CREATE TABLE shop_clone (`shop_id` INT NOT NULL PRIMARY KEY, `cust_id` REFERENCE customer FOREIGN KEY, 

drop table customer; 

select * from customer;

show tables;

select * from customer;

delete from customer where id = 30;

alter table customer add unique(email);

insert INTO customer (name,email,mobile,password,address) values('ajay','ajay@gmail.com','919989439254','1111','haldwani'); 

INSERT INTO customer (name,email,mobile,password,address) VALUES('ajay','ajay@gmail.com','919989439254','1111','haldwani'); 

show tables;

desc customer;

-- product

INSERT INTO product(name,price,quantity) values('MSI Lepoard GP66' , 115000 , 50);
INSERT INTO product(name,price,quantity) values('Huwaei P60' , 59999 , 100);
INSERT INTO product(name,price,quantity) values('Vivo X6 Pro' , 69999 , 500);
INSERT INTO product(name,price,quantity) values('Motorola Edge 40 Pro' , 40000 , 200);
INSERT INTO product(name,price,quantity) values('Hisense 139 cm (55 inches) Bezelless Series 4K Ultra HD Smart LED Google TV 55A6H (Black)',59900,10000);
INSERT INTO product(name,price,quantity) values('Sony Bravia 139 cm (55 inches) 4K Ultra HD Smart LED Google TV KD-55X74K (Black)',57900,13452);
INSERT INTO product(name,price,quantity) values('Lloyd 1.5 Ton 3 Star Inverter Split AC ',58990,48352);
INSERT INTO product(name,price,quantity) values('Samsung 192 L 2 Star Direct Cool Single Door Refrigerator',19990,84372);
INSERT INTO product(name,price,quantity) values('',,);

SELECT id,name,price FROM product;

SELECT * from product;

delete from product where id = 2;

-- orders tables

SELECT * FROM orders;

INSERT INTO orders(group_order_id,customer_id,product_id) VALUES (1,1,1);

SELECT MAX(group_order_id) +1 id FROM orders;

SELECT orders.id , product.name , product.price , orders.quantity , orders.order_date , orders.order_status  FROM orders JOIN product ON orders.product_id = product.id WHERE orders.customer_id = 1 ;

-- deactivating safe mode

SET SQL_SAFE_UPDATES = 0;

-- updating encryption password

UPDATE customer SET password = "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4" WHERE name = "aman";

UPDATE customer SET password = "9af15b336e6a9619928537df30b2e6a2376569fcf9d7e773eccede65606529a0" WHERE name = "ankit";

UPDATE customer SET password = "0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c" WHERE name = "ajay";

-- activating safe mode

SET SQL_SAFE_UPDATES = 1;

UPDATE customer SET name = "Ajay" where id = 3 ;