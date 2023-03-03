SELECT `first_name`,`last_name`
FROM `employees`
WHERE lower(left(`first_name`,2)) = 'sa';

SELECT `first_name`,`last_name`
FROM `employees`
WHERE lower(`last_name`) LIKE '%ei%';

SELECT `first_name`
FROM `employees`
WHERE `department_id` IN (3,10) AND year(`hire_date`) BETWEEN '1995' AND '2005'
ORDER BY `employee_id`;

SELECT `first_name`,`last_name`
FROM `employees`
WHERE lower(`job_title`) NOT LIKE '%engineer%'
ORDER BY `employee_id`;

SELECT `name`
FROM `towns`
WHERE length(`name`) IN(5,6)
ORDER BY `name`;

SELECT `town_id`,`name`
FROM `towns`
WHERE left(`name`,1) IN('M', 'K', 'B', 'E')
ORDER BY `name`;

SELECT `town_id`,`name`
FROM `towns`
WHERE NOT left(`name`,1) IN('R', 'D', 'B')
ORDER BY `name`;

CREATE VIEW `v_employees_hired_after_2000` AS
SELECT `first_name`,`last_name`
FROM `employees`
WHERE year(`hire_date`) > '2000';

SELECT * FROM `v_employees_hired_after_2000`;

SELECT `first_name`,`last_name`
FROM `employees`
WHERE length(`last_name`) = 5;

SELECT `country_name`,`iso_code`
FROM `countries`
WHERE lower(`country_name`) LIKE '%a%a%a%'
ORDER BY `iso_code`;

SELECT `peak_name`,`river_name`,lower(concat('',left(`peak_name`,length(`peak_name`)-1),`river_name`)) AS `mix`
FROM `peaks`,`rivers`
WHERE right(`peak_name`,1)=left(`river_name`,1)
ORDER BY `mix`;

SELECT `name`, date_format(`start`,'%Y-%m-%d') as `start`
FROM `games`
WHERE year(`start`) in('2011','2012')
ORDER BY `start`,`name`
LIMIT 50;

SELECT `user_name`,REGEXP_REPLACE(`email`,('[a-z]*@'),'') AS `email provider`
FROM `users`
ORDER BY `email provider`,`user_name`;

SELECT `user_name`,`ip_address`
FROM `users`
WHERE `ip_address` LIKE '___.1%.%.___'
ORDER BY `user_name`;

SELECT `name` AS `game`, 
CASE WHEN hour(`start`) BETWEEN '0' AND '11' THEN 'Morning'
	 WHEN hour(`start`) BETWEEN '12' AND '17' THEN 'Afternoon'
     ELSE 'Evening' END 
AS `Part of the Day`, 
CASE WHEN `duration` BETWEEN '0' AND '3' THEN 'Extra Short'
	 WHEN `duration` BETWEEN '4' AND '6' THEN 'Short'
     WHEN `duration` BETWEEN '7' AND '10' THEN 'Long' 
     ELSE 'Extra Long' END 
AS `Duration`
FROM `games`;

SELECT * from orders;

Select `product_name`, `order_date`, ADDDATE(`order_date`, INTERVAL 3 DAY) AS `pay_due`, ADDDATE(`order_date`, INTERVAL 1 MONTH) AS `deliver_due`
From `orders`;