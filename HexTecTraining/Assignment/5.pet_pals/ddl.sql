-- MySQL Workbench Forward Engineering


-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb_pp` DEFAULT CHARACTER SET utf8 ;
USE `mydb_pp` ;

-- -----------------------------------------------------
-- Table `mydb`.`shelters`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_pp`.`shelters` (
  `shelter_id` INT NOT NULL,
  `shelter_name` VARCHAR(255) NOT NULL,
  `shelter_location` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`shelter_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`pets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_pp`.`pets` (
  `pet_id` INT NOT NULL AUTO_INCREMENT,
  `pet_name` VARCHAR(255) NOT NULL,
  `pet_age` INT NOT NULL,
  `pet_breed` VARCHAR(255) NOT NULL,
  `pet_type` VARCHAR(255) NOT NULL,
  `pet_available_for_adoption` BIT(2) NOT NULL,
  `shelter_id` INT NOT NULL,
  PRIMARY KEY (`pet_id`),
  INDEX `fk_pets_shelters1_idx` (`shelter_id` ASC) ,
  CONSTRAINT `fk_pets_shelters1`
    FOREIGN KEY (`shelter_id`)
    REFERENCES `mydb_pp`.`shelters` (`shelter_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`donations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_pp`.`donations` (
  `donation_id` INT NOT NULL AUTO_INCREMENT,
  `donation_name` VARCHAR(255) NOT NULL,
  `donation_type` VARCHAR(255) NOT NULL,
  `donation_amount` DOUBLE NOT NULL,
  `donation_item` VARCHAR(255) NOT NULL,
  `donation_date` DATE NOT NULL,
  `shelter_id` INT NOT NULL,
  PRIMARY KEY (`donation_id`),
  INDEX `fk_donations_shelters1_idx` (`shelter_id` ASC) ,
  CONSTRAINT `fk_donations_shelters1`
    FOREIGN KEY (`shelter_id`)
    REFERENCES `mydb_pp`.`shelters` (`shelter_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`adoption_events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_pp`.`adoption_events` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `event_name` VARCHAR(255) NOT NULL,
  `event_date` DATE NOT NULL,
  `event_location` VARCHAR(255) NOT NULL,
  `shelter_id` INT NOT NULL,
  PRIMARY KEY (`event_id`),
  INDEX `fk_adoption_events_shelters1_idx` (`shelter_id` ASC) ,
  CONSTRAINT `fk_adoption_events_shelters1`
    FOREIGN KEY (`shelter_id`)
    REFERENCES `mydb_pp`.`shelters` (`shelter_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`participants`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_pp`.`participants` (
  `participants_id` INT NOT NULL AUTO_INCREMENT,
  `participants_name` VARCHAR(255) NOT NULL,
  `participants_type` VARCHAR(255) NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`participants_id`),
  INDEX `fk_participants_adoption_events_idx` (`event_id` ASC) ,
  CONSTRAINT `fk_participants_adoption_events`
    FOREIGN KEY (`event_id`)
    REFERENCES `mydb_pp`.`adoption_events` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



