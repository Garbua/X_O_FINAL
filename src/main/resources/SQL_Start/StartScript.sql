DROP DATABASE IF EXISTS xo_final;

CREATE DATABASE IF NOT EXISTS xo_final;

USE xo_final;


CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `pass` varchar(30) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `game_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  CONSTRAINT `g_id` FOREIGN KEY (`id`) REFERENCES `games` (`id_game`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `games` (
  `id_game` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `0` varchar(45) DEFAULT NULL,
  `1` varchar(45) DEFAULT NULL,
  `2` varchar(45) DEFAULT NULL,
  `3` varchar(45) DEFAULT NULL,
  `4` varchar(45) DEFAULT NULL,
  `5` varchar(45) DEFAULT NULL,
  `6` varchar(45) DEFAULT NULL,
  `7` varchar(45) DEFAULT NULL,
  `8` varchar(45) DEFAULT NULL,
  `winner` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_game`),
  KEY `game_user_idx` (`user_id`),
  CONSTRAINT `game_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





