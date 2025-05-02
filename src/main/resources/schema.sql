DROP SCHEMA IF EXISTS `CS367_Project_Subject`;
CREATE SCHEMA IF NOT EXISTS `CS367_Project_Subject` DEFAULT CHARACTER SET utf8 ;
USE `CS367_Project_Subject`;

CREATE TABLE `subjects` (
  `subject_id` varchar(255) NOT NULL,
  `credit` int NOT NULL,
  `max_seats` int NOT NULL,
  `subject_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;