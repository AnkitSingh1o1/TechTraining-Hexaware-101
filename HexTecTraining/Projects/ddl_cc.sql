-- MySQL Workbench Forward Engineering


-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb3 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `user_id` INT NOT NULL,
  `user_username` VARCHAR(255) NOT NULL,
  `user_password` VARCHAR(255) NOT NULL,
  `user_role` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`admin` (
  `admin_id` INT NOT NULL,
  `admin_first_name` VARCHAR(255) NOT NULL,
  `admin_last_name` VARCHAR(255) NOT NULL,
  `admin_email` VARCHAR(255) NOT NULL,
  `admin_phone_number` VARCHAR(255) NOT NULL,
  `admin_role` VARCHAR(255) NOT NULL,
  `admin_join_date` DATE NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`admin_id`),
  INDEX `fk_admin_user1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_admin_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`address` (
  `address_id`  NOT NULL,
  `address_state`  NOT NULL,
  `address_city`  NOT NULL,
  `address_pincode`  NOT NULL,
  PRIMARY KEY (`address_id`));


-- -----------------------------------------------------
-- Table `mydb`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `customer_first_name` VARCHAR(255) NOT NULL,
  `customer_last_name` VARCHAR(255) NOT NULL,
  `customer_email` VARCHAR(255) NOT NULL,
  `customer_phone_number` VARCHAR(255) NOT NULL,
  `customer_registration_date` DATE NOT NULL,
  `user_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE INDEX `customer_email_UNIQUE` (`customer_email` ASC) ,
  INDEX `fk_customer_user1_idx` (`user_id` ASC) ,
  INDEX `fk_customer_address_11_idx` (`address_id` ASC) ,
  CONSTRAINT `fk_customer_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`user_id`),
  CONSTRAINT `fk_customer_address_11`
    FOREIGN KEY (`address_id`)
    REFERENCES `mydb`.`address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`vendor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`vendor` (
  `vendor_id` INT NOT NULL,
  `vendor_first_name` VARCHAR(255) NOT NULL,
  `vendor_last_name` VARCHAR(255) NOT NULL,
  `vendor_email` VARCHAR(255) NOT NULL,
  `vendor_phone_number` VARCHAR(255) NOT NULL,
  `vendor_registration_date` DATE NOT NULL,
  `user_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`vendor_id`),
  INDEX `fk_vendor_user1_idx` (`user_id` ASC) ,
  INDEX `fk_vendor_address_11_idx` (`address_id` ASC) ,
  CONSTRAINT `fk_vendor_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`user_id`),
  CONSTRAINT `fk_vendor_address_11`
    FOREIGN KEY (`address_id`)
    REFERENCES `mydb`.`address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`vehicle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`vehicle` (
  `vehicle_id` INT NOT NULL,
  `vehicle_model` VARCHAR(255) NOT NULL,
  `vehicle_make` VARCHAR(255) NOT NULL,
  `vehicle_year` YEAR NOT NULL,
  `vehicle_color` VARCHAR(255) NOT NULL,
  `vehicle_registration_no` VARCHAR(255) NOT NULL,
  `vehicle_availability` BIT(1) NOT NULL,
  `vehicle_daily_rate` DOUBLE NOT NULL,
  `vendor_id` INT NOT NULL,
  PRIMARY KEY (`vehicle_id`),
  INDEX `fk_vehicle_vendor1_idx` (`vendor_id` ASC) ,
  CONSTRAINT `fk_vehicle_vendor1`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `mydb`.`vendor` (`vendor_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`reservation` (
  `customer_id` INT NOT NULL,
  `vehicle_id` INT NOT NULL,
  `reservation_id` INT NOT NULL,
  `reservation_start_date` DATE NOT NULL,
  `reservation_end_date` DATE NOT NULL,
  `reservation_total_cost` DOUBLE NOT NULL,
  `reservation_status` VARCHAR(255) NOT NULL,
  `admin_id` INT NOT NULL,
  PRIMARY KEY (`reservation_id`),
  INDEX `fk_customer_has_vehicle_vehicle1_idx` (`vehicle_id` ASC) ,
  INDEX `fk_customer_has_vehicle_customer_idx` (`customer_id` ASC) ,
  INDEX `fk_reservation_admin1_idx` (`admin_id` ASC) ,
  CONSTRAINT `fk_customer_has_vehicle_customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`customer` (`customer_id`),
  CONSTRAINT `fk_customer_has_vehicle_vehicle1`
    FOREIGN KEY (`vehicle_id`)
    REFERENCES `mydb`.`vehicle` (`vehicle_id`),
  CONSTRAINT `fk_reservation_admin1`
    FOREIGN KEY (`admin_id`)
    REFERENCES `mydb`.`admin` (`admin_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`review` (
  `customer_id` INT NOT NULL,
  `vehicle_id` INT NOT NULL,
  `review_comment` VARCHAR(255) NOT NULL,
  `review_id` INT NOT NULL,
  `review_rating` INT NOT NULL,
  PRIMARY KEY (`review_id`),
  INDEX `fk_customer_has_vehicle_vehicle2_idx` (`vehicle_id` ASC) ,
  INDEX `fk_customer_has_vehicle_customer1_idx` (`customer_id` ASC) ,
  CONSTRAINT `fk_customer_has_vehicle_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`customer` (`customer_id`),
  CONSTRAINT `fk_customer_has_vehicle_vehicle2`
    FOREIGN KEY (`vehicle_id`)
    REFERENCES `mydb`.`vehicle` (`vehicle_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`address` (
  `address_id`  NOT NULL,
  `address_state`  NOT NULL,
  `address_city`  NOT NULL,
  `address_pincode`  NOT NULL,
  PRIMARY KEY (`address_id`));


