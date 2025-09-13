----
-- Table structure for hyd_excel_industry_core_indicators
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_industry_core_indicators`;
CREATE TABLE `hyd_excel_industry_core_indicators` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增唯一标识',
  `statisticalYear` int NOT NULL COMMENT '统计年度',
  `totalOutputValue` decimal(10,2) DEFAULT NULL COMMENT '体育产业总产值（亿）',
  `addedValue` decimal(10,2) DEFAULT NULL COMMENT '产业增加值（亿）',
  `marketEntityCount` int DEFAULT NULL COMMENT '体育市场主体总量（家）',
  `employeeCount` decimal(10,2) DEFAULT NULL COMMENT '从业人员数量（万）',
  `sportsConsumptionTotalScale` decimal(10,2) DEFAULT NULL COMMENT '体育消费总规模（万）',
  `perCapitaSportsConsumption` decimal(10,2) DEFAULT NULL COMMENT '人均体育消费（元）',
  `otherSportsServicesRevenueGrowthRate` decimal(10,2) DEFAULT NULL COMMENT '体育其他服务业营收增速',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业-核心指标总表';

-- ----------------------------
-- Table structure for hyd_excel_industry_core_indicators_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_industry_core_indicators_history`;
CREATE TABLE `hyd_excel_industry_core_indicators_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增唯一标识',
  `statisticalYear` int NOT NULL COMMENT '统计年度',
  `totalOutputValue` decimal(10,2) DEFAULT NULL COMMENT '体育产业总产值（亿）',
  `addedValue` decimal(10,2) DEFAULT NULL COMMENT '产业增加值（亿）',
  `marketEntityCount` int DEFAULT NULL COMMENT '体育市场主体总量（家）',
  `employeeCount` decimal(10,2) DEFAULT NULL COMMENT '从业人员数量（万）',
  `sportsConsumptionTotalScale` decimal(10,2) DEFAULT NULL COMMENT '体育消费总规模（万）',
  `perCapitaSportsConsumption` decimal(10,2) DEFAULT NULL COMMENT '人均体育消费（元）',
  `otherSportsServicesRevenueGrowthRate` decimal(10,2) DEFAULT NULL COMMENT '体育其他服务业营收增速',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业（历史）-核心指标总表';

-- ----------------------------
-- Table structure for hyd_excel_industry_employee_count
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_industry_employee_count`;
CREATE TABLE `hyd_excel_industry_employee_count` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `statisticalYear` int NOT NULL COMMENT '统计年度',
  `entityType` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '从业类型（体育管理活动 / 竞赛表演等）',
  `personCount` int DEFAULT NULL COMMENT '人数（人）',
  `dataSource` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据来源/备注',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业-从业人员数量（分类统计）表';

-- ----------------------------
-- Table structure for hyd_excel_industry_employee_count_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_industry_employee_count_history`;
CREATE TABLE `hyd_excel_industry_employee_count_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `statisticalYear` int NOT NULL COMMENT '统计年度',
  `entityType` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '从业类型（体育管理活动 / 竞赛表演等）',
  `personCount` int DEFAULT NULL COMMENT '人数（人）',
  `dataSource` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据来源/备注',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业（历史）-从业人员数量（分类统计）表';

-- ----------------------------
-- Table structure for hyd_excel_industry_entity_count_ratio
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_industry_entity_count_ratio`;
CREATE TABLE `hyd_excel_industry_entity_count_ratio` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `statisticalYear` int NOT NULL COMMENT '统计年度',
  `entityType` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '市场主体类型（如体育管理活动 / 竞赛表演等）',
  `proportion` decimal(5,2) DEFAULT NULL COMMENT '占比（%）',
  `dataSource` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据来源/备注',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业-市场主体数量（分类占比）表';

-- ----------------------------
-- Table structure for hyd_excel_industry_entity_count_ratio_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_industry_entity_count_ratio_history`;
CREATE TABLE `hyd_excel_industry_entity_count_ratio_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `statisticalYear` int NOT NULL COMMENT '统计年度',
  `entityType` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '市场主体类型（如体育管理活动 / 竞赛表演等）',
  `proportion` decimal(5,2) DEFAULT NULL COMMENT '占比（%）',
  `dataSource` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据来源/备注',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业（历史）-市场主体数量（分类占比）表';

-- ----------------------------
-- Table structure for hyd_excel_industry_goods_purchase_rate
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_industry_goods_purchase_rate`;
CREATE TABLE `hyd_excel_industry_goods_purchase_rate` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `statisticalYear` int NOT NULL COMMENT '统计年度',
  `entityType` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '体育用品类型（运动鞋 / 运动服饰等）',
  `growthRate` decimal(5,2) DEFAULT NULL COMMENT '购买率（%）',
  `dataSource` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据来源/备注',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业-居民体育用品购买率表';

-- ----------------------------
-- Table structure for hyd_excel_industry_goods_purchase_rate_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_industry_goods_purchase_rate_history`;
CREATE TABLE `hyd_excel_industry_goods_purchase_rate_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `statisticalYear` int NOT NULL COMMENT '统计年度',
  `entityType` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '体育用品类型（运动鞋 / 运动服饰等）',
  `growthRate` decimal(5,2) DEFAULT NULL COMMENT '购买率（%）',
  `dataSource` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据来源/备注',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业（历史）-居民体育用品购买率表';

-- ----------------------------
-- Table structure for hyd_excel_industry_growth_value_trend
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_industry_growth_value_trend`;
CREATE TABLE `hyd_excel_industry_growth_value_trend` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `statisticalYear` int NOT NULL COMMENT '统计年度',
  `totalOutputValue` decimal(10,2) DEFAULT NULL COMMENT '总产值（亿元）',
  `growthRate` decimal(5,2) DEFAULT NULL COMMENT '增长率（%）',
  `dataSource` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据来源/备注',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业-总增速和增加值（年度趋势）表';

-- ----------------------------
-- Table structure for hyd_excel_industry_growth_value_trend_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_industry_growth_value_trend_history`;
CREATE TABLE `hyd_excel_industry_growth_value_trend_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `statisticalYear` int NOT NULL COMMENT '统计年度',
  `totalOutputValue` decimal(10,2) DEFAULT NULL COMMENT '总产值（亿元）',
  `growthRate` decimal(5,2) DEFAULT NULL COMMENT '增长率（%）',
  `dataSource` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据来源/备注',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业（历史）-总增速和增加值（年度趋势）表';

-- ----------------------------
-- Table structure for hyd_excel_industry_scale_trend
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_industry_scale_trend`;
CREATE TABLE `hyd_excel_industry_scale_trend` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `statisticalYear` int NOT NULL COMMENT '统计年度',
  `totalOutputValue` decimal(10,2) DEFAULT NULL COMMENT '总产值（亿元）',
  `growthRate` decimal(5,2) DEFAULT NULL COMMENT '增长率（%）',
  `dataSource` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据来源/备注',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业-总规模（年度趋势）表';

