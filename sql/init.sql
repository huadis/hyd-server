create database hyd;
use hyd;
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
INSERT INTO `hyd_sys_user` (`user_id`, `username`, `nick_name`, `gender`, `phone`, `email`, `avatar_name`, `avatar_path`, `password`, `enabled`, `create_by`, `update_by`, `pwd_reset_time`, `create_time`, `update_time`) VALUES (1, 'admin', '管理员', '男', '18888888888', '201507802@qq.com', 'avatar-20250122102642222.png', '/Users/jie/Documents/work/private/eladmin/~/avatar/avatar-20250122102642222.png', '$2a$10$Egp1/gvFlt7zhlXVfEFw4OfWQCGPw0ClmMcc6FjTnvXNRVf9zdMRa', b'1', NULL, 'admin', '2020-05-03 16:38:31', '2018-08-23 09:11:56', '2025-01-22 10:26:42');
INSERT INTO `hyd_sys_user` (`user_id`, `username`, `nick_name`, `gender`, `phone`, `email`, `avatar_name`, `avatar_path`, `password`, `enabled`, `create_by`, `update_by`, `pwd_reset_time`, `create_time`, `update_time`) VALUES (2, 'test', '测试', '男', '19999999999', '231@qq.com', NULL, NULL, '$2a$10$4XcyudOYTSz6fue6KFNMHeUQnCX5jbBQypLEnGk1PmekXt5c95JcK', b'1', 'admin', 'admin', NULL, '2020-05-05 11:15:49', '2025-01-21 14:53:04');
COMMIT;


CREATE TABLE `hyd_user_channel` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `registerUserNum` varchar(255) COMMENT '注册用户',
    `realNameUserNum` varchar(255) COMMENT '实名用户',
    `receiveCouponUserNum` varchar(255) COMMENT '领券用户',
    `useCouponUserNum` varchar(255) COMMENT '用券用户',
    `orderUserNum` varchar(255) COMMENT '下单用户',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-用户来源渠道';

CREATE TABLE `hyd_user_register` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `month` varchar(255) COMMENT '月份',
    `userNum` varchar(255) COMMENT '数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-每月新增用户';

CREATE TABLE `hyd_user_sex` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `sex` varchar(255) COMMENT '性别',
    `sexNum` varchar(255) COMMENT '数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-男女占比';


