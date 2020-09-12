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
-- Table structure for table `md_customer`
--

DROP TABLE IF EXISTS `md_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `md_customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_code` varchar(45) NOT NULL COMMENT '客户编码',
  `customer_name` varchar(45) NOT NULL COMMENT '客户名称',
  `customer_type` varchar(45) NOT NULL COMMENT '客户类型（个人、公司）',
  `customer_address` varchar(100) DEFAULT NULL COMMENT '客户地址',
  `customer_telephone` varchar(45) DEFAULT NULL COMMENT '客户电话',
  `customer_country` varchar(45) NOT NULL COMMENT '客户国家',
  `customer_city` varchar(45) DEFAULT NULL COMMENT '客户城市',
  `customer_category` varchar(45) DEFAULT NULL COMMENT '客户类型',
  `customer_label` varchar(100) DEFAULT NULL COMMENT '客户标签',
  `own_flag` char(1) NOT NULL DEFAULT 'N' COMMENT '本公司标识',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '客户状态',
  `approve_status` varchar(45) NOT NULL DEFAULT 'UNSUBMIT' COMMENT '审批状态（未提交UNSUBMIT、已提交SUBMIT、已审批APPROVE、已驳回REJECT）',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `customer_code_UNIQUE` (`customer_code`),
  UNIQUE KEY `customer_name_UNIQUE` (`customer_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主数据客户表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-12 23:02:05
