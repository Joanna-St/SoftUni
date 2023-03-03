CREATE DATABASE `gamebar`;

USE `gamebar`;

CREATE TABLE `employees` (
	id INT AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `categories` (
	id INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `products` (
	id INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY(`id`)
);

INSERT INTO `employees` (`first_name`, `last_name`) VALUES('Georgi','Georgiev');
INSERT INTO `employees` (`first_name`, `last_name`) VALUES('Maria','Petrova');
INSERT INTO `employees` (`first_name`, `last_name`) VALUES('Ivan','Penchev');

ALTER TABLE `employees` 
ADD COLUMN `middle_name` VARCHAR(50);

ALTER TABLE `employees`
MODIFY COLUMN `middle_name` VARCHAR(100);

ALTER TABLE `products`
ADD CONSTRAINT fk_id 
FOREIGN KEY (`category_id`)
REFERENCES `categories`(`id`);

