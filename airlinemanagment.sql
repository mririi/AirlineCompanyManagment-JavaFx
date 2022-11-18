-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 18 nov. 2022 à 19:50
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `airplane`
--

INSERT INTO `airplane` (`idAirplane`, `name`, `status`) VALUES
(1, 'hello', 1),
(2, 'a', 0);

-- --------------------------------------------------------

--
-- Structure de la table `airport`
--

DROP TABLE IF EXISTS `airport`;
CREATE TABLE IF NOT EXISTS `airport` (
  `idA` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `city` varchar(30) NOT NULL,
  `state` varchar(30) NOT NULL,
  PRIMARY KEY (`idA`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `airport`
--

INSERT INTO `airport` (`idA`, `name`, `city`, `state`) VALUES
(1, '111', '111', '11');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `idC` int(11) NOT NULL AUTO_INCREMENT,
  `lastname` varchar(30) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  `tel` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `birthdate` date NOT NULL,
  PRIMARY KEY (`idC`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`idC`, `lastname`, `firstname`, `address`, `tel`, `email`, `birthdate`) VALUES
(9, 'ddd', 'ddd', 'ddd', 555, 'ddd', '2000-10-10'),
(10, 'a', 'a', 'a', 21345678, 'a', '2000-12-12'),
(11, 'aza', 'azza', 'azaz', 111111, '11111', '2022-11-09');

-- --------------------------------------------------------

--
-- Structure de la table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
  `idDep` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`idDep`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `department`
--

INSERT INTO `department` (`idDep`, `name`) VALUES
(6, 'azzaazza');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `employee`
--

INSERT INTO `employee` (`idE`, `lastname`, `firstname`, `address`, `tel`, `email`, `birthdate`, `salary`, `idDep`) VALUES
(4, 'nnnn', 'fdsdf', 'azazaz', 2222, 'wasssda', '2000-10-20', 20, 6),
(5, '111', '111', '11', 11, '11', '2000-10-10', 11, 6),
(6, '11', '111', '11', 11, '11', '2000-10-10', 11, 6),
(7, 'az', 'za', 'za', 1, 'saz', '2022-11-02', 11, 6);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `flight`
--

INSERT INTO `flight` (`idF`, `datedepart`, `datearrival`, `tempsdepart`, `tempsarrival`, `destination`, `idAirport`, `idAirplane`) VALUES
(1, '2000-10-11', '2000-10-11', '', '', 'sm', 1, 1),
(2, '2022-11-16', '2022-11-16', '', '', 'fra', 1, 1),
(3, '2022-11-23', '2022-11-23', '', '', 'az', 1, 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ticket`
--

INSERT INTO `ticket` (`idT`, `price`, `idC`, `idF`) VALUES
(11, 22222, 9, 1);

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
