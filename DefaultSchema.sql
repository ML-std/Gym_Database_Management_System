CREATE DATABASE  IF NOT EXISTS `gym_management_database_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `gym_management_database_system`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gym_management_database_system
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.11-MariaDB

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
-- Table structure for table `attends`
--

DROP TABLE IF EXISTS `attends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attends` (
  `CID` int(11) NOT NULL AUTO_INCREMENT,
  `Batch_ID` int(11) NOT NULL,
  PRIMARY KEY (`CID`,`Batch_ID`),
  KEY `Batch_ID` (`Batch_ID`),
  CONSTRAINT `attends_ibfk_1` FOREIGN KEY (`CID`) REFERENCES `customer` (`CID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `attends_ibfk_2` FOREIGN KEY (`Batch_ID`) REFERENCES `batches` (`Batch_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=300021 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attends`
--

LOCK TABLES `attends` WRITE;
/*!40000 ALTER TABLE `attends` DISABLE KEYS */;
INSERT INTO `attends` VALUES (300002,500002),(300005,500003),(300005,500004),(300006,500003),(300007,500004),(300008,500004),(300010,500005),(300012,500001),(300015,500003),(300016,500003),(300017,500004),(300018,500004),(300020,500005);
/*!40000 ALTER TABLE `attends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batches`
--

DROP TABLE IF EXISTS `batches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batches` (
  `Batch_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Start_Time` time DEFAULT NULL,
  `End_Time` time DEFAULT NULL,
  `Batch_type` varchar(45) NOT NULL,
  `Branch_ID` int(11) NOT NULL,
  `Employee_ID` int(11) NOT NULL,
  PRIMARY KEY (`Batch_ID`),
  KEY `Branch_ID` (`Branch_ID`),
  KEY `batches_ibfk_2_idx` (`Employee_ID`),
  KEY `batches_ibfk_2` (`Employee_ID`),
  CONSTRAINT `batches_ibfk_1` FOREIGN KEY (`Branch_ID`) REFERENCES `fitness_branch` (`Branch_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `batches_ibfk_2` FOREIGN KEY (`Employee_ID`) REFERENCES `trainer` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=500016 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batches`
--

LOCK TABLES `batches` WRITE;
/*!40000 ALTER TABLE `batches` DISABLE KEYS */;
INSERT INTO `batches` VALUES (500001,'03:00:00','40:00:00','Cardio',100005,220001),(500002,'03:00:00','40:00:00','Cardio',100005,220002),(500003,'03:00:00','40:00:00','Strength',100005,220003),(500004,'02:00:00','03:00:00','Strength',100005,220004),(500005,'03:00:00','04:00:00','Strength',100005,220005),(500006,'04:00:00','05:00:00','Strength',100005,220006),(500007,'04:00:00','05:00:00','Stroke',100005,220007),(500008,'03:00:00','04:00:00','Stroke',100001,220008);
/*!40000 ALTER TABLE `batches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cleaner`
--

DROP TABLE IF EXISTS `cleaner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cleaner` (
  `Daily_Salary` int(11) NOT NULL,
  `Employee_ID` int(11) NOT NULL,
  PRIMARY KEY (`Employee_ID`),
  CONSTRAINT `cleaner_ibfk_1` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cleaner`
--

LOCK TABLES `cleaner` WRITE;
/*!40000 ALTER TABLE `cleaner` DISABLE KEYS */;
INSERT INTO `cleaner` VALUES (78,230001),(70,230002),(65,230003),(90,230004),(98,230005),(100,230006),(70,230007),(75,230008),(89,230009),(87,230010),(100,230011),(75,230012),(80,230013),(60,230014),(70,230015);
/*!40000 ALTER TABLE `cleaner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cleans`
--

DROP TABLE IF EXISTS `cleans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cleans` (
  `Employee_ID` int(11) NOT NULL,
  `Facility_ID` int(11) NOT NULL,
  PRIMARY KEY (`Employee_ID`,`Facility_ID`),
  KEY `Facility_ID` (`Facility_ID`),
  CONSTRAINT `cleans_ibfk_1` FOREIGN KEY (`Facility_ID`) REFERENCES `facilities` (`Facility_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cleans_ibfk_2` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cleans`
--

LOCK TABLES `cleans` WRITE;
/*!40000 ALTER TABLE `cleans` DISABLE KEYS */;
INSERT INTO `cleans` VALUES (230001,110001),(230001,110002),(230002,110001),(230003,110001),(230004,110003),(230004,110004),(230005,110004),(230006,110003),(230007,110005),(230008,110006),(230009,110006),(230010,110008),(230011,110007),(230012,110008),(230013,110009),(230014,110010),(230015,110010);
/*!40000 ALTER TABLE `cleans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cphone`
--

DROP TABLE IF EXISTS `cphone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cphone` (
  `Phone` varchar(12) NOT NULL,
  `CID` int(11) NOT NULL,
  PRIMARY KEY (`Phone`,`CID`),
  KEY `CID` (`CID`),
  CONSTRAINT `CID` FOREIGN KEY (`CID`) REFERENCES `customer` (`CID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cphone`
--

LOCK TABLES `cphone` WRITE;
/*!40000 ALTER TABLE `cphone` DISABLE KEYS */;
INSERT INTO `cphone` VALUES ('402-612-2846',300010),('402-998-9145',300020),('502-553-3879',300006),('502-652-6844',300015),('502-661-6066',300005),('502-674-8841',300016),('502-951-7741',300005),('510-625-5496',300002),('510-684-2568',300012),('530-720-3652',300002),('534-555-7575',300046),('534-723-8888',300045),('534-723-9999',300046),('845-663-1388',300008),('845-998-9512',300018),('907-846-9952',300007),('907-867-3867',300007),('907-953-5858',300017);
/*!40000 ALTER TABLE `cphone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `CID` int(11) NOT NULL AUTO_INCREMENT,
  `First_Name` varchar(45) NOT NULL,
  `Middle_Name` varchar(45) DEFAULT NULL,
  `Last_Name` varchar(45) NOT NULL,
  `Sex` enum('MALE','FEMALE') NOT NULL,
  `Address` varchar(45) NOT NULL,
  `SSN` int(11) NOT NULL,
  `B_Date` date NOT NULL,
  `Employee_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`CID`),
  KEY `customer_ibfk_1` (`Employee_ID`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`Employee_ID`) REFERENCES `trainer` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=300052 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (300002,'Emily','M','Bedolla','FEMALE','4131  Meadow Lane Napa California',609307119,'1987-11-22',220001),(300005,'Angela','J','Dawson','FEMALE','3938  Cerullo Road Louisville Kentucky',402967582,'1986-06-06',220006),(300006,'Andy','P','Hans','FEMALE','4371  Radford Street Louisville Kentucky',407395706,'1970-05-15',220005),(300007,'Donna','R','Carmen','MALE','4841  Longview Avenue Brooklyn New York',134142865,'1976-11-14',220007),(300008,'Ernie','J','Campos','MALE','2764  Parrish Avenue Gerry New York',635762882,'1969-03-06',220008),(300010,'Kevin','M','Lopez','FEMALE','4056  Commerce Boulevard Omaha Nebraska',505451777,'1986-04-23',220010),(300012,'Frances','K','Meys','FEMALE','4905  Murphy Court Riverside California',606170587,'1956-03-06',220001),(300015,'George','R','Lebowitz','FEMALE','2079  Coffman Alley Madisonville Kentucky',405327895,'1952-08-19',220005),(300016,'Dolores','A','Collins','FEMALE','4668  Daffodil Lane Bagdad Kentucky',226651947,'1959-03-12',220006),(300017,'Jesus','K','Colon','MALE','2388  Cameron Road Niagara Falls New York',72227922,'1992-06-04',220008),(300018,'Brian','J','Anderson','MALE','2054  Bell Street New York New York',65944364,'1973-12-05',220007),(300020,'David','S','Florez','FEMALE','543  Bungalow Road Omaha Nebraska',507389183,'1956-08-10',220009);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_report`
--

DROP TABLE IF EXISTS `customer_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_report` (
  `Report_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CID` int(11) NOT NULL,
  `Fat(%)` int(11) NOT NULL,
  `Weight(kg)` int(11) NOT NULL,
  `Height(cm)` int(11) NOT NULL,
  `R_Date` date NOT NULL,
  PRIMARY KEY (`Report_ID`,`CID`),
  KEY `CID` (`CID`),
  CONSTRAINT `customer_report_ibfk_1` FOREIGN KEY (`CID`) REFERENCES `customer` (`CID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=410014 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_report`
--

LOCK TABLES `customer_report` WRITE;
/*!40000 ALTER TABLE `customer_report` DISABLE KEYS */;
INSERT INTO `customer_report` VALUES (400002,300002,22,90,196,'2019-06-20'),(400005,300005,29,110,174,'2019-08-22'),(400006,300006,15,68,175,'2019-08-24'),(400007,300007,14,60,169,'2019-08-30'),(400008,300008,12,72,186,'2019-09-09'),(400010,300010,21,84,183,'2019-10-24'),(400012,300012,17,78,180,'2020-01-06'),(400015,300015,12,70,173,'2020-01-16'),(400016,300016,16,72,180,'2020-01-25'),(400017,300017,18,81,194,'2020-01-28'),(400018,300018,26,100,174,'2020-02-04'),(400020,300020,21,88,179,'2020-02-09'),(400021,300002,19,82,193,'2020-02-16'),(400022,300005,24,91,190,'2020-02-24'),(400023,300018,22,84,169,'2020-03-02');
/*!40000 ALTER TABLE `customer_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `Employee_ID` int(11) NOT NULL AUTO_INCREMENT,
  `First_Name` varchar(45) NOT NULL,
  `Middle_Name` varchar(45) DEFAULT NULL,
  `Last_Name` varchar(45) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `Branch_ID` int(11) NOT NULL,
  `SSN` int(11) NOT NULL,
  PRIMARY KEY (`Employee_ID`),
  KEY `Branch_ID` (`Branch_ID`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`Branch_ID`) REFERENCES `fitness_branch` (`Branch_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=230027 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (200001,'Kyle ','M','Pasquale','1665  Par Drive Santa Barbara California','200001',100001,767865718),(200002,'Evan','M','Semon','2980  Woodhill Avenue Glen Burnie Maryland','000000',100002,323876461),(200003,'Gregory','N','Morgan','2893  Carolyns Circle Bagdad Kentucky','000000',100003,287546325),(200004,'Judith','W','Geiger','57  Deans Lane New York New York','000000',100004,778152565),(200005,'Martina ','P','Anderson','2719  Kyle Street Bayard Nebraska','000000',100005,751234576),(210001,'Fern ','J','Smith','3562  Riverwood Drive Sacramento California','210001',100001,951357486),(210002,'Michael ','S','Brant','159  Green Gate Lane Baltimore Maryland','000000',100002,684257138),(210003,'Elton','A','Zamora','979  Gregory Lane Louisville Kentucky','000000',100003,754254568),(210004,'Gary','T','Tran','2259  Gnatty Creek Road Bethpage New York','000000',100004,751248625),(210005,'Russel','H','Koster','3757  Kyle Street Bingham Nebraska','000000',100005,953684515),(220001,'Johnny','K','Bravo','2905  Prospect Valley Road Gardena California','220001',100001,856742687),(220002,'Kevin','C','Robins','1539  Liberty Avenue Anaheim California','000000',100001,137568425),(220003,'Justin ','M','Hux','1974  Chatham Way Reston Maryland','000000',100002,521547856),(220004,'Consuelo ','J','Goad','1300  Bluff Street Greenbelt Maryland','000000',100002,624751358),(220005,'Richard ','J','Wilson',' 279  Karen Lane Louisville Kentucky','000000',100003,451278685),(220006,'Joyce ','K','Palombo','4045  Crosswind Drive Madisonville Kentucky','000000',100003,456215458),(220007,'William ','V','Wilcox',' 4237  Benedum Drive Brewster New York','000000',100004,685742145),(220008,'Bryon ','N','Hart','3528  Elm Drive New York New York','000000',100004,159824744),(220009,'Dianne ','R','Pettey','3929  Commerce Boulevard Cordova Nebraska','000000',100005,566855245),(220010,'Ruthanne','J','Payne','476  Commerce Boulevard Omaha Nebraska','000000',100005,751245844),(230001,'Betty ','A','Almedia','3664  Thompson Drive Oakland California','000000',100001,754245786),(230002,'Jed','C','Ringler','564  Margaret Street LOS ANGELES California','000000',100001,524525457),(230003,'Michael ','I','Fuentez','4145  State Street LOS ANGELES California','000000',100001,758412587),(230004,'Victor','C','Rivers','5001  Woodhill Avenue Towson Maryland','000000',100002,685452158),(230005,'Adam','C','Vaughan','3584 Baltimore Hewes Avenue Maryland','000000',100002,588585823),(230006,'Gwen','E','Oakman','3463  Bluff Street Beltsville Maryland','000000',100002,321454865),(230007,'Victor','R','Leonard','3375  Gregory Lane Stamping Ground Kentucky','000000',100003,756265412),(230008,'Damion','J','alatorre','2726  Zappia Drive Springfield Kentucky','000000',100003,555874556),(230009,'Billy','L','Garcia','2849  May Street Lexington Kentucky','000000',100003,951747425),(230010,'Christie','J','Marshall','1322  Farnum Road New York New York','000000',100004,556258741),(230011,'Laura','D','Gutiterrez','445  Browning Lane Binghamton New York','000000',100004,455445624),(230012,'Hazel','K','Myles','2103  Nuzum Court Buffalo New York','000000',100004,565247865),(230013,'Dennis','K','Todd','4591  Romrog Way Cedar Rapids Nebraska','000000',100005,458515687),(230014,'John','M','Lozano','4670  Oak Way Lincoln Nebraska','000000',100005,573152548),(230015,'Manuel','T','Ma','775  Bungalow Road Omaha Nebraska','000000',100005,968574213);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ephone`
--

DROP TABLE IF EXISTS `ephone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ephone` (
  `Phone` varchar(12) NOT NULL,
  `Employee_ID` int(11) NOT NULL,
  PRIMARY KEY (`Phone`,`Employee_ID`),
  KEY `ephone_ibfk_1` (`Employee_ID`),
  CONSTRAINT `ephone_ibfk_1` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ephone`
--

LOCK TABLES `ephone` WRITE;
/*!40000 ALTER TABLE `ephone` DISABLE KEYS */;
INSERT INTO `ephone` VALUES ('123-456-789',230026),('123-456-7890',230017),('123-456-7890',230021),('240-684-1255',220004),('240-888-5028',200002),('302-354-6958',220005),('302-445-6214',210003),('302-452-1525',230007),('302-458-1301',230009),('302-545-8563',230008),('302-886-9150',200003),('310-352-4562',220002),('310-422-6981',200001),('310-598-4521',230003),('315-124-8851',230011),('315-236-5796',200004),('315-245-2568',210004),('315-356-8258',220007),('315-452-1387',230012),('315-468-0015',230010),('315-545-1005',220008),('315-856-3952',200004),('323-242-4351',200001),('323-546-2545',220001),('323-658-4562',210001),('323-658-5412',230002),('323-688-4125',230001),('402-113-6745',230015),('402-200-5461',200005),('402-365-1565',200005),('402-368-7710',230013),('402-399-7512',230014),('402-546-8512',220009),('402-562-5442',210005),('402-789-5145',220010),('502-254-0236',220006),('502-779-5083',200003),('505-452-4582',210002),('505-486-5014',230005),('505-524-5168',220003),('505-632-1456',230004),('505-887-8900',200002);
/*!40000 ALTER TABLE `ephone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment` (
  `EID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Manufacturer` varchar(45) NOT NULL,
  `Condition` enum('BAD','FAIR','GOOD') NOT NULL,
  `Type` varchar(45) NOT NULL,
  `Count` int(11) NOT NULL,
  `Branch_ID` int(11) NOT NULL,
  PRIMARY KEY (`EID`),
  KEY `Branch_ID` (`Branch_ID`),
  CONSTRAINT `equipment_ibfk_1` FOREIGN KEY (`Branch_ID`) REFERENCES `fitness_branch` (`Branch_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=600053 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (600001,'CA_Equipment','Speedo','GOOD','swimming_googles',5,100001),(600002,'CA_Equipment','CAP Barbell','FAIR','dumbbell',10,100001),(600003,'MD_Equipment','Speedo','FAIR','swimming_googles',8,100002),(600004,'MD_Equipment','Power Block','BAD','dumbbell',5,100002),(600005,'KY_Equipment','Aqua Sphere','BAD','swimming_googles',4,100003),(600006,'KY_Equipment','CAP Barbell','GOOD','dumbbell',15,100003),(600007,'NY_Equipment','Aqua Sphere','FAIR','swimming_googles',20,100004),(600008,'NY_Equipment','Bowflex','FAIR','dumbbell',12,100004),(600009,'NE_Equipment','Speedo','GOOD','swimming_googles',15,100005),(600010,'NE_Equipment','Power Block','GOOD','dumbbell',10,100005);
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment_maintains`
--

DROP TABLE IF EXISTS `equipment_maintains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment_maintains` (
  `EID` int(11) NOT NULL,
  `Employee_ID` int(11) NOT NULL,
  PRIMARY KEY (`EID`,`Employee_ID`),
  KEY `Employee_ID)` (`Employee_ID`),
  CONSTRAINT `Employee_ID)` FOREIGN KEY (`Employee_ID`) REFERENCES `receptionist` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `equipment_maintains_ibfk_1` FOREIGN KEY (`EID`) REFERENCES `equipment` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment_maintains`
--

LOCK TABLES `equipment_maintains` WRITE;
/*!40000 ALTER TABLE `equipment_maintains` DISABLE KEYS */;
INSERT INTO `equipment_maintains` VALUES (600001,210001),(600002,210001),(600003,210002),(600004,210002),(600005,210003),(600006,210003),(600007,210004),(600008,210004),(600009,210005),(600010,210005),(600011,210001),(600012,210001),(600015,210001),(600016,210001),(600021,210001),(600022,210001),(600023,210001),(600024,210001),(600025,210001),(600026,210001),(600027,210001),(600028,210001),(600029,210001),(600030,210001),(600031,210001),(600032,210001),(600033,210001),(600034,210001),(600035,210001),(600036,210001),(600037,210001),(600038,210001),(600039,210001),(600040,210001),(600041,210001),(600042,210001),(600043,210001),(600044,210001),(600045,210001),(600046,210001),(600047,210001),(600048,210001),(600052,210001);
/*!40000 ALTER TABLE `equipment_maintains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facilities`
--

DROP TABLE IF EXISTS `facilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facilities` (
  `Facility_ID` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Type` varchar(45) NOT NULL,
  `Number` int(11) NOT NULL,
  `Branch_ID` int(11) NOT NULL,
  PRIMARY KEY (`Facility_ID`),
  KEY `Branch_ID` (`Branch_ID`),
  CONSTRAINT `facilities_ibfk_1` FOREIGN KEY (`Branch_ID`) REFERENCES `fitness_branch` (`Branch_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facilities`
--

LOCK TABLES `facilities` WRITE;
/*!40000 ALTER TABLE `facilities` DISABLE KEYS */;
INSERT INTO `facilities` VALUES (110001,'Swimming_CA','Swimming Center',4543,100001),(110002,'Fitness_CA','Fitness Center',4542,100001),(110003,'Swimming_MD','Swimming Center',3452,100002),(110004,'Fitness_MD','Fitness Center',3453,100002),(110005,'Swimming_KY','Swimming Center',5522,100003),(110006,'Fitness_KY','Fitness Center',5523,100003),(110007,'Swimming_NY','Swimming Center',9293,100004),(110008,'Fitness_NY','Fitness Center',9294,100004),(110009,'Swimming_NE','Swimming Center',1223,100005),(110010,'Fitness_NE','Fitness Center',1225,100005);
/*!40000 ALTER TABLE `facilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facility_review`
--

DROP TABLE IF EXISTS `facility_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facility_review` (
  `CID` int(11) NOT NULL,
  `Facility_ID` int(11) NOT NULL,
  `Rating` int(11) NOT NULL,
  PRIMARY KEY (`CID`,`Facility_ID`),
  KEY `Facility_ID` (`Facility_ID`),
  CONSTRAINT `facility_review_ibfk_1` FOREIGN KEY (`CID`) REFERENCES `customer` (`CID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `facility_review_ibfk_2` FOREIGN KEY (`Facility_ID`) REFERENCES `facilities` (`Facility_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facility_review`
--

LOCK TABLES `facility_review` WRITE;
/*!40000 ALTER TABLE `facility_review` DISABLE KEYS */;
INSERT INTO `facility_review` VALUES (300002,110001,8),(300002,110002,8),(300002,110009,9),(300005,110007,9),(300007,110007,4),(300015,110005,9),(300016,110006,9),(300018,110008,7);
/*!40000 ALTER TABLE `facility_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fitness_branch`
--

DROP TABLE IF EXISTS `fitness_branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fitness_branch` (
  `Branch_ID` int(11) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `Phone` varchar(12) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Employee_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Branch_ID`),
  KEY `Employee_ID` (`Employee_ID`),
  CONSTRAINT `Employee_ID` FOREIGN KEY (`Employee_ID`) REFERENCES `manager` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fitness_branch`
--

LOCK TABLES `fitness_branch` WRITE;
/*!40000 ALTER TABLE `fitness_branch` DISABLE KEYS */;
INSERT INTO `fitness_branch` VALUES (100001,'2701 Wilson Street Vista California','510-349-5413','gym_management_database_system_CA',200001),(100002,'4377  Bluff Street Upper Marlboro Maryland','240-802-2982','gym_management_database_system_MD',200002),(100003,'4461  Straford Park Hyden Kentucky','270-473-1323','gym_management_database_system_KY',200003),(100004,'4709  Anmoore Road New York New York ','212-329-4432','gym_management_database_system_NY',200004),(100005,'4044  Poling Farm Road Omaha Nebraska','402-715-4747','gym_management_database_system_NE',200005);
/*!40000 ALTER TABLE `fitness_branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `Monthly_Salary` int(11) NOT NULL,
  `Employee_ID` int(11) NOT NULL,
  PRIMARY KEY (`Employee_ID`),
  CONSTRAINT `manager_ibfk_1` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (5000,200001),(4500,200002),(3200,200003),(7000,200004),(3000,200005);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receptionist`
--

DROP TABLE IF EXISTS `receptionist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receptionist` (
  `Weekly_Salary` int(11) NOT NULL,
  `Employee_ID` int(11) NOT NULL,
  PRIMARY KEY (`Employee_ID`),
  CONSTRAINT `receptionist_ibfk_1` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receptionist`
--

LOCK TABLES `receptionist` WRITE;
/*!40000 ALTER TABLE `receptionist` DISABLE KEYS */;
INSERT INTO `receptionist` VALUES (700,210001),(690,210002),(750,210003),(1200,210004),(650,210005);
/*!40000 ALTER TABLE `receptionist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports_maintains`
--

DROP TABLE IF EXISTS `reports_maintains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports_maintains` (
  `Report_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CID` int(11) NOT NULL,
  `Employee_ID` int(11) NOT NULL,
  PRIMARY KEY (`Report_ID`,`CID`,`Employee_ID`),
  KEY `CID` (`CID`),
  KEY `reports_maintains_ibfk_2_idx` (`Employee_ID`),
  CONSTRAINT `key` FOREIGN KEY (`Employee_ID`) REFERENCES `receptionist` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reports_maintains_ibfk_3` FOREIGN KEY (`CID`) REFERENCES `customer_report` (`CID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=410014 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports_maintains`
--

LOCK TABLES `reports_maintains` WRITE;
/*!40000 ALTER TABLE `reports_maintains` DISABLE KEYS */;
INSERT INTO `reports_maintains` VALUES (400002,300002,210001),(400005,300005,210002),(400006,300006,210003),(400007,300007,210004),(400008,300008,210005);
/*!40000 ALTER TABLE `reports_maintains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainer`
--

DROP TABLE IF EXISTS `trainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trainer` (
  `Hourly_Salary` int(11) NOT NULL,
  `Employee_ID` int(11) NOT NULL,
  PRIMARY KEY (`Employee_ID`),
  CONSTRAINT `trainer_ibfk_1` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainer`
--

LOCK TABLES `trainer` WRITE;
/*!40000 ALTER TABLE `trainer` DISABLE KEYS */;
INSERT INTO `trainer` VALUES (22,220001),(20,220002),(16,220003),(18,220004),(24,220005),(13,220006),(32,220007),(22,220008),(22,220009),(25,220010),(5000,230021),(3000,230025),(3000,230026);
/*!40000 ALTER TABLE `trainer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainer_review`
--

DROP TABLE IF EXISTS `trainer_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trainer_review` (
  `Employee_ID` int(11) NOT NULL,
  `CID` int(11) NOT NULL,
  `Rating` int(11) NOT NULL,
  PRIMARY KEY (`Employee_ID`,`CID`),
  KEY `CID` (`CID`),
  CONSTRAINT `trainer_review_ibfk_1` FOREIGN KEY (`CID`) REFERENCES `customer` (`CID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `trainer_review_ibfk_2` FOREIGN KEY (`Employee_ID`) REFERENCES `trainer` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainer_review`
--

LOCK TABLES `trainer_review` WRITE;
/*!40000 ALTER TABLE `trainer_review` DISABLE KEYS */;
INSERT INTO `trainer_review` VALUES (220001,300002,4),(220001,300005,9),(220001,300012,8),(220005,300005,6),(220005,300006,8),(220005,300015,4),(220006,300005,7),(220007,300018,8),(220008,300008,8),(220009,300020,6),(220010,300010,1);
/*!40000 ALTER TABLE `trainer_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trains`
--

DROP TABLE IF EXISTS `trains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trains` (
  `Batch_ID` int(11) NOT NULL,
  `Employee_ID` int(11) NOT NULL,
  PRIMARY KEY (`Batch_ID`,`Employee_ID`),
  KEY `trains_ibfk_2` (`Employee_ID`),
  CONSTRAINT `trains_ibfk_1` FOREIGN KEY (`Batch_ID`) REFERENCES `batches` (`Batch_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `trains_ibfk_2` FOREIGN KEY (`Employee_ID`) REFERENCES `trainer` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trains`
--

LOCK TABLES `trains` WRITE;
/*!40000 ALTER TABLE `trains` DISABLE KEYS */;
INSERT INTO `trains` VALUES (500001,220001),(500001,220002),(500002,220003),(500003,220005),(500004,220007),(500005,220009),(500006,220001);
/*!40000 ALTER TABLE `trains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uses`
--

DROP TABLE IF EXISTS `uses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uses` (
  `Facility_ID` int(11) NOT NULL,
  `CID` int(11) NOT NULL,
  PRIMARY KEY (`Facility_ID`,`CID`),
  KEY `CID` (`CID`),
  CONSTRAINT `uses_ibfk_1` FOREIGN KEY (`Facility_ID`) REFERENCES `facilities` (`Facility_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uses_ibfk_2` FOREIGN KEY (`CID`) REFERENCES `customer` (`CID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uses`
--

LOCK TABLES `uses` WRITE;
/*!40000 ALTER TABLE `uses` DISABLE KEYS */;
INSERT INTO `uses` VALUES (110002,300002),(110002,300012),(110005,300005),(110005,300015),(110006,300006),(110006,300015),(110006,300016),(110007,300007),(110007,300017),(110008,300008),(110008,300017),(110008,300018),(110009,300020),(110010,300010);
/*!40000 ALTER TABLE `uses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-11 17:34:44
