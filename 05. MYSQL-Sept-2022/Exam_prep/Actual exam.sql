#2
INSERT INTO products(name,type,price)
SELECT 
    CONCAT(last_name, ' specialty'),
    'Cocktail',
    CEIL(salary * 0.01)
FROM
    waiters
WHERE
    id > 6;
    
#3
UPDATE orders 
SET 
    table_id = table_id - 1
WHERE
    id BETWEEN 12 AND 23;
    
#4
DELETE w FROM waiters AS w
        LEFT JOIN
    orders AS o ON o.waiter_id = w.id 
WHERE
    o.id IS NULL;
    
#5
SELECT 
    *
FROM
    clients
ORDER BY birthdate DESC , id DESC;

#6
SELECT 
    first_name, last_name, birthdate, review
FROM
    clients
WHERE
    card IS NULL
        AND YEAR(birthdate) BETWEEN 1978 AND 1993
ORDER BY last_name DESC , id
LIMIT 5;

#7
SELECT 
    CONCAT(last_name,
            first_name,
            LENGTH(first_name),
            'Restaurant') AS username,
    REVERSE(SUBSTR(email, 2, 12)) AS 'password'
FROM
    waiters
WHERE
    salary IS NOT NULL
ORDER BY `password` DESC;

#8
SELECT 
    p.id, p.name, COUNT(o.id) AS count
FROM
    products AS p
        JOIN
    orders_products AS op ON op.product_id = p.id
        JOIN
    orders AS o ON o.id = op.order_id
GROUP BY p.id
HAVING COUNT(o.id) >= 5
ORDER BY count DESC , p.name;

#9
SELECT 
    t.id AS table_id,
    t.capacity,
    COUNT(c.id) AS count_clients,
    CASE
        WHEN COUNT(c.id) < t.capacity THEN 'Free seats'
        WHEN COUNT(c.id) = t.capacity THEN 'Full'
        ELSE 'Extra seats'
    END AS availability
FROM
    `tables` AS t
        JOIN
    orders AS o ON o.table_id = t.id
        JOIN
    orders_clients AS oc ON o.id = oc.order_id
        JOIN
    clients AS c ON c.id = oc.client_id
WHERE
    floor = 1
GROUP BY t.id
ORDER BY t.id DESC;

#10
DELIMITER $$
CREATE FUNCTION udf_client_bill(full_name VARCHAR(50))
RETURNS DECIMAL(19,2)
DETERMINISTIC
BEGIN
	RETURN (SELECT 
		SUM(p.price)
	FROM
		orders AS o
			JOIN
		orders_products AS op ON o.id = op.order_id
			JOIN
		products AS p ON p.id = op.product_id
			JOIN
		orders_clients AS oc ON o.id = oc.order_id
			JOIN
		clients AS c ON c.id = oc.client_id
	WHERE concat_ws(' ',c.first_name,c.last_name) = full_name
		GROUP BY c.id);
END$$

SELECT c.first_name,c.last_name, udf_client_bill('Charil Sheaber') as 'bill' FROM clients c
WHERE c.first_name = 'Charil' AND c.last_name= 'Sheaber'$$

#12
CREATE PROCEDURE udp_happy_hour(`type` VARCHAR(50))
BEGIN
	UPDATE products AS p 
	SET 
		p.price = p.price * 0.8
	WHERE
		p.price >= 10 AND p.type = `type`;
END$$

CALL udp_happy_hour ('Cognac');
