-- MySQL Workbench Forward Engineering


-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb_bs` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema banking_system
-- -----------------------------------------------------
USE `mydb_bs` ;

-- -----------------------------------------------------
-- Table `mydb`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_bs`.`customer` (
  `customer_id` INT NOT NULL,
  `customer_first_name` VARCHAR(255) NOT NULL,
  `customer_last_name` VARCHAR(255) NOT NULL,
  `customer_dob` DATE NOT NULL,
  PRIMARY KEY (`customer_id`));


-- -----------------------------------------------------
-- Table `mydb`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_bs`.`account` (
  `account_id` INT NOT NULL,
  `account_type` VARCHAR(45) NOT NULL,
  `account_balance` DOUBLE NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`account_id`),
  INDEX `fk_account_customer_idx` (`customer_id` ASC) ,
  CONSTRAINT `fk_account_customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb_bs`.`customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_bs`.`transaction` (
  `transaction_id` INT NOT NULL,
  `transaction_type` VARCHAR(45) NOT NULL,
  `transaction_amount` DOUBLE NOT NULL,
  `transaction_date` DATE NOT NULL,
  `account_id` INT NOT NULL,
  PRIMARY KEY (`transaction_id`),
  INDEX `fk_transaction_account1_idx` (`account_id` ASC) ,
  CONSTRAINT `fk_transaction_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `mydb_bs`.`account` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_bs`.`address` (
  `address_id` INT NOT NULL,
  `address_state` VARCHAR(255) NOT NULL,
  `address_city` VARCHAR(255) NOT NULL,
  `address_pincode` INT NOT NULL,
  `customer_id` INT NOT NULL,
  INDEX `fk_address_customer1_idx` (`customer_id` ASC) ,
  PRIMARY KEY (`address_id`),
  CONSTRAINT `fk_address_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb_bs`.`customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



