-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: zudemy_search_product
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `product_description` varchar(255) DEFAULT NULL,
  `product_available` bit(1) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `price` float NOT NULL,
  `product_purchased_date` datetime(6) DEFAULT NULL,
  `product_type` varchar(255) DEFAULT NULL,
  `product_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Nike','Green','Excellent',_binary '','TShirt',250,'2023-01-18 13:17:17.000000','XL','IN_INVENTORY'),(2,'Adidas','Green','Good',_binary '','TShirt',450,'2023-02-02 13:17:17.000000','Large','OUT_OF_STOCK'),(3,'D-Cot','Blue','Good',_binary '','Trousers',600,'2023-03-02 13:17:17.000000','Narrow','ON_THE_WAY'),(4,'Reebok','Orange','Good',_binary '','Jeans',1200.5,'2023-02-03 13:17:17.000000','Slim','IN_INVENTORY'),(5,'Reebok','Red','Excellent',_binary '\0','Shirts',800.5,'2023-02-03 13:17:17.000000','Fit','ON_THE_WAY'),(6,'Nike','White','Average',_binary '','Shoes',400.5,'2023-02-01 13:17:17.000000','Casuals','ON_THE_WAY'),(7,'Mufti','Yellow','Excellent',_binary '','Jackets',1100.5,'2023-02-02 13:17:17.000000','Casuals','ON_THE_WAY'),(8,'Spykar','Black','Excellent',_binary '','Jeans',1700.5,'2023-02-01 13:17:17.000000','Slim','IN_INVENTORY'),(9,'Spykar','Blue','Excellent',_binary '','Jeans',1700.5,'2023-02-01 13:17:17.000000','Slim','IN_INVENTORY'),(10,'Adidas','Blue','Excellent',_binary '','Shoes',1500.5,'2023-02-05 13:17:17.000000','Sports','OUT_OF_STOCK');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-07 20:42:37
