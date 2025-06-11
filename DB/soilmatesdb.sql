-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema soilmatesdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `soilmatesdb` ;

-- -----------------------------------------------------
-- Schema soilmatesdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `soilmatesdb` DEFAULT CHARACTER SET utf8 ;
USE `soilmatesdb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `enabled` TINYINT NOT NULL,
  `email` VARCHAR(50) NULL,
  `first_name` VARCHAR(50) NULL,
  `last_name` VARCHAR(50) NULL,
  `role` VARCHAR(50) NULL,
  `created_at` TIMESTAMP NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plant_species`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plant_species` ;

CREATE TABLE IF NOT EXISTS `plant_species` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `genus` VARCHAR(2000) NOT NULL,
  `species` VARCHAR(2000) NOT NULL,
  `common_name` VARCHAR(2000) NOT NULL,
  `description` TEXT BINARY NULL,
  `care_difficulty` VARCHAR(45) NULL,
  `light_requirements` VARCHAR(2000) NULL,
  `image_url` VARCHAR(4000) NULL,
  `water_frequency` VARCHAR(2000) NULL,
  `native_to` VARCHAR(2000) NULL,
  `temperature_range` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_plant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_plant` ;

CREATE TABLE IF NOT EXISTS `user_plant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(2000) NULL,
  `acquired_date` TIMESTAMP NULL,
  `location` VARCHAR(2000) NULL,
  `notes` TEXT NULL,
  `is_alive` TINYINT NULL,
  `created_date` TIMESTAMP NULL,
  `updated_date` TIMESTAMP NULL,
  `image_url` VARCHAR(4000) NULL,
  `user_id` INT NOT NULL,
  `plant_species_id` INT NOT NULL,
  PRIMARY KEY (`id`, `user_id`, `plant_species_id`),
  INDEX `fk_user_plant_user_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_user_plant_plant_species1_idx` (`plant_species_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_plant_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_plant_plant_species1`
    FOREIGN KEY (`plant_species_id`)
    REFERENCES `plant_species` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `care_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `care_log` ;

CREATE TABLE IF NOT EXISTS `care_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `care_type` VARCHAR(2000) NULL,
  `notes` TEXT NULL,
  `care_date` TIMESTAMP NULL,
  `image_url` VARCHAR(4000) NULL,
  `user_plant_id` INT NOT NULL,
  `user_plant_user_id` INT NOT NULL,
  `user_plant_plant_species_id` INT NOT NULL,
  PRIMARY KEY (`id`, `user_plant_id`, `user_plant_user_id`, `user_plant_plant_species_id`),
  INDEX `fk_care_log_user_plant1_idx` (`user_plant_id` ASC, `user_plant_user_id` ASC, `user_plant_plant_species_id` ASC) VISIBLE,
  CONSTRAINT `fk_care_log_user_plant1`
    FOREIGN KEY (`user_plant_id` , `user_plant_user_id` , `user_plant_plant_species_id`)
    REFERENCES `user_plant` (`id` , `user_id` , `plant_species_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reminder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reminder` ;

CREATE TABLE IF NOT EXISTS `reminder` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP NULL,
  `reminder_date` TIMESTAMP NULL,
  `reminder_time` TIMESTAMP NULL,
  `reminder_type` VARCHAR(2000) NULL,
  `title` VARCHAR(2000) NULL,
  `description` TEXT NULL,
  `is_complete` TINYINT NULL,
  `image_url` VARCHAR(4000) NULL,
  `user_plant_id` INT NOT NULL,
  `user_plant_user_id` INT NOT NULL,
  `user_plant_plant_species_id` INT NOT NULL,
  PRIMARY KEY (`id`, `user_plant_id`, `user_plant_user_id`, `user_plant_plant_species_id`),
  INDEX `fk_reminder_user_plant1_idx` (`user_plant_id` ASC, `user_plant_user_id` ASC, `user_plant_plant_species_id` ASC) VISIBLE,
  CONSTRAINT `fk_reminder_user_plant1`
    FOREIGN KEY (`user_plant_id` , `user_plant_user_id` , `user_plant_plant_species_id`)
    REFERENCES `user_plant` (`id` , `user_id` , `plant_species_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plant_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plant_comment` ;

CREATE TABLE IF NOT EXISTS `plant_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP NULL,
  `updated_at` TIMESTAMP NULL,
  `comment` TEXT NULL,
  `image_url` VARCHAR(4000) NULL,
  `plant_species_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`, `plant_species_id`, `user_id`),
  INDEX `fk_plant_comment_plant_species1_idx` (`plant_species_id` ASC) VISIBLE,
  INDEX `fk_plant_comment_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_plant_comment_plant_species1`
    FOREIGN KEY (`plant_species_id`)
    REFERENCES `plant_species` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plant_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS soilmatesuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'soilmatesuser'@'localhost' IDENTIFIED BY 'soilmatesuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'soilmatesuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `soilmatesdb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `email`, `first_name`, `last_name`, `role`, `created_at`, `image_url`) VALUES (1, 'test', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, NULL, NULL, NULL, 'admin', NULL, NULL);

COMMIT;

