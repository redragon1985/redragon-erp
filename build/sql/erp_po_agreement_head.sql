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
-- Table structure for table `po_agreement_head`
--

DROP TABLE IF EXISTS `po_agreement_head`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `po_agreement_head` (
  `po_head_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '采购协议头id',
  `po_head_code` varchar(45) NOT NULL COMMENT '采购协议头编码',
  `po_type` varchar(45) NOT NULL COMMENT '采购协议类型',
  `po_name` varchar(45) NOT NULL COMMENT '采购协议名称',
  `po_desc` varchar(500) DEFAULT NULL COMMENT '采购协议描述',
  `project_code` varchar(45) DEFAULT NULL COMMENT '项目编码',
  `vendor_code` varchar(45) DEFAULT NULL COMMENT '供应商编码',
  `currency_code` varchar(45) NOT NULL COMMENT '采购协议币种',
  `poa_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '采购协议金额',
  `prepay_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '采购协议预付款金额',
  `start_date` date DEFAULT NULL COMMENT '采购协议开始日期',
  `end_date` date DEFAULT NULL COMMENT '采购协议结束日期',
  `sign_date` date DEFAULT NULL COMMENT '采购协议签订日期',
  `tax_type` varchar(45) DEFAULT NULL COMMENT '计税类型',
  `tax_percent` double DEFAULT NULL COMMENT '计税比率',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  `status` varchar(10) NOT NULL DEFAULT 'NEW' COMMENT '状态（新建NEW，确认CONFIRM，变更ALTER，取消CANCEL）',
  `approve_status` varchar(45) NOT NULL DEFAULT 'UNSUBMIT' COMMENT '审批状态（未提交UNSUBMIT、已提交SUBMIT、已审批APPROVE、已驳回REJECT）',
  `receive_status` varchar(10) DEFAULT NULL COMMENT '接收状态（未入库N，已入库Y，部分入库PART）',
  `payment_status` varchar(10) DEFAULT NULL COMMENT '付款状态（未付款N，已付款Y，部分付款PART）',
  `staff_code` varchar(45) NOT NULL COMMENT '采购员',
  `department_code` varchar(45) NOT NULL COMMENT '采购部门',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  `attribute1` varchar(45) DEFAULT NULL,
  `attribute2` varchar(45) DEFAULT NULL,
  `attribute3` varchar(45) DEFAULT NULL,
  `attribute4` varchar(45) DEFAULT NULL,
  `attribute5` varchar(45) DEFAULT NULL,
  `attribute6` varchar(45) DEFAULT NULL,
  `attribute7` varchar(45) DEFAULT NULL,
  `attribute8` varchar(45) DEFAULT NULL,
  `attribute9` varchar(45) DEFAULT NULL,
  `attribute10` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`po_head_id`),
  UNIQUE KEY `po_head_code_UNIQUE` (`po_head_code`),
  UNIQUE KEY `po_name_UNIQUE` (`po_name`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='采购协议头表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-05 16:07:25
