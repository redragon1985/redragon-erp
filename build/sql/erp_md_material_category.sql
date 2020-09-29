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
-- Dumping data for table `md_material_category`
--

LOCK TABLES `md_material_category` WRITE;
/*!40000 ALTER TABLE `md_material_category` DISABLE KEYS */;
INSERT INTO `md_material_category` VALUES (1,'MC000','根类型',NULL,'MC000','根类型',0,'Y','2020-08-02 16:24:55','redragon',NULL,NULL,'erp.com'),(2,'MC100','物料','MC000','MC000_MC100','根类型_物料',0,'Y','2020-08-02 16:25:39','redragon',NULL,NULL,'erp.com'),(3,'MC200','事项','MC000','MC000_MC200','根类型_事项',0,'Y','2020-08-02 16:25:54','redragon',NULL,NULL,'erp.com'),(5,'MC110','原材料','MC100','MC000_MC100_MC110','根类型_物料_原材料',0,'Y','2020-08-02 16:28:29','redragon',NULL,NULL,'erp.com'),(7,'MC120','成品','MC100','MC000_MC100_MC120','根类型_物料_成品',0,'Y','2020-08-02 16:28:58','redragon',NULL,NULL,'erp.com'),(8,'MC121','办公用品','MC120','MC000_MC100_MC120_MC121','根类型_物料_成品_办公用品',0,'Y','2020-08-02 16:30:10','redragon',NULL,NULL,'erp.com'),(9,'MC121-1','电脑','MC121','MC000_MC100_MC120_MC121_MC121-1','根类型_物料_成品_办公用品_电脑',0,'Y','2020-08-02 16:30:29','redragon',NULL,NULL,'erp.com'),(10,'MC122','固定资产','MC120','MC000_MC100_MC120_MC122','根类型_物料_成品_固定资产',0,'Y','2020-08-02 16:31:36','redragon',NULL,NULL,'erp.com'),(11,'MC122-1','服务器','MC122','MC000_MC100_MC120_MC122_MC122-1','根类型_物料_成品_固定资产_服务器',0,'Y','2020-08-02 16:31:47','redragon',NULL,NULL,'erp.com'),(12,'MC210','经营类','MC200','MC000_MC200_MC210','根类型_事项_经营类',0,'Y','2020-08-02 16:32:54','redragon',NULL,NULL,'erp.com'),(13,'MC220','服务类','MC200','MC000_MC200_MC220','根类型_事项_服务类',0,'Y','2020-08-02 16:33:28','redragon',NULL,NULL,'erp.com'),(14,'MC230','特殊事项','MC200','MC000_MC200_MC230','根类型_事项_特殊事项',0,'Y','2020-08-02 16:33:47','redragon',NULL,NULL,'erp.com'),(15,'MC211','二次开发','MC210','MC000_MC200_MC210_MC211','根类型_事项_经营类_二次开发',0,'Y','2020-08-02 16:34:25','redragon',NULL,NULL,'erp.com'),(16,'MC212','项目实施','MC210','MC000_MC200_MC210_MC212','根类型_事项_经营类_项目实施',0,'Y','2020-08-02 16:34:54','redragon',NULL,NULL,'erp.com'),(17,'MC221','项目维保','MC220','MC000_MC200_MC220_MC221','根类型_事项_服务类_项目维保',0,'Y','2020-08-02 16:35:27','redragon',NULL,NULL,'erp.com'),(18,'MC231','不可抗力损失','MC230','MC000_MC200_MC230_MC231','根类型_事项_特殊事项_不可抗力损失',0,'Y','2020-08-02 16:37:03','redragon',NULL,NULL,'erp.com'),(19,'MC123','软件产品','MC120','MC000_MC100_MC120_MC123','根类型_物料_成品_软件产品',0,'Y','2020-08-26 21:15:33','redragon',NULL,NULL,'erp.com'),(20,'MC123-1','ERP','MC123','MC000_MC100_MC120_MC123_MC123-1','根类型_物料_成品_软件产品_ERP',0,'Y','2020-08-26 21:15:54','redragon','2020-08-26 21:16:06','redragon','erp.com');
/*!40000 ALTER TABLE `md_material_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-29 20:58:18
