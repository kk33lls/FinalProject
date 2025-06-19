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
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `role`, `created_at`, `updated_at`, `image_url`, `biography`, `enabled`) VALUES (1, 'demo', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'Demo123@soilmates.com', 'Bobby', 'Tables', '', NULL, NULL, 'https://i.imgur.com/1k2Te0M.jpeg', NULL, 1);
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `role`, `created_at`, `updated_at`, `image_url`, `biography`, `enabled`) VALUES (2, 'test', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 'test@soilmates.com', 'Bobby', 'Tables', NULL, NULL, NULL, 'https://i.imgur.com/bknkwqK.jpeg', NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `care_difficulty`
-- -----------------------------------------------------
START TRANSACTION;
USE `soilmatesdb`;
INSERT INTO `care_difficulty` (`id`, `name`, `description`, `image_url`) VALUES (1, 'Foolproof', 'Nearly impossible to kill. Great for beginners or busy folks.', NULL);
INSERT INTO `care_difficulty` (`id`, `name`, `description`, `image_url`) VALUES (2, 'Very Easy', 'Minimal care needed, thrives in various conditions.', NULL);
INSERT INTO `care_difficulty` (`id`, `name`, `description`, `image_url`) VALUES (3, 'Easy', 'Requires basic care like light and watering once a week.', NULL);
INSERT INTO `care_difficulty` (`id`, `name`, `description`, `image_url`) VALUES (4, 'Moderate', 'Needs attention to light, water, and occasional fertilizing.', NULL);
INSERT INTO `care_difficulty` (`id`, `name`, `description`, `image_url`) VALUES (5, 'Challenging', 'Requires consistent care and ideal conditions.', NULL);
INSERT INTO `care_difficulty` (`id`, `name`, `description`, `image_url`) VALUES (6, 'Demanding', 'Sensitive to environment; mistakes can cost dearly.', NULL);
INSERT INTO `care_difficulty` (`id`, `name`, `description`, `image_url`) VALUES (7, 'Expert', 'For seasoned plant lovers. High maintenance and fragile.', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plant_species`
-- -----------------------------------------------------
START TRANSACTION;
USE `soilmatesdb`;
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (1, 'Ficus', 'elastica', 'Rubber Plant', 'Glossy-leaved beauty, great indoors.', 'Bright indirect', 'weekly', 'Southeast Asia', '60–75°F', 'https://i.imgur.com/jCmXZup.png Ficus Elastica	https://i.imgur.com/jCmXZup.png https://i.imgur.com/jCmXZup.png', 1, 3);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (2, 'Sansevieria', 'trifasciata', 'Snake Plant', 'Tolerates neglect and low light.', 'Low to bright indirect', 'Every 2–3 weeks', 'West Africa', '60–85°F', 'https://i.imgur.com/o1jJuGU.jpeg', 1, 2);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (3, 'Aloe', 'barbadensis', 'Aloe Vera', 'Succulent with medicinal gel inside leaves.', 'Low to bright indirect', 'Every 2–3 weeks', 'North Africa', '55–80°F', 'https://i.imgur.com/EG2eL8E.png', 1, 2);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (4, 'Chlorophytum', 'comosum', 'Spider Plant', 'Air-purifying plant with runners.', 'Bright Indirect', 'Weekly', 'South Africa', '65–75°F', 'https://i.imgur.com/Y780m4C.png', 1, 2);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (5, 'Spathiphyllum', 'wallisii', 'Peace Lily', 'Flowering plant, prefers moist soil.', 'Low to Medium', 'Weekly', 'Americas', '65–80°F', 'https://i.imgur.com/w2denoT.jpeg', 1, 1);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (6, 'Zamioculcas', 'zamiifolia', 'ZZ Plant', 'Glossy green leaves, thrives on neglect.', 'Low to Bright, Indirect', 'Every 2-3 weeks', 'Eastern Africa', '60–75°F', 'https://i.imgur.com/Zl4xpLn.jpeg', 1, 1);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (7, 'Epipremnum', 'aureum', 'Devil\'s Ivy', 'Fast-growing vine with heart leaves.', 'Low to Bright', 'Weekly', 'Solomon Islands', '60–80°F', 'https://i.imgur.com/FoRUsfl.jpeg', 1, 2);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (8, 'Monstera', 'deliciosa', 'Swiss Cheese Plant', 'Tropical plant with fenestrated leaves.', 'Bright Indirect', 'Weekly', 'Central America', '65–85°F', 'https://i.imgur.com/shuG4xw.png', 1, 4);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (9, 'Ficus', 'robusta', 'Rubber Tree', 'Dark green, tough leaves, air purifying.', 'Bright Indirect', 'Weekly', 'India', '65–80°F', 'https://i.imgur.com/1eY3nFT.jpeg', 1, 3);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (10, 'Nephrolepis', 'exaltata', 'Boston Fern', 'Feathery fronds, loves moisture.', 'Indirect Light', '2-3 Times per week', 'Tropical Americas', '65–80°F', 'https://i.imgur.com/qXRy96Y.jpeg', 1, 5);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (11, 'Phalaenopsis', 'spp.', 'Moth Orchid', 'Long-blooming elegant flowers.', 'Bright Indirect', 'Weekly (less in Winter)', 'Asia', '60–80°F', 'https://i.imgur.com/PU6DgtB.png', 1, 5);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (12, 'Crassula', 'ovata', 'Jade Plant', 'Thick leaves, stores water.', 'Bright Light', 'Every 2-3 weeks', 'South Africa', '65–75°F', 'https://i.imgur.com/ZaJSdvF.jpeg', 1, 6);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (13, 'Chamaedorea', 'seifrizii', 'Bamboo Palm', 'Air-purifying indoor palm.', 'Low to Bright Indirect', 'Weekly', 'Central America', '65–75°F', 'https://i.imgur.com/CEpNAGS.jpeg', 1, 4);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (14, 'Codiaeum', 'variegatum', 'Croton', 'Colorful tropical leaves.', 'Bright Light', 'Weekly', 'Southeast Asia', '60–85°F', 'https://i.imgur.com/PSZyKF3.jpeg', 1, 5);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (15, 'Calathea', 'ornata', 'Pinstripe Calathea', 'Striped leaves that move at night.', 'Low to Medium', '2-3 Times per week', 'South America', '65–80°F', 'https://i.imgur.com/5ZQO921.jpeg', 1, 6);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (16, 'Dracaena', 'marginata', 'Dragon Tree', 'Spiky leaves, low maintenance.', 'Bright Indirect', 'Every 2 weeks', 'Madagascar', '65–80°F', 'https://i.imgur.com/7Xt03CK.jpeg', 1, 3);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (17, 'Dracaena', 'sanderiana', 'Lucky Bamboo', 'Often grown in water.', 'Low to Medium', 'Top off weekly', 'Central Africa', '65–80°F', 'https://i.imgur.com/RzgIq6m.png', 1, 2);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (18, 'Aglaonema', 'spp.\'', 'Chinese Evergreen', 'Colorful, easy-care foliage.', 'Low to Medium', 'Weekly', 'Asia', '65–80°F', 'https://i.imgur.com/axPt7ZP.jpeg', 1, 2);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (19, 'Strelitzia', 'reginae', 'Bird of Paradise', 'Striking tropical flowers.', 'Bright Indirect to Direct', 'Weekly', 'South Africa', '65–80°F', 'https://i.imgur.com/OsRdxLW.png', 1, 5);
INSERT INTO `plant_species` (`id`, `genus`, `species`, `common_names`, `description`, `light_requirements`, `water_frequency`, `native_to`, `temperature_range`, `image_url`, `enabled`, `care_difficulty_id`) VALUES (20, 'Dionaea', 'muscipula', 'Venus Flytrap', 'Carnivorous plant, sensitive to care.', 'Bright Direct', 'Keep Moist; distilled water only.', 'United States', '65–80°F', 'https://i.imgur.com/ssykkoD.png', 1, 7);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_plant`
-- -----------------------------------------------------
START TRANSACTION;
USE `soilmatesdb`;
INSERT INTO `user_plant` (`id`, `name`, `acquired_date`, `where_acquired`, `location`, `notes`, `alive`, `created_date`, `updated_date`, `image_url`, `enabled`, `user_id`, `plant_species_id`) VALUES (1, 'Snappy', '2023-11-02\'', 'Online Shop', 'Office Desk', 'Super chill, always has munchies', 1, NULL, NULL, NULL, 1, 2, 20);
INSERT INTO `user_plant` (`id`, `name`, `acquired_date`, `where_acquired`, `location`, `notes`, `alive`, `created_date`, `updated_date`, `image_url`, `enabled`, `user_id`, `plant_species_id`) VALUES (2, 'Ruby', '2024-03-15', 'Farmer\\\'s market', 'Bedroom Shelf', 'My first ever plant — a total survivor.', 1, NULL, NULL, NULL, 1, 2, 1);
INSERT INTO `user_plant` (`id`, `name`, `acquired_date`, `where_acquired`, `location`, `notes`, `alive`, `created_date`, `updated_date`, `image_url`, `enabled`, `user_id`, `plant_species_id`) VALUES (3, 'Bitter Aloe', '2024-05-03', 'Gift from Mom', 'Kitchen Window', 'Useful for burns, thrives on neglect.', 1, NULL, NULL, NULL, 1, 2, 3);
INSERT INTO `user_plant` (`id`, `name`, `acquired_date`, `where_acquired`, `location`, `notes`, `alive`, `created_date`, `updated_date`, `image_url`, `enabled`, `user_id`, `plant_species_id`) VALUES (4, 'Fronz', '2022-05-10', 'Community Nursery', 'Bathroom', 'I keep this Boston Fern in the bathroom.', 1, NULL, NULL, NULL, 1, 2, 2);
INSERT INTO `user_plant` (`id`, `name`, `acquired_date`, `where_acquired`, `location`, `notes`, `alive`, `created_date`, `updated_date`, `image_url`, `enabled`, `user_id`, `plant_species_id`) VALUES (5, 'Whiz', '2025-03-12', 'Terrarium Kit', 'Glass Bowl on Table', 'Big Monstera taking over the living room.', 1, NULL, NULL, NULL, 1, 2, 10);
INSERT INTO `user_plant` (`id`, `name`, `acquired_date`, `where_acquired`, `location`, `notes`, `alive`, `created_date`, `updated_date`, `image_url`, `enabled`, `user_id`, `plant_species_id`) VALUES (6, 'Scooter', '2022-05-10', 'Local Garden', 'Front Porch', 'One of my favs', NULL, NULL, NULL, NULL, 1, 2, 11);
INSERT INTO `user_plant` (`id`, `name`, `acquired_date`, `where_acquired`, `location`, `notes`, `alive`, `created_date`, `updated_date`, `image_url`, `enabled`, `user_id`, `plant_species_id`) VALUES (7, 'Rex', '2025-02-22', 'Joe\'s Farm', 'Bedroom', 'Love it!', NULL, NULL, NULL, NULL, 1, 2, 14);

COMMIT;


-- -----------------------------------------------------
-- Data for table `care_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `soilmatesdb`;
INSERT INTO `care_type` (`id`, `name`, `description`, `image_url`) VALUES (1, 'Watering', 'Providing the plant with the appropriate amount of water.', NULL);
INSERT INTO `care_type` (`id`, `name`, `description`, `image_url`) VALUES (2, 'Pruning', 'Trimming dead or overgrown leaves, stems, or branches.', NULL);
INSERT INTO `care_type` (`id`, `name`, `description`, `image_url`) VALUES (3, 'Fertilizing', 'Adding nutrients to the soil to promote growth.', NULL);
INSERT INTO `care_type` (`id`, `name`, `description`, `image_url`) VALUES (4, 'Reporting', 'Repotting\', \'Moving the plant to a larger container or refreshing soil.', NULL);
INSERT INTO `care_type` (`id`, `name`, `description`, `image_url`) VALUES (5, 'Pest Control', 'Pest Control\', \'Treating the plant to remove or prevent pests.', NULL);
INSERT INTO `care_type` (`id`, `name`, `description`, `image_url`) VALUES (6, 'Cleaning', 'Cleaning\', \'Wiping dust off leaves or cleaning debris from soil.', NULL);
INSERT INTO `care_type` (`id`, `name`, `description`, `image_url`) VALUES (7, 'Sunlight Adjustment', 'Relocating the plant to improve light exposure.', NULL);
INSERT INTO `care_type` (`id`, `name`, `description`, `image_url`) VALUES (8, 'Other', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `care_log`
-- -----------------------------------------------------
START TRANSACTION;
USE `soilmatesdb`;
INSERT INTO `care_log` (`id`, `notes`, `care_date`, `image_url`, `enabled`, `user_plant_id`, `care_type_id`) VALUES (1, 'Thoroughly watered the soil.', '2025-06-10', NULL, 1, 1, 1);
INSERT INTO `care_log` (`id`, `notes`, `care_date`, `image_url`, `enabled`, `user_plant_id`, `care_type_id`) VALUES (2, 'Snipped off a few dead leaves.', '2025-06-08', NULL, 1, 3, 2);
INSERT INTO `care_log` (`id`, `notes`, `care_date`, `image_url`, `enabled`, `user_plant_id`, `care_type_id`) VALUES (3, 'Fed it with balanced fertilizer.', '2025-06-07', NULL, 1, 4, 3);
INSERT INTO `care_log` (`id`, `notes`, `care_date`, `image_url`, `enabled`, `user_plant_id`, `care_type_id`) VALUES (4, 'Moved plant to brighter window spot.', '2025-06-09', NULL, 1, 5, 7);
INSERT INTO `care_log` (`id`, `notes`, `care_date`, `image_url`, `enabled`, `user_plant_id`, `care_type_id`) VALUES (5, 'Removed pests with neem oil spray.', '2025-06-11', NULL, 1, 2, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `reminder`
-- -----------------------------------------------------
START TRANSACTION;
USE `soilmatesdb`;
INSERT INTO `reminder` (`id`, `created_at`, `reminder_date`, `title`, `notes`, `completed`, `image_url`, `enabled`, `user_plant_id`, `care_type_id`) VALUES (1, NULL, NULL, 'Water Ruby', 'Time to water Ruby.', 0, NULL, 1, 1, 1);
INSERT INTO `reminder` (`id`, `created_at`, `reminder_date`, `title`, `notes`, `completed`, `image_url`, `enabled`, `user_plant_id`, `care_type_id`) VALUES (2, NULL, NULL, 'Prune Spikey', 'Prune Spikey for better growth.', 0, NULL, 1, 2, 2);
INSERT INTO `reminder` (`id`, `created_at`, `reminder_date`, `title`, `notes`, `completed`, `image_url`, `enabled`, `user_plant_id`, `care_type_id`) VALUES (3, NULL, NULL, 'Feralize Ruby', 'Fertilize Ruby with houseplant food.', 0, NULL, 1, 1, 3);
INSERT INTO `reminder` (`id`, `created_at`, `reminder_date`, `title`, `notes`, `completed`, `image_url`, `enabled`, `user_plant_id`, `care_type_id`) VALUES (4, NULL, NULL, 'Clean Spikey', 'Clean dust off Spikey’s leaves.', 0, NULL, 1, 2, 6);
INSERT INTO `reminder` (`id`, `created_at`, `reminder_date`, `title`, `notes`, `completed`, `image_url`, `enabled`, `user_plant_id`, `care_type_id`) VALUES (5, NULL, NULL, 'Ruby needs new light', 'Move Ruby to sunnier spot for better light.', 0, NULL, 1, 1, 7);

COMMIT;


-- -----------------------------------------------------
-- Data for table `species_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `soilmatesdb`;
INSERT INTO `species_comment` (`id`, `created_at`, `updated_at`, `comment`, `image_url`, `enabled`, `plant_species_id`, `user_id`, `in_reply_to_id`) VALUES (1, NULL, NULL, 'Ficus elastica is super resilient!', NULL, 1, 1, 1, NULL);
INSERT INTO `species_comment` (`id`, `created_at`, `updated_at`, `comment`, `image_url`, `enabled`, `plant_species_id`, `user_id`, `in_reply_to_id`) VALUES (2, NULL, NULL, 'Snake Plants are basically immortal. Perfect for beginners.', NULL, 1, 2, 2, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plant_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `soilmatesdb`;
INSERT INTO `plant_comment` (`id`, `created_at`, `updated_at`, `comment`, `image_url`, `enabled`, `user_id`, `in_reply_to_id`, `user_plant_id`) VALUES (1, NULL, NULL, 'Ruby looks super healthy, great job!', NULL, 1, 2, NULL, 1);
INSERT INTO `plant_comment` (`id`, `created_at`, `updated_at`, `comment`, `image_url`, `enabled`, `user_id`, `in_reply_to_id`, `user_plant_id`) VALUES (2, NULL, NULL, 'Thanks! I’ve been misting her every morning.', NULL, 1, 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plant_collection`
-- -----------------------------------------------------
START TRANSACTION;
USE `soilmatesdb`;
INSERT INTO `plant_collection` (`id`, `name`, `description`, `image_url`, `created_at`, `updated_at`, `user_id`) VALUES (1, 'Sunny Squad', 'Plants that thrive in bright light.', NULL, NULL, NULL, 1);
INSERT INTO `plant_collection` (`id`, `name`, `description`, `image_url`, `created_at`, `updated_at`, `user_id`) VALUES (2, 'Shady Corner Crew', 'Low-light tolerant plants grouped together.', NULL, NULL, NULL, 1);
INSERT INTO `plant_collection` (`id`, `name`, `description`, `image_url`, `created_at`, `updated_at`, `user_id`) VALUES (3, 'Kitchen Greens', 'Plants adding life to the kitchen area.', NULL, NULL, NULL, 2);
INSERT INTO `plant_collection` (`id`, `name`, `description`, `image_url`, `created_at`, `updated_at`, `user_id`) VALUES (4, 'Healing Herbs', 'Useful and medicinal plants I rely on.', NULL, NULL, NULL, 2);
INSERT INTO `plant_collection` (`id`, `name`, `description`, `image_url`, `created_at`, `updated_at`, `user_id`) VALUES (5, 'Mood Boosters', 'Plants that make me feel good.', NULL, NULL, NULL, 2);
INSERT INTO `plant_collection` (`id`, `name`, `description`, `image_url`, `created_at`, `updated_at`, `user_id`) VALUES (DEFAULT, NULL, NULL, NULL, NULL, NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `collection_has_plant`
-- -----------------------------------------------------
START TRANSACTION;
USE `soilmatesdb`;
INSERT INTO `collection_has_plant` (`plant_collection_id`, `user_plant_id`) VALUES (1, 5);
INSERT INTO `collection_has_plant` (`plant_collection_id`, `user_plant_id`) VALUES (2, 3);
INSERT INTO `collection_has_plant` (`plant_collection_id`, `user_plant_id`) VALUES (3, 4);
INSERT INTO `collection_has_plant` (`plant_collection_id`, `user_plant_id`) VALUES (4, 2);
INSERT INTO `collection_has_plant` (`plant_collection_id`, `user_plant_id`) VALUES (5, 1);

COMMIT;

