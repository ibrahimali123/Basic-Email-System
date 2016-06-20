-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.33


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema `ia-basic-email`
--

CREATE DATABASE IF NOT EXISTS `ia-basic-email`;
USE `ia-basic-email`;

--
-- Definition of table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sender_id` int(10) unsigned NOT NULL,
  `thread_msg_id` int(10) unsigned NOT NULL,
  `subject` varchar(200) NOT NULL,
  `body` varchar(2000) NOT NULL,
  `attachment` varchar(200) NOT NULL,
  `timestap` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `is_readed` tinyint(1) NOT NULL DEFAULT '0',
  `is_trashed` tinyint(1) NOT NULL DEFAULT '0',
  `is_archived` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK__user` (`sender_id`),
  CONSTRAINT `FK__user` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `message`
--

/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;


--
-- Definition of table `recipient_message`
--

DROP TABLE IF EXISTS `recipient_message`;
CREATE TABLE `recipient_message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `thread_msg_id` int(10) unsigned NOT NULL,
  `msg_id` int(10) unsigned NOT NULL,
  `reciver_id` int(10) unsigned NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `is_trashed` tinyint(1) NOT NULL DEFAULT '0',
  `is_archived` tinyint(1) NOT NULL DEFAULT '0',
  `is_readed` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_recipient_message_message` (`msg_id`),
  KEY `FK_recipient_message_user` (`reciver_id`),
  CONSTRAINT `FK_recipient_message_message` FOREIGN KEY (`msg_id`) REFERENCES `message` (`id`),
  CONSTRAINT `FK_recipient_message_user` FOREIGN KEY (`reciver_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `recipient_message`
--

/*!40000 ALTER TABLE `recipient_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `recipient_message` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `photourl` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
