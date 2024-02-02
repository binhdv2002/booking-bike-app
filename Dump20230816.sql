-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: _db_eco
-- ------------------------------------------------------
-- Server version	5.7.42-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bike`
--

DROP TABLE IF EXISTS `bike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bike` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `barcode` varchar(255) DEFAULT NULL,
  `battery_level` int(11) DEFAULT NULL,
  `license_plate` varchar(255) DEFAULT NULL,
  `status_bike` bit(1) DEFAULT NULL,
  `station_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2icfwn345vvj81wtp09q5wlwf` (`station_id`),
  KEY `FKm8v019qfm03y6teed4e5dfnb6` (`type_id`),
  CONSTRAINT `FK2icfwn345vvj81wtp09q5wlwf` FOREIGN KEY (`station_id`) REFERENCES `bike_station` (`id`),
  CONSTRAINT `FKm8v019qfm03y6teed4e5dfnb6` FOREIGN KEY (`type_id`) REFERENCES `bike_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bike`
--

LOCK TABLES `bike` WRITE;
/*!40000 ALTER TABLE `bike` DISABLE KEYS */;
INSERT INTO `bike` VALUES (1,'98421354',100,'8457 - 6239',_binary '',3,2),(2,'24244706',100,'6428 - 1039',_binary '',3,2),(3,'33465271',100,'2024 - 5459',_binary '',3,2),(4,'21193693',100,'768 - 4849',_binary '',3,2),(5,'28674775',100,'7710 - 5675',_binary '',2,2),(6,'56107872',100,'7660 - 418',_binary '',2,2),(7,'90226846',100,'8789 - 3551',_binary '\0',2,2),(8,'13381964',100,'7668 - 6846',_binary '',2,2),(9,'88717910',100,'9866 - 5873',_binary '',1,3),(10,'50614360',100,'8225 - 8147',_binary '',1,3),(11,'42786911',100,'6900 - 1754',_binary '',1,3),(12,'73577775',100,'4886 - 2016',_binary '',1,3);
/*!40000 ALTER TABLE `bike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bike_rental`
--

DROP TABLE IF EXISTS `bike_rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bike_rental` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` enum('PAID','PENDING') DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `time_start` datetime(6) DEFAULT NULL,
  `total_cost` float DEFAULT NULL,
  `bike_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfle0xbpy7r6a4lkl9p6axxqv4` (`bike_id`),
  KEY `FK5imkfd01h21c0cr7gp7i1vd3n` (`user_id`),
  CONSTRAINT `FK5imkfd01h21c0cr7gp7i1vd3n` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKfle0xbpy7r6a4lkl9p6axxqv4` FOREIGN KEY (`bike_id`) REFERENCES `bike` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bike_rental`
--

