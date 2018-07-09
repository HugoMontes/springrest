/*
Navicat MySQL Data Transfer

Source Server         : mysql_local
Source Server Version : 100113
Source Host           : localhost:3306
Source Database       : springapirest

Target Server Type    : MYSQL
Target Server Version : 100113
File Encoding         : 65001

Date: 2018-07-09 00:03:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('2');

-- ----------------------------
-- Table structure for nota
-- ----------------------------
DROP TABLE IF EXISTS `nota`;
CREATE TABLE `nota` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `titulo` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contenido` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of nota
-- ----------------------------
INSERT INTO `nota` VALUES ('1', 'Juan', 'Lorem ipsum', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse ut neque vel odio faucibus vestibulum. Donec et accumsan leo. Ut consequat porttitor nisi ut blandit. Pellentesque vestibulum nulla quis condimentum commodo. Nullam eu elit tristique, tincidunt sem in, rhoncus sapien. Aenean nec finibus eros. Integer commodo lacus vel dignissim iaculis. Mauris sem turpis, pharetra et molestie at, euismod a erat. Sed vel elit nibh.');
INSERT INTO `nota` VALUES ('3', 'Lucas', 'Donec non est augue', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse ut neque vel odio faucibus vestibulum. Donec et accumsan leo. Ut consequat porttitor nisi ut blandit. Pellentesque vestibulum nulla quis condimentum commodo. Nullam eu elit tristique, tincidunt sem in, rhoncus sapien. Aenean nec finibus eros. Integer commodo lacus vel dignissim iaculis. Mauris sem turpis, pharetra et molestie at, euismod a erat. Sed vel elit nibh.');
INSERT INTO `nota` VALUES ('4', 'Marcos', 'Donec quis dui eget nulla viverra porttitor', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse ut neque vel odio faucibus vestibulum. Donec et accumsan leo. Ut consequat porttitor nisi ut blandit. Pellentesque vestibulum nulla quis condimentum commodo. Nullam eu elit tristique, tincidunt sem in, rhoncus sapien. Aenean nec finibus eros. Integer commodo lacus vel dignissim iaculis. Mauris sem turpis, pharetra et molestie at, euismod a erat. Sed vel elit nibh.');
INSERT INTO `nota` VALUES ('5', 'Ana', 'Phasellus sed nisl at nisl ', 'Etiam varius odio quam, a tempor ante porttitor nec. Nunc luctus pulvinar nibh ac scelerisque. Sed pharetra tellus a mi mattis aliquet. Donec non est augue. Ut cursus convallis ante, convallis vulputate ex commodo non. Suspendisse tempor tempor imperdiet. Nam id ornare ligula. Nulla ullamcorper dictum rhoncus. Integer sed quam id ante pretium iaculis vitae nec lacus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi dictum, neque id commodo cursus, dui nunc convallis sem, sit amet tristique dolor sapien sit amet arcu. Etiam non massa ultricies orci vestibulum dignissim auctor sed est.');
INSERT INTO `nota` VALUES ('6', 'Maria', 'Curabitur porta', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse ut neque vel odio faucibus vestibulum. Donec et accumsan leo. Ut consequat porttitor nisi ut blandit. Pellentesque vestibulum nulla quis condimentum commodo. Nullam eu elit tristique, tincidunt sem in, rhoncus sapien. Aenean nec finibus eros. Integer commodo lacus vel dignissim iaculis. Mauris sem turpis, pharetra et molestie at, euismod a erat. Sed vel elit nibh.');
INSERT INTO `nota` VALUES ('7', 'Juan', 'Mi primera nota', 'Descripcion de mi primera nota');
INSERT INTO `nota` VALUES ('8', 'Hugo', 'Nota de Hugo', 'Descripcion nota de Hugo');
INSERT INTO `nota` VALUES ('9', 'Ivan', 'Nota de Jesus', 'Descripcion nota de Jesus');
INSERT INTO `nota` VALUES ('10', 'Gabriel', 'Nota de Gabriel', 'Descripcion nota de Gabriel');
INSERT INTO `nota` VALUES ('11', 'Jose', 'Nota de Jose', 'Descripcion nota de Jose');

-- ----------------------------
-- Table structure for usuario
-- ----------------------------
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activo` bit(1) DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rol` tinyint(4) DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_863n1y3x0jalatoir4325ehal` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of usuario
-- ----------------------------
INSERT INTO `usuario` VALUES ('1', '', '$2a$10$9phEq3yBIirHTjg8KjiuAumfbhvslRxIRqf.PbkXH3Ho5JrM6bwc.', '1', 'juan');
INSERT INTO `usuario` VALUES ('2', '\0', '$2a$10$9phEq3yBIirHTjg8KjiuAumfbhvslRxIRqf.PbkXH3Ho5JrM6bwc.', '2', 'ana');
SET FOREIGN_KEY_CHECKS=1;
