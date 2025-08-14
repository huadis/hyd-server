DROP TABLE IF EXISTS `hyd_sys_user`;
CREATE TABLE `hyd_sys_user` (
    `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username` varchar(180) DEFAULT NULL COMMENT '用户名',
    `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
    `gender` varchar(2) DEFAULT NULL COMMENT '性别',
    `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
    `email` varchar(180) DEFAULT NULL COMMENT '邮箱',
    `avatar_name` varchar(255) DEFAULT NULL COMMENT '头像地址',
    `avatar_path` varchar(255) DEFAULT NULL COMMENT '头像真实路径',
    `password` varchar(255) DEFAULT NULL COMMENT '密码',
    `enabled` bit(1) DEFAULT NULL COMMENT '状态：1启用、0禁用',
    `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
    `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
    `pwd_reset_time` datetime DEFAULT NULL COMMENT '修改密码的时间',
    `create_time` datetime DEFAULT NULL COMMENT '创建日期',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`user_id`) USING BTREE,
    UNIQUE KEY `uniq_email` (`email`) USING BTREE,
    UNIQUE KEY `uniq_username` (`username`) USING BTREE,
    KEY `idx_enabled` (`enabled`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='系统用户';
-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `hyd_sys_user` (`user_id`, `username`, `nick_name`, `gender`, `phone`, `email`, `avatar_name`, `avatar_path`, `password`, `enabled`, `create_by`, `update_by`, `pwd_reset_time`, `create_time`, `update_time`) VALUES (1, 'admin', '管理员', '男', '18888888888', '201507802@qq.com', 'avatar-20250122102642222.png', '/~/avatar/avatar-20250122102642222.png', '$2a$10$Egp1/gvFlt7zhlXVfEFw4OfWQCGPw0ClmMcc6FjTnvXNRVf9zdMRa', b'1', NULL, 'admin', '2025-05-03 16:38:31', '2025-08-23 09:11:56', '2025-07-22 10:26:42');
INSERT INTO `hyd_sys_user` (`user_id`, `username`, `nick_name`, `gender`, `phone`, `email`, `avatar_name`, `avatar_path`, `password`, `enabled`, `create_by`, `update_by`, `pwd_reset_time`, `create_time`, `update_time`) VALUES (2, 'test', '测试', '男', '19999999999', '231@qq.com', NULL, NULL, '$2a$10$4XcyudOYTSz6fue6KFNMHeUQnCX5jbBQypLEnGk1PmekXt5c95JcK', b'1', 'admin', 'admin', NULL, '2025-05-05 11:15:49', '2025-07-21 14:53:04');
COMMIT;

-- --------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE `hyd_result_user_channel` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `registerUserNum` varchar(255) COMMENT '注册用户',
    `realNameUserNum` varchar(255) COMMENT '实名用户',
    `receiveCouponUserNum` varchar(255) COMMENT '领券用户',
    `useCouponUserNum` varchar(255) COMMENT '用券用户',
    `orderUserNum` varchar(255) COMMENT '下单用户',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-用户来源渠道';

INSERT INTO `hyd_result_user_channel`
(`id`, `registerUserNum`, `realNameUserNum`, `receiveCouponUserNum`, `useCouponUserNum`, `orderUserNum`, `createdTime`, `updateTime`)
VALUES
    (
        1,
        '15000', -- 注册用户数，模拟一个较大基数
        '6532',  -- 实名用户数，与图表示例呼应
        '1101',  -- 领券用户数，与图表示例呼应
        '298',   -- 用券用户数，与图表示例呼应
        '132',   -- 下单用户数，与图表示例呼应
        NOW(),   -- 创建时间，取当前时间
        NOW()    -- 更新时间，取当前时间
    );

CREATE TABLE `hyd_result_user_channel_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `registerUserNum` varchar(255) COMMENT '注册用户',
    `realNameUserNum` varchar(255) COMMENT '实名用户',
    `receiveCouponUserNum` varchar(255) COMMENT '领券用户',
    `useCouponUserNum` varchar(255) COMMENT '用券用户',
    `orderUserNum` varchar(255) COMMENT '下单用户',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-用户来源渠道';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_user_register` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `month` varchar(255) COMMENT '月份',
    `userNum` varchar(255) COMMENT '数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-每月新增用户';

INSERT INTO `hyd_result_user_register`
(`id`, `month`, `userNum`, `createdTime`, `updateTime`)
VALUES
-- 1-12月新增用户数据，数值模拟季节性波动
(1, '1月', '1250', NOW(), NOW()),
(2, '2月', '1180', NOW(), NOW()),
(3, '3月', '1520', NOW(), NOW()),  -- 春季活动增长
(4, '4月', '1680', NOW(), NOW()),
(5, '5月', '1950', NOW(), NOW()),  -- 五一假期高峰
(6, '6月', '2100', NOW(), NOW()),  -- 暑期前预热
(7, '7月', '2800', NOW(), NOW()),  -- 暑期高峰
(8, '8月', '2650', NOW(), NOW()),  -- 暑期持续
(9, '9月', '1800', NOW(), NOW()),  -- 开学后回落
(10, '10月', '2050', NOW(), NOW()), -- 国庆假期增长
(11, '11月', '1720', NOW(), NOW()),
(12, '12月', '1600', NOW(), NOW()); -- 年底平稳

CREATE TABLE `hyd_result_user_register_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `month` varchar(255) COMMENT '月份',
    `userNum` varchar(255) COMMENT '数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-每月新增用户';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_user_sex` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sex` varchar(255) COMMENT '性别',
    `sexNum` varchar(255) COMMENT '数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-男女占比';

INSERT INTO `hyd_result_user_sex`
(`id`, `sex`, `sexNum`, `createdTime`, `updateTime`)
VALUES
-- 男性用户数据
(1, '男', '8560', NOW(), NOW()),
-- 女性用户数据
(2, '女', '6440', NOW(), NOW());

CREATE TABLE `hyd_result_user_sex_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sex` varchar(255) COMMENT '性别',
    `sexNum` varchar(255) COMMENT '数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-男女占比';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_user_age` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `under18Num` varchar(255) COMMENT '18岁以下',
    `bt18and25Num` varchar(255) COMMENT '18-25岁',
    `bt26and30Num` varchar(255) COMMENT '26-30岁',
    `bt31and35Num` varchar(255) COMMENT '31-35岁',
    `bt36and40Num` varchar(255) COMMENT '36-40岁',
    `bt41and45Num` varchar(255) COMMENT '41-45岁',
    `bt46and50Num` varchar(255) COMMENT '46-50岁',
    `over50Num` varchar(255) COMMENT '50岁以上',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-年龄占比';

INSERT INTO `hyd_result_user_age`
(`id`, `under18Num`, `bt18and25Num`, `bt26and30Num`, `bt31and35Num`, `bt36and40Num`, `bt41and45Num`, `bt46and50Num`, `over50Num`, `createdTime`, `updateTime`)
VALUES
-- 模拟各年龄段用户数量，年轻群体（18-35岁）占比更高
(1, '800',  -- 18岁以下
 '2200', -- 18-25岁（主力年轻群体）
 '1800', -- 26-30岁
 '1500', -- 31-35岁
 '1000', -- 36-40岁
 '700',  -- 41-45岁
 '500',  -- 46-50岁
 '300',  -- 50岁以上（占比最低）
 NOW(), NOW());

CREATE TABLE `hyd_result_user_age_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `under18Num` varchar(255) COMMENT '18岁以下',
    `bt18and25Num` varchar(255) COMMENT '18-25岁',
    `bt26and30Num` varchar(255) COMMENT '26-30岁',
    `bt31and35Num` varchar(255) COMMENT '31-35岁',
    `bt36and40Num` varchar(255) COMMENT '36-40岁',
    `bt41and45Num` varchar(255) COMMENT '41-45岁',
    `bt46and50Num` varchar(255) COMMENT '46-50岁',
    `over50Num` varchar(255) COMMENT '50岁以上',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-年龄占比';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_user_repurchase` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `1Num` varchar(255) COMMENT '一次下单',
    `bt2and5Num` varchar(255) COMMENT '2-5次',
    `over5Num` varchar(255) COMMENT '5次以上',
    `over10Num` varchar(255) COMMENT '10次以上',
    `over50Num` varchar(255) COMMENT '50次以上',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-复购率';

INSERT INTO `hyd_result_user_repurchase`
(`id`, `1Num`, `bt2and5Num`, `over5Num`, `over10Num`, `over50Num`, `createdTime`, `updateTime`)
VALUES
-- 模拟复购分布：多数用户仅下单1次，少数高频复购
(1, '6000',  -- 一次下单（占比最高）
 '2500',  -- 2-5次（中等复购）
 '1000',  -- 5次以上（高频复购）
 '300',   -- 10次以上（核心忠诚用户）
 '50',    -- 50次以上（超级忠诚用户，占比最低）
 NOW(), NOW());

CREATE TABLE `hyd_result_user_repurchase_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `1Num` varchar(255) COMMENT '一次下单',
    `bt2and5Num` varchar(255) COMMENT '2-5次',
    `over5Num` varchar(255) COMMENT '5次以上',
    `over10Num` varchar(255) COMMENT '10次以上',
    `over50Num` varchar(255) COMMENT '50次以上',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-复购率';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_order` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `month` varchar(255) COMMENT '月份',
    `orderNum` varchar(255) COMMENT '订单总数',
    `orderAmount` varchar(255) COMMENT '订单总金额',
    `couponAmount` varchar(255) COMMENT '消费券总金额',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-订单数量';

INSERT INTO `hyd_result_order`
(`id`, `month`, `orderNum`, `orderAmount`, `couponAmount`, `createdTime`, `updateTime`)
VALUES
-- 1月数据
(1, '1月', '2500', '2200', '500', NOW(), NOW()),
-- 2月数据
(2, '2月', '2300', '2000', '450', NOW(), NOW()),
-- 3月数据
(3, '3月', '2800', '2600', '600', NOW(), NOW()),
-- 4月数据
(4, '4月', '2400', '2100', '480', NOW(), NOW()),
-- 5月数据
(5, '5月', '2600', '2300', '520', NOW(), NOW()),
-- 6月数据
(6, '6月', '2700', '2400', '550', NOW(), NOW());

CREATE TABLE `hyd_result_order_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `month` varchar(255) COMMENT '月份',
    `orderNum` varchar(255) COMMENT '订单总数',
    `orderAmount` varchar(255) COMMENT '订单总金额',
    `couponAmount` varchar(255) COMMENT '消费券总金额',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-订单数量';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_order_month` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `month` varchar(255) COMMENT '月份',
    `orderNum` varchar(255) COMMENT '数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-订单趋势';

CREATE TABLE `hyd_result_order_month_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `month` varchar(255) COMMENT '月份',
    `orderNum` varchar(255) COMMENT '数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-订单趋势';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_order_sport` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `sportName` varchar(255) COMMENT '项目名称',
    `orderAmount` varchar(255) COMMENT '消费券订单金额',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-项目消费券订单金额Top5';

INSERT INTO `hyd_result_order_sport`
(`id`, `sportName`, `orderAmount`, `createdTime`, `updateTime`)
VALUES
-- 乒乓球
(1, '乒乓球', '30', NOW(), NOW()),
-- 滑冰
(2, '滑冰', '35', NOW(), NOW()),
-- 羽毛球
(3, '羽毛球', '20', NOW(), NOW()),
-- 篮球
(4, '篮球', '70', NOW(), NOW()),
-- 台球
(5, '台球', '10', NOW(), NOW());

CREATE TABLE `hyd_result_order_sport_history` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `sportName` varchar(255) COMMENT '项目名称',
    `orderAmount` varchar(255) COMMENT '消费券订单金额',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-项目消费券订单金额Top5';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_order_stadium` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `stadiumName` varchar(255) COMMENT '场馆名称',
    `orderAmount` varchar(255) COMMENT '消费券订单金额',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-场馆消费券订单金额Top5';

INSERT INTO `hyd_result_order_stadium`
(`id`, `stadiumName`, `orderAmount`, `createdTime`, `updateTime`)
VALUES
-- 汉风超越乒乓球...（名称按图表简化，可补全）
(1, '汉风超越乒乓球馆', '30', NOW(), NOW()),
-- 新洲区篮球馆
(2, '新洲区篮球馆', '35', NOW(), NOW()),
-- 武汉国际体育
(3, '武汉国际体育场馆', '20', NOW(), NOW()),
-- 新量乒乓球
(4, '新量乒乓球馆', '70', NOW(), NOW()),
-- 冰龙冰上运动
(5, '冰龙冰上运动馆', '10', NOW(), NOW());

CREATE TABLE `hyd_result_order_stadium_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `stadiumName` varchar(255) COMMENT '场馆名称',
    `orderAmount` varchar(255) COMMENT '消费券订单金额',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-场馆消费券订单金额Top5';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_stadium` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `onlineStadiumNum` varchar(255) COMMENT '在线场馆数量',
    `couponStadiumNum` varchar(255) COMMENT '消费券场馆数量',
    `socialStadiumNum` varchar(255) COMMENT '社会场馆数量',
    `publicStadiumNum` varchar(255) COMMENT '公共场馆数量',
    `useCouponStadiumNum` varchar(255) COMMENT '累计用券场馆数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-在线场馆数量';

CREATE TABLE `hyd_result_stadium_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `onlineStadiumNum` varchar(255) COMMENT '在线场馆数量',
    `couponStadiumNum` varchar(255) COMMENT '消费券场馆数量',
    `socialStadiumNum` varchar(255) COMMENT '社会场馆数量',
    `publicStadiumNum` varchar(255) COMMENT '公共场馆数量',
    `useCouponStadiumNum` varchar(255) COMMENT '累计用券场馆数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-在线场馆数量';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_stadium_district` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `district` varchar(255) COMMENT '所属区',
    `districtName` varchar(255) COMMENT '所属区名称',
    `couponStadiumNum` varchar(255) COMMENT '定点场馆数量',
    `publicStadiumNum` varchar(255) COMMENT '公共场馆数量',
    `socialStadiumNum` varchar(255) COMMENT '社会场馆数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-在线场馆各区情况';

INSERT INTO `hyd_result_stadium_district`
(`id`, `district`, `districtName`, `couponStadiumNum`, `publicStadiumNum`, `socialStadiumNum`, `createdTime`, `updateTime`)
VALUES
-- 江岸区
(1, '江岸区', '江岸区', '20', '15', '10', NOW(), NOW()),
-- 江汉区
(2, '江汉区', '江汉区', '30', '20', '15', NOW(), NOW()),
-- 硚口区
(3, '硚口区', '硚口区', '18', '12', '8', NOW(), NOW()),
-- 汉阳区
(4, '汉阳区', '汉阳区', '22', '16', '11', NOW(), NOW()),
-- 武昌区
(5, '武昌区', '武昌区', '25', '19', '13', NOW(), NOW()),
-- 青山区
(6, '青山区', '青山区', '16', '11', '7', NOW(), NOW()),
-- 洪山区
(7, '洪山区', '洪山区', '28', '21', '16', NOW(), NOW()),
-- 东西湖区
(8, '东西湖区', '东西湖区', '21', '15', '10', NOW(), NOW()),
-- 武汉开发区（汉南区 ）
(9, '武汉开发区（汉南区 ）', '武汉开发区（汉南区 ）', '19', '14', '9', NOW(), NOW()),
-- 蔡甸区
(10, '蔡甸区', '蔡甸区', '24', '18', '12', NOW(), NOW()),
-- 江夏区
(11, '江夏区', '江夏区', '17', '12', '8', NOW(), NOW()),
-- 黄陂区
(12, '黄陂区', '黄陂区', '26', '20', '14', NOW(), NOW()),
-- 新洲区
(13, '新洲区', '新洲区', '30', '23', '17', NOW(), NOW()),
-- 东湖新技术开发区
(14, '东湖新技术开发区', '东湖新技术开发区', '23', '17', '12', NOW(), NOW());

CREATE TABLE `hyd_result_stadium_district_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `district` varchar(255) COMMENT '所属区',
    `districtName` varchar(255) COMMENT '所属区名称',
    `couponStadiumNum` varchar(255) COMMENT '定点场馆数量',
    `publicStadiumNum` varchar(255) COMMENT '公共场馆数量',
    `socialStadiumNum` varchar(255) COMMENT '社会场馆数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-在线场馆各区情况';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_stadium_sport_coupon` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sportName` varchar(255) COMMENT '运动项目',
    `sportNum` varchar(255) COMMENT '项目数量',
    `useCountRate` varchar(255) COMMENT '用券占比',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-运动项目分布用券数占比';

CREATE TABLE `hyd_result_stadium_sport_coupon_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sportName` varchar(255) COMMENT '运动项目',
    `sportNum` varchar(255) COMMENT '项目数量',
    `useCountRate` varchar(255) COMMENT '用券占比',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-运动项目分布用券数占比';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_facility` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `facilityTypeName` varchar(255) COMMENT '设施类型名称',
    `facilityNum` varchar(255) COMMENT '设施数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-设施全貌';

INSERT INTO `hyd_result_facility`
(`facilityTypeName`, `facilityNum`, `createdTime`, `updateTime`)
VALUES
    ('全民健身路径', '80', NOW(), NOW()),
    ('体测站', '10', NOW(), NOW()),
    ('健身步道', '3', NOW(), NOW()),
    ('健身驿站（广场）', '1', NOW(), NOW()),
    ('少儿无动力设施', '1', NOW(), NOW()),
    ('健身房', '1', NOW(), NOW()),
    ('体育公园', '1', NOW(), NOW());

CREATE TABLE `hyd_result_facility_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `facilityTypeName` varchar(255) COMMENT '设施类型名称',
    `facilityNum` varchar(255) COMMENT '设施数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-设施全貌';

-- --------------------------------------------------------------------------------------------------------------------------------------------


CREATE TABLE `hyd_result_facility_district` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `facilityTypeName` varchar(255) COMMENT '设施类型名称',
    `districtName` varchar(255) COMMENT '区名称',
    `facilityNum` varchar(255) COMMENT '设施数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-设施各区分布';

INSERT INTO `hyd_result_facility_district` (`facilityTypeName`, `districtName`, `facilityNum`, `createdTime`, `updateTime`)
VALUES
    ('', '江岸区', '35', NOW(), NOW()),
    ('', '江汉区', '85', NOW(), NOW()),
    ('', '硚口区', '65', NOW(), NOW()),
    ('', '汉阳区', '55', NOW(), NOW()),
    ('', '武昌区', '65', NOW(), NOW()),
    ('', '青山区', '35', NOW(), NOW()),
    ('', '洪山区', '75', NOW(), NOW()),
    ('', '东西湖区', '65', NOW(), NOW()),
    ('', '武汉开发区（汉南区）', '55', NOW(), NOW()),
    ('', '蔡甸区', '80', NOW(), NOW()),
    ('', '江夏区', '35', NOW(), NOW()),
    ('', '黄陂区', '80', NOW(), NOW()),
    ('', '新洲区', '90', NOW(), NOW()),
    ('', '东湖新技术开发区', '55', NOW(), NOW());

CREATE TABLE `hyd_result_facility_district_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `facilityTypeName` varchar(255) COMMENT '设施类型名称',
    `districtName` varchar(255) COMMENT '区名称',
    `facilityNum` varchar(255) COMMENT '设施数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-设施各区分布';

-- --------------------------------------------------------------------------------------------------------------------------------------------


CREATE TABLE `hyd_result_stadium_map` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `resourceType` varchar(255) COMMENT '资源类型',
    `resourceName` varchar(255) COMMENT '资源名称',
    `resourceNum` varchar(255) COMMENT '资源数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-电子地图';

CREATE TABLE `hyd_result_stadium_map_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `resourceType` varchar(255) COMMENT '资源类型',
    `resourceName` varchar(255) COMMENT '资源名称',
    `resourceNum` varchar(255) COMMENT '资源数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-电子地图';

-- --------------------------------------------------------------------------------------------------------------------------------------------


CREATE TABLE `hyd_result_facility_year` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `quantity` varchar(255) COMMENT '健身点位数量',
    `yearInspectRate` varchar(255) COMMENT '本年巡检率',
    `yearInspectYes` varchar(255) COMMENT '本年已巡检',
    `yearInspectNo` varchar(255) COMMENT '本年待巡检',
    `yearRepairRate` varchar(255) COMMENT '本年维修完成率',
    `yearRepairYes` varchar(255) COMMENT '本年已维修',
    `yearRepairNo` varchar(255) COMMENT '本年待维修',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-健身点位年数据';

INSERT INTO `hyd_result_facility_year`
(`quantity`, `yearInspectRate`, `yearInspectYes`, `yearInspectNo`, `yearRepairRate`, `yearRepairYes`, `yearRepairNo`, `createdTime`, `updateTime`)
VALUES
    (
        '28739', -- 健身点位数量
        '96%',   -- 本年巡检率
        '28739', -- 本年已巡检
        '1101',  -- 本年待巡检
        '91%',   -- 本年维修完成率
        '249',   -- 本年已维修
        '19',    -- 本年待维修
        NOW(),   -- 创建时间，取当前时间
        NOW()    -- 更新时间，取当前时间
    );

CREATE TABLE `hyd_result_facility_year_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `quantity` varchar(255) COMMENT '健身点位数量',
    `yearInspectRate` varchar(255) COMMENT '本年巡检率',
    `yearInspectYes` varchar(255) COMMENT '本年已巡检',
    `yearInspectNo` varchar(255) COMMENT '本年待巡检',
    `yearRepairRate` varchar(255) COMMENT '本年维修完成率',
    `yearRepairYes` varchar(255) COMMENT '本年已维修',
    `yearRepairNo` varchar(255) COMMENT '本年待维修',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-健身点位年数据';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_facility_district_month` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `districtName` varchar(255) COMMENT '区名称',
    `monthInspectYes` varchar(255) COMMENT '本月已巡检',
    `monthInspectNo` varchar(255) COMMENT '本月待巡检',
    `monthRepairYes` varchar(255) COMMENT '本月已维修',
    `monthRepairNo` varchar(255) COMMENT '本月待维修',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-设施各区月数据';

INSERT INTO `hyd_result_facility_district_month`
(`districtName`, `monthInspectYes`, `monthInspectNo`, `monthRepairYes`, `monthRepairNo`, `createdTime`, `updateTime`)
VALUES
    ('江岸区', '500', '50', '450', '30', NOW(), NOW()),
    ('江汉区', '520', '40', '480', '25', NOW(), NOW()),
    ('硚口区', '220', '30', '200', '20', NOW(), NOW()),
    ('汉阳区', '300', '45', '280', '22', NOW(), NOW()),
    ('武昌区', '240', '35', '210', '18', NOW(), NOW()),
    ('青山区', '230', '32', '205', '16', NOW(), NOW()),
    ('武汉开发区（汉南区 ）', '320', '42', '300', '28', NOW(), NOW()),
    ('洪山区', '210', '33', '190', '15', NOW(), NOW()),
    ('东西湖区', '225', '31', '202', '17', NOW(), NOW()),
    ('蔡甸区', '235', '34', '215', '19', NOW(), NOW()),
    ('江夏区', '510', '45', '470', '26', NOW(), NOW()),
    ('黄陂区', '250', '36', '230', '20', NOW(), NOW()),
    ('东湖新技术开发区', '245', '33', '220', '18', NOW(), NOW()),
    ('新洲区', '310', '40', '290', '26', NOW(), NOW());

CREATE TABLE `hyd_result_facility_district_month_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `districtName` varchar(255) COMMENT '区名称',
    `monthInspectYes` varchar(255) COMMENT '本月已巡检',
    `monthInspectNo` varchar(255) COMMENT '本月待巡检',
    `monthRepairYes` varchar(255) COMMENT '本月已维修',
    `monthRepairNo` varchar(255) COMMENT '本月待维修',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-设施各区月数据';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_facility_inspect` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `code` varchar(255) COMMENT '设施点编号',
    `districtName` varchar(255) COMMENT '所属区县',
    `streetName` varchar(255) COMMENT '所属街道',
    `location` varchar(255) COMMENT '安装点名称',
    `type` varchar(255) COMMENT '巡检单类型',
    `inspector` varchar(255) COMMENT '巡检人名称',
    `status` varchar(255) COMMENT '巡检完成状态',
    `completeTime` varchar(255) COMMENT '巡检完成时间',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-巡检动态';

INSERT INTO `hyd_result_facility_inspect`
(`code`, `districtName`, `streetName`, `location`, `type`, `inspector`, `status`, `completeTime`, `createdTime`, `updateTime`)
VALUES
    ('js12123344', '汉阳区', 'XX街道', 'XX社区新城西门', '派单', '丁一', '已巡检', '2025-07-10 12:45:12', NOW(), NOW()),
    ('js12123344', '汉阳区', 'XX街道', 'XX社区新城西门', '派单', '丁一', '待巡检', '2025-07-10 12:45:12', NOW(), NOW()),
    ('js12123344', '汉阳区', 'XX街道', 'XX社区新城西门', '派单', '丁一', '已巡检', '2025-07-10 12:45:12', NOW(), NOW()),
    ('js12123344', '武昌区', 'XX街道', 'XX社区新城西门', '派单', '丁一', '已巡检', '2025-07-10 12:45:12', NOW(), NOW()),
    ('js12123344', '硚口区', 'XX街道', 'XX社区新城西门', '派单', '丁一', '已巡检', '2025-07-10 12:45:12', NOW(), NOW()),
    ('js12123344', '汉阳区', 'XX街道', 'XX社区新城西门', '派单', '丁一', '已巡检', '2025-07-10 12:45:12', NOW(), NOW()),
    ('js12123344', '江岸区', 'XX街道', 'XX社区新城西门', '派单', '丁一', '已巡检', '2025-07-10 12:45:12', NOW(), NOW());

CREATE TABLE `hyd_result_facility_inspect_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `code` varchar(255) COMMENT '设施点编号',
    `districtName` varchar(255) COMMENT '所属区县',
    `streetName` varchar(255) COMMENT '所属街道',
    `location` varchar(255) COMMENT '安装点名称',
    `type` varchar(255) COMMENT '巡检单类型',
    `inspector` varchar(255) COMMENT '巡检人名称',
    `status` varchar(255) COMMENT '巡检完成状态',
    `completeTime` varchar(255) COMMENT '巡检完成时间',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-巡检动态';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_coupon_amount` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sendAmount` varchar(255) COMMENT '发放金额',
    `receiveCount` varchar(255) COMMENT '领券人次',
    `usedCount` varchar(255) COMMENT '用券人次',
    `usedRatio` varchar(255) COMMENT '用券资金占比',
    `useCouponAmount` varchar(255) COMMENT '用券金额',
    `orderAmount` varchar(255) COMMENT '订单金额',
    `orderRatio` varchar(255) COMMENT '拉动消费比',
    `activityId` varchar(255) COMMENT '活动id',
    `activityName` varchar(255) COMMENT '活动名称',
    `groupId` varchar(255) COMMENT '分组id',
    `groupName` varchar(255) COMMENT '分组名称',
    `type` varchar(255) COMMENT '类型',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-消费券总金额';

CREATE TABLE `hyd_result_coupon_amount_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sendAmount` varchar(255) COMMENT '发放金额',
    `receiveCount` varchar(255) COMMENT '领券人次',
    `usedCount` varchar(255) COMMENT '用券人次',
    `usedRatio` varchar(255) COMMENT '用券资金占比',
    `useCouponAmount` varchar(255) COMMENT '用券金额',
    `orderAmount` varchar(255) COMMENT '订单金额',
    `orderRatio` varchar(255) COMMENT '拉动消费比',
    `activityId` varchar(255) COMMENT '活动id',
    `activityName` varchar(255) COMMENT '活动名称',
    `groupId` varchar(255) COMMENT '分组id',
    `groupName` varchar(255) COMMENT '分组名称',
    `type` varchar(255) COMMENT '类型',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-消费券总金额';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_stock` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `stockName` varchar(255) COMMENT '券名称',
    `receiveNum` varchar(255) COMMENT '领券数',
    `useNum` varchar(255) COMMENT '用券数',
    `useRate` varchar(255) COMMENT '用券率',
    `useCouponAmount` varchar(255) COMMENT '用券金额',
    `orderAmount` varchar(255) COMMENT '订单金额',
    `orderRate` varchar(255) COMMENT '拉动消费比',
    `activityId` varchar(255) COMMENT '活动id',
    `activityName` varchar(255) COMMENT '活动名称',
    `groupId` varchar(255) COMMENT '分组id',
    `groupName` varchar(255) COMMENT '分组名称',
    `type` varchar(255) COMMENT '类型',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-消费券领用券';

CREATE TABLE `hyd_result_stock_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `stockName` varchar(255) COMMENT '券名称',
    `receiveNum` varchar(255) COMMENT '领券数',
    `useNum` varchar(255) COMMENT '用券数',
    `useRate` varchar(255) COMMENT '用券率',
    `useCouponAmount` varchar(255) COMMENT '用券金额',
    `orderAmount` varchar(255) COMMENT '订单金额',
    `orderRate` varchar(255) COMMENT '拉动消费比',
    `activityId` varchar(255) COMMENT '活动id',
    `activityName` varchar(255) COMMENT '活动名称',
    `groupId` varchar(255) COMMENT '分组id',
    `groupName` varchar(255) COMMENT '分组名称',
    `type` varchar(255) COMMENT '类型',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-消费券领用券';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_coupon_user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `receiveCouponNum` varchar(255) COMMENT '领券人数',
    `useCouponNum` varchar(255) COMMENT '用券人数',
    `maleNum` varchar(255) COMMENT '男性人数',
    `femaleNum` varchar(255) COMMENT '女性人数',
    `activityId` varchar(255) COMMENT '活动id',
    `activityName` varchar(255) COMMENT '活动名称',
    `groupId` varchar(255) COMMENT '分组id',
    `groupName` varchar(255) COMMENT '分组名称',
    `type` varchar(255) COMMENT '类型',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-券用户分析';

CREATE TABLE `hyd_result_coupon_user_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `receiveCouponNum` varchar(255) COMMENT '领券人数',
    `useCouponNum` varchar(255) COMMENT '用券人数',
    `maleNum` varchar(255) COMMENT '男性人数',
    `femaleNum` varchar(255) COMMENT '女性人数',
    `activityId` varchar(255) COMMENT '活动id',
    `activityName` varchar(255) COMMENT '活动名称',
    `groupId` varchar(255) COMMENT '分组id',
    `groupName` varchar(255) COMMENT '分组名称',
    `type` varchar(255) COMMENT '类型',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-券用户分析';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_coupon_user_age` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `under25Num` varchar(255) COMMENT '25岁以下',
    `bt26and30Num` varchar(255) COMMENT '26-30岁',
    `bt31and35Num` varchar(255) COMMENT '31-35岁',
    `bt36and40Num` varchar(255) COMMENT '36-40岁',
    `bt41and45Num` varchar(255) COMMENT '41-45岁',
    `bt46and50Num` varchar(255) COMMENT '46-50岁',
    `bt51and60Num` varchar(255) COMMENT '51-60岁',
    `over60Num` varchar(255) COMMENT '60岁以上',
    `activityId` varchar(255) COMMENT '活动id',
    `activityName` varchar(255) COMMENT '活动名称',
    `groupId` varchar(255) COMMENT '分组id',
    `groupName` varchar(255) COMMENT '分组名称',
    `type` varchar(255) COMMENT '类型',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-券用户年龄分布';

CREATE TABLE `hyd_result_coupon_user_age_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `under25Num` varchar(255) COMMENT '25岁以下',
    `bt26and30Num` varchar(255) COMMENT '26-30岁',
    `bt31and35Num` varchar(255) COMMENT '31-35岁',
    `bt36and40Num` varchar(255) COMMENT '36-40岁',
    `bt41and45Num` varchar(255) COMMENT '41-45岁',
    `bt46and50Num` varchar(255) COMMENT '46-50岁',
    `bt51and60Num` varchar(255) COMMENT '51-60岁',
    `over60Num` varchar(255) COMMENT '60岁以上',
    `activityId` varchar(255) COMMENT '活动id',
    `activityName` varchar(255) COMMENT '活动名称',
    `groupId` varchar(255) COMMENT '分组id',
    `groupName` varchar(255) COMMENT '分组名称',
    `type` varchar(255) COMMENT '类型',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-券用户年龄分布';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_coupon_stadium_top` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `stadiumName` varchar(255) COMMENT '场馆名称',
    `couponAmount` varchar(255) COMMENT '消费券金额',
    `activityId` varchar(255) COMMENT '活动id',
    `activityName` varchar(255) COMMENT '活动名称',
    `groupId` varchar(255) COMMENT '分组id',
    `groupName` varchar(255) COMMENT '分组名称',
    `type` varchar(255) COMMENT '类型',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表-驾驶舱-消费券场馆预订 top';

INSERT INTO `hyd_result_coupon_stadium_top`
(`id`, `stadiumName`, `couponAmount`, `createdTime`, `updateTime`)
VALUES
-- 汉风超越乒乓球...（名称按图表简化，可补全）
(1, '汉风超越乒乓球馆', '30', NOW(), NOW()),
-- 新洲区篮球馆
(2, '新洲区篮球馆', '35', NOW(), NOW()),
-- 武汉国际体育
(3, '武汉国际体育场馆', '20', NOW(), NOW()),
-- 新量乒乓球
(4, '新量乒乓球馆', '70', NOW(), NOW()),
-- 冰龙冰上运动
(5, '冰龙冰上运动馆', '10', NOW(), NOW());

CREATE TABLE `hyd_result_coupon_stadium_top_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `stadiumName` varchar(255) COMMENT '场馆名称',
    `couponAmount` varchar(255) COMMENT '消费券金额',
    `activityId` varchar(255) COMMENT '活动id',
    `activityName` varchar(255) COMMENT '活动名称',
    `groupId` varchar(255) COMMENT '分组id',
    `groupName` varchar(255) COMMENT '分组名称',
    `type` varchar(255) COMMENT '类型',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='结果表（历史）-驾驶舱-消费券场馆预订 top';
-- --------------------------------------------------------------------------------------------------------------------------------------------

-- ------------------------------------------ 体育指导员 --------------------------------------------
CREATE TABLE `hyd_instructor_info` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增唯一标识',
    `name` varchar(255) NOT NULL COMMENT '姓名',
    `gender` varchar(10) NOT NULL COMMENT '性别（男/女）',
    `birthDate` date COMMENT '出生日期，格式如1980-12-12',
    `serviceProject` varchar(255) COMMENT '服务项目',
    `level` varchar(255) COMMENT '级别',
    `certifyTime` date COMMENT '获证时间，格式如2010-10-20',
    `region` varchar(255) COMMENT '所在地区',
    `remark` varchar(255) COMMENT '备注',
    `uploadTime` date COMMENT '上传时间，自动填充',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育指导员-汇总';

CREATE TABLE `hyd_instructor_info_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增唯一标识',
    `name` varchar(255) NOT NULL COMMENT '姓名',
    `gender` varchar(10) NOT NULL COMMENT '性别（男/女）',
    `birthDate` date COMMENT '出生日期，格式如1980-12-12',
    `serviceProject` varchar(255) COMMENT '服务项目',
    `level` varchar(255) COMMENT '级别',
    `certifyTime` date COMMENT '获证时间，格式如2010-10-20',
    `region` varchar(255) COMMENT '所在地区',
    `remark` varchar(255) COMMENT '备注',
    `uploadTime` date COMMENT '上传时间，自动填充',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育指导员（历史）-汇总';

CREATE TABLE `hyd_instructor_age_stats` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度，如2024',
    `ageInterval` varchar(50) NOT NULL COMMENT '年龄区间，如20岁以下、20-30岁等',
    `personCount` int(11) COMMENT '对应年龄区间的人数',
    `dataSource` varchar(255) COMMENT '数据来源/备注',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育指导员-年龄统计明细表';

CREATE TABLE `hyd_instructor_age_stats_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度，如2024',
    `ageInterval` varchar(50) NOT NULL COMMENT '年龄区间，如20岁以下、20-30岁等',
    `personCount` int(11) COMMENT '对应年龄区间的人数',
    `dataSource` varchar(255) COMMENT '数据来源/备注',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育指导员（历史）-年龄统计明细表';

CREATE TABLE `hyd_instructor_age_growth` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增唯一标识',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度，如2024',
    `ageInterval` varchar(50) NOT NULL COMMENT '年龄区间，如20岁以下、20-30岁等',
    `personCount` int(11) COMMENT '对应年龄区间的人数',
    `growthRate` decimal(5,2) COMMENT '增长率（%），保留两位小数',
    `dataSource` varchar(255) COMMENT '数据来源/备注信息',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育指导员-人数增长统计明细表';

CREATE TABLE `hyd_instructor_age_growth_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增唯一标识',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度，如2024',
    `ageInterval` varchar(50) NOT NULL COMMENT '年龄区间，如20岁以下、20-30岁等',
    `personCount` int(11) COMMENT '对应年龄区间的人数',
    `growthRate` decimal(5,2) COMMENT '增长率（%），保留两位小数',
    `dataSource` varchar(255) COMMENT '数据来源/备注信息',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育指导员（历史）-人数增长统计明细表';

-- ------------------------------------------ 体育产业 --------------------------------------------
CREATE TABLE `hyd_excel_industry_core_indicators` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增唯一标识',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度',
    `totalOutputValue` decimal(10,2) COMMENT '体育产业总产值（亿）',
    `addedValue` decimal(10,2) COMMENT '产业增加值（亿）',
    `marketEntityCount` int(11) COMMENT '体育市场主体总量（家）',
    `employeeCount` decimal(5,1) COMMENT '从业人员数量（万）',
    `perCapitaSportsConsumption` int(11) COMMENT '人均体育消费（元）',
    `perCapitaSportsArea` decimal(5,2) COMMENT '人均体育场地面积（㎡）',
    `majorProjectContract` decimal(10,2) COMMENT '重大项目签约（亿）',
    `remark` varchar(255) COMMENT '备注',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业-核心指标总表';

CREATE TABLE `hyd_excel_industry_core_indicators_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增唯一标识',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度',
    `totalOutputValue` decimal(10,2) COMMENT '体育产业总产值（亿）',
    `addedValue` decimal(10,2) COMMENT '产业增加值（亿）',
    `marketEntityCount` int(11) COMMENT '体育市场主体总量（家）',
    `employeeCount` decimal(5,1) COMMENT '从业人员数量（万）',
    `perCapitaSportsConsumption` int(11) COMMENT '人均体育消费（元）',
    `perCapitaSportsArea` decimal(5,2) COMMENT '人均体育场地面积（㎡）',
    `majorProjectContract` decimal(10,2) COMMENT '重大项目签约（亿）',
    `remark` varchar(255) COMMENT '备注',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业（历史）-核心指标总表';

CREATE TABLE `hyd_excel_industry_scale_trend` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度',
    `totalOutputValue` decimal(10, 2) COMMENT '总产值（亿元）',
    `growthRate` decimal(5, 2) COMMENT '增长率（%）',
    `dataSource` varchar(255) COMMENT '数据来源/备注',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业-总规模（年度趋势）表';

CREATE TABLE `hyd_excel_industry_scale_trend_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度',
    `totalOutputValue` decimal(10, 2) COMMENT '总产值（亿元）',
    `growthRate` decimal(5, 2) COMMENT '增长率（%）',
    `dataSource` varchar(255) COMMENT '数据来源/备注',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业（历史）-总规模（年度趋势）表';

CREATE TABLE `hyd_excel_industry_entity_count_ratio` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度',
    `entityType` varchar(255) NOT NULL COMMENT '市场主体类型（如体育管理活动 / 竞赛表演等）',
    `proportion` decimal(5, 2) COMMENT '占比（%）',
    `dataSource` varchar(255) COMMENT '数据来源/备注',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业-市场主体数量（分类占比）表';

CREATE TABLE `hyd_excel_industry_entity_count_ratio_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度',
    `entityType` varchar(255) NOT NULL COMMENT '市场主体类型（如体育管理活动 / 竞赛表演等）',
    `proportion` decimal(5, 2) COMMENT '占比（%）',
    `dataSource` varchar(255) COMMENT '数据来源/备注',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业（历史）-市场主体数量（分类占比）表';

CREATE TABLE `hyd_excel_industry_growth_value_trend` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度',
    `totalOutputValue` decimal(10, 2) COMMENT '总产值（亿元）',
    `growthRate` decimal(5, 2) COMMENT '增长率（%）',
    `dataSource` varchar(255) COMMENT '数据来源/备注',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业-总增速和增加值（年度趋势）表';

CREATE TABLE `hyd_excel_industry_growth_value_trend_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度',
    `totalOutputValue` decimal(10, 2) COMMENT '总产值（亿元）',
    `growthRate` decimal(5, 2) COMMENT '增长率（%）',
    `dataSource` varchar(255) COMMENT '数据来源/备注',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业（历史）-总增速和增加值（年度趋势）表';

CREATE TABLE `hyd_excel_industry_training_participation_rate` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度',
    `entityType` varchar(255) NOT NULL COMMENT '培训项目（帆船、皮筏艇 / 羽毛球等）',
    `growthRate` decimal(5, 2) COMMENT '参与率（%）',
    `dataSource` varchar(255) COMMENT '数据来源/备注',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业-居民体育培训项目参与率表';

CREATE TABLE `hyd_excel_industry_training_participation_rate_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度',
    `entityType` varchar(255) NOT NULL COMMENT '培训项目（帆船、皮筏艇 / 羽毛球等）',
    `growthRate` decimal(5, 2) COMMENT '参与率（%）',
    `dataSource` varchar(255) COMMENT '数据来源/备注',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业（历史）-居民体育培训项目参与率表';

CREATE TABLE `hyd_excel_industry_goods_purchase_rate` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度',
    `entityType` varchar(255) NOT NULL COMMENT '体育用品类型（运动鞋 / 运动服饰等）',
    `growthRate` decimal(5, 2) COMMENT '购买率（%）',
    `dataSource` varchar(255) COMMENT '数据来源/备注',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业-居民体育用品购买率表';

CREATE TABLE `hyd_excel_industry_goods_purchase_rate_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度',
    `entityType` varchar(255) NOT NULL COMMENT '体育用品类型（运动鞋 / 运动服饰等）',
    `growthRate` decimal(5, 2) COMMENT '购买率（%）',
    `dataSource` varchar(255) COMMENT '数据来源/备注',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业（历史）-居民体育用品购买率表';

CREATE TABLE `hyd_excel_industry_employee_count` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度',
    `entityType` varchar(255) NOT NULL COMMENT '从业类型（体育管理活动 / 竞赛表演等）',
    `personCount` int(11) COMMENT '人数（人）',
    `dataSource` varchar(255) COMMENT '数据来源/备注',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业-从业人员数量（分类统计）表';

CREATE TABLE `hyd_excel_industry_employee_count_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `statisticalYear` int(4) NOT NULL COMMENT '统计年度',
    `entityType` varchar(255) NOT NULL COMMENT '从业类型（体育管理活动 / 竞赛表演等）',
    `personCount` int(11) COMMENT '人数（人）',
    `dataSource` varchar(255) COMMENT '数据来源/备注',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业（历史）-从业人员数量（分类统计）表';

-- ------------------------------------------ 大众赛事 --------------------------------------------

CREATE TABLE `hyd_excel_public_events` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sequence` bigint(20) NOT NULL COMMENT '序号',
    `district` varchar(255) COMMENT '区属，如江夏区',
    `eventName` varchar(255) COMMENT '赛事名称',
    `sportItem` varchar(255) COMMENT '运动项目',
    `hostUnit` varchar(255) COMMENT '主办单位',
    `organizerUnit` varchar(255) COMMENT '承办单位',
    `eventYear` varchar(255) COMMENT '赛事年份',
    `eventMonth` varchar(255) COMMENT '赛事月份',
    `eventDate` date COMMENT '赛事日期',
    `eventLocation` varchar(255) COMMENT '赛事活动地点',
    `participantCount` int(11) COMMENT '参赛规模（人数）',
    `eventLevel` varchar(255) COMMENT '赛事级别（国际级、国家级、省级、市级、区级、街道级、社区级）',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='大众赛事-体育赛事信息表';

CREATE TABLE `hyd_excel_public_events_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sequence` bigint(20) NOT NULL COMMENT '序号',
    `district` varchar(255) COMMENT '区属，如江夏区',
    `eventName` varchar(255) COMMENT '赛事名称',
    `sportItem` varchar(255) COMMENT '运动项目',
    `hostUnit` varchar(255) COMMENT '主办单位',
    `organizerUnit` varchar(255) COMMENT '承办单位',
    `eventYear` varchar(255) COMMENT '赛事年份',
    `eventMonth` varchar(255) COMMENT '赛事月份',
    `eventDate` date COMMENT '赛事日期',
    `eventLocation` varchar(255) COMMENT '赛事活动地点',
    `participantCount` int(11) COMMENT '参赛规模（人数）',
    `eventLevel` varchar(255) COMMENT '赛事级别（国际级、国家级、省级、市级、区级、街道级、社区级）',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='大众赛事（历史）-体育赛事信息表';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_tenant` (
    `id` VARCHAR(32) NOT NULL COMMENT '主键id',
    `tenantName` VARCHAR(256) COMMENT '名称',
    `shortName` VARCHAR(128) COMMENT '简称',
    `province` VARCHAR(32) COMMENT '省编码',
    `city` VARCHAR(32) COMMENT '市编码',
    `district` VARCHAR(32) COMMENT '区编码',
    `provinceName` VARCHAR(33) COMMENT '省名称',
    `cityName` VARCHAR(34) COMMENT '市名称',
    `districtName` VARCHAR(35) COMMENT '区名称',
    `address` VARCHAR(256) COMMENT '地址',
    `legalPerson` VARCHAR(128) COMMENT '法人代表',
    `phone` VARCHAR(32) COMMENT '电话',
    `businessLicenseling` VARCHAR(256) COMMENT '营业执照',
    `tenantIcon` VARCHAR(256) COMMENT '公司照片',
    `createdTime` DATETIME COMMENT '创建时间',
    `updatedTime` DATETIME COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='组织机构表';

CREATE TABLE `hyd_tenant_history` (
    `id` VARCHAR(32) NOT NULL COMMENT '主键id',
    `tenantName` VARCHAR(256) COMMENT '名称',
    `shortName` VARCHAR(128) COMMENT '简称',
    `province` VARCHAR(32) COMMENT '省编码',
    `city` VARCHAR(32) COMMENT '市编码',
    `district` VARCHAR(32) COMMENT '区编码',
    `provinceName` VARCHAR(33) COMMENT '省名称',
    `cityName` VARCHAR(34) COMMENT '市名称',
    `districtName` VARCHAR(35) COMMENT '区名称',
    `address` VARCHAR(256) COMMENT '地址',
    `legalPerson` VARCHAR(128) COMMENT '法人代表',
    `phone` VARCHAR(32) COMMENT '电话',
    `businessLicenseling` VARCHAR(256) COMMENT '营业执照',
    `tenantIcon` VARCHAR(256) COMMENT '公司照片',
    `createdTime` DATETIME COMMENT '创建时间',
    `updatedTime` DATETIME COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='组织机构表（历史）';

CREATE TABLE `hyd_stadium` (
    `id` VARCHAR(32) NOT NULL COMMENT '主键id',
    `tenantId` VARCHAR(32) COMMENT '组织id',
    `stadiumName` VARCHAR(128) COMMENT '名称',
    `shortName` VARCHAR(32) COMMENT '简称',
    `province` VARCHAR(32) COMMENT '省编码',
    `city` VARCHAR(32) COMMENT '市编码',
    `district` VARCHAR(32) COMMENT '区编码',
    `provinceName` VARCHAR(33) COMMENT '省名称',
    `cityName` VARCHAR(34) COMMENT '市名称',
    `districtName` VARCHAR(35) COMMENT '区名称',
    `street` VARCHAR(128) COMMENT '街道',
    `address` VARCHAR(128) COMMENT '地址',
    `location` GEOMETRY COMMENT '经纬度',
    `area` VARCHAR(64) COMMENT '面积',
    `holdNum` INT COMMENT '可容纳人数',
    `contactName` VARCHAR(32) COMMENT '联系人',
    `telephone` VARCHAR(32) COMMENT '联系电话',
    `online` TINYINT COMMENT '是否上线（0-未上线，1-上线）',
    `investmentNature` TINYINT COMMENT '场馆性质（0-社会的，1-公共的）',
    `disabled` TINYINT COMMENT '状态（0-启用，1-停用）',
    `createdTime` DATETIME COMMENT '创建时间',
    `updatedTime` DATETIME COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='培训场馆表';

CREATE TABLE `hyd_stadium_history` (
    `id` VARCHAR(32) NOT NULL COMMENT '主键id',
    `tenantId` VARCHAR(32) COMMENT '组织id',
    `stadiumName` VARCHAR(128) COMMENT '名称',
    `shortName` VARCHAR(32) COMMENT '简称',
    `province` VARCHAR(32) COMMENT '省编码',
    `city` VARCHAR(32) COMMENT '市编码',
    `district` VARCHAR(32) COMMENT '区编码',
    `provinceName` VARCHAR(33) COMMENT '省名称',
    `cityName` VARCHAR(34) COMMENT '市名称',
    `districtName` VARCHAR(35) COMMENT '区名称',
    `street` VARCHAR(128) COMMENT '街道',
    `address` VARCHAR(128) COMMENT '地址',
    `location` GEOMETRY COMMENT '经纬度',
    `area` VARCHAR(64) COMMENT '面积',
    `holdNum` INT COMMENT '可容纳人数',
    `contactName` VARCHAR(32) COMMENT '联系人',
    `telephone` VARCHAR(32) COMMENT '联系电话',
    `online` TINYINT COMMENT '是否上线（0-未上线，1-上线）',
    `investmentNature` TINYINT COMMENT '场馆性质（0-社会的，1-公共的）',
    `disabled` TINYINT COMMENT '状态（0-启用，1-停用）',
    `createdTime` DATETIME COMMENT '创建时间',
    `updatedTime` DATETIME COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='培训场馆表（历史）';

CREATE TABLE `hyd_stadium_item` (
    `id` VARCHAR(32) NOT NULL COMMENT '主键id',
    `tenantId` VARCHAR(32) COMMENT '组织id',
    `stadiumId` VARCHAR(32) COMMENT '场馆id',
    `stadiumItemName` VARCHAR(32) COMMENT '项目名称',
    `sportCode` VARCHAR(10) COMMENT '项目类型编码',
    `sportName` VARCHAR(32) COMMENT '项目类型名称',
    `disabled` TINYINT COMMENT '状态（0-启用，1-停用 ）',
    `createdTime` DATETIME COMMENT '创建时间',
    `updatedTime` DATETIME COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆培训项目表';

CREATE TABLE `hyd_stadium_item_history` (
    `id` VARCHAR(32) NOT NULL COMMENT '主键id',
    `tenantId` VARCHAR(32) COMMENT '组织id',
    `stadiumId` VARCHAR(32) COMMENT '场馆id',
    `stadiumItemName` VARCHAR(32) COMMENT '项目名称',
    `sportCode` VARCHAR(10) COMMENT '项目类型编码',
    `sportName` VARCHAR(32) COMMENT '项目类型名称',
    `disabled` TINYINT COMMENT '状态（0-启用，1-停用 ）',
    `createdTime` DATETIME COMMENT '创建时间',
    `updatedTime` DATETIME COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆培训项目表（历史）';

CREATE TABLE `hyd_training_activity` (
    `id` VARCHAR(32) NOT NULL COMMENT '主键id',
    `activityName` VARCHAR(64) COMMENT '活动名称',
    `organizingCompany` VARCHAR(64) COMMENT '主办单位',
    `startTime` DATETIME COMMENT '活动开始时间',
    `endTime` DATETIME COMMENT '活动结束时间',
    `open` TINYINT COMMENT '启用开关（0-关闭，1-打开 ）',
    `disabled` TINYINT COMMENT '状态（0-启用，1-停用 ）',
    `createdTime` DATETIME COMMENT '创建时间',
    `updatedTime` DATETIME COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='培训活动表';

CREATE TABLE `hyd_training_activity_history` (
    `id` VARCHAR(32) NOT NULL COMMENT '主键id',
    `activityName` VARCHAR(64) COMMENT '活动名称',
    `organizingCompany` VARCHAR(64) COMMENT '主办单位',
    `startTime` DATETIME COMMENT '活动开始时间',
    `endTime` DATETIME COMMENT '活动结束时间',
    `open` TINYINT COMMENT '启用开关（0-关闭，1-打开 ）',
    `disabled` TINYINT COMMENT '状态（0-启用，1-停用 ）',
    `createdTime` DATETIME COMMENT '创建时间',
    `updatedTime` DATETIME COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='培训活动表（历史）';

CREATE TABLE `hyd_training_activity_item` (
    `id` VARCHAR(32) NOT NULL COMMENT '主键id',
    `activityId` VARCHAR(32) COMMENT '活动id',
    `sportCode` VARCHAR(10) COMMENT '项目类型编码',
    `sportName` VARCHAR(32) COMMENT '项目类型名称',
    `ageStartLimit` VARCHAR(32) COMMENT '培训对象年龄起始限制',
    `ageEndLimit` VARCHAR(32) COMMENT '培训对象年龄结束限制',
    `disabled` TINYINT COMMENT '状态（0-启用，1-停用 ）',
    `createdTime` DATETIME COMMENT '创建时间',
    `updatedTime` DATETIME COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='培训活动支持的项目表';

CREATE TABLE `hyd_training_activity_item_history` (
    `id` VARCHAR(32) NOT NULL COMMENT '主键id',
    `activityId` VARCHAR(32) COMMENT '活动id',
    `sportCode` VARCHAR(10) COMMENT '项目类型编码',
    `sportName` VARCHAR(32) COMMENT '项目类型名称',
    `ageStartLimit` VARCHAR(32) COMMENT '培训对象年龄起始限制',
    `ageEndLimit` VARCHAR(32) COMMENT '培训对象年龄结束限制',
    `disabled` TINYINT COMMENT '状态（0-启用，1-停用 ）',
    `createdTime` DATETIME COMMENT '创建时间',
    `updatedTime` DATETIME COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='培训活动支持的项目表（历史）';

CREATE TABLE `hyd_training_activity_item_stadium` (
    `id` VARCHAR(32) NOT NULL COMMENT '主键id',
    `activityId` VARCHAR(32) COMMENT '活动id',
    `stadiumId` VARCHAR(32) COMMENT '场馆id',
    `sportCode` VARCHAR(10) COMMENT '项目类型编码',
    `sportName` VARCHAR(32) COMMENT '项目类型名称',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='培训活动场馆支持的项目表';

CREATE TABLE `hyd_training_activity_item_stadium_history` (
    `id` VARCHAR(32) NOT NULL COMMENT '主键id',
    `activityId` VARCHAR(32) COMMENT '活动id',
    `stadiumId` VARCHAR(32) COMMENT '场馆id',
    `sportCode` VARCHAR(10) COMMENT '项目类型编码',
    `sportName` VARCHAR(32) COMMENT '项目类型名称',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='培训活动场馆支持的项目表（历史）';

CREATE TABLE `hyd_training_course` (
    `id` varchar(32) NOT NULL COMMENT '主键id',
    `activityId` varchar(32) COMMENT '活动id',
    `tenantId` varchar(32) COMMENT '组织id',
    `stadiumId` varchar(32) COMMENT '场馆id',
    `stadiumItemId` varchar(32) COMMENT '项目id',
    `courseName` varchar(32) COMMENT '课程名称',
    `startTime` bigint(20) COMMENT '开课时间',
    `endTime` bigint(20) COMMENT '结课时间',
    `registrationStartTime` bigint(20) COMMENT '报名开始时间',
    `registrationEndTime` bigint(20) COMMENT '报名结束时间',
    `headCountLimit` int(11) COMMENT '限报人数限制',
    `durationLimit` int(11) COMMENT '课程时长，单位分钟',
    `totalCountLimit` int(11) COMMENT '限报人数限制',
    `ageStartLimit` varchar(32) COMMENT '培训对象年龄起始',
    `ageEndLimit` varchar(32) COMMENT '培训对象年龄结束',
    `price` int(11) COMMENT '价格，单位分',
    `hydOnline` tinyint(1) COMMENT '小程序上下线',
    `disabled` tinyint(1) COMMENT '状态，0-启用，1-停用',
    `createdTime` datetime COMMENT '创建时间',
    `updatedTime` datetime COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='培训课程表';

CREATE TABLE `hyd_training_course_history` (
    `id` varchar(32) NOT NULL COMMENT '主键id',
    `activityId` varchar(32) COMMENT '活动id',
    `tenantId` varchar(32) COMMENT '组织id',
    `stadiumId` varchar(32) COMMENT '场馆id',
    `stadiumItemId` varchar(32) COMMENT '项目id',
    `courseName` varchar(32) COMMENT '课程名称',
    `startTime` bigint(20) COMMENT '开课时间',
    `endTime` bigint(20) COMMENT '结课时间',
    `registrationStartTime` bigint(20) COMMENT '报名开始时间',
    `registrationEndTime` bigint(20) COMMENT '报名结束时间',
    `headCountLimit` int(11) COMMENT '限报人数限制',
    `durationLimit` int(11) COMMENT '课程时长，单位分钟',
    `totalCountLimit` int(11) COMMENT '限报人数限制',
    `ageStartLimit` varchar(32) COMMENT '培训对象年龄起始',
    `ageEndLimit` varchar(32) COMMENT '培训对象年龄结束',
    `price` int(11) COMMENT '价格，单位分',
    `hydOnline` tinyint(1) COMMENT '小程序上下线',
    `disabled` tinyint(1) COMMENT '状态，0-启用，1-停用',
    `createdTime` datetime COMMENT '创建时间',
    `updatedTime` datetime COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='培训课程表（历史）';

CREATE TABLE `hyd_order` (
    `id` varchar(32) NOT NULL COMMENT '主键id',
    `activityId` varchar(32) COMMENT '活动id',
    `tenantId` varchar(32) COMMENT '组织id',
    `stadiumId` varchar(32) COMMENT '场馆id',
    `stadiumItemId` varchar(32) COMMENT '项目id',
    `courseId` varchar(32) COMMENT '课程id',
    `orderAmount` int(11) COMMENT '订单金额，单位分',
    `actualAmount` int(11) COMMENT '实付金额，单位分',
    `couponAmount` int(11) COMMENT '消费券金额，单位分',
    `refundAmount` int(11) COMMENT '退款金额，单位分',
    `useCoupon` tinyint(1) COMMENT '是否使用消费券',
    `userAge` int(11) COMMENT '用户年龄',
    `userGender` varchar(11) COMMENT '用户性别',
    `studentAge` int(11) COMMENT '学员年龄',
    `studentGender` varchar(11) COMMENT '学员性别',
    `studentRegion` varchar(32) COMMENT '学员所属区域',
    `orderStatus` varchar(32) COMMENT '订单状态',
    `createdTime` datetime COMMENT '创建时间',
    `updatedTime` datetime COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表';

CREATE TABLE `hyd_order_history` (
    `id` varchar(32) NOT NULL COMMENT '主键id',
    `activityId` varchar(32) COMMENT '活动id',
    `tenantId` varchar(32) COMMENT '组织id',
    `stadiumId` varchar(32) COMMENT '场馆id',
    `stadiumItemId` varchar(32) COMMENT '项目id',
    `courseId` varchar(32) COMMENT '课程id',
    `orderAmount` int(11) COMMENT '订单金额，单位分',
    `actualAmount` int(11) COMMENT '实付金额，单位分',
    `couponAmount` int(11) COMMENT '消费券金额，单位分',
    `refundAmount` int(11) COMMENT '退款金额，单位分',
    `useCoupon` tinyint(1) COMMENT '是否使用消费券',
    `userAge` int(11) COMMENT '用户年龄',
    `userGender` varchar(11) COMMENT '用户性别',
    `studentAge` int(11) COMMENT '学员年龄',
    `studentGender` varchar(11) COMMENT '学员性别',
    `studentRegion` varchar(32) COMMENT '学员所属区域',
    `orderStatus` varchar(32) COMMENT '订单状态',
    `createdTime` datetime COMMENT '创建时间',
    `updatedTime` datetime COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表（历史）';

CREATE TABLE `hyd_la_stadium` (
    `id` INT(11) NOT NULL COMMENT '主键',
    `userId` INT(11) COMMENT '用户id',
    `title` VARCHAR(32) COMMENT '名称',
    `certBus` VARCHAR(10) COMMENT '营业类型',
    `doorImages` VARCHAR(32) COMMENT '门头照片',
    `busHours` VARCHAR(255) COMMENT '营业时间',
    `socialCode` VARCHAR(255) COMMENT '统一社会信用代码',
    `legalPerson` VARCHAR(255) COMMENT '法人',
    `legalMobile` VARCHAR(255) COMMENT '法人电话',
    `region` VARCHAR(10) COMMENT '地域',
    `type` VARCHAR(10) COMMENT '类别',
    `area` VARCHAR(255) COMMENT '面积',
    `contacts` VARCHAR(255) COMMENT '联系人',
    `contactsMobile` VARCHAR(255) COMMENT '联系电话',
    `address` VARCHAR(500) COMMENT '地址',
    `employed` INT(11) COMMENT '从业人数',
    `status` INT(1) COMMENT '状态，0-待受理，1-已受理，2-勘察中，3-已通过，4-已驳回',
    `indexStatus` INT(11) COMMENT '首页推荐，1-是，0-否',
    `sort` INT(5) COMMENT '排序',
    `createTime` INT(11) COMMENT '创建时间',
    `updateTime` INT(11) COMMENT '更新时间',
    `deleteTime` INT(11) COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='校外培训机构表';

CREATE TABLE `hyd_la_stadium_history` (
    `id` INT(11) NOT NULL COMMENT '主键',
    `userId` INT(11) COMMENT '用户id',
    `title` VARCHAR(32) COMMENT '名称',
    `certBus` VARCHAR(10) COMMENT '营业类型',
    `doorImages` VARCHAR(32) COMMENT '门头照片',
    `busHours` VARCHAR(255) COMMENT '营业时间',
    `socialCode` VARCHAR(255) COMMENT '统一社会信用代码',
    `legalPerson` VARCHAR(255) COMMENT '法人',
    `legalMobile` VARCHAR(255) COMMENT '法人电话',
    `region` VARCHAR(10) COMMENT '地域',
    `type` VARCHAR(10) COMMENT '类别',
    `area` VARCHAR(255) COMMENT '面积',
    `contacts` VARCHAR(255) COMMENT '联系人',
    `contactsMobile` VARCHAR(255) COMMENT '联系电话',
    `address` VARCHAR(500) COMMENT '地址',
    `employed` INT(11) COMMENT '从业人数',
    `status` INT(1) COMMENT '状态，0-待受理，1-已受理，2-勘察中，3-已通过，4-已驳回',
    `indexStatus` INT(11) COMMENT '首页推荐，1-是，0-否',
    `sort` INT(5) COMMENT '排序',
    `createTime` INT(11) COMMENT '创建时间',
    `updateTime` INT(11) COMMENT '更新时间',
    `deleteTime` INT(11) COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='校外培训机构表（历史）';

CREATE TABLE `hyd_la_stadium_file` (
    `id` INT(11) COMMENT '主键id',
    `userId` INT(11) COMMENT '用户id',
    `stadiumId` INT(11) COMMENT '场馆id',
    `mczzsbgzs` VARCHAR(255) COMMENT '《名称自主申报告知书》或《民办非企业单位成立名称核准表》',
    `xwpxjgsqdjb` VARCHAR(256) COMMENT '湖北省体育类校外培训机构申请登记表',
    `pxjgzc` VARCHAR(257) COMMENT '消防安全承诺书',
    `zjtrdyxzm` VARCHAR(258) COMMENT '培训机构章程',
    `pxjgrymx` VARCHAR(259) COMMENT '资金投入的有效证明材料',
    `wfczns` VARCHAR(260) COMMENT '湖北省体育类类校外培训机构从业人员明细表',
    `fwcqzm` VARCHAR(261) COMMENT '从业人员无犯罪记录、无失德失信或严重违纪行为承诺书',
    `pxcsnbjgt` VARCHAR(262) COMMENT '培训场所房屋产权证明、租赁(借用)合同',
    `pxcsnbpmt` VARCHAR(263) COMMENT '培训场所内部结构平面图',
    `nbglzd` VARCHAR(264) COMMENT '内部管理制度',
    `yljjpjsb` VARCHAR(265) COMMENT '配备常规、医疗急救药品及设备',
    `pxjgjcbab` VARCHAR(266) COMMENT '《湖北省类培训机构教材备案表》以及培训计划、教学大纲、培训教材和其他培训材料',
    `lhjbtylpxjgxy` VARCHAR(267) COMMENT '联合举办体育类培训机构的，应提交联合办学协议',
    `bxht` VARCHAR(268) COMMENT '保险合同',
    `remark` VARCHAR(269) COMMENT '其他',
    `createTime` INT(11) COMMENT '创建时间',
    `updateTime` INT(11) COMMENT '更新时间',
    `deleteTime` INT(11) COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='校外培训机构附件表';

CREATE TABLE `hyd_la_stadium_file_history` (
    `id` INT(11) COMMENT '主键id',
    `userId` INT(11) COMMENT '用户id',
    `stadiumId` INT(11) COMMENT '场馆id',
    `mczzsbgzs` VARCHAR(255) COMMENT '《名称自主申报告知书》或《民办非企业单位成立名称核准表》',
    `xwpxjgsqdjb` VARCHAR(256) COMMENT '湖北省体育类校外培训机构申请登记表',
    `pxjgzc` VARCHAR(257) COMMENT '消防安全承诺书',
    `zjtrdyxzm` VARCHAR(258) COMMENT '培训机构章程',
    `pxjgrymx` VARCHAR(259) COMMENT '资金投入的有效证明材料',
    `wfczns` VARCHAR(260) COMMENT '湖北省体育类类校外培训机构从业人员明细表',
    `fwcqzm` VARCHAR(261) COMMENT '从业人员无犯罪记录、无失德失信或严重违纪行为承诺书',
    `pxcsnbjgt` VARCHAR(262) COMMENT '培训场所房屋产权证明、租赁(借用)合同',
    `pxcsnbpmt` VARCHAR(263) COMMENT '培训场所内部结构平面图',
    `nbglzd` VARCHAR(264) COMMENT '内部管理制度',
    `yljjpjsb` VARCHAR(265) COMMENT '配备常规、医疗急救药品及设备',
    `pxjgjcbab` VARCHAR(266) COMMENT '《湖北省类培训机构教材备案表》以及培训计划、教学大纲、培训教材和其他培训材料',
    `lhjbtylpxjgxy` VARCHAR(267) COMMENT '联合举办体育类培训机构的，应提交联合办学协议',
    `bxht` VARCHAR(268) COMMENT '保险合同',
    `remark` VARCHAR(269) COMMENT '其他',
    `createTime` INT(11) COMMENT '创建时间',
    `updateTime` INT(11) COMMENT '更新时间',
    `deleteTime` INT(11) COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='校外培训机构附件表（历史）';
