DROP SCHEMA IF EXISTS `MijuPrice`;
CREATE SCHEMA `MijuPrice`;

USE `MijuPrice`;

CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(1000) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `finishing` (
  `pdrate` double NOT NULL,
  `priceweightflag` varchar(10) NOT NULL,
  `postdisposaletypeename` varchar(500) NOT NULL,
  `price` double NOT NULL,
  `fin_id` int NOT NULL,
  PRIMARY KEY (`fin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `mats` (
  `mtrename` varchar(500) NOT NULL,
  `mtrtype` varchar(5) NOT NULL,
  `countgu` double NOT NULL,
  `countzhi` double NOT NULL,
  `prprice` double NOT NULL,
  `colorprice` double NOT NULL,
  `drprice` double NOT NULL,
  `whiteprice` double NOT NULL,
  `dyeprice` double NOT NULL,
  `whiteloss` double NOT NULL,
  `warploss` double NOT NULL,
  `colorloss` double NOT NULL,
  `mtrid` varchar(50) NOT NULL,
  `material_id` int NOT NULL,
  PRIMARY KEY (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `quotes` (
  `create_date` varchar(50) NOT NULL,
  `model_name` varchar(500) NOT NULL,
  `fabric_type` varchar(500) NOT NULL,
  `finish_module` varchar(500) NOT NULL,
  `memo` varchar(2000) DEFAULT NULL,
  `quote_id` int NOT NULL AUTO_INCREMENT,
  `ppcm` double NOT NULL,
  `total_warp` double NOT NULL,
  `weight` double NOT NULL,
  `sale_price` double NOT NULL,
  PRIMARY KEY (`quote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `materials` (
  `quote_id` int NOT NULL,
  `mat_id` int NOT NULL AUTO_INCREMENT,
  `mat_name` varchar(500) NOT NULL,
  `ww_type` varchar(50) NOT NULL,
  `color_type` varchar(50) NOT NULL,
  `use_percent` double NOT NULL,
  `mat_cost` double NOT NULL,
  `mat_memo` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`mat_id`),
  KEY `quote_id_idx` (`quote_id`),
  CONSTRAINT `quote_id` FOREIGN KEY (`quote_id`) REFERENCES `quotes` (`quote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;