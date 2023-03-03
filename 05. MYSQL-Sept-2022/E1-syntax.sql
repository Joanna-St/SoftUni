CREATE DATABASE `minions`;

USE `minions`;

CREATE TABLE `minions` (
	`id` INT AUTO_INCREMENT,
    `name` VARCHAR(50),
    `age` INT,
    PRIMARY KEY (`id`)
);

CREATE TABLE `towns` (
	`town_id` INT AUTO_INCREMENT,
    `name` VARCHAR(50),
    PRIMARY KEY (`town_id`)
);

ALTER TABLE `minions`
ADD COLUMN `town_id` INT;

ALTER TABLE `minions`
ADD CONSTRAINT `fk_town_id`
FOREIGN KEY(`town_id`) REFERENCES `towns` (`id`);

INSERT INTO `towns` (`id`,`name`) VALUES 
(1,'Sofia'),
(2,'Plovdiv'),
(3,'Varna');

INSERT INTO `minions` (`id`,`name`,`age`,`town_id`) VALUES
(1,'Kevin',22,1),
(2,'Bob',15,3);
INSERT INTO `minions` (`id`,`name`,`town_id`) VALUES (3,'Steward',2);

TRUNCATE `minions`;

DROP TABLE `minions`, `towns`;

CREATE TABLE `people` (
	`id` INT AUTO_INCREMENT,
    `name` VARCHAR(200) NOT NULL,
    `picture` INT(2),
    `height` DOUBLE(3,2),
    `weight` DOUBLE(5,2),
    `gender` ENUM('m','f') NOT NULL,
    `birthdate` DATE NOT NULL,
    `biography` TEXT,
    PRIMARY KEY (`id`)
);

INSERT INTO `people` (`name`,`gender`,`birthdate`) VALUES
('Leo Valdez','m','1994-07-07'),
('Hazel Levesque','f','1928-12-17'),
('Frank Zhang','m','1994-06-05');

INSERT INTO `people` (`name`,`picture`,`height`,`weight`,`gender`,`birthdate`,`biography`) VALUES
('Piper McLean',1,1.66,54.30,'f','1994-06-03','Piper McLean is the demigod daughter of Aphrodite and the actor Tristan McLean, the counselor of Cabin 10 at Camp Half-Blood, the former girlfriend of Jason Grace and one of the main characters of the 2010-2014 Greco-Roman book series The Heroes of Olympus.'),
('Percy Jackson',1,1.77,65,'m','1993-08-18','Perseus "Percy" Jackson is a Greek demigod, the son of Poseidon and Sally Jackson. He is the main protagonist and narrator of the Percy Jackson and the Olympians series, and one of the main characters of The Heroes of Olympus series. He is the former head counselor at Poseidon Cabin and a former Praetor of the Twelfth Legion at Camp Jupiter.');

CREATE TABLE `users` (
	`id` INT AUTO_INCREMENT,
    `username` VARCHAR(30) NOT NULL,
    `password` VARCHAR(26) NOT NULL,
    `profile_picture` VARBINARY(900),
    `last_login_time` TIME,
    `is_deleted` BOOLEAN, 
    PRIMARY KEY (`id`)
);	

INSERT INTO `users` (`username`,`password`,`profile_picture`,`last_login_time`,`is_deleted`) VALUES 
('Key','Shinee',763,'11:33:56',0),
('Hoshi','Seventeen',111,'11:11:11',0),
('Taeil','NCT',553,'03:26:43',0),
('Shownu','MonstaX',618,'16:09:38',0),
('Tao','EXO',NULL,NULL,1);

ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users_composite
PRIMARY KEY (`id`,`username`);

ALTER TABLE `users`
MODIFY COLUMN `last_login_time` DATETIME DEFAULT NOW();

ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users
PRIMARY KEY (`id`);

ALTER TABLE `users`
ADD CONSTRAINT uq_username
UNIQUE (`username`);



-- NEW DATABASE -----------------------------
CREATE DATABASE `Movies`;

USE `Movies`;

CREATE TABLE `directors` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `director_name` VARCHAR(100) NOT NULL,
    `notes` TEXT
);

