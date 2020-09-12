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
-- Table structure for table `fin_voucher_model_line`
--

DROP TABLE IF EXISTS `fin_voucher_model_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fin_voucher_model_line` (
  `voucher_line_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '凭证行id',
  `voucher_line_code` varchar(45) NOT NULL COMMENT '凭证行编码',
  `voucher_head_code` varchar(45) NOT NULL COMMENT '凭证头编码',
  `memo` varchar(200) NOT NULL COMMENT '摘要',
  `subject_code` varchar(45) NOT NULL COMMENT '科目',
  `dr_amount` varchar(45) DEFAULT NULL COMMENT '借方金额',
  `cr_amount` varchar(45) DEFAULT NULL COMMENT '贷方金额',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`voucher_line_id`),
  UNIQUE KEY `voucher_line_code_UNIQUE` (`voucher_line_code`),
  KEY `IX_fin_voucher_model_line_voucher_head_code` (`voucher_head_code`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='财务凭证模板行';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-12 23:02:11
