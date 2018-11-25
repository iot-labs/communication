-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.3.8-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- lect 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `lect` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `lect`;

-- 테이블 lect.auth 구조 내보내기
CREATE TABLE IF NOT EXISTS `auth` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `salt` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `facebook_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `kakao_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 lect.node_list 구조 내보내기
CREATE TABLE IF NOT EXISTS `node_list` (
  `owner_id` int(11) DEFAULT NULL,
  `node_id` int(11) NOT NULL AUTO_INCREMENT,
  `node_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `node_location` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 lect.topic_table 구조 내보내기
CREATE TABLE IF NOT EXISTS `topic_table` (
  `owner_id` int(11) DEFAULT NULL,
  `id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `content` text COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 내보낼 데이터가 선택되어 있지 않습니다.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
