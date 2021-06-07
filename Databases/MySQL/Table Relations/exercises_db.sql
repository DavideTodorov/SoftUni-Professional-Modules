CREATE SCHEMA `exercises`;

-- 01.One-To-One Relationship
CREATE TABLE `passports`(
	`passport_id` INT PRIMARY KEY UNIQUE,
    `passport_number` VARCHAR(50) UNIQUE
);

INSERT INTO `passports`
VALUES
(101, 'N34FG21B'),
(102, 'K65LO4R7'),
(103, 'ZE657QP2');

CREATE TABLE `people`(
	`person_id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(50),
    `salary` DECIMAL(7, 2),
	`passport_id` INT UNIQUE,
    CONSTRAINT `fk_people_passports`
    FOREIGN KEY (`passport_id`)
    REFERENCES `passports`(`passport_id`)
);

INSERT INTO `people`
VALUES
(1, 'Roberto', 43300, 102),
(2, 'Tom', 56100, 103),
(3, 'Yana', 60200, 101);



-- 02.One-To-Many Relationship
CREATE TABLE `manufacturers` (
    `manufacturer_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50),
    `established_on` DATE
);

INSERT INTO `manufacturers`
VALUES
(1, 'BMW', '1916-03-01'),
(2, 'Tesla', '2003-01-01'),
(3, 'Lada', '1966-05-01');

CREATE TABLE `models` (
    `model_id` INT PRIMARY KEY,
    `name` VARCHAR(50),
    `manufacturer_id` INT,
    CONSTRAINT `fk_models_manufacturers` FOREIGN KEY (`manufacturer_id`)
        REFERENCES `manufacturers` (`manufacturer_id`)
);

INSERT INTO `models`
VALUES 
(101, 'X1', 1),
(102, 'i6', 1),
(103, 'Model S', 2),
(104, 'Model X', 2),
(105, 'Model 3', 2),
(106, 'Nova', 3);


-- 03.Many-To-Many Relationship
CREATE TABLE `students` (
    `student_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50)
);

INSERT INTO `students` (`name`)
VALUES
('Mila'), ('Toni'), ('Ron');

CREATE TABLE `exams`(
	`exam_id` INT PRIMARY KEY,
    `name` VARCHAR(50)
);

INSERT INTO `exams`
VALUES 
(101, 'Spring MVC'),
(102, 'Neo4j'),
(103, 'Oracle 11g');

CREATE TABLE `students_exams` (
    `student_id` INT,
    `exam_id` INT,
    PRIMARY KEY (`student_id` , `exam_id`),
    CONSTRAINT `fk_students_st_exams` FOREIGN KEY (`student_id`)
        REFERENCES `students` (`student_id`),
    CONSTRAINT `fk_exams_st_exams` FOREIGN KEY (`exam_id`)
        REFERENCES `exams` (`exam_id`)
);

INSERT INTO `students_exams`
VALUES
(1, 101),
(1, 102),
(2, 101),
(3, 103),
(2, 102),
(2, 103);



-- 04.Self-Referencing
CREATE TABLE `teachers`(
	`teacher_id` INT PRIMARY KEY,
    `name` VARCHAR(50),
    `manager_id` INT
);

INSERT INTO `teachers`
VALUES
(101, 'John', NULL),
(102, 'Maya', 106),
(103, 'Silvia', 106),
(104, 'Ted', 105),
(105, 'Mark', 101),
(106, 'Greta', 101);

ALTER TABLE `teachers`
ADD CONSTRAINT `sr_fk_teacher_manager`
FOREIGN KEY (`manager_id`)
REFERENCES `teachers`(`teacher_id`);



-- 05.Online Store Database
CREATE SCHEMA `online_store`;

CREATE TABLE `cities`(
	`city_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50)
);

CREATE TABLE `customers`(
	`customer_id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(50),
    `birthday` DATE,
    `city_id` INT,
    CONSTRAINT `fk_customers_city`
    FOREIGN KEY (`city_id`)
    REFERENCES `cities`(`city_id`)
);

CREATE TABLE `orders`(
	`order_id` INT PRIMARY KEY AUTO_INCREMENT,
    `customer_id` INT,
    CONSTRAINT `fk_orders_customers`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customers`(`customer_id`)
);

CREATE TABLE `item_types`(
	`item_type_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50)
);

CREATE TABLE `items`(
	`item_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50),
    `item_type_id` INT,
    CONSTRAINT `fk_items_itemtypes`
    FOREIGN KEY (`item_type_id`)
    REFERENCES `item_types`(`item_type_id`)
);

CREATE TABLE `order_items`(
	`order_id` INT NOT NULL,
    `item_id` INT NOT NULL,
    CONSTRAINT `pk_order_items` PRIMARY KEY (`order_id`, `item_id`),
    
    CONSTRAINT `fk_orderItems_orders`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders`(`order_id`), 
    
    CONSTRAINT `fk_orderItems_items`
    FOREIGN KEY (`item_id`)
    REFERENCES `items`(`item_id`)
);



-- 06.University Database
CREATE SCHEMA `university`;

CREATE TABLE `majors`(
	`major_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50)
);

CREATE TABLE `students`(
	`student_id` INT PRIMARY KEY AUTO_INCREMENT,
    `student_number` VARCHAR(12),
    `student_name` VARCHAR(50),
    `major_id` INT,
    CONSTRAINT `fk_students_majors`
    FOREIGN KEY (`major_id`)
    REFERENCES `majors`(`major_id`)
);

CREATE TABLE `payments`(
	`payment_id` INT PRIMARY KEY AUTO_INCREMENT,
    `payment_date` DATE,
    `payment_amount` DECIMAL(8, 2),
    `student_id` INT,
    CONSTRAINT `fk_payments_students`
    FOREIGN KEY (`student_id`)
    REFERENCES `students`(`student_id`)
); 

CREATE TABLE `subjects`(
	`subject_id` INT PRIMARY KEY AUTO_INCREMENT,
    `subject_name` VARCHAR(50)
);

CREATE TABLE `agenda`(
	`student_id` INT NOT NULL,
	`subject_id` INT NOT NULL,
    CONSTRAINT `pk_agenda` PRIMARY KEY (`student_id`, `subject_id`),
    
    CONSTRAINT `fk_agenda_students`
    FOREIGN KEY (`student_id`)
    REFERENCES `students`(`student_id`),
    
    CONSTRAINT `fk_agenda_subjects`
    FOREIGN KEY (`subject_id`)
    REFERENCES `subjects`(`subject_id`)
);