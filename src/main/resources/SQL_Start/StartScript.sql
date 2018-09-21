DROP DATABASE IF EXISTS xo_final;

CREATE DATABASE IF NOT EXISTS xo_final;

USE xo_final;

CREATE TABLE xo_final.profile
(
  profile_id  INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name   VARCHAR(45),
  lastName    VARCHAR(45),
  email       VARCHAR(45) UNIQUE ,
  age         INT(45) DEFAULT NULL,
  sex        VARCHAR(45),
  city        VARCHAR(45),
  phoneNumber VARCHAR(45)
);

CREATE TABLE xo_final.users
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  email VARCHAR(65) NOT NULL UNIQUE,
  login VARCHAR(20) NOT NULL UNIQUE,
  pass VARCHAR(12) NOT NULL,
  profile_id INT NOT NULL,
  FOREIGN KEY (profile_id) REFERENCES profile (profile_id) ON UPDATE CASCADE ON DELETE CASCADE
);





