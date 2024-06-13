-- MySQL Workbench Forward Engineering


-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb_banking_system` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema banking_system
-- -----------------------------------------------------
USE `mydb_banking_system` ;

-- -----------------------------------------------------
-- Table `mydb`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_banking_system`.`customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `customer_first_name` VARCHAR(255) NOT NULL,
  `customer_last_name` VARCHAR(255) NOT NULL,
  `customer_dob` DATE NOT NULL,
  PRIMARY KEY (`customer_id`));


-- -----------------------------------------------------
-- Table `mydb`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_banking_system`.`account` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `account_type` VARCHAR(45) NOT NULL,
  `account_balance` DOUBLE NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`account_id`),
  INDEX `fk_account_customer_idx` (`customer_id` ASC) ,
  CONSTRAINT `fk_account_customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb_banking_system`.`customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_banking_system`.`transaction` (
  `transaction_id` INT NOT NULL AUTO_INCREMENT,
  `transaction_type` VARCHAR(45) NOT NULL,
  `transaction_amount` DOUBLE NOT NULL,
  `transaction_date` DATE NOT NULL,
  `account_id` INT NOT NULL,
  PRIMARY KEY (`transaction_id`),
  INDEX `fk_transaction_account1_idx` (`account_id` ASC) ,
  CONSTRAINT `fk_transaction_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `mydb_banking_system`.`account` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



