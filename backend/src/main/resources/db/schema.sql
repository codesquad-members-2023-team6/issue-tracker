SET
    @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET
    @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET
    @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema issue_tracker_schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `issue_tracker_schema` DEFAULT CHARACTER SET utf8;
USE
    `issue_tracker_schema`;

-- -----------------------------------------------------
-- Table `issue_tracker_schema`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `issue_tracker_schema`.`member`
(
    `member_idx`        BIGINT       NOT NULL AUTO_INCREMENT,
    `id`                VARCHAR(32)  NOT NULL,
    `password`          VARCHAR(24)  NOT NULL,
    `profile_image_url` VARCHAR(200) NULL DEFAULT NULL,
    `github_join`       boolean      NOT NULL,
    PRIMARY KEY (`member_idx`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
) ENGINE = InnoDB;
#데이터베이스 테이블이 저장될 때 사용되는 저장 엔진


-- -----------------------------------------------------
-- Table `issue_tracker_schema`.`label`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `issue_tracker_schema`.`label`
(
    `label_idx`        BIGINT        NOT NULL AUTO_INCREMENT,
    `title`            VARCHAR(1000) NOT NULL,
    `description`      VARCHAR(1000) NULL     DEFAULT NULL,
    `background_color` VARCHAR(40)   NOT NULL,
    `text_color`       VARCHAR(40)   NOT NULL,
    `is_deleted`       boolean       NOT NULL default false,
    PRIMARY KEY (`label_idx`)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `issue_tracker_schema`.`milestone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `milestone`;
CREATE TABLE `issue_tracker_schema`.`milestone` (
      `milestone_idx` BIGINT NOT NULL AUTO_INCREMENT,
      `title` VARCHAR(1000) NOT NULL,
      `is_open` boolean NOT NULL,
      `ended_at` DATETIME NULL DEFAULT NULL,
      `contents` VARCHAR(1000) NULL DEFAULT NULL,
      `is_deleted` boolean NOT NULL,
      PRIMARY KEY (`milestone_idx`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `issue_tracker_schema`.`issue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `issue`;
CREATE TABLE `issue_tracker_schema`.`issue`
(
    `issue_idx`     BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `title`         VARCHAR(100) NOT NULL,
    `contents`      TEXT(30000)  NOT NULL,
    `is_open`       boolean      NOT NULL default true,
    `created_at`    DATETIME     NOT NULL,
    `edited_at`     DATETIME     NULL     DEFAULT NULL,
    `milestone_idx` BIGINT       NULL,
    writer          BIGINT       NOT NULL,
    assignee        BIGINT       NOT NULL,
    `is_deleted`    boolean      NOT NULL,
    INDEX `fk_issue_milestone1_idx` (`milestone_idx` ASC) VISIBLE,
    INDEX `fk_issue_writer_idx` (writer ASC) VISIBLE,
    INDEX `fk_issue_assignee_idx` (assignee ASC) VISIBLE,
    CONSTRAINT `fk_issue_milestone`
        FOREIGN KEY (`milestone_idx`)
            REFERENCES `issue_tracker_schema`.`milestone` (`milestone_idx`),
    CONSTRAINT `fk_issue_member_writer`
        FOREIGN KEY (writer)
            REFERENCES `issue_tracker_schema`.`member` (`member_idx`),
    CONSTRAINT `fk_issue_member_assignee`
        FOREIGN KEY (assignee)
            REFERENCES `issue_tracker_schema`.`member` (`member_idx`)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `issue_tracker_schema`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `issue_tracker_schema`.`comment`
(
    `comment_idx` BIGINT      NOT NULL AUTO_INCREMENT,
    `contents`    TEXT(10000) NOT NULL,
    `created_at`  DATETIME    NOT NULL,
    edited_at     DATETIME,
    is_open       boolean     NOT NULL default true,
    issue_idx     BIGINT      NOT NULL,
    created_by    BIGINT      NOT NULL,
    PRIMARY KEY (`comment_idx`, issue_idx, created_by),
    INDEX `fk_comment_issue_idx` (issue_idx ASC) VISIBLE,
    INDEX `fk_comment_member_idx` (created_by ASC) VISIBLE,
    CONSTRAINT `fk_comment_issue2`
        FOREIGN KEY (issue_idx)
            REFERENCES `issue_tracker_schema`.`issue` (`issue_idx`),
    CONSTRAINT `fk_comment_member2`
        FOREIGN KEY (created_by)
            REFERENCES `issue_tracker_schema`.`member` (`member_idx`)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `issue_tracker_schema`.`labeling`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `labeling`;
CREATE TABLE `issue_tracker_schema`.`labeling`
(
    `labeling_idx` BIGINT NOT NULL AUTO_INCREMENT,
    `issue_idx`    BIGINT NOT NULL,
    `label_idx`    BIGINT NOT NULL,
    PRIMARY KEY (`labeling_idx`),
    INDEX `fk_issue_has_label_label1_idx` (`label_idx` ASC) VISIBLE,
    INDEX `fk_issue_has_label_issue1_idx` (`issue_idx` ASC) VISIBLE,
    UNIQUE INDEX `labeling_idx_UNIQUE` (`labeling_idx` ASC) VISIBLE,
    CONSTRAINT `fk_issue_has_label_issue1`
        FOREIGN KEY (`issue_idx`)
            REFERENCES `issue_tracker_schema`.`issue` (`issue_idx`),
    CONSTRAINT `fk_issue_has_label_label1`
        FOREIGN KEY (`label_idx`)
            REFERENCES `issue_tracker_schema`.`label` (`label_idx`)
) ENGINE = InnoDB;


SET
    SQL_MODE = @OLD_SQL_MODE;
SET
    FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET
    UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
