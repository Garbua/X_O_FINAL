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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `games` (
  `id_game` int(11) NOT NULL AUTO_INCREMENT,
  `winner` int(11) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id_game`),
  KEY `game_user_idx` (`winner`),
  CONSTRAINT `game_user` FOREIGN KEY (`winner`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `move` (
  `id_move` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(11) NOT NULL,
  `pole` varchar(45) NOT NULL,
  `move` varchar(45) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id_move`),
  KEY `game_games_idx` (`game_id`),
  KEY `user_users_idx` (`user_id`),
  CONSTRAINT `g_games` FOREIGN KEY (`game_id`) REFERENCES `games` (`id_game`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `user_games` (
  `user_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `sign` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`,`game_id`),
  KEY `user_user_idx` (`user_id`),
  KEY `game_games_idx` (`game_id`),
  CONSTRAINT `game_games` FOREIGN KEY (`game_id`) REFERENCES `games` (`id_game`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