CREATE TABLE `genres` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `genre_name` VARCHAR(100) NOT NULL,
    `notes` TEXT
);

CREATE TABLE `categories` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `category_name` VARCHAR(100) NOT NULL,
    `notes` TEXT
);

CREATE TABLE `movies` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `title` TEXT NOT NULL,
    `director_id` INT,
    `copyright_year` INT,
    `length` INT,
    `genre_id` INT,
    `category_id` INT,
    `rating` VARCHAR(50),
    `notes` TEXT
);	

INSERT INTO `directors` (`director_name`, `notes`) VALUES 
('John Doe', 'Popular name'),
('Mark Lee','Total kid, very successful'),
('Finn Deeke',NULL),
('Rosalyn Greene',NULL),
('Marry Owens',NULL);

INSERT INTO `genres` (`genre_name`, `notes`) VALUES 
('Fantasy', 'Fantasy is a genre of speculative fiction involving magical elements, typically set in a fictional universe and sometimes inspired by mythology and folklore. '),
('Horror','Horror is a genre of literature, film, and television that is meant to scare, startle, shock, and even repulse audiences.'),
('Sci-Fi','Science fiction, often called “sci-fi,” is a genre of fiction literature whose content is imaginative, but based in science.'),
('Comedy',NULL),
('Adventure',NULL);

INSERT INTO `categories` (`category_name`, `notes`) VALUES 
('Fantasy', 'Fantasy is a genre of speculative fiction involving magical elements, typically set in a fictional universe and sometimes inspired by mythology and folklore. '),
('Horror','Horror is a genre of literature, film, and television that is meant to scare, startle, shock, and even repulse audiences.'),
('Sci-Fi','Science fiction, often called “sci-fi,” is a genre of fiction literature whose content is imaginative, but based in science.'),
('Comedy',NULL),
('Adventure',NULL);

INSERT INTO `movies` (`title`,`director_id`,`copyright_year`,`length`,`genre_id`,`category_id`,`rating`,`notes`) VALUES
('Thor',1,2011,114,1,3,'PG-13','Thor is exiled by his father, Odin, the King of Asgard, to the Earth to live among mortals. When he lands on Earth, his trusted weapon Mjolnir is discovered and captured by S.H.I.E.L.D.'),
('Thor: Ragnarok',2,2017,130,1,4,'PG-13','Deprived of his mighty hammer Mjolnir, Thor must escape the other side of the universe to save his home, Asgard, from Hela, the goddess of death.'),
('Legion',3,2010,100,1,2,'R',NULL),
('Fantastic Beasts: The Secrets of Dumbledore',4,2022,143,1,5,'PG-13','Dumbledore assembles a team of wizards, witches and a Muggle baker to oppose the rise of Gellert Grindelwald. They hatch a plan to confuse Grindelwald and stop him from attaining political power.'),
('Jumanji: Welcome to the Jungle',1,2017,119,4,5,'PG-13',NULL);



-- NEW DATABASE -----------------------------
CREATE DATABASE `car_rental`;

USE `car_rental`;

CREATE TABLE `categories` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`category` VARCHAR(100) NOT NULL,
	`daily_rate` DOUBLE,
	`weekly_rate` DOUBLE,
	`monthly_rate` DOUBLE,
	`weekend_rate` DOUBLE
);

INSERT INTO `categories` (`category`, `daily_rate`, `weekly_rate`, `monthly_rate`, `weekend_rate`) VALUES
('Sports',33.5,200.90,5000.00,49.99),
('Van',NULL,500,NULL,NULL),
('Electric',50,300,7500,NULL);

CREATE TABLE `cars` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`plate_number` VARCHAR(10) NOT NULL,
	`make` VARCHAR(100),
	`model` VARCHAR(100),
	`car_year` INT,
	`category_id` INT,
	`doors` INT,
	`picture` BLOB,
	`car_condition` VARCHAR(100),
	`available` BOOLEAN NOT NULL
);

INSERT INTO `cars` (`plate_number`,`make`,`model`,`car_year`,`category_id`,`doors`,`picture`,`car_condition`,`available`) VALUES
('S6789VN','Toyota','Corolla',2009,1,4,NULL,NULL,1),
('S6841BR','Merzedes','Something',2015,2,4,NULL,NULL,1),
('S1478TE','Tesla','Futura',2022,3,2,NULL,NULL,0);

