-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: OldieButGoldie
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile` (
  `profileId` int NOT NULL,
  `description` varchar(400) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `image` varchar(50) DEFAULT NULL,
  `age` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`profileId`),
  CONSTRAINT `profile_ibfk_1` FOREIGN KEY (`profileId`) REFERENCES `registration` (`profileId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (1,'Jag tycker om att cykla i skogen','Sweden','Stockholm','Images/Walter.png','60'),(2,'Jag tycker om att snusa i skogen','U.S.A','Dallas','Images/leif.jpg','61'),(3,'Jag tycker om att t├ñlta i skogen','France','Bordeaux','Images/greg.jpg','62'),(4,'Jag tycker om att titta p├Ñ maskar i skogen','Sweden','J├Ânk├Âping','Images/Albert.jpg','63'),(5,NULL,NULL,NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `profileId` int NOT NULL,
  PRIMARY KEY (`profileId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES ('Walter','Johnsson','walter@gmail.com','RB\\&.ÔÇØ┼¢Z+┬¥V├è@o├║i',1),('Leif','Smith','Leif@hotmail.com','ÔÇöÔÇÜJ^f6\\┼á├ÇHm┬║HÔÇªÔÇØ',2),('Greg','Moreau','Greg@yahoo.com','\Z├║Rh┬»8├í\n├│f\r┬│╦£ÔÇ×├®E',3),('Albert','Berg','Albert@gmail.com','┬úCq├║J├Ñ├¿ ┬╣t├»┬╣-├À├░',4),('asdf','sdf','asdf','ÔÇ╣├║├òk(<┬¬├À03┬Ø├ª\Z├¼',5),('Alva','Themn├®r','alva@themner.se','KI┬ÉG├ï^L┬Á┬Å*┬╝OÔÇí┬®',6);
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-10 18:22:59
