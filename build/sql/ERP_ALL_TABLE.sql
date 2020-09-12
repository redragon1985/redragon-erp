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
-- Table structure for table `ap_invoice_head`
--

DROP TABLE IF EXISTS `ap_invoice_head`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ap_invoice_head` (
  `invoice_head_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '付款头id',
  `invoice_head_code` varchar(45) NOT NULL COMMENT '付款头编码',
  `invoice_source_type` varchar(45) NOT NULL COMMENT '付款来源类型（采购订单PO、入库单INPUT）',
  `invoice_source_head_code` varchar(45) NOT NULL COMMENT '付款来源头编码（采购订单头编码、入库单头编码）',
  `payer` varchar(45) NOT NULL COMMENT '付款方',
  `receiver` varchar(45) NOT NULL COMMENT '收款方',
  `amount` decimal(10,2) NOT NULL COMMENT '发票金额',
  `currency_code` varchar(45) NOT NULL COMMENT '币种',
  `reference_number` varchar(45) DEFAULT NULL COMMENT '发票参考号（纸质发票号）',
  `invoice_date` date NOT NULL COMMENT '付款时间',
  `prepay_flag` char(1) NOT NULL DEFAULT 'N' COMMENT '预付款标识',
  `pay_mode` varchar(45) NOT NULL COMMENT '付款方式',
  `bank_code` varchar(45) DEFAULT NULL COMMENT '银行编码',
  `sub_bank_code` varchar(45) DEFAULT NULL COMMENT '分行编码',
  `bank_account` varchar(45) DEFAULT NULL COMMENT '银行账户',
  `memo` varchar(200) DEFAULT NULL COMMENT '摘要',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  `status` varchar(10) NOT NULL DEFAULT 'NEW' COMMENT '状态（新建NEW，确认CONFIRM，取消CANCEL）',
  `approve_status` varchar(10) NOT NULL DEFAULT 'UNSUBMIT' COMMENT '审批状态（未提交UNSUBMIT、已提交SUBMIT、已审批APPROVE、已驳回REJECT）',
  `paid_status` varchar(10) DEFAULT NULL COMMENT '付款状态（未付款N，已付款Y）',
  `staff_code` varchar(45) NOT NULL COMMENT '制单人',
  `department_code` varchar(45) NOT NULL COMMENT '制单部门',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`invoice_head_id`),
  UNIQUE KEY `pay_head_code_UNIQUE` (`invoice_head_code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='付款单头表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ap_invoice_line`
--

DROP TABLE IF EXISTS `ap_invoice_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ap_invoice_line` (
  `invoice_line_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '付款行id',
  `invoice_line_code` varchar(45) NOT NULL COMMENT '付款行编码',
  `invoice_head_code` varchar(45) NOT NULL COMMENT '付款头编码',
  `invoice_source_line_code` varchar(45) NOT NULL COMMENT '付款来源行编码（采购订单行编码、入库单行编码）',
  `quantity` double NOT NULL COMMENT '发票行数量',
  `amount` decimal(10,2) NOT NULL COMMENT '行金额',
  `tax_rate` double NOT NULL COMMENT '税率（带小数）',
  `tax_amount` decimal(10,2) NOT NULL COMMENT '税额',
  `memo` varchar(200) DEFAULT NULL COMMENT '摘要',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  `status` varchar(10) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`invoice_line_id`),
  UNIQUE KEY `pay_line_code_UNIQUE` (`invoice_line_code`),
  KEY `IX_pay_line_pay_head_code` (`invoice_head_code`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='付款单行表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ar_invoice_head`
--

DROP TABLE IF EXISTS `ar_invoice_head`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ar_invoice_head` (
  `invoice_head_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收款头id',
  `invoice_head_code` varchar(45) NOT NULL COMMENT '收款头编码',
  `invoice_source_type` varchar(45) NOT NULL COMMENT '收款来源类型（采购订单SO、入库单OUTPUT）',
  `invoice_source_head_code` varchar(45) NOT NULL COMMENT '收款来源头编码（销售订单头编码、出库单头编码）',
  `payer` varchar(45) NOT NULL COMMENT '付款方',
  `receiver` varchar(45) NOT NULL COMMENT '收款方',
  `amount` decimal(10,2) NOT NULL COMMENT '发票金额',
  `currency_code` varchar(45) NOT NULL COMMENT '币种',
  `reference_number` varchar(45) DEFAULT NULL COMMENT '发票参考号（纸质发票号）',
  `invoice_date` date NOT NULL COMMENT '收款时间',
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
  PRIMARY KEY (`invoice_head_id`),
  UNIQUE KEY `receipt_head_code_UNIQUE` (`invoice_head_code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收款单头表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ar_invoice_line`
--

DROP TABLE IF EXISTS `ar_invoice_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ar_invoice_line` (
  `invoice_line_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收款行id',
  `invoice_line_code` varchar(45) NOT NULL COMMENT '收款行编码',
  `invoice_head_code` varchar(45) NOT NULL COMMENT '收款头编码',
  `invoice_source_line_code` varchar(45) NOT NULL COMMENT '收款来源行编码',
  `quantity` double NOT NULL COMMENT '发票行数量',
  `amount` decimal(10,2) NOT NULL COMMENT '行金额',
  `tax_rate` double NOT NULL COMMENT '税率（带小数）',
  `tax_amount` decimal(10,2) NOT NULL COMMENT '税额',
  `memo` varchar(200) DEFAULT NULL COMMENT '摘要',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`invoice_line_id`),
  UNIQUE KEY `receipt_line_code_UNIQUE` (`invoice_line_code`),
  KEY `IX_receipt_line_receipt_head_code` (`invoice_head_code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收款单行表';
/*!40101 SET character_set_client = @saved_cs_client */;

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

--
-- Table structure for table `fin_voucher_head`
--

DROP TABLE IF EXISTS `fin_voucher_head`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fin_voucher_head` (
  `voucher_head_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '凭证头id',
  `voucher_head_code` varchar(45) NOT NULL COMMENT '凭证头编码',
  `voucher_type` varchar(10) NOT NULL COMMENT '凭单字',
  `voucher_number` varchar(45) NOT NULL COMMENT '凭证号',
  `voucher_date` date NOT NULL COMMENT '凭证日期',
  `bill_num` int(11) NOT NULL COMMENT '单据数量',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  `status` varchar(10) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `approve_status` varchar(10) NOT NULL DEFAULT 'UNSUBMIT' COMMENT '审批状态（未提交UNSUBMIT、已提交SUBMIT、已审批APPROVE、已驳回REJECT）',
  `staff_code` varchar(45) NOT NULL COMMENT '制单人',
  `department_code` varchar(45) NOT NULL COMMENT '制单部门',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`voucher_head_id`),
  UNIQUE KEY `voucher_code_UNIQUE` (`voucher_head_code`),
  UNIQUE KEY `UK_fin_voucher_head` (`voucher_type`,`voucher_number`) /*!80000 INVISIBLE */,
  KEY `IX_fin_voucher_head_voucher_number` (`voucher_number`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='财务凭证头';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fin_voucher_line`
--

DROP TABLE IF EXISTS `fin_voucher_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fin_voucher_line` (
  `voucher_line_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '凭证行id',
  `voucher_line_code` varchar(45) NOT NULL COMMENT '凭证行编码',
  `voucher_head_code` varchar(45) NOT NULL COMMENT '凭证头编码',
  `memo` varchar(200) NOT NULL COMMENT '摘要',
  `subject_code` varchar(45) NOT NULL COMMENT '科目',
  `dr_amount` decimal(10,2) NOT NULL COMMENT '借方金额',
  `cr_amount` decimal(10,2) NOT NULL COMMENT '贷方金额',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`voucher_line_id`),
  UNIQUE KEY `voucher_line_code_UNIQUE` (`voucher_line_code`),
  KEY `IX_fin_voucher_line_voucher_head_code` (`voucher_head_code`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='财务凭证行';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fin_voucher_model_head`
--

DROP TABLE IF EXISTS `fin_voucher_model_head`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fin_voucher_model_head` (
  `voucher_head_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '凭证头id',
  `voucher_head_code` varchar(45) NOT NULL COMMENT '凭证头编码',
  `model_name` varchar(45) NOT NULL COMMENT '模板名称',
  `business_type` varchar(45) NOT NULL COMMENT '业务类型(CUSTOM，PAY，RECEIPT，INPUT，OUTPUT)',
  `voucher_type` varchar(10) NOT NULL COMMENT '凭单字',
  `voucher_number` varchar(45) DEFAULT NULL COMMENT '凭证号',
  `voucher_date` date DEFAULT NULL COMMENT '凭证日期',
  `bill_num` int(11) DEFAULT NULL COMMENT '单据数量',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  `status` varchar(10) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `staff_code` varchar(45) NOT NULL COMMENT '制单人',
  `department_code` varchar(45) NOT NULL COMMENT '制单部门',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`voucher_head_id`),
  UNIQUE KEY `voucher_head_code_UNIQUE` (`voucher_head_code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='财务凭证模板头';
/*!40101 SET character_set_client = @saved_cs_client */;

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

--
-- Table structure for table `hr_department`
--

DROP TABLE IF EXISTS `hr_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hr_department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `department_code` varchar(45) NOT NULL COMMENT '部门编码',
  `department_name` varchar(45) NOT NULL COMMENT '部门名称',
  `parent_department_code` varchar(45) DEFAULT NULL COMMENT '父部门编码',
  `segment_code` varchar(200) NOT NULL COMMENT '部门段值编码',
  `segment_desc` varchar(200) NOT NULL COMMENT '部门段值描述',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`department_id`),
  UNIQUE KEY `department_code_UNIQUE` (`department_code`),
  UNIQUE KEY `department_name_UNIQUE` (`department_name`),
  UNIQUE KEY `segment_code_UNIQUE` (`segment_code`),
  UNIQUE KEY `segment_desc_UNIQUE` (`segment_desc`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='人力部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hr_position`
--

DROP TABLE IF EXISTS `hr_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hr_position` (
  `position_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `position_code` varchar(45) NOT NULL COMMENT '岗位编码',
  `position_name` varchar(45) NOT NULL COMMENT '岗位名称',
  `position_type` varchar(45) NOT NULL COMMENT '岗位类型（经理MANAGER、职员EMPLOYEE）',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`position_id`),
  UNIQUE KEY `position_code_UNIQUE` (`position_code`),
  UNIQUE KEY `position_name_UNIQUE` (`position_name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='人力职位表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hr_staff`
--

DROP TABLE IF EXISTS `hr_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hr_staff` (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `staff_code` varchar(45) NOT NULL COMMENT '职员编码',
  `staff_number` varchar(45) NOT NULL COMMENT '职员工号',
  `staff_name` varchar(45) NOT NULL COMMENT '职员名称',
  `staff_sex` varchar(10) DEFAULT NULL COMMENT '性别（MALE、FEMALE）',
  `staff_entry_date` date DEFAULT NULL COMMENT '入职日期',
  `staff_status` varchar(10) NOT NULL DEFAULT 'WORK' COMMENT '职员状态（WORK、LEAVE）',
  `staff_mobile` varchar(45) DEFAULT NULL COMMENT '手机',
  `staff_email` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `username` varchar(45) NOT NULL COMMENT '关联的用户名',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`staff_id`),
  UNIQUE KEY `staff_code_UNIQUE` (`staff_code`),
  UNIQUE KEY `staff_number_UNIQUE` (`staff_number`),
  UNIQUE KEY `UK_hr_staff_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='人力职员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hr_staff_department_r`
--

DROP TABLE IF EXISTS `hr_staff_department_r`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hr_staff_department_r` (
  `sd_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `staff_code` varchar(45) NOT NULL COMMENT '职员编码',
  `department_code` varchar(45) NOT NULL COMMENT '部门编码',
  `position_code` varchar(45) NOT NULL COMMENT '岗位编码',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`sd_id`),
  UNIQUE KEY `uk_staff_department_position` (`staff_code`,`department_code`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='人力职员与部门关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

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

--
-- Table structure for table `inv_input_line`
--

DROP TABLE IF EXISTS `inv_input_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `inv_input_line` (
  `input_line_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `input_line_code` varchar(45) NOT NULL COMMENT '入库行编码',
  `input_head_code` varchar(45) NOT NULL COMMENT '入库头编码',
  `input_source_line_code` varchar(45) NOT NULL COMMENT '入库来源行编码（采购订单行编码）',
  `material_code` varchar(45) NOT NULL COMMENT '物料编码',
  `input_quantity` double NOT NULL COMMENT '入库数量',
  `memo` varchar(200) DEFAULT NULL COMMENT '备注',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  `status` varchar(10) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`input_line_id`),
  UNIQUE KEY `input_line_code_UNIQUE` (`input_line_code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入库单行表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inv_output_head`
--

DROP TABLE IF EXISTS `inv_output_head`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `inv_output_head` (
  `output_head_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `output_head_code` varchar(45) NOT NULL COMMENT '出库单编码',
  `output_type` varchar(45) NOT NULL COMMENT '出库类型（销售出库SO_OUT、购入退出PO_RETURN、盘点出库CHECK_OUT）',
  `output_source_type` varchar(45) NOT NULL COMMENT '出库来源类型（销售订单SO）',
  `output_source_head_code` varchar(45) NOT NULL COMMENT '出库来源头编码（销售订单头编码）',
  `warehouse_code` varchar(45) NOT NULL COMMENT '仓库编码',
  `output_date` date NOT NULL COMMENT '出库日期',
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
  PRIMARY KEY (`output_head_id`),
  UNIQUE KEY `input_head_code_UNIQUE` (`output_head_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='出库单头表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inv_output_line`
--

DROP TABLE IF EXISTS `inv_output_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `inv_output_line` (
  `output_line_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `output_line_code` varchar(45) NOT NULL COMMENT '出库行编码',
  `output_head_code` varchar(45) NOT NULL COMMENT '出库头编码',
  `output_source_line_code` varchar(45) NOT NULL COMMENT '出库来源行编码（销售订单行编码）',
  `material_code` varchar(45) NOT NULL COMMENT '物料编码',
  `output_quantity` double NOT NULL COMMENT '出库数量',
  `memo` varchar(200) DEFAULT NULL COMMENT '备注',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  `status` varchar(10) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`output_line_id`),
  UNIQUE KEY `input_line_code_UNIQUE` (`output_line_code`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='出库单行表';
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='库存表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inv_stock_check_head`
--

DROP TABLE IF EXISTS `inv_stock_check_head`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `inv_stock_check_head` (
  `check_head_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `check_head_code` varchar(45) NOT NULL COMMENT '盘点头编码',
  `check_name` varchar(45) NOT NULL COMMENT '盘点名称',
  `warehouse_code` varchar(45) NOT NULL COMMENT '仓库编码',
  `check_date` varchar(45) NOT NULL COMMENT '盘点日期（一般是年月）',
  `memo` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` varchar(10) NOT NULL DEFAULT 'NEW' COMMENT '状态（新建NEW，确认CONFIRM，取消CANCEL）',
  `approve_status` varchar(10) NOT NULL DEFAULT 'UNSUBMIT' COMMENT '审批状态（未提交UNSUBMIT、已提交SUBMIT、已审批APPROVE、已驳回REJECT）',
  `staff_code` varchar(45) NOT NULL COMMENT '制单人',
  `department_code` varchar(45) NOT NULL COMMENT '制单部门',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改日期',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`check_head_id`),
  UNIQUE KEY `check_head_code_UNIQUE` (`check_head_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='库存盘点表头';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inv_stock_check_line`
--

DROP TABLE IF EXISTS `inv_stock_check_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `inv_stock_check_line` (
  `check_line_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `check_line_code` varchar(45) NOT NULL COMMENT '盘点行编码',
  `check_head_code` varchar(45) NOT NULL COMMENT '盘点头编码',
  `material_code` varchar(45) NOT NULL COMMENT '物料编码',
  `check_before_quantity` double NOT NULL COMMENT '盘点前数量',
  `check_after_quantity` double NOT NULL COMMENT '盘点后数量',
  `memo` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` varchar(45) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`check_line_id`),
  UNIQUE KEY `check_line_code_UNIQUE` (`check_line_code`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='库存盘点表行';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inv_warehouse`
--

DROP TABLE IF EXISTS `inv_warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `inv_warehouse` (
  `warehouse_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `warehouse_code` varchar(45) NOT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(45) NOT NULL COMMENT '仓库名称',
  `warehouse_address` varchar(45) NOT NULL COMMENT '仓库地址',
  `memo` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`warehouse_id`),
  UNIQUE KEY `warehouse_code_UNIQUE` (`warehouse_code`),
  UNIQUE KEY `warehouse_name_UNIQUE` (`warehouse_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='仓库表';
/*!40101 SET character_set_client = @saved_cs_client */;

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

--
-- Table structure for table `md_customer_bank`
--

DROP TABLE IF EXISTS `md_customer_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `md_customer_bank` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_code` varchar(45) NOT NULL COMMENT '客户编码',
  `bank_code` varchar(45) NOT NULL COMMENT '银行编码',
  `sub_bank_code` varchar(45) DEFAULT NULL COMMENT '分行编码',
  `bank_account` varchar(45) NOT NULL COMMENT '银行账户',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`bank_id`),
  UNIQUE KEY `UK_md_customer_bank` (`customer_code`,`bank_code`,`bank_account`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主数据客户银行表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `md_customer_contact`
--

DROP TABLE IF EXISTS `md_customer_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `md_customer_contact` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_code` varchar(45) NOT NULL COMMENT '客户编码',
  `contact_name` varchar(45) NOT NULL COMMENT '联系人',
  `contact_telephone` varchar(45) NOT NULL COMMENT '联系电话',
  `contact_position` varchar(45) DEFAULT NULL COMMENT '联系人职位',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`contact_id`),
  KEY `UK_md_customer_contact_customer_code` (`customer_code`) /*!80000 INVISIBLE */
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主数据客户联系人表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `md_customer_license`
--

DROP TABLE IF EXISTS `md_customer_license`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `md_customer_license` (
  `license_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_code` varchar(45) NOT NULL COMMENT '客户编码',
  `license_number` varchar(45) NOT NULL COMMENT '营业执照号',
  `legal_person` varchar(45) NOT NULL COMMENT '法人代表',
  `company_type` varchar(45) NOT NULL COMMENT '公司类型',
  `business_scope` varchar(200) DEFAULT NULL COMMENT '经营范围',
  `start_date` date NOT NULL COMMENT '成立日期',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`license_id`),
  UNIQUE KEY `license_number_UNIQUE` (`license_number`),
  KEY `IX_md_customer_license_customer_code` (`customer_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主数据客户营业执照表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `md_finance_department`
--

DROP TABLE IF EXISTS `md_finance_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `md_finance_department` (
  `fi_department_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fi_department_code` varchar(45) NOT NULL COMMENT '部门编码',
  `fi_department_name` varchar(45) NOT NULL COMMENT '部门名称',
  `parent_fi_department_code` varchar(45) DEFAULT NULL COMMENT '父部门编码',
  `segment_code` varchar(200) NOT NULL COMMENT '部门段值编码',
  `segment_desc` varchar(200) NOT NULL COMMENT '部门段值描述',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`fi_department_id`),
  UNIQUE KEY `fi_department_code_UNIQUE` (`fi_department_code`),
  UNIQUE KEY `fi_department_name_UNIQUE` (`fi_department_name`),
  UNIQUE KEY `segment_code_UNIQUE` (`segment_code`),
  UNIQUE KEY `segment_desc_UNIQUE` (`segment_desc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主数据财务组织机构树';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `md_finance_subject`
--

DROP TABLE IF EXISTS `md_finance_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `md_finance_subject` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_code` varchar(45) NOT NULL COMMENT '科目编码',
  `subject_name` varchar(45) NOT NULL COMMENT '科目名称',
  `parent_subject_code` varchar(45) DEFAULT NULL COMMENT '父科目编码',
  `segment_code` varchar(200) NOT NULL COMMENT '科目段值编码',
  `segment_desc` varchar(200) NOT NULL COMMENT '科目段值描述',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`subject_id`),
  UNIQUE KEY `subject_code_UNIQUE` (`subject_code`),
  UNIQUE KEY `subject_name_UNIQUE` (`subject_name`),
  UNIQUE KEY `segment_code_UNIQUE` (`segment_code`),
  UNIQUE KEY `segment_desc_UNIQUE` (`segment_desc`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主数据财务科目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `md_material`
--

DROP TABLE IF EXISTS `md_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `md_material` (
  `material_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `material_code` varchar(45) NOT NULL COMMENT '物料编码',
  `material_name` varchar(45) NOT NULL COMMENT '物料名称',
  `material_type` varchar(45) NOT NULL COMMENT '物料或事项（MATERIAL、MATTER）',
  `category_code` varchar(45) NOT NULL COMMENT '类别编码',
  `material_unit` varchar(45) DEFAULT NULL COMMENT '物料单位',
  `valid_day` int(11) DEFAULT NULL COMMENT '效期（天数）',
  `standard` varchar(45) DEFAULT NULL COMMENT '规格',
  `standard_unit` varchar(45) DEFAULT NULL COMMENT '规格单位',
  `pack_standard` varchar(45) DEFAULT NULL COMMENT '包装规格',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `approve_status` varchar(45) NOT NULL DEFAULT 'UNSUBMIT' COMMENT '审批状态（未提交UNSUBMIT、已提交SUBMIT、已审批APPROVE、已驳回REJECT）',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改日期',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`material_id`),
  UNIQUE KEY `material_code_UNIQUE` (`material_code`),
  UNIQUE KEY `material_name_UNIQUE` (`material_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主数据物料表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `md_material_category`
--

DROP TABLE IF EXISTS `md_material_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `md_material_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_code` varchar(45) NOT NULL COMMENT '类别编码',
  `category_name` varchar(45) NOT NULL COMMENT '类别名称',
  `parent_category_code` varchar(45) DEFAULT NULL COMMENT '父类别编码',
  `segment_code` varchar(200) NOT NULL COMMENT '类别段值编码',
  `segment_desc` varchar(200) NOT NULL COMMENT '类别段值描述',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_code_UNIQUE` (`category_code`),
  UNIQUE KEY `category_name_UNIQUE` (`category_name`),
  UNIQUE KEY `segment_code_UNIQUE` (`segment_code`),
  UNIQUE KEY `segment_desc_UNIQUE` (`segment_desc`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主数据物料类别表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `md_project`
--

DROP TABLE IF EXISTS `md_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `md_project` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_code` varchar(45) NOT NULL COMMENT '项目编码',
  `project_name` varchar(45) NOT NULL COMMENT '项目名称',
  `project_desc` varchar(45) DEFAULT NULL COMMENT '项目描述',
  `project_type` varchar(45) NOT NULL COMMENT '项目类型',
  `start_date` date NOT NULL COMMENT '开始日期',
  `end_date` date DEFAULT NULL COMMENT '结束日期',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `approve_status` varchar(45) NOT NULL DEFAULT 'UNSUBMIT' COMMENT '审批状态（未提交UNSUBMIT、已提交SUBMIT、已审批APPROVE、已驳回REJECT）',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`project_id`),
  UNIQUE KEY `project_code_UNIQUE` (`project_code`),
  UNIQUE KEY `project_name_UNIQUE` (`project_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主数据项目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `md_vendor`
--

DROP TABLE IF EXISTS `md_vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `md_vendor` (
  `vendor_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vendor_code` varchar(45) NOT NULL COMMENT '供应商编码',
  `vendor_name` varchar(45) NOT NULL COMMENT '供应商名称',
  `vendor_type` varchar(45) NOT NULL COMMENT '供应商类型（公司、个人）',
  `vendor_address` varchar(100) DEFAULT NULL COMMENT '供应商地址',
  `vendor_telephone` varchar(45) DEFAULT NULL COMMENT '供应商电话',
  `vendor_country` varchar(45) NOT NULL COMMENT '供应商国家',
  `vendor_city` varchar(45) DEFAULT NULL COMMENT '供应商城市',
  `vendor_category` varchar(45) DEFAULT NULL COMMENT '供应商类别',
  `vendor_label` varchar(100) DEFAULT NULL COMMENT '供应商标签',
  `own_flag` char(1) NOT NULL DEFAULT 'N' COMMENT '本公司标识',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `approve_status` varchar(45) NOT NULL DEFAULT 'UNSUBMIT' COMMENT '审批状态（未提交UNSUBMIT、已提交SUBMIT、已审批APPROVE、已驳回REJECT）',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`vendor_id`),
  UNIQUE KEY `vendor_code_UNIQUE` (`vendor_code`),
  UNIQUE KEY `vendor_name_UNIQUE` (`vendor_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主数据供应商表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `md_vendor_bank`
--

DROP TABLE IF EXISTS `md_vendor_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `md_vendor_bank` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vendor_code` varchar(45) NOT NULL COMMENT '供应商编码',
  `bank_code` varchar(45) NOT NULL COMMENT '银行编码',
  `sub_bank_code` varchar(45) DEFAULT NULL COMMENT '分行编码',
  `bank_account` varchar(45) NOT NULL COMMENT '银行账户',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`bank_id`),
  UNIQUE KEY `UK_md_vendor_bank` (`vendor_code`,`bank_code`,`bank_account`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主数据供应商银行表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `md_vendor_contact`
--

DROP TABLE IF EXISTS `md_vendor_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `md_vendor_contact` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vendor_code` varchar(45) NOT NULL COMMENT '供应商编码',
  `contact_name` varchar(45) NOT NULL COMMENT '联系人',
  `contact_telephone` varchar(45) NOT NULL COMMENT '联系人电话',
  `contact_position` varchar(45) DEFAULT NULL COMMENT '联系人岗位',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`contact_id`),
  KEY `IX_md_vendor_contact_vendor_code` (`vendor_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主数据供应商联系人表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `md_vendor_license`
--

DROP TABLE IF EXISTS `md_vendor_license`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `md_vendor_license` (
  `license_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vendor_code` varchar(45) NOT NULL COMMENT '供应商编码',
  `license_number` varchar(45) NOT NULL COMMENT '营业执照号',
  `legal_person` varchar(45) NOT NULL COMMENT '法人代表',
  `company_type` varchar(45) NOT NULL COMMENT '企业类型',
  `business_scope` varchar(200) DEFAULT NULL COMMENT '经营范围',
  `start_date` date NOT NULL COMMENT '成立日期',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`license_id`),
  UNIQUE KEY `license_number_UNIQUE` (`license_number`),
  KEY `IX_md_vendor_license_vendor_code` (`vendor_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主数据供应商营业执照表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `po_head`
--

DROP TABLE IF EXISTS `po_head`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `po_head` (
  `po_head_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '采购订单头id',
  `po_head_code` varchar(45) NOT NULL COMMENT '采购订单头编码',
  `po_type` varchar(45) NOT NULL COMMENT '采购订单类型',
  `po_name` varchar(45) NOT NULL COMMENT '采购订单名称',
  `po_desc` varchar(500) DEFAULT NULL COMMENT '采购订单描述',
  `project_code` varchar(45) DEFAULT NULL COMMENT '项目编码',
  `vendor_code` varchar(45) NOT NULL COMMENT '供应商编码',
  `currency_code` varchar(45) NOT NULL COMMENT '采购订单币种',
  `prepay_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '采购订单预付款金额',
  `start_date` date DEFAULT NULL COMMENT '采购订单开始日期',
  `end_date` date DEFAULT NULL COMMENT '采购订单结束日期',
  `sign_date` date NOT NULL COMMENT '采购订单签订日期',
  `tax_type` varchar(45) DEFAULT NULL COMMENT '计税类型',
  `tax_percent` double DEFAULT NULL COMMENT '计税比率',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  `status` varchar(10) NOT NULL DEFAULT 'NEW' COMMENT '状态（新建NEW，确认CONFIRM，取消CANCEL）',
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
  PRIMARY KEY (`po_head_id`),
  UNIQUE KEY `po_head_code_UNIQUE` (`po_head_code`),
  UNIQUE KEY `po_name_UNIQUE` (`po_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='采购订单头表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `po_line`
--

DROP TABLE IF EXISTS `po_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `po_line` (
  `po_line_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '采购订单行id',
  `po_line_code` varchar(45) NOT NULL COMMENT '采购订单行编码',
  `po_head_code` varchar(45) NOT NULL COMMENT '采购订单头编码',
  `material_code` varchar(45) NOT NULL COMMENT '物料编码',
  `quantity` double NOT NULL COMMENT '数量',
  `price` decimal(10,2) NOT NULL COMMENT '单价',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  `unit` varchar(45) NOT NULL COMMENT '单位',
  `memo` varchar(500) DEFAULT NULL COMMENT '摘要',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  `status` varchar(10) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`po_line_id`),
  UNIQUE KEY `po_line_code_UNIQUE` (`po_line_code`),
  KEY `IX_po_line_po_head_code` (`po_head_code`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='采购订单行表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `so_head`
--

DROP TABLE IF EXISTS `so_head`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `so_head` (
  `so_head_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '销售订单头id',
  `so_head_code` varchar(45) NOT NULL COMMENT '销售订单头编码',
  `so_type` varchar(45) NOT NULL COMMENT '销售订单类型',
  `so_name` varchar(45) NOT NULL COMMENT '销售订单名称',
  `so_desc` varchar(500) DEFAULT NULL COMMENT '销售订单描述',
  `project_code` varchar(45) DEFAULT NULL COMMENT '项目编码',
  `customer_code` varchar(45) NOT NULL COMMENT '客户编码',
  `currency_code` varchar(45) NOT NULL COMMENT '销售订单币种',
  `pre_receipt_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '销售订单预收款金额',
  `start_date` date DEFAULT NULL COMMENT '销售订单开始日期',
  `end_date` date DEFAULT NULL COMMENT '销售订单结束日期',
  `sign_date` date NOT NULL COMMENT '销售订单签订日期',
  `tax_type` varchar(45) DEFAULT NULL COMMENT '计税类型',
  `tax_percent` double DEFAULT NULL COMMENT '计税比率',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  `status` varchar(10) NOT NULL DEFAULT 'NEW' COMMENT '状态（新建NEW，确认CONFIRM，取消CANCEL）',
  `approve_status` varchar(45) NOT NULL DEFAULT 'UNSUBMIT' COMMENT '审批状态（未提交UNSUBMIT、已提交SUBMIT、已审批APPROVE、已驳回REJECT）',
  `shipment_status` varchar(10) DEFAULT NULL COMMENT '发运状态（未出库N，已出库Y，部分出库PART）',
  `receipt_status` varchar(10) DEFAULT NULL COMMENT '收款状态（未收款N，已收款Y，部分收款PART）',
  `staff_code` varchar(45) NOT NULL COMMENT '销售员',
  `department_code` varchar(45) NOT NULL COMMENT '销售部门',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`so_head_id`),
  UNIQUE KEY `po_head_code_UNIQUE` (`so_head_code`),
  UNIQUE KEY `so_name_UNIQUE` (`so_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='销售订单头表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `so_line`
--

DROP TABLE IF EXISTS `so_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `so_line` (
  `so_line_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '销售订单行id',
  `so_line_code` varchar(45) NOT NULL COMMENT '销售订单行编码',
  `so_head_code` varchar(45) NOT NULL COMMENT '销售订单头编码',
  `material_code` varchar(45) NOT NULL COMMENT '物料编码',
  `quantity` double NOT NULL COMMENT '数量',
  `price` decimal(10,2) NOT NULL COMMENT '单价',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  `unit` varchar(45) NOT NULL COMMENT '单位',
  `memo` varchar(500) DEFAULT NULL COMMENT '摘要',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  `status` varchar(10) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`so_line_id`),
  UNIQUE KEY `so_line_code_UNIQUE` (`so_line_code`),
  KEY `IX_so_line_so_head_code` (`so_head_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='销售订单行表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_auth`
--

DROP TABLE IF EXISTS `sys_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_auth` (
  `auth_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `auth_code` varchar(45) NOT NULL COMMENT '权限编码',
  `auth_name` varchar(45) NOT NULL COMMENT '权限名称',
  `auth_type` varchar(30) NOT NULL COMMENT '权限类型',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(30) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(30) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`auth_id`),
  UNIQUE KEY `auth_code_UNIQUE` (`auth_code`),
  UNIQUE KEY `auth_name_UNIQUE` (`auth_name`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_dataset`
--

DROP TABLE IF EXISTS `sys_dataset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_dataset` (
  `dataset_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dataset_code` varchar(45) NOT NULL COMMENT '值集编码',
  `dataset_name` varchar(45) NOT NULL COMMENT '值集名称',
  `dataset_type_code` varchar(45) NOT NULL COMMENT '值集类型编码',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`dataset_id`),
  UNIQUE KEY `dataset_name_UNIQUE` (`dataset_name`),
  UNIQUE KEY `dataset_code_UNIQUE` (`dataset_code`),
  KEY `IX_dataset_dataset_type_code` (`dataset_type_code`) /*!80000 INVISIBLE */
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统数据字典值表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_dataset_type`
--

DROP TABLE IF EXISTS `sys_dataset_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_dataset_type` (
  `dataset_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dataset_type_code` varchar(45) NOT NULL COMMENT '值集类型编码',
  `dataset_type_name` varchar(45) NOT NULL COMMENT '值集类型名称',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(45) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(45) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`dataset_type_id`),
  UNIQUE KEY `type_code_UNIQUE` (`dataset_type_code`),
  UNIQUE KEY `type_name_UNIQUE` (`dataset_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统数据字典类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(45) NOT NULL COMMENT '角色编码',
  `role_name` varchar(45) NOT NULL COMMENT '角色名称',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(30) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` varchar(30) DEFAULT NULL COMMENT '最后修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_code_UNIQUE` (`role_code`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_role_auth_r`
--

DROP TABLE IF EXISTS `sys_role_auth_r`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_role_auth_r` (
  `ra_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(45) NOT NULL COMMENT '角色编码',
  `auth_code` varchar(45) NOT NULL COMMENT '权限编码',
  `created_date` datetime NOT NULL,
  `created_by` varchar(30) NOT NULL,
  `last_updated_date` datetime DEFAULT NULL,
  `last_updated_by` varchar(30) DEFAULT NULL,
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`ra_id`),
  UNIQUE KEY `UK_sys_role_auth_r` (`role_code`,`auth_code`)
) ENGINE=InnoDB AUTO_INCREMENT=356 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统角色与权限关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(45) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(30) NOT NULL COMMENT '创建人',
  `last_updated_date` datetime DEFAULT NULL COMMENT '修改时间',
  `last_updated_by` varchar(30) DEFAULT NULL COMMENT '修改人',
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_user_role_r`
--

DROP TABLE IF EXISTS `sys_user_role_r`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_user_role_r` (
  `ur_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(45) NOT NULL COMMENT '用户名',
  `role_code` varchar(45) NOT NULL COMMENT '角色编码',
  `created_date` datetime NOT NULL,
  `created_by` varchar(30) NOT NULL,
  `last_updated_date` datetime DEFAULT NULL,
  `last_updated_by` varchar(30) DEFAULT NULL,
  `org_code` varchar(10) NOT NULL COMMENT '组织机构',
  PRIMARY KEY (`ur_id`),
  UNIQUE KEY `UK_sys_user_role_r` (`username`,`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户与角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-12 23:03:55
