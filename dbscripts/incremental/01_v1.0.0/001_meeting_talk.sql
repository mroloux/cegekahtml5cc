CREATE  TABLE IF NOT EXISTS `Meeting` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `venue` VARCHAR(64) NOT NULL ,
  `title` VARCHAR(128) NOT NULL ,
  `startDateTime` DATETIME NULL ,
  `endDateTime` DATETIME NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `Talk` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `speaker` VARCHAR(64) NOT NULL ,
  `subject` VARCHAR(128) NOT NULL ,
  `location` VARCHAR(64) NOT NULL ,
  `from` DATETIME NULL ,
  `till` DATETIME NULL ,
  `summary` VARCHAR(300) NULL ,
  `objective` VARCHAR(300) NULL ,
  `Meeting_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Talk_Meeting_idx` (`Meeting_id` ASC) ,
  CONSTRAINT `fk_Talk_Meeting`
    FOREIGN KEY (`Meeting_id` )
    REFERENCES `Meeting` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;