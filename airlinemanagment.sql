-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 21 nov. 2022 à 14:49
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `airlinemanagment`
--

-- --------------------------------------------------------

--
-- Structure de la table `airplane`
--

DROP TABLE IF EXISTS `airplane`;
CREATE TABLE IF NOT EXISTS `airplane` (
  `idAirplane` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`idAirplane`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `airplane`
--

INSERT INTO `airplane` (`idAirplane`, `name`, `status`) VALUES
(3, 'Airbus A350 XWB', 0),
(4, 'Antonov An-148/An-158', 0),
(5, 'Boeing 737', 0),
(6, 'Boeing 767', 0);

-- --------------------------------------------------------

--
-- Structure de la table `airport`
--

DROP TABLE IF EXISTS `airport`;
CREATE TABLE IF NOT EXISTS `airport` (
  `idA` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `city` varchar(30) NOT NULL,
  `state` varchar(30) NOT NULL,
  PRIMARY KEY (`idA`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `airport`
--

INSERT INTO `airport` (`idA`, `name`, `city`, `state`) VALUES
(3, 'Tunis Carthage International Airport', 'Carthage', 'Tunis'),
(4, 'Enfidha - Hammamet International Airport', 'Hammamet', 'Nabeul'),
(5, 'Djerba Zarzis International Airport', 'Zarzis', 'Djerba'),
(6, 'Monastir Habib Bourguiba International Airport', 'Monastir', 'Monastir'),
(7, 'Sfax Thyna International Airport', 'Sfax', 'Sfax');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `idC` int(11) NOT NULL AUTO_INCREMENT,
  `npassport` varchar(8) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  `tel` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `birthdate` date NOT NULL,
  PRIMARY KEY (`idC`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`idC`, `npassport`, `lastname`, `firstname`, `address`, `tel`, `email`, `birthdate`) VALUES
(13, 'RF524686', 'Ruine', 'Firas', 'Dar Chabenne, Nabeul', 24000000, 'fruine6@gmail.com', '2000-01-24'),
(14, 'RF524677', 'Yakoubi', 'Hatem', 'Gafsa', 24000000, 'hatem@gmail.com', '1988-11-18'),
(15, 'RF524677', 'Soua', 'Dhouha', 'Gafsa', 24000000, 'dhouha@gmail.com', '2001-01-19');

-- --------------------------------------------------------

--
-- Structure de la table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
  `idDep` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`idDep`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `department`
--

INSERT INTO `department` (`idDep`, `name`) VALUES
(7, 'RH'),
(8, 'Pilotage');

-- --------------------------------------------------------

--
-- Structure de la table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `idE` int(11) NOT NULL AUTO_INCREMENT,
  `lastname` varchar(30) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  `tel` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `birthdate` date NOT NULL,
  `salary` double NOT NULL,
  `idDep` int(11) NOT NULL,
  PRIMARY KEY (`idE`),
  KEY `idDep` (`idDep`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `employee`
--

INSERT INTO `employee` (`idE`, `lastname`, `firstname`, `address`, `tel`, `email`, `birthdate`, `salary`, `idDep`) VALUES
(10, 'Ahmedo', 'Ahmed', 'Nabeul', 25555333, 'ahmed@gmail.com', '1999-12-02', 2500, 7),
(11, 'Askem', 'Salim', 'Nabeul', 25555333, 'Salim@gmail.com', '1996-11-07', 1500, 7),
(12, 'Hjaiji', 'Lina', 'Nabeul', 25555333, 'Lina@gmail.com', '2000-11-16', 4500, 8),
(13, 'Mriri', 'Wassim', 'Nabeul', 25555333, 'Lina@gmail.com', '2000-04-28', 4500, 8);

-- --------------------------------------------------------

--
-- Structure de la table `flight`
--

DROP TABLE IF EXISTS `flight`;
CREATE TABLE IF NOT EXISTS `flight` (
  `idF` int(11) NOT NULL AUTO_INCREMENT,
  `datedepart` date NOT NULL,
  `datearrival` date NOT NULL,
  `tempsdepart` varchar(30) NOT NULL,
  `tempsarrival` varchar(30) NOT NULL,
  `destination` varchar(30) NOT NULL,
  `idAirport` int(11) NOT NULL,
  `idAirplane` int(11) NOT NULL,
  PRIMARY KEY (`idF`),
  KEY `idAirport` (`idAirport`,`idAirplane`),
  KEY `fk7` (`idAirplane`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `flight`
--

INSERT INTO `flight` (`idF`, `datedepart`, `datearrival`, `tempsdepart`, `tempsarrival`, `destination`, `idAirport`, `idAirplane`) VALUES
(11, '2022-12-10', '2022-12-11', '23:30', '01:30', 'Paris', 3, 3),
(12, '2022-12-10', '2022-12-11', '23:30', '01:00', 'Jerba', 4, 4),
(13, '2022-12-10', '2022-12-11', '10:30', '11:00', 'Hammamet', 5, 4);

-- --------------------------------------------------------

--
-- Structure de la table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE IF NOT EXISTS `ticket` (
  `idT` int(11) NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `idC` int(11) NOT NULL,
  `idF` int(11) NOT NULL,
  PRIMARY KEY (`idT`),
  KEY `idC` (`idC`,`idF`),
  KEY `fk2` (`idF`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ticket`
--

INSERT INTO `ticket` (`idT`, `price`, `idC`, `idF`) VALUES
(14, 600, 13, 11),
(15, 90, 15, 12),
(16, 90, 15, 13);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `idU` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`idU`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`idU`, `email`, `password`) VALUES
(1, '1', '1'),
(2, '1', '1'),
(3, '1', '1'),
(4, '2', '2'),
(5, '2', '2'),
(6, '4', '4'),
(7, '5', '5'),
(8, '6', '6'),
(9, '9', '9'),
(10, '23', '3'),
(11, '25', '25'),
(12, 't', 't'),
(13, 'm', 'm'),
(14, 'm', 'mi'),
(15, 'z', 'z'),
(16, '55', '66'),
(17, 'azaz', 'az'),
(18, '14', '14'),
(19, 'azaz', 'zaaz'),
(20, 'sq', 'sq');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `fk4` FOREIGN KEY (`idDep`) REFERENCES `department` (`idDep`);

--
-- Contraintes pour la table `flight`
--
ALTER TABLE `flight`
  ADD CONSTRAINT `fk6` FOREIGN KEY (`idAirport`) REFERENCES `airport` (`idA`),
  ADD CONSTRAINT `fk7` FOREIGN KEY (`idAirplane`) REFERENCES `airplane` (`idAirplane`);

--
-- Contraintes pour la table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`idC`) REFERENCES `client` (`idC`),
  ADD CONSTRAINT `fk2` FOREIGN KEY (`idF`) REFERENCES `flight` (`idF`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
