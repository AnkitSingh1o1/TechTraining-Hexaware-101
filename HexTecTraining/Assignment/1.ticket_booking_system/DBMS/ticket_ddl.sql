-- MySQL Workbench Forward Engineering


-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`venue`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`venue` (
  `venue_id` INT NOT NULL AUTO_INCREMENT,
  `venue_name` VARCHAR(255) NOT NULL,
  `venue_address` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`venue_id`));


-- -----------------------------------------------------
-- Table `mydb`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `customer_name` VARCHAR(255) NOT NULL,
  `customer_email` VARCHAR(255) NOT NULL,
  `customer_phone_number` INT NOT NULL,
  PRIMARY KEY (`customer_id`));


-- -----------------------------------------------------
-- Table `mydb`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`event` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `event_name` VARCHAR(255) NOT NULL,
  `event_date` DATE NOT NULL,
  `event_time` TIME NOT NULL,
  `event_total_seats` INT NOT NULL,
  `event_available_seats` INT NOT NULL,
  `event_ticket_price` DOUBLE NOT NULL,
  `event_type` VARCHAR(255) NOT NULL,
  `venue_id` INT NOT NULL,
  PRIMARY KEY (`event_id`),
  INDEX `fk_event_venue_idx` (`venue_id` ASC) ,
  CONSTRAINT `fk_event_venue`
    FOREIGN KEY (`venue_id`)
    REFERENCES `mydb`.`venue` (`venue_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`booking` (
  `event_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `booking_num_tickets` INT NOT NULL,
  `booking_total_cost` DOUBLE NOT NULL,
  `booking_date` DATE NOT NULL,
  PRIMARY KEY (`event_id`, `customer_id`),
  INDEX `fk_event_has_customer_customer1_idx` (`customer_id` ASC) ,
  INDEX `fk_event_has_customer_event1_idx` (`event_id` ASC) ,
  CONSTRAINT `fk_event_has_customer_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `mydb`.`event` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_has_customer_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



