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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Admin User','admin@example.com','adminpassword123','admin'),(2,'Owner1','owner1@example.com','ownerpassword1','owner'),(3,'Tenant1','tenant1@example.com','tenantpassword1','tenant'),(4,'Owner2','owner2@example.com','ownerpassword2','owner'),(5,'Tenant2','tenant2@example.com','tenantpassword2','tenant'),(6,'Owner3','owner3@example.com','ownerpassword3','owner'),(7,'Tenant3','tenant3@example.com','tenantpassword3','tenant'),(8,'Owner4','owner4@example.com','ownerpassword4','owner'),(9,'Tenant4','tenant4@example.com','tenantpassword4','tenant'),(10,'Owner5','owner5@example.com','ownerpassword5','owner'),(11,'Tenant5','tenant5@example.com','tenantpassword5','tenant'),(12,'Tenant6','tenant6@example.com','tenantpassword6','tenant'),(13,'Owner6','owner6@example.com','ownerpassword6','owner'),(14,'Tenant7','tenant7@example.com','tenantpassword7','tenant'),(15,'Owner7','owner7@example.com','ownerpassword7','owner'),(16,'Tenant8','tenant8@example.com','tenantpassword8','tenant'),(17,'Owner8','owner8@example.com','ownerpassword8','owner'),(18,'Tenant9','tenant9@example.com','tenantpassword9','tenant'),(19,'Owner9','owner9@example.com','ownerpassword9','owner'),(20,'Tenant10','tenant10@example.com','tenantpassword10','tenant'),(21,'Owner10','owner10@example.com','ownerpassword10','owner'),(22,'Tenant11','tenant11@example.com','tenantpassword11','tenant'),(23,'Tenant12','tenant12@example.com','tenantpassword12','tenant'),(24,'Tenant13','tenant13@example.com','tenantpassword13','tenant'),(25,'Tenant14','tenant14@example.com','tenantpassword14','tenant');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
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
