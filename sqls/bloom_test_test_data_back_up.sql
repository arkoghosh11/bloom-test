/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.21 : Database - bloom
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = '' */;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS */`bloom` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `bloom`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `address_id`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `address1`     VARCHAR(200) NOT NULL DEFAULT 'Default',
  `address2`     VARCHAR(200) NOT NULL DEFAULT 'Default',
  `city`         VARCHAR(100) NOT NULL DEFAULT 'Default',
  `district`     VARCHAR(100) NOT NULL DEFAULT 'Default',
  `state`        VARCHAR(100) NOT NULL DEFAULT 'Default',
  `zipcode`      INT(11)      NOT NULL DEFAULT '0',
  `created_date` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `location_id`  BIGINT(20)            DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `FK_address_locations_location_id` (`location_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1409027
  DEFAULT CHARSET = latin1;

/*Data for the table `address` */

INSERT INTO `address` (`address_id`, `address1`, `address2`, `city`, `district`, `state`, `zipcode`, `created_date`, `updated_date`, `location_id`)
VALUES (0, 'Default', 'Default', 'Default', 'Default', 'Default', 0, '2015-02-28 01:32:36', '2015-02-28 01:32:36', 0),
  (1, 'test', 'test', 'test_city', 'test_district', 'test_state', 99999, '2015-02-28 01:32:36', '2015-03-04 01:23:35',
   1);

/*Table structure for table `astrals` */

DROP TABLE IF EXISTS `astrals`;

CREATE TABLE `astrals` (
  `astral_id`        BIGINT(20) NOT NULL AUTO_INCREMENT,
  `astral_attribute` VARCHAR(255)        DEFAULT NULL,
  `astral_color`     VARCHAR(255)        DEFAULT NULL,
  `astral_level`     INT(11)             DEFAULT NULL,
  `astral_name`      VARCHAR(255)        DEFAULT NULL,
  `created_date`     DATETIME            DEFAULT NULL,
  `updated_date`     DATETIME            DEFAULT NULL,
  `player_id`        BIGINT(20)          DEFAULT NULL,
  PRIMARY KEY (`astral_id`),
  KEY `FKD611B5787AFAD337` (`player_id`),
  CONSTRAINT `FKD611B5787AFAD337` FOREIGN KEY (`player_id`) REFERENCES `players` (`player_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `astrals` */

/*Table structure for table `cards` */

DROP TABLE IF EXISTS `cards`;

CREATE TABLE `cards` (
  `discriminator` VARCHAR(31)  NOT NULL,
  `card_id`       VARCHAR(255) NOT NULL,
  `has_pic`       TINYINT(1)   DEFAULT NULL,
  `card_type`     VARCHAR(255) DEFAULT NULL,
  `first_name`    VARCHAR(255) DEFAULT NULL,
  `issue_date`    VARCHAR(255) DEFAULT NULL,
  `last_name`     VARCHAR(255) DEFAULT NULL,
  `code`          VARCHAR(255) DEFAULT NULL,
  `card_number`   VARCHAR(255) DEFAULT NULL,
  `modification`  DATETIME     DEFAULT NULL,
  `exp_date`      VARCHAR(255) DEFAULT NULL,
  `middle_name`   VARCHAR(255) DEFAULT NULL,
  `customer_id`   VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`card_id`),
  KEY `FK5A0E7634D5F4A89` (`customer_id`),
  CONSTRAINT `FK5A0E7634D5F4A89` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `cards` */

/*Table structure for table `clothes` */

DROP TABLE IF EXISTS `clothes`;

CREATE TABLE `clothes` (
  `cloth_id`     BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `cloth_level`  INT(11)      NOT NULL,
  `cloth_name`   VARCHAR(255) NOT NULL,
  `cloth_type`   VARCHAR(255) NOT NULL,
  `created_date` DATETIME              DEFAULT NULL,
  `is_wing`      TINYINT(1)   NOT NULL,
  `updated_date` DATETIME              DEFAULT NULL,
  `player_id`    BIGINT(20)   NOT NULL,
  PRIMARY KEY (`cloth_id`),
  KEY `FK33A6CC487AFAD337` (`player_id`),
  CONSTRAINT `FK33A6CC487AFAD337` FOREIGN KEY (`player_id`) REFERENCES `players` (`player_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `clothes` */

/*Table structure for table `customer_preference` */

DROP TABLE IF EXISTS `customer_preference`;

CREATE TABLE `customer_preference` (
  `preference_id` VARCHAR(255) NOT NULL,
  `user_id`       VARCHAR(255) NOT NULL,
  KEY `FK64319BDCAE39BB7C` (`user_id`),
  KEY `FK64319BDC351BA5A9` (`preference_id`),
  CONSTRAINT `FK64319BDC351BA5A9` FOREIGN KEY (`preference_id`) REFERENCES `preferences` (`preference_id`),
  CONSTRAINT `FK64319BDCAE39BB7C` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `customer_preference` */

/*Table structure for table `dungeons` */

DROP TABLE IF EXISTS `dungeons`;

CREATE TABLE `dungeons` (
  `dungeon_id`              BIGINT(20) NOT NULL AUTO_INCREMENT,
  `created_date`            DATETIME            DEFAULT NULL,
  `dungeon_level_completed` INT(11)             DEFAULT NULL,
  `dungeon_max_level`       INT(11)             DEFAULT NULL,
  `dungeon_name`            VARCHAR(255)        DEFAULT NULL,
  `dungeon_type`            INT(11)             DEFAULT NULL,
  `updated_date`            DATETIME            DEFAULT NULL,
  `player_id`               BIGINT(20)          DEFAULT NULL,
  PRIMARY KEY (`dungeon_id`),
  KEY `FK82BEBCF97AFAD337` (`player_id`),
  CONSTRAINT `FK82BEBCF97AFAD337` FOREIGN KEY (`player_id`) REFERENCES `players` (`player_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `dungeons` */

/*Table structure for table `equipments` */

DROP TABLE IF EXISTS `equipments`;

CREATE TABLE `equipments` (
  `equipment_id`     BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `created_date`     DATETIME              DEFAULT NULL,
  `equip_level`      INT(11)      NOT NULL,
  `equip_name`       VARCHAR(255) NOT NULL,
  `equip_stats`      VARCHAR(255) NOT NULL,
  `equip_type`       VARCHAR(255) NOT NULL,
  `is_rage_pve_gear` TINYINT(1)   NOT NULL,
  `updated_date`     DATETIME              DEFAULT NULL,
  `player_id`        BIGINT(20)            DEFAULT NULL,
  PRIMARY KEY (`equipment_id`),
  KEY `FKC4D4CCA57AFAD337` (`player_id`),
  CONSTRAINT `FKC4D4CCA57AFAD337` FOREIGN KEY (`player_id`) REFERENCES `players` (`player_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `equipments` */

/*Table structure for table `guilds` */

DROP TABLE IF EXISTS `guilds`;

CREATE TABLE `guilds` (
  `guildId`            BIGINT(20) NOT NULL AUTO_INCREMENT,
  `createdDate`        DATETIME            DEFAULT NULL,
  `guild_altar`        INT(11)             DEFAULT NULL,
  `guild_level`        INT(11)             DEFAULT NULL,
  `guild_master`       VARCHAR(255)        DEFAULT NULL,
  `guild_member_count` INT(11)             DEFAULT NULL,
  `guild_name`         VARCHAR(255)        DEFAULT NULL,
  `guild_shop`         INT(11)             DEFAULT NULL,
  `guild_skill_tower`  INT(11)             DEFAULT NULL,
  `guild_vault`        INT(11)             DEFAULT NULL,
  `updatedDate`        DATETIME            DEFAULT NULL,
  PRIMARY KEY (`guildId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `guilds` */

/*Table structure for table `hibernate_sequences` */

DROP TABLE IF EXISTS `hibernate_sequences`;

CREATE TABLE `hibernate_sequences` (
  `sequence_name`          VARCHAR(255) DEFAULT NULL,
  `sequence_next_hi_value` INT(11)      DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `hibernate_sequences` */

INSERT INTO `hibernate_sequences` (`sequence_name`, `sequence_next_hi_value`)
VALUES ('items', 55), ('shops', 54), ('address', 44), ('working_hours', 53);

/*Table structure for table `items` */

DROP TABLE IF EXISTS `items`;

CREATE TABLE `items` (
  `item_id`             INT(11)      NOT NULL AUTO_INCREMENT,
  `item_name`           VARCHAR(80)  NOT NULL DEFAULT 'default',
  `item_price`          DOUBLE       NOT NULL DEFAULT '0',
  `item_type`           VARCHAR(100) NOT NULL DEFAULT 'food',
  `item_price_currency` VARCHAR(50)  NOT NULL DEFAULT 'dollar',
  `quantity`            DOUBLE       NOT NULL DEFAULT '0',
  `weight`              DOUBLE       NOT NULL DEFAULT '0',
  `bought_from`         VARCHAR(100)          DEFAULT 'Unknown',
  `quantity_type`       VARCHAR(50)  NOT NULL DEFAULT 'unit',
  `item_sub_type`       VARCHAR(255)          DEFAULT 'N/A',
  `weighted_unit`       VARCHAR(255) NOT NULL DEFAULT 'kg',
  `bought_date`         TIMESTAMP    NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_date`        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date`        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `shop_id`             BIGINT(20)   NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FK_items_shops_shop_id` (`shop_id`),
  CONSTRAINT `FK_items_shops_shop_id` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`shop_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1769477
  DEFAULT CHARSET = latin1;

/*Data for the table `items` */

INSERT INTO `items` (`item_id`, `item_name`, `item_price`, `item_type`, `item_price_currency`, `quantity`, `weight`, `bought_from`, `quantity_type`, `item_sub_type`, `weighted_unit`, `bought_date`, `created_date`, `updated_date`, `shop_id`)
VALUES
  (0, 'default_name', 0, 'default_type', 'Dollar', 0, 0, 'default', 'unit', 'default_type', 'kg', '2015-02-25 01:00:00',
   '2015-01-29 04:45:31', '2015-03-14 15:04:20', 0),
  (1, 'test', 2, 'test_type', 'Rupee', 1, 1, 'test', 'unit', 'test', 'pound', '2015-02-25 01:00:00',
   '2015-01-29 04:50:08', '2015-02-28 01:13:49', 1);

/*Table structure for table `locations` */

DROP TABLE IF EXISTS `locations`;

CREATE TABLE `locations` (
  `location_id`  BIGINT(20) NOT NULL AUTO_INCREMENT,
  `latitude`     DOUBLE              DEFAULT '0',
  `longitude`    DOUBLE              DEFAULT '0',
  `locationId`   BIGINT(20) NOT NULL,
  `created_date` DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`location_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1
  COLLATE = latin1_general_ci;

/*Data for the table `locations` */

INSERT INTO `locations` (`location_id`, `latitude`, `longitude`, `locationId`, `created_date`, `updated_date`)
VALUES (0, 1, 1, 0, '0000-00-00 00:00:00', '2015-02-28 01:17:23'),
  (1, 1, 1, 0, '0000-00-00 00:00:00', '2015-03-03 18:48:48');

/*Table structure for table `mounts` */

DROP TABLE IF EXISTS `mounts`;

CREATE TABLE `mounts` (
  `mount_id`             BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `created_date`         DATETIME     NOT NULL,
  `mount_names`          VARCHAR(255) NOT NULL,
  `number_of_mounts`     INT(11)      NOT NULL,
  `total_mount_strength` INT(11)      NOT NULL,
  `updated_date`         DATETIME              DEFAULT NULL,
  `player_id`            BIGINT(20)   NOT NULL,
  PRIMARY KEY (`mount_id`),
  KEY `FKC053477A7AFAD337` (`player_id`),
  CONSTRAINT `FKC053477A7AFAD337` FOREIGN KEY (`player_id`) REFERENCES `players` (`player_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `mounts` */

/*Table structure for table `phones` */

DROP TABLE IF EXISTS `phones`;

CREATE TABLE `phones` (
  `phone_id`      VARCHAR(255) NOT NULL,
  `phone_carrier` VARCHAR(255) DEFAULT NULL,
  `phone_model`   VARCHAR(255) DEFAULT NULL,
  `phone_number`  VARCHAR(255) NOT NULL,
  `phone_type`    VARCHAR(255) DEFAULT NULL,
  `customer_id`   VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`phone_id`),
  KEY `FKC50C70C54D5F4A89` (`customer_id`),
  CONSTRAINT `FKC50C70C54D5F4A89` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `phones` */

/*Table structure for table `players` */

DROP TABLE IF EXISTS `players`;

CREATE TABLE `players` (
  `player_id`            BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `class_type`           VARCHAR(255) NOT NULL,
  `created_date`         DATETIME              DEFAULT NULL,
  `facebook_profile_url` VARCHAR(255) NOT NULL,
  `gender`               VARCHAR(255) NOT NULL,
  `is_guild_master`      TINYINT(1)   NOT NULL,
  `guild_role`           VARCHAR(255) NOT NULL,
  `level`                INT(11)      NOT NULL,
  `other`                VARCHAR(255)          DEFAULT NULL,
  `player_name`          VARCHAR(255) NOT NULL,
  `previous_guild_name`  VARCHAR(255)          DEFAULT NULL,
  `updated_date`         DATETIME              DEFAULT NULL,
  `guild_id`             BIGINT(20)            DEFAULT NULL,
  PRIMARY KEY (`player_id`),
  KEY `FKE294C1B2EAC2981D` (`guild_id`),
  CONSTRAINT `FKE294C1B2EAC2981D` FOREIGN KEY (`guild_id`) REFERENCES `guilds` (`guildId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `players` */

/*Table structure for table `preferences` */

DROP TABLE IF EXISTS `preferences`;

CREATE TABLE `preferences` (
  `preference_id`   VARCHAR(255) NOT NULL,
  `is_preferred`    TINYINT(1)   DEFAULT NULL,
  `preference_name` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`preference_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `preferences` */

/*Table structure for table `shops` */

DROP TABLE IF EXISTS `shops`;

CREATE TABLE `shops` (
  `shop_id`       BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `shop_main_id`  BIGINT(20)   NOT NULL,
  `shop_name`     VARCHAR(100) NOT NULL,
  `shop_web_link` VARCHAR(100) NOT NULL,
  `created_date`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `address_id`    BIGINT(20)   NOT NULL,
  PRIMARY KEY (`shop_id`),
  UNIQUE KEY `FK_shops_address_address_id` (`address_id`),
  CONSTRAINT `FK_shops_address_address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1736707
  DEFAULT CHARSET = latin1;

/*Data for the table `shops` */

INSERT INTO `shops` (`shop_id`, `shop_main_id`, `shop_name`, `shop_web_link`, `created_date`, `updated_date`, `address_id`)
VALUES (0, 0, 'default', 'default', '2015-02-28 01:26:34', '2015-02-28 01:26:34', 0),
  (1, 1111, 'test', 'test', '2015-02-28 01:26:34', '2015-02-28 01:26:34', 1);

/*Table structure for table `tabs` */

DROP TABLE IF EXISTS `tabs`;

CREATE TABLE `tabs` (
  `tab_id`      INT(11) NOT NULL,
  `tab_content` VARCHAR(255) DEFAULT NULL,
  `tab_name`    VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`tab_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `tabs` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `discriminator` VARCHAR(31)  NOT NULL,
  `user_id`       VARCHAR(255) NOT NULL,
  `email`         VARCHAR(255) NOT NULL,
  `password`      VARCHAR(20)  NOT NULL,
  `user_name`     VARCHAR(20)  NOT NULL,
  `first_name`    VARCHAR(110) DEFAULT NULL,
  `last_name`     VARCHAR(100) DEFAULT NULL,
  `middle_name`   VARCHAR(100) DEFAULT NULL,
  `title`         VARCHAR(10)  DEFAULT NULL,
  `address_id`    BIGINT(20)   NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `FK_user_address_id` (`address_id`),
  CONSTRAINT `FK_user_address_address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `users` */

/*Table structure for table `working_hours` */

DROP TABLE IF EXISTS `working_hours`;

CREATE TABLE `working_hours` (
  `working_hour_id`   BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `day_of_week`       VARCHAR(10) NOT NULL DEFAULT 'Monday',
  `open_time_of_day`  TIME        NOT NULL DEFAULT '07:00:00',
  `close_time_of_day` TIME        NOT NULL DEFAULT '20:00:00',
  `is_closed`         TINYINT(2)  NOT NULL DEFAULT '0',
  `is_holiday`        TINYINT(2)  NOT NULL DEFAULT '0',
  `is_weekend`        TINYINT(2)  NOT NULL DEFAULT '0',
  `shop_id`           BIGINT(20)  NOT NULL,
  `created_date`      TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date`      TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`working_hour_id`),
  KEY `FK_working_hours_shops_shop_id` (`shop_id`),
  CONSTRAINT `FK_working_hours_shops_shop_id` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`shop_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1703938
  DEFAULT CHARSET = latin1;

/*Data for the table `working_hours` */

INSERT INTO `working_hours` (`working_hour_id`, `day_of_week`, `open_time_of_day`, `close_time_of_day`, `is_closed`, `is_holiday`, `is_weekend`, `shop_id`, `created_date`, `updated_date`)
VALUES (0, 'Monday', '00:00:00', '20:00:00', 0, 0, 0, 0, '2015-02-28 01:34:33', '2015-02-28 01:36:10'),
  (1, 'test', '00:00:00', '11:59:00', 1, 1, 1, 1, '2015-02-28 01:35:28', '2015-03-08 03:11:36');

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
