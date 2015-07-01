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
  `created_date` TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP,
  `updated_date` TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `location_id`  BIGINT(20)            DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `FK_address_locations_location_id` (`location_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;

/*Data for the table `address` */

INSERT INTO `address` (`address_id`, `address1`, `address2`, `city`, `district`, `state`, `zipcode`, `created_date`, `updated_date`, `location_id`)
VALUES (0, 'Default', 'Default', 'Default', 'Default', 'Default', 0, '2015-02-28 01:32:36', '2015-02-28 01:32:36', 0),
  (1, 'test', 'test', 'test_city', 'test_district', 'test_state', 99999, '2015-02-28 01:32:36', '2015-03-04 01:23:35',
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
  (0, 'default', 'default', '2015-12-30 04:45:31', '2015-01-29 04:45:31', 'bloom', 'optional', '2015-05-02 16:52:42',
   '2015-05-02 16:52:42'),
  (1, 'test', 'test', '2015-10-15 16:45:31', '2015-01-25 09:45:31', 'bloom', 'optional', '2015-05-02 16:53:50',
   '2015-05-02 16:53:50');

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
  PRIMARY KEY (`card_id`),
  UNIQUE KEY `card_number` (`card_number`, `customer_id`),
  KEY `FK5A0E763189EC4A7` (`customer_id`),
  CONSTRAINT `FK5A0E763189EC4A7` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `cards` */

INSERT INTO `cards` (`discriminator`, `card_id`, `first_name`, `last_name`, `card_number`, `issue_date`, `has_picture`, `picture_location`, `middle_name`, `cvv_code`, `card_type`, `modification`, `expiry_date`, `created_date`, `updated_date`, `customer_id`)
VALUES ('Card', 0, 'default', 'default', '4532296367907972', '01:10', 0, '', NULL, NULL, NULL, NULL, NULL,
        '2015-05-01 10:33:43', '2015-05-03 00:45:22', NULL),
  ('CreditCard', 1, 'test', 'test', '5558871425828877', '01:14', 0, ' ', '', 231, 'Master Card', NULL, '10:20',
   '2015-05-01 10:35:53', '2015-05-01 10:54:11', 1),
  ('CreditCard', 2, 'test1', 'test1', '6011885685194704', '02:14', 1, 'C:\\Resources\\photos', 'test', 212, 'Discover',
   NULL, '05:19', '2015-05-01 10:52:04', '2015-05-01 10:53:24', 1);

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
VALUES (0, 'meankur2@gmail.com', '', 'arkoghosh@hotmail.com', 'default1', 'default1', 0, NULL, '2015-04-15 00:12:51',
        'default1', 0, NULL, '00:00'),
  (1, 'meankur2@gmail.com', 'bloomrono11@gmail.com', 'arkoghosh@hotmail.com,arkogh@gmail.com', 'Test', 'Test', 1,
   'C:/Properties/resources/pictures/happy_birthday_froggie_card.jpg', '2015-04-20 23:13:53', 'test', 0, 'Test',
   '23:13'), (2, 'meankur2@gmail.com', 'ronobloom11@gmail.com', 'arkoghosh@hotmail.com,arkogh@gmail.com', 'Hello Test',
              'Hello Test', 1,
              'C:/Properties/resources/pictures/happy_birthday_cartoon_card.jpg,C:/Properties/resources/pictures/happy_birthday_froggie_card.jpg',
              '2015-04-20 23:13:58', 'hello', 0, 'Hello', '23:13'),
  (3, 'ronobloom11@gmail.com', '', 'arkoghosh@hotmail.com,arkogh@gmail.com', 'Test2', 'Body', 0, NULL,
   '2015-04-20 23:14:05', 'test2', 0, 'Test2', '23:14'),
  (4, 'ruchij.mhs@gmail.com', '', 'arkoghosh@hotmail.com', 'Happy Birthday',
   '\nHello Ruchi,\n\n\n\nHappy Birthday to you from your friends and Froggie.\n\nMany Happy returns of the day.\n\n\nYours faithfully,\nFroggie',
   1,
   'C:/Properties/resources/pictures/happy_birthday_cartoon_card.jpg,C:/Properties/resources/pictures/happy_birthday_froggie_card.jpg,C:/Properties/resources/pictures/RJ_froggie_sleeping.jpg',
   '2015-04-21 00:23:01', 'Happy_Birthday_Ruchi_Joshi', 0, 'name:Ruchi_Joshi, BD:04-21-1989', '00:23'),
  (8, 'ronobloom11@gmail.com', '', 'arkoghosh@hotmail.com,arkogh@gmail.com', 'Happy Birthday', 'Happy Birthday', 0,
   NULL, '2015-04-29 15:15:04', 'Happy_Birthday_Deepander_Bagdas', 0, 'name:Deepander Bagdas, BD:04-29-1987', '15:15'),
  (9, 'ronobloom11@gmail.com', '', 'arkoghosh@hotmail.com,arkogh@gmail.com', 'HB', 'HB', 0, NULL, '2015-05-28 21:43:39',
   'HB_WOlfie', 0, 'name: Servetnik Vadim, BD:05-28-1982', '21:43');

/*Table structure for table `customer_address` */

DROP TABLE IF EXISTS `customer_address`;

CREATE TABLE `customer_address` (
  `customer_id` BIGINT(20) NOT NULL,
  `address_id`  BIGINT(20) NOT NULL,
  UNIQUE KEY `customer_address_id` (`customer_id`, `address_id`),
  KEY `FK_address_address_id` (`address_id`),
  KEY `FK_user_customer_id` (`customer_id`),
  CONSTRAINT `FK_address_address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_user_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`)
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `customer_address` */

INSERT INTO `customer_address` (`customer_id`, `address_id`) VALUES (1, 1);

/*Table structure for table `customer_preference` */

DROP TABLE IF EXISTS `customer_preference`;

CREATE TABLE `customer_preference` (
  `preference_id` BIGINT(20) NOT NULL,
  `customer_id`   BIGINT(20) NOT NULL,
  KEY `FK64319BDC189EC4A7` (`customer_id`),
  KEY `FK64319BDC2E651047` (`preference_id`),
  CONSTRAINT `FK64319BDC189EC4A7` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`)
    ON UPDATE CASCADE,
  CONSTRAINT `FK64319BDC2E651047` FOREIGN KEY (`preference_id`) REFERENCES `preferences` (`preference_id`)
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `customer_preference` */

