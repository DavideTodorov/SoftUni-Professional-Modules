/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

USE soft_uni;

-- Dumping structure for table bank.accounts
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE IF NOT EXISTS `accounts` (
  `id` int(11) NOT NULL,
  `account_holder_id` int(11) NOT NULL,
  `balance` decimal(19,4) DEFAULT '0.0000',
  PRIMARY KEY (`id`),
  KEY `fk_accounts_account_holders` (`account_holder_id`),
  CONSTRAINT `fk_accounts_account_holders` FOREIGN KEY (`account_holder_id`) REFERENCES `account_holders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bank.accounts: ~18 rows (approximately)
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` (`id`, `account_holder_id`, `balance`) VALUES
	(1, 1, 123.1200),
	(2, 3, 4354.2300),
	(3, 12, 6546543.2300),
	(4, 9, 15345.6400),
	(5, 11, 36521.2000),
	(6, 8, 5436.3400),
	(7, 10, 565649.2000),
	(8, 11, 999453.5000),
	(9, 1, 5349758.2300),
	(10, 2, 543.3000),
	(11, 3, 10.2000),
	(12, 7, 245656.2300),
	(13, 5, 5435.3200),
	(14, 4, 1.2300),
	(15, 6, 0.1900),
	(16, 2, 5345.3400),
	(17, 11, 76653.2000),
	(18, 1, 235469.8900);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;


-- Dumping structure for table bank.account_holders
DROP TABLE IF EXISTS `account_holders`;
CREATE TABLE IF NOT EXISTS `account_holders` (
  `id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `ssn` char(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bank.account_holders: ~12 rows (approximately)
/*!40000 ALTER TABLE `account_holders` DISABLE KEYS */;
INSERT INTO `account_holders` (`id`, `first_name`, `last_name`, `ssn`) VALUES
	(1, 'Susan', 'Cane', '1234567890'),
	(2, 'Kim', 'Novac', '1234567890'),
	(3, 'Jimmy', 'Henderson', '1234567890'),
	(4, 'Steve', 'Stevenson', '1234567890'),
	(5, 'Bjorn', 'Sweden', '1234567890'),
	(6, 'Kiril', 'Petrov', '1234567890'),
	(7, 'Petar', 'Kirilov', '1234567890'),
	(8, 'Michka', 'Tsekova', '1234567890'),
	(9, 'Zlatina', 'Pateva', '1234567890'),
	(10, 'Monika', 'Miteva', '1234567890'),
	(11, 'Zlatko', 'Zlatyov', '1234567890'),
	(12, 'Petko', 'Petkov Junior', '1234567890');
/*!40000 ALTER TABLE `account_holders` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;


-- 08. Find Full Name
DELIMITER &&

CREATE PROCEDURE usp_get_holders_full_name()
BEGIN

	SELECT CONCAT(`first_name`, ' ', `last_name`) AS `full_name`
    FROM `account_holders`
    ORDER BY `full_name`, `id`; 

END&&

DELIMITER ;

CALL usp_get_holders_full_name();


-- *9. People with Balance Higher Than
DELIMITER &&

CREATE PROCEDURE usp_get_holders_with_balance_higher_than(min_sum INT)
BEGIN

	SELECT ah.`first_name`, ah.`last_name`
    FROM `account_holders` AS ah
    JOIN `accounts` AS a
	ON a.`account_holder_id` = ah.`id`
    GROUP BY a.`account_holder_id`
    HAVING SUM(a.`balance`) > min_sum
    ORDER By ah.`id`;
    
END&&

DELIMITER ;

CALL usp_get_holders_with_balance_higher_than(7000);


-- 10. Future Value Function
DELIMITER &&

CREATE FUNCTION ufn_calculate_future_value(initial_sum DECIMAL(40,4), 
yearly_interest_rate DOUBLE(40,4), 
number_of_years INT)
RETURNS DECIMAL(40,4)
DETERMINISTIC
BEGIN

	DECLARE result DECIMAL(40,4);
    
    SET result := initial_sum * POW((1 + yearly_interest_rate), number_of_years);
    
    RETURN result;

END&&

DELIMITER ;

SELECT ufn_calculate_future_value(1000, 0.5, 5);


-- 11. Calculating Interest
DELIMITER &&

CREATE PROCEDURE usp_calculate_future_value_for_account(account_id INT, yearly_interest_rate DECIMAL(40,4))
BEGIN

	SELECT ah.`id`, ah.`first_name`, ah.`last_name`,
    a.`balance`, 
    ufn_calculate_future_value(a.`balance`,yearly_interest_rate, 5) AS `balance_in_5_years`
    FROM `account_holders` AS ah
    JOIN `accounts` AS a
    ON ah.`id` = a.`account_holder_id`
    WHERE a.`id` = account_id;

END&&

DELIMITER ;

CALL usp_calculate_future_value_for_account(1, 0.1);


-- 12. Deposit Money
DELIMITER &&

CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL(20,4))
BEGIN

	IF money_amount > 0
    THEN 
    UPDATE `accounts`
    SET `balance` = `balance` + money_amount
    WHERE `id` = account_id;
    END IF;

END&&

DELIMITER ;

CALL usp_deposit_money(1, 10);
SELECT * FROM `accounts` WHERE `id` = 1;


-- 13. Withdraw Money
DELIMITER &&

CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL(20,4))
BEGIN

	IF money_amount > 0
    THEN 
    UPDATE `accounts`
    SET `balance` = `balance` - money_amount
    WHERE `id` = account_id AND `balance` >= money_amount;
    END IF;

END&&

DELIMITER ;

CALL usp_withdraw_money(1,10);
SELECT 
    *
FROM
    `accounts`
WHERE
    `id` = 1;


-- 14. Money Transfer
DELIMITER &&

CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(20,4))
BEGIN
	START TRANSACTION;
	IF 	
		amount <= 0 OR 
		(SELECT COUNT(`id`) FROM `accounts` WHERE `id` = from_account_id) <= 0 OR
		(SELECT COUNT(`id`) FROM `accounts` WHERE `id` = to_account_id) <= 0 OR
		(SELECT COUNT(`id`) FROM `accounts` WHERE `id` = from_account_id AND `balance` < amount) > 0
    THEN 
		ROLLBACK;
    ELSE
    
    UPDATE `accounts`
	SET `balance` = `balance` - amount
    WHERE `id` = from_account_id;
    
    UPDATE `accounts`
	SET `balance` = `balance` + amount
    WHERE `id` = to_account_id;
    
    END IF;
END&&

DELIMITER ;

CALL usp_transfer_money(1, 2, 10);
SELECT * FROM `accounts` WHERE `id` IN (1,2);


-- 15. Log Accounts Trigger
CREATE TABLE `logs` (
	log_id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT,
    old_sum DECIMAL(25,4),
    new_sum DECIMAL(25,4)
);

DELIMITER &&

CREATE TRIGGER tr_deleted_accounts
AFTER UPDATE
ON `accounts`
FOR EACH ROW
BEGIN
	
    INSERT INTO `logs`(`account_id`, `old_sum`, `new_sum`)
    VALUES (OLD.id, OLD.balance, NEW.balance);

END&&

DELIMITER ;