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
-- Dumping data for table `sys_dataset_type`
--

LOCK TABLES `sys_dataset_type` WRITE;
/*!40000 ALTER TABLE `sys_dataset_type` DISABLE KEYS */;
INSERT INTO `sys_dataset_type` VALUES (2,'city','城市','Y','2020-07-03 16:16:11','dongbin','2020-07-13 23:24:21','dongbin','erp.com'),(3,'currency','币种','Y','2020-07-03 16:17:36','dongbin',NULL,NULL,'erp.com'),(6,'country','国家','Y','2020-07-13 22:49:10','dongbin',NULL,NULL,'erp.com'),(7,'bank','银行','Y','2020-07-13 23:24:33','dongbin',NULL,NULL,'erp.com'),(9,'material_unit','物料单位','Y','2020-07-14 23:25:41','dongbin',NULL,NULL,'erp.com'),(10,'project_type','项目类型','Y','2020-07-15 13:23:31','dongbin',NULL,NULL,'erp.com'),(11,'po_type','采购订单类型','Y','2020-07-15 15:34:49','dongbin',NULL,NULL,'erp.com'),(12,'tax_type','计税种类','Y','2020-07-15 15:41:10','dongbin',NULL,NULL,'erp.com'),(13,'so_type','销售订单类型','Y','2020-07-17 22:33:49','dongbin',NULL,NULL,'erp.com'),(14,'pay_mode','付款方式','Y','2020-07-19 16:12:53','dongbin',NULL,NULL,'erp.com'),(15,'voucher_type','凭证字','Y','2020-07-25 17:19:22','dongbin',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `sys_dataset_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-29 20:58:18
