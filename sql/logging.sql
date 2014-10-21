/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : muta

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2014-02-15 13:48:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `logging`
-- ----------------------------
DROP TABLE IF EXISTS `logging`;
CREATE TABLE `logging` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `log_date` datetime DEFAULT NULL,
  `log_level` varchar(64) DEFAULT NULL,
  `location` varchar(256) DEFAULT NULL,
  `message` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logging
-- ----------------------------
INSERT INTO `logging` VALUES ('1', '2014-02-15 01:37:17', 'INFO', 'com.log4j.test.Test', 'test logger info');
INSERT INTO `logging` VALUES ('2', '2014-02-15 01:37:18', 'ERROR', 'com.log4j.test.Test', 'test logger error');
INSERT INTO `logging` VALUES ('3', '2014-02-15 01:37:18', 'FATAL', 'com.log4j.test.Test', 'test logger fatal');
INSERT INTO `logging` VALUES ('4', '2014-02-15 01:38:18', 'INFO', 'com.log4j.test.Test', 'test logger info');
INSERT INTO `logging` VALUES ('5', '2014-02-15 01:38:18', 'ERROR', 'com.log4j.test.Test', 'test logger error');
INSERT INTO `logging` VALUES ('6', '2014-02-15 01:38:18', 'FATAL', 'com.log4j.test.Test', 'test logger fatal');
