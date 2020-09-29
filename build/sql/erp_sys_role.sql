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
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (6,'admin_role','超级管理员角色','Y','2020-07-31 16:29:37','dongbin','2020-08-15 13:19:51','redragon','erp.com'),(7,'sys_role','系统管理角色','Y','2020-08-01 13:43:54','dongbin','2020-08-15 12:56:22','redragon','erp.com'),(10,'fin_role','财务管理角色','Y','2020-08-15 12:52:04','redragon',NULL,NULL,'erp.com'),(11,'hr_role','人力管理角色','Y','2020-08-15 12:52:57','redragon',NULL,NULL,'erp.com'),(12,'md_role','主数据管理角色','Y','2020-08-15 12:53:41','redragon',NULL,NULL,'erp.com'),(13,'pay_role','应付管理角色','Y','2020-08-15 12:54:49','redragon',NULL,NULL,'erp.com'),(14,'po_role','采购管理角色','Y','2020-08-15 12:55:13','redragon',NULL,NULL,'erp.com'),(15,'receipt_role','应收管理角色','Y','2020-08-15 12:55:42','redragon',NULL,NULL,'erp.com'),(16,'so_role','销售管理角色','Y','2020-08-15 12:56:04','redragon',NULL,NULL,'erp.com'),(18,'data_auth_role','数据权限角色','Y','2020-08-16 18:08:57','redragon',NULL,NULL,'erp.com'),(19,'inv_role','库房管理角色','Y','2020-08-17 23:23:14','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-29 20:58:27
