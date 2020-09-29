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
-- Dumping data for table `fin_voucher_model_head`
--

LOCK TABLES `fin_voucher_model_head` WRITE;
/*!40000 ALTER TABLE `fin_voucher_model_head` DISABLE KEYS */;
INSERT INTO `fin_voucher_model_head` VALUES (1,'447998260606324736','工资付款凭证','CUSTOM','ji','',NULL,NULL,1,'Y','RD001','produce','2020-07-28 15:51:58','dongbin','2020-07-31 00:35:43','dongbin','erp.com'),(3,'448366117370384384','付款单(系统默认)凭证模板','PAY','fu','',NULL,NULL,1,'Y','RD001','produce','2020-07-29 16:13:42','dongbin','2020-09-21 22:50:15','redragon','erp.com'),(5,'449086343363874816','收款单(系统默认)凭证模板','RECEIPT','shou','',NULL,NULL,1,'Y','RD001','produce','2020-07-31 15:55:38','dongbin','2020-09-21 23:04:20','redragon','erp.com'),(7,'468033862575771648','入库单(系统默认)凭证模板','INPUT','zhuan','',NULL,NULL,1,'Y','STAFF-001','java','2020-09-21 22:46:18','redragon','2020-09-23 19:46:54','redragon','erp.com'),(8,'468034618632622080','采购发票(系统默认)凭证模板','AP_INVOICE','zhuan','',NULL,NULL,1,'Y','STAFF-001','java','2020-09-21 22:49:18','redragon','2020-09-21 22:52:01','redragon','erp.com'),(10,'468037950046654464','出库单(系统默认)凭证模板','OUTPUT','zhuan','',NULL,NULL,1,'Y','STAFF-001','java','2020-09-21 23:02:33','redragon',NULL,NULL,'erp.com'),(11,'468038253194170368','销售发票(系统默认)凭证模板','AR_INVOICE','zhuan','',NULL,NULL,1,'Y','STAFF-001','java','2020-09-21 23:03:45','redragon','2020-09-21 23:03:53','redragon','erp.com');
/*!40000 ALTER TABLE `fin_voucher_model_head` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-29 20:58:30
