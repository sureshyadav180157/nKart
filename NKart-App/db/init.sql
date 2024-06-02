--
-- Host: localhost    Database: nkart
-- ------------------------------------------------------
-- Server version	8.0.27
-- Table structure for table `item_color`
--
CREATE DATABASE IF NOT EXISTS nKart;
USE nKart;

DROP TABLE IF EXISTS `item_color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_color` (
  `ColorID` int NOT NULL,
  `ColorCode` varchar(255) DEFAULT NULL,
  `ColorName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ColorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_color`
--

LOCK TABLES `item_color` WRITE;
/*!40000 ALTER TABLE `item_color` DISABLE KEYS */;
INSERT INTO `item_color` VALUES (1,'#0000FF','Blue'),(2,'#008000','Green'),(3,'#FFFF00','Yellow'),(4,'#FF0000','Red'),(5,'#000000','Black'),(6,'#FFFFFF','White');
/*!40000 ALTER TABLE `item_color` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
-- Host: localhost    Database: nkart
-- ------------------------------------------------------
-- Server version	8.0.27
-- Table structure for table `item_size`
--
CREATE DATABASE IF NOT EXISTS nKart;
USE nKart;

DROP TABLE IF EXISTS `item_size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_size` (
  `SizeID` int NOT NULL,
  `SizeValue` varchar(255) DEFAULT NULL,
  `SizeName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SizeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Dumping data for table `item_size`

LOCK TABLES `item_size` WRITE;
/*!40000 ALTER TABLE `item_size` DISABLE KEYS */;
INSERT INTO `item_size` VALUES (1,'38','Small'),(2,'40','Medium'),(3,'42','Large'),(4,'44','Xl'),(5,'46','XXl'),(6,'48','XXXl');
/*!40000 ALTER TABLE `item_size` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
-- Host: localhost    Database: nkart
-- ------------------------------------------------------
-- Server version	8.0.27
-- Table structure for table `products`
--
CREATE DATABASE IF NOT EXISTS nKart;
USE nKart;

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `productId` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `shortDesc` varchar(255) DEFAULT NULL,
  `categoryId` int DEFAULT NULL,
  `subCategoryId` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `lastUpdated` date DEFAULT NULL,
  `thumbnailUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`productId`),
  KEY `categoryId` (`categoryId`),
  KEY `subCategoryId` (`subCategoryId`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `product_category` (`CategoryID`),
  CONSTRAINT `products_ibfk_2` FOREIGN KEY (`subCategoryId`) REFERENCES `sub_category` (`SubCategory`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Oppo A53s','Oppo A53s 5G 128 GB, 8 GB RAM, Ink Black, Smart Phone',1,1,10,25000,20,'2021-11-11','2021-11-11','https://images-eu.ssl-images-amazon.com/images/I/41kZhC-EkeL._SX300_SY300_QL70_FMwebp_.jpg'),(2,'Samsung Galaxy A22','Samsung Galaxy A22 128 GB, 6 GB, Black, Smarphone',1,1,10,25000,20,'2021-11-11','2021-11-11','https://images-eu.ssl-images-amazon.com/images/I/41gamtY4GLS._SX300_SY300_QL70_FMwebp_.jpg'),(3,'Vivo V21','Vivo V21 5G 128 GB, 8 GB RAM, Dusk Blue, Smartphone',1,1,10,25000,20,'2021-11-11','2021-11-11','https://images-eu.ssl-images-amazon.com/images/I/61gRGQe8jjL._AC_UY327_FMwebp_QL65_.jpg'),(4,'Apple iPhone 13 Mini','Apple iPhone 13 Mini 128 GB, Midnight (Black)',1,1,10,25000,20,'2021-11-11','2021-11-11','https://images-eu.ssl-images-amazon.com/images/I/31GEfQ0aszL._SY445_SX342_QL70_FMwebp_.jpg'),(5,'Samsung Galaxy S10','Samsung Galaxy S10 Smart Phone 512 GB, 8 GB RAM, Prism White',1,1,10,25000,20,'2021-11-11','2021-11-11','https://images-eu.ssl-images-amazon.com/images/I/71b5BwTCcZL._AC_UY327_FMwebp_QL65_.jpg');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
-- Host: localhost    Database: nkart
-- ------------------------------------------------------
-- Server version	8.0.27
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `CategoryID` int NOT NULL,
  `CategoryName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,'MOBILES & TABLETS'),(2,'TELEVISION & AUDIO'),(3,'HOME APPLIANCES'),(4,'COMPUTERS'),(5,'CAMERAS'),(6,'KITCHEN APPLIANCES'),(7,'PERSONAL CARE'),(8,'ACCESSORIES');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
-- Host: localhost    Database: nkart
-- ------------------------------------------------------
-- Server version	8.0.27

-- Table structure for table `product_details`
--
CREATE DATABASE IF NOT EXISTS nKart;
USE nKart;

DROP TABLE IF EXISTS `product_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_details` (
  `productId` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `imageId` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `productSize` int DEFAULT NULL,
  `productColour` int DEFAULT NULL,
  `thumbnailUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`productId`),
  KEY `imageId` (`imageId`),
  KEY `productSize` (`productSize`),
  KEY `productColour` (`productColour`),
  CONSTRAINT `product_details_ibfk_1` FOREIGN KEY (`imageId`) REFERENCES `product_image` (`imageId`),
  CONSTRAINT `product_details_ibfk_2` FOREIGN KEY (`productSize`) REFERENCES `item_size` (`SizeID`),
  CONSTRAINT `product_details_ibfk_3` FOREIGN KEY (`productColour`) REFERENCES `item_color` (`ColorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Dumping data for table `product_details`

LOCK TABLES `product_details` WRITE;
/*!40000 ALTER TABLE `product_details` DISABLE KEYS */;
INSERT INTO `product_details` VALUES (1,'Oppo A53s','Oppo A53s 5G 128 GB, 8 GB RAM, Ink Black, Smart Phone',5,1,15000,20,1,1,'Not available'),(2,'Samsung Galaxy A22','Samsung Galaxy A22 128 GB, 6 GB, Black, Smarphone',5,1,15000,20,1,1,'Not available'),(3,'Vivo V21','Vivo V21 5G 128 GB, 8 GB RAM, Dusk Blue, Smartphone',5,1,15000,20,2,2,'Not available'),(4,'Apple iPhone 13 Mini','Apple iPhone 13 Mini 128 GB, Midnight (Black)',5,1,20000,20,3,3,'Not available'),(5,'Samsung Galaxy S10','Samsung Galaxy S10 Smart Phone 512 GB, 8 GB RAM, Prism White',5,1,10000,20,1,1,'Not available');
/*!40000 ALTER TABLE `product_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
-- Host: localhost    Database: nkart
-- ------------------------------------------------------
-- Server version	8.0.27
--
-- Table structure for table `product_image`
--
CREATE DATABASE IF NOT EXISTS nKart;
USE nKart;

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image` (
  `productId` int DEFAULT NULL,
  `imageId` int NOT NULL,
  `colorCode` int DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `thumbnailUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`imageId`),
  KEY `productId` (`productId`),
  CONSTRAINT `product_image_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `products` (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` VALUES (1,1,1,'https://media.istockphoto.com/vectors/web-banner-or-horizontal-template-design-with-special-offer-on-mobile-vector-id1194426021?k=20&m=1194426021&s=170667a&w=0&h=oqoKnGQuMehJVUDapL3aHZ36AFDgzM16hI96WpaiSYM=','https://m.media-amazon.com/images/I/41YyRdK+npL._SS36_.jpg'),(2,2,2,'https://i.pinimg.com/originals/ff/5a/5c/ff5a5cae792cec9de6f7fc65b5dc7aba.jpg','https://m.media-amazon.com/images/I/41gamtY4GLS._SS36_.jpg'),(3,3,3,'https://www.shopickr.com/wp-content/uploads/2016/06/amazon-india-dslr-camera-best-discounts-offers-coupons.jpg','https://m.media-amazon.com/images/I/41AQksmndzS._SS36_.jpg'),(4,4,4,'https://1.bp.blogspot.com/-AEqH0iEMaug/Vh1RUlH1SSI/AAAAAAAAXzU/9bBzDeiRRDE/s1600/20151013_225433_730x300_home-appliances.jpg','https://m.media-amazon.com/images/I/31MaO1CM2YL._SX38_SY50_CR,0,0,38,50_.jpg'),(5,5,5,'https://malabaroffers.com/wp-content/uploads/2021/07/hp-offer.jpg','https://m.media-amazon.com/images/I/31ZjOGUgjSL._SX38_SY50_CR,0,0,38,50_.jpg');
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

-- Host: localhost    Database: nkart
-- ------------------------------------------------------
-- Server version	8.0.27
--
-- Table structure for table `sub_category`
--
CREATE DATABASE IF NOT EXISTS nKart;
USE nKart;

DROP TABLE IF EXISTS `sub_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sub_category` (
  `CategoryID` int DEFAULT NULL,
  `SubCategory` int NOT NULL,
  `CategoryName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SubCategory`),
  KEY `CategoryID` (`CategoryID`),
  CONSTRAINT `sub_category_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `product_category` (`CategoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_category`
--

LOCK TABLES `sub_category` WRITE;
/*!40000 ALTER TABLE `sub_category` DISABLE KEYS */;
INSERT INTO `sub_category` VALUES (1,1,'Smartphones'),(1,2,'Apple Accessories'),(1,3,'Tablet Accessories'),(1,4,'Headphones & Headsets'),(1,5,'Power Banks'),(1,6,'Smart Wearables'),(1,7,'Docking Stations'),(2,8,'Smart TVs'),(2,9,'4K Ultra HD TVs'),(2,10,'Streaming Devices'),(2,11,'Gaming Consoles'),(2,12,'Projectors'),(2,13,'Party Speakers'),(2,14,'Musical Keyboards'),(3,15,'Air Conditioners'),(3,16,'Air Coolers'),(3,17,'Air Purifiers'),(3,18,'Washing Machines'),(3,19,'Refrigerators'),(3,20,'Vacuum Cleaners'),(4,22,'Laptops'),(4,23,'Desktops Computers'),(4,24,'Bluetooth & WiFi Speakers'),(4,25,'Wireless Hotspot'),(4,26,'Data Storage Devices'),(4,27,'Printers'),(4,28,'Mouse & Keyboards'),(5,29,'DSLR Cameras'),(5,30,'Mirrorless Cameras'),(5,31,'ProSumer Cameras'),(5,32,'Binoculars'),(5,33,'Camera Accessories'),(5,34,'Tripods & Monopods'),(5,35,'Memory Cards'),(6,36,'Microwave Ovens'),(6,37,'Water Purifiers'),(6,38,'Juicer Mixer Grinders'),(6,39,'Food Processors'),(6,40,'Kitchen Chimneys'),(6,41,'Sandwich Makers'),(6,42,'Electric Kettles'),(7,43,'Shavers & Trimmers'),(7,44,'Hair Dryers & Stylers'),(7,45,'Hygiene & Personal Care'),(7,46,'Digital Thermometers'),(7,47,'Epilators'),(7,48,'Massager'),(7,49,'Grooming Collection'),(8,50,'Shavers & Trimmers'),(8,51,'Smart Devices'),(8,52,'Smart Sensors'),(8,53,'Smart Lights'),(8,54,'Audio/Video Accessories'),(8,55,'Cleaners & Protectors'),(8,56,'Chargers & Adapters');
/*!40000 ALTER TABLE `sub_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
--
-- Host: localhost    Database: nkart
-- ------------------------------------------------------
-- Server version	8.0.27
-- Table structure for table `users`
--
CREATE DATABASE IF NOT EXISTS nKart;
USE nKart;

DROP TABLE IF EXISTS `nkart.users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userId` int NOT NULL,
  `salutation` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `lastUpdated` date DEFAULT NULL,
  `lastLogin` date DEFAULT NULL,
  `passwordHash` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (0,'Mr','Harsh','Gaur','harsh.gaur01@nagarro.com','9999765342','2021-11-24','2021-11-24','2021-11-24','Harsh@1234','HarshGaur'),(1,'Mr','Amit','Kumar','amit.kumar01@nagarro.com','9999769838','2021-11-11','2021-11-11','2021-11-11','nagarro@123','amit.kumar01'),(2,'Mr','KrishnaKanth','Gupta','krishnakant@nagarro.com','9926855305','2021-11-11','2021-11-11','2021-11-11','nagarro@1234','krishnakant'),(3,'Mr','Harsh','Vishavkarma','harsh.karma01@nagarro.com','9999765342','2021-11-24','2021-11-24','2021-11-24','Harsh@123456','HarshVishavkarma');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

-- Host: localhost    Database: nkart
-- ------------------------------------------------------
-- Server version	8.0.27
-- Table structure for table `address`
--
CREATE DATABASE IF NOT EXISTS nKart;
USE nKart;

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `addressId` int NOT NULL,
  `userId` int DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `isDefault` tinyint(1) DEFAULT NULL,
  `isDeleted` tinyint(1) DEFAULT NULL,
  `contactPerson` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`addressId`),
  KEY `userId` (`userId`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,1,'102-B, Sector-38','Gurgaon','Gurgaon','India','122003',1,0,'Amit Yadav','9999769838'),(2,2,'Hno 1000','Indore','MP','India','122766',1,0,'Krishankanth Gupta','9926855305');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;


-- Host: localhost    Database: nkart
-- ------------------------------------------------------
-- Server version	8.0.27
-- Table structure for table `wish_list`
--
CREATE DATABASE IF NOT EXISTS nKart;
USE nKart;

DROP TABLE IF EXISTS `wish_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wish_list` (
  `productId` int DEFAULT NULL,
  `userId` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  KEY `userId` (`userId`),
  CONSTRAINT `wish_list_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `products` (`productId`),
  CONSTRAINT `wish_list_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Dumping data for table `wish_list`
--

LOCK TABLES `wish_list` WRITE;
/*!40000 ALTER TABLE `wish_list` DISABLE KEYS */;
INSERT INTO `wish_list` VALUES (2,1,2),(4,1,4),(5,1,5),(5,2,6),(4,2,7),(3,2,8),(2,2,9);
/*!40000 ALTER TABLE `wish_list` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `cart_list`
--
CREATE DATABASE IF NOT EXISTS nKart;
USE nKart;

DROP TABLE IF EXISTS `cart_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_list` (
  `productId` int DEFAULT NULL,
  `userId` int DEFAULT NULL,
  `colorId` int DEFAULT NULL,
  `sizeId` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `cart_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cart_id`),
  KEY `productId` (`productId`),
  KEY `userId` (`userId`),
  KEY `colorId` (`colorId`),
  KEY `sizeId` (`sizeId`),
  CONSTRAINT `cart_list_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `products` (`productId`),
  CONSTRAINT `cart_list_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`),
  CONSTRAINT `cart_list_ibfk_3` FOREIGN KEY (`colorId`) REFERENCES `item_color` (`ColorID`),
  CONSTRAINT `cart_list_ibfk_4` FOREIGN KEY (`sizeId`) REFERENCES `item_size` (`SizeID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Dumping data for table `cart_list`
--

LOCK TABLES `cart_list` WRITE;
/*!40000 ALTER TABLE `cart_list` DISABLE KEYS */;
INSERT INTO `cart_list` VALUES (3,1,2,2,1,11),(4,1,1,1,1,12),(2,1,1,1,1,13);
/*!40000 ALTER TABLE `cart_list` ENABLE KEYS */;
UNLOCK TABLES;