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
-- Table structure for table `receipt_head`
--

DROP TABLE IF EXISTS `receipt_head`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `receipt_head` (
  `receipt_head_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收款头id',
  `receipt_head_code` varchar(45) NOT NULL COMMENT '收款头编码',
  `receipt_source_type` varchar(45) NOT NULL COMMENT '收款来源类型（采购订单SO、入库单OUTPUT）',
  `receipt_source_head_code` varchar(45) NOT NULL COMMENT '收款来源头编码（销售订单头编码、出库单头编码）',
  `payer` varchar(45) NOT NULL COMMENT '付款方',
  `receiver` varchar(45) NOT NULL COMMENT '收款方',
  `amount` decimal(10,2) NOT NULL COMMENT '发票金额',
  `currency_code` varchar(45) NOT NULL COMMENT '币种',
  `reference_number` varchar(45) DEFAULT NULL COMMENT '发票参考号（纸质发票号）',
  `receipt_date` date NOT NULL COMMENT '收款时间',
  `pre_receipt_flag` char(1) NOT NULL COMMENT '预收款标识',
  `receipt_mode` varchar(45) NOT NULL DEFAULT 'N' COMMENT '收款方式',
  `bank_code` varchar(45) DEFAULT NULL COMMENT '银行编码',
  `sub_bank_code` varchar(45) DEFAULT NULL COMMENT '分行编码',
  `bank_account` varchar(45) DEFAULT NULL COMMENT '银行账户',
  `memo` varchar(200) DEFAULT NULL COMMENT '摘要',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  `status` varchar(10) NOT NULL DEFAULT 'NEW' COMMENT '状态（新建NEW，确认CONFIRM，取消CANCEL）',
  `approve_status` varchar(10) NOT NULL DEFAULT 'UNSUBMIT' COMMENT '审批状态（未提交UNSUBMIT、已提交SUBMIT、已审批APPROVE、已驳回REJECT）',
  `received_status` varchar(10) DEFAULT NULL COMMENT '收款状态（未付款N，已付款Y）',
  `staff_code` varchar(45) NOT NULL COMMENT '制单人',
  `department_code` varchar(45) NOT NULL COMMENT '制单人部门',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`receipt_head_id`),
  UNIQUE KEY `receipt_head_code_UNIQUE` (`receipt_head_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收款单头表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-10 21:55:51
