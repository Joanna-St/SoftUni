### Lecture ###
#1
DELIMITER $$
CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(50))
RETURNS INT DETERMINISTIC
BEGIN
	RETURN (SELECT 
		count(*)
	FROM
		employees AS e
			JOIN
		addresses AS a ON e.address_id = a.address_id
			JOIN
		towns AS t ON t.town_id = a.town_id
	WHERE t.name = town_name);
END$$

SELECT ufn_count_employees_by_town('Sofia')$$

#2
CREATE PROCEDURE usp_raise_salaries(department_name VARCHAR(50))
BEGIN
	UPDATE employees AS e 
	SET 
		e.salary = e.salary * 1.05
	WHERE
		e.department_id = (SELECT 
				d.department_id
			FROM
				departments as d
			WHERE
				d.name = department_name);
END$$

CALL usp_raise_salaries('Finance')$$

SELECT 
    first_name, salary
FROM
    employees AS e
        JOIN
    departments AS d ON e.department_id = d.department_id
WHERE
    d.name = 'Finance'
ORDER BY first_name , salary$$

#3
SET autocommit = 0$$

CREATE PROCEDURE usp_raise_salary_by_id(id int)
BEGIN
	START TRANSACTION;
		UPDATE employees AS e 
		SET 
			e.salary = e.salary * 1.05
		WHERE
			e.employee_id = id;
END$$

CALL usp_raise_salary_by_id(17)$$

SELECT salary from employees where employee_id = 40000$$

CALL usp_raise_salary_by_id(40000)$$

DELIMITER ;

#4
DROP TABLE deleted_employees;
DROP TRIGGER del_employees;

CREATE TABLE deleted_employees (
    employee_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    middle_name VARCHAR(50),
    job_title VARCHAR(50),
    department_id INT,
    salary DECIMAL(19 , 4 )
);

CREATE TRIGGER del_employees BEFORE DELETE ON employees
FOR EACH ROW 
INSERT INTO deleted_employees(first_name,last_name,middle_name,job_title,department_id,salary)
VALUES(OLD.first_name,OLD.last_name,OLD.middle_name,OLD.job_title,OLD.department_id,OLD.salary);

DELETE FROM employees where employee_id = 2;

### EXERCISE ###

#1
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
	SELECT 
		first_name, last_name
	FROM
		employees
	WHERE
		salary > 35000
	ORDER BY first_name , last_name , employee_id;
END$$

CALL usp_get_employees_salary_above_35000()$$

#2
CREATE PROCEDURE usp_get_employees_salary_above(salary_value DECIMAL(19,4))
BEGIN
	SELECT 
		first_name, last_name
	FROM
		employees
	WHERE
		salary >= salary_value
	ORDER BY first_name , last_name , employee_id;
END$$

CALL usp_get_employees_salary_above(48100)$$

#3
CREATE PROCEDURE usp_get_towns_starting_with(input VARCHAR(50))
BEGIN
	SELECT 
		name AS town_name
	FROM
		towns
	WHERE
		name LIKE (concat(input , '%'))
	ORDER BY name;
END$$

CALL usp_get_towns_starting_with('b')$$

#4
CREATE PROCEDURE usp_get_employees_from_town(town_name VARCHAR(50))
BEGIN
	SELECT 
		e.first_name, e.last_name
	FROM
		employees AS e
			JOIN
		addresses AS a ON e.address_id = a.address_id
			JOIN
		towns AS t ON t.town_id = a.town_id
	WHERE
		t.name = town_name
	ORDER BY e.first_name , e.last_name , e.employee_id;
END$$

CALL usp_get_employees_from_town('San Francisco')$$

#5
CREATE FUNCTION ufn_get_salary_level (salary DECIMAL(19,4))
RETURNS VARCHAR(10)
DETERMINISTIC
BEGIN
	DECLARE s_level VARCHAR(10);
	
	IF salary < 30000 THEN SET s_level = 'Low';
	ELSEIF salary BETWEEN 30000 AND 50000 THEN SET s_level = 'Average';
	ELSE SET s_level = 'High';
	END if;
    
	RETURN s_level;
END$$

SELECT ufn_get_salary_level(50000.00)$$

