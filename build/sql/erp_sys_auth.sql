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
-- Dumping data for table `sys_auth`
--

LOCK TABLES `sys_auth` WRITE;
/*!40000 ALTER TABLE `sys_auth` DISABLE KEYS */;
INSERT INTO `sys_auth` VALUES (13,'sysDatasetType_menu_auth','数据字典菜单权限','menu','Y','2020-08-15 13:02:17','redragon',NULL,NULL,'erp.com'),(14,'sysUser_menu_auth','用户管理菜单权限','menu','Y','2020-08-15 13:03:41','redragon',NULL,NULL,'erp.com'),(15,'sysRole_menu_auth','角色管理菜单权限','menu','Y','2020-08-15 13:04:30','redragon',NULL,NULL,'erp.com'),(16,'sysAuth_menu_auth','权限管理菜单权限','menu','Y','2020-08-15 13:05:19','redragon',NULL,NULL,'erp.com'),(17,'hrStaff_menu_auth','职员管理菜单权限','menu','Y','2020-08-15 13:07:04','redragon',NULL,NULL,'erp.com'),(18,'hrPosition_menu_auth','岗位管理菜单权限','menu','Y','2020-08-15 13:07:52','redragon',NULL,NULL,'erp.com'),(19,'hrDepartment_menu_auth','部门管理菜单权限','menu','Y','2020-08-15 13:08:32','redragon',NULL,NULL,'erp.com'),(20,'hrStaffDepartmentR_menu_auth','职员关联部门菜单权限','menu','Y','2020-08-15 13:09:11','redragon',NULL,NULL,'erp.com'),(21,'mdCustomer_menu_auth','客户管理菜单权限','menu','Y','2020-08-15 13:09:53','redragon',NULL,NULL,'erp.com'),(22,'mdVendor_menu_auth','供应商管理菜单权限','menu','Y','2020-08-15 13:10:47','redragon',NULL,NULL,'erp.com'),(23,'mdMaterialCategory_menu_auth','物料与事项类型菜单权限','menu','Y','2020-08-15 13:12:13','redragon',NULL,NULL,'erp.com'),(24,'mdMaterial_menu_auth','物料与事项菜单权限','menu','Y','2020-08-15 13:12:56','redragon',NULL,NULL,'erp.com'),(25,'mdProject_menu_auth','项目管理菜单权限','menu','Y','2020-08-15 13:13:32','redragon',NULL,NULL,'erp.com'),(26,'mdFinanceSubject_menu_auth','科目管理菜单权限','menu','Y','2020-08-15 13:14:20','redragon',NULL,NULL,'erp.com'),(27,'poHead_menu_auth','采购订单菜单权限','menu','Y','2020-08-15 13:15:13','redragon',NULL,NULL,'erp.com'),(28,'soHead_menu_auth','销售订单菜单权限','menu','Y','2020-08-15 13:15:46','redragon',NULL,NULL,'erp.com'),(29,'apInvoiceHead_menu_auth','采购发票单菜单权限','menu','Y','2020-08-15 13:16:52','redragon',NULL,NULL,'erp.com'),(30,'arInvoiceHead_menu_auth','销售发票单菜单权限','menu','Y','2020-08-15 13:17:34','redragon',NULL,NULL,'erp.com'),(31,'finVoucherHead_menu_auth','凭证管理菜单权限','menu','Y','2020-08-15 13:18:33','redragon',NULL,NULL,'erp.com'),(32,'finVoucherModelHead_menu_auth','凭证模板菜单权限','menu','Y','2020-08-15 13:19:12','redragon',NULL,NULL,'erp.com'),(33,'finVoucherModelHead_control_auth','凭证模板操作权限','control','Y','2020-08-15 15:06:39','redragon',NULL,NULL,'erp.com'),(34,'finVoucherHead_control_auth','凭证管理操作权限','control','Y','2020-08-15 15:07:28','redragon',NULL,NULL,'erp.com'),(35,'arInvoiceHead_control_auth','销售发票单操作权限','control','Y','2020-08-15 15:08:16','redragon',NULL,NULL,'erp.com'),(36,'apInvoiceHead_control_auth','采购发票单操作权限','control','Y','2020-08-15 15:08:54','redragon',NULL,NULL,'erp.com'),(38,'soHead_control_auth','销售订单操作权限','control','Y','2020-08-15 15:10:00','redragon',NULL,NULL,'erp.com'),(39,'poHead_control_auth','采购订单操作权限','control','Y','2020-08-15 15:10:33','redragon',NULL,NULL,'erp.com'),(40,'mdFinanceSubject_control_auth','科目管理操作权限','control','Y','2020-08-15 15:10:57','redragon',NULL,NULL,'erp.com'),(41,'mdProject_control_auth','项目管理操作权限','control','Y','2020-08-15 15:11:30','redragon',NULL,NULL,'erp.com'),(42,'mdMaterial_control_auth','物料与事项操作权限','control','Y','2020-08-15 15:11:54','redragon',NULL,NULL,'erp.com'),(43,'mdMaterialCategory_control_auth','物料与事项类型操作权限','control','Y','2020-08-15 15:12:18','redragon',NULL,NULL,'erp.com'),(44,'mdVendor_control_auth','供应商管理操作权限','control','Y','2020-08-15 15:13:17','redragon',NULL,NULL,'erp.com'),(45,'mdCustomer_control_auth','客户管理操作权限','control','Y','2020-08-15 15:13:40','redragon',NULL,NULL,'erp.com'),(46,'hrStaffDepartmentR_control_auth','职员关联部门操作权限','control','Y','2020-08-15 15:14:04','redragon',NULL,NULL,'erp.com'),(47,'hrDepartment_control_auth','部门管理操作权限','control','Y','2020-08-15 15:14:25','redragon',NULL,NULL,'erp.com'),(48,'hrPosition_control_auth','岗位管理操作权限','control','Y','2020-08-15 15:14:50','redragon',NULL,NULL,'erp.com'),(49,'hrStaff_control_auth','职员管理操作权限','control','Y','2020-08-15 15:15:13','redragon',NULL,NULL,'erp.com'),(50,'sysAuth_control_auth','权限管理操作权限','control','Y','2020-08-15 15:15:37','redragon',NULL,NULL,'erp.com'),(51,'sysRole_control_auth','角色管理操作权限','control','Y','2020-08-15 15:16:00','redragon',NULL,NULL,'erp.com'),(52,'sysUser_control_auth','用户管理操作权限','control','Y','2020-08-15 15:16:29','redragon',NULL,NULL,'erp.com'),(53,'sysDatasetType_control_auth','数据字典操作权限','control','Y','2020-08-15 15:16:54','redragon',NULL,NULL,'erp.com'),(54,'private_data_auth','个人数据权限','data','Y','2020-08-16 12:15:36','redragon',NULL,NULL,'erp.com'),(55,'public_data_auth','公共数据权限','data','Y','2020-08-16 12:16:49','redragon',NULL,NULL,'erp.com'),(56,'org_level0_data_auth','本部门数据权限','data','Y','2020-08-16 12:24:40','redragon',NULL,NULL,'erp.com'),(57,'org_level1_data_auth','上级部门数据权限','data','Y','2020-08-16 12:25:00','redragon',NULL,NULL,'erp.com'),(58,'org_level2_data_auth','上上级部门数据权限','data','Y','2020-08-16 12:26:15','redragon',NULL,NULL,'erp.com'),(59,'invWarehouse_menu_auth','仓库菜单权限','menu','Y','2020-08-17 23:19:42','redragon',NULL,NULL,'erp.com'),(60,'invWarehouse_control_auth','仓库操作权限','control','Y','2020-08-17 23:20:01','redragon',NULL,NULL,'erp.com'),(61,'invStock_menu_auth','库存菜单权限','menu','Y','2020-08-18 16:54:59','redragon',NULL,NULL,'erp.com'),(62,'invStock_control_auth','库存操作权限','control','Y','2020-08-18 16:55:26','redragon',NULL,NULL,'erp.com'),(63,'invInputHead_menu_auth','入库菜单权限','menu','Y','2020-08-22 18:42:19','redragon',NULL,NULL,'erp.com'),(64,'invInputHead_control_auth','入库操作权限','control','Y','2020-08-22 18:42:55','redragon',NULL,NULL,'erp.com'),(65,'invOutputHead_menu_auth','出库菜单权限','menu','Y','2020-08-22 18:43:36','redragon',NULL,NULL,'erp.com'),(66,'invOutputHead_control_auth','出库操作权限','control','Y','2020-08-22 18:43:57','redragon',NULL,NULL,'erp.com'),(67,'invStockCheckHead_control_auth','库存盘点操作权限','control','Y','2020-08-27 17:08:59','redragon',NULL,NULL,'erp.com'),(68,'apPayHead_menu_auth','付款菜单权限','menu','Y','2020-09-15 21:42:28','redragon',NULL,NULL,'erp.com'),(69,'apPayHead_control_auth','付款操作权限','control','Y','2020-09-15 21:43:02','redragon',NULL,NULL,'erp.com'),(70,'arReceiptHead_menu_auth','收款菜单权限','menu','Y','2020-09-15 21:43:36','redragon',NULL,NULL,'erp.com'),(71,'arReceiptHead_control_auth','收款操作权限','control','Y','2020-09-15 21:44:05','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `sys_auth` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-29 20:58:27
