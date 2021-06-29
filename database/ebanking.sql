-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 29 juin 2021 à 00:40
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ebanking`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `agence`
--

CREATE TABLE `agence` (
  `id_agence` bigint(20) NOT NULL,
  `adresse_agence` varchar(255) DEFAULT NULL,
  `email_agence` varchar(255) DEFAULT NULL,
  `nom_agence` varchar(255) DEFAULT NULL,
  `telephone_agence` varchar(255) DEFAULT NULL,
  `creation_admin_agence` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `agence`
--

INSERT INTO `agence` (`id_agence`, `adresse_agence`, `email_agence`, `nom_agence`, `telephone_agence`, `creation_admin_agence`) VALUES
(1, 'Marrakech', 'agenceMarra@gmail.com', 'AgenceMarra', '083762672', NULL),
(2, 'marrakech', 'agence12@gmail.com', 'agence marrakech', '0678654545', 1),
(3, 'casablanca', 'agencecasa@gmail.com', 'agence casablanca', '0656453476', 1),
(4, 'fes', 'agencyfes19@gmail.com', 'agence fes', '+212589876545', 1);

-- --------------------------------------------------------

--
-- Structure de la table `agent`
--

CREATE TABLE `agent` (
  `id` bigint(20) NOT NULL,
  `agence_agent` bigint(20) DEFAULT NULL,
  `creation_admin_agent` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `agent`
--

INSERT INTO `agent` (`id`, `agence_agent`, `creation_admin_agent`) VALUES
(143, 1, 1),
(155, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `est_operateur_client` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `agence_client` bigint(20) DEFAULT NULL,
  `creation_agent_client` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`est_operateur_client`, `id`, `agence_client`, `creation_agent_client`, `status`) VALUES
(NULL, 3, 1, 143, 'Valide'),
(NULL, 148, 1, 143, 'Valide'),
(NULL, 149, 1, 143, 'Valide'),
(NULL, 150, 1, 143, 'Valide');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `id_compte` bigint(20) NOT NULL,
  `creation_date_compte` datetime(6) DEFAULT NULL,
  `devise_compte` varchar(255) DEFAULT NULL,
  `numero_compte` varchar(255) DEFAULT NULL,
  `solde_compte` double DEFAULT NULL,
  `type_compte` varchar(255) DEFAULT NULL,
  `creation_agent_compte` bigint(20) DEFAULT NULL,
  `proprietaire_compte` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`id_compte`, `creation_date_compte`, `devise_compte`, `numero_compte`, `solde_compte`, `type_compte`, `creation_agent_compte`, `proprietaire_compte`) VALUES
(2, '2021-05-30 14:26:35.000000', 'DH', '7729499490', 4314.04, 'VISA', 143, 3),
(6, '2021-06-19 16:13:09.000000', 'DH', '201', 9121.6, 'VISA', 143, 148),
(7, '2021-06-19 16:24:05.000000', 'DH', '617', 398.79999999999995, 'MasterCard', 143, 148),
(10, '2021-06-22 18:18:22.000000', 'DH', '783', 100, 'VISA', 143, 149),
(11, '2021-06-22 19:11:57.000000', 'DH', '241', 990, 'VISA', 143, 150);

-- --------------------------------------------------------

--
-- Structure de la table `devise`
--

CREATE TABLE `devise` (
  `id_devise` bigint(20) NOT NULL,
  `alpha_code_devise` varchar(255) DEFAULT NULL,
  `bank_code_devise` varchar(255) DEFAULT NULL,
  `code_devise` varchar(255) DEFAULT NULL,
  `creation_date_devise` datetime(6) DEFAULT NULL,
  `iso_code_devise` varchar(255) DEFAULT NULL,
  `langue_devise` varchar(255) DEFAULT NULL,
  `modification_date_devise` datetime(6) DEFAULT NULL,
  `nom_devise` varchar(255) DEFAULT NULL,
  `pays_code_devise` varchar(255) DEFAULT NULL,
  `creation_admin_devise` bigint(20) DEFAULT NULL,
  `modification_admin_devise` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `operateur`
--

CREATE TABLE `operateur` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE `operation` (
  `id_operation` bigint(20) NOT NULL,
  `date_operation` datetime(6) DEFAULT NULL,
  `devise_operation` varchar(255) DEFAULT NULL,
  `somme_compte_operation` double DEFAULT NULL,
  `somme_espece_operation` double DEFAULT NULL,
  `type_operation` varchar(255) DEFAULT NULL,
  `compte_operation` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `operation`
--

INSERT INTO `operation` (`id_operation`, `date_operation`, `devise_operation`, `somme_compte_operation`, `somme_espece_operation`, `type_operation`, `compte_operation`) VALUES
(4, '2021-06-19 19:32:34.000000', 'DH', 0, 780, 'retrait', 2),
(8, '2021-06-19 19:43:33.000000', 'DH', 5, 1000, 'versement', 2),
(9, '2021-06-19 19:44:45.000000', 'DH', 5, 1000, 'retrait', 2),
(10, '2021-06-23 11:29:21.000000', 'DH', 5, 10000, 'versement', 11),
(11, '2021-06-23 11:29:31.000000', 'DH', 5, 995, 'retrait', 11),
(12, '2021-06-23 11:29:42.000000', 'DH', 5, 9000, 'retrait', 11);

-- --------------------------------------------------------

--
-- Structure de la table `rendez_vous`
--

CREATE TABLE `rendez_vous` (
  `id` bigint(20) NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `for_date` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `rendezvous_agent` bigint(20) DEFAULT NULL,
  `rendezvous_client` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `cin` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `adresse`, `cin`, `email`, `nom`, `password`, `prenom`, `role`, `telephone`, `username`) VALUES
(1, 'fes', 'EE11563', 'admin@gmail.com', 'admin', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'admin', 'Admin', '06878787', 'admin'),
(3, 'casablanca', 'E098765', 'amine1942@gmail.com', 'BOUGUESSA', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'AMINE', 'Client', '072092009', 'amine'),
(143, 'Marrakech', 'EE298654', 'agent@gmail.com', 'Agent', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'Agent', 'Agent', '0678787654', 'agent'),
(148, 'Marrakech', 'EE111111', 'saidelmoussaoui19@gmail.com', 'EL MOUSSAOUI', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'SAID', 'Client', '0600000000', 'said'),
(149, '1234 ,MARRAKECH', 'EE234657', 'mocef12@gmail.com', 'ECHAIB', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'MONCEF', 'Client', '0612345678', 'moncef'),
(150, 'casablanca', 'EE11111123', 'hamza@gmail.com', 'GHARAFI', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855', 'HAMZA', 'Client', '0678465464', 'hamza'),
(155, NULL, 'EE1111111', 'agent@gmail.com', 'agent2', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'agent2', 'Agent', '0689898989', 'agent2');

-- --------------------------------------------------------

--
-- Structure de la table `virement`
--

CREATE TABLE `virement` (
  `id_virement` bigint(20) NOT NULL,
  `date_virement` datetime(6) DEFAULT NULL,
  `somme_env_virement` double DEFAULT NULL,
  `somme_recu_virement` double DEFAULT NULL,
  `creancier_virement` bigint(20) DEFAULT NULL,
  `debiteur_virement` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `virement`
--

INSERT INTO `virement` (`id_virement`, `date_virement`, `somme_env_virement`, `somme_recu_virement`, `creancier_virement`, `debiteur_virement`) VALUES
(37, '2021-06-22 13:06:09.000000', 32, 31.36, 7, 2),
(38, '2021-06-22 13:06:09.000000', 100, 98, 6, 2),
(39, '2021-06-22 13:07:36.000000', 100, 98, 6, 2),
(40, '2021-06-22 13:07:36.000000', 120, 117.6, 7, 2),
(41, '2021-06-22 20:30:37.000000', 6, 5.88, 7, 2),
(42, '2021-06-23 11:59:01.000000', 100, 98, 6, 2),
(43, '2021-06-23 11:59:36.000000', 100, 98, 7, 2),
(44, '2021-06-23 12:00:51.000000', 109, 106.82, 7, 2),
(45, '2021-06-23 12:00:51.000000', 103, 100.94, 7, 2),
(46, '2021-06-23 12:07:38.000000', 123, 120.54, 6, 2),
(47, '2021-06-23 12:08:02.000000', 120, 117.6, 6, 2),
(48, '2021-06-23 12:08:45.000000', 129, 126.42, 6, 2),
(49, '2021-06-23 12:08:45.000000', 100, 98, 6, 7),
(50, '2021-06-23 12:09:16.000000', 102, 99.96, 6, 2),
(51, '2021-06-23 12:09:16.000000', 122, 119.56, 6, 2),
(52, '2021-06-23 12:11:51.000000', 109, 106.82, 7, 2),
(53, '2021-06-23 12:11:51.000000', 10, 9.8, 7, 7),
(54, '2021-06-23 12:11:51.000000', 120, 117.6, 7, 6);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `agence`
--
ALTER TABLE `agence`
  ADD PRIMARY KEY (`id_agence`),
  ADD UNIQUE KEY `UK_fok72pesq7mv2wgfj6d8co6va` (`nom_agence`),
  ADD UNIQUE KEY `UK_qis7yadpxvk7ipwy5xx3kvbc4` (`telephone_agence`),
  ADD KEY `FK9p9s7tyhinawh66m6xxxkusbv` (`creation_admin_agence`);

--
-- Index pour la table `agent`
--
ALTER TABLE `agent`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9vb5uoc9ydv9l5uibls95gofq` (`agence_agent`),
  ADD KEY `FK7nfid8tnmlovcmxyixivowgok` (`creation_admin_agent`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKffhxhi0svo09jynreeh558im9` (`agence_client`),
  ADD KEY `FK3olu6yn8ow9w7nv3pp5yub4n6` (`creation_agent_client`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`id_compte`),
  ADD UNIQUE KEY `UK_hx2we7gc1i1c5ylusignv31f3` (`numero_compte`),
  ADD KEY `FKyg8viwuq54yanf42nl7erwgw` (`creation_agent_compte`),
  ADD KEY `FKv0peuejqne4hime4hjbdsaon` (`proprietaire_compte`);

--
-- Index pour la table `devise`
--
ALTER TABLE `devise`
  ADD PRIMARY KEY (`id_devise`),
  ADD UNIQUE KEY `UK_fmkthia8gxtcjwppyqv8om5ap` (`code_devise`),
  ADD KEY `FKqm6v94y1f0vy3nqqp01namdwq` (`creation_admin_devise`),
  ADD KEY `FK2lmrw6gygbmxo3w7n16upwp17` (`modification_admin_devise`);

--
-- Index pour la table `operateur`
--
ALTER TABLE `operateur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `operation`
--
ALTER TABLE `operation`
  ADD PRIMARY KEY (`id_operation`),
  ADD KEY `FK18c9p187hjinfy9dc0ti8hhr0` (`compte_operation`);

--
-- Index pour la table `rendez_vous`
--
ALTER TABLE `rendez_vous`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgunp6p7cqagcs3l8um6vouxja` (`rendezvous_agent`),
  ADD KEY `FKhmcrdh0nvgf1d7yyf06arvljd` (`rendezvous_client`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_kq7nt5wyq9v9lpcpgxag2f24a` (`username`);

--
-- Index pour la table `virement`
--
ALTER TABLE `virement`
  ADD PRIMARY KEY (`id_virement`),
  ADD KEY `FKii81m9a51q2tqate5icp9as3w` (`creancier_virement`),
  ADD KEY `FKov0teenvrnbw16orck7wsw47w` (`debiteur_virement`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `agence`
--
ALTER TABLE `agence`
  MODIFY `id_agence` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `id_compte` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `devise`
--
ALTER TABLE `devise`
  MODIFY `id_devise` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `operation`
--
ALTER TABLE `operation`
  MODIFY `id_operation` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=159;

--
-- AUTO_INCREMENT pour la table `virement`
--
ALTER TABLE `virement`
  MODIFY `id_virement` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `FKgodqjbbtwk30kf3s0xuxklkr3` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `agence`
--
ALTER TABLE `agence`
  ADD CONSTRAINT `FK9p9s7tyhinawh66m6xxxkusbv` FOREIGN KEY (`creation_admin_agence`) REFERENCES `admin` (`id`);

--
-- Contraintes pour la table `agent`
--
ALTER TABLE `agent`
  ADD CONSTRAINT `FK7nfid8tnmlovcmxyixivowgok` FOREIGN KEY (`creation_admin_agent`) REFERENCES `admin` (`id`),
  ADD CONSTRAINT `FK9vb5uoc9ydv9l5uibls95gofq` FOREIGN KEY (`agence_agent`) REFERENCES `agence` (`id_agence`),
  ADD CONSTRAINT `FKoqghuuphfog6kj5cwvmy9movn` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK3olu6yn8ow9w7nv3pp5yub4n6` FOREIGN KEY (`creation_agent_client`) REFERENCES `agent` (`id`),
  ADD CONSTRAINT `FKffhxhi0svo09jynreeh558im9` FOREIGN KEY (`agence_client`) REFERENCES `agence` (`id_agence`),
  ADD CONSTRAINT `FKod74ye6k4t6qnirp5a5a8bkm9` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `FKv0peuejqne4hime4hjbdsaon` FOREIGN KEY (`proprietaire_compte`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FKyg8viwuq54yanf42nl7erwgw` FOREIGN KEY (`creation_agent_compte`) REFERENCES `agent` (`id`);

--
-- Contraintes pour la table `devise`
--
ALTER TABLE `devise`
  ADD CONSTRAINT `FK2lmrw6gygbmxo3w7n16upwp17` FOREIGN KEY (`modification_admin_devise`) REFERENCES `admin` (`id`),
  ADD CONSTRAINT `FKqm6v94y1f0vy3nqqp01namdwq` FOREIGN KEY (`creation_admin_devise`) REFERENCES `admin` (`id`);

--
-- Contraintes pour la table `operateur`
--
ALTER TABLE `operateur`
  ADD CONSTRAINT `FK2frdos9s6unrk71rpchjly22n` FOREIGN KEY (`id`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `FK18c9p187hjinfy9dc0ti8hhr0` FOREIGN KEY (`compte_operation`) REFERENCES `compte` (`id_compte`);

--
-- Contraintes pour la table `rendez_vous`
--
ALTER TABLE `rendez_vous`
  ADD CONSTRAINT `FKgunp6p7cqagcs3l8um6vouxja` FOREIGN KEY (`rendezvous_agent`) REFERENCES `agent` (`id`),
  ADD CONSTRAINT `FKhmcrdh0nvgf1d7yyf06arvljd` FOREIGN KEY (`rendezvous_client`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `virement`
--
ALTER TABLE `virement`
  ADD CONSTRAINT `FKii81m9a51q2tqate5icp9as3w` FOREIGN KEY (`creancier_virement`) REFERENCES `compte` (`id_compte`),
  ADD CONSTRAINT `FKov0teenvrnbw16orck7wsw47w` FOREIGN KEY (`debiteur_virement`) REFERENCES `compte` (`id_compte`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