LOCK TABLES `bike_rental` WRITE;
/*!40000 ALTER TABLE `bike_rental` DISABLE KEYS */;
INSERT INTO `bike_rental` VALUES (1,'PAID','2023-08-12 23:32:40.765757','2023-08-12 20:21:47.716010',64500,10,7),(2,'PENDING','2023-08-13 00:12:25.363033','2023-08-13 00:12:24.363033',0,7,7);
/*!40000 ALTER TABLE `bike_rental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bike_station`
--

DROP TABLE IF EXISTS `bike_station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bike_station` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `acreage_park` int(11) DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `capcity` int(11) DEFAULT NULL,
  `empty_slots` int(11) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `total_bikes` int(11) DEFAULT NULL,
  `walking_distance` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bike_station`
--

LOCK TABLES `bike_station` WRITE;
/*!40000 ALTER TABLE `bike_station` DISABLE KEYS */;
INSERT INTO `bike_station` VALUES (1,2000,' Hai Bà Trưng, Hà Nội, Việt Nam',500,496,'2R3W+W58, Bãi gửi xe, Bách Khoa,',4,550),(2,1800,' P. Trần Đại Nghĩa, Bách Khoa, Hai Bà Trưng, Hà Nội, Việt Nam',400,397,'2R4V+XCX Bãi gửi xe C1',3,150),(3,2200,'Hai Bà Trưng, Hà Nội, Việt Nam',800,796,'2V52+62 Hai Bà Trưng',4,850),(4,1500,' Hà Nội, Việt Nam',400,400,'XRXV+9Q Hai Bà Trưng',0,850);
/*!40000 ALTER TABLE `bike_station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bike_type`
--

DROP TABLE IF EXISTS `bike_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bike_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deposit_price` float DEFAULT NULL,
  `rental_price` float DEFAULT NULL,
  `type_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bike_type`
--

LOCK TABLES `bike_type` WRITE;
/*!40000 ALTER TABLE `bike_type` DISABLE KEYS */;
INSERT INTO `bike_type` VALUES (1,400000,1000000,'Xe đạp đơn'),(2,700000,1750000,'Xe đạp đơn điện'),(3,550000,1375000,'Xe đạp đôi'),(4,800000,2000000,'Xe đạp đôi điện'),(5,0,2000000,'Xe đạp thuê kiểu mới'),(6,0,2000000,'Xe đạp thuê kiểu mới');
/*!40000 ALTER TABLE `bike_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_card`
--

DROP TABLE IF EXISTS `credit_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credit_card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balance` float DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `cvv` varchar(255) DEFAULT NULL,
  `expired_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_card`
--

LOCK TABLES `credit_card` WRITE;
/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
INSERT INTO `credit_card` VALUES (1,1000000,'6545424244','123','2023-08-13'),(2,1000000,'123','123','2023-08-13'),(7,235500,'1','123','2023-08-13'),(8,1000000,'32131235','152','2023-08-13'),(9,1000000,'1231656','152','2023-08-13'),(10,1000000,'1','123','2023-08-13'),(11,1000000,'321','3123','2023-08-13'),(12,1000000,'321','3123','2023-08-13'),(13,1000000,'123','12313123','2023-08-13'),(14,1000000,'123','123123','2023-08-13'),(15,1000000,'123','123','2023-08-13'),(16,1000000,'12','12','2023-08-13'),(17,1000000,'123','123','2023-08-13'),(18,1000000,'12','12','2023-08-13'),(19,1000000,'435','45','2023-08-13'),(20,1000000,'56','56','2023-08-13'),(21,1000000,'67','76','2023-08-13'),(22,1000000,'45','45','2023-08-13'),(23,1000000,'45','45','2023-08-13'),(24,1000000,'45','45','2023-08-13'),(25,1000000,'45','45','2023-08-13'),(26,1000000,'45','45','2023-08-13'),(27,1000000,'34','34','2023-08-13'),(28,1000000,'12','12','2023-08-13'),(29,1000000,'34','34','2023-08-13'),(30,1000000,'ds','12','2023-08-14'),(31,1000000,'21','12','2023-08-14'),(32,1000000,'43','34','2023-08-14'),(33,1000000,'34','34','2023-08-14'),(34,1000000,'12','12','2023-08-14'),(35,1000000,'12','12','2023-08-14'),(36,1000000,'32','12','2023-08-14'),(37,1000000,'12','12','2023-08-14'),(38,1000000,'12','12','2023-08-14'),(39,1000000,'12','12','2023-08-14'),(40,1000000,'12','12','2023-08-14'),(41,1000000,'12','12','2023-08-14'),(42,1000000,'12','12','2023-08-14'),(43,1000000,'12','12','2023-08-14'),(44,1000000,'12','12','2023-08-14');
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` float DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `is_return` bit(1) DEFAULT NULL,
  `transaction_time` datetime(6) DEFAULT NULL,
  `credit_card_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrwv4me42yeif7mywc5u8j60sk` (`credit_card_id`),
  KEY `FKsg7jp0aj6qipr50856wf6vbw1` (`user_id`),
  CONSTRAINT `FKrwv4me42yeif7mywc5u8j60sk` FOREIGN KEY (`credit_card_id`) REFERENCES `credit_card` (`id`),
  CONSTRAINT `FKsg7jp0aj6qipr50856wf6vbw1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,550000,'Rent Xe đạp đôi',_binary '','2023-08-12 20:21:47.653425',7,7),(2,700000,'Rent Xe đạp đơn điện',_binary '\0','2023-08-13 00:12:24.346915',7,7);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `credit_card_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_kiqfjabx9puw3p1eg7kily8kg` (`password`),
  KEY `FKgcjy88ysvwgqo3umk010mjuxv` (`credit_card_id`),
  CONSTRAINT `FKgcjy88ysvwgqo3umk010mjuxv` FOREIGN KEY (`credit_card_id`) REFERENCES `credit_card` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'account1691829377565@gmail.com','$2a$10$3nKoyptLaJd7MYNCcmg8KOfoIrxg.OYyAhukVe/vvsEf/Ja2XNT6G',1),(2,'account1691829826812@gmail.com','$2a$10$bk4H6cKrVNe6fuD//T.mweYK57Ym1C/bWSgdsyJS98PZtQmCY31M2',2),(7,'account1691832323204@gmail.com','$2a$10$CcYUN98OeCqKMPh4uGBxGudkPFsc/GUjoOr/I9XcxHn1oYRLoaye.',7),(8,'account1691832456467@gmail.com','$2a$10$A.7OPMj2DwvCAHK1F.FGWOvIboKofuDeFe16UfK9yMeP..frRciJm',8),(9,'account1691832661703@gmail.com','$2a$10$OeI9DSLvr5J05icj8ujPaeoD.MveWtd5PiMq9bIjObm4E9clb1N4C',9),(10,'account1691832994322@gmail.com','$2a$10$qOrZbONiYI6HU59K5A62uu9Fy4DPmZPTN4.XzuU/kD1Cf3EBqmjom',10),(11,'account1691833632768@gmail.com','$2a$10$hxF0gtFx.AWcXoB8jRTo1u16JkJvBVdYA.LT540EjZ0xgZ3/MEIlK',11),(12,'account1691843466145@gmail.com','$2a$10$KMR2i0Izl9/GEyVRDqHqt.i.qsNoXHiEFfynwShh3orXjVXJ7hxCy',12),(13,'account1691843545552@gmail.com','$2a$10$Tjd11ai5n5CoFq0OxpQasuilIB5miF01pQMEMXoljBXvu/HGXawDa',13),(14,'account1691843679103@gmail.com','$2a$10$7CnwDyHdGPoSk0sluN/L3O1zieE7GwwOa2k/tO0zmwkWUlVD9Cc6.',14),(15,'account1691846738581@gmail.com','$2a$10$e4sRYKUQYv.aWfu7JX5szeIXQaX62IrkAUNBcqgAW8rCWM6k3gIt.',15),(16,'account1691849923080@gmail.com','$2a$10$B1F8EmIhxvajAaZGw/mfxOgASTXBPLHzwdmUqjrQ/sMUDEx.0aWzW',16),(17,'account1691850978710@gmail.com','$2a$10$Ufea2cRXGVtmOO5VRz/9lexZFShhPMQ4ZSUCcGc7tBT4aDCF1Nx3W',17),(18,'account1691851769841@gmail.com','$2a$10$Ubpy2ez.MsEv0aeA4w98e.jKNQsnCuOj3w1qJ3faBxJ8dSFsEsAhy',18),(19,'account1691851849564@gmail.com','$2a$10$2rEqIDVCnHecRRx378XBGO8m0FIkFZaRs5.2seOdknpOr1gtlJMyS',19),(20,'account1691851924346@gmail.com','$2a$10$vc8q3tArNaGzwdcK8SGR9evqN5cAiRjCcIZFXwPzEEmUz6bhTtitm',20),(21,'account1691851958307@gmail.com','$2a$10$psAxTbpsvQ2QeDyaBIx3ZesfpxthzFfVkiIl9dH2e77nzqCQkS1Nu',21),(22,'account1691852014957@gmail.com','$2a$10$40OzWFDaTiJlMS/MIyd40eZ.XZ/Ohq48h7aPjwK2O6Q/CtB7MK7sa',22),(23,'account1691852038907@gmail.com','$2a$10$qeehaRiZyQOKqlNBV95fPemWp7lHX2YdAfTmewaJ2G7GlYu1ozo6y',23),(24,'account1691852147339@gmail.com','$2a$10$DmU/RpQf5YQNmYVmmt1dVutaIiIMG8HiYQ1gSx68HPEeN73R7ElXu',24),(25,'account1691852161435@gmail.com','$2a$10$SHZuMC/1R3U1KQxV9d.HR.P22gir56MHholQlUXJMCahxkGmBYRAC',25),(26,'account1691852947810@gmail.com','$2a$10$OW7E/y8FJqoNwd8xJcKZ6O0mmWWxrkEkm9oNpNJ5dp.kEwsLqhzLa',26),(27,'account1691852980134@gmail.com','$2a$10$JJel8fIGDB97RqNP7TY27uItlspAukW.mJEz.ErRoAPKOldgc1k5K',27),(28,'account1691853181827@gmail.com','$2a$10$BUOHcYU/LfvlWdEJGa9.F.xbjgMUwmWpUIX3w3GjJfSVoiLdVKx/G',28),(29,'account1691856688494@gmail.com','$2a$10$l0D4OD5Xerx801N8X890AuWFZNhylovrwYm8erO1Za232DE6QNO/G',29),(30,'account1691859631626@gmail.com','$2a$10$SLNE/JGhzQNUXW1O5fZ1QeMsk1VaITdY0lux1ppehmuPFVFyzlkVi',30),(31,'account1691859656526@gmail.com','$2a$10$YwCPEzm4pk8YxY4iquXtKeB8piGPOXYReI496BPKvgZSd4.SO1O26',31),(32,'account1691859769986@gmail.com','$2a$10$xQuW8DznrDJBRPxBmwhA/uxunFNZ1aA5cA39X/PCWypgB0h5h8LcG',32),(33,'account1691860154611@gmail.com','$2a$10$.HYmuP.eXth2sv8X8QEEJ.LU5etWpPjboOAH.qOhRexgF0wuJe6qm',33),(34,'account1691860342393@gmail.com','$2a$10$6wPE6RnnF3vfW806ufdRl.KT9PB9/F8rsr82yDkF2Juv8lQCaIfB6',34),(35,'account1691860667255@gmail.com','$2a$10$MYrUVXQInsmod.fR39igvuLmQobVyn7wo/YpFP8ip0gvTukSn/g/u',35),(36,'account1691860722572@gmail.com','$2a$10$Zjukdh.it8e83tCdpfqIUe4ROeYHRKp64.9oVBQTrtsgSBPVjv4LO',36),(37,'account1691860743126@gmail.com','$2a$10$LRrGd4/8pocRlMU5G6QUvuWlLsIFg8L7SjtnhiyrLay5p4eTYoAOm',37),(38,'account1691860780385@gmail.com','$2a$10$l8h9ykncgZYt3pa9iJ0A7OeBmM/iCbSonJ7sXpi5RGlmo7hhoEvNe',38),(39,'account1691860813792@gmail.com','$2a$10$mXzpCq0rJLlYABteV1zxaucV54wHpeM8aHGDSDP/FR5pEFXPu.FGS',39),(40,'account1691861306036@gmail.com','$2a$10$x9EjK9IGKfd6qZZ.BMnwGeBCgP5cP5ossLqLAwALG4ReH.C0PjTaW',40),(41,'account1691861516311@gmail.com','$2a$10$UbhFjZm9cV02A/xclpkm1ehlIW2aikz3lPyl/cT5JdA9hSmTozBu2',41),(42,'account1691861533478@gmail.com','$2a$10$Sx9Slv0jDyYbd1U1r5ACJ.1AhTbnVCx9fDxDnWSpskmxYmiR254Fa',42),(43,'account1691861565799@gmail.com','$2a$10$WFlFzrGQT9ZhFIMuVqJyoOsTiWhbOyn0N5fY6zU6wikR2fJeOOAOG',43),(44,'account1691861652290@gmail.com','$2a$10$fd1UNLcvpj2slZs6nkMhXe/N7bAypQYVgTd7TrMvno7.z9GbcgdPi',44);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-16 21:09:32
