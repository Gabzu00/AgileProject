-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: oldiebutgoldie
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `likedusers`
--

DROP TABLE IF EXISTS `likedusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likedusers` (
  `userId` int NOT NULL,
  `likedUserId` int NOT NULL,
  PRIMARY KEY (`userId`,`likedUserId`),
  CONSTRAINT `likedusers_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likedusers`
--

LOCK TABLES `likedusers` WRITE;
/*!40000 ALTER TABLE `likedusers` DISABLE KEYS */;
INSERT INTO `likedusers` VALUES (3,1),(4,1);
/*!40000 ALTER TABLE `likedusers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userId` int NOT NULL,
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `image` varchar(50) DEFAULT NULL,
  `age` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Walter','Johnsson','walter@gmail.com','Jag tycker om att cykla i skogen','Sweden','Stockholm','RB\\&.”ŽZ+¾VÊ@oúi','Images/Walter.png','60'),(2,'Leif','Smith','Leif@hotmail.com','Jag tycker om att snusa i skogen','U.S.A','Dallas','—‚J^f6\\ŠÀHmºH…”','Images/leif.jpg','61'),(3,'Greg','Moreau','Greg@yahoo.com','Jag tycker om att tälta i skogen','France','Bordeaux','\ZúRh¯8á\nóf\r³˜„éE','Images/greg.jpg','62'),(4,'Albert','Berg','Albert@gmail.com','Jag tycker om att titta på maskar i skogen','Sweden','Jönköping','£CqúJåè ¹tï¹-÷ð','Images/Albert.jpg','63');
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

-- Dump completed on 2022-05-23 21:06:10
