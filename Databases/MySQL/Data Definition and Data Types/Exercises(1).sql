CREATE SCHEMA `gamebar`;
USE `gamebar`;

/*Task One*/
CREATE TABLE `employees` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL
);

CREATE TABLE `categories` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL
);

CREATE TABLE `products` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `category_id` INT NOT NULL
);

/*Task Two*/
INSERT INTO `employees`(`first_name`, `last_name`)
VALUES ('Ivan', 'Ivanov'),
		('Pesho', 'Peshov'),
        ('Gosho', 'Goshov');
        
SELECT * FROM `employees`;

/*Task Three*/
ALTER TABLE `employees`
ADD COLUMN `middle_name` VARCHAR(50);

/*Task Four*/
ALTER TABLE `products`
ADD CONSTRAINT fk_products_categories
FOREIGN KEY (`category_id`)
REFERENCES `categories`(`id`);

/*Task Five */
ALTER TABLE `employees`
CHANGE COLUMN `middle_name` `middle_name` VARCHAR(100);