CREATE TABLE `employees` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`first_name` VARCHAR(100) NOT NULL,
	`last_name` VARCHAR(100) NOT NULL,
	`title` VARCHAR(100),
	`notes` TEXT
);

INSERT INTO `employees` (`first_name`,`last_name`,`title`) VALUES
('Ivan','Petrov','Senior Sales Representative'),
('Georgi','Angelov','Front Desk'),
('Ivelina','Nikolova','Senior Sales Representative');

CREATE TABLE `customers` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`driver_licence_number` VARCHAR(100) NOT NULL,
	`full_name` VARCHAR(500) NOT NULL,
	`address` VARCHAR(500) NOT NULL,
	`city` VARCHAR(100),
	`zip_code` VARCHAR(100),
	`notes` TEXT
);

INSERT INTO `customers` (`driver_licence_number`,`full_name`,`address`,`city`) VALUES
('4557HGRUH54YGY','Martin Petrov Petrov','Mladost 3, blok 345', 'Sofia'),
('9867MNSVEJI797','Jordan Yasenov Angelov','Drujhba 1, blok 78', 'Sofia'),
('735GHRB5J6O909','Irena Mihaylova Nikolova','Sofia, Nadezhda 2, blok 234', NULL);


CREATE TABLE `rental_orders` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`employee_id` INT,
	`customer_id` INT,
	`car_id` INT,
	`car_condition` VARCHAR(100),
	`tank_level` INT,
	`kilometrage_start` INT,
	`kilometrage_end` INT,
	`total_kilometrage` INT,
	`start_date` DATE,
	`end_date` DATE,
	`total_days` INT,
	`rate_applied` DOUBLE,
	`tax_rate` DOUBLE,
	`order_status` VARCHAR(100),
	`notes` TEXT
);

INSERT INTO `rental_orders` (`employee_id`, `customer_id`,`car_id`)
VALUES 
(1, 2, 2),
(2, 3, 1),
(3, 1, 3);


-- NEW DATABASE -----------------------------
CREATE DATABASE `soft_uni`;

USE `soft_uni`;

CREATE TABLE `towns` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(100) NOT NULL
);

CREATE TABLE `addresses` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`address_text` VARCHAR(500) NOT NULL,
	`town_id` INT,
	FOREIGN KEY (`town_id`) REFERENCES `towns`(`id`)
);

CREATE TABLE `departments` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(100) NOT NULL
);

CREATE TABLE `employees` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`first_name` VARCHAR(100) NOT NULL,
	`middle_name` VARCHAR(100),
	`last_name` VARCHAR(100) NOT NULL,
	`job_title` VARCHAR(100),
	`department_id` INT,
	`hire_date` DATE,
	`salary` DOUBLE,
	`address_id` INT,
	FOREIGN KEY (`department_id`) REFERENCES `departments`(`id`),
	FOREIGN KEY (`address_id`) REFERENCES `addresses`(`id`)
);

INSERT INTO `towns` (`name`)
VALUES 
('Sofia'),
('Plovdiv'),
('Varna'),
('Burgas');

INSERT INTO `departments` (`name`)
VALUES 
('Engineering'),
('Sales'),
('Marketing'),
('Software Development'),
('Quality Assurance');

INSERT INTO `employees` (`first_name`, `middle_name`, `last_name`, `job_title`, `department_id`, `hire_date`, `salary`)
VALUES 
('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013-02-01', 3500.00),
('Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004-03-02', 4000.00),
('Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016-08-28', 525.25),
('Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007-12-09', 3000.00),
('Peter', 'Pan', 'Pan', 'Intern', 3, '2016-08-28', 599.88);

SELECT `name` FROM `towns` ORDER BY `name`;

SELECT `name` FROM `departments` ORDER BY `name`;

SELECT `first_name`, `last_name`, `job_title`, `salary` FROM `employees` ORDER BY `salary` DESC;

UPDATE `employees` SET `salary` = `salary` * 1.1 WHERE `id` > 0;

SELECT `salary` from `employees`
