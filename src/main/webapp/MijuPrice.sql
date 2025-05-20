DROP SCHEMA IF EXISTS `MijuPrice`;
CREATE SCHEMA `MijuPrice`;

USE `MijuPrice`;

CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
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

CREATE TABLE `development` (
  `development_id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `color` varchar(50),
  `cost` double DEFAULT 0.0,
  `IsParagonClean` BOOLEAN DEFAULT false,
  `Is400hrFCL` BOOLEAN DEFAULT false,
  `IsPieceDyed` BOOLEAN DEFAULT false,
  `NeedFeedback` BOOLEAN DEFAULT false,
  `IsSDY` BOOLEAN DEFAULT false,
  `IsChenille` BOOLEAN DEFAULT false,
  `fabric_type` varchar(50),
  `design_type` varchar(50),
  `colorist` varchar(50),
  `finishing_used` varchar(50),
  `season` varchar(50),
  `yarn_type` varchar(50),
  `warp_type` varchar(50),
  `content` varchar(50),
  `strike_off_status` varchar(50),
  `blanket_status` varchar(500),
  `colorline_status` varchar(50),
  `colorline_datestamp` varchar(50),
  `rollsample_status` varchar(50),
  `rollsample_datestamp` varchar(50),
  `test_status` varchar(50),
  `test_datestamp` varchar(50),
  `moq` double DEFAULT 0.0,
  `weight` double DEFAULT 0.0,
  `numColorline` int DEFAULT 0,
  `ppcm` double DEFAULT 0.0,
  `note` varchar(1000),
  `fabric_img_path` varchar(500),
  `pid_path` varchar(500),
  `test_report_path` varchar(500),
  `currentPhase` varchar(50),
  `DateTime` varchar(50) NOT NULL,
  `LastModified` varchar(50) NOT NULL,
  `DateCurrentPhase` varchar(50) NOT NULL,
  `IsKnit` BOOLEAN DEFAULT false,
  `designer` varchar(50),
  `direction` varchar(50),
  `GeorgeCanceled` BOOLEAN DEFAULT false,
  `strike_off_birthday` varchar(50),
  PRIMARY KEY (`development_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `comment` (
  `development_id` int NOT NULL,
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `date_stamp` varchar(50) NOT NULL,
  `content` varchar(1000) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `development_id_idx` (`development_id`),
  FOREIGN KEY (`development_id`) REFERENCES `development` (`development_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `log` (
  `development_id` int NOT NULL,
  `log_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `date_stamp` varchar(50) NOT NULL,
  `content` varchar(1000) NOT NULL,
  PRIMARY KEY (`log_id`),
  KEY `development_id_idx` (`development_id`),
  FOREIGN KEY (`development_id`) REFERENCES `development` (`development_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;