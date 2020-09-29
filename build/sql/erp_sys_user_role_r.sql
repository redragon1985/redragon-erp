CREATE DATABASE  IF NOT EXISTS `erp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `erp`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: erp
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `sys_user_role_r`
--

LOCK TABLES `sys_user_role_r` WRITE;
/*!40000 ALTER TABLE `sys_user_role_r` DISABLE KEYS */;
INSERT INTO `sys_user_role_r` VALUES (11,'admin','sys_role','2020-08-01 15:18:43','dongbin',NULL,NULL,''),(59,'redragon','data_auth_role','2020-08-17 23:15:48','redragon',NULL,NULL,'erp.com'),(60,'redragon','admin_role','2020-08-17 23:15:48','redragon',NULL,NULL,'erp.com'),(61,'dongbin','inv_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(62,'dongbin','data_auth_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(63,'dongbin','fin_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(64,'dongbin','hr_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(65,'dongbin','md_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(66,'dongbin','pay_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(67,'dongbin','po_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(68,'dongbin','receipt_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(69,'dongbin','so_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(70,'dongbin','sys_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `sys_user_role_r` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-29 20:58:31
