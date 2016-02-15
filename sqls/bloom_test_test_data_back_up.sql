/*
SQLyog Ultimate v12.09 (64 bit)
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
  `created_date` TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP,
  `updated_date` TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `location_id`  BIGINT(20)            DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `FK_address_locations_location_id` (`location_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1998850
  DEFAULT CHARSET = latin1;

/*Data for the table `address` */

INSERT INTO `address` (`address_id`, `address1`, `address2`, `city`, `district`, `state`, `zipcode`, `created_date`, `updated_date`, `location_id`)
VALUES (0, 'Default', 'Default', 'Default', 'Default', 'Default', 0, '2015-02-28 00:32:36', '2015-02-28 00:32:36', 0),
  (1, 'test', 'test', 'test_city', 'test_district', 'test_state', 99999, '2015-02-28 00:32:36', '2015-03-04 00:23:35',
   1);

/*Table structure for table `calendar_events` */

DROP TABLE IF EXISTS `calendar_events`;

CREATE TABLE `calendar_events` (
  `calendar_event_id` BIGINT(20)   NOT NULL,
  `event_description` VARCHAR(255)          DEFAULT NULL,
  `event_name`        VARCHAR(150) NOT NULL,
  `event_start_date`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `event_end_date`    TIMESTAMP    NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_by`        VARCHAR(150) NOT NULL DEFAULT 'bloom',
  `optional`          VARCHAR(255)          DEFAULT NULL,
  `created_date`      TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP,
  `updated_date`      TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`calendar_event_id`),
  UNIQUE KEY `event_name` (`event_name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `calendar_events` */

INSERT INTO `calendar_events` (`calendar_event_id`, `event_description`, `event_name`, `event_start_date`, `event_end_date`, `created_by`, `optional`, `created_date`, `updated_date`)
VALUES
  (0, 'default', 'default', '2015-12-30 03:45:31', '2015-01-29 03:45:31', 'bloom', 'optional', '2015-05-02 15:52:42',
   '2015-05-02 15:52:42'),
  (1, 'test', 'test', '2015-10-15 15:45:31', '2015-01-25 08:45:31', 'bloom', 'optional', '2015-05-02 15:53:50',
   '2015-05-02 15:53:50');

/*Table structure for table `cards` */

DROP TABLE IF EXISTS `cards`;

CREATE TABLE `cards` (
  `discriminator`    VARCHAR(31)  NOT NULL,
  `card_id`          BIGINT(20)   NOT NULL,
  `first_name`       VARCHAR(255) NOT NULL,
  `last_name`        VARCHAR(255) NOT NULL,
  `card_number`      VARCHAR(255) NOT NULL,
  `issue_date`       VARCHAR(255) DEFAULT NULL,
  `has_picture`      TINYINT(1)   DEFAULT '0',
  `picture_location` VARCHAR(255) DEFAULT NULL,
  `middle_name`      VARCHAR(255) DEFAULT NULL,
  `cvv_code`         INT(11)      DEFAULT NULL,
  `card_type`        VARCHAR(255) DEFAULT NULL,
  `modification`     DATETIME     DEFAULT NULL,
  `expiry_date`      VARCHAR(255) DEFAULT NULL,
  `created_date`     DATETIME     DEFAULT CURRENT_TIMESTAMP,
  `updated_date`     DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `customer_id`      BIGINT(20)   DEFAULT NULL,
  `code`             VARCHAR(255) DEFAULT NULL,
  `exp_date`         VARCHAR(255) DEFAULT NULL,
  `has_pic`          TINYINT(1)   DEFAULT NULL,
  PRIMARY KEY (`card_id`),
  UNIQUE KEY `card_number` (`card_number`, `customer_id`),
  KEY `FK5A0E763189EC4A7` (`customer_id`),
  CONSTRAINT `FK5A0E763189EC4A7` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `cards` */

INSERT INTO `cards` (`discriminator`, `card_id`, `first_name`, `last_name`, `card_number`, `issue_date`, `has_picture`, `picture_location`, `middle_name`, `cvv_code`, `card_type`, `modification`, `expiry_date`, `created_date`, `updated_date`, `customer_id`, `code`, `exp_date`, `has_pic`)
VALUES ('Card', 0, 'default', 'default', '4532296367907972', '01:10', 0, '', NULL, NULL, NULL, NULL, NULL,
        '2015-05-01 10:33:43', '2015-05-03 00:45:22', NULL, NULL, NULL, NULL),
  ('CreditCard', 1, 'test', 'test', '5558871425828877', '01:14', 0, ' ', '', 231, 'Master Card', NULL, '10:20',
   '2015-05-01 10:35:53', '2015-05-01 10:54:11', 1, NULL, NULL, NULL),
  ('CreditCard', 2, 'test1', 'test1', '6011885685194704', '02:14', 1, 'C:\\Resources\\photos', 'test', 212, 'Discover',
   NULL, '05:19', '2015-05-01 10:52:04', '2015-05-01 10:53:24', 1, NULL, NULL, NULL);

/*Table structure for table `custom_events` */

DROP TABLE IF EXISTS `custom_events`;

CREATE TABLE `custom_events` (
  `custom_event_id`     BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `receivers`           VARCHAR(255) NOT NULL
  COMMENT 'email receiver',
  `cc_receivers`        VARCHAR(255) NOT NULL
  COMMENT 'email cc',
  `bcc_receivers`       VARCHAR(255) NOT NULL DEFAULT 'arkoghosh@hotmail.com,arkogh@gmail.com'
  COMMENT 'email bcc',
  `subject`             VARCHAR(255) NOT NULL,
  `body`                VARCHAR(255) NOT NULL,
  `has_attachment`      TINYINT(1)            DEFAULT NULL,
  `attachment_location` VARCHAR(255)          DEFAULT NULL,
  `event_date`          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `event_name`          VARCHAR(255)          DEFAULT NULL,
  `use_scheduler`       TINYINT(1)            DEFAULT NULL,
  `optional_data`       VARCHAR(255)          DEFAULT NULL,
  `time_of_event`       VARCHAR(255) NOT NULL,
  PRIMARY KEY (`custom_event_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = latin1;

/*Data for the table `custom_events` */

INSERT INTO `custom_events` (`custom_event_id`, `receivers`, `cc_receivers`, `bcc_receivers`, `subject`, `body`, `has_attachment`, `attachment_location`, `event_date`, `event_name`, `use_scheduler`, `optional_data`, `time_of_event`)
VALUES (0, 'meankur2@gmail.com', '', 'arkoghosh@hotmail.com', 'default1', 'default1', 0, NULL, '2015-04-14 23:12:51',
        'default1', 0, NULL, '00:00'),
  (1, 'meankur2@gmail.com', 'bloomrono11@gmail.com', 'arkoghosh@hotmail.com,arkogh@gmail.com', 'Test', 'Test', 1,
   'C:/Properties/resources/pictures/happy_birthday_froggie_card.jpg', '2015-04-20 22:13:53', 'test', 0, 'Test',
   '23:13'), (2, 'meankur2@gmail.com', 'ronobloom11@gmail.com', 'arkoghosh@hotmail.com,arkogh@gmail.com', 'Hello Test',
              'Hello Test', 1,
              'C:/Properties/resources/pictures/happy_birthday_cartoon_card.jpg,C:/Properties/resources/pictures/happy_birthday_froggie_card.jpg',
              '2015-04-20 22:13:58', 'hello', 0, 'Hello', '23:13'),
  (3, 'ronobloom11@gmail.com', '', 'arkoghosh@hotmail.com,arkogh@gmail.com', 'Test2', 'Body', 0, NULL,
   '2015-04-20 22:14:05', 'test2', 0, 'Test2', '23:14'),
  (4, 'ruchij.mhs@gmail.com', '', 'arkoghosh@hotmail.com', 'Happy Birthday',
   '\nHello Ruchi,\n\n\n\nHappy Birthday to you from your friends and Froggie.\n\nMany Happy returns of the day.\n\n\nYours faithfully,\nFroggie',
   1,
   'C:/Properties/resources/pictures/happy_birthday_cartoon_card.jpg,C:/Properties/resources/pictures/happy_birthday_froggie_card.jpg,C:/Properties/resources/pictures/RJ_froggie_sleeping.jpg',
   '2015-04-20 23:23:01', 'Happy_Birthday_Ruchi_Joshi', 0, 'name:Ruchi_Joshi, BD:04-21-1989', '00:23'),
  (8, 'ronobloom11@gmail.com', '', 'arkoghosh@hotmail.com,arkogh@gmail.com', 'Happy Birthday', 'Happy Birthday', 0,
   NULL, '2015-04-29 14:15:04', 'Happy_Birthday_Deepander_Bagdas', 0, 'name:Deepander Bagdas, BD:04-29-1987', '15:15'),
  (9, 'ronobloom11@gmail.com', '', 'arkoghosh@hotmail.com,arkogh@gmail.com', 'HB', 'HB', 0, NULL, '2015-05-28 20:43:39',
   'HB_WOlfie', 0, 'name: Servetnik Vadim, BD:05-28-1982', '21:43');

/*Table structure for table `customer_address` */

DROP TABLE IF EXISTS `customer_address`;

CREATE TABLE `customer_address` (
  `customer_id` BIGINT(20) NOT NULL,
  `address_id`  BIGINT(20) NOT NULL,
  UNIQUE KEY `customer_address_id` (`customer_id`, `address_id`),
  KEY `FK_address_address_id` (`address_id`),
  KEY `FK_user_customer_id` (`customer_id`),
  KEY `FK41B20493D22D28D1` (`address_id`),
  KEY `FK41B204937DABF78` (`customer_id`),
  CONSTRAINT `FK_address_address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_user_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `customer_address` */

INSERT INTO `customer_address` (`customer_id`, `address_id`) VALUES (1, 0), (1, 1);

/*Table structure for table `customer_preference` */

DROP TABLE IF EXISTS `customer_preference`;

CREATE TABLE `customer_preference` (
  `preference_id` BIGINT(20) NOT NULL,
  `customer_id`   BIGINT(20) NOT NULL,
  UNIQUE KEY `customer_preference_id` (`preference_id`, `customer_id`),
  KEY `FK64319BDC189EC4A7` (`customer_id`),
  KEY `FK64319BDC2E651047` (`preference_id`),
  CONSTRAINT `FK64319BDC189EC4A7` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK64319BDC2E651047` FOREIGN KEY (`preference_id`) REFERENCES `preferences` (`preference_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `customer_preference` */

INSERT INTO `customer_preference` (`preference_id`, `customer_id`) VALUES (0, 1), (1, 1);

/*Table structure for table `gemstones` */

DROP TABLE IF EXISTS `gemstones`;

CREATE TABLE `gemstones` (
  `gemstone_id`          BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `gemstone_name`        VARCHAR(100) NOT NULL,
  `gemstone_description` VARCHAR(256) NOT NULL DEFAULT 'Not Available',
  `created_date`         TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date`         TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`gemstone_id`),
  UNIQUE KEY `UK_gemstone_name` (`gemstone_name`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 262202
  DEFAULT CHARSET = latin1;

/*Data for the table `gemstones` */

INSERT INTO `gemstones` (`gemstone_id`, `gemstone_name`, `gemstone_description`, `created_date`, `updated_date`)
VALUES (0, 'default', 'default', '2016-02-13 19:55:35', '2016-02-13 19:55:44'),
  (1, 'test', 'test', '2016-02-13 15:55:28', '2016-02-13 19:56:52'),
  (2, 'emerald', 'Not Available', '2016-02-13 15:55:36', '2016-02-13 19:56:44'),
  (3, 'rainbow moonlight', 'Not Available', '2016-02-13 15:55:52', '2016-02-13 15:55:52'),
  (4, 'black onix', 'Not Available', '2016-02-13 15:56:04', '2016-02-13 15:56:04'),
  (5, 'ruby', 'Not Available', '2016-02-13 19:56:08', '2016-02-13 19:56:08'),
  (262184, 'AGATE', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262185, 'AMBER', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262186, 'AMETHYST', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262187, 'BLUE SAPPHIRE', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262188, 'CHALCEDONY', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262189, 'CITRINE', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262190, 'CORAL', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262191, 'DRUZY', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262192, 'LABRADORITE', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262193, 'LAPIS', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262194, 'LEMON TOPAZ', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262195, 'MYSTIC TOPAZ', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262196, 'PEARL', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262197, 'ROXK CRUSTAL', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262198, 'SMOKY TOPAZ', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262199, 'STAR RUBY', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262200, 'SUNSTONE', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21'),
  (262201, 'TURQUOISE', 'Not Available', '2016-02-14 21:01:21', '2016-02-14 21:01:21');

/*Table structure for table `greeting` */

DROP TABLE IF EXISTS `greeting`;

CREATE TABLE `greeting` (
  `ID`      BIGINT(20) NOT NULL AUTO_INCREMENT,
  `AUTHOR`  VARCHAR(255)        DEFAULT NULL,
  `CONTENT` VARCHAR(255)        DEFAULT NULL,
  `date`    DATETIME            DEFAULT NULL,
  PRIMARY KEY (`ID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 17
  DEFAULT CHARSET = latin1;

/*Data for the table `greeting` */

INSERT INTO `greeting` (`ID`, `AUTHOR`, `CONTENT`, `date`)
VALUES (1, 'user', 'Hello!', '2015-08-05 15:42:42'), (2, 'user', 'Hi!', '2015-08-05 15:42:42'),
  (3, 'user', 'Hello!', '2015-08-05 15:42:43'), (4, 'user', 'Hi!', '2015-08-05 15:42:43'),
  (5, 'user', 'Hi!', '2015-08-05 15:42:44'), (6, 'user', 'Hello!', '2015-08-05 15:42:44'),
  (7, 'user', 'Hello!', '2015-08-05 15:42:44'), (8, 'user', 'Hi!', '2015-08-05 15:42:44'),
  (9, 'user', 'Hello!', '2015-08-05 15:49:27'), (10, 'user', 'Hi!', '2015-08-05 15:49:27'),
  (11, 'user', 'Hi!', '2015-08-05 15:49:29'), (12, 'user', 'Hello!', '2015-08-05 15:49:29'),
  (13, 'user', 'Hello!', '2015-08-05 15:49:29'), (14, 'user', 'Hi!', '2015-08-05 15:49:29'),
  (15, 'user', 'Hi!', '2015-08-05 15:49:30'), (16, 'user', 'Hello!', '2015-08-05 15:49:30');

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
VALUES ('items', 57), ('shops', 47), ('address', 62), ('working_hours', 49), ('users', 26), ('cards', 28),
  ('phones', 32), ('preferences', 26), ('privileges', 36), ('user_roles', 34), ('Calendar_Events', 27), ('tabs', 27),
  ('item_discounts', 40), ('item_images', 25), ('gemstones', 9);

/*Table structure for table `home` */

DROP TABLE IF EXISTS `home`;

CREATE TABLE `home` (
  `home_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`home_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `home` */

/*Table structure for table `item_discounts` */

DROP TABLE IF EXISTS `item_discounts`;

CREATE TABLE `item_discounts` (
  `item_discount_id` BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `discount_percent` DOUBLE(20, 5)        DEFAULT '0.00000',
  `discount_type`    VARCHAR(256)         DEFAULT NULL,
  `is_active`        TINYINT(1)           DEFAULT '0',
  `start_date`       TIMESTAMP   NULL     DEFAULT '0000-00-00 00:00:00',
  `end_date`         TIMESTAMP   NULL     DEFAULT '0000-00-00 00:00:00',
  `user_role_name`   VARCHAR(20) NOT NULL DEFAULT 'default',
  `created_date`     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date`     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `item_id`          BIGINT(20)           DEFAULT NULL,
  PRIMARY KEY (`item_discount_id`),
  KEY `FK_items_item_dis_item_id` (`item_id`),
  CONSTRAINT `FK_items_item_dis_item_id` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1277958
  DEFAULT CHARSET = latin1;

/*Data for the table `item_discounts` */

INSERT INTO `item_discounts` (`item_discount_id`, `discount_percent`, `discount_type`, `is_active`, `start_date`, `end_date`, `user_role_name`, `created_date`, `updated_date`, `item_id`)
VALUES (0, 10.50000, 'default_type', 0, '2015-03-23 17:22:12', '2015-05-23 17:22:12', 'default', '2015-11-25 21:45:36',
        '2015-11-29 19:17:17', 0),
  (1, 10.50000, 'test', 1, '2015-03-23 16:22:12', '2015-05-23 17:22:12', 'anonymous', '2015-03-23 16:22:12',
   '2015-11-29 19:17:51', 1);

/*Table structure for table `item_gemstones` */

DROP TABLE IF EXISTS `item_gemstones`;

CREATE TABLE `item_gemstones` (
  `item_gemstone_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `item_id`          BIGINT(20) NOT NULL,
  `gemstone_id`      BIGINT(20) NOT NULL,
  PRIMARY KEY (`item_gemstone_id`),
  UNIQUE KEY `uk_item_gemstone` (`item_id`, `gemstone_id`),
  KEY `fk_gemstone_id` (`gemstone_id`),
  CONSTRAINT `fk_gemstone_id` FOREIGN KEY (`gemstone_id`) REFERENCES `gemstones` (`gemstone_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_item_id` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = latin1;

/*Data for the table `item_gemstones` */

INSERT INTO `item_gemstones` (`item_gemstone_id`, `item_id`, `gemstone_id`)
VALUES (0, 0, 0), (4, 1, 3), (3, 2, 2), (1, 3, 1), (2, 4, 1);

/*Table structure for table `item_images` */

DROP TABLE IF EXISTS `item_images`;

CREATE TABLE `item_images` (
  `item_image_id`  BIGINT(20)    NOT NULL AUTO_INCREMENT,
  `image_priority` INT(20) UNSIGNED       DEFAULT '0',
  `image_location` TEXT          NOT NULL,
  `image_height`   DOUBLE(10, 5) NOT NULL DEFAULT '100.00000',
  `image_width`    DOUBLE(10, 5) NOT NULL DEFAULT '100.00000',
  `created_date`   TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date`   TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `item_id`        BIGINT(20)             DEFAULT NULL,
  PRIMARY KEY (`item_image_id`),
  KEY `FK_items_item_img_item_id` (`item_id`),
  CONSTRAINT `FK_items_item_img_item_id` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 786438
  DEFAULT CHARSET = latin1;

/*Data for the table `item_images` */

INSERT INTO `item_images` (`item_image_id`, `image_priority`, `image_location`, `image_height`, `image_width`, `created_date`, `updated_date`, `item_id`)
VALUES (0, 0, 'default', 0.00000, 0.00000, '2015-11-25 21:46:54', '2016-02-10 21:10:36', 0),
  (1, 1, 'D:/Properties/resources/images/default.png', 10.10000, 10.10000, '2015-09-21 18:16:11', '2016-02-10 21:10:39',
   1), (2, 1, 'pictures/C1.jpg', 200.00000, 200.00000, '2016-02-10 20:47:33', '2016-02-10 21:10:46', 3),
  (3, 2, 'pictures/D1.jpg', 200.00000, 200.00000, '2016-02-10 20:47:33', '2016-02-10 21:10:47', 3),
  (4, 3, 'pictures/H1.jpg', 200.00000, 200.00000, '2016-02-10 20:47:33', '2016-02-10 21:10:48', 3),
  (5, 1, 'pictures/J1.jpg', 200.00000, 200.00000, '2016-02-10 20:47:33', '2016-02-10 21:10:40', 1),
  (6, 2, 'pictures/K1.jpg', 200.00000, 200.00000, '2016-02-10 20:47:33', '2016-02-10 21:10:41', 1),
  (7, 3, 'pictures/L1.jpg', 200.00000, 200.00000, '2016-02-10 20:47:33', '2016-02-10 21:10:42', 1),
  (8, 1, 'pictures/P1.jpg', 200.00000, 200.00000, '2016-02-10 20:47:33', '2016-02-10 21:10:44', 2),
  (9, 2, 'pictures/T1.jpg', 200.00000, 200.00000, '2016-02-10 20:47:33', '2016-02-10 21:10:45', 2);

/*Table structure for table `items` */

DROP TABLE IF EXISTS `items`;

CREATE TABLE `items` (
  `item_id`             BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `item_name`           VARCHAR(80)  NOT NULL DEFAULT 'default',
  `item_price`          DOUBLE       NOT NULL DEFAULT '0',
  `item_type`           VARCHAR(100) NOT NULL DEFAULT 'food',
  `item_description`    VARCHAR(256) NOT NULL DEFAULT 'None',
  `item_price_currency` VARCHAR(50)  NOT NULL DEFAULT 'dollar',
  `quantity`            DOUBLE       NOT NULL DEFAULT '0',
  `weight`              DOUBLE       NOT NULL DEFAULT '0',
  `bought_from`         VARCHAR(100)          DEFAULT 'Unknown',
  `quantity_type`       VARCHAR(50)  NOT NULL DEFAULT 'unit',
  `item_sub_type`       VARCHAR(255)          DEFAULT 'N/A',
  `weighted_unit`       VARCHAR(255) NOT NULL DEFAULT 'kg',
  `bought_date`         TIMESTAMP    NOT NULL DEFAULT '0000-00-00 00:00:00',
  `image_count`         INT(20)      NOT NULL DEFAULT '0',
  `item_origin`         VARCHAR(100) NOT NULL DEFAULT 'India',
  `created_date`        TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP,
  `updated_date`        TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `shop_id`             BIGINT(20)   NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FK_items_shops_shop_id` (`shop_id`),
  CONSTRAINT `FK_items_shops_shop_id` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`shop_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = latin1;

/*Data for the table `items` */

INSERT INTO `items` (`item_id`, `item_name`, `item_price`, `item_type`, `item_description`, `item_price_currency`, `quantity`, `weight`, `bought_from`, `quantity_type`, `item_sub_type`, `weighted_unit`, `bought_date`, `image_count`, `item_origin`, `created_date`, `updated_date`, `shop_id`)
VALUES (0, 'default_name', 0, 'default_type', 'None', 'Dollar', 0, 0, 'default', 'unit', 'default_type', 'kg',
        '2015-02-25 01:00:00', 1, 'default', '2015-01-29 03:45:31', '2016-02-13 16:46:40', 0),
  (1, 'test', 2, 'test_type', 'None', 'Rupee', 1, 1, 'test', 'unit', 'test', 'pound', '2015-02-25 01:00:00', 4, 'test',
   '2015-01-29 03:50:08', '2016-02-13 16:46:50', 1),
  (2, 'ruby belt', 200, 'belts', 'ruby belt with silver base', 'dollar', 15, 25, 'Unknown', 'unit', 'ruby', 'kg',
   '0000-00-00 00:00:00', 2, 'India', '2016-02-10 20:18:40', '2016-02-14 19:16:39', 0),
  (3, 'emerald belt', 200, 'belts', 'emerald belt with silver base', 'dollar', 15, 25, 'Unknown', 'unit', 'emerald',
   'kg', '0000-00-00 00:00:00', 3, 'India', '2016-02-10 20:18:40', '2016-02-14 19:16:34', 0),
  (4, 'emerald ring', 10, 'rings', 'emerald ring with silver base hand crafted', 'dollar', 50, 5, 'Unknown', 'unit',
   'emerald', 'kg', '0000-00-00 00:00:00', 0, 'India', '2016-02-13 15:48:27', '2016-02-14 19:15:13', 0);

/*Table structure for table `locations` */

DROP TABLE IF EXISTS `locations`;

CREATE TABLE `locations` (
  `location_id`  BIGINT(20) NOT NULL AUTO_INCREMENT,
  `latitude`     DOUBLE              DEFAULT '0',
  `longitude`    DOUBLE              DEFAULT '0',
  `locationId`   BIGINT(20) NOT NULL,
  `created_date` TIMESTAMP  NULL     DEFAULT CURRENT_TIMESTAMP,
  `updated_date` TIMESTAMP  NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`location_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1
  COLLATE = latin1_general_ci;

/*Data for the table `locations` */

INSERT INTO `locations` (`location_id`, `latitude`, `longitude`, `locationId`, `created_date`, `updated_date`)
VALUES (0, 1, 1, 0, '0000-00-00 00:00:00', '2015-02-28 00:17:23'),
  (1, 1, 1, 0, '0000-00-00 00:00:00', '2015-03-03 17:48:48');

/*Table structure for table `phones` */

DROP TABLE IF EXISTS `phones`;

CREATE TABLE `phones` (
  `phone_id`      BIGINT(20)   NOT NULL,
  `phone_carrier` VARCHAR(255) DEFAULT NULL,
  `phone_model`   VARCHAR(255) DEFAULT NULL,
  `phone_name`    VARCHAR(255) DEFAULT NULL,
  `phone_number`  VARCHAR(255) NOT NULL,
  `phone_type`    VARCHAR(255) DEFAULT NULL,
  `bought_date`   DATETIME     DEFAULT NULL,
  `created_date`  DATETIME     DEFAULT CURRENT_TIMESTAMP,
  `updated_date`  DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `customer_id`   BIGINT(20)   DEFAULT NULL,
  PRIMARY KEY (`phone_id`),
  UNIQUE KEY `phone_number` (`phone_number`),
  KEY `FKC50C70C5189EC4A7` (`customer_id`),
  CONSTRAINT `FKC50C70C5189EC4A7` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `phones` */

INSERT INTO `phones` (`phone_id`, `phone_carrier`, `phone_model`, `phone_name`, `phone_number`, `phone_type`, `bought_date`, `created_date`, `updated_date`, `customer_id`)
VALUES (0, 'default', 'default_model', 'default_name', '+7013451222', 'default_type', '2015-05-01 16:20:25',
        '2015-05-01 16:20:25', '2015-05-02 16:20:35', 1),
  (1, 'test', 'test', 'test', '7033451222', 'test', '2015-05-02 16:21:51', '2015-05-02 16:21:51', '2015-05-02 16:21:51',
   1);

/*Table structure for table `preferences` */

DROP TABLE IF EXISTS `preferences`;

CREATE TABLE `preferences` (
  `preference_id`   BIGINT(20)   NOT NULL,
  `is_preferred`    TINYINT(1)        DEFAULT NULL,
  `preference_name` VARCHAR(255) NOT NULL,
  `created_date`    TIMESTAMP    NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date`    TIMESTAMP    NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`preference_id`),
  UNIQUE KEY `preference_name` (`preference_name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `preferences` */

INSERT INTO `preferences` (`preference_id`, `is_preferred`, `preference_name`, `created_date`, `updated_date`)
VALUES (0, 0, 'default', '2015-05-02 17:40:07', '2015-05-02 17:40:07'),
  (1, 1, 'test', '2015-05-02 17:40:07', '2015-05-02 17:40:07');

/*Table structure for table `privileges` */

DROP TABLE IF EXISTS `privileges`;

CREATE TABLE `privileges` (
  `privilege_id`   INT(10)      NOT NULL AUTO_INCREMENT,
  `privilege_name` VARCHAR(100) NOT NULL DEFAULT 'default',
  `is_accessible`  TINYINT(1)            DEFAULT NULL,
  `created_date`   TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP,
  `updated_date`   TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`privilege_id`),
  UNIQUE KEY `unique_priveledge_name` (`privilege_name`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = latin1;

/*Data for the table `privileges` */

INSERT INTO `privileges` (`privilege_id`, `privilege_name`, `is_accessible`, `created_date`, `updated_date`)
VALUES (0, 'default', 0, '2015-05-14 17:21:54', '2015-05-14 18:06:27'),
  (1, 'create', 1, '2015-05-14 17:21:21', '2015-05-14 17:22:03'),
  (2, 'update', 1, '2015-05-14 17:21:21', '2015-05-14 17:22:04'),
  (3, 'read', 1, '2015-05-14 17:21:21', '2015-05-14 17:22:06'),
  (4, 'delete', 1, '2015-05-14 17:21:21', '2015-05-14 17:21:21'),
  (5, 'all', 1, '2015-05-14 17:21:21', '2015-05-14 17:21:21');

/*Table structure for table `shops` */

DROP TABLE IF EXISTS `shops`;

CREATE TABLE `shops` (
  `shop_id`          BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `shop_main_id`     BIGINT(20)   NOT NULL,
  `shop_name`        VARCHAR(100) NOT NULL,
  `shop_description` VARCHAR(256) NOT NULL DEFAULT 'None',
  `shop_web_link`    VARCHAR(100) NOT NULL,
  `created_date`     TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP,
  `updated_date`     TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `address_id`       BIGINT(20)   NOT NULL,
  PRIMARY KEY (`shop_id`),
  UNIQUE KEY `FK_shops_address_address_id` (`address_id`),
  UNIQUE KEY `UK_shop_main_id` (`shop_main_id`),
  CONSTRAINT `FK_shops_address_address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1507333
  DEFAULT CHARSET = latin1;

/*Data for the table `shops` */

INSERT INTO `shops` (`shop_id`, `shop_main_id`, `shop_name`, `shop_description`, `shop_web_link`, `created_date`, `updated_date`, `address_id`)
VALUES (0, 0, 'default', 'None', 'default data', '2015-02-28 00:26:34', '2016-01-25 00:22:19', 0),
  (1, 1111, 'test', 'None', 'test data', '2015-02-28 00:26:34', '2016-01-25 00:22:10', 1);

/*Table structure for table `sidebar_items` */

DROP TABLE IF EXISTS `sidebar_items`;

CREATE TABLE `sidebar_items` (
  `discriminator`   VARCHAR(31) NOT NULL,
  `sidebar_type_id` BIGINT(20)  NOT NULL,
  `due_date`        DATE         DEFAULT NULL,
  `group_count`     INT(11)      DEFAULT NULL,
  `group_priority`  INT(11)      DEFAULT NULL,
  `group_type`      VARCHAR(255) DEFAULT NULL,
  `item_type`       VARCHAR(255) DEFAULT NULL,
  `subject`         VARCHAR(255) DEFAULT NULL,
  `success`         TINYINT(1)   DEFAULT NULL,
  `type_priority`   INT(11)      DEFAULT NULL,
  `created_date`    DATETIME     DEFAULT CURRENT_TIMESTAMP,
  `updated_date`    DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date`            DATE         DEFAULT NULL,
  PRIMARY KEY (`sidebar_type_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `sidebar_items` */

/*Table structure for table `tabs` */

DROP TABLE IF EXISTS `tabs`;

CREATE TABLE `tabs` (
  `tab_id`       INT(11)      NOT NULL,
  `tab_content`  VARCHAR(255) NOT NULL,
  `tab_name`     VARCHAR(255) NOT NULL,
  `tab_color`    VARCHAR(10)  NOT NULL DEFAULT '#000000',
  `tab_position` VARCHAR(15)  NOT NULL DEFAULT 'top',
  `created_date` DATETIME              DEFAULT CURRENT_TIMESTAMP,
  `updated_date` DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`tab_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `tabs` */

INSERT INTO `tabs` (`tab_id`, `tab_content`, `tab_name`, `tab_color`, `tab_position`, `created_date`, `updated_date`)
VALUES (0, 'default', 'default', '#000000', 'top', '2015-05-02 16:06:43', '2015-05-02 16:10:03'),
  (1, 'test', 'test', '#000000', 'top', '2015-05-02 16:06:50', '2015-05-02 16:06:50');

/*Table structure for table `user_role_privilege` */

DROP TABLE IF EXISTS `user_role_privilege`;

CREATE TABLE `user_role_privilege` (
  `user_role_id` INT(10) NOT NULL,
  `privilege_id` INT(10) NOT NULL,
  UNIQUE KEY `user_role_privilege_id` (`user_role_id`, `privilege_id`),
  KEY `FK_privileges_privilege_id` (`privilege_id`),
  CONSTRAINT `FK_privileges_privilege_id` FOREIGN KEY (`privilege_id`) REFERENCES `privileges` (`privilege_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_user_roles_user_role_id` FOREIGN KEY (`user_role_id`) REFERENCES `user_roles` (`user_role_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `user_role_privilege` */

INSERT INTO `user_role_privilege` (`user_role_id`, `privilege_id`)
VALUES (0, 0), (3, 1), (4, 1), (5, 1), (4, 2), (5, 2), (1, 3), (2, 3), (3, 3), (4, 3), (5, 3), (6, 3), (5, 4), (6, 4),
  (7, 5);

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_role_id`   INT(10)      NOT NULL AUTO_INCREMENT,
  `user_role_name` VARCHAR(100) NOT NULL,
  `is_active`      TINYINT(1)            DEFAULT NULL,
  `is_locked`      TINYINT(1)            DEFAULT NULL,
  `is_expired`     TINYINT(1)            DEFAULT NULL,
  `created_date`   TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP,
  `updated_date`   TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `user_role_name` (`user_role_name`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = latin1;

/*Data for the table `user_roles` */

INSERT INTO `user_roles` (`user_role_id`, `user_role_name`, `is_active`, `is_locked`, `is_expired`, `created_date`, `updated_date`)
VALUES (0, 'default', 0, 0, 0, '2015-05-13 10:12:19', '2015-07-08 07:46:19'),
  (1, 'anonymous', 1, 0, 0, '2015-05-13 10:11:01', '2015-07-08 09:11:47'),
  (2, 'free_user', 1, 0, 0, '2015-05-13 10:11:01', '2015-07-08 07:46:28'),
  (3, 'vip_user', 1, 0, 0, '2015-05-13 10:11:01', '2015-07-08 07:46:31'),
  (4, 'moderator', 1, 0, 0, '2015-05-13 10:11:01', '2015-07-08 07:46:33'),
  (5, 'super_moderator', 1, 0, 0, '2015-05-13 10:11:01', '2015-07-08 07:46:35'),
  (6, 'maintainence_support', 1, 0, 0, '2015-05-13 10:11:01', '2015-07-08 07:46:37'),
  (7, 'admin_super_user', 1, 0, 0, '2015-05-13 10:11:01', '2015-07-08 07:46:43'),
  (8, 'ROLE_USER', 1, 0, 0, '2015-08-29 01:49:20', '2015-11-29 19:37:53'),
  (9, 'ROLE_ADMIN', 1, 0, 0, '2015-08-29 01:49:30', '2015-11-29 19:37:57');

/*Table structure for table `user_roles1` */

DROP TABLE IF EXISTS `user_roles1`;

CREATE TABLE `user_roles1` (
  `user_role_id` INT(11)     NOT NULL AUTO_INCREMENT,
  `username`     VARCHAR(45) NOT NULL,
  `role`         VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`role`, `username`),
  KEY `fk_username_idx` (`username`),
  CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users1` (`username`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = latin1;

/*Data for the table `user_roles1` */

INSERT INTO `user_roles1` (`user_role_id`, `username`, `role`)
VALUES (2, 'mkyong', 'ROLE_ADMIN'), (3, 'alex', 'ROLE_USER'), (1, 'mkyong', 'ROLE_USER');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `discriminator` VARCHAR(31)  NOT NULL,
  `user_id`       BIGINT(20)   NOT NULL,
  `user_name`     VARCHAR(255) NOT NULL,
  `password`      VARCHAR(255) NOT NULL,
  `email`         VARCHAR(255) NOT NULL,
  `first_name`    VARCHAR(255) NOT NULL DEFAULT 'unknown',
  `last_name`     VARCHAR(255) NOT NULL DEFAULT 'unknown',
  `middle_name`   VARCHAR(255)          DEFAULT NULL,
  `title`         VARCHAR(255)          DEFAULT NULL,
  `created_date`  DATETIME              DEFAULT CURRENT_TIMESTAMP,
  `updated_date`  DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_role_id`  INT(10)      NOT NULL,
  `address_id`    BIGINT(20)            DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `email` (`email`),
  KEY `FK_users_user_roles_id` (`user_role_id`),
  KEY `FK6A68E08C16923A2` (`address_id`),
  CONSTRAINT `FK_users_user_roles_id` FOREIGN KEY (`user_role_id`) REFERENCES `user_roles` (`user_role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `users` */

INSERT INTO `users` (`discriminator`, `user_id`, `user_name`, `password`, `email`, `first_name`, `last_name`, `middle_name`, `title`, `created_date`, `updated_date`, `user_role_id`, `address_id`)
VALUES ('User', 0, 'default', 'default', 'default@default.com', 'unknown', 'unknown', NULL, NULL, '2015-05-01 10:23:29',
        '2015-11-29 19:56:37', 0, 0),
  ('Customer', 1, 'test', 'test', 'test@test.com', 'test_f_n', 'test_l_n', 'test_m_n', 'test', '2015-05-01 10:24:59',
   '2015-08-06 18:27:17', 0, 1);

/*Table structure for table `users1` */

DROP TABLE IF EXISTS `users1`;

CREATE TABLE `users1` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `enabled`  TINYINT(4)  NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `users1` */

INSERT INTO `users1` (`username`, `password`, `enabled`) VALUES ('alex', '123456', 1), ('mkyong', '123456', 1);

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
  `created_date`      TIMESTAMP   NULL     DEFAULT CURRENT_TIMESTAMP,
  `updated_date`      TIMESTAMP   NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`working_hour_id`),
  KEY `FK_working_hours_shops_shop_id` (`shop_id`),
  CONSTRAINT `FK_working_hours_shops_shop_id` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`shop_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1572870
  DEFAULT CHARSET = latin1;

/*Data for the table `working_hours` */

INSERT INTO `working_hours` (`working_hour_id`, `day_of_week`, `open_time_of_day`, `close_time_of_day`, `is_closed`, `is_holiday`, `is_weekend`, `shop_id`, `created_date`, `updated_date`)
VALUES (0, 'Monday', '00:00:00', '20:00:00', 0, 0, 0, 0, '2015-02-28 00:34:33', '2015-02-28 00:36:10'),
  (1, 'test', '00:00:00', '11:59:00', 1, 1, 1, 1, '2015-02-28 00:35:28', '2015-03-08 01:11:36');

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
