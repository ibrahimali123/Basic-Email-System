-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.26-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for ia-basic-email
CREATE DATABASE IF NOT EXISTS `ia-basic-email` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ia-basic-email`;


-- Dumping structure for table ia-basic-email.message
CREATE TABLE IF NOT EXISTS `message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sender_id` int(10) unsigned NOT NULL,
  `thread_msg_id` int(10) unsigned DEFAULT NULL,
  `subject` varchar(200) NOT NULL,
  `body` varchar(2000) NOT NULL,
  `attachment` varchar(200) DEFAULT NULL,
  `timestap` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK__user` (`sender_id`),
  CONSTRAINT `FK__user` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table ia-basic-email.opertaions
CREATE TABLE IF NOT EXISTS `opertaions` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userID` int(10) unsigned NOT NULL DEFAULT '0',
  `thredID` int(10) NOT NULL DEFAULT '0',
  `is_archived` tinyint(4) DEFAULT '0',
  `is_deleted` tinyint(4) DEFAULT '0',
  `is_readed` tinyint(4) DEFAULT '0',
  `is_trashed` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_opertaions_user` (`userID`),
  CONSTRAINT `FK_opertaions_user` FOREIGN KEY (`userID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table ia-basic-email.recipient_message
CREATE TABLE IF NOT EXISTS `recipient_message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `thread_msg_id` int(10) unsigned NOT NULL,
  `msg_id` int(10) unsigned NOT NULL,
  `reciver_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_recipient_message_message` (`msg_id`),
  KEY `FK_recipient_message_user` (`reciver_id`),
  CONSTRAINT `FK_recipient_message_message` FOREIGN KEY (`msg_id`) REFERENCES `message` (`id`),
  CONSTRAINT `FK_recipient_message_user` FOREIGN KEY (`reciver_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table ia-basic-email.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `photourl` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
