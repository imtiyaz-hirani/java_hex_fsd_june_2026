USE ecom_june_26;
 
INSERT INTO category (name, seq) VALUES
('Electronics', 1),
('Fashion', 2),
('Books', 3),
('Home & Kitchen', 4),
('Sports', 5);

 
INSERT INTO seller (name, city) VALUES
('Tech World', 'Mumbai'),
('Fashion Hub', 'Delhi'),
('Book Planet', 'Pune'),
('Home Store', 'Bangalore'),
('Sports Zone', 'Chennai');

 
INSERT INTO products
(title, price, description, stock_count, category_id, seller_id)
VALUES

('Laptop Dell Inspiron', 65000,
'15 inch laptop', 20, 1, 1),

('Wireless Mouse', 799,
'Bluetooth Mouse', 100, 1, 1),

('Samsung 24 Inch Monitor', 12000,
'Full HD Monitor', 35, 1, 1),

('Men T-Shirt', 699,
'Cotton Round Neck', 150, 2, 2),

('Women Jeans', 1499,
'Slim Fit Jeans', 70, 2, 2),

('Java Programming', 899,
'Complete Java Guide', 50, 3, 3),

('MySQL for Beginners', 650,
'Learn SQL Easily', 60, 3, 3),

('Mixer Grinder', 3499,
'750 Watt Mixer', 25, 4, 4),

('Cricket Bat', 2499,
'English Willow Bat', 30, 5, 5),

('Football', 899,
'FIFA Size 5 Football', 80, 5, 5);

 
INSERT INTO customers (name, email) VALUES
('Amit Sharma', 'amit@gmail.com'),
('Priya Patel', 'priya@gmail.com'),
('Rahul Verma', 'rahul@gmail.com'),
('Sneha Joshi', 'sneha@gmail.com'),
('Vikas Singh', 'vikas@gmail.com'),
('Neha Kapoor', 'neha@gmail.com'),
('Rohan Mehta', 'rohan@gmail.com'),
('Pooja Shah', 'pooja@gmail.com');

 
INSERT INTO customer_product
(customers_id, products_id, purchase_date, qty, discount)
VALUES

-- Amit
(1,1,'2025-06-01',1,5000),
(1,2,'2025-06-01',2,50),
(1,6,'2025-06-03',1,100),

-- Priya
(2,4,'2025-06-02',3,150),
(2,5,'2025-06-02',1,200),
(2,10,'2025-06-05',2,50),

-- Rahul
(3,1,'2025-06-04',1,3000),
(3,7,'2025-06-04',2,100),

-- Sneha
(4,2,'2025-06-05',1,0),
(4,3,'2025-06-05',1,500),
(4,8,'2025-06-06',1,200),

-- Vikas
(5,9,'2025-06-07',1,100),
(5,10,'2025-06-07',2,50),
(5,4,'2025-06-08',2,100),

-- Neha
(6,6,'2025-06-08',1,50),
(6,7,'2025-06-08',1,50),
(6,2,'2025-06-09',1,20),

-- Rohan
(7,1,'2025-06-10',1,4000),
(7,8,'2025-06-10',1,250),
(7,9,'2025-06-11',1,150),

-- Pooja
(8,3,'2025-06-12',2,1000),
(8,5,'2025-06-12',1,300),
(8,6,'2025-06-12',1,75),
(8,10,'2025-06-13',1,25);