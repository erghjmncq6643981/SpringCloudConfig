/*
 Navicat MySQL Data Transfer

 Source Server         : myRepository
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : security

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 03/13/2018 12:30:02 PM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin NOT NULL,
  `password` varchar(500) COLLATE utf8_bin NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`,`username`),
  UNIQUE KEY `username_3` (`username`),
  KEY `username` (`username`),
  KEY `username_2` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `users`
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('1', 'user', 'password', '1'), ('2', 'admin', 'password', '1'), ('3', 'didispace', '37cc5635-559b-4e6f-b633-7e932b813f73', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
