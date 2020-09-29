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
-- Temporary view structure for view `fin_voucher_report_v`
--

DROP TABLE IF EXISTS `fin_voucher_report_v`;
/*!50001 DROP VIEW IF EXISTS `fin_voucher_report_v`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `fin_voucher_report_v` AS SELECT 
 1 AS `voucherHeadCode`,
 1 AS `voucherType`,
 1 AS `voucherTypeDesc`,
 1 AS `voucherNumber`,
 1 AS `voucherDate`,
 1 AS `billNum`,
 1 AS `memo`,
 1 AS `subjectCode`,
 1 AS `subjectDesc`,
 1 AS `drAmount`,
 1 AS `crAmount`,
 1 AS `billType`,
 1 AS `billTypeDesc`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `fin_voucher_report_v`
--

/*!50001 DROP VIEW IF EXISTS `fin_voucher_report_v`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `fin_voucher_report_v` AS select `h`.`voucher_head_code` AS `voucherHeadCode`,`h`.`voucher_type` AS `voucherType`,(case when (`h`.`voucher_type` = 'shou') then '收' when (`h`.`voucher_type` = 'fu') then '付' when (`h`.`voucher_type` = 'zhuan') then '转' when (`h`.`voucher_type` = 'ji') then '记' end) AS `voucherTypeDesc`,`h`.`voucher_number` AS `voucherNumber`,`h`.`voucher_date` AS `voucherDate`,`h`.`bill_num` AS `billNum`,`l`.`memo` AS `memo`,`l`.`subject_code` AS `subjectCode`,(select `s`.`segment_desc` from `md_finance_subject` `s` where (`s`.`subject_code` = `l`.`subject_code`)) AS `subjectDesc`,`l`.`dr_amount` AS `drAmount`,`l`.`cr_amount` AS `crAmount`,`b`.`bill_type` AS `billType`,(case when (`b`.`bill_type` = 'INPUT') then '入库单' when (`b`.`bill_type` = 'OUTPUT') then '出库单' when (`b`.`bill_type` = 'AP_INVOICE') then '采购发票' when (`b`.`bill_type` = 'AR_INVOICE') then '销售发票' when (`b`.`bill_type` = 'PAY') then '付款单' when (`b`.`bill_type` = 'RECEIPT') then '收款单' else '' end) AS `billTypeDesc` from (`fin_voucher_line` `l` join (`fin_voucher_head` `h` left join `fin_voucher_bill_r` `b` on((`h`.`voucher_head_code` = `b`.`voucher_head_code`)))) where (`h`.`voucher_head_code` = `l`.`voucher_head_code`) order by `h`.`voucher_head_id`,`l`.`voucher_line_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-29 20:58:37
