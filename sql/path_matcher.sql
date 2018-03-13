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

 Date: 03/13/2018 12:29:53 PM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `path_matcher`
-- ----------------------------
DROP TABLE IF EXISTS `path_matcher`;
CREATE TABLE `path_matcher` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `path` varchar(100) COLLATE utf8_bin NOT NULL,
  `authority` varchar(100) COLLATE utf8_bin NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `path_matcher`
-- ----------------------------
BEGIN;
INSERT INTO `path_matcher` VALUES ('1', '/security/**', 'ROLE_USER', '1'), ('2', '/security1/**', 'ROLE_ADMIN', '1'), ('3', '/didispace/**', 'ROLE_DIDISPACE', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
