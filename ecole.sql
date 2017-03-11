-- MySQL dump 10.13  Distrib 5.7.11, for Win64 (x86_64)
--
-- Host: localhost    Database: ecole
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
  PRIMARY KEY (`matricule_eleve`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eleve`
--

LOCK TABLES `eleve` WRITE;
/*!40000 ALTER TABLE `eleve` DISABLE KEYS */;
INSERT INTO `eleve` VALUES ('67f9812a0a10443a97e33943dd9de5c3','dcs','dcsdc','2016-08-11','Homme'),('8c89046c4d87472fa12db0129bd437b7','khalil','khalil','2016-08-03','Homme');
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
  PRIMARY KEY (`code_niveau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `niveau`
--

LOCK TABLES `niveau` WRITE;
/*!40000 ALTER TABLE `niveau` DISABLE KEYS */;
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
INSERT INTO `parent` VALUES ('902c2d9871c04016887274c0a2ce29b6','67f9812a0a10443a97e33943dd9de5c3'),('ad5dfd65f7c84438aa4e635fddec0bcd','8c89046c4d87472fa12db0129bd437b7');
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
INSERT INTO `utilisateur` VALUES ('902c2d9871c04016887274c0a2ce29b6','dali','dali','2016-07-15','55 aedf56665252','25060190','ing','Enseignant','Homme','07153417'),('ad5dfd65f7c84438aa4e635fddec0bcd','dalidou','dalilou','2016-07-23','5555','25162136','ing','Parent','Homme','07153415');
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

-- Dump completed on 2017-03-11 20:19:44
