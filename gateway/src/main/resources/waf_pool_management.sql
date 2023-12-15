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

 Date: 27/11/2023 21:40:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
-- Table structure for request
-- ----------------------------
DROP TABLE IF EXISTS `request`;
CREATE TABLE `request`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '请求的唯一标识',
  `method` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'GET' COMMENT '请求方法',
  `source_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '源IP',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/' COMMENT '请求路径',
  `mode` tinyint(1) NOT NULL DEFAULT 1 COMMENT '请求模式（1：串行，0：并行）',
  `start_time` datetime NOT NULL COMMENT '请求时间',
  `time` int(11) NOT NULL DEFAULT 0 COMMENT '请求时间',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '请求状态（0：成功，1：失败）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请求记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of request
-- ----------------------------
INSERT INTO `request` VALUES (1, '3f656abe-f537-f50e-08bb-5c5c7af4a57c', 'GET', '89.47.252.22', 'http://video.tamsw8.biz/IndustrialScientificSupplies', 1, '2023-11-04 20:45:46', 7803, '0');
INSERT INTO `request` VALUES (2, '8ec03057-0ff8-9906-0184-e70b6c3d68c8', 'DELETE', '6.7.1.0', 'http://drive.sato8.co.jp/Books', 0, '2023-11-02 06:45:53', 4623, '1');
INSERT INTO `request` VALUES (3, 'a3a22dbe-2ed1-8bd4-ddc8-d3bfd3a8cebd', 'DELETE', '7.38.253.78', 'http://auth.jonathanw.xyz/CellPhonesAccessories', 1, '2023-11-03 08:44:47', 5861, '0');
INSERT INTO `request` VALUES (4, 'e328f2a9-f5ac-19ff-287f-9c73cd442029', 'PATCH', '69.255.254.219', 'http://drive.syng.info/ToolsHomeDecoration', 0, '2023-11-01 17:29:09', 7167, '0');
INSERT INTO `request` VALUES (5, 'f7dab3cb-a715-893b-d501-b5db2d975c6e', 'GET', '250.181.55.119', 'https://image.shizita83.cn/ToolsHomeDecoration', 0, '2023-11-04 16:46:52', 2349, '1');
INSERT INTO `request` VALUES (6, 'dd53b0f3-e5a4-9e67-a79e-17dc12c1a3c1', 'GET', '59.7.140.206', 'https://www.renakanek.com/CellPhonesAccessories', 0, '2023-11-01 06:49:40', 8764, '0');
INSERT INTO `request` VALUES (7, '5e7e80e3-da66-52b4-268f-3ac6b633aa59', 'PUT', '163.7.242.2', 'https://image.yky.biz/VideoGames', 0, '2023-11-04 03:19:07', 5566, '1');
INSERT INTO `request` VALUES (8, '3cc1ac9f-632a-12f9-e4ae-19a9ef06e95e', 'GET', '144.5.7.254', 'https://www.dezhiyuan.info/ToolsHomeDecoration', 1, '2023-11-04 23:49:00', 5851, '0');
INSERT INTO `request` VALUES (9, 'dfd12478-8ecc-8833-0d40-b07e5fff8756', 'POST', '113.154.250.203', 'https://auth.yungkafai.us/PetSupplies', 0, '2023-11-04 22:25:47', 3460, '1');
INSERT INTO `request` VALUES (10, '8328a26d-a3ce-450b-6362-b3cfa92d0ff6', 'GET', '252.1.158.159', 'http://video.chancl.jp/Handcrafts', 1, '2023-11-03 15:09:34', 647, '0');
INSERT INTO `request` VALUES (11, '0c2906ad-cc27-b568-37c6-a31ce588fbb3', 'DELETE', '251.255.9.250', 'http://www.josrog7.xyz/PetSupplies', 0, '2023-11-03 07:59:22', 5004, '1');
INSERT INTO `request` VALUES (12, 'c855a479-6117-4e7c-78b2-f30343eaa718', 'PUT', '65.0.230.255', 'http://auth.takwch.biz/HouseholdKitchenAppliances', 1, '2023-11-01 05:48:02', 3277, '0');
INSERT INTO `request` VALUES (13, 'b83fa3cc-f78f-127f-914a-77b59a3b9fe0', 'PUT', '21.186.6.5', 'https://video.kasharada.net/Baby', 0, '2023-11-03 14:58:52', 7972, '0');
INSERT INTO `request` VALUES (14, '814a0213-472a-d585-3ed6-91f8534bd327', 'PATCH', '252.4.255.255', 'https://image.twtsang9.org/Beauty', 1, '2023-11-04 22:43:58', 2126, '0');
INSERT INTO `request` VALUES (15, '50af187a-9e5e-1626-3d79-ab82a291f5da', 'POST', '246.250.10.243', 'http://image.wantw01.xyz/CellPhonesAccessories', 1, '2023-11-03 18:18:13', 8396, '1');
INSERT INTO `request` VALUES (16, '6859ec84-ca48-4978-086b-050b7686966e', 'DELETE', '252.9.7.144', 'https://www.jackson3.org/VideoGames', 0, '2023-11-04 23:12:24', 4609, '1');
INSERT INTO `request` VALUES (17, '7fe38b69-dd18-3728-273b-41f74f9499d0', 'PUT', '172.71.185.253', 'http://www.jianz.cn/Baby', 0, '2023-11-04 09:52:41', 5887, '1');
INSERT INTO `request` VALUES (18, '3bc26e52-601c-ba3d-96ba-24874da2ec75', 'PUT', '240.5.0.175', 'https://video.yuzhe810.jp/SportsOutdoor', 1, '2023-11-04 10:19:51', 1836, '0');
INSERT INTO `request` VALUES (19, '622e2c7c-bbdc-0e9c-8853-39e0e33a8186', 'POST', '178.251.254.101', 'http://drive.lopeza.net/Food', 0, '2023-11-01 01:05:38', 68, '0');
INSERT INTO `request` VALUES (20, '355c7eb0-9cc8-77ec-a013-f280f4f3b2c7', 'PUT', '104.165.233.199', 'http://drive.herrerae.biz/ComputersElectronics', 1, '2023-11-04 12:34:00', 45, '1');
INSERT INTO `request` VALUES (21, '3690b898-6045-0623-018d-40992e8e8dab', 'PUT', '224.1.255.251', 'https://auth.itsuks.jp/AppsGames', 1, '2023-11-04 02:31:40', 4198, '1');
INSERT INTO `request` VALUES (22, '09f2fdab-3f38-8345-eb86-4975fb9fbda4', 'DELETE', '228.255.124.22', 'http://auth.huimemi.jp/Appliances', 0, '2023-11-04 15:47:41', 5819, '1');
INSERT INTO `request` VALUES (23, '2104fd40-38e0-4e98-bbf6-2e194320b76d', 'PATCH', '245.240.253.68', 'http://auth.myech.org/Baby', 1, '2023-11-04 14:01:54', 3728, '0');
INSERT INTO `request` VALUES (24, '0c6025d1-ab3f-8250-fae3-134617264174', 'DELETE', '209.251.6.235', 'http://www.gcon.jp/HouseholdKitchenAppliances', 1, '2023-11-01 01:56:05', 2358, '1');
INSERT INTO `request` VALUES (25, '51b4cd9a-9076-98ed-b240-2f1f71c98788', 'PATCH', '20.253.255.12', 'https://auth.barryhawki.biz/ToolsHomeDecoration', 1, '2023-11-04 05:22:34', 3363, '1');
INSERT INTO `request` VALUES (26, 'e668feb4-c3f7-b22e-f643-613b0f55c59f', 'PATCH', '251.22.7.41', 'http://drive.yamashazuki55.co.jp/Books', 0, '2023-11-03 16:25:39', 1413, '0');
INSERT INTO `request` VALUES (27, '7c230e8f-84ca-21ef-22ca-5bedbcd2038c', 'GET', '225.153.170.1', 'https://drive.anqil1019.biz/CenturionGardenOutdoor', 0, '2023-11-03 19:39:08', 862, '0');
INSERT INTO `request` VALUES (28, 'bca565cd-e369-b2c6-c0ad-d026e73c3ed7', 'DELETE', '150.227.251.133', 'http://video.hows.org/PetSupplies', 1, '2023-11-03 01:40:43', 9385, '0');
INSERT INTO `request` VALUES (29, '970e406c-0f0b-ee49-d5ad-c26d122820cc', 'POST', '77.7.177.253', 'http://auth.onna3.net/ClothingShoesandJewelry', 1, '2023-11-04 03:57:42', 7383, '0');
INSERT INTO `request` VALUES (30, '7c6f7b81-eaf5-27ef-3d8a-04bc7f467b65', 'PUT', '251.67.4.230', 'http://auth.huaziyi.cn/Appliances', 1, '2023-11-01 13:49:05', 8491, '0');
INSERT INTO `request` VALUES (31, '6c43612d-b92a-1769-1c49-abe5be2955b8', 'PATCH', '126.206.252.75', 'http://www.heung5.co.jp/ToysGames', 1, '2023-11-01 11:29:02', 9395, '1');
INSERT INTO `request` VALUES (32, '471819fb-48cc-1486-e3b9-f765c269d49b', 'PUT', '22.151.40.255', 'http://video.cho312.jp/BaggageTravelEquipment', 0, '2023-11-04 14:52:38', 7612, '0');
INSERT INTO `request` VALUES (33, 'e3a138f0-44cd-39da-3f28-7874feb9e94f', 'PUT', '112.0.201.205', 'http://auth.wlu1.com/IndustrialScientificSupplies', 0, '2023-11-01 04:24:16', 137, '0');
INSERT INTO `request` VALUES (34, 'b7034d21-2e8a-ccb2-7479-e4748ebca0ba', 'POST', '49.53.5.7', 'https://drive.probinson.xyz/MusicalInstrument', 1, '2023-11-04 20:41:28', 7298, '1');
INSERT INTO `request` VALUES (35, '5a5d1848-3816-a17f-f72a-8c8aa97f1d99', 'POST', '6.207.199.24', 'https://image.robertsonv.info/SportsOutdoor', 1, '2023-11-02 19:13:52', 6615, '1');
INSERT INTO `request` VALUES (36, '7fec4287-73fc-08d9-8d1a-74320b861494', 'DELETE', '3.34.157.0', 'https://video.chm.biz/BaggageTravelEquipment', 0, '2023-11-01 09:56:29', 6158, '1');
INSERT INTO `request` VALUES (37, 'ce3d649f-2c02-87af-7b08-26b2efce0d37', 'POST', '254.43.176.123', 'http://auth.zhennan5.xyz/HealthBabyCare', 1, '2023-11-04 13:27:52', 4893, '1');
INSERT INTO `request` VALUES (38, 'b9a91936-0c9e-cbcc-27ac-6bdd7fc3b514', 'DELETE', '255.7.193.98', 'https://drive.noguchiairi.net/HealthBabyCare', 0, '2023-11-02 18:37:28', 709, '0');
INSERT INTO `request` VALUES (39, 'a3cdb7ba-9585-b12d-750d-419583d339a9', 'PUT', '252.182.96.200', 'http://auth.yeowyuling97.com/Handcrafts', 1, '2023-11-04 07:47:22', 8589, '0');
INSERT INTO `request` VALUES (40, '85c50221-8cc8-6c68-4ae1-2d238939a507', 'PATCH', '183.167.101.216', 'https://video.harredw211.us/HouseholdKitchenAppliances', 0, '2023-11-02 02:25:23', 4094, '1');
INSERT INTO `request` VALUES (41, 'eac026ca-d2ae-c389-5a4c-c4128835e2e3', 'GET', '5.13.204.4', 'https://www.ksw.org/Baby', 1, '2023-11-03 22:10:35', 690, '1');
INSERT INTO `request` VALUES (42, '5604cbec-4e1b-879d-18b8-cd66e2c3be7b', 'GET', '6.27.37.134', 'http://image.wmt.org/ToysGames', 1, '2023-11-04 11:54:34', 3953, '1');
INSERT INTO `request` VALUES (43, 'c3716d0f-aec5-d6f8-be7c-cf55d90dd6a6', 'DELETE', '5.250.31.221', 'http://drive.stephglor.us/HealthBabyCare', 0, '2023-11-02 04:03:59', 7677, '0');
INSERT INTO `request` VALUES (44, '4e12178d-8f12-8178-0c2d-7528066e3f84', 'DELETE', '243.0.238.204', 'http://video.smc.co.jp/AppsGames', 0, '2023-11-03 16:09:19', 7675, '0');
INSERT INTO `request` VALUES (45, 'e07083e6-16eb-0b90-5f54-8216400ad25e', 'GET', '110.250.100.5', 'http://www.wongyf52.org/CenturionGardenOutdoor', 0, '2023-11-03 15:07:45', 8317, '0');
INSERT INTO `request` VALUES (46, '465c43bf-9692-90ae-b670-653dd2e3c3f0', 'POST', '245.140.238.4', 'http://drive.eugea.net/CollectiblesFineArt', 0, '2023-11-02 15:43:56', 1400, '1');
INSERT INTO `request` VALUES (47, 'bdf8fce9-5c6b-4975-a2df-0fac71a4b529', 'PATCH', '206.45.9.181', 'https://image.lu3.biz/Others', 1, '2023-11-02 22:06:01', 4360, '1');
INSERT INTO `request` VALUES (48, 'f5f0cf64-6415-a22d-fc31-fc9c9b707b41', 'DELETE', '0.60.8.8', 'https://video.lam2020.net/Food', 1, '2023-11-03 03:39:39', 4607, '1');
INSERT INTO `request` VALUES (49, '2016e555-3e81-7957-b630-c8e612dd59c6', 'PATCH', '2.238.63.232', 'https://drive.rtorres.info/FilmSupplies', 1, '2023-11-04 01:54:11', 7303, '0');
INSERT INTO `request` VALUES (50, '2a9d670e-7c87-4558-9207-b32c6b09f82f', 'DELETE', '230.251.9.250', 'http://www.swta.net/BaggageTravelEquipment', 1, '2023-11-03 06:10:03', 5973, '0');
INSERT INTO `request` VALUES (51, 'aba60334-a522-152b-0829-70e536ea10d2', 'PATCH', '47.2.6.207', 'http://video.haikki1230.co.jp/SportsOutdoor', 0, '2023-11-03 09:55:49', 2539, '1');
INSERT INTO `request` VALUES (52, '4e7a609d-4b73-8cae-6131-5df65e409a48', 'PATCH', '171.251.3.6', 'https://auth.fong6.biz/Baby', 1, '2023-11-01 12:12:56', 7766, '1');
INSERT INTO `request` VALUES (53, '301be743-5ec4-6853-bde9-e5de0af5f62e', 'GET', '196.2.245.89', 'http://www.tikk1.info/PetSupplies', 0, '2023-11-01 16:20:46', 6810, '0');
INSERT INTO `request` VALUES (54, '32dc3a84-be67-0efa-07e8-f074634e7925', 'PUT', '64.140.254.24', 'http://auth.fumin.info/CollectiblesFineArt', 0, '2023-11-04 05:11:11', 2397, '1');
INSERT INTO `request` VALUES (55, 'fb3db159-4c63-075f-e990-6d5991eef50a', 'DELETE', '141.254.185.252', 'http://auth.lulu406.us/MusicalInstrument', 1, '2023-11-02 03:39:13', 6880, '1');
INSERT INTO `request` VALUES (56, '762c4307-0756-5ab2-52e7-70ba7fd4d3fc', 'PATCH', '45.7.55.250', 'http://video.xiuying2.cn/Books', 1, '2023-11-01 01:16:58', 3834, '0');
INSERT INTO `request` VALUES (57, 'be211d1a-cb0f-06c5-65b0-7350ea9e4578', 'PATCH', '228.91.38.159', 'https://auth.ab11.xyz/CDsVinyl', 0, '2023-11-04 03:51:59', 4504, '1');
INSERT INTO `request` VALUES (58, 'c92cebca-e282-cae4-39f1-68f4b0602119', 'PATCH', '178.169.228.105', 'https://video.ayatin4.net/ClothingShoesandJewelry', 1, '2023-11-01 02:35:18', 6716, '0');
INSERT INTO `request` VALUES (59, '859349a9-ace7-7d85-67e9-f5d2ad355c65', 'POST', '52.241.46.207', 'http://auth.huisaiwing.info/Handcrafts', 1, '2023-11-04 03:26:58', 373, '1');
INSERT INTO `request` VALUES (60, '33b10491-961e-a895-6627-75e8d5842fc9', 'GET', '105.23.254.22', 'https://video.lji.com/MusicalInstrument', 1, '2023-11-01 12:03:09', 3081, '0');
INSERT INTO `request` VALUES (61, 'e6118176-6f8c-7e9c-92d3-20b7531800d9', 'PUT', '143.253.212.0', 'http://www.rui9.xyz/CellPhonesAccessories', 1, '2023-11-02 16:57:09', 1747, '0');
INSERT INTO `request` VALUES (62, '9d0ee401-83c3-581f-da50-9f10fea2a9a3', 'POST', '176.251.7.254', 'https://video.wangz811.co.jp/ToolsHomeDecoration', 0, '2023-11-03 04:06:23', 1338, '0');
INSERT INTO `request` VALUES (63, 'efdcbcf4-326a-bea6-570b-c8e7358fb742', 'POST', '190.130.6.148', 'https://auth.chengwingsze.org/Handcrafts', 0, '2023-11-03 21:12:40', 4615, '0');
INSERT INTO `request` VALUES (64, 'efd9b4b5-f35a-8848-8a30-cc6e11bf4cd2', 'GET', '254.10.7.43', 'http://image.chanz3.xyz/SportsOutdoor', 0, '2023-11-04 00:12:22', 9226, '0');
INSERT INTO `request` VALUES (65, '05465a0e-8521-3aef-0ba2-0af56450ad1d', 'PUT', '250.237.237.69', 'https://drive.hxiaoming.net/CellPhonesAccessories', 0, '2023-11-03 14:15:25', 2660, '0');
INSERT INTO `request` VALUES (66, 'd51f8d61-5741-d402-7d73-27c19d0569d4', 'POST', '112.252.236.9', 'http://image.lia9.cn/FilmSupplies', 0, '2023-11-02 08:48:29', 7180, '0');
INSERT INTO `request` VALUES (67, '5b103d6e-35b5-bc8f-2694-25b9d3d2a150', 'POST', '8.251.73.232', 'https://drive.tanghy.biz/ComputersElectronics', 1, '2023-11-03 06:39:18', 2104, '1');
INSERT INTO `request` VALUES (68, '51860da4-12f9-ced8-3890-85ca0329af14', 'PATCH', '54.229.190.0', 'http://drive.kaitomasuda.biz/FilmSupplies', 1, '2023-11-01 07:58:28', 78, '0');
INSERT INTO `request` VALUES (69, '588dc416-ce9e-117a-d27a-d03eec48e60c', 'PUT', '210.252.214.3', 'https://video.tsubasanakamori1130.jp/ClothingShoesandJewelry', 0, '2023-11-03 11:21:37', 180, '1');
INSERT INTO `request` VALUES (70, 'e393a9ba-426b-4812-83a3-ab3619174ae5', 'GET', '1.207.196.249', 'https://drive.ota1975.biz/Beauty', 1, '2023-11-04 12:50:59', 8847, '1');
INSERT INTO `request` VALUES (71, 'cfded3c8-9ae7-4f27-7fff-58a9f80763f7', 'DELETE', '103.184.3.254', 'http://auth.wala.info/PetSupplies', 0, '2023-11-03 04:27:05', 6219, '1');
INSERT INTO `request` VALUES (72, 'b58eb407-bcec-0826-7f4e-be10d988d362', 'POST', '9.210.71.254', 'http://www.focynthia3.info/CellPhonesAccessories', 0, '2023-11-02 09:34:45', 9881, '1');
INSERT INTO `request` VALUES (73, '0051c512-360e-838c-0164-b19d6b30fac5', 'PUT', '10.252.51.223', 'https://drive.washington56.com/IndustrialScientificSupplies', 0, '2023-11-02 15:33:18', 6637, '1');
INSERT INTO `request` VALUES (74, '4ba7fb8f-3bc7-49af-3092-7b9cfe0938da', 'GET', '240.8.93.3', 'https://video.hayashiy.xyz/BeautyPersonalCare', 1, '2023-11-04 14:57:09', 7711, '0');
INSERT INTO `request` VALUES (75, '8465362e-d03e-680c-efbb-b9506f8b0e9c', 'DELETE', '8.6.93.79', 'https://video.ming506.org/AppsGames', 0, '2023-11-01 15:17:15', 3294, '0');
INSERT INTO `request` VALUES (76, 'd7fe906d-9f54-f955-f3d9-c3bd17d6346d', 'PUT', '251.7.252.9', 'http://www.anqi10.jp/CollectiblesFineArt', 1, '2023-11-02 20:33:54', 8559, '1');
INSERT INTO `request` VALUES (77, '260557b7-ec11-931c-98a7-64093d056737', 'GET', '53.251.219.5', 'https://www.takwahlau.com/MusicalInstrument', 1, '2023-11-02 19:50:06', 2131, '0');
INSERT INTO `request` VALUES (78, 'd282420d-70de-bffd-a908-212725188022', 'PUT', '206.136.236.250', 'http://www.wxiaom94.cn/CollectiblesFineArt', 1, '2023-11-01 07:46:49', 2817, '0');
INSERT INTO `request` VALUES (79, 'f391212b-baf3-ea9d-9b7c-2e353ef199bd', 'PUT', '245.54.58.188', 'https://www.lu809.jp/Books', 1, '2023-11-04 21:59:49', 9875, '1');
INSERT INTO `request` VALUES (80, 'f7eb8fc7-4cb2-ecb7-02b2-dc7957847ac4', 'POST', '219.197.67.0', 'https://www.hikaru10.biz/ClothingShoesandJewelry', 0, '2023-11-03 12:52:26', 6077, '0');
INSERT INTO `request` VALUES (81, '027928ee-e40c-7f9f-01bd-686a22bce931', 'PUT', '252.215.7.148', 'http://auth.yzhu.com/PetSupplies', 1, '2023-11-03 11:26:37', 4750, '1');
INSERT INTO `request` VALUES (82, '233bca1d-1bdc-eb99-bc94-916bcbfec512', 'POST', '0.37.255.253', 'https://auth.kwokyinlo.jp/ToysGames', 0, '2023-11-01 23:19:45', 5843, '1');
INSERT INTO `request` VALUES (83, 'c977871c-38a2-2642-7002-e525ccb0faab', 'POST', '64.207.129.254', 'https://image.wmai217.us/FilmSupplies', 1, '2023-11-01 14:00:07', 772, '1');
INSERT INTO `request` VALUES (84, 'bee97f5d-51ee-1d6a-526b-3db076deaec8', 'PUT', '117.39.197.115', 'http://www.szekwanyeung.net/AppsGames', 1, '2023-11-02 04:36:17', 5378, '0');
INSERT INTO `request` VALUES (85, 'da2d2161-4cc1-90f1-80d2-beaa0b8996e5', 'GET', '215.54.24.35', 'http://www.chingwan7.com/AppsGames', 0, '2023-11-03 17:57:25', 6661, '1');
INSERT INTO `request` VALUES (86, '4bd1ae59-27bc-6366-f405-68416a5b53c5', 'DELETE', '92.35.4.11', 'https://drive.lazhao.cn/Appliances', 1, '2023-11-03 22:04:32', 4665, '0');
INSERT INTO `request` VALUES (87, '647d60e9-ba5f-6893-687a-272e3b6289d3', 'PUT', '57.213.242.2', 'https://drive.robes217.cn/SportsOutdoor', 1, '2023-11-02 11:19:11', 1446, '1');
INSERT INTO `request` VALUES (88, '85c958cb-221f-9501-c764-3ba15ca51e0e', 'DELETE', '1.163.252.225', 'https://www.haradahikari.biz/ArtsHandicraftsSewing', 0, '2023-11-04 02:30:15', 8999, '0');
INSERT INTO `request` VALUES (89, 'f1043d9f-08fd-197d-04cb-cd69feab487d', 'PATCH', '197.239.237.2', 'http://video.ryotm.co.jp/ComputersElectronics', 1, '2023-11-02 03:51:05', 9358, '0');
INSERT INTO `request` VALUES (90, '9c03fa1a-cccc-170c-c3ab-03569bd6886e', 'PUT', '64.254.252.3', 'https://drive.mhy1960.xyz/ClothingShoesandJewelry', 0, '2023-11-04 12:37:31', 1461, '1');
INSERT INTO `request` VALUES (91, 'fffba034-a9fd-f173-231e-13217b975647', 'POST', '240.254.8.225', 'https://www.jackpatterson10.com/AutomotivePartsAccessories', 1, '2023-11-02 09:28:52', 9096, '1');
INSERT INTO `request` VALUES (92, '4a454ccc-c5c8-49e2-76d6-1965021da2be', 'PUT', '165.227.1.214', 'https://auth.xzhennan.co.jp/MusicalInstrument', 0, '2023-11-04 09:37:19', 6798, '1');
INSERT INTO `request` VALUES (93, '52b9fc70-aaf3-245e-91d2-973c6d9c509c', 'PATCH', '243.157.197.7', 'http://video.chiyuen48.net/ArtsHandicraftsSewing', 1, '2023-11-02 13:37:55', 6613, '0');
INSERT INTO `request` VALUES (94, 'f7110bbc-fae2-0904-7207-267b77155166', 'POST', '183.5.28.64', 'http://drive.onnalui3.cn/CDsVinyl', 1, '2023-11-03 02:10:56', 5179, '0');
INSERT INTO `request` VALUES (95, '59315bc3-1c42-4653-4f06-39c618fa33a8', 'POST', '203.253.5.253', 'http://auth.torresvaler62.xyz/Others', 0, '2023-11-02 14:21:04', 8836, '1');
INSERT INTO `request` VALUES (96, '4c2a15d1-09b0-147b-37d5-d693665c5738', 'POST', '124.128.211.100', 'http://www.chiyuen2012.co.jp/AppsGames', 1, '2023-11-01 06:21:20', 3689, '1');
INSERT INTO `request` VALUES (97, '9acfba17-e856-8514-d6c3-ec7b04cb0ed8', 'PUT', '89.255.4.9', 'http://www.bryad4.net/IndustrialScientificSupplies', 1, '2023-11-02 15:08:15', 8864, '0');
INSERT INTO `request` VALUES (98, '50a85069-f3d7-715f-2559-a094269929cf', 'DELETE', '250.181.55.114', 'http://drive.jiazh.us/Others', 0, '2023-11-01 14:56:14', 4686, '0');
INSERT INTO `request` VALUES (99, 'ceea12b7-2752-4d58-56f2-a28485224b05', 'GET', '6.49.6.135', 'https://video.glan1952.us/PetSupplies', 0, '2023-11-04 13:49:06', 7725, '1');
INSERT INTO `request` VALUES (100, '2156be5f-9ca2-c4b2-1225-b89e8b1ea271', 'PATCH', '255.141.250.160', 'https://image.yuningtang.info/SportsOutdoor', 1, '2023-11-02 07:44:09', 2247, '1');

-- ----------------------------
-- Table structure for request_detail
-- ----------------------------
DROP TABLE IF EXISTS `request_detail`;
CREATE TABLE `request_detail`  (
  `id` int(11) NOT NULL,
  `time` int(11) NOT NULL COMMENT '请求时间',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求状态（0：成功，1：失败）',
  `waf_id` int(11) NOT NULL COMMENT 'waf的id',
  `request_id` int(11) NOT NULL COMMENT '请求id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `request_id`(`request_id`) USING BTREE,
  CONSTRAINT `request_detail_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `request` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of request_detail