CREATE TABLE `hyd_user_age` (
    `id` bigint(20) NOT NULL COMMENT '主键',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-年龄占比';


CREATE TABLE `hyd_user_repurchase` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `1Num` varchar(255) COMMENT '一次下单',
    `bt2and5Num` varchar(255) COMMENT '2-5次',
    `over5Num` varchar(255) COMMENT '5次以上',
    `over10Num` varchar(255) COMMENT '10次以上',
    `over50Num` varchar(255) COMMENT '50次以上',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-复购率';


CREATE TABLE `hyd_order` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `orderNum` varchar(255) COMMENT '订单总数',
    `orderAmount` varchar(255) COMMENT '订单总金额',
    `couponAmount` varchar(255) COMMENT '消费券总金额',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-订单数量';

CREATE TABLE `hyd_order_month` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `month` varchar(255) COMMENT '月份',
    `orderNum` varchar(255) COMMENT '数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-订单趋势';

CREATE TABLE `hyd_order_sport` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `sportName` varchar(255) COMMENT '项目名称',
    `orderAmount` varchar(255) COMMENT '消费券订单金额',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-项目消费券订单金额Top5';

CREATE TABLE `hyd_order_stadium` (
    `id` bigint(20) NOT NULL COMMENT '主键id',
    `stadiumName` varchar(255) COMMENT '场馆名称',
    `orderAmount` varchar(255) COMMENT '消费券订单金额',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-场馆消费券订单金额Top5';

CREATE TABLE `hyd_stadium` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `onlineStadiumNum` varchar(255) COMMENT '在线场馆数量',
    `couponStadiumNum` varchar(255) COMMENT '消费券场馆数量',
    `socialStadiumNum` varchar(255) COMMENT '社会场馆数量',
    `publicStadiumNum` varchar(255) COMMENT '公共场馆数量',
    `useCouponStadiumNum` varchar(255) COMMENT '累计用券场馆数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-在线场馆数量';

CREATE TABLE `hyd_stadium_district` (
    `id` bigint(20) NOT NULL COMMENT '主键id',
    `district` varchar(255) COMMENT '所属区',
    `districtName` varchar(255) COMMENT '所属区名称',
    `couponStadiumNum` varchar(255) COMMENT '定点场馆数量',
    `publicStadiumNum` varchar(255) COMMENT '公共场馆数量',
    `socialStadiumNum` varchar(255) COMMENT '社会场馆数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-在线场馆各区情况';

CREATE TABLE `hyd_stadium_sport_coupon` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `sportName` varchar(255) COMMENT '运动项目',
    `sportNum` varchar(255) COMMENT '项目数量',
    `useCountRate` varchar(255) COMMENT '用券占比',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-运动项目分布用券数占比';

CREATE TABLE `hyd_facility` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `facilityTypeName` varchar(255) COMMENT '设施类型名称',
    `facilityNum` varchar(255) COMMENT '设施数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-设施全貌';

CREATE TABLE `hyd_facility_district` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `districtName` varchar(255) COMMENT '区名称',
    `facilityNum` varchar(255) COMMENT '设施数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-设施各区分布';

CREATE TABLE `hyd_stadium_map` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `resourceType` varchar(255) COMMENT '资源类型',
    `resourceName` varchar(255) COMMENT '资源名称',
    `resourceNum` varchar(255) COMMENT '资源数量',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-电子地图';

CREATE TABLE `hyd_facility_year` (
    `id` bigint(20) NOT NULL COMMENT '主键',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-健身点位年数据';

CREATE TABLE `hyd_facility_district_month` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `districtName` varchar(255) COMMENT '区名称',
    `monthInspectYes` varchar(255) COMMENT '本月已巡检',
    `monthInspectNo` varchar(255) COMMENT '本月待巡检',
    `monthRepairYes` varchar(255) COMMENT '本月已维修',
    `monthRepairNo` varchar(255) COMMENT '本月待维修',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-设施各区月数据';

CREATE TABLE `hyd_facility_inspect` (
    `id` bigint(20) NOT NULL COMMENT '主键',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-巡检动态';

CREATE TABLE `hyd_coupon_amount` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `sendAmount` varchar(255) COMMENT '发放金额',
    `receiveCount` varchar(255) COMMENT '领券人次',
    `usedCount` varchar(255) COMMENT '用券人次',
    `usedRatio` varchar(255) COMMENT '用券资金占比',
    `useCouponAmount` varchar(255) COMMENT '用券金额',
    `orderAmount` varchar(255) COMMENT '订单金额',
    `orderRatio` varchar(255) COMMENT '拉动消费比',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-消费券总金额';

CREATE TABLE `hyd_stock` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `stockName` varchar(255) COMMENT '券名称',
    `receiveNum` varchar(255) COMMENT '领券数',
    `useNum` varchar(255) COMMENT '用券数',
    `useRate` varchar(255) COMMENT '用券率',
    `useCouponAmount` varchar(255) COMMENT '用券金额',
    `orderAmount` varchar(255) COMMENT '订单金额',
    `orderRate` varchar(255) COMMENT '拉动消费比',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-消费券领用券';

CREATE TABLE `hyd_coupon_user_age` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `under25Num` varchar(255) COMMENT '25岁以下',
    `bt26and30Num` varchar(255) COMMENT '26-30岁',
    `bt31and35Num` varchar(255) COMMENT '31-35岁',
    `bt36and40Num` varchar(255) COMMENT '36-40岁',
    `bt41and45Num` varchar(255) COMMENT '41-45岁',
    `bt46and50Num` varchar(255) COMMENT '46-50岁',
    `bt51and60Num` varchar(255) COMMENT '51-60岁',
    `over60Num` varchar(255) COMMENT '60岁以上',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-券用户年龄分布';

CREATE TABLE `hyd_coupon_stadium_top` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `stadiumName` varchar(255) COMMENT '场馆名称',
    `couponAmount` varchar(255) COMMENT '消费券金额',
    `createdTime` timestamp COMMENT '创建时间',
    `updateTime` timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='驾驶舱-消费券场馆预订 top/';
