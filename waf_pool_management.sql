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

 Date: 30/12/2023 10:41:31
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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'docker镜像信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of image_info
-- ----------------------------
INSERT INTO `image_info` VALUES (1, 'sha256:41dc8ea0f13974958c4abc07b9d2ac20ac56793834c216633db012be18899f67', '2.5', 'FC4AVHVrfK', '2023-12-18 23:16:34', '2023-12-18 23:26:33');
INSERT INTO `image_info` VALUES (2, 'sha256:cf1c9961eeb6ad29607c1b409396eada508171e95b9fdd9ace3710959fd65fb6', '3.2', 'cT4qFddOFx', '2023-12-18 23:16:34', '2023-12-18 23:28:15');
INSERT INTO `image_info` VALUES (3, 'sha256:6c3c2a225947fba15a76015eb596fd1e768b0fbec7829008e57d54d35cee039c', '2.6', 'lnnh7nWkRy', '2023-12-18 23:16:34', '2023-12-18 23:28:20');
INSERT INTO `image_info` VALUES (4, 'Flores', '3.4', 'FSjcGsmu5H', '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `image_info` VALUES (5, 'Torres', '5.6', '1kHW3OF8lb', '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `image_info` VALUES (6, 'Hunter', '3.6', 're2PIuDBqd', '2023-12-18 23:16:34', '2023-12-18 23:16:34');
INSERT INTO `image_info` VALUES (8, 'nginx', 'latest', '605c77e624ddb75e6110f997c58876baa13f8754486b461117934b24a9dc3a85', '2023-12-20 15:25:18', '2023-12-20 15:25:18');

-- ----------------------------
-- Table structure for perm
-- ----------------------------
DROP TABLE IF EXISTS `perm`;
CREATE TABLE `perm`  (
  `id` tinyint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名',
  `perm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of perm
-- ----------------------------
INSERT INTO `perm` VALUES (1, '用户管理', 'user', '2023-12-29 19:40:51', '2023-12-29 19:40:51');
INSERT INTO `perm` VALUES (2, '角色管理', 'role', '2023-12-29 19:40:51', '2023-12-29 19:40:51');
INSERT INTO `perm` VALUES (3, '权限管理', 'perm', '2023-12-29 19:40:51', '2023-12-29 19:40:51');
INSERT INTO `perm` VALUES (4, 'waf管理', 'waf', '2023-12-29 19:40:51', '2023-12-29 19:40:51');

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
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请求记录表' ROW_FORMAT = DYNAMIC;

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
INSERT INTO `request_record` VALUES (11, '1fd75dd0-a4c6-42de-92aa-e8a3549de920', 'GET', '0:0:0:0:0:0:0:1', 'localhost', '/', 0, 0, 1, '2023-12-28 13:50:20', '2023-12-28 13:50:20');
INSERT INTO `request_record` VALUES (12, 'be065d6e-6496-47bd-ae21-9eca844d72d7', 'GET', '0:0:0:0:0:0:0:1', 'localhost', '/', 0, 0, 1, '2023-12-28 13:56:59', '2023-12-28 13:56:59');
INSERT INTO `request_record` VALUES (13, '3b7f25a9-e827-4a7c-9480-d0b7ba1f7fe9', 'GET', '0:0:0:0:0:0:0:1', 'localhost', '/', 0, 0, 1, '2023-12-28 14:02:39', '2023-12-28 14:02:39');
INSERT INTO `request_record` VALUES (14, 'd3d5cc1f-df41-4c69-abfb-603f44d2de69', 'GET', '0:0:0:0:0:0:0:1', 'localhost', '/', 0, 0, 1, '2023-12-28 14:04:02', '2023-12-28 14:04:02');
INSERT INTO `request_record` VALUES (15, '585419f2-f701-4c48-9101-49cb9d5e952e', 'GET', '0:0:0:0:0:0:0:1', 'localhost', '/', 0, 0, 1, '2023-12-28 14:32:18', '2023-12-28 14:32:18');
INSERT INTO `request_record` VALUES (16, 'ad5ef5e7-f5a5-4a17-9afe-55f65021f4d8', 'GET', '0:0:0:0:0:0:0:1', 'localhost', '/', 0, 0, 1, '2023-12-28 14:41:33', '2023-12-28 14:41:33');
INSERT INTO `request_record` VALUES (17, '10af3600-068e-44a8-aa5f-595649f07bd3', 'GET', '0:0:0:0:0:0:0:1', 'localhost', '/', 0, 0, 1, '2023-12-28 14:48:23', '2023-12-28 14:48:23');
INSERT INTO `request_record` VALUES (18, 'e8845eaa-7487-4749-84d5-128d94ef485e', 'GET', '0:0:0:0:0:0:0:1', 'localhost', '/', 0, 144419, 0, '2023-12-28 14:58:43', '2023-12-28 15:01:14');
INSERT INTO `request_record` VALUES (19, '56dbc17f-6d77-4a3e-a8cf-265b90903ec6', 'GET', '127.0.0.1', 'localhost', '/', 0, 201, 0, '2023-12-28 15:01:17', '2023-12-28 15:01:18');
INSERT INTO `request_record` VALUES (20, 'b5c6220b-b31e-4df6-9be0-40c53c71b067', 'GET', '127.0.0.1', 'localhost', '/', 0, 147, 0, '2023-12-28 15:01:18', '2023-12-28 15:01:18');
INSERT INTO `request_record` VALUES (21, '18df7b60-76cc-4a5d-ab01-e96e737d4d8b', 'GET', '127.0.0.1', 'localhost', '/', 0, 353, 0, '2023-12-28 15:01:18', '2023-12-28 15:01:18');
INSERT INTO `request_record` VALUES (22, '4929e1ff-142a-4d2d-9efc-44cf26e720ba', 'GET', '127.0.0.1', 'localhost', '/', 0, 85, 0, '2023-12-28 15:01:18', '2023-12-28 15:01:18');
INSERT INTO `request_record` VALUES (23, 'd03c522c-c9b4-42a0-99ad-2e772d8467e4', 'GET', '127.0.0.1', 'localhost', '/', 0, 93, 0, '2023-12-28 15:01:18', '2023-12-28 15:01:18');
INSERT INTO `request_record` VALUES (24, '6bd966fb-cd7d-4d8a-997f-1ced2f2f9e41', 'GET', '127.0.0.1', 'localhost', '/', 0, 183, 0, '2023-12-28 15:01:18', '2023-12-28 15:01:19');
INSERT INTO `request_record` VALUES (25, 'aba89bd3-03d0-49b2-a2db-9502f21e49ab', 'GET', '127.0.0.1', 'localhost', '/', 0, 89, 0, '2023-12-28 15:01:19', '2023-12-28 15:01:19');
INSERT INTO `request_record` VALUES (26, '4fd8dbf8-af56-40c2-8f0c-7ac0e4d19ffa', 'GET', '127.0.0.1', 'localhost', '/', 0, 164, 0, '2023-12-28 15:01:19', '2023-12-28 15:01:19');
INSERT INTO `request_record` VALUES (27, 'b84f696b-5dc4-4cf0-a449-8f98b69ea72f', 'GET', '127.0.0.1', 'localhost', '/', 0, 63, 0, '2023-12-28 15:01:19', '2023-12-28 15:01:19');
INSERT INTO `request_record` VALUES (28, '8228b6f5-5267-4ef7-b1e5-78b3f5527497', 'GET', '127.0.0.1', 'localhost', '/', 0, 65, 0, '2023-12-28 15:01:19', '2023-12-28 15:01:19');
INSERT INTO `request_record` VALUES (29, '828cd68f-0e09-43da-99c9-57d9dceea457', 'GET', '127.0.0.1', 'localhost', '/', 0, 854, 0, '2023-12-28 15:01:19', '2023-12-28 15:01:20');
INSERT INTO `request_record` VALUES (30, 'f19129cc-8db4-449e-8858-608b28bc7028', 'GET', '127.0.0.1', 'localhost', '/', 0, 187, 0, '2023-12-28 15:01:20', '2023-12-28 15:01:20');
INSERT INTO `request_record` VALUES (31, '91381653-1e1a-4e44-8834-1379b68d3a17', 'GET', '127.0.0.1', 'localhost', '/', 0, 81, 0, '2023-12-28 15:01:20', '2023-12-28 15:01:20');
INSERT INTO `request_record` VALUES (32, 'f130fa16-d782-4187-91c6-6ea886aa9d1f', 'GET', '127.0.0.1', 'localhost', '/', 0, 168, 0, '2023-12-28 15:01:20', '2023-12-28 15:01:20');
INSERT INTO `request_record` VALUES (33, '5e77b483-12e2-4014-85bc-4571290c3b12', 'GET', '127.0.0.1', 'localhost', '/', 0, 68, 0, '2023-12-28 15:01:20', '2023-12-28 15:01:21');
INSERT INTO `request_record` VALUES (34, '33e00cdc-4fb9-4657-bc49-7fa774050511', 'GET', '127.0.0.1', 'localhost', '/', 0, 60, 0, '2023-12-28 15:01:21', '2023-12-28 15:01:21');
INSERT INTO `request_record` VALUES (35, '3c2a423e-891b-442f-af99-1b61ed003eb0', 'GET', '127.0.0.1', 'localhost', '/', 0, 578, 0, '2023-12-28 15:01:21', '2023-12-28 15:01:21');
INSERT INTO `request_record` VALUES (36, 'dcd9e789-4dde-4e3f-adcf-1cd7f752c6b7', 'GET', '127.0.0.1', 'localhost', '/', 0, 317, 0, '2023-12-28 15:01:21', '2023-12-28 15:01:22');
INSERT INTO `request_record` VALUES (37, '356d479b-3057-4a8f-9174-ebf29ce711bf', 'GET', '127.0.0.1', 'localhost', '/', 0, 330, 0, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `request_record` VALUES (38, 'e24564f3-f900-4a03-b60d-838858198d01', 'GET', '127.0.0.1', 'localhost', '/', 0, 75, 0, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `request_record` VALUES (39, 'e3a7549d-8816-44f8-ba7b-cca7337f4cee', 'GET', '127.0.0.1', 'localhost', '/', 0, 78, 0, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `request_record` VALUES (40, 'f4e82028-d0f8-475f-ae57-c87823f53866', 'GET', '127.0.0.1', 'localhost', '/', 0, 77, 0, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `request_record` VALUES (41, '6647b246-001e-4ba4-b971-f3c7ee87f688', 'GET', '127.0.0.1', 'localhost', '/', 0, 74, 0, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `request_record` VALUES (42, '9f117c7c-1309-4872-8e5d-bc923da6d2bd', 'GET', '127.0.0.1', 'localhost', '/', 0, 70, 0, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `request_record` VALUES (43, 'e2b79d31-b498-434a-bed3-04a7084d9b6a', 'GET', '127.0.0.1', 'localhost', '/', 0, 62, 0, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `request_record` VALUES (44, '366abec0-5de3-4836-8c54-97b9e2b2c1a0', 'GET', '127.0.0.1', 'localhost', '/', 0, 77, 0, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `request_record` VALUES (45, 'c98adeec-7b49-4fc7-9e74-6d427ceaaee7', 'GET', '127.0.0.1', 'localhost', '/', 0, 76, 0, '2023-12-28 15:01:23', '2023-12-28 15:01:23');
INSERT INTO `request_record` VALUES (46, '3af89528-cec7-42c6-a0fd-9aac328fdae0', 'GET', '127.0.0.1', 'localhost', '/', 0, 77, 0, '2023-12-28 15:01:23', '2023-12-28 15:01:23');
INSERT INTO `request_record` VALUES (47, 'aea12cc4-4201-4358-abd6-fc3b08ba15e4', 'GET', '127.0.0.1', 'localhost', '/', 0, 76, 0, '2023-12-28 15:01:23', '2023-12-28 15:01:23');
INSERT INTO `request_record` VALUES (48, 'e644791d-9033-4424-a9af-38ee3901e782', 'GET', '127.0.0.1', 'localhost', '/', 0, 317, 0, '2023-12-28 15:01:23', '2023-12-28 15:01:23');
INSERT INTO `request_record` VALUES (49, '1eea4159-182a-4ee0-a403-f06e484e81f1', 'GET', '127.0.0.1', 'localhost', '/', 0, 77, 0, '2023-12-28 15:01:23', '2023-12-28 15:01:23');
INSERT INTO `request_record` VALUES (50, '3e30990c-5271-4a2b-9790-ce4a894eedf7', 'GET', '127.0.0.1', 'localhost', '/', 0, 308, 0, '2023-12-28 15:01:23', '2023-12-28 15:01:24');
INSERT INTO `request_record` VALUES (51, '44d38f8b-d75b-474c-971a-bd29681565d3', 'GET', '127.0.0.1', 'localhost', '/', 0, 554, 0, '2023-12-28 15:01:24', '2023-12-28 15:01:24');
INSERT INTO `request_record` VALUES (52, '3cd07020-96ec-4e05-a139-ada657287dbe', 'GET', '127.0.0.1', 'localhost', '/', 0, 335, 0, '2023-12-28 15:01:24', '2023-12-28 15:01:24');
INSERT INTO `request_record` VALUES (53, 'e37a55ba-fc33-4588-9d48-bb2211f8696b', 'GET', '127.0.0.1', 'localhost', '/', 0, 320, 0, '2023-12-28 15:01:24', '2023-12-28 15:01:25');
INSERT INTO `request_record` VALUES (54, 'c6049c6d-ce0c-47bc-90df-408839982da3', 'GET', '127.0.0.1', 'localhost', '/', 0, 74, 0, '2023-12-28 15:01:25', '2023-12-28 15:01:25');
INSERT INTO `request_record` VALUES (55, '8e487cac-74f9-41b1-bd0a-cbdb09adb49d', 'GET', '127.0.0.1', 'localhost', '/', 0, 72, 0, '2023-12-28 15:01:25', '2023-12-28 15:01:25');
INSERT INTO `request_record` VALUES (56, 'bfd282df-714e-4ad3-9784-da3a54f5753c', 'GET', '127.0.0.1', 'localhost', '/', 0, 45, 0, '2023-12-28 15:01:25', '2023-12-28 15:01:25');
INSERT INTO `request_record` VALUES (57, '9c9853ee-6a44-4d0e-82e6-9d2d1ce9a701', 'GET', '127.0.0.1', 'localhost', '/', 0, 48, 0, '2023-12-28 15:01:25', '2023-12-28 15:01:25');
INSERT INTO `request_record` VALUES (58, '55f03982-9e59-4a6b-9221-be2179028562', 'GET', '0:0:0:0:0:0:0:1', 'localhost', '/', 0, 0, 1, '2023-12-28 15:05:07', '2023-12-28 15:05:07');
INSERT INTO `request_record` VALUES (59, '651112b4-1c28-4ffa-9642-07618b6e8612', 'GET', '0:0:0:0:0:0:0:1', 'localhost', '/', 0, 256, 0, '2023-12-28 18:51:03', '2023-12-28 18:51:03');
INSERT INTO `request_record` VALUES (60, '075e92d1-dc28-4177-a256-a75d197168fd', 'GET', '0:0:0:0:0:0:0:1', 'localhost', '/', 0, 69, 0, '2023-12-28 18:51:36', '2023-12-28 18:51:36');
INSERT INTO `request_record` VALUES (61, '5c4f2cff-6110-4e30-8b4e-ab6522a9a831', 'GET', '0:0:0:0:0:0:0:1', 'localhost', '/', 0, 174, 0, '2023-12-28 19:09:24', '2023-12-28 19:09:24');
INSERT INTO `request_record` VALUES (62, 'ab6b2b0e-2e01-46eb-8027-0e51eefef398', 'GET', '0:0:0:0:0:0:0:1', 'localhost', '/', 0, 190481, 0, '2023-12-28 19:16:17', '2023-12-28 19:16:17');
INSERT INTO `request_record` VALUES (63, 'f932f269-ecdc-43b4-8e7e-4728b1cc7501', 'GET', '0:0:0:0:0:0:0:1', 'localhost', '/', 0, 262, 0, '2023-12-28 19:17:16', '2023-12-28 19:17:17');
INSERT INTO `request_record` VALUES (64, '12d2d89c-3e45-4fa0-b437-e1c5548ec7d4', 'GET', '0:0:0:0:0:0:0:1', 'localhost', '/', 0, 134, 0, '2023-12-28 19:18:48', '2023-12-28 19:18:48');
INSERT INTO `request_record` VALUES (65, 'bd0e92fa-37ee-413c-8c4d-db80ad33773b', 'GET', '127.0.0.1', '127.0.0.1', '/', 0, 199, 0, '2023-12-28 19:30:40', '2023-12-28 19:30:41');
INSERT INTO `request_record` VALUES (66, '710cbbe4-89f4-4250-88f8-53dc6ec2a33a', 'GET', '127.0.0.1', '127.0.0.1', '/', 0, 77, 0, '2023-12-28 19:33:31', '2023-12-28 19:33:31');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `role_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', 'admin', '2023-12-29 19:41:43', '2023-12-29 19:41:43');
INSERT INTO `role` VALUES (2, '用户', 'user', '2023-12-29 19:41:43', '2023-12-29 19:41:43');
INSERT INTO `role` VALUES (3, '超级管理员', 'super', '2023-12-29 19:41:43', '2023-12-29 19:41:43');

-- ----------------------------
-- Table structure for role_perm
-- ----------------------------
DROP TABLE IF EXISTS `role_perm`;
CREATE TABLE `role_perm`  (
  `role_id` int(11) NOT NULL,
  `perm_id` int(11) NOT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色关联权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_perm
-- ----------------------------
INSERT INTO `role_perm` VALUES (1, 1, '2023-12-29 19:42:37', '2023-12-29 19:42:37');
INSERT INTO `role_perm` VALUES (1, 4, '2023-12-29 19:42:37', '2023-12-29 19:42:37');
INSERT INTO `role_perm` VALUES (3, 1, '2023-12-29 19:42:37', '2023-12-29 19:42:37');
INSERT INTO `role_perm` VALUES (3, 2, '2023-12-29 19:42:37', '2023-12-29 19:42:37');
INSERT INTO `role_perm` VALUES (3, 3, '2023-12-29 19:42:37', '2023-12-29 19:42:37');
INSERT INTO `role_perm` VALUES (3, 4, '2023-12-29 19:42:37', '2023-12-29 19:42:37');
INSERT INTO `role_perm` VALUES (2, 1, '2023-12-29 20:58:12', '2023-12-29 20:58:12');

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
) ENGINE = InnoDB AUTO_INCREMENT = 119 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'waf的调度过程记录表' ROW_FORMAT = Dynamic;

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
INSERT INTO `schedule_record` VALUES (31, 47578, 0, 3, 18, '2023-12-28 14:59:35', '2023-12-28 14:59:35');
INSERT INTO `schedule_record` VALUES (32, 70235, 0, 1, 18, '2023-12-28 15:01:02', '2023-12-28 15:01:02');
INSERT INTO `schedule_record` VALUES (33, 148, 0, 7, 19, '2023-12-28 15:01:18', '2023-12-28 15:01:18');
INSERT INTO `schedule_record` VALUES (34, 20, 0, 1, 19, '2023-12-28 15:01:18', '2023-12-28 15:01:18');
INSERT INTO `schedule_record` VALUES (35, 33, 0, 3, 20, '2023-12-28 15:01:18', '2023-12-28 15:01:18');
INSERT INTO `schedule_record` VALUES (36, 60, 0, 1, 20, '2023-12-28 15:01:18', '2023-12-28 15:01:18');
INSERT INTO `schedule_record` VALUES (37, 32, 0, 4, 21, '2023-12-28 15:01:18', '2023-12-28 15:01:18');
INSERT INTO `schedule_record` VALUES (38, 269, 0, 2, 21, '2023-12-28 15:01:18', '2023-12-28 15:01:18');
INSERT INTO `schedule_record` VALUES (39, 29, 0, 4, 22, '2023-12-28 15:01:18', '2023-12-28 15:01:18');
INSERT INTO `schedule_record` VALUES (40, 23, 0, 7, 22, '2023-12-28 15:01:18', '2023-12-28 15:01:18');
INSERT INTO `schedule_record` VALUES (41, 28, 0, 4, 23, '2023-12-28 15:01:18', '2023-12-28 15:01:18');
INSERT INTO `schedule_record` VALUES (42, 33, 0, 1, 23, '2023-12-28 15:01:18', '2023-12-28 15:01:18');
INSERT INTO `schedule_record` VALUES (43, 52, 0, 2, 24, '2023-12-28 15:01:18', '2023-12-28 15:01:18');
INSERT INTO `schedule_record` VALUES (44, 99, 0, 1, 24, '2023-12-28 15:01:19', '2023-12-28 15:01:19');
INSERT INTO `schedule_record` VALUES (45, 28, 0, 1, 25, '2023-12-28 15:01:19', '2023-12-28 15:01:19');
INSERT INTO `schedule_record` VALUES (46, 24, 0, 3, 25, '2023-12-28 15:01:19', '2023-12-28 15:01:19');
INSERT INTO `schedule_record` VALUES (47, 35, 0, 7, 26, '2023-12-28 15:01:19', '2023-12-28 15:01:19');
INSERT INTO `schedule_record` VALUES (48, 95, 0, 4, 26, '2023-12-28 15:01:19', '2023-12-28 15:01:19');
INSERT INTO `schedule_record` VALUES (49, 22, 0, 4, 27, '2023-12-28 15:01:19', '2023-12-28 15:01:19');
INSERT INTO `schedule_record` VALUES (50, 20, 0, 2, 27, '2023-12-28 15:01:19', '2023-12-28 15:01:19');
INSERT INTO `schedule_record` VALUES (51, 25, 0, 1, 28, '2023-12-28 15:01:19', '2023-12-28 15:01:19');
INSERT INTO `schedule_record` VALUES (52, 19, 0, 2, 28, '2023-12-28 15:01:19', '2023-12-28 15:01:19');
INSERT INTO `schedule_record` VALUES (53, 277, 0, 7, 29, '2023-12-28 15:01:19', '2023-12-28 15:01:19');
INSERT INTO `schedule_record` VALUES (54, 551, 0, 3, 29, '2023-12-28 15:01:20', '2023-12-28 15:01:20');
INSERT INTO `schedule_record` VALUES (55, 29, 0, 7, 30, '2023-12-28 15:01:20', '2023-12-28 15:01:20');
INSERT INTO `schedule_record` VALUES (56, 125, 0, 3, 30, '2023-12-28 15:01:20', '2023-12-28 15:01:20');
INSERT INTO `schedule_record` VALUES (57, 27, 0, 1, 31, '2023-12-28 15:01:20', '2023-12-28 15:01:20');
INSERT INTO `schedule_record` VALUES (58, 24, 0, 3, 31, '2023-12-28 15:01:20', '2023-12-28 15:01:20');
INSERT INTO `schedule_record` VALUES (59, 27, 0, 7, 32, '2023-12-28 15:01:20', '2023-12-28 15:01:20');
INSERT INTO `schedule_record` VALUES (60, 114, 0, 1, 32, '2023-12-28 15:01:20', '2023-12-28 15:01:20');
INSERT INTO `schedule_record` VALUES (61, 22, 0, 4, 33, '2023-12-28 15:01:20', '2023-12-28 15:01:20');
INSERT INTO `schedule_record` VALUES (62, 22, 0, 2, 33, '2023-12-28 15:01:21', '2023-12-28 15:01:21');
INSERT INTO `schedule_record` VALUES (63, 22, 0, 7, 34, '2023-12-28 15:01:21', '2023-12-28 15:01:21');
INSERT INTO `schedule_record` VALUES (64, 18, 0, 2, 34, '2023-12-28 15:01:21', '2023-12-28 15:01:21');
INSERT INTO `schedule_record` VALUES (65, 539, 0, 4, 35, '2023-12-28 15:01:21', '2023-12-28 15:01:21');
INSERT INTO `schedule_record` VALUES (66, 19, 0, 3, 35, '2023-12-28 15:01:21', '2023-12-28 15:01:21');
INSERT INTO `schedule_record` VALUES (67, 276, 0, 2, 36, '2023-12-28 15:01:21', '2023-12-28 15:01:21');
INSERT INTO `schedule_record` VALUES (68, 21, 0, 1, 36, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (69, 282, 0, 4, 37, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (70, 22, 0, 3, 37, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (71, 26, 0, 4, 38, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (72, 26, 0, 3, 38, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (73, 26, 0, 1, 39, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (74, 24, 0, 3, 39, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (75, 26, 0, 7, 40, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (76, 24, 0, 1, 40, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (77, 26, 0, 7, 41, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (78, 22, 0, 3, 41, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (79, 26, 0, 1, 42, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (80, 20, 0, 2, 42, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (81, 24, 0, 3, 43, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (82, 21, 0, 2, 43, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (83, 29, 0, 3, 44, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (84, 22, 0, 2, 44, '2023-12-28 15:01:22', '2023-12-28 15:01:22');
INSERT INTO `schedule_record` VALUES (85, 27, 0, 4, 45, '2023-12-28 15:01:23', '2023-12-28 15:01:23');
INSERT INTO `schedule_record` VALUES (86, 23, 0, 1, 45, '2023-12-28 15:01:23', '2023-12-28 15:01:23');
INSERT INTO `schedule_record` VALUES (87, 28, 0, 1, 46, '2023-12-28 15:01:23', '2023-12-28 15:01:23');
INSERT INTO `schedule_record` VALUES (88, 23, 0, 7, 46, '2023-12-28 15:01:23', '2023-12-28 15:01:23');
INSERT INTO `schedule_record` VALUES (89, 27, 0, 3, 47, '2023-12-28 15:01:23', '2023-12-28 15:01:23');
INSERT INTO `schedule_record` VALUES (90, 23, 0, 4, 47, '2023-12-28 15:01:23', '2023-12-28 15:01:23');
INSERT INTO `schedule_record` VALUES (91, 25, 0, 7, 48, '2023-12-28 15:01:23', '2023-12-28 15:01:23');
INSERT INTO `schedule_record` VALUES (92, 266, 0, 3, 48, '2023-12-28 15:01:23', '2023-12-28 15:01:23');
INSERT INTO `schedule_record` VALUES (93, 30, 0, 2, 49, '2023-12-28 15:01:23', '2023-12-28 15:01:23');
INSERT INTO `schedule_record` VALUES (94, 22, 0, 4, 49, '2023-12-28 15:01:23', '2023-12-28 15:01:23');
INSERT INTO `schedule_record` VALUES (95, 267, 0, 7, 50, '2023-12-28 15:01:23', '2023-12-28 15:01:23');
INSERT INTO `schedule_record` VALUES (96, 22, 0, 1, 50, '2023-12-28 15:01:24', '2023-12-28 15:01:24');
INSERT INTO `schedule_record` VALUES (97, 27, 0, 2, 51, '2023-12-28 15:01:24', '2023-12-28 15:01:24');
INSERT INTO `schedule_record` VALUES (98, 505, 0, 3, 51, '2023-12-28 15:01:24', '2023-12-28 15:01:24');
INSERT INTO `schedule_record` VALUES (99, 292, 0, 7, 52, '2023-12-28 15:01:24', '2023-12-28 15:01:24');
INSERT INTO `schedule_record` VALUES (100, 25, 0, 1, 52, '2023-12-28 15:01:24', '2023-12-28 15:01:24');
INSERT INTO `schedule_record` VALUES (101, 27, 0, 3, 53, '2023-12-28 15:01:24', '2023-12-28 15:01:24');
INSERT INTO `schedule_record` VALUES (102, 269, 0, 7, 53, '2023-12-28 15:01:25', '2023-12-28 15:01:25');
INSERT INTO `schedule_record` VALUES (103, 27, 0, 2, 54, '2023-12-28 15:01:25', '2023-12-28 15:01:25');
INSERT INTO `schedule_record` VALUES (104, 24, 0, 1, 54, '2023-12-28 15:01:25', '2023-12-28 15:01:25');
INSERT INTO `schedule_record` VALUES (105, 30, 0, 4, 55, '2023-12-28 15:01:25', '2023-12-28 15:01:25');
INSERT INTO `schedule_record` VALUES (106, 21, 0, 1, 55, '2023-12-28 15:01:25', '2023-12-28 15:01:25');
INSERT INTO `schedule_record` VALUES (107, 15, 0, 4, 56, '2023-12-28 15:01:25', '2023-12-28 15:01:25');
INSERT INTO `schedule_record` VALUES (108, 14, 0, 7, 56, '2023-12-28 15:01:25', '2023-12-28 15:01:25');
INSERT INTO `schedule_record` VALUES (109, 15, 0, 1, 57, '2023-12-28 15:01:25', '2023-12-28 15:01:25');
INSERT INTO `schedule_record` VALUES (110, 17, 0, 7, 57, '2023-12-28 15:01:25', '2023-12-28 15:01:25');
INSERT INTO `schedule_record` VALUES (111, 160, 0, 3, 59, '2023-12-28 18:51:03', '2023-12-28 18:51:03');
INSERT INTO `schedule_record` VALUES (112, 42, 0, 2, 60, '2023-12-28 18:51:36', '2023-12-28 18:51:36');
INSERT INTO `schedule_record` VALUES (113, 138, 0, 7, 61, '2023-12-28 19:09:24', '2023-12-28 19:09:24');
INSERT INTO `schedule_record` VALUES (114, 91, 0, 3, 62, '2023-12-28 19:16:17', '2023-12-28 19:16:17');
INSERT INTO `schedule_record` VALUES (115, 184, 0, 1, 63, '2023-12-28 19:17:17', '2023-12-28 19:17:17');
INSERT INTO `schedule_record` VALUES (116, 103, 0, 2, 64, '2023-12-28 19:18:48', '2023-12-28 19:18:48');
INSERT INTO `schedule_record` VALUES (117, 112, 0, 3, 65, '2023-12-28 19:30:41', '2023-12-28 19:30:41');
INSERT INTO `schedule_record` VALUES (118, 42, 0, 7, 66, '2023-12-28 19:33:31', '2023-12-28 19:33:31');

-- ----------------------------
-- Table structure for site
-- ----------------------------
DROP TABLE IF EXISTS `site`;
CREATE TABLE `site`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `domain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '域名',
  `server` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '防护的服务器',
  `mode` tinyint(1) NULL DEFAULT NULL COMMENT '调度器模式（1：串行，0：并行）',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '防护站点' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of site
-- ----------------------------
INSERT INTO `site` VALUES (1, '*', 'http://SmpPHZWM6j', 1, 'NDuC0DmBPl', '2023-12-27 22:10:04', '2023-12-28 12:22:22');
INSERT INTO `site` VALUES (2, '*', 'http://CMg6RZ8OVf', 1, '2jTkzBUxy9', '2023-12-27 22:10:04', '2023-12-28 12:22:28');
INSERT INTO `site` VALUES (3, '*', 'http://iTP3cQs8fv', 1, 'TVrpUZXY9H', '2023-12-27 22:10:04', '2023-12-28 12:22:29');
INSERT INTO `site` VALUES (4, '*', 'http://kO2At1K3bV', 1, '6He0nHgg8E', '2023-12-27 22:10:04', '2023-12-28 12:22:30');
INSERT INTO `site` VALUES (5, '*', 'http://c033S9txQX', 1, 'gmMr20Xv47', '2023-12-27 22:10:04', '2023-12-28 12:22:30');
INSERT INTO `site` VALUES (6, '*', 'http://x5mJgxAAs8', 1, 'ZLkWHOGYfP', '2023-12-27 22:10:04', '2023-12-28 12:22:31');
INSERT INTO `site` VALUES (7, '*', 'http://VqN2uP0XW1', 1, 'fAZzUGyCbj', '2023-12-27 22:10:04', '2023-12-28 12:22:31');
INSERT INTO `site` VALUES (8, '*', 'http://QXFerFgIoE', 1, '1Cp4cT3Sgx', '2023-12-27 22:10:04', '2023-12-28 12:22:32');
INSERT INTO `site` VALUES (9, '*', 'http://SWD4exspii', 1, 'U8ecC3L1c5', '2023-12-27 22:10:04', '2023-12-28 12:22:32');
INSERT INTO `site` VALUES (10, '*', 'http://u2ivJL0cyV', 1, 'l4bBFFu2wi', '2023-12-27 22:10:04', '2023-12-28 13:33:14');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '电子邮件',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（0：启用，1：禁用）',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_pk`(`username`) USING BTREE,
  UNIQUE INDEX `user_pk2`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', '$2a$10$NKpX2Q3A8VEu8y8F7L1LyO/e9KhnK9hMUSGdCDDLRfl5CqBwP8WDC', 'zls2434474199@163.com', 0, '2023-12-29 19:43:47', '2023-12-29 19:43:47');
INSERT INTO `user` VALUES (5, 'lisi', '$2a$10$wS5jon3pqiPeNSYg9WoUrerCzH08mXvkMyLvtuxStdRn.5GiBxziu', 'sdfasfsa@qq.com', 0, '2023-12-29 19:43:47', '2023-12-29 19:43:47');
INSERT INTO `user` VALUES (8, 'lili', '$2a$10$TxXl84/T8cKdxcTzS2QD7.4s.aAaehgLhbvTugV8Uz2zgGPnGjtTe', 'shdfksf', 1, '2023-12-29 19:43:47', '2023-12-29 19:43:47');
INSERT INTO `user` VALUES (16, '老六', '$2a$10$bPEfoz80Wx9ADWVxruj3cujFi/klsWKjgvcG64nVRJTQC0nIj5zK6', 'f.xbnxipdq@qq.com', 1, '2023-12-29 21:45:07', '2023-12-29 22:01:56');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `role_id` int(11) NOT NULL DEFAULT 0 COMMENT '角色id',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户关联角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 3, '2023-12-29 19:44:24', '2023-12-29 19:44:24');
INSERT INTO `user_role` VALUES (5, 1, '2023-12-29 19:44:24', '2023-12-29 19:44:24');
INSERT INTO `user_role` VALUES (8, 2, '2023-12-29 19:44:24', '2023-12-29 19:44:24');
INSERT INTO `user_role` VALUES (15, 2, '2023-12-29 20:57:26', '2023-12-29 20:57:26');

-- ----------------------------
-- Table structure for waf_info
-- ----------------------------
DROP TABLE IF EXISTS `waf_info`;
CREATE TABLE `waf_info`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'waf名称',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'ip地址',
  `port` int(10) NULL DEFAULT 0 COMMENT '端口',
  `config_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'waf的配置地址',
  `is_online` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否上线',
  `weight` tinyint(4) NULL DEFAULT NULL COMMENT '权重，用于调度器按权重选取waf',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '描述信息',
  `image_id` bigint(20) NULL DEFAULT NULL COMMENT 'docker形式的waf的镜像id',
  `container_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'docker形式的waf的容器id',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'waf信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of waf_info
-- ----------------------------
INSERT INTO `waf_info` VALUES (1, 'Melissa', '101.43.29.82', 80, 'http://101.43.29.82:9443', 1, 2, 'PU7uFIZSTm', 8, '3hGDQe0Ens', '2023-12-18 23:16:34', '2023-12-28 14:19:56');
INSERT INTO `waf_info` VALUES (2, 'Rosa', '101.43.29.82', 80, 'http://101.43.29.82:9443', 1, 1, 'DdePZrDhxL', 8, 'fflWoHeZJH', '2023-12-18 23:16:34', '2023-12-28 14:19:56');
INSERT INTO `waf_info` VALUES (3, 'Arthur', '101.43.29.82', 80, 'http://101.43.29.82:9443', 1, 0, 'ydk71rRrvf', 8, 'dvYPusW3q4', '2023-12-18 23:16:34', '2023-12-28 14:19:57');
INSERT INTO `waf_info` VALUES (4, 'Wanda', '101.43.29.82', 80, 'http://101.43.29.82:9443', 1, 3, 'j2eQ73xfaL', 8, '7r5gPwg2KL', '2023-12-18 23:16:34', '2023-12-28 14:19:57');
INSERT INTO `waf_info` VALUES (5, 'Jacqueline', '101.43.29.82', 80, 'http://101.43.29.82:9443', 0, 2, 'JkhJMlN2yU', 8, 'HOIcyT1ABl', '2023-12-18 23:16:34', '2023-12-28 14:19:58');
INSERT INTO `waf_info` VALUES (6, 'Marcus', '101.43.29.82', 80, 'http://101.43.29.82:9443', 0, 1, 'wKmCTkxJPl', 8, 'zvqglD6xXf', '2023-12-18 23:16:34', '2023-12-28 14:19:58');
INSERT INTO `waf_info` VALUES (7, 'nginx', '101.43.29.82', 80, 'http://101.43.29.82:9443', 1, 0, '', 8, 'e4045387609ece98a98aef0ca4c88092cb934103ffbb71e593ac4fd70584973b', '2023-12-21 16:10:28', '2023-12-28 14:19:59');

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
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'waf_监控信息表' ROW_FORMAT = Dynamic;

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
INSERT INTO `waf_monitor` VALUES (101, 0.00, 0.08, 7, '2023-12-21 16:31:04', '2023-12-21 16:31:04');
INSERT INTO `waf_monitor` VALUES (102, 0.00, 0.08, 7, '2023-12-21 16:31:08', '2023-12-21 16:31:08');
INSERT INTO `waf_monitor` VALUES (103, 0.00, 0.08, 7, '2023-12-21 16:31:13', '2023-12-21 16:31:13');
INSERT INTO `waf_monitor` VALUES (104, 0.00, 0.08, 7, '2023-12-21 16:31:18', '2023-12-21 16:31:18');
INSERT INTO `waf_monitor` VALUES (105, 0.00, 0.08, 7, '2023-12-21 16:31:23', '2023-12-21 16:31:23');
INSERT INTO `waf_monitor` VALUES (106, 0.00, 0.08, 7, '2023-12-21 16:31:28', '2023-12-21 16:31:28');
INSERT INTO `waf_monitor` VALUES (107, 0.00, 0.08, 7, '2023-12-21 16:31:33', '2023-12-21 16:31:33');
INSERT INTO `waf_monitor` VALUES (108, 0.00, 0.08, 7, '2023-12-21 16:31:38', '2023-12-21 16:31:38');

SET FOREIGN_KEY_CHECKS = 1;
