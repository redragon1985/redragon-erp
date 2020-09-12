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
-- Table structure for table `fin_voucher_bill_r`
--

DROP TABLE IF EXISTS `fin_voucher_bill_r`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fin_voucher_bill_r` (
  `vb_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `voucher_head_code` varchar(45) NOT NULL COMMENT '凭证头编码',
  `bill_type` varchar(45) NOT NULL COMMENT '单据类型(PAY，RECEIPT，INPUT，OUTPUT)',
  `bill_head_code` varchar(45) NOT NULL COMMENT '单据头编码',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`vb_id`),
  UNIQUE KEY `bill_head_code_UNIQUE` (`bill_head_code`),
  UNIQUE KEY `UK_fin_voucher_bill_r` (`voucher_head_code`,`bill_type`,`bill_head_code`),
  KEY `IX_fin_voucher_bill_r_bill_head_code` (`bill_head_code`) /*!80000 INVISIBLE */,
  KEY `IX_fin_voucher_bill_r_voucher_head_code` (`voucher_head_code`) /*!80000 INVISIBLE */
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='财务凭证与单据关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-12 23:02:02
