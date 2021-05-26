CREATE DATABASE `soft_uni`;
USE `soft_uni`;

CREATE TABLE `towns`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL
);

CREATE TABLE `addresses`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `address_text` VARCHAR(100) NOT NULL,
    `town_id` INT NOT NULL,
	CONSTRAINT fk_addresses_towns
    FOREIGN KEY `addresses`(`town_id`)
    REFERENCES `towns`(`id`)
);

CREATE TABLE `departments`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NUll
);


CREATE TABLE `employees`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NUll,
    `middle_name` VARCHAR(50) NOT NUll,
    `last_name` VARCHAR(50) NOT NUll,
    `job_title` VARCHAR(50) NOT NUll,
    `department_id` INT,
    `hire_date` DATE,
    `salary` DECIMAL(10,2) NOT NULL,
    `address_id` INT
);

ALTER TABLE `employees`
ADD CONSTRAINT fk_employees_departments
FOREIGN KEY (`department_id`)
REFERENCES `departments`(`id`);

ALTER TABLE `employees`
ADD CONSTRAINT fk_employees_addresses
FOREIGN KEY (`address_id`)
REFERENCES `addresses`(`id`);


-- 12.Basic Insert
INSERT INTO `towns`(`name`)
VALUES 
('Sofia'),
('Plovdiv'),
('Varna'),
('Burgas');


INSERT INTO `departments`(`name`)
VALUES 
('Engineering'),
('Sales'),
('Marketing'),
('Software Development'),
('Quality Assurance');

INSERT INTO `employees`(`id`, `first_name`, `middle_name`, `last_name`, `job_title`, `department_id`, `hire_date`, `salary`)
VALUES 
(1, 'Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013-02-01', 3500.00),
(2, 'Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004-03-02', 4000.00),
(3, 'Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016-08-28', 525.25),
(4, 'Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007-12-09', 3000.00),
(5, 'Peter', 'Pan', 'Pan', 'Intern', 3, '2016-08-28', 599.88);