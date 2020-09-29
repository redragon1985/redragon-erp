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
-- Dumping data for table `hr_department`
--

LOCK TABLES `hr_department` WRITE;
/*!40000 ALTER TABLE `hr_department` DISABLE KEYS */;
INSERT INTO `hr_department` VALUES (25,'erp','ERP公司',NULL,'erp','ERP公司',0,'Y','2020-07-08 20:26:14','dongbin',NULL,NULL,'erp.com'),(26,'hr','人力资源中心','erp','erp_hr','ERP公司_人力资源中心',0,'Y','2020-07-08 20:26:32','dongbin',NULL,NULL,'erp.com'),(27,'tech','技术与产品中心','erp','erp_tech','ERP公司_技术与产品中心',0,'Y','2020-07-08 20:27:02','dongbin',NULL,NULL,'erp.com'),(28,'fa','计划财务中心','erp','erp_fa','ERP公司_计划财务中心',0,'Y','2020-07-08 20:28:02','dongbin',NULL,NULL,'erp.com'),(29,'jishu','技术部','tech','erp_tech_jishu','ERP公司_技术与产品中心_技术部',0,'Y','2020-07-08 20:28:25','dongbin',NULL,NULL,'erp.com'),(31,'chanpin','产品部','tech','erp_tech_chanpin','ERP公司_技术与产品中心_产品部',0,'Y','2020-07-08 20:29:12','dongbin',NULL,NULL,'erp.com'),(32,'yunwei','运维部','tech','erp_tech_yunwei','ERP公司_技术与产品中心_运维部',0,'Y','2020-07-08 20:29:31','dongbin',NULL,NULL,'erp.com'),(36,'sheji','设计组','chanpin','erp_tech_chanpin_sheji','ERP公司_技术与产品中心_产品部_设计组',0,'N','2020-07-08 20:35:41','dongbin','2020-07-08 20:38:37','dongbin','erp.com'),(37,'qianduan','前端组','jishu','erp_tech_jishu_qianduan','ERP公司_技术与产品中心_技术部_前端组',0,'Y','2020-07-08 20:37:07','dongbin',NULL,NULL,'erp.com'),(38,'java','JAVA组','jishu','erp_tech_jishu_java','ERP公司_技术与产品中心_技术部_JAVA组',0,'Y','2020-07-08 20:37:23','dongbin',NULL,NULL,'erp.com'),(39,'ceshi','测试组','jishu','erp_tech_jishu_ceshi','ERP公司_技术与产品中心_技术部_测试组',0,'Y','2020-07-08 20:37:32','dongbin',NULL,NULL,'erp.com'),(41,'produce','产品组','chanpin','erp_tech_chanpin_produce','ERP公司_技术与产品中心_产品部_产品组',0,'Y','2020-07-08 20:38:25','dongbin','2020-08-04 15:38:57','redragon','erp.com'),(42,'ui','UI组','chanpin','erp_tech_chanpin_ui','ERP公司_技术与产品中心_产品部_UI组',0,'Y','2020-07-08 20:38:51','dongbin',NULL,NULL,'erp.com'),(44,'yingjian','硬件组','yunwei','erp_tech_yunwei_yingjian','ERP公司_技术与产品中心_运维部_硬件组',0,'Y','2020-08-04 15:27:51','redragon',NULL,NULL,'erp.com'),(46,'kuaiji','会计部','fa','erp_fa_kuaiji','ERP公司_计划财务中心_会计部',0,'Y','2020-08-05 16:57:04','redragon',NULL,NULL,'erp.com'),(47,'chuna','出纳部','fa','erp_fa_chuna','ERP公司_计划财务中心_出纳部',0,'Y','2020-08-05 16:57:18','redragon',NULL,NULL,'erp.com'),(48,'zhaopin','招聘部','hr','erp_hr_zhaopin','ERP公司_人力资源中心_招聘部',0,'Y','2020-08-05 16:57:36','redragon',NULL,NULL,'erp.com'),(49,'peixun','培训部','hr','erp_hr_peixun','ERP公司_人力资源中心_培训部',0,'Y','2020-08-05 16:58:12','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `hr_department` ENABLE KEYS */;
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
