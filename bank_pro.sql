/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50724
Source Host           : 127.0.0.1:3306
Source Database       : bank_pro

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-03-25 11:49:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_admin`;
CREATE TABLE `t_sys_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(32) NOT NULL COMMENT '用户名isListShowisFormShow',
  `password` varchar(128) NOT NULL COMMENT '登录密码isListShowisFormShow',
  `real_name` varchar(32) NOT NULL COMMENT '真实姓名isListShowisFormShow',
  `is_block` varchar(16) DEFAULT 'NO' COMMENT '锁定状态isListShowisFormShow|YES:锁定,NO:正常',
  `user_type` varchar(16) DEFAULT NULL COMMENT '用户类型',
  `store_id` bigint(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间isListShow',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户-登录用户';

-- ----------------------------
-- Records of t_sys_admin
-- ----------------------------
INSERT INTO `t_sys_admin` VALUES ('1', 'superadmin', 'b3e5eeb592d650f8fa81b4f378c4dbab4758d8233481b8564d5e0ebad9b71434', '超级管理员1', 'NO', 'ADMIN', null, '2018-05-31 16:16:11');

-- ----------------------------
-- Table structure for t_sys_bank
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_bank`;
CREATE TABLE `t_sys_bank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(32) DEFAULT NULL COMMENT '银行名称isListShowisFormShow',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `level` int(10) DEFAULT NULL COMMENT '银行等级',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间isListShow',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=503 DEFAULT CHARSET=utf8 COMMENT='银行';

-- ----------------------------
-- Records of t_sys_bank
-- ----------------------------
INSERT INTO `t_sys_bank` VALUES ('446', '包头农商行本部', null, '1', '2019-03-17 22:07:21');
INSERT INTO `t_sys_bank` VALUES ('447', '包头农商行营业部', null, '1', '2019-03-17 22:07:21');
INSERT INTO `t_sys_bank` VALUES ('448', '东河支行', null, '1', '2019-03-17 22:07:21');
INSERT INTO `t_sys_bank` VALUES ('449', '河东支行', '448', '2', '2019-03-17 22:07:21');
INSERT INTO `t_sys_bank` VALUES ('450', '佳美支行', '448', '2', '2019-03-17 22:07:21');
INSERT INTO `t_sys_bank` VALUES ('451', '南海支行', '448', '2', '2019-03-17 22:07:21');
INSERT INTO `t_sys_bank` VALUES ('452', '西脑包支行', '448', '2', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('453', '东门外支行', '448', '2', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('454', '北梁支行', '448', '2', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('455', '环城路支行', '448', '2', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('456', '站北路支行', '448', '2', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('457', '车站分理处', '448', '2', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('458', '惠民支行', '448', '2', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('459', '九原支行', null, '1', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('460', '哈屯高勒支行', '459', '2', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('461', '前明支行', '459', '2', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('462', '金创支行', '459', '2', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('463', '兴胜支行', '459', '2', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('464', '新都市区支行', '459', '2', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('465', '健康路支行', '459', '2', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('466', '富力城支行', '459', '2', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('467', '青山支行', null, '1', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('468', '北新苑支行', '467', '2', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('469', '富强路支行', '467', '2', '2019-03-17 22:07:22');
INSERT INTO `t_sys_bank` VALUES ('470', '商会支行', '467', '2', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('471', '文化路支行', '467', '2', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('472', '科学路支行', '467', '2', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('473', '昆都仑支行', null, '1', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('474', '林荫南路支行', '473', '2', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('475', '包西支行', '473', '2', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('476', '钢铁大街支行', '473', '2', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('477', '青年路支行', '473', '2', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('478', '少先路女商支行', null, '1', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('479', '铁西区支行', null, '1', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('480', '凤凰城支行', '479', '2', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('481', '巴彦塔拉支行', '479', '2', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('482', '铁西二区分理处', '479', '2', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('483', '石拐支行', null, '1', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('484', '五当召支行', '483', '2', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('485', '后营子支行', '483', '2', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('486', '滨河支行', null, '1', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('487', '交界营子支行', '486', '2', '2019-03-17 22:07:23');
INSERT INTO `t_sys_bank` VALUES ('488', '润恒分理处', '486', '2', '2019-03-17 22:07:24');
INSERT INTO `t_sys_bank` VALUES ('489', '幸福南路支行', '486', '2', '2019-03-17 22:07:24');
INSERT INTO `t_sys_bank` VALUES ('490', '古城湾支行', null, '1', '2019-03-17 22:07:24');
INSERT INTO `t_sys_bank` VALUES ('491', '铝厂分理处', '490', '2', '2019-03-17 22:07:24');
INSERT INTO `t_sys_bank` VALUES ('492', '糖厂分理处', '490', '2', '2019-03-17 22:07:24');
INSERT INTO `t_sys_bank` VALUES ('493', '沙尔沁支行', null, '1', '2019-03-17 22:07:24');
INSERT INTO `t_sys_bank` VALUES ('494', '土合气分理处', '493', '2', '2019-03-17 22:07:24');
INSERT INTO `t_sys_bank` VALUES ('495', '东园支行', null, '1', '2019-03-17 22:07:24');
INSERT INTO `t_sys_bank` VALUES ('496', '东园分理处', '495', '2', '2019-03-17 22:07:24');
INSERT INTO `t_sys_bank` VALUES ('497', '公积板分理处', '495', '2', '2019-03-17 22:07:24');
INSERT INTO `t_sys_bank` VALUES ('498', '白云鄂博支行', null, '1', '2019-03-17 22:07:24');
INSERT INTO `t_sys_bank` VALUES ('499', '民族东路普惠支行', null, '1', '2019-03-17 22:07:24');
INSERT INTO `t_sys_bank` VALUES ('500', '网银', null, '1', '2019-03-17 22:07:24');
INSERT INTO `t_sys_bank` VALUES ('501', '达茂支行', null, '1', '2019-03-17 22:07:24');
INSERT INTO `t_sys_bank` VALUES ('502', '土右支行', null, '1', '2019-03-17 22:07:24');

-- ----------------------------
-- Table structure for t_sys_data
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_data`;
CREATE TABLE `t_sys_data` (
  `id` bigint(20) NOT NULL,
  `period_id` bigint(20) DEFAULT NULL,
  `bank_id` bigint(20) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `buy_money` int(10) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL COMMENT 'isListShowisFormShow|LY:保本封闭式,TY:保本开放式,XY:非保本封闭式,RRY:非保本开放式',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='导入数据';

-- ----------------------------
-- Records of t_sys_data
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_ledger
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_ledger`;
CREATE TABLE `t_sys_ledger` (
  `id` bigint(20) NOT NULL,
  `sort_number` int(10) DEFAULT NULL COMMENT '序号',
  `number` varchar(255) DEFAULT NULL COMMENT '期数',
  `start_time` datetime DEFAULT NULL COMMENT '起息日',
  `end_time` datetime DEFAULT NULL COMMENT '到期日',
  `end_days` int(10) DEFAULT NULL COMMENT '到期天数',
  `period` int(10) DEFAULT NULL COMMENT '期限',
  `rate` varchar(255) DEFAULT NULL COMMENT '收益率',
  `buy_money` int(11) DEFAULT NULL COMMENT '购买金额',
  `period_id` bigint(20) DEFAULT NULL COMMENT '关联ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='台账';

-- ----------------------------
-- Records of t_sys_ledger
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_period
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_period`;
CREATE TABLE `t_sys_period` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '标题isListShowisFormShow',
  `descs` varchar(500) DEFAULT NULL COMMENT '描述isListShowisFormShow',
  `type` varchar(16) DEFAULT NULL COMMENT '类型isListShowisFormShow|LY:保本封闭式,TY:保本开放式,XY:非保本封闭式,RRY:非保本开放式',
  `data_type` varchar(16) DEFAULT NULL COMMENT '数据类型|BB:保本,FBB:非保本',
  `create_id` bigint(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间isListShow',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='统计';

-- ----------------------------
-- Records of t_sys_period
-- ----------------------------