#6
CREATE PROCEDURE usp_get_employees_by_salary_level (s_level VARCHAR(10))
BEGIN
	IF lcase(s_level) = 'low' THEN (SELECT 
										first_name, last_name
									FROM
										employees
									WHERE
										salary < 30000
									ORDER BY first_name DESC , last_name DESC);
	ELSEIF lcase(s_level) = 'average' THEN (SELECT 
										first_name, last_name
									FROM
										employees
									WHERE
										salary BETWEEN 30000 AND 50000
									ORDER BY first_name DESC , last_name DESC);
	ELSEIF lcase(s_level) = 'high' THEN (SELECT 
										first_name, last_name
									FROM
										employees
									WHERE
										salary > 50000
									ORDER BY first_name DESC , last_name DESC);
    END IF;
END$$

CALL usp_get_employees_by_salary_level('high')$$

#7
CREATE FUNCTION ufn_is_word_comprised(set_of_letters VARCHAR(50), word VARCHAR(50)) 
RETURNS BIT
DETERMINISTIC
BEGIN
	RETURN lcase(word) REGEXP (concat('^[',set_of_letters,']+$'));
END$$

Select ufn_is_word_comprised('oistmiahf','Sofia')$$

#8

CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
	SELECT 
    CONCAT_WS(' ', first_name, last_name) AS full_name
	FROM
		account_holders
	ORDER BY full_name , id;
END$$

CALL usp_get_holders_full_name()$$

#10
CREATE FUNCTION ufn_calculate_future_value(init_sum DECIMAL(19,4), yearly_interest_rate DOUBLE, num_years INT)
RETURNS DECIMAL(19,4)
DETERMINISTIC
BEGIN
	RETURN init_sum * power((1+yearly_interest_rate),num_years);
END$$

SELECT ufn_calculate_future_value(1000,0.5,5)$$

#11
CREATE PROCEDURE usp_calculate_future_value_for_account (input_id INT, intrest_rate DOUBLE)
BEGIN
	SELECT 
		a.id,
		ah.first_name,
		ah.last_name,
		a.balance AS current_balance,
		FORMAT((a.balance * power((1 + intrest_rate),5)),4) as balance_in_5_years
	FROM
		account_holders AS ah
			JOIN
		accounts AS a ON ah.id = a.account_holder_id
	WHERE a.id = input_id;
END$$

CALL usp_calculate_future_value_for_account(1,0.1)$$

#12
CREATE PROCEDURE usp_deposit_money (input_account_id INT, money_amount DECIMAL(19,4))
BEGIN
	START TRANSACTION;
    IF money_amount <= 0 THEN 
		ROLLBACK;
	ELSE 
		UPDATE accounts AS a 
		SET 
			a.balance = a.balance + money_amount
		WHERE
			a.id = input_account_id;
    END IF;
END$$

#13
CREATE PROCEDURE usp_withdraw_money (input_account_id INT, money_amount DECIMAL(19,4))
BEGIN
	START TRANSACTION;
    IF money_amount > (SELECT 
							balance
						FROM
							accounts
						WHERE
							id = input_account_id) THEN 
		ROLLBACK;
	ELSEIF money_amount >= 0 THEN
		UPDATE accounts AS a 
		SET 
			a.balance = a.balance - money_amount
		WHERE
			a.id = input_account_id;
    END IF;
END$$

CALL usp_withdraw_money(1,10)$$
SELECT * from accounts where id = 1$$

#14
CREATE PROCEDURE usp_transfer_money (from_account_id INT, to_account_id INT, amount DECIMAL(19,4))
BEGIN
	START TRANSACTION;
    IF amount > (SELECT balance FROM accounts WHERE id = from_account_id) THEN ROLLBACK;
    ELSEIF amount < 0 THEN ROLLBACK;
    ELSEIF from_account_id = to_account_id THEN ROLLBACK;
    ELSEIF from_account_id NOT IN  (SELECT id from accounts) then ROLLBACK;
    ELSEIF to_account_id NOT IN  (SELECT id from accounts) then ROLLBACK;
    ELSE
		UPDATE accounts AS a 
		SET 
			a.balance = a.balance - amount
		WHERE
			a.id = from_account_id;
            
		UPDATE accounts AS a 
			SET 
				a.balance = a.balance + amount
			WHERE
				a.id = to_account_id;
    END IF;
END$$

CALL usp_transfer_money(1,1,10)$$
SELECT * from accounts where id IN (1,2)$$






