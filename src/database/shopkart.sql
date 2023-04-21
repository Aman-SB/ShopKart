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

drop table customer; 

select * from customer;

show tables;

select * from customer;

alter table customer add unique(email);

insert INTO customer (name,email,mobile,password,address) values('ajay','ajay@gmail.com','919989439254','1111','haldwani'); 

show tables;

desc customer;

-- product

INSERT INTO product(name,price,quantity) values('MSI Lepoard GP66' , 115000 , 50);
INSERT INTO product(name,price,quantity) values('Huwaei P60' , 59999 , 100);
INSERT INTO product(name,price,quantity) values('Vivo X6 Pro' , 69999 , 500);
INSERT INTO product(name,price,quantity) values('Motorola Edge 40 Pro' , 40000 , 200); 

SELECT id,name,price FROM product;

delete from product where id = 2;

-- orders tables

SELECT * FROM orders;

INSERT INTO orders(group_order_id,customer_id,product_id) VALUES (1,1,1);

SELECT MAX(group_order_id) +1 id FROM orders;

SELECT orders.id , product.name , orders.quantity , orders.order_date , orders.order_status  FROM orders JOIN product ON orders.product_id = product.id;