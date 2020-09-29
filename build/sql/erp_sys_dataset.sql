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
-- Dumping data for table `sys_dataset`
--

LOCK TABLES `sys_dataset` WRITE;
/*!40000 ALTER TABLE `sys_dataset` DISABLE KEYS */;
INSERT INTO `sys_dataset` VALUES (1,'rmb','人民币','currency','Y','2020-07-03 22:14:26','dongbin',NULL,NULL,'erp.com'),(4,'china','中国','country','Y','2020-07-13 22:50:11','dongbin',NULL,NULL,'erp.com'),(5,'110000','北京市','city','Y','2020-07-13 22:57:48','dongbin',NULL,NULL,'erp.com'),(6,'310000','上海市','city','Y','2020-07-13 22:58:09','dongbin',NULL,NULL,'erp.com'),(7,'440100','广州市','city','Y','2020-07-13 22:58:25','dongbin',NULL,NULL,'erp.com'),(9,'BOC','中国银行','bank','Y','2020-07-13 23:24:51','dongbin',NULL,NULL,'erp.com'),(10,'CMB','招商银行','bank','Y','2020-07-13 23:25:06','dongbin',NULL,NULL,'erp.com'),(11,'CCB','中国建设银行','bank','Y','2020-07-13 23:25:24','dongbin',NULL,NULL,'erp.com'),(12,'ABC','中国农业银行','bank','Y','2020-07-13 23:25:37','dongbin',NULL,NULL,'erp.com'),(13,'ICBC','中国工商银行','bank','Y','2020-07-13 23:25:53','dongbin',NULL,NULL,'erp.com'),(14,'BCOM','交通银行','bank','Y','2020-07-13 23:26:08','dongbin',NULL,NULL,'erp.com'),(15,'CITIC','中信银行','bank','Y','2020-07-13 23:26:28','dongbin',NULL,NULL,'erp.com'),(18,'KG','千克','material_unit','Y','2020-07-14 23:26:36','dongbin',NULL,NULL,'erp.com'),(19,'ML','毫升','material_unit','Y','2020-07-14 23:26:53','dongbin',NULL,NULL,'erp.com'),(20,'AN','个/台','material_unit','Y','2020-07-14 23:27:36','dongbin','2020-08-04 15:44:09','redragon','erp.com'),(21,'ZY','自营项目','project_type','Y','2020-07-15 13:24:02','dongbin',NULL,NULL,'erp.com'),(22,'DL','代理项目','project_type','Y','2020-07-15 13:24:12','dongbin',NULL,NULL,'erp.com'),(23,'FW','服务项目','project_type','Y','2020-07-15 13:24:22','dongbin',NULL,NULL,'erp.com'),(24,'POTYPE01','标准采购订单','po_type','Y','2020-07-15 15:35:40','dongbin',NULL,NULL,'erp.com'),(25,'POTYPE02','计划采购订单','po_type','Y','2020-07-15 15:35:58','dongbin',NULL,NULL,'erp.com'),(26,'POTYPE03','一揽子采购订单','po_type','Y','2020-07-15 15:36:26','dongbin',NULL,NULL,'erp.com'),(28,'17','增值税','tax_type','Y','2020-07-15 15:42:17','dongbin',NULL,NULL,'erp.com'),(29,'5','营业税','tax_type','Y','2020-07-15 15:43:07','dongbin',NULL,NULL,'erp.com'),(30,'20','企业所得税','tax_type','Y','2020-07-15 15:46:13','dongbin',NULL,NULL,'erp.com'),(31,'SOTYPE01','标准销售订单','so_type','Y','2020-07-17 22:36:20','dongbin',NULL,NULL,'erp.com'),(32,'SOTYPE02','计划销售订单','so_type','Y','2020-07-17 22:36:36','dongbin',NULL,NULL,'erp.com'),(33,'cash','现金','pay_mode','Y','2020-07-19 16:17:46','dongbin',NULL,NULL,'erp.com'),(34,'check','支票','pay_mode','Y','2020-07-19 16:18:18','dongbin',NULL,NULL,'erp.com'),(36,'transfer','转账','pay_mode','Y','2020-07-19 16:30:02','dongbin',NULL,NULL,'erp.com'),(37,'ji','记','voucher_type','Y','2020-07-25 17:20:07','dongbin',NULL,NULL,'erp.com'),(38,'fu','付','voucher_type','Y','2020-07-25 17:20:35','dongbin',NULL,NULL,'erp.com'),(39,'shou','收','voucher_type','Y','2020-07-25 17:20:43','dongbin',NULL,NULL,'erp.com'),(44,'zhuan','转','voucher_type','Y','2020-08-04 15:40:23','redragon',NULL,NULL,'erp.com'),(45,'ZH','综合项目','project_type','Y','2020-08-04 15:42:17','redragon',NULL,NULL,'erp.com'),(46,'CI','次','material_unit','Y','2020-08-04 15:45:32','redragon',NULL,NULL,'erp.com'),(47,'120000','天津市','city','Y','2020-08-04 15:58:16','redragon',NULL,NULL,'erp.com'),(48,'130100','石家庄市','city','Y','2020-08-04 16:00:54','redragon',NULL,NULL,'erp.com'),(49,'140100','太原市','city','Y','2020-08-04 16:01:17','redragon',NULL,NULL,'erp.com'),(50,'150100','呼和浩特市','city','Y','2020-08-04 16:01:33','redragon',NULL,NULL,'erp.com'),(51,'210100','沈阳市','city','Y','2020-08-04 16:02:01','redragon',NULL,NULL,'erp.com'),(52,'210200','大连市','city','Y','2020-08-04 16:02:13','redragon',NULL,NULL,'erp.com'),(53,'220100','长春市','city','Y','2020-08-04 16:02:27','redragon',NULL,NULL,'erp.com'),(54,'230100','哈尔滨市','city','Y','2020-08-04 16:02:47','redragon',NULL,NULL,'erp.com'),(55,'320100','南京市','city','Y','2020-08-04 16:03:10','redragon',NULL,NULL,'erp.com'),(56,'320500','苏州市','city','Y','2020-08-04 16:03:28','redragon',NULL,NULL,'erp.com'),(57,'330100','杭州市','city','Y','2020-08-04 16:03:41','redragon',NULL,NULL,'erp.com'),(58,'340100','合肥市','city','Y','2020-08-04 16:03:57','redragon',NULL,NULL,'erp.com'),(59,'350100','福州市','city','Y','2020-08-04 16:04:14','redragon',NULL,NULL,'erp.com'),(60,'350200','厦门市','city','Y','2020-08-04 16:04:26','redragon',NULL,NULL,'erp.com'),(61,'360100','南昌市','city','Y','2020-08-04 16:04:40','redragon',NULL,NULL,'erp.com'),(62,'370100','济南市','city','Y','2020-08-04 16:04:58','redragon',NULL,NULL,'erp.com'),(63,'410100','郑州市','city','Y','2020-08-04 16:05:15','redragon',NULL,NULL,'erp.com'),(64,'420100','武汉市','city','Y','2020-08-04 16:05:31','redragon',NULL,NULL,'erp.com'),(65,'430100','长沙市','city','Y','2020-08-04 16:05:43','redragon',NULL,NULL,'erp.com'),(66,'450100','南宁市','city','Y','2020-08-04 16:06:25','redragon',NULL,NULL,'erp.com'),(67,'460100','海口市','city','Y','2020-08-04 16:06:37','redragon',NULL,NULL,'erp.com'),(68,'500000','重庆','city','Y','2020-08-04 16:07:08','redragon',NULL,NULL,'erp.com'),(69,'510100','成都市','city','Y','2020-08-04 16:07:20','redragon',NULL,NULL,'erp.com'),(70,'520100','贵阳市','city','Y','2020-08-04 16:07:34','redragon',NULL,NULL,'erp.com'),(71,'530100','昆明市','city','Y','2020-08-04 16:07:46','redragon',NULL,NULL,'erp.com'),(72,'540100','拉萨市','city','Y','2020-08-04 16:08:00','redragon',NULL,NULL,'erp.com'),(73,'610100','西安市','city','Y','2020-08-04 16:08:12','redragon',NULL,NULL,'erp.com'),(74,'620100','兰州市','city','Y','2020-08-04 16:08:25','redragon',NULL,NULL,'erp.com'),(75,'630100','西宁市','city','Y','2020-08-04 16:08:37','redragon',NULL,NULL,'erp.com'),(76,'640100','银川市','city','Y','2020-08-04 16:08:49','redragon',NULL,NULL,'erp.com'),(77,'650100','乌鲁木齐市','city','Y','2020-08-04 16:09:01','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `sys_dataset` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-29 20:58:33
