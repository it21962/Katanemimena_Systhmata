-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: rental_system
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `rental_requests`
--

DROP TABLE IF EXISTS `rental_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rental_requests` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `property_id` int NOT NULL,
  `request_date` datetime NOT NULL,
  `status` varchar(20) NOT NULL,
  `request_type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `property_id` (`property_id`),
  CONSTRAINT `rental_requests_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `rental_requests_ibfk_2` FOREIGN KEY (`property_id`) REFERENCES `properties` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental_requests`
--

LOCK TABLES `rental_requests` WRITE;
/*!40000 ALTER TABLE `rental_requests` DISABLE KEYS */;
INSERT INTO `rental_requests` VALUES (1,2,1,'2025-01-15 17:30:00','approved','listing'),(2,3,1,'2025-01-15 17:30:00','pending','rental'),(3,4,2,'2025-01-15 17:30:00','approved','listing'),(4,5,4,'2025-01-15 17:30:00','approved','rental'),(5,6,3,'2025-01-15 17:30:00','pending','listing'),(6,7,6,'2025-01-15 17:30:00','approved','rental'),(7,8,4,'2025-01-15 17:30:00','rejected','listing'),(8,9,8,'2025-01-15 17:30:00','pending','rental'),(9,10,5,'2025-01-15 17:30:00','approved','listing'),(10,11,10,'2025-01-15 17:30:00','rejected','rental'),(11,12,9,'2025-01-15 17:30:00','pending','rental'),(12,13,6,'2025-01-15 17:30:00','approved','listing'),(13,14,6,'2025-01-15 17:30:00','pending','rental'),(14,15,7,'2025-01-15 17:30:00','approved','listing'),(15,16,10,'2025-01-15 17:30:00','rejected','rental'),(16,17,8,'2025-01-15 17:30:00','pending','listing'),(17,18,3,'2025-01-15 17:30:00','approved','rental'),(18,19,9,'2025-01-15 17:30:00','pending','listing'),(19,20,4,'2025-01-15 17:30:00','rejected','rental'),(20,21,10,'2025-01-15 17:30:00','approved','listing'),(21,22,7,'2025-01-15 17:30:00','pending','rental'),(22,23,5,'2025-01-15 17:30:00','approved','rental'),(23,24,6,'2025-01-15 17:30:00','rejected','rental'),(24,25,10,'2025-01-15 17:30:00','approved','rental');
/*!40000 ALTER TABLE `rental_requests` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-15 13:30:36
