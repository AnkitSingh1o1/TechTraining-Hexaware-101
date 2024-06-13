-- MySQL Workbench Forward Engineering
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb_vag` DEFAULT CHARACTER SET utf8 ;
USE `mydb_vag` ;

-- -----------------------------------------------------
-- Table `mydb`.`artists`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_vag`.`artists` (
  `artists_id` INT NOT NULL,
  `artists_name` VARCHAR(255) NOT NULL,
  `artists_biography` TEXT(5) NULL,
  `artists_nationality` VARCHAR(100) NULL,
  PRIMARY KEY (`artists_id`));


-- -----------------------------------------------------
-- Table `mydb`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_vag`.`categories` (
  `categories_id` INT NOT NULL,
  `catogeries_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`categories_id`));


-- -----------------------------------------------------
-- Table `mydb`.`exhibition`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_vag`.`exhibition` (
  `exhibition_id` INT NOT NULL,
  `exhibition_title` VARCHAR(255) NOT NULL,
  `exhibition_start_date` DATE NULL,
  `exhibition_end_date` DATE NULL,
  `exhibition_description` TEXT(5) NULL,
  PRIMARY KEY (`exhibition_id`));


-- -----------------------------------------------------
-- Table `mydb`.`artworks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_vag`.`artworks` (
  `categories_id` INT NULL,
  `artists_id` INT NULL,
  `artwork_id` INT NOT NULL,
  `artwork_title` VARCHAR(255) NOT NULL,
  `artwork_year` INT NULL,
  `artwork_description` TEXT(5) NULL,
  `artwork_image_url` VARCHAR(255) NULL,
  INDEX `fk_categories_has_artists_artists1_idx` (`artists_id` ASC) ,
  INDEX `fk_categories_has_artists_categories_idx` (`categories_id` ASC) ,
  PRIMARY KEY (`artwork_id`),
  CONSTRAINT `fk_categories_has_artists_categories`
    FOREIGN KEY (`categories_id`)
    REFERENCES `mydb_vag`.`categories` (`categories_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_categories_has_artists_artists1`
    FOREIGN KEY (`artists_id`)
    REFERENCES `mydb_vag`.`artists` (`artists_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`exhibition_artwork`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_vag`.`exhibition_artwork` (
  `exhibition_id` INT NOT NULL,
  `artwork_id` INT NOT NULL,
  PRIMARY KEY (`exhibition_id`, `artwork_id`),
  INDEX `fk_exhibition_has_artworks_artworks1_idx` (`artwork_id` ASC) ,
  INDEX `fk_exhibition_has_artworks_exhibition1_idx` (`exhibition_id` ASC) ,
  CONSTRAINT `fk_exhibition_has_artworks_exhibition1`
    FOREIGN KEY (`exhibition_id`)
    REFERENCES `mydb_vag`.`exhibition` (`exhibition_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_exhibition_has_artworks_artworks1`
    FOREIGN KEY (`artwork_id`)
    REFERENCES `mydb_vag`.`artworks` (`artwork_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


