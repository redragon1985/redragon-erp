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
-- Table structure for table `inv_input_head`
--

DROP TABLE IF EXISTS `inv_input_head`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `inv_input_head` (
  `input_head_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `input_head_code` varchar(45) NOT NULL COMMENT '入库单编码',
  `input_type` varchar(45) NOT NULL COMMENT '入库类型（采购入库PO_IN、销售退回SO_RETURN、盘点入库CHECK_IN）',
  `input_source_type` varchar(45) NOT NULL COMMENT '入库来源类型（采购订单PO）',
  `input_source_head_code` varchar(45) NOT NULL COMMENT '入库来源头编码（采购订单头编码）',
  `warehouse_code` varchar(45) NOT NULL COMMENT '仓库编码',
  `input_date` date NOT NULL COMMENT '入库日期',
  `memo` varchar(200) DEFAULT NULL COMMENT '备注',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  `status` varchar(10) NOT NULL DEFAULT 'NEW' COMMENT '状态（新建NEW，确认CONFIRM，取消CANCEL）',
  `approve_status` varchar(10) NOT NULL DEFAULT 'UNSUBMIT' COMMENT '审批状态（未提交UNSUBMIT、已提交SUBMIT、已审批APPROVE、已驳回REJECT）',
  `staff_code` varchar(45) NOT NULL COMMENT '制单人',
  `department_code` varchar(45) NOT NULL COMMENT '制单部门',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改日期',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`input_head_id`),
  UNIQUE KEY `input_head_code_UNIQUE` (`input_head_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入库单头表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-20 14:04:54
