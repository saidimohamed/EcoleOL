-- MySQL dump 10.13  Distrib 5.7.14, for Win64 (x86_64)
--
-- Host: localhost    Database: ecole
-- ------------------------------------------------------
-- Server version	5.7.14-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `annee_scolaire`
--

DROP TABLE IF EXISTS `annee_scolaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `annee_scolaire` (
  `code_annee` varchar(40) NOT NULL,
  `designation` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`code_annee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `annee_scolaire`
--

LOCK TABLES `annee_scolaire` WRITE;
/*!40000 ALTER TABLE `annee_scolaire` DISABLE KEYS */;
INSERT INTO `annee_scolaire` VALUES ('1','2017-2018');
/*!40000 ALTER TABLE `annee_scolaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classe`
--

DROP TABLE IF EXISTS `classe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classe` (
  `code_classe` varchar(40) NOT NULL,
  `designation` varchar(20) DEFAULT NULL,
  `code_niveau` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`code_classe`),
  KEY `code_niveau` (`code_niveau`),
  CONSTRAINT `classe_ibfk_1` FOREIGN KEY (`code_niveau`) REFERENCES `niveau` (`code_niveau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classe`
--

LOCK TABLES `classe` WRITE;
/*!40000 ALTER TABLE `classe` DISABLE KEYS */;
INSERT INTO `classe` VALUES ('14dd9ce696034c34ba7bf5a4dcd09568','gggttt','847b89bf1c674a059ba9c11fe21d5e7f'),('3ab830d69cf44c3d8e766a670ae44a06','ttttggg','847b89bf1c674a059ba9c11fe21d5e7f'),('b2e7294232f644c0b3199789457a80db','classe 1','b539b188303a42a3811b395566cba1b1');
/*!40000 ALTER TABLE `classe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compte`
--

DROP TABLE IF EXISTS `compte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compte` (
  `id_utilisateur` varchar(40) DEFAULT NULL,
  `login` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `type_compte` varchar(10) DEFAULT NULL,
  KEY `id_utilisateur` (`id_utilisateur`),
  CONSTRAINT `compte_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compte`
--

LOCK TABLES `compte` WRITE;
/*!40000 ALTER TABLE `compte` DISABLE KEYS */;
/*!40000 ALTER TABLE `compte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eleve`
--

DROP TABLE IF EXISTS `eleve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eleve` (
  `matricule_eleve` varchar(40) NOT NULL,
  `nom` varchar(20) DEFAULT NULL,
  `prenom` varchar(20) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `sexe` varchar(10) DEFAULT NULL,
  `deleted` date DEFAULT NULL,
  PRIMARY KEY (`matricule_eleve`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eleve`
--

LOCK TABLES `eleve` WRITE;
/*!40000 ALTER TABLE `eleve` DISABLE KEYS */;
INSERT INTO `eleve` VALUES ('0586271dbd184b8fad38754bdb0285a8','da','da','2016-12-14','Homme',NULL),('1a1ae241ebbf42f9a2525ad3d0763b23','dali3','dali','2016-12-25','Homme','2016-12-25'),('50b19b3afdb0477f965fe6f981ab2cd9','dali2','dali2','2016-12-29','Homme','2016-12-25'),('7f1d36e68e20460c9e2a79062da19395','test','test','2016-12-08','Femme','2016-12-25'),('a6fdcf30529644afbe1f5ea51018f69a','Dali3','Dali4','2016-12-08','Homme','2016-12-25'),('c552e2f91b7849e7acb595b5ad3faac4','dali','dali','2016-12-22','Homme','2016-12-24'),('c956148da5434ef292b511634a04b435','dali','dali','2016-12-07','Homme','2016-12-25'),('dbfe14c136b94ccf89fcab239c571fee','zef','zef','2016-12-09','Homme',NULL),('df563b2f98b44d538d80173aca340d9f','dalidou','dalidou','2016-12-18','Homme','2016-12-25'),('e27bb20dd992499d899e2673e9f0a364','Ammi','Slim2','2004-07-19','Homme','2016-12-24'),('eb75d8521b724e3195ca52905ab6108a','dali1','dali1','2016-12-16','Homme','2016-12-25');
/*!40000 ALTER TABLE `eleve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eleve_classe`
--

DROP TABLE IF EXISTS `eleve_classe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eleve_classe` (
  `matricule_eleve` varchar(40) NOT NULL,
  `code_classe` varchar(40) NOT NULL,
  `code_annee` varchar(40) NOT NULL,
  PRIMARY KEY (`matricule_eleve`,`code_classe`,`code_annee`),
  KEY `code_classe` (`code_classe`),
  KEY `code_annee` (`code_annee`),
  CONSTRAINT `eleve_classe_ibfk_1` FOREIGN KEY (`code_classe`) REFERENCES `classe` (`code_classe`),
  CONSTRAINT `eleve_classe_ibfk_2` FOREIGN KEY (`matricule_eleve`) REFERENCES `eleve` (`matricule_eleve`),
  CONSTRAINT `eleve_classe_ibfk_3` FOREIGN KEY (`code_annee`) REFERENCES `annee_scolaire` (`code_annee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eleve_classe`
--

LOCK TABLES `eleve_classe` WRITE;
/*!40000 ALTER TABLE `eleve_classe` DISABLE KEYS */;
INSERT INTO `eleve_classe` VALUES ('0586271dbd184b8fad38754bdb0285a8','14dd9ce696034c34ba7bf5a4dcd09568','1'),('dbfe14c136b94ccf89fcab239c571fee','b2e7294232f644c0b3199789457a80db','1'),('e27bb20dd992499d899e2673e9f0a364','b2e7294232f644c0b3199789457a80db','1');
/*!40000 ALTER TABLE `eleve_classe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matiere`
--

DROP TABLE IF EXISTS `matiere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matiere` (
  `code_matiere` varchar(40) NOT NULL,
  `designation` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`code_matiere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matiere`
--

LOCK TABLES `matiere` WRITE;
/*!40000 ALTER TABLE `matiere` DISABLE KEYS */;
/*!40000 ALTER TABLE `matiere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `niveau`
--

DROP TABLE IF EXISTS `niveau`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `niveau` (
  `code_niveau` varchar(40) NOT NULL,
  `designation` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`code_niveau`),
  UNIQUE KEY `unkdsg` (`designation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `niveau`
--

LOCK TABLES `niveau` WRITE;
/*!40000 ALTER TABLE `niveau` DISABLE KEYS */;
INSERT INTO `niveau` VALUES ('2693bda53dbd40b29afc967753bb7282','2 eme'),('c795d400d09847e484ef697b38d3cc48','3 eme'),('3f6862a0f4ce4e6aaa4b0a235a6aa910','4 eme'),('147eaf57d659436aa452eba745cc6c77','5 eme'),('b539b188303a42a3811b395566cba1b1','6 eme'),('12bf085702c54001a158ee4309be5849','7 eme'),('847b89bf1c674a059ba9c11fe21d5e7f','8 eme');
/*!40000 ALTER TABLE `niveau` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `niveau_matiere`
--

DROP TABLE IF EXISTS `niveau_matiere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `niveau_matiere` (
  `code_niveau` varchar(40) NOT NULL,
  `code_matiere` varchar(40) NOT NULL,
  PRIMARY KEY (`code_niveau`,`code_matiere`),
  KEY `code_matiere` (`code_matiere`),
  CONSTRAINT `niveau_matiere_ibfk_1` FOREIGN KEY (`code_niveau`) REFERENCES `niveau` (`code_niveau`),
  CONSTRAINT `niveau_matiere_ibfk_2` FOREIGN KEY (`code_matiere`) REFERENCES `matiere` (`code_matiere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `niveau_matiere`
--

LOCK TABLES `niveau_matiere` WRITE;
/*!40000 ALTER TABLE `niveau_matiere` DISABLE KEYS */;
/*!40000 ALTER TABLE `niveau_matiere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parent`
--

DROP TABLE IF EXISTS `parent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parent` (
  `id_utilisateur` varchar(40) NOT NULL,
  `matricule_eleve` varchar(40) NOT NULL,
  PRIMARY KEY (`id_utilisateur`,`matricule_eleve`),
  KEY `fk_eleve` (`matricule_eleve`),
  CONSTRAINT `parent_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`),
  CONSTRAINT `parent_ibfk_2` FOREIGN KEY (`matricule_eleve`) REFERENCES `eleve` (`matricule_eleve`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parent`
--

LOCK TABLES `parent` WRITE;
/*!40000 ALTER TABLE `parent` DISABLE KEYS */;
INSERT INTO `parent` VALUES ('99be04fae8ae4a98906d6ff7c489ede7','0586271dbd184b8fad38754bdb0285a8'),('99be04fae8ae4a98906d6ff7c489ede7','dbfe14c136b94ccf89fcab239c571fee');
/*!40000 ALTER TABLE `parent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utilisateur` (
  `id_utilisateur` varchar(40) NOT NULL,
  `nom` varchar(20) DEFAULT NULL,
  `prenom` varchar(20) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `adresse` varchar(100) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `fonction` varchar(20) DEFAULT NULL,
  `role` varchar(10) DEFAULT NULL,
  `sexe` varchar(10) DEFAULT NULL,
  `cin` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_utilisateur`),
  UNIQUE KEY `ucin` (`cin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES ('99be04fae8ae4a98906d6ff7c489ede7','Saidi','Khalil','1990-03-10','3 rue x, mourouj 4','25030123','Ingenieur','Parent','Homme','07123456'),('d7a8b0ba3a3e4176947c021a6b0984de','Test','final','2016-08-11','final','25050600','final','Parent','Homme','02345678'),('e7b38dd93337454495440176d33bbce2','mlkj','mlkj','2016-08-03','mlkj','23456789','mlk','Parent','Homme','01234568');
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-14 10:15:05
