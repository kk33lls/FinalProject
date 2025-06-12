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
  `email` VARCHAR(50) NULL,
  `first_name` VARCHAR(50) NULL,
  `last_name` VARCHAR(50) NULL,
  `role` VARCHAR(50) NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `image_url` VARCHAR(2000) NULL,
  `biography` TEXT NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `care_difficulty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `care_difficulty` ;

CREATE TABLE IF NOT EXISTS `care_difficulty` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plant_species`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plant_species` ;

CREATE TABLE IF NOT EXISTS `plant_species` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `genus` VARCHAR(50) NOT NULL,
  `species` VARCHAR(50) NOT NULL,
  `common_names` TEXT NOT NULL,
  `description` TEXT BINARY NULL,
  `light_requirements` TEXT NULL,
  `water_frequency` TEXT NULL,
  `native_to` VARCHAR(200) NULL,
  `temperature_range` VARCHAR(50) NULL,
  `image_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NOT NULL,
  `care_difficulty_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_plant_species_care_difficulty1_idx` (`care_difficulty_id` ASC) VISIBLE,
  CONSTRAINT `fk_plant_species_care_difficulty1`
    FOREIGN KEY (`care_difficulty_id`)
    REFERENCES `care_difficulty` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_plant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_plant` ;

CREATE TABLE IF NOT EXISTS `user_plant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  `acquired_date` DATE NULL,
  `where_acquired` VARCHAR(200) NULL,
  `location` VARCHAR(200) NULL,
  `notes` TEXT NULL,
  `alive` TINYINT NULL,
  `created_date` DATETIME NULL,
  `updated_date` DATETIME NULL,
  `image_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NOT NULL,
  `user_id` INT NOT NULL,
  `plant_species_id` INT NOT NULL,
  PRIMARY KEY (`id`),
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
-- Table `care_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `care_type` ;

CREATE TABLE IF NOT EXISTS `care_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `care_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `care_log` ;

CREATE TABLE IF NOT EXISTS `care_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `notes` TEXT NULL,
  `care_date` DATETIME NULL,
  `image_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NOT NULL,
  `user_plant_id` INT NOT NULL,
  `care_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_care_log_user_plant1_idx` (`user_plant_id` ASC) VISIBLE,
  INDEX `fk_care_log_care_type1_idx` (`care_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_care_log_user_plant1`
    FOREIGN KEY (`user_plant_id`)
    REFERENCES `user_plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_care_log_care_type1`
    FOREIGN KEY (`care_type_id`)
    REFERENCES `care_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reminder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reminder` ;

CREATE TABLE IF NOT EXISTS `reminder` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NULL,
  `reminder_date` DATETIME NULL,
  `title` VARCHAR(2000) NULL,
  `notes` TEXT NULL,
  `completed` TINYINT NULL,
  `image_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NOT NULL,
  `user_plant_id` INT NOT NULL,
  `care_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reminder_user_plant1_idx` (`user_plant_id` ASC) VISIBLE,
  INDEX `fk_reminder_care_type1_idx` (`care_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_reminder_user_plant1`
    FOREIGN KEY (`user_plant_id`)
    REFERENCES `user_plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reminder_care_type1`
    FOREIGN KEY (`care_type_id`)
    REFERENCES `care_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `species_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `species_comment` ;

CREATE TABLE IF NOT EXISTS `species_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `comment` TEXT NULL,
  `image_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NOT NULL,
  `plant_species_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `in_reply_to_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_plant_comment_plant_species1_idx` (`plant_species_id` ASC) VISIBLE,
  INDEX `fk_plant_comment_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_species_comment_species_comment1_idx` (`in_reply_to_id` ASC) VISIBLE,
  CONSTRAINT `fk_plant_comment_plant_species1`
    FOREIGN KEY (`plant_species_id`)
    REFERENCES `plant_species` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plant_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_species_comment_species_comment1`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `species_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plant_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plant_comment` ;

CREATE TABLE IF NOT EXISTS `plant_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `comment` TEXT NULL,
  `image_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NOT NULL,
  `user_id` INT NOT NULL,
  `in_reply_to_id` INT NULL,
  `user_plant_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_plant_comment_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_species_comment_species_comment1_idx` (`in_reply_to_id` ASC) VISIBLE,
  INDEX `fk_plant_comment_user_plant1_idx` (`user_plant_id` ASC) VISIBLE,
  CONSTRAINT `fk_plant_comment_user10`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_species_comment_species_comment10`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `plant_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plant_comment_user_plant1`
    FOREIGN KEY (`user_plant_id`)
    REFERENCES `user_plant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plant_collection`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `plant_collection` ;

CREATE TABLE IF NOT EXISTS `plant_collection` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(2000) NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_plant_collection_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_plant_collection_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `collection_has_plant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `collection_has_plant` ;

CREATE TABLE IF NOT EXISTS `collection_has_plant` (
  `plant_collection_id` INT NOT NULL,
  `user_plant_id` INT NOT NULL,
  PRIMARY KEY (`plant_collection_id`, `user_plant_id`),
  INDEX `fk_plant_collection_has_user_plant_user_plant1_idx` (`user_plant_id` ASC) VISIBLE,
  INDEX `fk_plant_collection_has_user_plant_plant_collection1_idx` (`plant_collection_id` ASC) VISIBLE,
  CONSTRAINT `fk_plant_collection_has_user_plant_plant_collection1`
    FOREIGN KEY (`plant_collection_id`)
    REFERENCES `plant_collection` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plant_collection_has_user_plant_user_plant1`
    FOREIGN KEY (`user_plant_id`)
    REFERENCES `user_plant` (`id`)
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
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `role`, `created_at`, `updated_at`, `image_url`, `biography`, `enabled`) VALUES (1, 'test', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', NULL, NULL, NULL, 'role', NULL, NULL, NULL, NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `care_difficulty`
-- -----------------------------------------------------
START TRANSACTION;
USE `soilmatesdb`;
INSERT INTO `care_difficulty` (`id`, `name`, `description`, `image_url`) VALUES (1, 'Easy', NULL, NULL);
INSERT INTO `care_difficulty` (`id`, `name`, `description`, `image_url`) VALUES (2, 'Medium', NULL, NULL);
INSERT INTO `care_difficulty` (`id`, `name`, `description`, `image_url`) VALUES (3, 'Req. Special Equipment', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `care_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `soilmatesdb`;
INSERT INTO `care_type` (`id`, `name`, `description`, `image_url`) VALUES (1, 'Watering', NULL, NULL);
INSERT INTO `care_type` (`id`, `name`, `description`, `image_url`) VALUES (2, 'Fertilize', NULL, NULL);

COMMIT;