-- ----------------------------
-- Table structure for hyd_excel_industry_scale_trend_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_industry_scale_trend_history`;
CREATE TABLE `hyd_excel_industry_scale_trend_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `statisticalYear` int NOT NULL COMMENT '统计年度',
  `totalOutputValue` decimal(10,2) DEFAULT NULL COMMENT '总产值（亿元）',
  `growthRate` decimal(5,2) DEFAULT NULL COMMENT '增长率（%）',
  `dataSource` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据来源/备注',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业（历史）-总规模（年度趋势）表';

-- ----------------------------
-- Table structure for hyd_excel_industry_training_participation_rate
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_industry_training_participation_rate`;
CREATE TABLE `hyd_excel_industry_training_participation_rate` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `statisticalYear` int NOT NULL COMMENT '统计年度',
  `entityType` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '培训项目（帆船、皮筏艇 / 羽毛球等）',
  `growthRate` decimal(5,2) DEFAULT NULL COMMENT '参与率（%）',
  `dataSource` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据来源/备注',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业-居民体育培训项目参与率表';

-- ----------------------------
-- Table structure for hyd_excel_industry_training_participation_rate_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_industry_training_participation_rate_history`;
CREATE TABLE `hyd_excel_industry_training_participation_rate_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `statisticalYear` int NOT NULL COMMENT '统计年度',
  `entityType` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '培训项目（帆船、皮筏艇 / 羽毛球等）',
  `growthRate` decimal(5,2) DEFAULT NULL COMMENT '参与率（%）',
  `dataSource` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据来源/备注',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育产业（历史）-居民体育培训项目参与率表';

-- ----------------------------
-- Table structure for hyd_excel_instructor_age_growth
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_instructor_age_growth`;
CREATE TABLE `hyd_excel_instructor_age_growth` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增唯一标识',
  `statisticalYear` int NOT NULL COMMENT '统计年度，如2024',
  `ageInterval` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '年龄区间，如20岁以下、20-30岁等',
  `personCount` int DEFAULT NULL COMMENT '对应年龄区间的人数',
  `growthRate` decimal(5,2) DEFAULT NULL COMMENT '增长率（%），保留两位小数',
  `dataSource` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据来源/备注信息',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育指导员-人数增长统计明细表';

-- ----------------------------
-- Table structure for hyd_excel_instructor_age_growth_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_instructor_age_growth_history`;
CREATE TABLE `hyd_excel_instructor_age_growth_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增唯一标识',
  `statisticalYear` int NOT NULL COMMENT '统计年度，如2024',
  `ageInterval` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '年龄区间，如20岁以下、20-30岁等',
  `personCount` int DEFAULT NULL COMMENT '对应年龄区间的人数',
  `growthRate` decimal(5,2) DEFAULT NULL COMMENT '增长率（%），保留两位小数',
  `dataSource` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据来源/备注信息',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育指导员（历史）-人数增长统计明细表';

-- ----------------------------
-- Table structure for hyd_excel_instructor_age_stats
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_instructor_age_stats`;
CREATE TABLE `hyd_excel_instructor_age_stats` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `statisticalYear` int NOT NULL COMMENT '统计年度，如2024',
  `ageInterval` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '年龄区间，如20岁以下、20-30岁等',
  `personCount` int DEFAULT NULL COMMENT '对应年龄区间的人数',
  `dataSource` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据来源/备注',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育指导员-年龄统计明细表';

-- ----------------------------
-- Table structure for hyd_excel_instructor_age_stats_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_instructor_age_stats_history`;
CREATE TABLE `hyd_excel_instructor_age_stats_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `statisticalYear` int NOT NULL COMMENT '统计年度，如2024',
  `ageInterval` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '年龄区间，如20岁以下、20-30岁等',
  `personCount` int DEFAULT NULL COMMENT '对应年龄区间的人数',
  `dataSource` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据来源/备注',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育指导员（历史）-年龄统计明细表';

-- ----------------------------
-- Table structure for hyd_excel_instructor_info
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_instructor_info`;
CREATE TABLE `hyd_excel_instructor_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增唯一标识',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
  `gender` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '性别（男/女）',
  `birthDate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '出生日期，格式如1980-12-12',
  `serviceProject` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '服务项目',
  `level` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '级别',
  `certifyTime` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '获证时间，格式如2010-10-20',
  `region` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所在地区',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `uploadTime` date DEFAULT NULL COMMENT '上传时间，自动填充',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育指导员-汇总';

-- ----------------------------
-- Table structure for hyd_excel_instructor_info_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_instructor_info_history`;
CREATE TABLE `hyd_excel_instructor_info_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增唯一标识',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
  `gender` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '性别（男/女）',
  `birthDate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '出生日期，格式如1980-12-12',
  `serviceProject` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '服务项目',
  `level` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '级别',
  `certifyTime` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '获证时间，格式如2010-10-20',
  `region` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所在地区',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `uploadTime` date DEFAULT NULL COMMENT '上传时间，自动填充',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育指导员（历史）-汇总';

-- ----------------------------
-- Table structure for hyd_excel_public_events
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_public_events`;
CREATE TABLE `hyd_excel_public_events` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sequence` bigint NOT NULL COMMENT '序号',
  `district` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区属，如江夏区',
  `eventName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '赛事名称',
  `sportItem` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '运动项目',
  `hostUnit` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '主办单位',
  `organizerUnit` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '承办单位',
  `eventYear` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '赛事年份',
  `eventMonth` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '赛事月份',
  `eventDate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '赛事日期',
  `eventLocation` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '赛事活动地点',
  `participantCount` int DEFAULT NULL COMMENT '参赛规模（人数）',
  `eventLevel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '赛事级别（国际级、国家级、省级、市级、区级、街道级、社区级）',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='大众赛事-体育赛事信息表';

