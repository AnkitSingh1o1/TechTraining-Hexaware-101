-- MySQL Workbench Forward Engineering


-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb_sms` DEFAULT CHARACTER SET utf8 ;
USE `mydb_sms` ;

-- -----------------------------------------------------
-- Table `mydb`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_sms`.`students` (
  `stu_id` INT NOT NULL AUTO_INCREMENT,
  `stu_first_name` VARCHAR(255) NOT NULL,
  `stu_last_name` VARCHAR(255) NOT NULL,
  `stu_dob` DATE NOT NULL,
  `stu_email` VARCHAR(255) NOT NULL,
  `stu_phone_number` INT NOT NULL,
  PRIMARY KEY (`stu_id`));


-- -----------------------------------------------------
-- Table `mydb`.`teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_sms`.`teacher` (
  `teacher_id` INT NOT NULL AUTO_INCREMENT,
  `teacher_first_name` VARCHAR(255) NOT NULL,
  `teacher_last_name` VARCHAR(255) NOT NULL,
  `teacher_email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`teacher_id`));


-- -----------------------------------------------------
-- Table `mydb`.`courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_sms`.`courses` (
  `course_id` INT NOT NULL,
  `course_name` VARCHAR(255) NOT NULL,
  `course_credits` INT NOT NULL,
  `teacher_id` INT NOT NULL,
  PRIMARY KEY (`course_id`),
  INDEX `fk_courses_teacher1_idx` (`teacher_id` ASC) ,
  CONSTRAINT `fk_courses_teacher1`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `mydb_sms`.`teacher` (`teacher_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`enrollments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_sms`.`enrollments` (
  `stu_id` INT NOT NULL,
  `course_id` INT NOT NULL,
  `enrollment_id` INT NOT NULL AUTO_INCREMENT,
  `enrollment_date` DATE NOT NULL,
  PRIMARY KEY (`enrollment_id`),
  INDEX `fk_students_has_courses_courses1_idx` (`course_id` ASC) ,
  INDEX `fk_students_has_courses_students_idx` (`stu_id` ASC) ,
  CONSTRAINT `fk_students_has_courses_students`
    FOREIGN KEY (`stu_id`)
    REFERENCES `mydb_sms`.`students` (`stu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_students_has_courses_courses1`
    FOREIGN KEY (`course_id`)
    REFERENCES `mydb_sms`.`courses` (`course_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`payments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb_sms`.`payments` (
  `payment_id` INT NOT NULL AUTO_INCREMENT,
  `payment_amount` INT NOT NULL,
  `payment_date` DATE NOT NULL,
  `stu_id` INT NOT NULL,
  PRIMARY KEY (`payment_id`),
  INDEX `fk_payments_students1_idx` (`stu_id` ASC) ,
  CONSTRAINT `fk_payments_students1`
    FOREIGN KEY (`stu_id`)
    REFERENCES `mydb_sms`.`students` (`stu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


