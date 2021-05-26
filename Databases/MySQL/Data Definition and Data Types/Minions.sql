CREATE DATABASE `minions`;
USE `minions`;


-- 1.Create Tables
CREATE TABLE `minions`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `age` INT
);

CREATE TABLE `towns`(
	`town_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL
);


-- 2.Alter Minions Table
ALTER TABLE `minions`
ADD COLUMN `town_id` INT NOT NULL;

ALTER TABLE `minions`
ADD CONSTRAINT fk_minions_towns
FOREIGN KEY (`town_id`)
REFERENCES `towns`(`id`);


-- 3.Insert Records in Both Tables
INSERT INTO `towns`
VALUES (1,'Sofia'),
		(2,'Plovdiv'),
		(3,'Varna');
        
INSERT INTO `minions`
VALUES (1,'Kevin', 22, 1),
		(2,'Bob', 15, 3),
        (3,'Steward', NULL, 2);
        
        
-- 4.Truncate Table Minions
TRUNCATE TABLE `minions`;

-- 5.Drop All Tables
DROP SCHEMA `minions`;