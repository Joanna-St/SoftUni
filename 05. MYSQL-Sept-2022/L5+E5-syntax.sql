### Lecture ###

#1
CREATE TABLE `mountains` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50)
);

CREATE TABLE `peaks` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50),
    `mountain_id` INT,
    CONSTRAINT `fk_peaks_mountains` FOREIGN KEY (mountain_id)
        REFERENCES `mountains`(`id`)
);

#2
SELECT 
    `driver_id`, `vehicle_type`, concat_ws(' ',`first_name`,`last_name`) as 'driver_name'
FROM
    `vehicles`
        JOIN
    `campers` ON `campers`.`id` = `vehicles`.`driver_id`;
    
#3
SELECT 
    `starting_point`,
    `end_point`,
    `leader_id`,
    CONCAT_WS(' ', `first_name`, `last_name`) AS 'leader_name'
FROM
    `routes`
        JOIN
    `campers` ON `campers`.`id` = `routes`.`leader_id`;
    
#4
CREATE TABLE `mountains` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50)
);

CREATE TABLE `peaks` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50),
    `mountain_id` INT,
    CONSTRAINT `fk_peaks_mountains` FOREIGN KEY (mountain_id)
        REFERENCES `mountains` (`id`)
        ON DELETE CASCADE
);


#5
CREATE SCHEMA `project_management`;

USE `project_management`;

CREATE TABLE `clients` (
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    client_name VARCHAR(100)
);

CREATE TABLE `projects` (
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    client_id INT(11),
    project_lead_id INT(11),
    CONSTRAINT fk_projects_clients_id FOREIGN KEY (client_id)
        REFERENCES clients (id)
);

CREATE TABLE `employees` (
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    project_id INT(11),
    CONSTRAINT fk_employees_projects_id FOREIGN KEY (project_id)
        REFERENCES projects (id)
);

ALTER TABLE `projects`
	ADD CONSTRAINT fk_projects_leads_id FOREIGN KEY (project_lead_id)
		REFERENCES employees(id);

### Exercise ###

CREATE SCHEMA exercise_5;

USE exercise_5;

#1
CREATE TABLE passports (
    passport_id INT NOT NULL PRIMARY KEY,
    passport_number VARCHAR(10) UNIQUE
);
    
INSERT INTO passports (passport_id,passport_number)
VALUES	(101,'N34FG21B'),
		(102,'K65LO4R7'),
		(103,'ZE657QP2');

CREATE TABLE people (
    person_id INT NOT NULL PRIMARY KEY,
    first_name VARCHAR(50),
    salary DECIMAL(8 , 2 ),
    passport_id INT UNIQUE,
    CONSTRAINT `fk_people_passport_id` FOREIGN KEY (`passport_id`)
        REFERENCES `passports` (`passport_id`)
);

INSERT INTO people (person_id,first_name,salary,passport_id)
VALUES	(1,'Roberto',43300.00,102),
		(2,'Tom',56100.00,103),
		(3,'Yana',60200.00,101);

#2
CREATE TABLE manufacturers (
    manufacturer_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50),
    established_on DATE
);

INSERT INTO manufacturers (manufacturer_id,`name`,established_on)
VALUES 	(1,'BMW','1916-03-01'),
		(2,'Tesla','2003-01-01'),
		(3,'Lada', '1966-05-01');
        
CREATE TABLE models (
    model_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50),
    manufacturer_id INT,
    CONSTRAINT fk_models_manufacturers FOREIGN KEY (manufacturer_id)
        REFERENCES manufacturers (manufacturer_id)
);

INSERT INTO models (model_id,`name`,manufacturer_id)
VALUES 	(101,'X1',1),
		(102,'i6',1),
		(103,'Model S',2),
		(104,'Model X',2),
		(105,'Model 3',2),
		(106,'Nova',3);

#3
CREATE TABLE students (
    student_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50)
);

INSERT INTO students (student_id,`name`)
VALUES  (1,'Mila'),
		(2,'Toni'),
		(3,'Ron');
        
CREATE TABLE exams (
    exam_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50)
);

INSERT INTO exams (exam_id,`name`)
VALUES  (101,'Spring MVC'),
		(102,'Neo4j'),
		(103,'Oracle 11g');
        
CREATE TABLE students_exams (
    student_id INT,
    exam_id INT,
    PRIMARY KEY (student_id,exam_id),
    CONSTRAINT fk_student_id FOREIGN KEY (student_id)
        REFERENCES students (student_id),
    CONSTRAINT fk_exam_id FOREIGN KEY (exam_id)
        REFERENCES exams (exam_id)
);

INSERT INTO students_exams (student_id,exam_id)
VALUES 	(1,101),
		(1,102),
		(2,101),
		(3,103),
		(2,102),
		(2,103);
 
 #4
CREATE TABLE teachers (
    teacher_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50),
    manager_id INT
);

INSERT INTO teachers
VALUES 	(101,'John',null),
		(102,'Maya',106),
		(103,'Silvia',106),
		(104,'Ted',105),
		(105,'Mark',101),
		(106,'Greta',101);
        
ALTER TABLE teachers
ADD CONSTRAINT fk_managers
    FOREIGN KEY (manager_id) REFERENCES teachers(teacher_id);

#5
CREATE SCHEMA online_store;

USE online_store;

CREATE TABLE cities (
    city_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50)
);

CREATE TABLE item_types (
    item_type_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50)
);

CREATE TABLE customers (
    customer_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50),
    birthday DATE,
    city_id INT,
    CONSTRAINT fk_customers_cities FOREIGN KEY (city_id)
        REFERENCES cities (city_id)
);

CREATE TABLE items (
    item_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50),
    item_type_id INT,
    CONSTRAINT fk_items_types FOREIGN KEY (item_type_id)
        REFERENCES item_types (item_type_id)
);

CREATE TABLE orders (
    order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    CONSTRAINT fk_customers_orders FOREIGN KEY (customer_id)
        REFERENCES customers (customer_id)
);

CREATE TABLE order_items (
    order_id INT,
    item_id INT,
    PRIMARY KEY (order_id , item_id),
    CONSTRAINT fk_oders_ids FOREIGN KEY (order_id)
        REFERENCES orders (order_id),
    CONSTRAINT fk_items_ids FOREIGN KEY (item_id)
        REFERENCES items (item_id)
);

#6
CREATE SCHEMA university;

USE university;

CREATE TABLE subjects (
    subject_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    subject_name VARCHAR(50)
);

CREATE TABLE majors (
    major_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50)
);

CREATE TABLE students (
    student_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    student_number VARCHAR(12),
    student_name VARCHAR(50),
    major_id INT,
    CONSTRAINT fk_students_majors FOREIGN KEY (major_id)
        REFERENCES majors (major_id)
);

CREATE TABLE payments (
    payment_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    payment_date DATE,
    payment_amount DECIMAL(8,2),
    student_id INT,
    CONSTRAINT fk_payments_students FOREIGN KEY (student_id)
        REFERENCES students (student_id)
);

CREATE TABLE agenda (
    student_id INT,
    subject_id INT,
    PRIMARY KEY (student_id , subject_id),
    CONSTRAINT fk_students_ids FOREIGN KEY (student_id)
        REFERENCES students (student_id),
    CONSTRAINT fk_subjects_ids FOREIGN KEY (subject_id)
        REFERENCES subjects (subject_id)
);

#9
SELECT 
    mountain_range, peak_name, elevation AS peak_elevation
FROM
    mountains AS m
        JOIN
    peaks AS p ON p.mountain_id = m.id
WHERE
    mountain_range = 'Rila'
ORDER BY peak_elevation DESC;