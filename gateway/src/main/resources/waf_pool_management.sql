/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50741
 Source Host           : localhost:3306
 Source Schema         : waf_pool_management

 Target Server Type    : MySQL
 Target Server Version : 50741
 File Encoding         : 65001

 Date: 18/12/2023 23:35:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for image_info
-- ----------------------------
DROP TABLE IF EXISTS `image_info`;
CREATE TABLE `image_info`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '镜像名',
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '镜像的TAG',
  `image_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '镜像id',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'docker镜像信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of image_info
-- ----------------------------
INSERT INTO `image_info` VALUES (1, 'sha256:41dc8ea0f13974958c4abc07b9d2ac20ac56793834c216633db012be18899f67', '2.5', 'FC4AVHVrfK', '2023-12-18 23:16:34', '2023-12-18 23:26:33');
INSERT INTO `image_info` VALUES (2, 'sha256:cf1c9961eeb6ad29607c1b409396eada508171e95b9fdd9ace3710959fd65fb6', '3.2', 'cT4qFddOFx', '2023-12-18 23:16:34', '2023-12-18 23:28:15');
INSERT INTO `image_info` VALUES (3, 'sha256:6c3c2a225947fba15a76015eb596fd1e768b0fbec7829008e57d54d35cee039c', '2.6', 'lnnh7nWkRy', '2023-12-18 23:16:34', '2023-12-18 23:28:20');
INSERT INTO `image_info` VALUES (4, 'Flores', '3.4', 'FSjcGsmu5H', '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `image_info` VALUES (5, 'Torres', '5.6', '1kHW3OF8lb', '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `image_info` VALUES (6, 'Hunter', '3.6', 're2PIuDBqd', '2023-12-18 23:16:34', '2023-12-18 23:16:34');

-- ----------------------------
-- Table structure for perm
-- ----------------------------
DROP TABLE IF EXISTS `perm`;
CREATE TABLE `perm`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名',
  `perm` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of perm
-- ----------------------------
INSERT INTO `perm` VALUES (1, '用户管理', 'user');
INSERT INTO `perm` VALUES (2, '角色管理', 'role');
INSERT INTO `perm` VALUES (3, '权限管理', 'perm');
INSERT INTO `perm` VALUES (4, 'waf管理', 'waf');

-- ----------------------------
-- Table structure for request_record
-- ----------------------------
DROP TABLE IF EXISTS `request_record`;
CREATE TABLE `request_record`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求的唯一标识',
  `method` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `src_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源ip',
  `des_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目的ip',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `mode` tinyint(1) NULL DEFAULT NULL COMMENT '请求的调度模式（1：串行，0：并行）',
  `time` bigint(11) NULL DEFAULT NULL COMMENT '请求处理时间',
  `is_pass` tinyint(1) NULL DEFAULT NULL COMMENT '是否放行',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请求记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of request_record
-- ----------------------------
INSERT INTO `request_record` VALUES (1, '74bcbd10-bc87-b54f-6fe6-7b3704b51ded', 'GET', '0.17.253.149', '218.180.39.255', 'https://video.xia1230.us/Food', 0, 669, 0, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `request_record` VALUES (2, '225306ae-5065-bd67-54e9-8586d643300e', 'GET', '38.252.86.40', '181.194.100.251', 'https://video.siuwaipa84.cn/ToysGames', 0, 1610, 1, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `request_record` VALUES (3, '0d739684-920e-a3f4-a7c9-1c6da3a7196c', 'POST', '40.176.223.66', '17.234.160.81', 'https://video.renom.jp/FilmSupplies', 1, 103, 0, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `request_record` VALUES (4, 'cd68db8c-e353-ebe3-03b2-9c4210d4f129', 'GET', '1.120.63.105', '235.112.206.204', 'http://www.nakagriku9.cn/ComputersElectronics', 1, 1759, 0, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `request_record` VALUES (5, '8ed54409-1bb4-6fa2-6b49-ab7cd2da979c', 'DELETE', '6.233.251.148', '180.255.6.22', 'https://video.tangcm.jp/ToolsHomeDecoration', 1, 1233, 1, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `request_record` VALUES (6, '812ebfc8-28e7-12bc-aca2-0b778179b290', 'UPDATE', '252.11.127.175', '200.4.92.69', 'http://www.wschiang.us/Books', 0, 1318, 0, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `request_record` VALUES (7, 'd8533251-0068-af9e-a89c-70b5372fa8f5', 'UPDATE', '3.12.141.129', '253.255.250.9', 'https://drive.mmurakami2.info/PetSupplies', 1, 1526, 1, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `request_record` VALUES (8, '40ffebcb-35a7-f6f9-e101-2907117a9d2f', 'DELETE', '232.7.251.33', '212.109.1.3', 'https://www.yt424.xyz/Baby', 0, 958, 1, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `request_record` VALUES (9, '56a92e81-fe30-b2d2-a693-f5d91b0a809b', 'UPDATE', '187.227.251.196', '201.7.50.255', 'https://drive.kojima1123.net/Appliances', 0, 57, 1, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `request_record` VALUES (10, 'c96c2c32-4568-9387-7296-ee9149003b31', 'POST', '3.13.2.229', '1.132.109.244', 'https://www.zitaowei1119.com/Appliances', 0, 335, 1, '2023-12-18 23:16:34', '2023-12-18 23:16:34');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', 'admin');
INSERT INTO `role` VALUES (2, '用户', 'user');
INSERT INTO `role` VALUES (3, '超级管理员', 'super');

-- ----------------------------
-- Table structure for role_perm
-- ----------------------------
DROP TABLE IF EXISTS `role_perm`;
CREATE TABLE `role_perm`  (
  `role_id` int(11) NOT NULL,
  `perm_id` int(11) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色关联权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_perm
-- ----------------------------
INSERT INTO `role_perm` VALUES (1, 1);
INSERT INTO `role_perm` VALUES (1, 4);
INSERT INTO `role_perm` VALUES (3, 1);
INSERT INTO `role_perm` VALUES (3, 2);
INSERT INTO `role_perm` VALUES (3, 3);
INSERT INTO `role_perm` VALUES (3, 4);

-- ----------------------------
-- Table structure for schedule_record
-- ----------------------------
DROP TABLE IF EXISTS `schedule_record`;
CREATE TABLE `schedule_record`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `time` bigint(20) NULL DEFAULT NULL COMMENT 'waf处理时间',
  `is_pass` tinyint(4) NULL DEFAULT NULL COMMENT '是否放行',
  `waf_id` bigint(20) NULL DEFAULT NULL,
  `request_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'waf的调度过程记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_record
