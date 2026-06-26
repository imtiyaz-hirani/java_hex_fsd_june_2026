create database hex_june_26;
use hex_june_26;

create table products(id int primary key auto_increment, 
title varchar(255) not null, price double default 0, category varchar(255));

insert into products(title, price, category) values ('Apple phone', 450, 'mobile'), ('Oppo phone', 350, 'mobile'), 
('HP 700', 670, 'laptop');
desc products;

/*
	AIM: CAP to display the list of products
*/
DELIMITER $$
create procedure all_products()
BEGIN
	select * from products;
END
$$ 

-- Call the procedure
CALL all_products();
/*
	AIM: CAP to display the list of products based on category
*/
DELIMITER $$
 create procedure get_products_by_category(IN p_category varchar(255)) -- In parameter
  BEGIN
  
 -- validate the input 
 if p_category = '' then
	signal sqlstate "45000" -- telling your db, that i signal you to throw an error(exception) 
    SET message_text = "category should not be blank" ;
 end if;
 -- write SQL 
	select * 
    from products
    where category = p_category;
 END
$$

drop procedure get_products_by_category;
CALL get_products_by_category('mobile');

-- session variables in SQL 
@my_var = 34;

-- proc 1
-- proc 2
-- proc 3 
select @my_var;

-- CAP to count number of products based on given price value [the products should have the price more than the given value]
-- hint [out param]

DELIMITER $$
create procedure get_products_by_price_higher(IN p_price double , OUT cnt_products int)
BEGIN

select count(*) into cnt_products
from products
where price > p_price; 

END
$$
CALL get_products_by_price_higher(400, @count_products);
select @count_products;

/*
	Without Param
    IN Pram
    IN and OUT Param
    INOUT Param 
    
    Views 
    Trigger
    
    Function
    Cursor
*/

-- CAP to update the category of the product based on the given id using INOUT param 
DELIMITER $$
create procedure update_category(IN p_id INT, INOUT p_category varchar(255)) 
-- u read category , update it nd give it back in same variable
BEGIN
	update products
    SET category = p_category
    where id = p_id;
END
$$

SET @cat_val = "computer";
CALL update_category(3, @cat_val);
select @cat_val;
 
-- Views 
-- create view to show all products without revealing the price 
create view product_view_v1 AS 
select id,title,category
from products;

-- create view to hide record that belong to category 'computer'.
create view product_view_v2 AS
select * 
from products
where category NOT IN ('computer'); 



