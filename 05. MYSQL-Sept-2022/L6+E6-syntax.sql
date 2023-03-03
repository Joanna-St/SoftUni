### Lecture ###

#1
SELECT 
    e.employee_id,
    CONCAT_WS(' ', e.first_name, e.last_name) AS full_name,
    d.department_id,
    d.name AS department_name
FROM
    employees AS e
        JOIN
    departments AS d ON d.manager_id = e.employee_id
ORDER BY employee_id
LIMIT 5;

#2
SELECT 
    t.town_id, `name` AS town_name, address_text
FROM
    towns AS t
        JOIN
    addresses AS a ON a.town_id = t.town_id
WHERE
    t.name IN ('San Francisco' , 'Sofia', 'Carnation')
ORDER BY t.town_id , address_id;

#3
SELECT 
    employee_id, first_name, last_name, department_id, salary
FROM
    employees
WHERE
    manager_id IS NULL;
    
#4
SELECT 
    COUNT(*)
FROM
    employees
WHERE
    salary > (SELECT 
            AVG(salary)
        FROM
            employees);
            
            
### Exercise ###

#1
SELECT 
    employee_id, job_title, e.address_id, address_text
FROM
    employees AS e
        JOIN
    addresses AS a ON a.address_id = e.address_id
ORDER BY e.address_id
LIMIT 5;

#2
SELECT 
    first_name, last_name, t.name AS town, address_text
FROM
    employees AS e
        JOIN
    addresses AS a ON a.address_id = e.address_id
        JOIN
    towns AS t ON t.town_id = a.town_id
ORDER BY first_name , last_name
LIMIT 5;

#3
SELECT 
    employee_id, first_name, last_name, name AS department_name
FROM
    employees AS e
        JOIN
    departments AS d ON d.department_id = e.department_id
WHERE name = 'Sales'
ORDER BY employee_id DESC
Limit 5;

#4
SELECT 
    employee_id, first_name, salary, name AS department_name
FROM
    employees AS e
        JOIN
    departments AS d ON d.department_id = e.department_id
WHERE
    salary > 15000
ORDER BY d.department_id DESC
LIMIT 5;

#5
SELECT 
    e.employee_id, e.first_name
FROM
    employees AS e
        LEFT JOIN
    employees_projects AS ep ON e.employee_id = ep.employee_id
WHERE
    ep.project_id IS NULL
ORDER BY e.employee_id DESC
LIMIT 3;

#6
SELECT 
    e.first_name,
    e.last_name,
    e.hire_date,
    d.name AS 'dept_name'
FROM
    employees AS e
        JOIN
    departments AS d ON e.department_id = d.department_id
WHERE
    DATE(e.hire_date) > '1999-01-01'
        AND d.name IN ('Sales' , 'Finance')
ORDER BY e.hire_date;

#7
SELECT 
    e.employee_id, e.first_name,p.name as 'project_name'
FROM
    employees AS e
        JOIN
    employees_projects AS ep ON e.employee_id = ep.employee_id
        JOIN
    projects AS p ON p.project_id = ep.project_id
WHERE
    p.end_date IS NULL
        AND DATE(p.start_date) > '2002-08-13'
ORDER BY e.first_name,p.name
LIMIT 5;

#8
SELECT 
    e.employee_id,
    e.first_name,
    IF(YEAR(p.start_date) >= 2005,
        NULL,
        p.name) AS 'project_name'
FROM
    employees AS e
        JOIN
    employees_projects AS ep ON e.employee_id = ep.employee_id
        JOIN
    projects AS p ON p.project_id = ep.project_id
WHERE
    e.employee_id = 24
ORDER BY project_name;

#9
SELECT 
    e.employee_id,
    e.first_name,
    e.manager_id,
    e1.first_name AS 'manager_name'
FROM
    employees AS e
        JOIN
    employees AS e1 ON e.manager_id = e1.employee_id