-- ----------------------------
INSERT INTO `request_detail` VALUES (1, 568, '0', 1, 87);
INSERT INTO `request_detail` VALUES (2, 934, '0', 2, 17);
INSERT INTO `request_detail` VALUES (3, 894, '1', 3, 40);
INSERT INTO `request_detail` VALUES (4, 105, '0', 5, 38);
INSERT INTO `request_detail` VALUES (5, 712, '1', 5, 90);
INSERT INTO `request_detail` VALUES (6, 64, '1', 4, 94);
INSERT INTO `request_detail` VALUES (7, 165, '0', 4, 47);
INSERT INTO `request_detail` VALUES (8, 478, '1', 4, 22);
INSERT INTO `request_detail` VALUES (9, 530, '0', 4, 69);
INSERT INTO `request_detail` VALUES (10, 468, '0', 2, 37);
INSERT INTO `request_detail` VALUES (11, 653, '1', 1, 65);
INSERT INTO `request_detail` VALUES (12, 91, '1', 4, 21);
INSERT INTO `request_detail` VALUES (13, 368, '1', 3, 70);
INSERT INTO `request_detail` VALUES (14, 558, '0', 5, 21);
INSERT INTO `request_detail` VALUES (15, 636, '0', 3, 6);
INSERT INTO `request_detail` VALUES (16, 102, '1', 2, 84);
INSERT INTO `request_detail` VALUES (17, 567, '1', 4, 63);
INSERT INTO `request_detail` VALUES (18, 347, '1', 4, 9);
INSERT INTO `request_detail` VALUES (19, 587, '1', 6, 25);
INSERT INTO `request_detail` VALUES (20, 857, '0', 2, 8);
INSERT INTO `request_detail` VALUES (21, 373, '0', 2, 28);
INSERT INTO `request_detail` VALUES (22, 118, '0', 5, 34);
INSERT INTO `request_detail` VALUES (23, 926, '0', 2, 5);
INSERT INTO `request_detail` VALUES (24, 747, '0', 2, 42);
INSERT INTO `request_detail` VALUES (25, 379, '1', 5, 7);
INSERT INTO `request_detail` VALUES (26, 346, '0', 2, 57);
INSERT INTO `request_detail` VALUES (27, 56, '0', 3, 31);
INSERT INTO `request_detail` VALUES (28, 37, '1', 4, 36);
INSERT INTO `request_detail` VALUES (29, 129, '0', 2, 80);
INSERT INTO `request_detail` VALUES (30, 797, '1', 2, 95);
INSERT INTO `request_detail` VALUES (31, 702, '1', 2, 91);
INSERT INTO `request_detail` VALUES (32, 858, '0', 5, 64);
INSERT INTO `request_detail` VALUES (33, 653, '1', 2, 50);
INSERT INTO `request_detail` VALUES (34, 312, '1', 3, 24);
INSERT INTO `request_detail` VALUES (35, 504, '0', 5, 45);
INSERT INTO `request_detail` VALUES (36, 640, '0', 5, 80);
INSERT INTO `request_detail` VALUES (37, 942, '1', 5, 25);
INSERT INTO `request_detail` VALUES (38, 724, '1', 1, 28);
INSERT INTO `request_detail` VALUES (39, 48, '1', 2, 14);
INSERT INTO `request_detail` VALUES (40, 493, '0', 3, 39);
INSERT INTO `request_detail` VALUES (41, 833, '0', 2, 6);
INSERT INTO `request_detail` VALUES (42, 110, '0', 5, 84);
INSERT INTO `request_detail` VALUES (43, 62, '1', 4, 100);
INSERT INTO `request_detail` VALUES (44, 247, '0', 3, 50);
INSERT INTO `request_detail` VALUES (45, 34, '1', 3, 77);
INSERT INTO `request_detail` VALUES (46, 453, '0', 3, 54);
INSERT INTO `request_detail` VALUES (47, 756, '1', 2, 81);
INSERT INTO `request_detail` VALUES (48, 294, '1', 6, 9);
INSERT INTO `request_detail` VALUES (49, 292, '0', 2, 10);
INSERT INTO `request_detail` VALUES (50, 373, '1', 5, 73);
INSERT INTO `request_detail` VALUES (51, 215, '0', 4, 23);
INSERT INTO `request_detail` VALUES (52, 9, '0', 3, 64);
INSERT INTO `request_detail` VALUES (53, 596, '1', 2, 51);
INSERT INTO `request_detail` VALUES (54, 556, '0', 5, 4);
INSERT INTO `request_detail` VALUES (55, 983, '0', 3, 38);
INSERT INTO `request_detail` VALUES (56, 486, '0', 5, 62);
INSERT INTO `request_detail` VALUES (57, 465, '0', 5, 14);
INSERT INTO `request_detail` VALUES (58, 743, '1', 3, 93);
INSERT INTO `request_detail` VALUES (59, 711, '1', 5, 5);
INSERT INTO `request_detail` VALUES (60, 898, '0', 6, 74);
INSERT INTO `request_detail` VALUES (61, 152, '0', 2, 63);
INSERT INTO `request_detail` VALUES (62, 755, '1', 2, 56);
INSERT INTO `request_detail` VALUES (63, 397, '0', 2, 36);
INSERT INTO `request_detail` VALUES (64, 663, '1', 3, 15);
INSERT INTO `request_detail` VALUES (65, 357, '0', 4, 63);
INSERT INTO `request_detail` VALUES (66, 79, '1', 5, 60);
INSERT INTO `request_detail` VALUES (67, 969, '1', 5, 5);
INSERT INTO `request_detail` VALUES (68, 874, '0', 4, 14);
INSERT INTO `request_detail` VALUES (69, 692, '0', 4, 84);
INSERT INTO `request_detail` VALUES (70, 769, '1', 2, 39);
INSERT INTO `request_detail` VALUES (71, 337, '1', 5, 73);
INSERT INTO `request_detail` VALUES (72, 409, '0', 4, 93);
INSERT INTO `request_detail` VALUES (73, 901, '0', 1, 90);
INSERT INTO `request_detail` VALUES (74, 975, '1', 4, 88);
INSERT INTO `request_detail` VALUES (75, 410, '0', 3, 42);
INSERT INTO `request_detail` VALUES (76, 495, '0', 4, 19);
INSERT INTO `request_detail` VALUES (77, 4, '0', 2, 77);
INSERT INTO `request_detail` VALUES (78, 382, '1', 3, 13);
INSERT INTO `request_detail` VALUES (79, 96, '1', 3, 46);
INSERT INTO `request_detail` VALUES (80, 401, '1', 4, 13);
INSERT INTO `request_detail` VALUES (81, 476, '1', 3, 8);
INSERT INTO `request_detail` VALUES (82, 464, '1', 5, 41);
INSERT INTO `request_detail` VALUES (83, 796, '0', 1, 92);
INSERT INTO `request_detail` VALUES (84, 665, '0', 4, 47);
INSERT INTO `request_detail` VALUES (85, 115, '0', 4, 20);
INSERT INTO `request_detail` VALUES (86, 287, '1', 3, 57);
INSERT INTO `request_detail` VALUES (87, 370, '1', 5, 28);
INSERT INTO `request_detail` VALUES (88, 187, '1', 3, 13);
INSERT INTO `request_detail` VALUES (89, 37, '0', 4, 45);
INSERT INTO `request_detail` VALUES (90, 241, '0', 4, 62);
INSERT INTO `request_detail` VALUES (91, 888, '0', 3, 48);
INSERT INTO `request_detail` VALUES (92, 8, '1', 1, 56);
INSERT INTO `request_detail` VALUES (93, 525, '1', 1, 57);
INSERT INTO `request_detail` VALUES (94, 770, '0', 5, 10);
INSERT INTO `request_detail` VALUES (95, 857, '0', 5, 67);
INSERT INTO `request_detail` VALUES (96, 920, '0', 2, 45);
INSERT INTO `request_detail` VALUES (97, 263, '1', 5, 18);
INSERT INTO `request_detail` VALUES (98, 541, '0', 4, 27);
INSERT INTO `request_detail` VALUES (99, 95, '1', 1, 72);
INSERT INTO `request_detail` VALUES (100, 615, '1', 3, 25);
INSERT INTO `request_detail` VALUES (101, 227, '1', 3, 74);
INSERT INTO `request_detail` VALUES (102, 767, '0', 6, 68);
INSERT INTO `request_detail` VALUES (103, 954, '0', 2, 89);
INSERT INTO `request_detail` VALUES (104, 843, '0', 3, 1);
INSERT INTO `request_detail` VALUES (105, 888, '0', 5, 93);
INSERT INTO `request_detail` VALUES (106, 200, '0', 5, 94);
INSERT INTO `request_detail` VALUES (107, 762, '0', 4, 58);
INSERT INTO `request_detail` VALUES (108, 655, '1', 4, 1);
INSERT INTO `request_detail` VALUES (109, 1, '1', 4, 7);
INSERT INTO `request_detail` VALUES (110, 148, '0', 4, 85);
INSERT INTO `request_detail` VALUES (111, 472, '0', 1, 56);
INSERT INTO `request_detail` VALUES (112, 703, '0', 2, 24);
INSERT INTO `request_detail` VALUES (113, 813, '0', 2, 94);
INSERT INTO `request_detail` VALUES (114, 723, '0', 4, 59);
INSERT INTO `request_detail` VALUES (115, 325, '0', 1, 91);
INSERT INTO `request_detail` VALUES (116, 720, '0', 4, 96);
INSERT INTO `request_detail` VALUES (117, 636, '0', 4, 91);
INSERT INTO `request_detail` VALUES (118, 142, '0', 5, 81);
INSERT INTO `request_detail` VALUES (119, 987, '1', 4, 52);
INSERT INTO `request_detail` VALUES (120, 106, '0', 3, 2);
INSERT INTO `request_detail` VALUES (121, 864, '0', 6, 49);
INSERT INTO `request_detail` VALUES (122, 40, '0', 1, 61);
INSERT INTO `request_detail` VALUES (123, 176, '0', 5, 52);
INSERT INTO `request_detail` VALUES (124, 60, '0', 3, 32);
INSERT INTO `request_detail` VALUES (125, 74, '0', 5, 50);
INSERT INTO `request_detail` VALUES (126, 72, '0', 5, 81);
INSERT INTO `request_detail` VALUES (127, 368, '1', 4, 60);
INSERT INTO `request_detail` VALUES (128, 474, '1', 4, 10);
INSERT INTO `request_detail` VALUES (129, 176, '0', 2, 23);
INSERT INTO `request_detail` VALUES (130, 432, '0', 2, 51);
INSERT INTO `request_detail` VALUES (131, 894, '0', 5, 9);
INSERT INTO `request_detail` VALUES (132, 366, '0', 1, 42);
INSERT INTO `request_detail` VALUES (133, 20, '0', 6, 86);
INSERT INTO `request_detail` VALUES (134, 872, '1', 5, 33);
INSERT INTO `request_detail` VALUES (135, 262, '0', 5, 47);
INSERT INTO `request_detail` VALUES (136, 527, '1', 6, 23);
INSERT INTO `request_detail` VALUES (137, 906, '0', 2, 51);
INSERT INTO `request_detail` VALUES (138, 140, '0', 4, 6);
INSERT INTO `request_detail` VALUES (139, 681, '1', 1, 68);
INSERT INTO `request_detail` VALUES (140, 806, '0', 4, 99);
INSERT INTO `request_detail` VALUES (141, 9, '0', 3, 76);
INSERT INTO `request_detail` VALUES (142, 272, '0', 2, 36);
INSERT INTO `request_detail` VALUES (143, 280, '0', 2, 87);
INSERT INTO `request_detail` VALUES (144, 650, '1', 3, 31);
INSERT INTO `request_detail` VALUES (145, 747, '0', 2, 49);
INSERT INTO `request_detail` VALUES (146, 508, '0', 2, 72);
INSERT INTO `request_detail` VALUES (147, 785, '0', 2, 60);
INSERT INTO `request_detail` VALUES (148, 777, '1', 3, 55);
INSERT INTO `request_detail` VALUES (149, 821, '0', 2, 27);
INSERT INTO `request_detail` VALUES (150, 73, '0', 5, 30);
INSERT INTO `request_detail` VALUES (151, 22, '0', 4, 90);
INSERT INTO `request_detail` VALUES (152, 543, '0', 4, 61);
INSERT INTO `request_detail` VALUES (153, 424, '0', 4, 21);
INSERT INTO `request_detail` VALUES (154, 741, '1', 1, 18);
INSERT INTO `request_detail` VALUES (155, 418, '1', 1, 1);
INSERT INTO `request_detail` VALUES (156, 495, '1', 5, 41);
INSERT INTO `request_detail` VALUES (157, 245, '1', 3, 95);
INSERT INTO `request_detail` VALUES (158, 596, '0', 5, 72);
INSERT INTO `request_detail` VALUES (159, 248, '0', 3, 75);
INSERT INTO `request_detail` VALUES (160, 625, '0', 5, 19);
INSERT INTO `request_detail` VALUES (161, 202, '0', 2, 74);
INSERT INTO `request_detail` VALUES (162, 307, '1', 3, 96);
INSERT INTO `request_detail` VALUES (163, 270, '1', 5, 40);
INSERT INTO `request_detail` VALUES (164, 454, '1', 2, 87);
INSERT INTO `request_detail` VALUES (165, 94, '1', 6, 80);
INSERT INTO `request_detail` VALUES (166, 975, '1', 4, 98);
INSERT INTO `request_detail` VALUES (167, 932, '1', 4, 99);
INSERT INTO `request_detail` VALUES (168, 805, '0', 2, 99);
INSERT INTO `request_detail` VALUES (169, 179, '0', 5, 40);
INSERT INTO `request_detail` VALUES (170, 846, '1', 4, 35);
INSERT INTO `request_detail` VALUES (171, 133, '0', 6, 71);
INSERT INTO `request_detail` VALUES (172, 585, '1', 2, 77);
INSERT INTO `request_detail` VALUES (173, 496, '1', 1, 53);
INSERT INTO `request_detail` VALUES (174, 432, '0', 4, 82);
INSERT INTO `request_detail` VALUES (175, 846, '1', 1, 39);
INSERT INTO `request_detail` VALUES (176, 209, '1', 3, 89);
INSERT INTO `request_detail` VALUES (177, 805, '1', 3, 100);
INSERT INTO `request_detail` VALUES (178, 971, '0', 4, 46);
INSERT INTO `request_detail` VALUES (179, 180, '1', 3, 86);
INSERT INTO `request_detail` VALUES (180, 915, '1', 3, 7);
INSERT INTO `request_detail` VALUES (181, 578, '1', 1, 4);
INSERT INTO `request_detail` VALUES (182, 980, '1', 2, 43);
INSERT INTO `request_detail` VALUES (183, 485, '0', 4, 66);
INSERT INTO `request_detail` VALUES (184, 694, '1', 3, 24);
INSERT INTO `request_detail` VALUES (185, 786, '1', 6, 38);
INSERT INTO `request_detail` VALUES (186, 41, '0', 4, 49);
INSERT INTO `request_detail` VALUES (187, 274, '1', 2, 88);
INSERT INTO `request_detail` VALUES (188, 799, '1', 2, 86);
INSERT INTO `request_detail` VALUES (189, 763, '0', 6, 35);
INSERT INTO `request_detail` VALUES (190, 309, '0', 2, 54);
INSERT INTO `request_detail` VALUES (191, 948, '0', 5, 97);
INSERT INTO `request_detail` VALUES (192, 554, '0', 2, 35);
INSERT INTO `request_detail` VALUES (193, 5, '1', 4, 32);
INSERT INTO `request_detail` VALUES (194, 717, '0', 4, 53);
INSERT INTO `request_detail` VALUES (195, 335, '1', 6, 70);
INSERT INTO `request_detail` VALUES (196, 258, '0', 4, 66);
INSERT INTO `request_detail` VALUES (197, 188, '0', 2, 30);
INSERT INTO `request_detail` VALUES (198, 471, '0', 2, 26);
INSERT INTO `request_detail` VALUES (199, 495, '0', 6, 70);
INSERT INTO `request_detail` VALUES (200, 777, '0', 3, 20);
INSERT INTO `request_detail` VALUES (201, 107, '0', 5, 75);
INSERT INTO `request_detail` VALUES (202, 473, '0', 1, 98);
INSERT INTO `request_detail` VALUES (203, 896, '0', 4, 41);
INSERT INTO `request_detail` VALUES (204, 350, '1', 6, 62);
INSERT INTO `request_detail` VALUES (205, 872, '0', 2, 69);
INSERT INTO `request_detail` VALUES (206, 413, '0', 2, 26);
INSERT INTO `request_detail` VALUES (207, 443, '0', 5, 34);
INSERT INTO `request_detail` VALUES (208, 278, '1', 4, 73);
INSERT INTO `request_detail` VALUES (209, 247, '0', 4, 37);
INSERT INTO `request_detail` VALUES (210, 722, '0', 5, 95);
INSERT INTO `request_detail` VALUES (211, 306, '1', 6, 65);
INSERT INTO `request_detail` VALUES (212, 291, '0', 5, 17);
INSERT INTO `request_detail` VALUES (213, 563, '1', 3, 69);
INSERT INTO `request_detail` VALUES (214, 405, '1', 3, 71);
INSERT INTO `request_detail` VALUES (215, 397, '1', 5, 59);
INSERT INTO `request_detail` VALUES (216, 72, '1', 6, 20);
INSERT INTO `request_detail` VALUES (217, 830, '1', 3, 76);
INSERT INTO `request_detail` VALUES (218, 323, '0', 2, 58);
INSERT INTO `request_detail` VALUES (219, 596, '0', 3, 46);
INSERT INTO `request_detail` VALUES (220, 195, '1', 5, 30);
INSERT INTO `request_detail` VALUES (221, 984, '0', 4, 100);
INSERT INTO `request_detail` VALUES (222, 833, '1', 3, 22);
INSERT INTO `request_detail` VALUES (223, 832, '0', 1, 98);
INSERT INTO `request_detail` VALUES (224, 193, '0', 5, 71);
INSERT INTO `request_detail` VALUES (225, 492, '1', 5, 31);
INSERT INTO `request_detail` VALUES (226, 896, '0', 2, 26);
INSERT INTO `request_detail` VALUES (227, 554, '0', 6, 78);
INSERT INTO `request_detail` VALUES (228, 309, '1', 5, 64);
INSERT INTO `request_detail` VALUES (229, 516, '1', 6, 22);
INSERT INTO `request_detail` VALUES (230, 253, '0', 1, 97);
INSERT INTO `request_detail` VALUES (231, 763, '1', 1, 76);
INSERT INTO `request_detail` VALUES (232, 762, '0', 4, 58);
INSERT INTO `request_detail` VALUES (233, 185, '0', 5, 17);
INSERT INTO `request_detail` VALUES (234, 281, '0', 3, 89);
INSERT INTO `request_detail` VALUES (235, 734, '0', 1, 78);
INSERT INTO `request_detail` VALUES (236, 362, '1', 2, 75);
INSERT INTO `request_detail` VALUES (237, 303, '1', 3, 85);
INSERT INTO `request_detail` VALUES (238, 513, '1', 3, 37);
INSERT INTO `request_detail` VALUES (239, 29, '1', 2, 12);
INSERT INTO `request_detail` VALUES (240, 371, '0', 5, 54);
INSERT INTO `request_detail` VALUES (241, 853, '0', 1, 3);
INSERT INTO `request_detail` VALUES (242, 720, '0', 3, 61);
INSERT INTO `request_detail` VALUES (243, 239, '0', 6, 79);
INSERT INTO `request_detail` VALUES (244, 266, '1', 4, 29);
INSERT INTO `request_detail` VALUES (245, 534, '1', 2, 3);
INSERT INTO `request_detail` VALUES (246, 227, '1', 5, 2);
INSERT INTO `request_detail` VALUES (247, 189, '1', 6, 66);
INSERT INTO `request_detail` VALUES (248, 302, '1', 5, 85);
INSERT INTO `request_detail` VALUES (249, 663, '0', 4, 52);
INSERT INTO `request_detail` VALUES (250, 545, '0', 5, 59);
INSERT INTO `request_detail` VALUES (251, 544, '1', 3, 68);
INSERT INTO `request_detail` VALUES (252, 934, '1', 4, 27);
INSERT INTO `request_detail` VALUES (253, 824, '0', 2, 15);
INSERT INTO `request_detail` VALUES (254, 542, '1', 3, 33);
INSERT INTO `request_detail` VALUES (255, 395, '0', 4, 11);
INSERT INTO `request_detail` VALUES (256, 410, '0', 4, 19);
INSERT INTO `request_detail` VALUES (257, 976, '1', 2, 8);
INSERT INTO `request_detail` VALUES (258, 15, '0', 5, 4);
INSERT INTO `request_detail` VALUES (259, 518, '0', 5, 53);
INSERT INTO `request_detail` VALUES (260, 608, '0', 6, 33);
INSERT INTO `request_detail` VALUES (261, 710, '1', 2, 15);
INSERT INTO `request_detail` VALUES (262, 340, '1', 3, 29);
INSERT INTO `request_detail` VALUES (263, 876, '1', 1, 92);
INSERT INTO `request_detail` VALUES (264, 757, '1', 5, 67);
INSERT INTO `request_detail` VALUES (265, 704, '0', 3, 88);
INSERT INTO `request_detail` VALUES (266, 770, '1', 5, 67);
INSERT INTO `request_detail` VALUES (267, 788, '1', 6, 11);
INSERT INTO `request_detail` VALUES (268, 497, '0', 4, 29);
INSERT INTO `request_detail` VALUES (269, 99, '0', 2, 78);
INSERT INTO `request_detail` VALUES (270, 764, '0', 2, 11);
INSERT INTO `request_detail` VALUES (271, 429, '1', 5, 65);
INSERT INTO `request_detail` VALUES (272, 260, '1', 3, 18);
INSERT INTO `request_detail` VALUES (273, 248, '1', 2, 2);
INSERT INTO `request_detail` VALUES (274, 996, '0', 3, 43);
INSERT INTO `request_detail` VALUES (275, 647, '1', 6, 96);
INSERT INTO `request_detail` VALUES (276, 454, '1', 3, 97);
INSERT INTO `request_detail` VALUES (277, 468, '0', 4, 48);
INSERT INTO `request_detail` VALUES (278, 760, '0', 3, 3);
INSERT INTO `request_detail` VALUES (279, 154, '0', 1, 48);
INSERT INTO `request_detail` VALUES (280, 577, '0', 6, 34);
INSERT INTO `request_detail` VALUES (281, 836, '0', 4, 43);
INSERT INTO `request_detail` VALUES (282, 62, '0', 6, 44);
INSERT INTO `request_detail` VALUES (283, 702, '1', 4, 32);
INSERT INTO `request_detail` VALUES (284, 501, '0', 1, 82);
INSERT INTO `request_detail` VALUES (285, 712, '0', 4, 79);
INSERT INTO `request_detail` VALUES (286, 18, '1', 4, 92);
INSERT INTO `request_detail` VALUES (287, 529, '1', 1, 79);
INSERT INTO `request_detail` VALUES (288, 262, '1', 4, 44);
INSERT INTO `request_detail` VALUES (289, 389, '1', 6, 12);
INSERT INTO `request_detail` VALUES (290, 918, '0', 6, 44);
INSERT INTO `request_detail` VALUES (291, 349, '0', 4, 55);
INSERT INTO `request_detail` VALUES (292, 232, '0', 1, 12);
INSERT INTO `request_detail` VALUES (293, 473, '1', 5, 83);
INSERT INTO `request_detail` VALUES (294, 172, '0', 3, 83);
INSERT INTO `request_detail` VALUES (295, 667, '0', 5, 16);
INSERT INTO `request_detail` VALUES (296, 42, '0', 1, 82);
INSERT INTO `request_detail` VALUES (297, 458, '0', 5, 55);
INSERT INTO `request_detail` VALUES (298, 740, '1', 3, 83);
INSERT INTO `request_detail` VALUES (299, 3, '1', 1, 16);
INSERT INTO `request_detail` VALUES (300, 740, '0', 5, 16);

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
-- Table structure for waf
-- ----------------------------
DROP TABLE IF EXISTS `waf`;
CREATE TABLE `waf`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'waf名称',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'ip地址',
  `port` int(10) NOT NULL DEFAULT 0 COMMENT '端口',
  `config_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '配置地址',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态（0：未启用，1：异常，2：正常运行）',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '描述信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'waf配置信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of waf
-- ----------------------------
INSERT INTO `waf` VALUES (1, 'Grape', '190.255.254.6', 45578, 'http://image.eita7.jp/Handcrafts', 0, 'o8CVT2SHeR');
INSERT INTO `waf` VALUES (2, 'Mrngo pi', '226.112.231.13', 51708, 'https://image.seikowada3.us/CollectiblesFineArt', 0, 'y0WZYqFi0T');
INSERT INTO `waf` VALUES (3, 'Kiwi', '240.255.141.0', 11367, 'https://video.tsui1.info/CollectiblesFineArt', 2, 'F5lG8ICvOq');
INSERT INTO `waf` VALUES (4, 'Cherry pi', '206.8.191.158', 52573, 'http://video.tiayuning85.xyz/IndustrialScientificSupplies', 0, 'ipVZJZhPyr');
INSERT INTO `waf` VALUES (5, 'Pluots', '4.56.251.2295', 10304, 'http://www.kaitonak.us/BaggageTravelEquipment', 0, 'mBMOrefaOa');
INSERT INTO `waf` VALUES (6, 'shega', '5.19.49.11', 5033, 'http://www.kaitonak.us/BaggageTravelEquipment2', 0, 'sasfsfasfa');

-- ----------------------------
-- Table structure for waf_status
-- ----------------------------
DROP TABLE IF EXISTS `waf_status`;
CREATE TABLE `waf_status`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `cpu` int(5) NULL DEFAULT NULL COMMENT 'cpu占用率',
  `memory` int(5) NULL DEFAULT NULL COMMENT '内存占用率',
  `start_time` datetime NULL DEFAULT NULL COMMENT '启动时间',
  `up_time` int(11) NULL DEFAULT NULL COMMENT '运行时间（分钟）',
  `waf_id` int(11) NULL DEFAULT NULL COMMENT 'wafId',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `waf_id`(`waf_id`) USING BTREE,
  CONSTRAINT `waf_status_ibfk_1` FOREIGN KEY (`waf_id`) REFERENCES `waf` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of waf_status
-- ----------------------------
INSERT INTO `waf_status` VALUES (1, 77, 11, '2023-11-10 01:27:51', 147, 1);
INSERT INTO `waf_status` VALUES (2, 79, 36, '2023-11-10 17:17:29', 684, 2);
INSERT INTO `waf_status` VALUES (3, 67, 47, '2023-11-10 20:05:58', 846, 3);
INSERT INTO `waf_status` VALUES (4, 19, 32, '2023-11-10 14:39:21', 968, 4);
INSERT INTO `waf_status` VALUES (5, 29, 55, '2023-11-10 14:46:30', 791, 5);
INSERT INTO `waf_status` VALUES (6, 56, 45, '2023-11-27 21:39:16', 15, 6);

SET FOREIGN_KEY_CHECKS = 1;
