/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : haixun

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2017-05-28 17:12:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL auto_increment,
  `uname` varchar(255) collate utf8_unicode_ci default NULL,
  `unumber` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'aa', '123456');
