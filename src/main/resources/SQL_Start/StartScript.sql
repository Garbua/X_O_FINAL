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
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `games` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `winner` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `game_user_idx` (`winner`),
  CONSTRAINT `game_user` FOREIGN KEY (`winner`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `signs` (
  `id_user` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `sign` varchar(45) NOT NULL,
  KEY `user_user_idx` (`id_user`),
  KEY `game_games_idx` (`id_game`),
  CONSTRAINT `game_games` FOREIGN KEY (`id_game`) REFERENCES `games` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `move` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game` int(11) NOT NULL,
  `pole` varchar(45) NOT NULL,
  `move` varchar(45) NOT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `game_games_idx` (`game`),
  KEY `user_users_idx` (`user`),
  CONSTRAINT `g_games` FOREIGN KEY (`game`) REFERENCES `games` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_users` FOREIGN KEY (`user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




