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
-- Table structure for table `inv_stock`
--

DROP TABLE IF EXISTS `inv_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `inv_stock` (
  `stock_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `stock_code` varchar(45) NOT NULL COMMENT '库存记录编码',
  `warehouse_code` varchar(45) NOT NULL COMMENT '仓库编码',
  `material_code` varchar(45) NOT NULL COMMENT '物料编码',
  `stock_number` double NOT NULL COMMENT '库存数量',
  `retain_flag` char(1) NOT NULL DEFAULT 'N' COMMENT '保留标识（用于出库时的库存保留判定）',
  `bill_type` varchar(45) DEFAULT NULL COMMENT '关联单据类型（入库INPUT、出库OUTPUT）',
  `bill_head_code` varchar(45) DEFAULT NULL COMMENT '关联单据头编码（入库、出库）',
  `bill_line_code` varchar(45) DEFAULT NULL COMMENT '关联单据行编码（入库、出库）',
  `memo` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `staff_code` varchar(45) NOT NULL COMMENT '职员编码',
  `department_code` varchar(45) NOT NULL COMMENT '部门编码',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`stock_id`),
  UNIQUE KEY `stock_code_UNIQUE` (`stock_code`),
  UNIQUE KEY `UK_inv_stock_bill` (`material_code`,`bill_type`,`bill_head_code`,`bill_line_code`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='库存表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-09 10:57:17
