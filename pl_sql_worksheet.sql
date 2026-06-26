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

-- Create table order 
create table order_tbl (id int primary key auto_increment, 
product_id INT, 
qty int, 
order_date DATE);

-- ADD a field stock_count to products table
Alter table products
ADD COLUMN stock_count INT;

-- update the stock_count 
update products SET stock_count=2 where id=1;
update products SET stock_count=1 where id=2;
update products SET stock_count=0 where id=3;

/*
Trigger is a program that get call automatically by DB Manager, when a procedure is called. 
Trigger is attached to a procedure. 
It gets CALLED, either BEFORE or AFTER the procedure depending on the way we configure it.
*/

-- I am going to create a trigger to ensure that the insert on order_tbl only happens if the product has enough stock 

insert into order_tbl(product_id, qty,order_date) values (1,1,now());

/*
NEW.product_id = 1
NEW.qty = 1
*/
DELIMITER $$
create trigger trg_check_stock_quantity 
BEFORE INSERT ON order_tbl
FOR EACH ROW 
BEGIN
	DECLARE v_stock INT; 
    
	-- fetch stock_count from products for given id (NEW.product_id) & save in variable v_stock
    select stock_count into v_stock
    from products
    where id = NEW.product_id; 
    
    -- now i have the available stock_qty in v_stock variable 
    
    -- lets check if this v_stock is less than NEW.qty (stock qty requested)
    if v_stock < NEW.qty THEN
		SIGNAL sqlstate '45000'
        SET message_text = 'Not enough stock available for product';
	End if;
END
$$
insert into order_tbl(product_id, qty,order_date) values (1,1,now());

/*
NEW.product_id = 1
NEW.qty = 1
*/
-- trigger for updating the stock count of the product after insert in order table. 

DELIMITER $$
create trigger trg_update_stock_quantity
AFTER insert on order_tbl 
FOR EACH ROW
BEGIN
	update products 
    SET stock_count = stock_count - NEW.qty
    where id = NEW.product_id; 
END
$$

-- Functions 
/*
- Functions can be used inside the procedures. 
- Functions can perform the calculations that we could save centrally. 
- For Ex, MySQl DB, has got pre build functions like now() that displays the date. 
--- We are going to build our own custom functions for our own use cases of our product/app

Rule: Function has to have a return statement  
*/

-- create a function that returns age based on DOB. yyyy-MM-dd , age  1983
DELIMITER $$
create function fnComputeAge(v_dob varchar(255))
returns int
deterministic
BEGIN
	DECLARE current_year INT; 
	DECLARE age INT; 
    DECLARE dob_year INT; 
    
	-- get the current running year 
    SET current_year = YEAR(curdate());
    
    -- get the year from DOB 
    SET dob_year = YEAR(v_dob);
    
    -- calc the age
    SET age = current_year - dob_year;
    
    return age;
END
$$


select YEAR('2023-07-12');
select fnComputeAge('1983-11-15');

















