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
-- Dumping data for table `ap_invoice_head`
--

LOCK TABLES `ap_invoice_head` WRITE;
/*!40000 ALTER TABLE `ap_invoice_head` DISABLE KEYS */;
INSERT INTO `ap_invoice_head` VALUES (2,'PAY001','PO','order-001','VENDOR000','VENDOR001',4000.00,'rmb','','2020-07-30','N','transfer','CITIC','北京分行','123','',1,'NEW','UNSUBMIT','N','RD001','produce','2020-07-30 15:28:31','dongbin','2020-09-12 17:18:30','redragon','erp.com'),(3,'PAY-002','PO','order-003','VENDOR000','VENDOR001',0.00,'rmb','A00000001','2020-08-01','Y','transfer','BOC','上海分行','456','',1,'NEW','REJECT','N','STAFF-001','java','2020-08-01 16:06:20','redragon','2020-09-12 17:21:09','redragon','erp.com'),(4,'INVOICE-001','PO','order-003','VENDOR000','VENDOR001',12000.00,'rmb','A00000002','2020-09-10','N','transfer','BOC','上海分行','456','',1,'NEW','UNSUBMIT','N','STAFF-001','java','2020-09-10 19:54:31','redragon','2020-09-10 20:39:30','redragon','erp.com');
/*!40000 ALTER TABLE `ap_invoice_head` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ap_invoice_line`
--

LOCK TABLES `ap_invoice_line` WRITE;
/*!40000 ALTER TABLE `ap_invoice_line` DISABLE KEYS */;
INSERT INTO `ap_invoice_line` VALUES (10,'448717316393193472','PAY001','443745645320130560',2,4700.00,0.17,799.00,'发票行已开票1',1,'Y','2020-07-30 15:29:15','dongbin','2020-09-09 20:11:13','redragon','erp.com'),(12,'464012202440839168','INVOICE-001','464004911691911168',1,12000.00,0.17,2040.00,'',1,'Y','2020-09-10 20:25:40','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `ap_invoice_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ap_pay_head`
--

LOCK TABLES `ap_pay_head` WRITE;
/*!40000 ALTER TABLE `ap_pay_head` DISABLE KEYS */;
INSERT INTO `ap_pay_head` VALUES (5,'PAY0001','MONEY','VENDOR001','rmb',10000.00,'2020-09-25','',1,'NEW','UNSUBMIT','STAFF-001','java','2020-09-25 22:30:08','redragon','2020-09-25 22:30:42','redragon','erp.com');
/*!40000 ALTER TABLE `ap_pay_head` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ap_pay_line`
--

LOCK TABLES `ap_pay_line` WRITE;
/*!40000 ALTER TABLE `ap_pay_line` DISABLE KEYS */;
INSERT INTO `ap_pay_line` VALUES (5,'469479444544671744','PAY0001','INVOICE-001',10000.00,'',1,'Y','2020-09-25 22:30:32','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `ap_pay_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ar_invoice_head`
--

LOCK TABLES `ar_invoice_head` WRITE;
/*!40000 ALTER TABLE `ar_invoice_head` DISABLE KEYS */;
INSERT INTO `ar_invoice_head` VALUES (2,'RECEIPT-001','SO','SO-001','CUST001','VENDOR000',1000.00,'rmb','B00000001','2020-07-30','N','transfer','ABC','北京分行','123456','',1,'NEW','UNSUBMIT','N','RD001','produce','2020-07-30 15:29:55','dongbin','2020-09-09 23:45:00','redragon','erp.com'),(3,'RECEIPT-002','SO','SO-002','CUSTOMER-001','VENDOR000',20000.00,'rmb','B00000003','2020-08-01','N','transfer','CITIC','北京分行','9559990022222000000','',1,'NEW','UNSUBMIT','N','STAFF-001','java','2020-08-01 16:13:12','redragon','2020-09-25 22:08:14','redragon','erp.com');
/*!40000 ALTER TABLE `ar_invoice_head` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ar_invoice_line`
--

LOCK TABLES `ar_invoice_line` WRITE;
/*!40000 ALTER TABLE `ar_invoice_line` DISABLE KEYS */;
INSERT INTO `ar_invoice_line` VALUES (6,'464012464417067008','RECEIPT-001','444118220411949056',5,50000.00,0.17,8500.00,'开票',1,'Y','2020-09-10 20:26:42','redragon',NULL,NULL,'erp.com'),(7,'464711162738429952','RECEIPT-002','450964139417718784',2,20000.00,0.17,3400.00,'111',1,'Y','2020-09-12 18:43:05','redragon','2020-09-12 18:43:10','redragon','erp.com');
/*!40000 ALTER TABLE `ar_invoice_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ar_receipt_head`
--

LOCK TABLES `ar_receipt_head` WRITE;
/*!40000 ALTER TABLE `ar_receipt_head` DISABLE KEYS */;
INSERT INTO `ar_receipt_head` VALUES (3,'receipt-0001','MONEY','CUSTOMER-001','rmb',8500.00,'2020-09-25','',1,'NEW','UNSUBMIT','STAFF-001','java','2020-09-25 22:32:02','redragon','2020-09-25 22:32:18','redragon','erp.com');
/*!40000 ALTER TABLE `ar_receipt_head` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ar_receipt_line`
--

LOCK TABLES `ar_receipt_line` WRITE;
/*!40000 ALTER TABLE `ar_receipt_line` DISABLE KEYS */;
INSERT INTO `ar_receipt_line` VALUES (3,'469479863236874240','receipt-0001','RECEIPT-002',8500.00,'',1,'Y','2020-09-25 22:32:12','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `ar_receipt_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fin_voucher_bill_r`
--

LOCK TABLES `fin_voucher_bill_r` WRITE;
/*!40000 ALTER TABLE `fin_voucher_bill_r` DISABLE KEYS */;
INSERT INTO `fin_voucher_bill_r` VALUES (2,'449086408774045696','RECEIPT','RECEIPT-001','2020-07-31 15:55:53','dongbin',NULL,NULL,'erp.com'),(3,'449456570865995776','PAY','PAY-002','2020-08-01 16:26:47','redragon',NULL,NULL,'erp.com'),(5,'454240281503387648','PAY','PAY001','2020-08-14 21:15:32','redragon',NULL,NULL,'erp.com'),(23,'470131793231532032','INPUT','input-003','2020-09-27 17:42:44','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `fin_voucher_bill_r` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fin_voucher_head`
--

LOCK TABLES `fin_voucher_head` WRITE;
/*!40000 ALTER TABLE `fin_voucher_head` DISABLE KEYS */;
INSERT INTO `fin_voucher_head` VALUES (6,'448315081561657344','fu','002','2020-07-29',0,1,'Y','UNSUBMIT','RD001','produce','2020-07-29 12:50:54','dongbin',NULL,NULL,'erp.com'),(7,'448339079586566144','ji','003','2020-07-29',1,1,'Y','UNSUBMIT','RD001','produce','2020-07-29 14:26:16','dongbin',NULL,NULL,'erp.com'),(8,'448342125414633472','ji','001','2020-07-01',0,1,'N','APPROVE','RD001','produce','2020-07-29 14:38:22','dongbin',NULL,NULL,'erp.com'),(12,'449086408774045696','shou','449086408774045696','2020-07-31',0,1,'Y','REJECT','RD001','produce','2020-07-31 15:55:53','dongbin',NULL,NULL,'erp.com'),(13,'449456351667474432','fu','004','2020-08-01',0,1,'Y','APPROVE','STAFF-001','java','2020-08-01 16:25:54','redragon',NULL,NULL,'erp.com'),(14,'449456570865995776','fu','449456570865995776','2020-08-01',0,1,'Y','SUBMIT','STAFF-001','java','2020-08-01 16:26:47','redragon',NULL,NULL,'erp.com'),(15,'449456631238807552','shou','449456631238807552','2020-08-02',0,1,'N','APPROVE','STAFF-001','java','2020-08-01 16:27:01','redragon','2020-08-06 20:03:52','redragon','erp.com'),(17,'454239452025245696','fu','11','2020-08-14',1,1,'Y','UNSUBMIT','STAFF-001','java','2020-08-14 21:12:14','redragon',NULL,NULL,'erp.com'),(18,'454239647463034880','fu','12','2020-08-14',0,1,'Y','UNSUBMIT','STAFF-001','java','2020-08-14 21:13:01','redragon',NULL,NULL,'erp.com'),(19,'454240281503387648','fu','13','2020-08-14',0,1,'Y','UNSUBMIT','STAFF-001','java','2020-08-14 21:15:32','redragon',NULL,NULL,'erp.com'),(37,'470131793231532032','zhuan','12','2020-09-27',0,1,'Y','UNSUBMIT','STAFF-001','java','2020-09-27 17:42:44','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `fin_voucher_head` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fin_voucher_line`
--

LOCK TABLES `fin_voucher_line` WRITE;
/*!40000 ALTER TABLE `fin_voucher_line` DISABLE KEYS */;
INSERT INTO `fin_voucher_line` VALUES (9,'448315081561657345','448315081561657344','员工工资','5201',2000.00,0.00,1,'Y','2020-07-29 12:50:55','dongbin',NULL,NULL,'erp.com'),(10,'448315081561657346','448315081561657344','银行账户','1002',0.00,2000.00,1,'Y','2020-07-29 12:50:55','dongbin',NULL,NULL,'erp.com'),(11,'448339079586566145','448339079586566144','员工工资','5201',1800.00,0.00,1,'Y','2020-07-29 14:26:16','dongbin',NULL,NULL,'erp.com'),(12,'448339079586566146','448339079586566144','银行账户','1002',0.00,2000.00,1,'Y','2020-07-29 14:26:16','dongbin',NULL,NULL,'erp.com'),(13,'448339079586566147','448339079586566144','税金','6801',200.00,0.00,1,'Y','2020-07-29 14:26:16','dongbin',NULL,NULL,'erp.com'),(14,'448342125414633473','448342125414633472','员工工资','5201',80.00,0.00,1,'Y','2020-07-29 14:38:22','dongbin',NULL,NULL,'erp.com'),(15,'448342125418827776','448342125414633472','银行账户','1002',0.00,100.00,1,'Y','2020-07-29 14:38:22','dongbin',NULL,NULL,'erp.com'),(16,'448342125418827777','448342125414633472','税金','6801',20.00,0.00,1,'Y','2020-07-29 14:38:22','dongbin',NULL,NULL,'erp.com'),(21,'449086409008926720','449086408774045696','银行','1002',1500.00,0.00,1,'Y','2020-07-31 15:55:53','dongbin',NULL,NULL,'erp.com'),(22,'449086409050869760','449086408774045696','应付','2202',0.00,1500.00,1,'Y','2020-07-31 15:55:53','dongbin',NULL,NULL,'erp.com'),(23,'449456351667474433','449456351667474432','测试','1001',2000.00,0.00,1,'Y','2020-08-01 16:25:55','redragon',NULL,NULL,'erp.com'),(24,'449456351667474434','449456351667474432','测试','2202',0.00,2000.00,1,'Y','2020-08-01 16:25:55','redragon',NULL,NULL,'erp.com'),(25,'449456570920521728','449456570865995776','应付1','2202',30000.00,0.00,1,'Y','2020-08-01 16:26:47','redragon',NULL,NULL,'erp.com'),(26,'449456570958270464','449456570865995776','银行1','1002',0.00,30000.00,1,'Y','2020-08-01 16:26:47','redragon',NULL,NULL,'erp.com'),(27,'449456631301722112','449456631238807552','银行','1002',20000.00,0.00,1,'Y','2020-08-06 20:03:52','redragon',NULL,NULL,'erp.com'),(28,'449456631339470848','449456631238807552','应付','2202',0.00,20000.00,1,'Y','2020-08-06 20:03:52','redragon',NULL,NULL,'erp.com'),(31,'454239452025245697','454239452025245696','礼物','6001',12.00,0.00,1,'Y','2020-08-14 21:12:15','redragon',NULL,NULL,'erp.com'),(32,'454239452025245698','454239452025245696','礼物','6401',0.00,12.00,1,'Y','2020-08-14 21:12:15','redragon',NULL,NULL,'erp.com'),(33,'454239647463034881','454239647463034880','员工工资','5201',100.00,0.00,1,'Y','2020-08-14 21:13:01','redragon',NULL,NULL,'erp.com'),(34,'454239647463034882','454239647463034880','银行账户','1002',0.00,80.00,1,'Y','2020-08-14 21:13:01','redragon',NULL,NULL,'erp.com'),(35,'454239647463034883','454239647463034880','税金','6801',0.00,20.00,1,'Y','2020-08-14 21:13:01','redragon',NULL,NULL,'erp.com'),(36,'454240281776017408','454240281503387648','应付1','2202',4000.00,0.00,1,'Y','2020-08-14 21:15:32','redragon',NULL,NULL,'erp.com'),(37,'454240281817960448','454240281503387648','银行1','1002',0.00,4000.00,1,'Y','2020-08-14 21:15:32','redragon',NULL,NULL,'erp.com'),(76,'470131793302835200','470131793231532032','入库','1406',12000.00,0.00,1,'Y','2020-09-27 17:42:44','redragon',NULL,NULL,'erp.com'),(77,'470131793353166848','470131793231532032','采购','220201',0.00,12000.00,1,'Y','2020-09-27 17:42:44','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `fin_voucher_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fin_voucher_model_head`
--

LOCK TABLES `fin_voucher_model_head` WRITE;
/*!40000 ALTER TABLE `fin_voucher_model_head` DISABLE KEYS */;
INSERT INTO `fin_voucher_model_head` VALUES (1,'447998260606324736','工资付款凭证','CUSTOM','ji','',NULL,NULL,1,'Y','RD001','produce','2020-07-28 15:51:58','dongbin','2020-07-31 00:35:43','dongbin','erp.com'),(3,'448366117370384384','付款单(系统默认)凭证模板','PAY','fu','',NULL,NULL,1,'Y','RD001','produce','2020-07-29 16:13:42','dongbin','2020-09-21 22:50:15','redragon','erp.com'),(5,'449086343363874816','收款单(系统默认)凭证模板','RECEIPT','shou','',NULL,NULL,1,'Y','RD001','produce','2020-07-31 15:55:38','dongbin','2020-09-21 23:04:20','redragon','erp.com'),(7,'468033862575771648','入库单(系统默认)凭证模板','INPUT','zhuan','',NULL,NULL,1,'Y','STAFF-001','java','2020-09-21 22:46:18','redragon','2020-09-23 19:46:54','redragon','erp.com'),(8,'468034618632622080','采购发票(系统默认)凭证模板','AP_INVOICE','zhuan','',NULL,NULL,1,'Y','STAFF-001','java','2020-09-21 22:49:18','redragon','2020-09-21 22:52:01','redragon','erp.com'),(10,'468037950046654464','出库单(系统默认)凭证模板','OUTPUT','zhuan','',NULL,NULL,1,'Y','STAFF-001','java','2020-09-21 23:02:33','redragon',NULL,NULL,'erp.com'),(11,'468038253194170368','销售发票(系统默认)凭证模板','AR_INVOICE','zhuan','',NULL,NULL,1,'Y','STAFF-001','java','2020-09-21 23:03:45','redragon','2020-09-21 23:03:53','redragon','erp.com');
/*!40000 ALTER TABLE `fin_voucher_model_head` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fin_voucher_model_line`
--

LOCK TABLES `fin_voucher_model_line` WRITE;
/*!40000 ALTER TABLE `fin_voucher_model_line` DISABLE KEYS */;
INSERT INTO `fin_voucher_model_line` VALUES (1,'447998260606324737','447998260606324736','员工工资','5201','','',1,'Y','2020-07-31 00:35:43','dongbin',NULL,NULL,'erp.com'),(2,'447998260606324738','447998260606324736','银行账户','1002','','',1,'Y','2020-07-31 00:35:43','dongbin',NULL,NULL,'erp.com'),(6,'448338912472911872','447998260606324736','税金','6801','','',1,'Y','2020-07-31 00:35:43','dongbin',NULL,NULL,'erp.com'),(7,'448366117370384385','448366117370384384','应付','220202','AMOUNT','',1,'Y','2020-09-21 22:50:15','redragon',NULL,NULL,'erp.com'),(8,'448366117370384386','448366117370384384','付款','1002','','AMOUNT',1,'Y','2020-09-21 22:50:15','redragon',NULL,NULL,'erp.com'),(11,'449086343368069120','449086343363874816','银行','1002','AMOUNT','',1,'Y','2020-09-21 23:04:20','redragon',NULL,NULL,'erp.com'),(12,'449086343368069121','449086343363874816','应付','2202','','AMOUNT',1,'Y','2020-09-21 23:04:20','redragon',NULL,NULL,'erp.com'),(15,'468033862575771649','468033862575771648','入库','1406','AMOUNT','',1,'Y','2020-09-23 19:46:54','redragon',NULL,NULL,'erp.com'),(16,'468033862575771650','468033862575771648','采购','220201','','AMOUNT',1,'Y','2020-09-23 19:46:55','redragon',NULL,NULL,'erp.com'),(17,'468034618632622081','468034618632622080','应付','220201','AMOUNT','',1,'Y','2020-09-21 22:52:01','redragon',NULL,NULL,'erp.com'),(18,'468034618632622082','468034618632622080','税费','22210101','AMOUNT','',1,'Y','2020-09-21 22:52:01','redragon',NULL,NULL,'erp.com'),(19,'468034618632622083','468034618632622080','发票','220202','','AMOUNT',1,'Y','2020-09-21 22:52:01','redragon',NULL,NULL,'erp.com'),(22,'468037950046654465','468037950046654464','出库','6001','AMOUNT','',1,'Y','2020-09-21 23:02:33','redragon',NULL,NULL,'erp.com'),(23,'468037950046654466','468037950046654464','库存','1406','','AMOUNT',1,'Y','2020-09-21 23:02:33','redragon',NULL,NULL,'erp.com'),(24,'468038253194170369','468038253194170368','发票','1122','AMOUNT','',1,'Y','2020-09-21 23:03:53','redragon',NULL,NULL,'erp.com'),(25,'468038253194170370','468038253194170368','出库','6001','','AMOUNT',1,'Y','2020-09-21 23:03:53','redragon',NULL,NULL,'erp.com'),(26,'468038253194170371','468038253194170368','税费','22210102','','AMOUNT',1,'Y','2020-09-21 23:03:53','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `fin_voucher_model_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hr_department`
--

LOCK TABLES `hr_department` WRITE;
/*!40000 ALTER TABLE `hr_department` DISABLE KEYS */;
INSERT INTO `hr_department` VALUES (25,'erp','ERP公司',NULL,'erp','ERP公司',0,'Y','2020-07-08 20:26:14','dongbin',NULL,NULL,'erp.com'),(26,'hr','人力资源中心','erp','erp_hr','ERP公司_人力资源中心',0,'Y','2020-07-08 20:26:32','dongbin',NULL,NULL,'erp.com'),(27,'tech','技术与产品中心','erp','erp_tech','ERP公司_技术与产品中心',0,'Y','2020-07-08 20:27:02','dongbin',NULL,NULL,'erp.com'),(28,'fa','计划财务中心','erp','erp_fa','ERP公司_计划财务中心',0,'Y','2020-07-08 20:28:02','dongbin',NULL,NULL,'erp.com'),(29,'jishu','技术部','tech','erp_tech_jishu','ERP公司_技术与产品中心_技术部',0,'Y','2020-07-08 20:28:25','dongbin',NULL,NULL,'erp.com'),(31,'chanpin','产品部','tech','erp_tech_chanpin','ERP公司_技术与产品中心_产品部',0,'Y','2020-07-08 20:29:12','dongbin',NULL,NULL,'erp.com'),(32,'yunwei','运维部','tech','erp_tech_yunwei','ERP公司_技术与产品中心_运维部',0,'Y','2020-07-08 20:29:31','dongbin',NULL,NULL,'erp.com'),(36,'sheji','设计组','chanpin','erp_tech_chanpin_sheji','ERP公司_技术与产品中心_产品部_设计组',0,'N','2020-07-08 20:35:41','dongbin','2020-07-08 20:38:37','dongbin','erp.com'),(37,'qianduan','前端组','jishu','erp_tech_jishu_qianduan','ERP公司_技术与产品中心_技术部_前端组',0,'Y','2020-07-08 20:37:07','dongbin',NULL,NULL,'erp.com'),(38,'java','JAVA组','jishu','erp_tech_jishu_java','ERP公司_技术与产品中心_技术部_JAVA组',0,'Y','2020-07-08 20:37:23','dongbin',NULL,NULL,'erp.com'),(39,'ceshi','测试组','jishu','erp_tech_jishu_ceshi','ERP公司_技术与产品中心_技术部_测试组',0,'Y','2020-07-08 20:37:32','dongbin',NULL,NULL,'erp.com'),(41,'produce','产品组','chanpin','erp_tech_chanpin_produce','ERP公司_技术与产品中心_产品部_产品组',0,'Y','2020-07-08 20:38:25','dongbin','2020-08-04 15:38:57','redragon','erp.com'),(42,'ui','UI组','chanpin','erp_tech_chanpin_ui','ERP公司_技术与产品中心_产品部_UI组',0,'Y','2020-07-08 20:38:51','dongbin',NULL,NULL,'erp.com'),(44,'yingjian','硬件组','yunwei','erp_tech_yunwei_yingjian','ERP公司_技术与产品中心_运维部_硬件组',0,'Y','2020-08-04 15:27:51','redragon',NULL,NULL,'erp.com'),(46,'kuaiji','会计部','fa','erp_fa_kuaiji','ERP公司_计划财务中心_会计部',0,'Y','2020-08-05 16:57:04','redragon',NULL,NULL,'erp.com'),(47,'chuna','出纳部','fa','erp_fa_chuna','ERP公司_计划财务中心_出纳部',0,'Y','2020-08-05 16:57:18','redragon',NULL,NULL,'erp.com'),(48,'zhaopin','招聘部','hr','erp_hr_zhaopin','ERP公司_人力资源中心_招聘部',0,'Y','2020-08-05 16:57:36','redragon',NULL,NULL,'erp.com'),(49,'peixun','培训部','hr','erp_hr_peixun','ERP公司_人力资源中心_培训部',0,'Y','2020-08-05 16:58:12','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `hr_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hr_position`
--

LOCK TABLES `hr_position` WRITE;
/*!40000 ALTER TABLE `hr_position` DISABLE KEYS */;
INSERT INTO `hr_position` VALUES (7,'java','java工程师','EMPLOYEE','Y','2020-07-10 17:26:46','dongbin','2020-08-03 16:26:44','redragon','erp.com'),(8,'product','产品经理','EMPLOYEE','Y','2020-07-10 17:27:06','dongbin','2020-08-03 16:26:39','redragon','erp.com'),(9,'employee','普通员工','EMPLOYEE','Y','2020-07-10 17:27:44','dongbin','2020-08-03 16:26:33','redragon','erp.com'),(10,'manager','经理','MANAGER','Y','2020-08-01 15:34:54','redragon','2020-08-03 16:26:26','redragon','erp.com'),(11,'hr','HR','EMPLOYEE','Y','2020-08-01 15:35:07','redragon','2020-08-03 16:26:20','redragon','erp.com'),(12,'finance','财务','EMPLOYEE','Y','2020-08-01 15:35:23','redragon','2020-08-03 16:26:13','redragon','erp.com');
/*!40000 ALTER TABLE `hr_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hr_staff`
--

LOCK TABLES `hr_staff` WRITE;
/*!40000 ALTER TABLE `hr_staff` DISABLE KEYS */;
INSERT INTO `hr_staff` VALUES (1,'RD001','RD001','李四','MALE','2020-07-02','WORK','','','dongbin','2020-07-04 22:17:10','dongbin','2020-08-04 14:59:42','redragon','erp.com'),(3,'RD000','RD000','王五','MALE','2020-07-04','WORK','130111111','aaa@163.com','admin','2020-07-04 23:01:51','dongbin','2020-08-01 15:37:10','redragon','erp.com'),(4,'STAFF-001','STAFF-001','张三','MALE',NULL,'WORK','','','redragon','2020-08-01 15:25:51','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `hr_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hr_staff_department_r`
--

LOCK TABLES `hr_staff_department_r` WRITE;
/*!40000 ALTER TABLE `hr_staff_department_r` DISABLE KEYS */;
INSERT INTO `hr_staff_department_r` VALUES (4,'STAFF-001','java','java','Y','2020-08-01 15:35:52','redragon',NULL,NULL,''),(5,'RD001','java','manager','Y','2020-08-01 15:36:36','redragon',NULL,NULL,''),(6,'RD000','jishu','manager','Y','2020-08-01 15:37:24','redragon',NULL,NULL,'');
/*!40000 ALTER TABLE `hr_staff_department_r` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `inv_input_head`
--

LOCK TABLES `inv_input_head` WRITE;
/*!40000 ALTER TABLE `inv_input_head` DISABLE KEYS */;
INSERT INTO `inv_input_head` VALUES (2,'input-001','PO_IN','PO','order-002','warehouse-001','2020-08-24','',1,'NEW','UNSUBMIT','STAFF-001','java','2020-08-24 15:52:59','redragon',NULL,NULL,'erp.com'),(3,'input-003','PO_IN','PO','order-003','warehouse-001','2020-09-10','',1,'NEW','APPROVE','STAFF-001','java','2020-09-10 20:05:54','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `inv_input_head` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `inv_input_line`
--

LOCK TABLES `inv_input_line` WRITE;
/*!40000 ALTER TABLE `inv_input_line` DISABLE KEYS */;
INSERT INTO `inv_input_line` VALUES (4,'457793479258329088','input-001','451655028259606528','Material-002',1,'全部到货',1,'Y','2020-08-24 16:34:41','redragon',NULL,NULL,'erp.com'),(5,'464007507156586496','input-003','464004911691911168','Material-003',1,'',1,'Y','2020-09-10 20:07:00','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `inv_input_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `inv_output_head`
--

LOCK TABLES `inv_output_head` WRITE;
/*!40000 ALTER TABLE `inv_output_head` DISABLE KEYS */;
INSERT INTO `inv_output_head` VALUES (2,'output-001','SO_OUT','SO','SO-002','warehouse-001','2020-08-24','',1,'NEW','UNSUBMIT','STAFF-001','java','2020-08-24 16:02:59','redragon',NULL,NULL,'erp.com'),(3,'output-002','SO_OUT','SO','SO-001','warehouse-001','2020-09-10','',1,'NEW','UNSUBMIT','STAFF-001','java','2020-09-10 20:13:33','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `inv_output_head` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `inv_output_line`
--

LOCK TABLES `inv_output_line` WRITE;
/*!40000 ALTER TABLE `inv_output_line` DISABLE KEYS */;
INSERT INTO `inv_output_line` VALUES (10,'457897424169390080','output-001','450964139417718784','M001',2,'出库',1,'Y','2020-08-24 23:27:43','redragon',NULL,NULL,'erp.com'),(11,'464009225919451136','output-002','444118220411949056','M001',12,'',1,'Y','2020-09-10 20:13:50','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `inv_output_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `inv_stock`
--

LOCK TABLES `inv_stock` WRITE;
/*!40000 ALTER TABLE `inv_stock` DISABLE KEYS */;
INSERT INTO `inv_stock` VALUES (1,'455667810432897024','warehouse-001','M001',100,'N',NULL,NULL,NULL,'','Y','STAFF-001','java','2020-08-18 19:48:02','redragon',NULL,NULL,'erp.com'),(7,'455692439159492608','warehouse-001','Material-002',50,'N',NULL,NULL,NULL,'服务器','Y','STAFF-001','java','2020-08-18 21:25:54','redragon',NULL,NULL,'erp.com'),(17,'457897424462991360','warehouse-001','M001',-2,'Y','OUTPUT','output-001','457897424169390080','出库单自动创建','Y','STAFF-001','java','2020-08-24 23:27:43','redragon',NULL,NULL,'erp.com'),(20,'458590351220723712','warehouse-001','Material-003',10000,'N',NULL,NULL,NULL,'库存初始化，基础版本，限量发售','Y','STAFF-001','java','2020-08-26 21:21:10','redragon',NULL,NULL,'erp.com'),(23,'459336978747281408','warehouse-001','M001',-8,'N','CHECK','check459263763475779584','459263763488362496','库存盘点[盘亏]自动创建','Y','STAFF-001','java','2020-08-28 22:48:00','redragon',NULL,NULL,'erp.com'),(24,'459336979380621312','warehouse-001','Material-002',10,'N','CHECK','check459263763475779584','459263763488362497','库存盘点[盘盈]自动创建','Y','STAFF-001','java','2020-08-28 22:48:00','redragon',NULL,NULL,'erp.com'),(29,'470131792744992768','warehouse-001','Material-003',1,'N','INPUT','input-003','464007507156586496','入库单自动创建','Y','STAFF-001','java','2020-09-27 17:42:44','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `inv_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `inv_stock_check_head`
--

LOCK TABLES `inv_stock_check_head` WRITE;
/*!40000 ALTER TABLE `inv_stock_check_head` DISABLE KEYS */;
INSERT INTO `inv_stock_check_head` VALUES (3,'check459263763475779584','中央仓库[2020-08]盘点','warehouse-001','2020-08','2020.8.28-库管员','NEW','APPROVE','STAFF-001','java','2020-08-28 18:03:03','redragon',NULL,NULL,'erp.com'),(4,'check470872472794615808','中央仓库[2020-09]盘点','warehouse-001','2020-09','','NEW','UNSUBMIT','STAFF-001','java','2020-09-29 18:50:10','redragon',NULL,NULL,'erp.com'),(5,'check470873610067562496','中央仓库[2020-09]盘点','warehouse-001','2020-09','','NEW','UNSUBMIT','STAFF-001','java','2020-09-29 18:51:03','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `inv_stock_check_head` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `inv_stock_check_line`
--

LOCK TABLES `inv_stock_check_line` WRITE;
/*!40000 ALTER TABLE `inv_stock_check_line` DISABLE KEYS */;
INSERT INTO `inv_stock_check_line` VALUES (16,'459263763488362496','check459263763475779584','M001',98,90,'11','Y','2020-08-28 18:03:03','redragon',NULL,NULL,'erp.com'),(17,'459263763488362497','check459263763475779584','Material-002',50,60,'22','Y','2020-08-28 18:03:03','redragon',NULL,NULL,'erp.com'),(18,'459263763488362498','check459263763475779584','Material-003',10000,10000,'33','Y','2020-08-28 18:03:03','redragon',NULL,NULL,'erp.com'),(19,'470872472803004416','check470872472794615808','M001',90,60,'','Y','2020-09-29 18:50:10','redragon',NULL,NULL,'erp.com'),(20,'470872472803004417','check470872472794615808','Material-002',60,90,'','Y','2020-09-29 18:50:10','redragon',NULL,NULL,'erp.com'),(21,'470872472803004418','check470872472794615808','Material-003',10001,10001,'','Y','2020-09-29 18:50:10','redragon',NULL,NULL,'erp.com'),(22,'470873754318065664','check470873610067562496','M001',90,90,'','Y','2020-09-29 18:51:03','redragon',NULL,NULL,'erp.com'),(23,'470873757132443648','check470873610067562496','Material-002',60,60,'','Y','2020-09-29 18:51:03','redragon',NULL,NULL,'erp.com'),(24,'470873759925850112','check470873610067562496','Material-003',10001,10000,'','Y','2020-09-29 18:51:03','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `inv_stock_check_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `inv_warehouse`
--

LOCK TABLES `inv_warehouse` WRITE;
/*!40000 ALTER TABLE `inv_warehouse` DISABLE KEYS */;
INSERT INTO `inv_warehouse` VALUES (2,'warehouse-001','中央仓库','北京总部','','Y','2020-08-18 16:47:43','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `inv_warehouse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `md_customer`
--

LOCK TABLES `md_customer` WRITE;
/*!40000 ALTER TABLE `md_customer` DISABLE KEYS */;
INSERT INTO `md_customer` VALUES (1,'CUST001','客户1','company','北京东城区','010','china','110000','common','测试','N','Y','UNSUBMIT','2020-07-12 17:13:30','dongbin','2020-07-20 19:49:59','dongbin','erp.com'),(4,'CUST000','我的公司','company','','','china','','common','','Y','Y','REJECT','2020-07-20 19:50:40','dongbin','2020-07-20 19:51:31','dongbin','erp.com'),(5,'CUSTOMER-001','北京电脑制造有限公司','company','北京朝阳区望京','010-123456','china','310000','common','IT 制造','N','Y','APPROVE','2020-07-31 23:40:00','dongbin','2020-07-31 23:40:23','dongbin','erp.com');
/*!40000 ALTER TABLE `md_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `md_customer_bank`
--

LOCK TABLES `md_customer_bank` WRITE;
/*!40000 ALTER TABLE `md_customer_bank` DISABLE KEYS */;
INSERT INTO `md_customer_bank` VALUES (2,'CUST001','CITIC','北京分行','111','Y','2020-07-14 15:17:37','dongbin',NULL,NULL,'erp.com'),(3,'CUST001','CMB','北京朝阳区分行','123','Y','2020-07-14 15:17:57','dongbin',NULL,NULL,'erp.com'),(4,'CUSTOMER-001','CITIC','北京分行','9559990022222000000','Y','2020-07-31 23:54:31','dongbin',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `md_customer_bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `md_customer_contact`
--

LOCK TABLES `md_customer_contact` WRITE;
/*!40000 ALTER TABLE `md_customer_contact` DISABLE KEYS */;
INSERT INTO `md_customer_contact` VALUES (1,'CUST001','联系人','130','CEO','Y','2020-07-13 15:49:15','dongbin',NULL,NULL,'erp.com'),(10,'CUST001','联系人2','131','COO','Y','2020-07-13 22:28:17','dongbin',NULL,NULL,'erp.com'),(11,'CUSTOMER-001','王五','12345678980','CEO','Y','2020-07-31 23:52:50','dongbin',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `md_customer_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `md_customer_license`
--

LOCK TABLES `md_customer_license` WRITE;
/*!40000 ALTER TABLE `md_customer_license` DISABLE KEYS */;
INSERT INTO `md_customer_license` VALUES (1,'CUST001','COM123','法人','yxzr','','2020-07-12','Y','2020-07-12 21:11:16','dongbin','2020-08-04 23:30:44','redragon','erp.com'),(3,'CUSTOMER-001','911101081111100000','王五','yxzr','123','2020-07-31','Y','2020-07-31 23:50:36','dongbin',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `md_customer_license` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `md_finance_department`
--

LOCK TABLES `md_finance_department` WRITE;
/*!40000 ALTER TABLE `md_finance_department` DISABLE KEYS */;
/*!40000 ALTER TABLE `md_finance_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `md_finance_subject`
--

LOCK TABLES `md_finance_subject` WRITE;
/*!40000 ALTER TABLE `md_finance_subject` DISABLE KEYS */;
INSERT INTO `md_finance_subject` VALUES (2,'0','根科目',NULL,'0','根科目',0,'Y','2020-07-27 17:44:20','dongbin',NULL,NULL,'erp.com'),(5,'10','资产类','0','0_1','根科目_资产类',0,'Y','2020-07-27 17:55:17','dongbin',NULL,NULL,'erp.com'),(6,'20','负债类','0','0_2','根科目_负债类',0,'Y','2020-07-27 17:55:27','dongbin',NULL,NULL,'erp.com'),(7,'30','共同类','0','0_3','根科目_共同类',0,'Y','2020-07-27 17:56:07','dongbin',NULL,NULL,'erp.com'),(8,'40','所有者权益类','0','0_4','根科目_所有者权益类',0,'Y','2020-07-27 17:56:50','dongbin',NULL,NULL,'erp.com'),(9,'50','成本类','0','0_5','根科目_成本类',0,'Y','2020-07-27 18:03:00','dongbin',NULL,NULL,'erp.com'),(11,'60','损益类','0','0_6','根科目_损益类',0,'Y','2020-07-27 18:03:20','dongbin',NULL,NULL,'erp.com'),(12,'1001','库存现金','1','0_1_1001','根科目_资产类_库存现金',0,'Y','2020-07-27 18:05:38','dongbin',NULL,NULL,'erp.com'),(13,'1002','银行存款','1','0_1_1002','根科目_资产类_银行存款',0,'Y','2020-07-27 18:05:50','dongbin',NULL,NULL,'erp.com'),(14,'1122','应收账款','1','0_1_1122','根科目_资产类_应收账款',0,'Y','2020-07-27 18:06:09','dongbin',NULL,NULL,'erp.com'),(16,'2202','应付账款','2','0_2_2202','根科目_负债类_应付账款',0,'Y','2020-07-27 18:06:57','dongbin',NULL,NULL,'erp.com'),(17,'5201','劳务成本','5','0_5_5201','根科目_成本类_劳务成本',0,'Y','2020-07-27 18:07:47','dongbin',NULL,NULL,'erp.com'),(19,'6401','主营业务成本','6','0_6_6401','根科目_损益类_主营业务成本',0,'Y','2020-07-27 18:08:34','dongbin',NULL,NULL,'erp.com'),(20,'6801','所得税','6','0_6_6801','根科目_损益类_所得税',0,'Y','2020-07-27 18:08:59','dongbin',NULL,NULL,'erp.com'),(22,'1121','应收票据','10','0_1_1121','根科目_资产类_应收票据',0,'Y','2020-08-05 17:07:56','redragon',NULL,NULL,'erp.com'),(24,'1123','预付帐款','10','0_1_1123','根科目_资产类_预付帐款',0,'Y','2020-08-05 17:08:23','redragon',NULL,NULL,'erp.com'),(26,'1231','其它应收款','10','0_1_1231','根科目_资产类_其它应收款',0,'Y','2020-08-05 17:08:58','redragon',NULL,NULL,'erp.com'),(27,'1401','材料采购','10','0_1_1401','根科目_资产类_材料采购',0,'Y','2020-08-05 17:09:20','redragon',NULL,NULL,'erp.com'),(28,'1403','原材料','10','0_1_1403','根科目_资产类_原材料',0,'Y','2020-08-05 17:09:37','redragon',NULL,NULL,'erp.com'),(29,'1406','库存商品','10','0_1_1406','根科目_资产类_库存商品',0,'Y','2020-08-05 17:09:56','redragon',NULL,NULL,'erp.com'),(30,'1601','固定资产','10','0_1_1601','根科目_资产类_固定资产',0,'Y','2020-08-05 17:10:33','redragon',NULL,NULL,'erp.com'),(31,'1602','累计折旧','10','0_1_1602','根科目_资产类_累计折旧',0,'Y','2020-08-05 17:10:44','redragon',NULL,NULL,'erp.com'),(32,'2001','短期借款','20','0_2_2001','根科目_负债类_短期借款',0,'Y','2020-08-05 17:11:30','redragon',NULL,NULL,'erp.com'),(33,'2201','应付票据','20','0_2_2201','根科目_负债类_应付票据',0,'Y','2020-08-05 17:11:57','redragon',NULL,NULL,'erp.com'),(34,'2205','预收帐款','20','0_2_2205','根科目_负债类_预收帐款',0,'Y','2020-08-05 17:12:19','redragon',NULL,NULL,'erp.com'),(35,'2211','应付职工薪酬','20','0_2_2211','根科目_负债类_应付职工薪酬',0,'Y','2020-08-05 17:12:44','redragon',NULL,NULL,'erp.com'),(36,'2221','应交税费','20','0_2_2221','根科目_负债类_应交税费',0,'Y','2020-08-05 17:13:02','redragon',NULL,NULL,'erp.com'),(37,'2241','其他应付款','20','0_2_2241','根科目_负债类_其他应付款',0,'Y','2020-08-05 17:13:17','redragon',NULL,NULL,'erp.com'),(38,'3001','清算资金往来','30','0_3_3001','根科目_共同类_清算资金往来',0,'Y','2020-08-05 17:14:18','redragon',NULL,NULL,'erp.com'),(39,'3002','外汇买卖','30','0_3_3002','根科目_共同类_外汇买卖',0,'Y','2020-08-05 17:14:28','redragon',NULL,NULL,'erp.com'),(40,'4001','实收资本','40','0_4_4001','根科目_所有者权益类_实收资本',0,'Y','2020-08-05 17:14:45','redragon',NULL,NULL,'erp.com'),(41,'4002','资本公积','40','0_4_4002','根科目_所有者权益类_资本公积',0,'Y','2020-08-05 17:14:59','redragon',NULL,NULL,'erp.com'),(42,'4103','本年利润','40','0_4_4103','根科目_所有者权益类_本年利润',0,'Y','2020-08-05 17:15:19','redragon',NULL,NULL,'erp.com'),(43,'4104','利润分配','40','0_4_4104','根科目_所有者权益类_利润分配',0,'Y','2020-08-05 17:15:34','redragon',NULL,NULL,'erp.com'),(44,'5001','生产成本','50','0_5_5001','根科目_成本类_生产成本',0,'Y','2020-08-05 17:15:56','redragon',NULL,NULL,'erp.com'),(45,'5101','制造费用','50','0_5_5101','根科目_成本类_制造费用',0,'Y','2020-08-05 17:16:12','redragon',NULL,NULL,'erp.com'),(46,'5301','研发支出','50','0_5_5301','根科目_成本类_研发支出',0,'Y','2020-08-05 17:16:26','redragon',NULL,NULL,'erp.com'),(47,'6001','主营业务收入','60','0_6_6001','根科目_损益类_主营业务收入',0,'Y','2020-08-05 17:18:17','redragon',NULL,NULL,'erp.com'),(48,'6051','其他业务收入','60','0_6_6051','根科目_损益类_其他业务收入',0,'Y','2020-08-05 17:18:53','redragon',NULL,NULL,'erp.com'),(49,'6111','投资收益','60','0_6_6111','根科目_损益类_投资收益',0,'Y','2020-08-05 17:19:15','redragon',NULL,NULL,'erp.com'),(50,'6301','营业外收入','60','0_6_6301','根科目_损益类_营业外收入',0,'Y','2020-08-05 17:19:33','redragon',NULL,NULL,'erp.com'),(51,'6402','其它业务成本','60','0_6_6402','根科目_损益类_其它业务成本',0,'Y','2020-08-05 17:19:50','redragon',NULL,NULL,'erp.com'),(52,'6601','销售费用','60','0_6_6601','根科目_损益类_销售费用',0,'Y','2020-08-05 17:20:42','redragon',NULL,NULL,'erp.com'),(53,'6602','管理费用','60','0_6_6602','根科目_损益类_管理费用',0,'Y','2020-08-05 17:20:55','redragon',NULL,NULL,'erp.com'),(54,'6603','财务费用','60','0_6_6603','根科目_损益类_财务费用',0,'Y','2020-08-05 17:21:20','redragon',NULL,NULL,'erp.com'),(55,'6711','营业外支出','60','0_6_6711','根科目_损益类_营业外支出',0,'Y','2020-08-05 17:22:32','redragon',NULL,NULL,'erp.com'),(57,'1402','在途物资','10','0_1_1402','根科目_资产类_在途物资',0,'Y','2020-09-21 21:48:06','redragon',NULL,NULL,'erp.com'),(58,'220201','应计负债','2202','0_2_2202_220201','根科目_负债类_应付账款_应计负债',0,'Y','2020-09-21 22:37:06','redragon',NULL,NULL,'erp.com'),(59,'220202','经营性','2202','0_2_2202_220202','根科目_负债类_应付账款_经营性',0,'Y','2020-09-21 22:37:35','redragon',NULL,NULL,'erp.com'),(60,'220203','应付暂估','2202','0_2_2202_220203','根科目_负债类_应付账款_应付暂估',0,'Y','2020-09-21 22:38:33','redragon',NULL,NULL,'erp.com'),(61,'222101','增值税','2221','0_2_2221_222101','根科目_负债类_应交税费_增值税',0,'Y','2020-09-21 22:42:13','redragon',NULL,NULL,'erp.com'),(62,'22210101','进项税','222101','0_2_2221_222101_2220101','根科目_负债类_应交税费_增值税_进项税',0,'Y','2020-09-21 22:42:46','redragon',NULL,NULL,'erp.com'),(63,'22210102','销项税','222101','0_2_2221_222101_22210102','根科目_负债类_应交税费_增值税_销项税',0,'Y','2020-09-21 22:43:00','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `md_finance_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `md_material`
--

LOCK TABLES `md_material` WRITE;
/*!40000 ALTER TABLE `md_material` DISABLE KEYS */;
INSERT INTO `md_material` VALUES (1,'M001','笔记本','MATERIAL','MC121-1','AN',NULL,'IBM X1','AN','','Y','UNSUBMIT','2020-07-14 23:36:12','dongbin','2020-08-02 22:52:26','redragon','erp.com'),(3,'Material-001','软件运维服务','MATTER','MC221','AN',NULL,'1','AN','','Y','UNSUBMIT','2020-07-31 23:59:46','dongbin','2020-09-04 22:57:17','redragon','erp.com'),(4,'Matter-001','软件二次开发','MATTER','MC211',NULL,NULL,'',NULL,'','Y','REJECT','2020-08-02 23:36:21','redragon',NULL,NULL,'erp.com'),(5,'Material-002','IBM服务器','MATERIAL','MC122-1','AN',NULL,'IBX最新型号','AN','','Y','SUBMIT','2020-08-02 23:44:35','redragon','2020-08-02 23:47:14','redragon','erp.com'),(6,'Material-003','赤龙ERP','MATERIAL','MC123-1','AN',NULL,'基础产品','AN','电子版','Y','UNSUBMIT','2020-08-26 21:17:17','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `md_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `md_material_category`
--

LOCK TABLES `md_material_category` WRITE;
/*!40000 ALTER TABLE `md_material_category` DISABLE KEYS */;
INSERT INTO `md_material_category` VALUES (1,'MC000','根类型',NULL,'MC000','根类型',0,'Y','2020-08-02 16:24:55','redragon',NULL,NULL,'erp.com'),(2,'MC100','物料','MC000','MC000_MC100','根类型_物料',0,'Y','2020-08-02 16:25:39','redragon',NULL,NULL,'erp.com'),(3,'MC200','事项','MC000','MC000_MC200','根类型_事项',0,'Y','2020-08-02 16:25:54','redragon',NULL,NULL,'erp.com'),(5,'MC110','原材料','MC100','MC000_MC100_MC110','根类型_物料_原材料',0,'Y','2020-08-02 16:28:29','redragon',NULL,NULL,'erp.com'),(7,'MC120','成品','MC100','MC000_MC100_MC120','根类型_物料_成品',0,'Y','2020-08-02 16:28:58','redragon',NULL,NULL,'erp.com'),(8,'MC121','办公用品','MC120','MC000_MC100_MC120_MC121','根类型_物料_成品_办公用品',0,'Y','2020-08-02 16:30:10','redragon',NULL,NULL,'erp.com'),(9,'MC121-1','电脑','MC121','MC000_MC100_MC120_MC121_MC121-1','根类型_物料_成品_办公用品_电脑',0,'Y','2020-08-02 16:30:29','redragon',NULL,NULL,'erp.com'),(10,'MC122','固定资产','MC120','MC000_MC100_MC120_MC122','根类型_物料_成品_固定资产',0,'Y','2020-08-02 16:31:36','redragon',NULL,NULL,'erp.com'),(11,'MC122-1','服务器','MC122','MC000_MC100_MC120_MC122_MC122-1','根类型_物料_成品_固定资产_服务器',0,'Y','2020-08-02 16:31:47','redragon',NULL,NULL,'erp.com'),(12,'MC210','经营类','MC200','MC000_MC200_MC210','根类型_事项_经营类',0,'Y','2020-08-02 16:32:54','redragon',NULL,NULL,'erp.com'),(13,'MC220','服务类','MC200','MC000_MC200_MC220','根类型_事项_服务类',0,'Y','2020-08-02 16:33:28','redragon',NULL,NULL,'erp.com'),(14,'MC230','特殊事项','MC200','MC000_MC200_MC230','根类型_事项_特殊事项',0,'Y','2020-08-02 16:33:47','redragon',NULL,NULL,'erp.com'),(15,'MC211','二次开发','MC210','MC000_MC200_MC210_MC211','根类型_事项_经营类_二次开发',0,'Y','2020-08-02 16:34:25','redragon',NULL,NULL,'erp.com'),(16,'MC212','项目实施','MC210','MC000_MC200_MC210_MC212','根类型_事项_经营类_项目实施',0,'Y','2020-08-02 16:34:54','redragon',NULL,NULL,'erp.com'),(17,'MC221','项目维保','MC220','MC000_MC200_MC220_MC221','根类型_事项_服务类_项目维保',0,'Y','2020-08-02 16:35:27','redragon',NULL,NULL,'erp.com'),(18,'MC231','不可抗力损失','MC230','MC000_MC200_MC230_MC231','根类型_事项_特殊事项_不可抗力损失',0,'Y','2020-08-02 16:37:03','redragon',NULL,NULL,'erp.com'),(19,'MC123','软件产品','MC120','MC000_MC100_MC120_MC123','根类型_物料_成品_软件产品',0,'Y','2020-08-26 21:15:33','redragon',NULL,NULL,'erp.com'),(20,'MC123-1','ERP','MC123','MC000_MC100_MC120_MC123_MC123-1','根类型_物料_成品_软件产品_ERP',0,'Y','2020-08-26 21:15:54','redragon','2020-08-26 21:16:06','redragon','erp.com');
/*!40000 ALTER TABLE `md_material_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `md_project`
--

LOCK TABLES `md_project` WRITE;
/*!40000 ALTER TABLE `md_project` DISABLE KEYS */;
INSERT INTO `md_project` VALUES (2,'P001','北京新项目','','ZY','2020-07-16','2020-07-31','Y','UNSUBMIT','2020-07-16 18:25:31','dongbin','2020-07-16 18:25:51','dongbin','erp.com'),(3,'P002','上海新项目','','DL','2020-07-16',NULL,'Y','UNSUBMIT','2020-07-16 18:39:40','dongbin',NULL,NULL,'erp.com'),(4,'PROJECT-001','本部员工计算机采购项目','','DL','2020-08-01','2020-09-30','Y','SUBMIT','2020-08-01 00:05:49','dongbin','2020-08-01 00:06:10','dongbin','erp.com');
/*!40000 ALTER TABLE `md_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `md_vendor`
--

LOCK TABLES `md_vendor` WRITE;
/*!40000 ALTER TABLE `md_vendor` DISABLE KEYS */;
INSERT INTO `md_vendor` VALUES (1,'VENDOR001','甲乙丙丁有限公司','company','','010','china','310000','common','','N','Y','UNSUBMIT','2020-07-14 18:34:01','dongbin','2020-08-13 16:32:26','redragon','erp.com'),(2,'VENDOR000','我的公司','company','','','china','','common','','Y','Y','UNSUBMIT','2020-07-20 19:52:39','dongbin','2020-07-20 20:24:17','dongbin','erp.com');
/*!40000 ALTER TABLE `md_vendor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `md_vendor_bank`
--

LOCK TABLES `md_vendor_bank` WRITE;
/*!40000 ALTER TABLE `md_vendor_bank` DISABLE KEYS */;
INSERT INTO `md_vendor_bank` VALUES (1,'VENDOR001','CITIC','北京分行','123','Y','2020-07-14 22:14:32','dongbin',NULL,NULL,'erp.com'),(3,'VENDOR001','BOC','上海分行','456','Y','2020-07-20 21:01:56','dongbin',NULL,NULL,'erp.com'),(4,'VENDOR000','ABC','北京分行','123456','Y','2020-08-06 19:14:02','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `md_vendor_bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `md_vendor_contact`
--

LOCK TABLES `md_vendor_contact` WRITE;
/*!40000 ALTER TABLE `md_vendor_contact` DISABLE KEYS */;
INSERT INTO `md_vendor_contact` VALUES (2,'VENDOR001','联系人2','131','COO','Y','2020-07-14 22:11:41','dongbin',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `md_vendor_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `md_vendor_license`
--

LOCK TABLES `md_vendor_license` WRITE;
/*!40000 ALTER TABLE `md_vendor_license` DISABLE KEYS */;
INSERT INTO `md_vendor_license` VALUES (1,'VENDOR001','COM321','法人','yxzr','','2020-07-14','Y','2020-07-14 18:50:52','dongbin','2020-08-04 23:31:06','redragon','erp.com');
/*!40000 ALTER TABLE `md_vendor_license` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `po_head`
--

LOCK TABLES `po_head` WRITE;
/*!40000 ALTER TABLE `po_head` DISABLE KEYS */;
INSERT INTO `po_head` VALUES (2,'order-001','POTYPE01','测试采购订单20200716','','P001','VENDOR001','rmb',0.00,NULL,NULL,'2020-07-20','',NULL,1,'NEW','UNSUBMIT','N','N','RD001','produce','2020-07-20 14:08:19','dongbin',NULL,NULL,'erp.com'),(3,'order-002','POTYPE02','测试采购订单20200720','','P002','VENDOR001','rmb',5000.00,NULL,NULL,'2020-07-01','',NULL,1,'NEW','REJECT','N','N','RD001','produce','2020-07-20 14:19:10','dongbin','2020-09-10 19:30:22','redragon','erp.com'),(4,'order-003','POTYPE01','本部员工计算机采购订单20200801','','PROJECT-001','VENDOR001','rmb',2000.00,'2020-08-01','2020-08-31','2020-08-01','',NULL,1,'NEW','APPROVE','N','N','STAFF-001','java','2020-08-01 15:40:23','redragon','2020-08-05 20:10:16','redragon','erp.com');
/*!40000 ALTER TABLE `po_head` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `po_line`
--

LOCK TABLES `po_line` WRITE;
/*!40000 ALTER TABLE `po_line` DISABLE KEYS */;
INSERT INTO `po_line` VALUES (1,'443745645320130560','order-001','M001',2,2350.00,4700.00,'AN','办公',1,'Y','2020-07-16 22:13:36','dongbin','2020-07-17 00:06:23','dongbin','erp.com'),(2,'443768355916009472','order-001','M001',1,1550.00,1550.00,'AN','礼物',1,'Y','2020-07-16 23:43:51','dongbin','2020-07-17 00:06:26','dongbin','erp.com'),(5,'445075866568806400','order-002','M001',10,1000.00,10000.00,'AN','',1,'Y','2020-07-20 14:19:25','dongbin',NULL,NULL,'erp.com'),(7,'450962333199421440','order-003','M001',10,10000.00,100000.00,'AN','',1,'Y','2020-08-05 20:10:08','redragon','2020-09-10 19:56:14','redragon','erp.com'),(8,'451655028259606528','order-002','Material-002',1,1500.00,1500.00,'AN','',1,'Y','2020-08-07 18:02:40','redragon',NULL,NULL,'erp.com'),(9,'463998263413886976','order-002','Matter-001',1,20000.00,20000.00,'CI','',1,'Y','2020-09-10 19:30:16','redragon',NULL,NULL,'erp.com'),(10,'464004763909804032','order-003','Matter-001',1,10000.00,10000.00,'CI','',1,'Y','2020-09-10 19:56:06','redragon',NULL,NULL,'erp.com'),(11,'464004911691911168','order-003','Material-003',1,12000.00,12000.00,'AN','',1,'Y','2020-09-10 19:56:41','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `po_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `so_head`
--

LOCK TABLES `so_head` WRITE;
/*!40000 ALTER TABLE `so_head` DISABLE KEYS */;
INSERT INTO `so_head` VALUES (2,'SO-001','SOTYPE01','北京分公司2020-07销售合同','','P001','CUST001','rmb',0.00,NULL,NULL,'2020-07-23','',NULL,1,'NEW','UNSUBMIT','N','N','RD001','produce','2020-07-23 21:17:27','dongbin','2020-09-10 20:12:08','redragon','erp.com'),(3,'SO-002','SOTYPE01','本部员工老旧电脑销售订单20200801','','PROJECT-001','CUSTOMER-001','rmb',20000.00,NULL,NULL,'2020-08-01','',NULL,1,'NEW','SUBMIT','N','N','STAFF-001','java','2020-08-01 15:43:33','redragon','2020-08-05 20:17:33','redragon','erp.com');
/*!40000 ALTER TABLE `so_head` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `so_line`
--

LOCK TABLES `so_line` WRITE;
/*!40000 ALTER TABLE `so_line` DISABLE KEYS */;
INSERT INTO `so_line` VALUES (1,'444118157677744128','SO-001','M001',1,1500.00,1500.00,'AN','',1,'Y','2020-07-17 22:53:50','dongbin',NULL,NULL,'erp.com'),(2,'444118220411949056','SO-001','M001',20,10000.00,200000.00,'AN','办公',1,'Y','2020-07-17 22:54:05','dongbin','2020-07-17 22:54:18','dongbin','erp.com'),(4,'450964139417718784','SO-002','M001',2,10000.00,20000.00,'AN','',1,'Y','2020-08-05 20:17:19','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `so_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_auth`
--

LOCK TABLES `sys_auth` WRITE;
/*!40000 ALTER TABLE `sys_auth` DISABLE KEYS */;
INSERT INTO `sys_auth` VALUES (13,'sysDatasetType_menu_auth','数据字典菜单权限','menu','Y','2020-08-15 13:02:17','redragon',NULL,NULL,'erp.com'),(14,'sysUser_menu_auth','用户管理菜单权限','menu','Y','2020-08-15 13:03:41','redragon',NULL,NULL,'erp.com'),(15,'sysRole_menu_auth','角色管理菜单权限','menu','Y','2020-08-15 13:04:30','redragon',NULL,NULL,'erp.com'),(16,'sysAuth_menu_auth','权限管理菜单权限','menu','Y','2020-08-15 13:05:19','redragon',NULL,NULL,'erp.com'),(17,'hrStaff_menu_auth','职员管理菜单权限','menu','Y','2020-08-15 13:07:04','redragon',NULL,NULL,'erp.com'),(18,'hrPosition_menu_auth','岗位管理菜单权限','menu','Y','2020-08-15 13:07:52','redragon',NULL,NULL,'erp.com'),(19,'hrDepartment_menu_auth','部门管理菜单权限','menu','Y','2020-08-15 13:08:32','redragon',NULL,NULL,'erp.com'),(20,'hrStaffDepartmentR_menu_auth','职员关联部门菜单权限','menu','Y','2020-08-15 13:09:11','redragon',NULL,NULL,'erp.com'),(21,'mdCustomer_menu_auth','客户管理菜单权限','menu','Y','2020-08-15 13:09:53','redragon',NULL,NULL,'erp.com'),(22,'mdVendor_menu_auth','供应商管理菜单权限','menu','Y','2020-08-15 13:10:47','redragon',NULL,NULL,'erp.com'),(23,'mdMaterialCategory_menu_auth','物料与事项类型菜单权限','menu','Y','2020-08-15 13:12:13','redragon',NULL,NULL,'erp.com'),(24,'mdMaterial_menu_auth','物料与事项菜单权限','menu','Y','2020-08-15 13:12:56','redragon',NULL,NULL,'erp.com'),(25,'mdProject_menu_auth','项目管理菜单权限','menu','Y','2020-08-15 13:13:32','redragon',NULL,NULL,'erp.com'),(26,'mdFinanceSubject_menu_auth','科目管理菜单权限','menu','Y','2020-08-15 13:14:20','redragon',NULL,NULL,'erp.com'),(27,'poHead_menu_auth','采购订单菜单权限','menu','Y','2020-08-15 13:15:13','redragon',NULL,NULL,'erp.com'),(28,'soHead_menu_auth','销售订单菜单权限','menu','Y','2020-08-15 13:15:46','redragon',NULL,NULL,'erp.com'),(29,'apInvoiceHead_menu_auth','采购发票单菜单权限','menu','Y','2020-08-15 13:16:52','redragon',NULL,NULL,'erp.com'),(30,'arInvoiceHead_menu_auth','销售发票单菜单权限','menu','Y','2020-08-15 13:17:34','redragon',NULL,NULL,'erp.com'),(31,'finVoucherHead_menu_auth','凭证管理菜单权限','menu','Y','2020-08-15 13:18:33','redragon',NULL,NULL,'erp.com'),(32,'finVoucherModelHead_menu_auth','凭证模板菜单权限','menu','Y','2020-08-15 13:19:12','redragon',NULL,NULL,'erp.com'),(33,'finVoucherModelHead_control_auth','凭证模板操作权限','control','Y','2020-08-15 15:06:39','redragon',NULL,NULL,'erp.com'),(34,'finVoucherHead_control_auth','凭证管理操作权限','control','Y','2020-08-15 15:07:28','redragon',NULL,NULL,'erp.com'),(35,'arInvoiceHead_control_auth','销售发票单操作权限','control','Y','2020-08-15 15:08:16','redragon',NULL,NULL,'erp.com'),(36,'apInvoiceHead_control_auth','采购发票单操作权限','control','Y','2020-08-15 15:08:54','redragon',NULL,NULL,'erp.com'),(38,'soHead_control_auth','销售订单操作权限','control','Y','2020-08-15 15:10:00','redragon',NULL,NULL,'erp.com'),(39,'poHead_control_auth','采购订单操作权限','control','Y','2020-08-15 15:10:33','redragon',NULL,NULL,'erp.com'),(40,'mdFinanceSubject_control_auth','科目管理操作权限','control','Y','2020-08-15 15:10:57','redragon',NULL,NULL,'erp.com'),(41,'mdProject_control_auth','项目管理操作权限','control','Y','2020-08-15 15:11:30','redragon',NULL,NULL,'erp.com'),(42,'mdMaterial_control_auth','物料与事项操作权限','control','Y','2020-08-15 15:11:54','redragon',NULL,NULL,'erp.com'),(43,'mdMaterialCategory_control_auth','物料与事项类型操作权限','control','Y','2020-08-15 15:12:18','redragon',NULL,NULL,'erp.com'),(44,'mdVendor_control_auth','供应商管理操作权限','control','Y','2020-08-15 15:13:17','redragon',NULL,NULL,'erp.com'),(45,'mdCustomer_control_auth','客户管理操作权限','control','Y','2020-08-15 15:13:40','redragon',NULL,NULL,'erp.com'),(46,'hrStaffDepartmentR_control_auth','职员关联部门操作权限','control','Y','2020-08-15 15:14:04','redragon',NULL,NULL,'erp.com'),(47,'hrDepartment_control_auth','部门管理操作权限','control','Y','2020-08-15 15:14:25','redragon',NULL,NULL,'erp.com'),(48,'hrPosition_control_auth','岗位管理操作权限','control','Y','2020-08-15 15:14:50','redragon',NULL,NULL,'erp.com'),(49,'hrStaff_control_auth','职员管理操作权限','control','Y','2020-08-15 15:15:13','redragon',NULL,NULL,'erp.com'),(50,'sysAuth_control_auth','权限管理操作权限','control','Y','2020-08-15 15:15:37','redragon',NULL,NULL,'erp.com'),(51,'sysRole_control_auth','角色管理操作权限','control','Y','2020-08-15 15:16:00','redragon',NULL,NULL,'erp.com'),(52,'sysUser_control_auth','用户管理操作权限','control','Y','2020-08-15 15:16:29','redragon',NULL,NULL,'erp.com'),(53,'sysDatasetType_control_auth','数据字典操作权限','control','Y','2020-08-15 15:16:54','redragon',NULL,NULL,'erp.com'),(54,'private_data_auth','个人数据权限','data','Y','2020-08-16 12:15:36','redragon',NULL,NULL,'erp.com'),(55,'public_data_auth','公共数据权限','data','Y','2020-08-16 12:16:49','redragon',NULL,NULL,'erp.com'),(56,'org_level0_data_auth','本部门数据权限','data','Y','2020-08-16 12:24:40','redragon',NULL,NULL,'erp.com'),(57,'org_level1_data_auth','上级部门数据权限','data','Y','2020-08-16 12:25:00','redragon',NULL,NULL,'erp.com'),(58,'org_level2_data_auth','上上级部门数据权限','data','Y','2020-08-16 12:26:15','redragon',NULL,NULL,'erp.com'),(59,'invWarehouse_menu_auth','仓库菜单权限','menu','Y','2020-08-17 23:19:42','redragon',NULL,NULL,'erp.com'),(60,'invWarehouse_control_auth','仓库操作权限','control','Y','2020-08-17 23:20:01','redragon',NULL,NULL,'erp.com'),(61,'invStock_menu_auth','库存菜单权限','menu','Y','2020-08-18 16:54:59','redragon',NULL,NULL,'erp.com'),(62,'invStock_control_auth','库存操作权限','control','Y','2020-08-18 16:55:26','redragon',NULL,NULL,'erp.com'),(63,'invInputHead_menu_auth','入库菜单权限','menu','Y','2020-08-22 18:42:19','redragon',NULL,NULL,'erp.com'),(64,'invInputHead_control_auth','入库操作权限','control','Y','2020-08-22 18:42:55','redragon',NULL,NULL,'erp.com'),(65,'invOutputHead_menu_auth','出库菜单权限','menu','Y','2020-08-22 18:43:36','redragon',NULL,NULL,'erp.com'),(66,'invOutputHead_control_auth','出库操作权限','control','Y','2020-08-22 18:43:57','redragon',NULL,NULL,'erp.com'),(67,'invStockCheckHead_control_auth','库存盘点操作权限','control','Y','2020-08-27 17:08:59','redragon',NULL,NULL,'erp.com'),(68,'apPayHead_menu_auth','付款菜单权限','menu','Y','2020-09-15 21:42:28','redragon',NULL,NULL,'erp.com'),(69,'apPayHead_control_auth','付款操作权限','control','Y','2020-09-15 21:43:02','redragon',NULL,NULL,'erp.com'),(70,'arReceiptHead_menu_auth','收款菜单权限','menu','Y','2020-09-15 21:43:36','redragon',NULL,NULL,'erp.com'),(71,'arReceiptHead_control_auth','收款操作权限','control','Y','2020-09-15 21:44:05','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `sys_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_dataset`
--

LOCK TABLES `sys_dataset` WRITE;
/*!40000 ALTER TABLE `sys_dataset` DISABLE KEYS */;
INSERT INTO `sys_dataset` VALUES (1,'rmb','人民币','currency','Y','2020-07-03 22:14:26','dongbin',NULL,NULL,'erp.com'),(4,'china','中国','country','Y','2020-07-13 22:50:11','dongbin',NULL,NULL,'erp.com'),(5,'110000','北京市','city','Y','2020-07-13 22:57:48','dongbin',NULL,NULL,'erp.com'),(6,'310000','上海市','city','Y','2020-07-13 22:58:09','dongbin',NULL,NULL,'erp.com'),(7,'440100','广州市','city','Y','2020-07-13 22:58:25','dongbin',NULL,NULL,'erp.com'),(9,'BOC','中国银行','bank','Y','2020-07-13 23:24:51','dongbin',NULL,NULL,'erp.com'),(10,'CMB','招商银行','bank','Y','2020-07-13 23:25:06','dongbin',NULL,NULL,'erp.com'),(11,'CCB','中国建设银行','bank','Y','2020-07-13 23:25:24','dongbin',NULL,NULL,'erp.com'),(12,'ABC','中国农业银行','bank','Y','2020-07-13 23:25:37','dongbin',NULL,NULL,'erp.com'),(13,'ICBC','中国工商银行','bank','Y','2020-07-13 23:25:53','dongbin',NULL,NULL,'erp.com'),(14,'BCOM','交通银行','bank','Y','2020-07-13 23:26:08','dongbin',NULL,NULL,'erp.com'),(15,'CITIC','中信银行','bank','Y','2020-07-13 23:26:28','dongbin',NULL,NULL,'erp.com'),(18,'KG','千克','material_unit','Y','2020-07-14 23:26:36','dongbin',NULL,NULL,'erp.com'),(19,'ML','毫升','material_unit','Y','2020-07-14 23:26:53','dongbin',NULL,NULL,'erp.com'),(20,'AN','个/台','material_unit','Y','2020-07-14 23:27:36','dongbin','2020-08-04 15:44:09','redragon','erp.com'),(21,'ZY','自营项目','project_type','Y','2020-07-15 13:24:02','dongbin',NULL,NULL,'erp.com'),(22,'DL','代理项目','project_type','Y','2020-07-15 13:24:12','dongbin',NULL,NULL,'erp.com'),(23,'FW','服务项目','project_type','Y','2020-07-15 13:24:22','dongbin',NULL,NULL,'erp.com'),(24,'POTYPE01','标准采购订单','po_type','Y','2020-07-15 15:35:40','dongbin',NULL,NULL,'erp.com'),(25,'POTYPE02','计划采购订单','po_type','Y','2020-07-15 15:35:58','dongbin',NULL,NULL,'erp.com'),(26,'POTYPE03','一揽子采购订单','po_type','Y','2020-07-15 15:36:26','dongbin',NULL,NULL,'erp.com'),(28,'17','增值税','tax_type','Y','2020-07-15 15:42:17','dongbin',NULL,NULL,'erp.com'),(29,'5','营业税','tax_type','Y','2020-07-15 15:43:07','dongbin',NULL,NULL,'erp.com'),(30,'20','企业所得税','tax_type','Y','2020-07-15 15:46:13','dongbin',NULL,NULL,'erp.com'),(31,'SOTYPE01','标准销售订单','so_type','Y','2020-07-17 22:36:20','dongbin',NULL,NULL,'erp.com'),(32,'SOTYPE02','计划销售订单','so_type','Y','2020-07-17 22:36:36','dongbin',NULL,NULL,'erp.com'),(33,'cash','现金','pay_mode','Y','2020-07-19 16:17:46','dongbin',NULL,NULL,'erp.com'),(34,'check','支票','pay_mode','Y','2020-07-19 16:18:18','dongbin',NULL,NULL,'erp.com'),(36,'transfer','转账','pay_mode','Y','2020-07-19 16:30:02','dongbin',NULL,NULL,'erp.com'),(37,'ji','记','voucher_type','Y','2020-07-25 17:20:07','dongbin',NULL,NULL,'erp.com'),(38,'fu','付','voucher_type','Y','2020-07-25 17:20:35','dongbin',NULL,NULL,'erp.com'),(39,'shou','收','voucher_type','Y','2020-07-25 17:20:43','dongbin',NULL,NULL,'erp.com'),(44,'zhuan','转','voucher_type','Y','2020-08-04 15:40:23','redragon',NULL,NULL,'erp.com'),(45,'ZH','综合项目','project_type','Y','2020-08-04 15:42:17','redragon',NULL,NULL,'erp.com'),(46,'CI','次','material_unit','Y','2020-08-04 15:45:32','redragon',NULL,NULL,'erp.com'),(47,'120000','天津市','city','Y','2020-08-04 15:58:16','redragon',NULL,NULL,'erp.com'),(48,'130100','石家庄市','city','Y','2020-08-04 16:00:54','redragon',NULL,NULL,'erp.com'),(49,'140100','太原市','city','Y','2020-08-04 16:01:17','redragon',NULL,NULL,'erp.com'),(50,'150100','呼和浩特市','city','Y','2020-08-04 16:01:33','redragon',NULL,NULL,'erp.com'),(51,'210100','沈阳市','city','Y','2020-08-04 16:02:01','redragon',NULL,NULL,'erp.com'),(52,'210200','大连市','city','Y','2020-08-04 16:02:13','redragon',NULL,NULL,'erp.com'),(53,'220100','长春市','city','Y','2020-08-04 16:02:27','redragon',NULL,NULL,'erp.com'),(54,'230100','哈尔滨市','city','Y','2020-08-04 16:02:47','redragon',NULL,NULL,'erp.com'),(55,'320100','南京市','city','Y','2020-08-04 16:03:10','redragon',NULL,NULL,'erp.com'),(56,'320500','苏州市','city','Y','2020-08-04 16:03:28','redragon',NULL,NULL,'erp.com'),(57,'330100','杭州市','city','Y','2020-08-04 16:03:41','redragon',NULL,NULL,'erp.com'),(58,'340100','合肥市','city','Y','2020-08-04 16:03:57','redragon',NULL,NULL,'erp.com'),(59,'350100','福州市','city','Y','2020-08-04 16:04:14','redragon',NULL,NULL,'erp.com'),(60,'350200','厦门市','city','Y','2020-08-04 16:04:26','redragon',NULL,NULL,'erp.com'),(61,'360100','南昌市','city','Y','2020-08-04 16:04:40','redragon',NULL,NULL,'erp.com'),(62,'370100','济南市','city','Y','2020-08-04 16:04:58','redragon',NULL,NULL,'erp.com'),(63,'410100','郑州市','city','Y','2020-08-04 16:05:15','redragon',NULL,NULL,'erp.com'),(64,'420100','武汉市','city','Y','2020-08-04 16:05:31','redragon',NULL,NULL,'erp.com'),(65,'430100','长沙市','city','Y','2020-08-04 16:05:43','redragon',NULL,NULL,'erp.com'),(66,'450100','南宁市','city','Y','2020-08-04 16:06:25','redragon',NULL,NULL,'erp.com'),(67,'460100','海口市','city','Y','2020-08-04 16:06:37','redragon',NULL,NULL,'erp.com'),(68,'500000','重庆','city','Y','2020-08-04 16:07:08','redragon',NULL,NULL,'erp.com'),(69,'510100','成都市','city','Y','2020-08-04 16:07:20','redragon',NULL,NULL,'erp.com'),(70,'520100','贵阳市','city','Y','2020-08-04 16:07:34','redragon',NULL,NULL,'erp.com'),(71,'530100','昆明市','city','Y','2020-08-04 16:07:46','redragon',NULL,NULL,'erp.com'),(72,'540100','拉萨市','city','Y','2020-08-04 16:08:00','redragon',NULL,NULL,'erp.com'),(73,'610100','西安市','city','Y','2020-08-04 16:08:12','redragon',NULL,NULL,'erp.com'),(74,'620100','兰州市','city','Y','2020-08-04 16:08:25','redragon',NULL,NULL,'erp.com'),(75,'630100','西宁市','city','Y','2020-08-04 16:08:37','redragon',NULL,NULL,'erp.com'),(76,'640100','银川市','city','Y','2020-08-04 16:08:49','redragon',NULL,NULL,'erp.com'),(77,'650100','乌鲁木齐市','city','Y','2020-08-04 16:09:01','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `sys_dataset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_dataset_type`
--

LOCK TABLES `sys_dataset_type` WRITE;
/*!40000 ALTER TABLE `sys_dataset_type` DISABLE KEYS */;
INSERT INTO `sys_dataset_type` VALUES (2,'city','城市','Y','2020-07-03 16:16:11','dongbin','2020-07-13 23:24:21','dongbin','erp.com'),(3,'currency','币种','Y','2020-07-03 16:17:36','dongbin',NULL,NULL,'erp.com'),(6,'country','国家','Y','2020-07-13 22:49:10','dongbin',NULL,NULL,'erp.com'),(7,'bank','银行','Y','2020-07-13 23:24:33','dongbin',NULL,NULL,'erp.com'),(9,'material_unit','物料单位','Y','2020-07-14 23:25:41','dongbin',NULL,NULL,'erp.com'),(10,'project_type','项目类型','Y','2020-07-15 13:23:31','dongbin',NULL,NULL,'erp.com'),(11,'po_type','采购订单类型','Y','2020-07-15 15:34:49','dongbin',NULL,NULL,'erp.com'),(12,'tax_type','计税种类','Y','2020-07-15 15:41:10','dongbin',NULL,NULL,'erp.com'),(13,'so_type','销售订单类型','Y','2020-07-17 22:33:49','dongbin',NULL,NULL,'erp.com'),(14,'pay_mode','付款方式','Y','2020-07-19 16:12:53','dongbin',NULL,NULL,'erp.com'),(15,'voucher_type','凭证字','Y','2020-07-25 17:19:22','dongbin',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `sys_dataset_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (6,'admin_role','超级管理员角色','Y','2020-07-31 16:29:37','dongbin','2020-08-15 13:19:51','redragon','erp.com'),(7,'sys_role','系统管理角色','Y','2020-08-01 13:43:54','dongbin','2020-08-15 12:56:22','redragon','erp.com'),(10,'fin_role','财务管理角色','Y','2020-08-15 12:52:04','redragon',NULL,NULL,'erp.com'),(11,'hr_role','人力管理角色','Y','2020-08-15 12:52:57','redragon',NULL,NULL,'erp.com'),(12,'md_role','主数据管理角色','Y','2020-08-15 12:53:41','redragon',NULL,NULL,'erp.com'),(13,'pay_role','应付管理角色','Y','2020-08-15 12:54:49','redragon',NULL,NULL,'erp.com'),(14,'po_role','采购管理角色','Y','2020-08-15 12:55:13','redragon',NULL,NULL,'erp.com'),(15,'receipt_role','应收管理角色','Y','2020-08-15 12:55:42','redragon',NULL,NULL,'erp.com'),(16,'so_role','销售管理角色','Y','2020-08-15 12:56:04','redragon',NULL,NULL,'erp.com'),(18,'data_auth_role','数据权限角色','Y','2020-08-16 18:08:57','redragon',NULL,NULL,'erp.com'),(19,'inv_role','库房管理角色','Y','2020-08-17 23:23:14','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_role_auth_r`
--

LOCK TABLES `sys_role_auth_r` WRITE;
/*!40000 ALTER TABLE `sys_role_auth_r` DISABLE KEYS */;
INSERT INTO `sys_role_auth_r` VALUES (73,'fin_role','finVoucherHead_menu_auth','2020-08-15 13:20:24','redragon',NULL,NULL,'erp.com'),(74,'fin_role','finVoucherModelHead_menu_auth','2020-08-15 13:20:24','redragon',NULL,NULL,'erp.com'),(79,'md_role','mdCustomer_menu_auth','2020-08-15 13:21:11','redragon',NULL,NULL,'erp.com'),(80,'md_role','mdFinanceSubject_menu_auth','2020-08-15 13:21:12','redragon',NULL,NULL,'erp.com'),(81,'md_role','mdMaterial_menu_auth','2020-08-15 13:21:12','redragon',NULL,NULL,'erp.com'),(82,'md_role','mdMaterialCategory_menu_auth','2020-08-15 13:21:12','redragon',NULL,NULL,'erp.com'),(83,'md_role','mdProject_menu_auth','2020-08-15 13:21:12','redragon',NULL,NULL,'erp.com'),(84,'md_role','mdVendor_menu_auth','2020-08-15 13:21:12','redragon',NULL,NULL,'erp.com'),(86,'po_role','poHead_menu_auth','2020-08-15 13:21:34','redragon',NULL,NULL,'erp.com'),(88,'so_role','soHead_menu_auth','2020-08-15 13:21:50','redragon',NULL,NULL,'erp.com'),(89,'sys_role','sysAuth_menu_auth','2020-08-15 13:22:27','redragon',NULL,NULL,'erp.com'),(90,'sys_role','sysDatasetType_menu_auth','2020-08-15 13:22:27','redragon',NULL,NULL,'erp.com'),(91,'sys_role','sysRole_menu_auth','2020-08-15 13:22:27','redragon',NULL,NULL,'erp.com'),(92,'sys_role','sysUser_menu_auth','2020-08-15 13:22:27','redragon',NULL,NULL,'erp.com'),(140,'hr_role','hrDepartment_menu_auth','2020-08-15 22:51:04','redragon',NULL,NULL,'erp.com'),(141,'hr_role','hrPosition_menu_auth','2020-08-15 22:51:04','redragon',NULL,NULL,'erp.com'),(142,'hr_role','hrStaff_menu_auth','2020-08-15 22:51:04','redragon',NULL,NULL,'erp.com'),(143,'hr_role','hrStaffDepartmentR_menu_auth','2020-08-15 22:51:04','redragon',NULL,NULL,'erp.com'),(341,'inv_role','invInputHead_control_auth','2020-09-04 22:23:43','redragon',NULL,NULL,'erp.com'),(342,'inv_role','invOutputHead_control_auth','2020-09-04 22:23:43','redragon',NULL,NULL,'erp.com'),(343,'inv_role','invStock_control_auth','2020-09-04 22:23:43','redragon',NULL,NULL,'erp.com'),(344,'inv_role','invStockCheckHead_control_auth','2020-09-04 22:23:43','redragon',NULL,NULL,'erp.com'),(345,'inv_role','invWarehouse_control_auth','2020-09-04 22:23:43','redragon',NULL,NULL,'erp.com'),(346,'inv_role','invInputHead_menu_auth','2020-09-04 22:23:43','redragon',NULL,NULL,'erp.com'),(347,'inv_role','invOutputHead_menu_auth','2020-09-04 22:23:43','redragon',NULL,NULL,'erp.com'),(348,'inv_role','invStock_menu_auth','2020-09-04 22:23:43','redragon',NULL,NULL,'erp.com'),(349,'inv_role','invWarehouse_menu_auth','2020-09-04 22:23:43','redragon',NULL,NULL,'erp.com'),(354,'data_auth_role','public_data_auth','2020-09-04 22:38:37','redragon',NULL,NULL,'erp.com'),(355,'data_auth_role','private_data_auth','2020-09-04 22:38:37','redragon',NULL,NULL,'erp.com'),(356,'pay_role','apPayHead_menu_auth','2020-09-15 21:44:39','redragon',NULL,NULL,'erp.com'),(357,'pay_role','apInvoiceHead_menu_auth','2020-09-15 21:44:39','redragon',NULL,NULL,'erp.com'),(358,'receipt_role','arReceiptHead_menu_auth','2020-09-15 21:44:51','redragon',NULL,NULL,'erp.com'),(359,'receipt_role','arInvoiceHead_menu_auth','2020-09-15 21:44:52','redragon',NULL,NULL,'erp.com'),(360,'admin_role','apPayHead_control_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(361,'admin_role','apPayHead_menu_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(362,'admin_role','arReceiptHead_control_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(363,'admin_role','arReceiptHead_menu_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(364,'admin_role','apInvoiceHead_control_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(365,'admin_role','apInvoiceHead_menu_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(366,'admin_role','arInvoiceHead_control_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(367,'admin_role','arInvoiceHead_menu_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(368,'admin_role','finVoucherHead_control_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(369,'admin_role','finVoucherHead_menu_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(370,'admin_role','finVoucherModelHead_control_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(371,'admin_role','finVoucherModelHead_menu_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(372,'admin_role','hrDepartment_control_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(373,'admin_role','hrDepartment_menu_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(374,'admin_role','hrPosition_control_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(375,'admin_role','hrPosition_menu_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(376,'admin_role','hrStaff_control_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(377,'admin_role','hrStaff_menu_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(378,'admin_role','hrStaffDepartmentR_control_auth','2020-09-15 21:45:05','redragon',NULL,NULL,'erp.com'),(379,'admin_role','hrStaffDepartmentR_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(380,'admin_role','invInputHead_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(381,'admin_role','invInputHead_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(382,'admin_role','invOutputHead_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(383,'admin_role','invOutputHead_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(384,'admin_role','invStock_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(385,'admin_role','invStock_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(386,'admin_role','invStockCheckHead_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(387,'admin_role','invWarehouse_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(388,'admin_role','invWarehouse_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(389,'admin_role','mdCustomer_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(390,'admin_role','mdCustomer_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(391,'admin_role','mdFinanceSubject_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(392,'admin_role','mdFinanceSubject_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(393,'admin_role','mdMaterial_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(394,'admin_role','mdMaterial_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(395,'admin_role','mdMaterialCategory_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(396,'admin_role','mdMaterialCategory_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(397,'admin_role','mdProject_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(398,'admin_role','mdProject_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(399,'admin_role','mdVendor_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(400,'admin_role','mdVendor_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(401,'admin_role','poHead_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(402,'admin_role','poHead_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(403,'admin_role','soHead_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(404,'admin_role','soHead_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(405,'admin_role','sysAuth_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(406,'admin_role','sysAuth_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(407,'admin_role','sysDatasetType_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(408,'admin_role','sysDatasetType_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(409,'admin_role','sysRole_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(410,'admin_role','sysRole_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(411,'admin_role','sysUser_control_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com'),(412,'admin_role','sysUser_menu_auth','2020-09-15 21:45:06','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `sys_role_auth_r` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (8,'dongbin','40bd001563085fc35165329ea1ff5c5ecbdbbeef','Y','2020-06-09 17:39:55','admin',NULL,NULL,'test.com'),(30,'admin','40bd001563085fc35165329ea1ff5c5ecbdbbeef','Y','2020-06-17 21:16:30','sys',NULL,NULL,'test.com'),(45,'redragon','40bd001563085fc35165329ea1ff5c5ecbdbbeef','Y','2020-08-01 13:41:56','admin','2020-08-03 19:02:10','redragon','erp.com');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_user_role_r`
--

LOCK TABLES `sys_user_role_r` WRITE;
/*!40000 ALTER TABLE `sys_user_role_r` DISABLE KEYS */;
INSERT INTO `sys_user_role_r` VALUES (11,'admin','sys_role','2020-08-01 15:18:43','dongbin',NULL,NULL,''),(59,'redragon','data_auth_role','2020-08-17 23:15:48','redragon',NULL,NULL,'erp.com'),(60,'redragon','admin_role','2020-08-17 23:15:48','redragon',NULL,NULL,'erp.com'),(61,'dongbin','inv_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(62,'dongbin','data_auth_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(63,'dongbin','fin_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(64,'dongbin','hr_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(65,'dongbin','md_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(66,'dongbin','pay_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(67,'dongbin','po_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(68,'dongbin','receipt_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(69,'dongbin','so_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com'),(70,'dongbin','sys_role','2020-09-04 22:15:27','redragon',NULL,NULL,'erp.com');
/*!40000 ALTER TABLE `sys_user_role_r` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-29 20:59:45