INSERT INTO `customer_preference` (`preference_id`, `customer_id`) VALUES (0, 1), (1, 1);

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
VALUES ('items', 75), ('shops', 71), ('address', 80), ('working_hours', 72), ('users', 20), ('cards', 20),
  ('phones', 20), ('preferences', 20);

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
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;

/*Data for the table `items` */

INSERT INTO `items` (`item_id`, `item_name`, `item_price`, `item_type`, `item_price_currency`, `quantity`, `weight`, `bought_from`, `quantity_type`, `item_sub_type`, `weighted_unit`, `bought_date`, `created_date`, `updated_date`, `shop_id`)
VALUES
  (0, 'default_name', 0, 'default_type', 'Dollar', 0, 0, 'default', 'unit', 'default_type', 'kg', '2015-02-25 01:00:00',
   '2015-01-29 04:45:31', '2015-03-14 15:04:20', 0),
  (1, 'test', 2, 'test_type', 'Rupee', 1, 1, 'test', 'unit', 'test', 'pound', '2015-02-25 01:00:00',
   '2015-01-29 04:50:08', '2015-02-28 01:13:49', 1);

/*Table structure for table `katie cooks` */

DROP TABLE IF EXISTS `katie cooks`;

CREATE TABLE `katie cooks` (
  `employee_id` BIGINT(20)   NOT NULL,
  `Cook_name`   VARCHAR(200) NOT NULL,
  `Position`    VARCHAR(200) NOT NULL,
  `Experience`  BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `katie cooks` */

INSERT INTO `katie cooks` (`employee_id`, `Cook_name`, `Position`, `Experience`) VALUES (123, 'Dada',
                                                                                         'Head cook\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
                                                                                         105),
  (124, 'palak', 'chinesefood', 30), (125, 'joshi', 'jki', 500);

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
VALUES (0, 1, 1, 0, '0000-00-00 00:00:00', '2015-02-28 01:17:23'),
  (1, 1, 1, 0, '0000-00-00 00:00:00', '2015-03-03 18:48:48');

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
VALUES (0, 0, 'default', '2015-05-02 18:40:07', '2015-05-02 18:40:07'),
  (1, 1, 'test', '2015-05-02 18:40:07', '2015-05-02 18:40:07');

/*Table structure for table `privileges` */

DROP TABLE IF EXISTS `privileges`;

CREATE TABLE `privileges` (
  `privilege_id`   INT(10)      NOT NULL AUTO_INCREMENT,
  `privilege_name` VARCHAR(150) NOT NULL DEFAULT 'default',
  `accessible`     TINYINT(1)   NOT NULL DEFAULT '0',
  `created_date`   TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP,
  `update_date`    TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`privilege_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = latin1;

/*Data for the table `privileges` */

INSERT INTO `privileges` (`privilege_id`, `privilege_name`, `accessible`, `created_date`, `update_date`)
VALUES (0, 'default', 0, '2015-05-14 18:21:54', '2015-05-14 19:06:27'),
  (1, 'create', 1, '2015-05-14 18:21:21', '2015-05-14 18:22:03'),
  (2, 'update', 1, '2015-05-14 18:21:21', '2015-05-14 18:22:04'),
  (3, 'read', 1, '2015-05-14 18:21:21', '2015-05-14 18:22:06'),
  (4, 'delete', 1, '2015-05-14 18:21:21', '2015-05-14 18:21:21'),
  (5, 'all', 1, '2015-05-14 18:21:21', '2015-05-14 18:21:21');

/*Table structure for table `shops` */

DROP TABLE IF EXISTS `shops`;

CREATE TABLE `shops` (
  `shop_id`       BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `shop_main_id`  BIGINT(20)   NOT NULL,
  `shop_name`     VARCHAR(100) NOT NULL,
  `shop_web_link` VARCHAR(100) NOT NULL,
  `created_date`  TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP,
  `updated_date`  TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `address_id`    BIGINT(20)   NOT NULL,
  PRIMARY KEY (`shop_id`),
  UNIQUE KEY `FK_shops_address_address_id` (`address_id`),
  CONSTRAINT `FK_shops_address_address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;

/*Data for the table `shops` */

INSERT INTO `shops` (`shop_id`, `shop_main_id`, `shop_name`, `shop_web_link`, `created_date`, `updated_date`, `address_id`)
VALUES (0, 0, 'default', 'default', '2015-02-28 01:26:34', '2015-02-28 01:26:34', 0),
  (1, 1111, 'test', 'test', '2015-02-28 01:26:34', '2015-02-28 01:26:34', 1);

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
  `created_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `updated_date` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`tab_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `tabs` */

INSERT INTO `tabs` (`tab_id`, `tab_content`, `tab_name`, `created_date`, `updated_date`)
VALUES (0, 'default', 'default', '2015-05-02 16:06:43', '2015-05-02 16:10:03'),
  (1, 'test', 'test', '2015-05-02 16:06:50', '2015-05-02 16:06:50');

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
  `created_date`   TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP,
  `updated_date`   TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_role_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = latin1;

/*Data for the table `user_roles` */

INSERT INTO `user_roles` (`user_role_id`, `user_role_name`, `is_active`, `created_date`, `updated_date`)
VALUES (0, 'default', 0, '2015-05-13 11:12:19', '2015-05-13 11:12:38'),
  (1, 'anonymus', 1, '2015-05-13 11:11:01', '2015-05-13 11:11:01'),
  (2, 'free_user', 1, '2015-05-13 11:11:01', '2015-05-13 11:11:01'),
  (3, 'vip_user', 1, '2015-05-13 11:11:01', '2015-05-13 11:11:01'),
  (4, 'moderator', 1, '2015-05-13 11:11:01', '2015-05-13 11:11:01'),
  (5, 'super_moderator', 1, '2015-05-13 11:11:01', '2015-05-13 11:11:01'),
  (6, 'maintainence_support', 1, '2015-05-13 11:11:01', '2015-05-13 11:11:01'),
  (7, 'admin_super_user', 1, '2015-05-13 11:11:01', '2015-05-13 11:11:01');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `discriminator` VARCHAR(31)  NOT NULL,
  `user_id`       BIGINT(20)   NOT NULL,
  `user_name`     VARCHAR(255) NOT NULL,
  `password`      VARCHAR(255) NOT NULL,
  `email`         VARCHAR(255) NOT NULL,
  `first_name`    VARCHAR(255) DEFAULT NULL,
  `last_name`     VARCHAR(255) DEFAULT NULL,
  `middle_name`   VARCHAR(255) DEFAULT NULL,
  `title`         VARCHAR(255) DEFAULT NULL,
  `created_date`  DATETIME     DEFAULT CURRENT_TIMESTAMP,
  `updated_date`  DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_role_id`  INT(10)      NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `email` (`email`),
  KEY `FK_users_user_roles_id` (`user_role_id`),
  CONSTRAINT `FK_users_user_roles_id` FOREIGN KEY (`user_role_id`) REFERENCES `user_roles` (`user_role_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

/*Data for the table `users` */

INSERT INTO `users` (`discriminator`, `user_id`, `user_name`, `password`, `email`, `first_name`, `last_name`, `middle_name`, `title`, `created_date`, `updated_date`, `user_role_id`)
VALUES ('User', 0, 'default', 'default', 'default@default.com', NULL, NULL, NULL, NULL, '2015-05-01 10:23:29',
        '2015-05-02 16:13:57', 0),
  ('Customer', 1, 'test', 'test', 'test@test.com', 'test_f_n', 'test_l_n', 'test_m_n', 'test', '2015-05-01 10:24:59',
   '2015-05-02 16:14:58', 0);

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
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;

/*Data for the table `working_hours` */

INSERT INTO `working_hours` (`working_hour_id`, `day_of_week`, `open_time_of_day`, `close_time_of_day`, `is_closed`, `is_holiday`, `is_weekend`, `shop_id`, `created_date`, `updated_date`)
VALUES (0, 'Monday', '00:00:00', '20:00:00', 0, 0, 0, 0, '2015-02-28 01:34:33', '2015-02-28 01:36:10'),
  (1, 'test', '00:00:00', '11:59:00', 1, 1, 1, 1, '2015-02-28 01:35:28', '2015-03-08 03:11:36');

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