-- ----------------------------
-- Table structure for hyd_excel_public_events_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_public_events_history`;
CREATE TABLE `hyd_excel_public_events_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sequence` bigint NOT NULL COMMENT '序号',
  `district` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区属，如江夏区',
  `eventName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '赛事名称',
  `sportItem` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '运动项目',
  `hostUnit` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '主办单位',
  `organizerUnit` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '承办单位',
  `eventYear` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '赛事年份',
  `eventMonth` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '赛事月份',
  `eventDate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '赛事日期',
  `eventLocation` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '赛事活动地点',
  `participantCount` int DEFAULT NULL COMMENT '参赛规模（人数）',
  `eventLevel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '赛事级别（国际级、国家级、省级、市级、区级、街道级、社区级）',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='大众赛事（历史）-体育赛事信息表';

-- ----------------------------
-- Table structure for hyd_excel_sports_organization
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_sports_organization`;
CREATE TABLE `hyd_excel_sports_organization` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sequence` bigint NOT NULL COMMENT '序号',
  `orgName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组织名称',
  `address` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `districtName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区属',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育组织';

-- ----------------------------
-- Table structure for hyd_excel_sports_organization_district_stat
-- ----------------------------
DROP TABLE IF EXISTS `hyd_excel_sports_organization_district_stat`;
CREATE TABLE `hyd_excel_sports_organization_district_stat` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `districtName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地区',
  `districtNum` bigint DEFAULT NULL COMMENT '数量',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育组织-区属统计';

-- ----------------------------
-- Table structure for hyd_origin_la_stadium_file_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_origin_la_stadium_file_history`;
CREATE TABLE `hyd_origin_la_stadium_file_history` (
  `id` int NOT NULL COMMENT '主键id',
  `userId` int DEFAULT NULL COMMENT '用户id',
  `stadiumId` int DEFAULT NULL COMMENT '场馆id',
  `mczzsbgzs` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '《名称自主申报告知书》或《民办非企业单位成立名称核准表》',
  `xwpxjgsqdjb` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '湖北省体育类校外培训机构申请登记表',
  `pxjgzc` varchar(257) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消防安全承诺书',
  `zjtrdyxzm` varchar(258) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '培训机构章程',
  `pxjgrymx` varchar(259) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资金投入的有效证明材料',
  `wfczns` varchar(260) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '湖北省体育类类校外培训机构从业人员明细表',
  `fwcqzm` varchar(261) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '从业人员无犯罪记录、无失德失信或严重违纪行为承诺书',
  `pxcsnbjgt` varchar(262) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '培训场所房屋产权证明、租赁(借用)合同',
  `pxcsnbpmt` varchar(263) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '培训场所内部结构平面图',
  `nbglzd` varchar(264) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内部管理制度',
  `yljjpjsb` varchar(265) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '配备常规、医疗急救药品及设备',
  `pxjgjcbab` varchar(266) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '《湖北省类培训机构教材备案表》以及培训计划、教学大纲、培训教材和其他培训材料',
  `lhjbtylpxjgxy` varchar(267) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联合举办体育类培训机构的，应提交联合办学协议',
  `bxht` varchar(268) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '保险合同',
  `remark` varchar(269) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '其他',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createTime` int DEFAULT NULL COMMENT '创建时间',
  `updateTime` int DEFAULT NULL COMMENT '更新时间',
  `deleteTime` int DEFAULT NULL COMMENT '删除时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='校外培训机构附件表（历史）';

-- ----------------------------
-- Table structure for hyd_origin_la_stadium_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_origin_la_stadium_history`;
CREATE TABLE `hyd_origin_la_stadium_history` (
  `id` int NOT NULL COMMENT '主键',
  `userId` int DEFAULT NULL COMMENT '用户id',
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `certBus` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '营业类型',
  `doorImages` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '门头照片',
  `busHours` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '营业时间',
  `socialCode` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '统一社会信用代码',
  `legalPerson` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '法人',
  `legalMobile` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '法人电话',
  `region` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地域',
  `type` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类别',
  `area` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '面积',
  `contacts` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系人',
  `contactsMobile` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `address` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `employed` int DEFAULT NULL COMMENT '从业人数',
  `status` int DEFAULT NULL COMMENT '状态，0-待受理，1-已受理，2-勘察中，3-已通过，4-已驳回',
  `indexStatus` int DEFAULT NULL COMMENT '首页推荐，1-是，0-否',
  `sort` int DEFAULT NULL COMMENT '排序',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createTime` int DEFAULT NULL COMMENT '创建时间',
  `updateTime` int DEFAULT NULL COMMENT '更新时间',
  `deleteTime` int DEFAULT NULL COMMENT '删除时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='校外培训机构表（历史）';

-- ----------------------------
-- Table structure for hyd_origin_order_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_origin_order_history`;
CREATE TABLE `hyd_origin_order_history` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键id',
  `activityId` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动id',
  `tenantId` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组织id',
  `stadiumId` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '场馆id',
  `stadiumItemId` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目id',
  `courseId` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程id',
  `orderAmount` int DEFAULT NULL COMMENT '订单金额，单位分',
  `actualAmount` int DEFAULT NULL COMMENT '实付金额，单位分',
  `couponAmount` int DEFAULT NULL COMMENT '消费券金额，单位分',
  `refundAmount` int DEFAULT NULL COMMENT '退款金额，单位分',
  `useCoupon` tinyint(1) DEFAULT NULL COMMENT '是否使用消费券',
  `userAge` int DEFAULT NULL COMMENT '用户年龄',
  `userGender` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户性别',
  `studentAge` int DEFAULT NULL COMMENT '学员年龄',
  `studentGender` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学员性别',
  `studentRegion` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学员所属区域',
  `orderStatus` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单状态',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表（历史）';

-- ----------------------------
-- Table structure for hyd_origin_stadium_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_origin_stadium_history`;
CREATE TABLE `hyd_origin_stadium_history` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键id',
  `tenantId` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组织id',
  `stadiumName` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `shortName` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '简称',
  `province` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '省编码',
  `city` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '市编码',
  `district` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区编码',
  `provinceName` varchar(33) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '省名称',
  `cityName` varchar(34) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '市名称',
  `districtName` varchar(35) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区名称',
  `street` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '街道',
  `address` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `longitude` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经度',
  `latitude` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '纬度',
  `area` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '面积',
  `holdNum` int DEFAULT NULL COMMENT '可容纳人数',
  `contactName` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系人',
  `telephone` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `online` tinyint DEFAULT NULL COMMENT '是否上线（0-未上线，1-上线）',
  `investmentNature` tinyint DEFAULT NULL COMMENT '场馆性质（0-社会的，1-公共的）',
  `disabled` tinyint DEFAULT NULL COMMENT '状态（0-启用，1-停用）',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='培训场馆表（历史）';

-- ----------------------------
-- Table structure for hyd_origin_stadium_item_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_origin_stadium_item_history`;
CREATE TABLE `hyd_origin_stadium_item_history` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键id',
  `tenantId` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组织id',
  `stadiumId` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '场馆id',
  `stadiumItemName` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目名称',
  `sportCode` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目类型编码',
  `sportName` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目类型名称',
  `disabled` tinyint DEFAULT NULL COMMENT '状态（0-启用，1-停用 ）',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆培训项目表（历史）';

-- ----------------------------
-- Table structure for hyd_origin_tenant_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_origin_tenant_history`;
CREATE TABLE `hyd_origin_tenant_history` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键id',
  `tenantName` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `shortName` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '简称',
  `province` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '省编码',
  `city` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '市编码',
  `district` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区编码',
  `provinceName` varchar(33) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '省名称',
  `cityName` varchar(34) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '市名称',
  `districtName` varchar(35) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区名称',
  `address` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `legalPerson` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '法人代表',
  `phone` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `businessLicenseImg` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '营业执照',
  `tenantIcon` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公司照片',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='组织机构表（历史）';

-- ----------------------------
-- Table structure for hyd_origin_training_activity_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_origin_training_activity_history`;
CREATE TABLE `hyd_origin_training_activity_history` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键id',
  `activityName` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动名称',
  `organizingCompany` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '主办单位',
  `startTime` datetime DEFAULT NULL COMMENT '活动开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '活动结束时间',
  `open` tinyint DEFAULT NULL COMMENT '启用开关（0-关闭，1-打开 ）',
  `disabled` tinyint DEFAULT NULL COMMENT '状态（0-启用，1-停用 ）',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='培训活动表（历史）';

-- ----------------------------
-- Table structure for hyd_origin_training_activity_item_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_origin_training_activity_item_history`;
CREATE TABLE `hyd_origin_training_activity_item_history` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键id',
  `activityId` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动id',
  `sportCode` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目类型编码',
  `sportName` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目类型名称',
  `ageStartLimit` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '培训对象年龄起始限制',
  `ageEndLimit` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '培训对象年龄结束限制',
  `disabled` tinyint DEFAULT NULL COMMENT '状态（0-启用，1-停用 ）',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='培训活动支持的项目表（历史）';

-- ----------------------------
-- Table structure for hyd_origin_training_activity_item_stadium_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_origin_training_activity_item_stadium_history`;
CREATE TABLE `hyd_origin_training_activity_item_stadium_history` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键id',
  `activityId` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动id',
  `stadiumId` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '场馆id',
  `sportCode` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目类型编码',
  `sportName` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目类型名称',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='培训活动场馆支持的项目表（历史）';

-- ----------------------------
-- Table structure for hyd_origin_training_course_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_origin_training_course_history`;
CREATE TABLE `hyd_origin_training_course_history` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键id',
  `activityId` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动id',
  `tenantId` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组织id',
  `stadiumId` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '场馆id',
  `stadiumItemId` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目id',
  `courseName` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `startTime` bigint DEFAULT NULL COMMENT '开课时间',
  `endTime` bigint DEFAULT NULL COMMENT '结课时间',
  `registrationStartTime` bigint DEFAULT NULL COMMENT '报名开始时间',
  `registrationEndTime` bigint DEFAULT NULL COMMENT '报名结束时间',
  `headCountLimit` int DEFAULT NULL COMMENT '限报人数限制',
  `durationLimit` int DEFAULT NULL COMMENT '课程时长，单位分钟',
  `totalCountLimit` int DEFAULT NULL COMMENT '限报人数限制',
  `ageStartLimit` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '培训对象年龄起始',
  `ageEndLimit` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '培训对象年龄结束',
  `price` int DEFAULT NULL COMMENT '价格，单位分',
  `hydOnline` tinyint(1) DEFAULT NULL COMMENT '小程序上下线',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '状态，0-启用，1-停用',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='培训课程表（历史）';

-- ----------------------------
-- Table structure for hyd_result_coupon_amount
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_coupon_amount`;
CREATE TABLE `hyd_result_coupon_amount` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sendAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发放金额',
  `receiveCount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '领券人次',
  `usedCount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券人次',
  `usedRatio` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券资金占比',
  `useCouponAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券金额',
  `orderAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单金额',
  `orderRatio` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '拉动消费比',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-消费券总金额-查询表';

-- ----------------------------
-- Table structure for hyd_result_coupon_amount_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_coupon_amount_history`;
CREATE TABLE `hyd_result_coupon_amount_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sendAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发放金额',
  `receiveCount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '领券人次',
  `usedCount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券人次',
  `usedRatio` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券资金占比',
  `useCouponAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券金额',
  `orderAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单金额',
  `orderRatio` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '拉动消费比',
  `activityId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动id',
  `activityName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动名称',
  `groupId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组id',
  `groupName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-消费券总金额-历史表';

-- ----------------------------
-- Table structure for hyd_result_coupon_stadium_top
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_coupon_stadium_top`;
CREATE TABLE `hyd_result_coupon_stadium_top` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `stadiumName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '场馆名称',
  `couponAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消费券金额',
  `activityId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动id',
  `activityName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动名称',
  `groupId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组id',
  `groupName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-消费券场馆预订Top';

-- ----------------------------
-- Table structure for hyd_result_coupon_stadium_top_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_coupon_stadium_top_history`;
CREATE TABLE `hyd_result_coupon_stadium_top_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `stadiumName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '场馆名称',
  `couponAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消费券金额',
  `activityId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动id',
  `activityName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动名称',
  `groupId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组id',
  `groupName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-消费券场馆预订Top';

-- ----------------------------
-- Table structure for hyd_result_coupon_user
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_coupon_user`;
CREATE TABLE `hyd_result_coupon_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `receiveCouponNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '领券人数',
  `useCouponNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券人数',
  `maleNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '男性人数',
  `femaleNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '女性人数',
  `activityId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动id',
  `activityName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动名称',
  `groupId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组id',
  `groupName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型',
  `batchNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-券用户分析';

-- ----------------------------
-- Table structure for hyd_result_coupon_user_age
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_coupon_user_age`;
CREATE TABLE `hyd_result_coupon_user_age` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `under25Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '25岁以下',
  `bt26and30Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '26-30岁',
  `bt31and35Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '31-35岁',
  `bt36and40Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '36-40岁',
  `bt41and45Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '41-45岁',
  `bt46and50Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '46-50岁',
  `bt51and60Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '51-60岁',
  `over60Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '60岁以上',
  `activityId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动id',
  `activityName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动名称',
  `groupId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组id',
  `groupName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-券用户年龄分布';

-- ----------------------------
-- Table structure for hyd_result_coupon_user_age_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_coupon_user_age_history`;
CREATE TABLE `hyd_result_coupon_user_age_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `under25Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '25岁以下',
  `bt26and30Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '26-30岁',
  `bt31and35Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '31-35岁',
  `bt36and40Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '36-40岁',
  `bt41and45Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '41-45岁',
  `bt46and50Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '46-50岁',
  `bt51and60Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '51-60岁',
  `over60Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '60岁以上',
  `activityId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动id',
  `activityName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动名称',
  `groupId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组id',
  `groupName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-券用户年龄分布';

-- ----------------------------
-- Table structure for hyd_result_coupon_user_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_coupon_user_history`;
CREATE TABLE `hyd_result_coupon_user_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `receiveCouponNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '领券人数',
  `useCouponNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券人数',
  `maleNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '男性人数',
  `femaleNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '女性人数',
  `activityId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动id',
  `activityName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动名称',
  `groupId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组id',
  `groupName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-券用户分析';

-- ----------------------------
-- Table structure for hyd_result_events_month_count_stat
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_events_month_count_stat`;
CREATE TABLE `hyd_result_events_month_count_stat` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `eventMonth` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '月份',
  `eventCount` bigint DEFAULT NULL COMMENT '赛事数量',
  `statisticalYear` int NOT NULL COMMENT '统计年度，如2024',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='大众赛事-各月办赛数据';

-- ----------------------------
-- Table structure for hyd_result_events_overview_stat
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_events_overview_stat`;
CREATE TABLE `hyd_result_events_overview_stat` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `total` bigint DEFAULT NULL COMMENT '总赛事场次',
  `participantCount` bigint DEFAULT NULL COMMENT '总参与人数',
  `internationalCount` bigint DEFAULT NULL COMMENT '国际赛事',
  `nationalCount` bigint DEFAULT NULL COMMENT '国家级赛事',
  `provinceCount` bigint DEFAULT NULL COMMENT '省级赛事',
  `cityCount` bigint DEFAULT NULL COMMENT '市级赛事',
  `statisticalYear` int NOT NULL COMMENT '统计年度，如2024',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='大众赛事-总览信息';

-- ----------------------------
-- Table structure for hyd_result_events_participant_level
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_events_participant_level`;
CREATE TABLE `hyd_result_events_participant_level` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `participantLevel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '人数人档级别',
  `count` bigint DEFAULT NULL COMMENT '数量',
  `statisticalYear` int NOT NULL COMMENT '统计年度，如2024',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='大众赛事-参赛人数人档';

-- ----------------------------
-- Table structure for hyd_result_events_sport_item_top
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_events_sport_item_top`;
CREATE TABLE `hyd_result_events_sport_item_top` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sportItem` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目',
  `count` bigint DEFAULT NULL COMMENT '数量',
  `statisticalYear` int NOT NULL COMMENT '统计年度，如2024',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='大众赛事-赛事数量TOP5项目';

-- ----------------------------
-- Table structure for hyd_result_facility
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_facility`;
CREATE TABLE `hyd_result_facility` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `facilityTypeName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设施类型名称',
  `facilityNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设施数量',
  `facilityPercentage` decimal(5,2) DEFAULT NULL COMMENT '设施占比（%）',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-设施全貌-查询表';

-- ----------------------------
-- Table structure for hyd_result_facility_district
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_facility_district`;
CREATE TABLE `hyd_result_facility_district` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `facilityTypeName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设施类型名称',
  `districtName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区名称',
  `facilityNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设施数量',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-设施各区分布-查询表';

-- ----------------------------
-- Table structure for hyd_result_facility_district_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_facility_district_history`;
CREATE TABLE `hyd_result_facility_district_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `facilityTypeName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设施类型名称',
  `districtName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区名称',
  `facilityNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设施数量',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-设施各区分布-历史表';

-- ----------------------------
-- Table structure for hyd_result_facility_district_month
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_facility_district_month`;
CREATE TABLE `hyd_result_facility_district_month` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `districtName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区名称',
  `monthInspectYes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本月已巡检',
  `monthInspectNo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本月待巡检',
  `monthRepairYes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本月已维修',
  `monthRepairNo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本月待维修',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-设施各区月数据-查询表';

-- ----------------------------
-- Table structure for hyd_result_facility_district_month_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_facility_district_month_history`;
CREATE TABLE `hyd_result_facility_district_month_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `districtName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区名称',
  `monthInspectYes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本月已巡检',
  `monthInspectNo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本月待巡检',
  `monthRepairYes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本月已维修',
  `monthRepairNo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本月待维修',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-设施各区月数据-历史表';

-- ----------------------------
-- Table structure for hyd_result_facility_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_facility_history`;
CREATE TABLE `hyd_result_facility_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `facilityTypeName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设施类型名称',
  `facilityNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设施数量',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-设施全貌-历史表';

-- ----------------------------
-- Table structure for hyd_result_facility_inspect
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_facility_inspect`;
CREATE TABLE `hyd_result_facility_inspect` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设施点编号',
  `districtName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属区县',
  `streetName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属街道',
  `location` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '安装点名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '巡检单类型',
  `inspector` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '巡检人名称',
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '巡检完成状态',
  `completeTime` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '巡检完成时间',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-巡检维修动态-查询表';

-- ----------------------------
-- Table structure for hyd_result_facility_inspect_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_facility_inspect_history`;
CREATE TABLE `hyd_result_facility_inspect_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设施点编号',
  `districtName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属区县',
  `streetName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属街道',
  `location` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '安装点名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '巡检单类型',
  `inspector` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '巡检人名称',
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '巡检完成状态',
  `completeTime` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '巡检完成时间',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-巡检维修动态-历史表';

-- ----------------------------
-- Table structure for hyd_result_facility_year
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_facility_year`;
CREATE TABLE `hyd_result_facility_year` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `quantity` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '健身点位数量',
  `yearInspectRate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本年巡检率',
  `yearInspectYes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本年已巡检',
  `yearInspectNo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本年待巡检',
  `yearRepairRate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本年维修完成率',
  `yearRepairYes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本年已维修',
  `yearRepairNo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本年待维修',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-健身点位年数据-查询表';

-- ----------------------------
-- Table structure for hyd_result_facility_year_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_facility_year_history`;
CREATE TABLE `hyd_result_facility_year_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `quantity` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '健身点位数量',
  `yearInspectRate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本年巡检率',
  `yearInspectYes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本年已巡检',
  `yearInspectNo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本年待巡检',
  `yearRepairRate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本年维修完成率',
  `yearRepairYes` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本年已维修',
  `yearRepairNo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本年待维修',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-健身点位年数据-历史表';

-- ----------------------------
-- Table structure for hyd_result_instructor_level
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_instructor_level`;
CREATE TABLE `hyd_result_instructor_level` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `level` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '级别',
  `personCount` bigint DEFAULT NULL COMMENT '数量',
  `proportion` decimal(5,2) DEFAULT NULL COMMENT '占比（%）',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='社会体育指导员-级别统计';

-- ----------------------------
-- Table structure for hyd_result_instructor_overview
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_instructor_overview`;
CREATE TABLE `hyd_result_instructor_overview` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `total` bigint DEFAULT NULL COMMENT '全市人数',
  `newCount` bigint DEFAULT NULL COMMENT '今年新增',
  `sexRatio` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '男女比例',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='社会体育指导员-概览';

-- ----------------------------
-- Table structure for hyd_result_instructor_region
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_instructor_region`;
CREATE TABLE `hyd_result_instructor_region` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `region` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地区',
  `instructorCount` bigint DEFAULT NULL COMMENT '人员数量',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='社会体育指导员-各区指导人员统计';

-- ----------------------------
-- Table structure for hyd_result_instructor_service_project
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_instructor_service_project`;
CREATE TABLE `hyd_result_instructor_service_project` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `serviceProject` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '服务项目',
  `totalPersonCount` bigint DEFAULT NULL COMMENT '人数',
  `newPersonCount` bigint DEFAULT NULL COMMENT '新增人数',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='社会体育指导员-项目统计';

-- ----------------------------
-- Table structure for hyd_result_instructor_service_project_top
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_instructor_service_project_top`;
CREATE TABLE `hyd_result_instructor_service_project_top` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `serviceProject` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '服务项目',
  `personCount` bigint DEFAULT NULL COMMENT '数量',
  `proportion` decimal(5,2) DEFAULT NULL COMMENT '占比（%）',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='社会体育指导员-指导项目统计TOP15';

-- ----------------------------
-- Table structure for hyd_result_instructor_usersex
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_instructor_usersex`;
CREATE TABLE `hyd_result_instructor_usersex` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `gender` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `personCount` bigint DEFAULT NULL COMMENT '数量',
  `proportion` decimal(5,2) DEFAULT NULL COMMENT '占比（%）',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='社会体育指导员-性别统计';

-- ----------------------------
-- Table structure for hyd_result_la_stadium_district
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_la_stadium_district`;
CREATE TABLE `hyd_result_la_stadium_district` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `districtName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地区',
  `stadiumNum` bigint DEFAULT NULL COMMENT '数量',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='校外培训机构-各区场馆数量统计';

-- ----------------------------
-- Table structure for hyd_result_la_stadium_sport_name
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_la_stadium_sport_name`;
CREATE TABLE `hyd_result_la_stadium_sport_name` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sportName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地区',
  `num` bigint DEFAULT NULL COMMENT '数量',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='校外培训机构-项目类型';

-- ----------------------------
-- Table structure for hyd_result_la_stadium_sport_name_top
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_la_stadium_sport_name_top`;
CREATE TABLE `hyd_result_la_stadium_sport_name_top` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sportName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地区',
  `num` bigint DEFAULT NULL COMMENT '数量',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='校外培训机构-项目类型占比TOP10';

-- ----------------------------
-- Table structure for hyd_result_order
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_order`;
CREATE TABLE `hyd_result_order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orderNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单总数',
  `orderAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单总金额',
  `couponAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消费券总金额',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-订单数量-查询表';

-- ----------------------------
-- Table structure for hyd_result_order_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_order_history`;
CREATE TABLE `hyd_result_order_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orderNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单总数',
  `orderAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单总金额',
  `couponAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消费券总金额',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-订单数量-历史表';

-- ----------------------------
-- Table structure for hyd_result_order_month
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_order_month`;
CREATE TABLE `hyd_result_order_month` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `month` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '月份',
  `orderNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数量',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-订单趋势-查询表';

-- ----------------------------
-- Table structure for hyd_result_order_month_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_order_month_history`;
CREATE TABLE `hyd_result_order_month_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `month` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '月份',
  `orderNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数量',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-订单趋势-历史表';

-- ----------------------------
-- Table structure for hyd_result_order_sport
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_order_sport`;
CREATE TABLE `hyd_result_order_sport` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sportName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目名称',
  `orderAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消费券订单金额',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-项目消费券订单金额Top5-查询表';

-- ----------------------------
-- Table structure for hyd_result_order_sport_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_order_sport_history`;
CREATE TABLE `hyd_result_order_sport_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sportName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目名称',
  `orderAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消费券订单金额',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-项目消费券订单金额Top5-历史表';

-- ----------------------------
-- Table structure for hyd_result_order_stadium
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_order_stadium`;
CREATE TABLE `hyd_result_order_stadium` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `stadiumName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '场馆名称',
  `orderAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消费券订单金额',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-场馆消费券订单金额Top5-查询表';

-- ----------------------------
-- Table structure for hyd_result_order_stadium_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_order_stadium_history`;
CREATE TABLE `hyd_result_order_stadium_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `stadiumName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '场馆名称',
  `orderAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消费券订单金额',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-场馆消费券订单金额Top5-历史表';

-- ----------------------------
-- Table structure for hyd_result_order_ykt_course_stat
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_order_ykt_course_stat`;
CREATE TABLE `hyd_result_order_ykt_course_stat` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `course` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程',
  `num` bigint DEFAULT NULL COMMENT '数量',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='青少年技能培训-课程热度排行TOP5';

-- ----------------------------
-- Table structure for hyd_result_order_ykt_district_stat
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_order_ykt_district_stat`;
CREATE TABLE `hyd_result_order_ykt_district_stat` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `district` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属区',
  `districtName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属区名称',
  `num` bigint DEFAULT NULL COMMENT '数量',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='青少年技能培训-各区机构数量统计';

-- ----------------------------
-- Table structure for hyd_result_order_ykt_project_stat
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_order_ykt_project_stat`;
CREATE TABLE `hyd_result_order_ykt_project_stat` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `project` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目',
  `num` bigint DEFAULT NULL COMMENT '数量',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='青少年技能培训-热门项目机构数量统计';

-- ----------------------------
-- Table structure for hyd_result_order_ykt_stadium_stat
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_order_ykt_stadium_stat`;
CREATE TABLE `hyd_result_order_ykt_stadium_stat` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `stadium` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '培训场馆',
  `orderAmount` int DEFAULT NULL COMMENT '总金额',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='青少年技能培训-培训场馆销售统计TOP10';

-- ----------------------------
-- Table structure for hyd_result_order_ykt_userage_stat
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_order_ykt_userage_stat`;
CREATE TABLE `hyd_result_order_ykt_userage_stat` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ageGroup` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '年龄分布',
  `num` bigint DEFAULT NULL COMMENT '数量',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='青少年技能培训-年龄分布';

-- ----------------------------
-- Table structure for hyd_result_order_ykt_usersex_stat
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_order_ykt_usersex_stat`;
CREATE TABLE `hyd_result_order_ykt_usersex_stat` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `gender` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `num` bigint DEFAULT NULL COMMENT '数量',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='青少年技能培训-性别统计';

-- ----------------------------
-- Table structure for hyd_result_stadium
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_stadium`;
CREATE TABLE `hyd_result_stadium` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `onlineStadiumNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '在线场馆数量',
  `couponStadiumNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消费券场馆数量',
  `socialStadiumNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '社会场馆数量',
  `publicStadiumNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公共场馆数量',
  `useCouponStadiumNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '累计用券场馆数量',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-在线场馆数量';

-- ----------------------------
-- Table structure for hyd_result_stadium_district
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_stadium_district`;
CREATE TABLE `hyd_result_stadium_district` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `district` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属区',
  `districtName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属区名称',
  `stadiumNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '定点场馆数量',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-在线场馆各区情况';

-- ----------------------------
-- Table structure for hyd_result_stadium_district_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_stadium_district_history`;
CREATE TABLE `hyd_result_stadium_district_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `district` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属区',
  `districtName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属区名称',
  `couponStadiumNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '定点场馆数量',
  `publicStadiumNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公共场馆数量',
  `socialStadiumNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '社会场馆数量',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-在线场馆各区情况';

-- ----------------------------
-- Table structure for hyd_result_stadium_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_stadium_history`;
CREATE TABLE `hyd_result_stadium_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `onlineStadiumNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '在线场馆数量',
  `couponStadiumNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消费券场馆数量',
  `socialStadiumNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '社会场馆数量',
  `publicStadiumNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公共场馆数量',
  `useCouponStadiumNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '累计用券场馆数量',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-在线场馆数量';

-- ----------------------------
-- Table structure for hyd_result_stadium_map
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_stadium_map`;
CREATE TABLE `hyd_result_stadium_map` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resourceType` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源类型',
  `resourceName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `resourceNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源数量',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-电子地图-查询表';

-- ----------------------------
-- Table structure for hyd_result_stadium_map_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_stadium_map_history`;
CREATE TABLE `hyd_result_stadium_map_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resourceType` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源类型',
  `resourceName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `resourceNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源数量',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-电子地图-历史表';

-- ----------------------------
-- Table structure for hyd_result_stadium_map_point
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_stadium_map_point`;
CREATE TABLE `hyd_result_stadium_map_point` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resourceType` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源类型',
  `resourceName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `longitude` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经度',
  `latitude` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '纬度',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-电子地图点位信息-查询表';

-- ----------------------------
-- Table structure for hyd_result_stadium_map_point_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_stadium_map_point_history`;
CREATE TABLE `hyd_result_stadium_map_point_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resourceType` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源类型',
  `resourceName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `longitude` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经度',
  `latitude` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '纬度',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育基础设施-电子地图点位信息-历史表';

-- ----------------------------
-- Table structure for hyd_result_stadium_sport_coupon
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_stadium_sport_coupon`;
CREATE TABLE `hyd_result_stadium_sport_coupon` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sportName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '运动项目',
  `sportNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目数量',
  `useCountRate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券占比',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-运动项目分布用券数占比';

-- ----------------------------
-- Table structure for hyd_result_stadium_sport_coupon_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_stadium_sport_coupon_history`;
CREATE TABLE `hyd_result_stadium_sport_coupon_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sportName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '运动项目',
  `sportNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目数量',
  `useCountRate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券占比',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-运动项目分布用券数占比';

-- ----------------------------
-- Table structure for hyd_result_stock
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_stock`;
CREATE TABLE `hyd_result_stock` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `stockName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '券名称',
  `receiveNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '领券数',
  `useNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券数',
  `useRate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券率',
  `useCouponAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券金额',
  `orderAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单金额',
  `orderRate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '拉动消费比',
  `activityId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动id',
  `activityName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动名称',
  `groupId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组id',
  `groupName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-消费券领卷用券-查询表';

-- ----------------------------
-- Table structure for hyd_result_stock_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_stock_history`;
CREATE TABLE `hyd_result_stock_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `stockName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '券名称',
  `receiveNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '领券数',
  `useNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券数',
  `useRate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券率',
  `useCouponAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券金额',
  `orderAmount` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单金额',
  `orderRate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '拉动消费比',
  `activityId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动id',
  `activityName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动名称',
  `groupId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组id',
  `groupName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='体育消费卷-消费券领卷用券-历史表';

-- ----------------------------
-- Table structure for hyd_result_user_age
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_user_age`;
CREATE TABLE `hyd_result_user_age` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `under18Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '18岁以下',
  `bt18and25Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '18-25岁',
  `bt26and30Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '26-30岁',
  `bt31and35Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '31-35岁',
  `bt36and40Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '36-40岁',
  `bt41and45Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '41-45岁',
  `bt46and50Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '46-50岁',
  `over50Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '50岁以上',
  `activityId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动id',
  `activityName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动名称',
  `groupId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组id',
  `groupName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-年龄占比';

-- ----------------------------
-- Table structure for hyd_result_user_age_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_user_age_history`;
CREATE TABLE `hyd_result_user_age_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `under18Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '18岁以下',
  `bt18and25Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '18-25岁',
  `bt26and30Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '26-30岁',
  `bt31and35Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '31-35岁',
  `bt36and40Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '36-40岁',
  `bt41and45Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '41-45岁',
  `bt46and50Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '46-50岁',
  `over50Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '50岁以上',
  `activityId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动id',
  `activityName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动名称',
  `groupId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组id',
  `groupName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-年龄占比';

-- ----------------------------
-- Table structure for hyd_result_user_channel
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_user_channel`;
CREATE TABLE `hyd_result_user_channel` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `registerUserNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '注册用户',
  `realNameUserNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '实名用户',
  `receiveCouponUserNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '领券用户',
  `useCouponUserNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券用户',
  `orderUserNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下单用户',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-用户来源渠道';

-- ----------------------------
-- Table structure for hyd_result_user_channel_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_user_channel_history`;
CREATE TABLE `hyd_result_user_channel_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `registerUserNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '注册用户',
  `realNameUserNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '实名用户',
  `receiveCouponUserNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '领券用户',
  `useCouponUserNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用券用户',
  `orderUserNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下单用户',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-用户来源渠道';

-- ----------------------------
-- Table structure for hyd_result_user_register
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_user_register`;
CREATE TABLE `hyd_result_user_register` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `month` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '月份',
  `userNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数量',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-每月新增用户';

-- ----------------------------
-- Table structure for hyd_result_user_register_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_user_register_history`;
CREATE TABLE `hyd_result_user_register_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `month` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '月份',
  `userNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数量',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-每月新增用户';

-- ----------------------------
-- Table structure for hyd_result_user_repurchase
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_user_repurchase`;
CREATE TABLE `hyd_result_user_repurchase` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `1Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '一次下单',
  `bt2and5Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '2-5次',
  `over5Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '5次以上',
  `over10Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '10次以上',
  `over50Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '50次以上',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-复购率';

-- ----------------------------
-- Table structure for hyd_result_user_repurchase_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_user_repurchase_history`;
CREATE TABLE `hyd_result_user_repurchase_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `1Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '一次下单',
  `bt2and5Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '2-5次',
  `over5Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '5次以上',
  `over10Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '10次以上',
  `over50Num` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '50次以上',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-复购率';

-- ----------------------------
-- Table structure for hyd_result_user_sex
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_user_sex`;
CREATE TABLE `hyd_result_user_sex` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sex` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `sexNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数量',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-男女占比';

-- ----------------------------
-- Table structure for hyd_result_user_sex_history
-- ----------------------------
DROP TABLE IF EXISTS `hyd_result_user_sex_history`;
CREATE TABLE `hyd_result_user_sex_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sex` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `sexNum` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数量',
  `batchNo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批次号，用于标识数据批次',
  `statisticalYear` int DEFAULT NULL COMMENT '统计年度',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `importTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '导入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='场馆预定-男女占比';

-- ----------------------------
-- Table structure for hyd_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `hyd_sys_config`;
CREATE TABLE `hyd_sys_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `key` varchar(100) NOT NULL COMMENT '键',
  `value` varchar(500) NOT NULL COMMENT '值',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注说明',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_key` (`key`) COMMENT '模块名称唯一'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统模块-配置表';

INSERT INTO `hyd_sys_config` (`id`, `key`, `value`, `remark`, `createdTime`, `updateTime`) VALUES (1, '体育基础设施', 'false', '是否冻结查询表数据', '2025-09-13 07:54:18', '2025-09-13 11:47:59');
INSERT INTO `hyd_sys_config` (`id`, `key`, `value`, `remark`, `createdTime`, `updateTime`) VALUES (2, '体育消费券', 'false', '是否冻结查询表数据', '2025-09-13 07:54:29', '2025-09-13 12:06:04');
INSERT INTO `hyd_sys_config` (`id`, `key`, `value`, `remark`, `createdTime`, `updateTime`) VALUES (3, '场馆预定', 'false', '是否冻结查询表数据', '2025-09-13 07:55:11', '2025-09-13 12:06:06');
INSERT INTO `hyd_sys_config` (`id`, `key`, `value`, `remark`, `createdTime`, `updateTime`) VALUES (4, '校外培训机构', 'false', '是否冻结查询表数据', '2025-09-13 07:55:28', '2025-09-13 12:06:08');
INSERT INTO `hyd_sys_config` (`id`, `key`, `value`, `remark`, `createdTime`, `updateTime`) VALUES (5, '青少年技能培训', 'false', '是否冻结查询表数据', '2025-09-13 07:55:35', '2025-09-13 12:06:09');

-- ----------------------------
-- Table structure for hyd_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `hyd_sys_user`;
CREATE TABLE `hyd_sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(180) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `gender` varchar(2) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号码',
  `email` varchar(180) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `avatar_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像地址',
  `avatar_path` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像真实路径',
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `enabled` bit(1) DEFAULT NULL COMMENT '状态：1启用、0禁用',
  `create_by` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新者',
  `pwd_reset_time` datetime DEFAULT NULL COMMENT '修改密码的时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `uniq_email` (`email`) USING BTREE,
  UNIQUE KEY `uniq_username` (`username`) USING BTREE,
  KEY `idx_enabled` (`enabled`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='系统用户';

INSERT INTO `hyd_sys_user` (`user_id`, `username`, `nick_name`, `gender`, `phone`, `email`, `avatar_name`, `avatar_path`, `password`, `enabled`, `create_by`, `update_by`, `pwd_reset_time`, `create_time`, `update_time`) VALUES (1, 'admin', '管理员', '男', '18888888888', '201507802@qq.com', 'avatar-20250122102642222.png', '/~/avatar/avatar-20250122102642222.png', '$2a$10$Egp1/gvFlt7zhlXVfEFw4OfWQCGPw0ClmMcc6FjTnvXNRVf9zdMRa', b'1', NULL, 'admin', '2025-05-03 16:38:31', '2025-08-23 09:11:56', '2025-07-22 10:26:42');
INSERT INTO `hyd_sys_user` (`user_id`, `username`, `nick_name`, `gender`, `phone`, `email`, `avatar_name`, `avatar_path`, `password`, `enabled`, `create_by`, `update_by`, `pwd_reset_time`, `create_time`, `update_time`) VALUES (2, 'test', '测试', '男', '19999999999', '231@qq.com', NULL, NULL, '$2a$10$4XcyudOYTSz6fue6KFNMHeUQnCX5jbBQypLEnGk1PmekXt5c95JcK', b'1', 'admin', 'admin', NULL, '2025-05-05 11:15:49', '2025-07-21 14:53:04');
