
--SQL statement to know which salesman are working for which customer
SELECT a.customer_name, a.city, s.SALESMAN_NAME FROM customers a 
INNER JOIN salesman s ON a.salesman_id=s.salesman_id;

-- Write an SQL statement to make a list in ascending order for the customer who holds a grade less than 300 and works either through a salesman
SELECT a.customer_name, a.city, a.grade, s.SALESMAN_NAME, s.SALESMAN_CITY FROM customers a 
LEFT OUTER JOIN salesman s ON a.salesman_id=s.salesman_id WHERE a.grade<300 
ORDER BY a.customer_id;

--Write an SQL statement to find the list of customers who appointed a salesman for their jobs who gets a commission from the company is more than 12%
SELECT a.customer_name , a.city, s.SALESMAN_NAME, s.commission FROM customers a 
INNER JOIN salesman s ON a.salesman_id=s.salesman_id 
WHERE s.commission>12;

-- Write an SQL statement to find the details of a order i.e. order number, order date, amount of order, which customer gives the order and which salesman works for that customer and commission rate he gets for an order.
SELECT o.order_no, o.order_date, o.purchase_amount, b.customer_name, b.grade, s.SALESMAN_NAME, s.commission FROM orders o 
INNER JOIN customers b ON o.customer_id=b.customer_id 
INNER JOIN salesman s ON o.salesman_id=s.salesman_id;