WHERE
    e.manager_id IN (3 , 7)
ORDER BY e.first_name;

#10
SELECT 
    e.employee_id,
    CONCAT_WS(' ', e.first_name, e.last_name) AS 'employee_name',
    CONCAT_WS(' ', e1.first_name, e1.last_name) AS 'manager_name',
    d.name AS 'department_name'
FROM
    employees AS e
        JOIN
    employees AS e1 ON e.manager_id = e1.employee_id
        JOIN
    departments AS d ON e.department_id = d.department_id
WHERE
    e.manager_id IS NOT NULL
ORDER BY e.employee_id
LIMIT 5;

#11
SELECT 
    AVG(salary) AS 'min_average_salary'
FROM
    employees
GROUP BY department_id
ORDER BY min_average_salary
LIMIT 1;

#12
SELECT 
    c.country_code, m.mountain_range, p.peak_name, p.elevation
FROM
    countries AS c
        JOIN
    mountains_countries AS mc ON c.country_code = mc.country_code
        JOIN
    mountains AS m ON m.id = mc.mountain_id
        JOIN
    peaks AS p ON p.mountain_id = m.id
WHERE
    c.country_code = 'BG'
        AND p.elevation > 2835
ORDER BY p.elevation DESC;

#13
SELECT 
    c.country_code, COUNT(mc.mountain_id) AS 'mountain_range'
FROM
    countries AS c
        JOIN
    mountains_countries AS mc ON c.country_code = mc.country_code
GROUP BY c.country_code
HAVING c.country_code IN ('BG' , 'US', 'RU')
ORDER BY mountain_range DESC;

#14
SELECT 
    c.country_name, r.river_name
FROM
    countries AS c
        LEFT JOIN
    countries_rivers AS cr ON cr.country_code = c.country_code
        LEFT JOIN
    rivers AS r ON r.id = cr.river_id
WHERE
    c.continent_code = 'AF'
ORDER BY c.country_name
LIMIT 5;

#15
SELECT DISTINCT
    c.continent_code,
    c.currency_code,
    (SELECT 
            COUNT(*)
        FROM
            countries AS c1
        WHERE
            c1.continent_code = c.continent_code
                AND c1.currency_code = c.currency_code) AS 'currency_usage'
FROM
    countries AS c
WHERE
    (SELECT 
            COUNT(*)
        FROM
            countries AS c2
        WHERE
            c2.continent_code = c.continent_code
                AND c2.currency_code = c.currency_code) = (SELECT 
            COUNT(*) AS count
        FROM
            countries AS c3
        WHERE
            c3.continent_code = c.continent_code
        GROUP BY c3.currency_code
        ORDER BY count DESC
        LIMIT 1)
        AND (SELECT 
            COUNT(*)
        FROM
            countries AS c2
        WHERE
            c2.continent_code = c.continent_code
                AND c2.currency_code = c.currency_code) > 1
ORDER BY c.continent_code , c.currency_code;

#16
SELECT 
    COUNT(*) as 'country_count'
FROM
    countries AS c
        LEFT JOIN
    mountains_countries AS mc ON c.country_code = mc.country_code
WHERE
    mc.mountain_id IS NULL;
    
#17
SELECT 
    c.country_name,
    MAX(p.elevation) AS 'highest_peak_elevation',
    MAX(r.length) AS 'longest_river_length'
FROM
    countries AS c
        LEFT JOIN
    mountains_countries AS mc ON c.country_code = mc.country_code
        LEFT JOIN
    mountains AS m ON m.id = mc.mountain_id
        LEFT JOIN
    peaks AS p ON m.id = p.mountain_id
        LEFT JOIN
    countries_rivers AS cr ON c.country_code = cr.country_code
        LEFT JOIN
    rivers AS r ON r.id = cr.river_id
GROUP BY c.country_code
ORDER BY highest_peak_elevation DESC , longest_river_length DESC , country_name
LIMIT 5;