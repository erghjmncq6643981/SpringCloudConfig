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

 Date: 03/13/2018 12:29:44 PM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `authorities`
-- ----------------------------
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `id` int(1) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin NOT NULL,
  `authority` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  CONSTRAINT `用户名` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `authorities`
-- ----------------------------
BEGIN;
INSERT INTO `authorities` VALUES ('1', 'user', 'ROLE_USER'), ('2', 'admin', 'ROLE_ADMIN'), ('3', 'didispace', 'ROLE_DIDISPACE');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
