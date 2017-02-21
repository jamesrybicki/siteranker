SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema siteranker_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `siteranker_db` ;

-- -----------------------------------------------------
-- Schema siteranker_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `siteranker_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `siteranker_db` ;

-- -----------------------------------------------------
-- Table `siteranker_db`.`site`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siteranker_db`.`site` (
  `id` BINARY(16) NOT NULL,
  `version` BIGINT(20) NOT NULL,
  `date_created` DATETIME NULL,
  `last_updated` DATETIME NULL,
  `url` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL,
  `period_end_date` DATE NOT NULL,
  `total_visits` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idx_site_url_period_uniq` (`url` ASC, `period_end_date` ASC),
  INDEX `idx_site_url` (`url` ASC),
  INDEX `idx_site_period_end_date` (`url` ASC),
  INDEX `idx_site_total_visits` (`total_visits` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
