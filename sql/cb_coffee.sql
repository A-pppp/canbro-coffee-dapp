/*
 Navicat Premium Dump SQL

 Source Server         : 本地环境
 Source Server Type    : MySQL
 Source Server Version : 90001 (9.0.1)
 Source Host           : localhost:3304
 Source Schema         : cb_coffee

 Target Server Type    : MySQL
 Target Server Version : 90001 (9.0.1)
 File Encoding         : 65001

 Date: 24/09/2024 17:38:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blockchain
-- ----------------------------
DROP TABLE IF EXISTS `t_blockchain`;
CREATE TABLE `t_blockchain`  (
  `blockchain_id` char(6) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '区块链id',
  `blockchain_code` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '区块链编码',
  `blockchain_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '区块链名称',
  `blockchain_main_network` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '区块链主网地址',
  `blockchain_parent_id` char(6) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '父区块链id',
  `blockchain_state` int NULL DEFAULT NULL COMMENT '状态',
  `create_by` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` int NULL DEFAULT NULL COMMENT '创建时间',
  `modify_by` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` int NULL DEFAULT NULL COMMENT '修改时间',
  `remark` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`blockchain_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_mch
-- ----------------------------
DROP TABLE IF EXISTS `t_mch`;
CREATE TABLE `t_mch`  (
  `mch_id` char(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '商户id',
  `mch_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商户名称',
  `mch_province` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商户省份',
  `mch_city` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商户城市',
  `mch_address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商户地址',
  `mch_state` int NULL DEFAULT NULL COMMENT '商户状态',
  `create_by` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` int NULL DEFAULT NULL COMMENT '创建时间',
  `modify_by` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` int NULL DEFAULT NULL COMMENT '修改时间',
  `remark` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`mch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_prod
-- ----------------------------
DROP TABLE IF EXISTS `t_prod`;
CREATE TABLE `t_prod`  (
  `prod_code` char(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '商品编码',
  `prod_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `prod_pic` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '商品小图',
  `prod_media_urls` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '商品轮播图地址',
  `category_id` int NULL DEFAULT NULL COMMENT '商品分类id',
  `attribute_list` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '商品规格列表',
  `prod_state` int NULL DEFAULT NULL COMMENT '商品状态',
  `create_time` int NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` int NULL DEFAULT NULL COMMENT '修改时间',
  `remark` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`prod_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_prod_attribute_key
-- ----------------------------
DROP TABLE IF EXISTS `t_prod_attribute_key`;
CREATE TABLE `t_prod_attribute_key`  (
  `attribute_key_id` int NOT NULL COMMENT '属性key id',
  `category_id` int NULL DEFAULT NULL COMMENT '分类id',
  `attribute_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '属性key名',
  `create_time` int NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` int NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`attribute_key_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_prod_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS `t_prod_attribute_value`;
CREATE TABLE `t_prod_attribute_value`  (
  `attribute_value_id` int NOT NULL COMMENT '属性值id',
  `attribute_key_id` int NULL DEFAULT NULL COMMENT '属性key id',
  `attribute_value` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '属性值',
  `create_time` int NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` int NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`attribute_value_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_prod_category
-- ----------------------------
DROP TABLE IF EXISTS `t_prod_category`;
CREATE TABLE `t_prod_category`  (
  `category_id` int NOT NULL COMMENT '商品类别id',
  `category_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品类别名称',
  `category_parent_id` int NULL DEFAULT NULL COMMENT '商品类别父id',
  `create_time` int NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` int NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_prod_specs
-- ----------------------------
DROP TABLE IF EXISTS `t_prod_specs`;
CREATE TABLE `t_prod_specs`  (
  `prod_specs_id` int NOT NULL COMMENT '商品规格id',
  `prod_id` int NULL DEFAULT NULL COMMENT '商品id',
  `mch_id` int NULL DEFAULT NULL COMMENT '商户id',
  `prod_stock` int NULL DEFAULT NULL COMMENT '商品库存',
  `prod_currency` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品价格币种',
  `prod_price` int NULL DEFAULT NULL COMMENT '商品价格',
  `create_time` int NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` int NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`prod_specs_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_trade_order
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_order`;
CREATE TABLE `t_trade_order`  (
  `trade_no` char(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '交易单号',
  `mch_id` char(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商户号',
  `trade_prods` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '订单商品详情(json array str)',
  `trade_currency` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '交易币种',
  `trade_amount` int NULL DEFAULT NULL COMMENT '交易金额',
  `pay_currency` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '支付币种',
  `pay_amount` int NULL DEFAULT NULL COMMENT '支付金额',
  `pay_exchange_rate` int NULL DEFAULT NULL COMMENT '支付时交易对价格',
  `pay_gas` int NULL DEFAULT NULL COMMENT '支付gas费',
  `mch_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商户名称',
  `mch_province` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商户省份',
  `mch_city` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商户城市',
  `mch_address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商户地址',
  `time_expire` int NULL DEFAULT NULL COMMENT '订单超时时间',
  `mch_wallet_currency` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商户钱包币种',
  `mch_wallet_address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商户钱包地址',
  `mch_wallet_memo` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商户钱包地址备注',
  `trade_desc` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '交易描述',
  `client_os` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '客户端系统',
  `client_ip` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '客户端ip',
  `client_app` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '客户端app',
  `trade_state` int NULL DEFAULT NULL COMMENT '订单状态',
  `pay_state` int NULL DEFAULT NULL COMMENT '支付状态',
  `refund_state` int NULL DEFAULT NULL COMMENT '退款状态',
  `create_time` int NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` int NULL DEFAULT NULL COMMENT '修改时间',
  `pay_time` int NULL DEFAULT NULL COMMENT '支付时间',
  `placing_time` int NULL DEFAULT NULL COMMENT '下单时间',
  `refund_time` int NULL DEFAULT NULL COMMENT '退款时间',
  `close_time` int NULL DEFAULT NULL COMMENT '关闭时间',
  `finish_time` int NULL DEFAULT NULL COMMENT '完成时间',
  `remark` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`trade_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_wallet
-- ----------------------------
DROP TABLE IF EXISTS `t_wallet`;
CREATE TABLE `t_wallet`  (
  `wallet_id` int NOT NULL AUTO_INCREMENT COMMENT '钱包id',
  `mch_id` char(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商户id',
  `wallet_currency` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '钱包币种',
  `wallet_address` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '钱包地址',
  `wallet_memo` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '钱包地址备注',
  `wallet_network` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '钱包网络',
  `wallet_state` int NULL DEFAULT NULL COMMENT '钱包状态',
  `create_by` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` int NULL DEFAULT NULL COMMENT '创建时间',
  `modify_by` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` int NULL DEFAULT NULL COMMENT '修改时间',
  `remark` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`wallet_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
