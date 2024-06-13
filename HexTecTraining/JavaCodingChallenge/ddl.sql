-- MySQL Workbench Forward Engineering


-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb_insurance` DEFAULT CHARACTER SET utf8 ;
USE `mydb_insurance` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_insurance`.`user` (
  `user_id` INT NOT NULL,
  `user_username` VARCHAR(255) NOT NULL,
  `user_password` VARCHAR(255) NOT NULL,
  `user_role` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_id`));


-- -----------------------------------------------------
-- Table `mydb`.`policy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_insurance`.`policy` (
  `policy_id` INT NOT NULL,
  `policy_name` VARCHAR(255) NOT NULL,
  `policy_cost` DOUBLE NOT NULL,
  PRIMARY KEY (`policy_id`));


-- -----------------------------------------------------
-- Table `mydb`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_insurance`.`client` (
  `client_id` INT NOT NULL,
  `client_name` VARCHAR(255) NOT NULL,
  `client_contact_info` VARCHAR(255) NOT NULL,
  `user_id` INT NOT NULL,
  `policy_id` INT NOT NULL,
  PRIMARY KEY (`client_id`),
  INDEX `fk_client_user1_idx` (`user_id` ASC) ,
  INDEX `fk_client_policy1_idx` (`policy_id` ASC) ,
  CONSTRAINT `fk_client_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb_insurance`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_policy1`
    FOREIGN KEY (`policy_id`)
    REFERENCES `mydb_insurance`.`policy` (`policy_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`claim`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_insurance`.`claim` (
  `claim_id` INT NOT NULL,
  `claim_number` INT NOT NULL,
  `claim_date_filed` DATE NOT NULL,
  `claim_amount` DOUBLE NOT NULL,
  `claim_status` VARCHAR(255) NOT NULL,
  `client_id` INT NOT NULL,
  `policy_id` INT NOT NULL,
  PRIMARY KEY (`claim_id`),
  INDEX `fk_claim_client1_idx` (`client_id` ASC) ,
  INDEX `fk_claim_policy1_idx` (`policy_id` ASC) ,
  CONSTRAINT `fk_claim_client1`
    FOREIGN KEY (`client_id`)
    REFERENCES `mydb_insurance`.`client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_claim_policy1`
    FOREIGN KEY (`policy_id`)
    REFERENCES `mydb_insurance`.`policy` (`policy_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_insurance`.`payment` (
  `payment_id` INT NOT NULL,
  `payment_date` DATE NOT NULL,
  `payment_amount` DOUBLE NOT NULL,
  `client_id` INT NOT NULL,
  PRIMARY KEY (`payment_id`),
  INDEX `fk_payment_client1_idx` (`client_id` ASC) ,
  CONSTRAINT `fk_payment_client1`
    FOREIGN KEY (`client_id`)
    REFERENCES `mydb_insurance`.`client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



