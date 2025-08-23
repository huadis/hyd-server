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


-- ----------------------------
-- 体育基础设施
-- ----------------------------

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
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-健身点位年数据-查询表';

CREATE TABLE `hyd_result_facility_year_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `quantity` varchar(255) COMMENT '健身点位数量',
    `yearInspectRate` varchar(255) COMMENT '本年巡检率',
    `yearInspectYes` varchar(255) COMMENT '本年已巡检',
    `yearInspectNo` varchar(255) COMMENT '本年待巡检',
    `yearRepairRate` varchar(255) COMMENT '本年维修完成率',
    `yearRepairYes` varchar(255) COMMENT '本年已维修',
    `yearRepairNo` varchar(255) COMMENT '本年待维修',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-健身点位年数据-历史表';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_facility` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `facilityTypeName` varchar(255) COMMENT '设施类型名称',
    `facilityPercentage` DECIMAL(5,2) COMMENT '设施占比（%）',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-设施全貌-查询表';

CREATE TABLE `hyd_result_facility_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `facilityTypeName` varchar(255) COMMENT '设施类型名称',
    `facilityNum` varchar(255) COMMENT '设施数量',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-设施全貌-历史表';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_facility_district` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `facilityTypeName` varchar(255) COMMENT '设施类型名称',
    `districtName` varchar(255) COMMENT '区名称',
    `facilityNum` varchar(255) COMMENT '设施数量',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-设施各区分布-查询表';

CREATE TABLE `hyd_result_facility_district_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `facilityTypeName` varchar(255) COMMENT '设施类型名称',
    `districtName` varchar(255) COMMENT '区名称',
    `facilityNum` varchar(255) COMMENT '设施数量',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-设施各区分布-历史表';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_stadium_map_point` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `resourceType` varchar(255) COMMENT '资源类型',
    `resourceName` varchar(255) COMMENT '资源名称',
    `longitude` varchar(255) COMMENT '经度',
    `latitude` varchar(255) COMMENT '纬度',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '体育基础设施-电子地图点位信息-查询表';

CREATE TABLE `hyd_result_stadium_map_point_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `resourceType` varchar(255) COMMENT '资源类型',
    `resourceName` varchar(255) COMMENT '资源名称',
    `longitude` varchar(255) COMMENT '经度',
    `latitude` varchar(255) COMMENT '纬度',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '体育基础设施-电子地图点位信息-历史表';

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
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-巡检维修动态-查询表';

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
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-巡检维修动态-历史表';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_facility_district_month` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `districtName` varchar(255) COMMENT '区名称',
    `monthInspectYes` varchar(255) COMMENT '本月已巡检',
    `monthInspectNo` varchar(255) COMMENT '本月待巡检',
    `monthRepairYes` varchar(255) COMMENT '本月已维修',
    `monthRepairNo` varchar(255) COMMENT '本月待维修',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-各区巡检维修数据-查询表';

CREATE TABLE `hyd_result_facility_district_month_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `districtName` varchar(255) COMMENT '区名称',
    `monthInspectYes` varchar(255) COMMENT '本月已巡检',
    `monthInspectNo` varchar(255) COMMENT '本月待巡检',
    `monthRepairYes` varchar(255) COMMENT '本月已维修',
    `monthRepairNo` varchar(255) COMMENT '本月待维修',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-各区巡检维修数据-历史表';

-- --------------------------------------------------------------------------------------------------------------------------------------------

-- ----------------------------
-- 体育消费卷
-- ----------------------------

CREATE TABLE `hyd_result_coupon_amount` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sendAmount` varchar(255) COMMENT '发放金额',
    `receiveCount` varchar(255) COMMENT '领券人次',
    `usedCount` varchar(255) COMMENT '用券人次',
    `usedRatio` varchar(255) COMMENT '用券资金占比',
    `useCouponAmount` varchar(255) COMMENT '用券金额',
    `orderAmount` varchar(255) COMMENT '订单金额',
    `orderRatio` varchar(255) COMMENT '拉动消费比',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-消费券总金额-查询表';

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
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-消费券总金额-历史表';

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
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-消费券领卷用券-查询表';

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
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-消费券领卷用券-历史表';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_order_sport` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sportName` varchar(255) COMMENT '项目名称',
    `orderAmount` varchar(255) COMMENT '消费券订单金额',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-项目消费券订单金额Top5-查询表';

CREATE TABLE `hyd_result_order_sport_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sportName` varchar(255) COMMENT '项目名称',
    `orderAmount` varchar(255) COMMENT '消费券订单金额',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-项目消费券订单金额Top5-历史表';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_order_stadium` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `stadiumName` varchar(255) COMMENT '场馆名称',
    `orderAmount` varchar(255) COMMENT '消费券订单金额',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-场馆消费券订单金额Top5-查询表';

CREATE TABLE `hyd_result_order_stadium_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `stadiumName` varchar(255) COMMENT '场馆名称',
    `orderAmount` varchar(255) COMMENT '消费券订单金额',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-场馆消费券订单金额Top5-历史表';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_order` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `month` varchar(255) COMMENT '月份',
    `orderNum` varchar(255) COMMENT '订单总数',
    `orderAmount` varchar(255) COMMENT '订单总金额',
    `couponAmount` varchar(255) COMMENT '消费券总金额',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-订单数量-查询表';

CREATE TABLE `hyd_result_order_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `month` varchar(255) COMMENT '月份',
    `orderNum` varchar(255) COMMENT '订单总数',
    `orderAmount` varchar(255) COMMENT '订单总金额',
    `couponAmount` varchar(255) COMMENT '消费券总金额',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-订单数量-历史表';

-- --------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE `hyd_result_order_month` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `month` varchar(255) COMMENT '月份',
    `orderNum` varchar(255) COMMENT '数量',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-订单趋势-查询表';

CREATE TABLE `hyd_result_order_month_history` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `month` varchar(255) COMMENT '月份',
    `orderNum` varchar(255) COMMENT '数量',
    `batchNo` varchar(50) COMMENT '批次号，用于标识数据批次',
    `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-订单趋势-历史表';