-- ----------------------------
INSERT INTO `schedule_record` VALUES (1, 403, 1, 6, 5, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (2, 280, 0, 2, 6, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (3, 303, 1, 3, 10, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (4, 171, 1, 2, 6, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (5, 848, 0, 4, 2, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (6, 462, 0, 5, 6, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (7, 40, 1, 4, 10, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (8, 548, 1, 3, 1, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (9, 143, 0, 3, 8, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (10, 46, 0, 2, 4, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (11, 259, 1, 5, 8, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (12, 744, 0, 4, 6, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (13, 929, 0, 4, 1, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (14, 297, 1, 4, 2, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (15, 183, 0, 2, 8, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (16, 802, 0, 5, 1, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (17, 986, 0, 3, 5, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (18, 327, 1, 3, 5, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (19, 70, 1, 5, 7, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (20, 745, 0, 3, 9, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (21, 145, 1, 3, 1, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (22, 104, 1, 5, 3, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (23, 11, 0, 4, 8, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (24, 115, 1, 2, 10, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (25, 92, 0, 2, 3, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (26, 83, 0, 6, 5, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (27, 74, 1, 2, 3, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (28, 197, 1, 5, 6, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (29, 553, 1, 2, 6, '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `schedule_record` VALUES (30, 88, 1, 3, 2, '2023-12-18 23:16:34', '2023-12-18 23:16:34');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '电子邮件',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态（0：启用，1：禁用）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_pk`(`username`) USING BTREE,
  UNIQUE INDEX `user_pk2`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', '$2a$10$NKpX2Q3A8VEu8y8F7L1LyO/e9KhnK9hMUSGdCDDLRfl5CqBwP8WDC', 'zls2434474199@163.com', '0');
INSERT INTO `user` VALUES (5, 'lisi', '$2a$10$wS5jon3pqiPeNSYg9WoUrerCzH08mXvkMyLvtuxStdRn.5GiBxziu', 'sdfasfsa@qq.com', '0');
INSERT INTO `user` VALUES (8, 'lili', '$2a$10$TxXl84/T8cKdxcTzS2QD7.4s.aAaehgLhbvTugV8Uz2zgGPnGjtTe', 'shdfksf', '1');
INSERT INTO `user` VALUES (15, '袁磊', '1', 'f.xbnxipdq@qq.com', '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `role_id` int(11) NOT NULL DEFAULT 0 COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户关联角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 3);
INSERT INTO `user_role` VALUES (5, 1);
INSERT INTO `user_role` VALUES (8, 2);

-- ----------------------------
-- Table structure for waf_info
-- ----------------------------
DROP TABLE IF EXISTS `waf_info`;
CREATE TABLE `waf_info`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `image_id` bigint(20) NULL DEFAULT NULL COMMENT 'docker形式的waf的镜像id',
  `container_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'docker形式的waf的容器id',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'waf名称',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'ip地址',
  `port` int(10) NULL DEFAULT 0 COMMENT '端口',
  `config_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'waf的配置地址',
  `is_online` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否上线',
  `weight` tinyint(4) NULL DEFAULT NULL COMMENT '权重，用于调度器按权重选取waf',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '描述信息',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'waf信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of waf_info
-- ----------------------------
INSERT INTO `waf_info` VALUES (1, 1, '3hGDQe0Ens', 'Melissa', '107.228.252.254', 9193, 'http://www.zhiyufeng7.jp/Handcrafts', 1, 2, 'PU7uFIZSTm', '2023-12-18 23:16:34', '2023-12-18 23:35:16');
INSERT INTO `waf_info` VALUES (2, 2, 'fflWoHeZJH', 'Rosa', '238.114.255.254', 32283, 'https://www.lok41.net/CenturionGardenOutdoor', 0, 1, 'DdePZrDhxL', '2023-12-18 23:16:34', '2023-12-18 23:35:19');
INSERT INTO `waf_info` VALUES (3, 3, 'dvYPusW3q4', 'Arthur', '250.24.224.90', 33482, 'https://drive.alberc.co.jp/ArtsHandicraftsSewing', 1, 0, 'ydk71rRrvf', '2023-12-18 23:16:34', '2023-12-18 23:35:21');
INSERT INTO `waf_info` VALUES (4, 1, '7r5gPwg2KL', 'Wanda', '7.254.111.164', 43608, 'https://auth.rha1104.biz/Others', 0, 3, 'j2eQ73xfaL', '2023-12-18 23:16:34', '2023-12-18 23:35:23');
INSERT INTO `waf_info` VALUES (5, 2, 'HOIcyT1ABl', 'Jacqueline', '4.141.218.46', 17911, 'https://www.cyho.org/ArtsHandicraftsSewing', 0, 2, 'JkhJMlN2yU', '2023-12-18 23:16:34', '2023-12-18 23:35:25');
INSERT INTO `waf_info` VALUES (6, 3, 'zvqglD6xXf', 'Marcus', '251.34.227.93', 1016, 'http://auth.moi.cn/BaggageTravelEquipment', 0, 1, 'wKmCTkxJPl', '2023-12-18 23:16:34', '2023-12-18 23:35:27');

-- ----------------------------
-- Table structure for waf_monitor
-- ----------------------------
DROP TABLE IF EXISTS `waf_monitor`;
CREATE TABLE `waf_monitor`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cpu` decimal(5, 2) NULL DEFAULT NULL COMMENT 'cpu占用率',
  `memory` decimal(5, 2) NULL DEFAULT NULL COMMENT '内存占用率',
  `waf_id` bigint(20) UNSIGNED NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'waf_监控信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of waf_monitor
-- ----------------------------
INSERT INTO `waf_monitor` VALUES (1, 94.46, 83.96, 2, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (2, 40.51, 34.28, 1, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (3, 31.10, 72.54, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (4, 7.24, 47.58, 2, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (5, 96.12, 56.64, 6, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (6, 37.55, 45.11, 2, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (7, 39.12, 95.50, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (8, 90.20, 35.43, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (9, 75.54, 78.58, 1, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (10, 75.10, 82.07, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (11, 98.12, 15.32, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (12, 49.05, 27.74, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (13, 71.89, 9.87, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (14, 46.24, 36.31, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (15, 12.25, 36.54, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (16, 40.20, 34.34, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (17, 21.77, 49.30, 1, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (18, 62.97, 90.11, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (19, 17.41, 16.11, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (20, 30.24, 57.86, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (21, 79.89, 60.57, 2, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (22, 6.16, 23.66, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (23, 36.24, 94.58, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (24, 98.68, 90.27, 1, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (25, 1.12, 38.55, 2, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (26, 26.77, 79.68, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (27, 72.18, 98.15, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (28, 24.41, 33.12, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (29, 87.75, 19.28, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (30, 90.31, 0.70, 6, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (31, 20.15, 63.45, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (32, 13.60, 95.15, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (33, 66.28, 42.79, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (34, 86.89, 28.24, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (35, 32.05, 52.49, 2, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (36, 3.29, 39.78, 1, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (37, 85.89, 37.79, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (38, 26.44, 16.58, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (39, 4.32, 21.48, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (40, 18.45, 59.08, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (41, 67.22, 74.94, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (42, 64.40, 71.00, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (43, 11.52, 99.50, 1, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (44, 22.58, 48.65, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (45, 76.85, 55.14, 1, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (46, 97.15, 30.37, 6, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (47, 42.83, 92.39, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (48, 16.85, 23.99, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (49, 19.77, 9.62, 1, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (50, 56.94, 11.34, 2, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (51, 80.59, 39.72, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (52, 99.30, 58.34, 2, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (53, 62.87, 6.57, 2, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (54, 31.21, 96.61, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (55, 99.39, 49.00, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (56, 14.98, 26.78, 2, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (57, 91.04, 77.79, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (58, 17.32, 24.11, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (59, 66.64, 85.00, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (60, 7.61, 80.48, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (61, 50.15, 97.80, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (62, 65.10, 36.71, 1, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (63, 89.88, 93.47, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (64, 91.60, 6.42, 2, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (65, 98.39, 43.69, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (66, 13.46, 93.95, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (67, 52.39, 75.11, 2, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (68, 25.90, 10.10, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (69, 44.32, 30.95, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (70, 12.49, 91.55, 6, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (71, 20.48, 21.94, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (72, 63.06, 48.47, 1, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (73, 76.28, 54.11, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (74, 98.49, 96.99, 2, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (75, 93.14, 78.18, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (76, 2.83, 77.70, 6, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (77, 70.28, 20.56, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (78, 75.46, 73.89, 6, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (79, 29.20, 93.53, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (80, 28.69, 94.04, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (81, 71.95, 13.04, 2, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (82, 64.39, 53.88, 6, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (83, 72.21, 85.96, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (84, 32.02, 95.81, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (85, 35.19, 15.26, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (86, 70.38, 75.41, 2, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (87, 55.44, 84.37, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (88, 13.09, 87.74, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (89, 65.14, 65.46, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (90, 38.47, 44.56, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (91, 30.93, 97.22, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (92, 63.13, 47.56, 1, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (93, 98.08, 34.20, 3, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (94, 35.48, 53.27, 1, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (95, 65.68, 12.15, 2, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (96, 60.13, 72.66, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (97, 72.67, 88.12, 1, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (98, 54.46, 25.98, 4, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (99, 42.94, 24.42, 5, '2023-12-18 23:19:50', '2023-12-18 23:19:50');
INSERT INTO `waf_monitor` VALUES (100, 11.20, 88.32, 1, '2023-12-18 23:19:50', '2023-12-18 23:19:50');

SET FOREIGN_KEY_CHECKS = 1;
