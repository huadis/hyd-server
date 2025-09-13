# hyd-server
武汉市体育数据大屏，对接汉运动平台的数据

# 数据源
> - 共 103 张表
> - 24\*2 结果表 + 10 原始表 + 11\*2 excel(大众赛事、体育产业、社会体育指导员) + 2 excel(体育组织) + 6张青少年技能培训 + 4 大众赛事 + 3 校外培训机构 + 6 社会体育指导员 + 2 系统表
> - 场馆表（hyd_origin_stadium_history） 项目表（hyd_origin_stadium_item_history） 课程表（hyd_origin_training_course_history） 是一直新增的，关联时，不需要带时间

- API 24结果表数据（场馆预定、体育消费卷、基础体育设施） -- 本身就是结果表， 历史和查询表，总共 48 张表
- API 10原始表数据（青少年技能培训、校外培训机构）      -- 涉及多表关联，每晚定时触发计算， 总共 10 张表 
- Excel 24张表数据（大众赛事、体育产业、社会体育指导员） -- 体育产业的excel就是结果表，大众赛事和社会体育指导员导入时计算，总共 张表 22 张表
- Excel （体育组织）                                -- 用于首页展示

> 1. 23张结果表是全量推送（先清空表中非今日的数据，再插入）
> 2. 10张原始表是增量更新，追加到表中
> 3. excel上传的，由增量改为全量（先清空表中数据，再插入）
> UPDATE hyd_excel_instructor_info_history SET batchNo = 'BATCH_20250829' WHERE DATE(createdTime) = '2025-08-29';

# 定时任务
- 青少年技能培训(每天凌晨 00:00 执行)
- 校外培训机构(每天凌晨 00:00 执行)

# 各模块数据计算

## 青少年技能培训

### 各区机构数量统计 
- `hyd_result_order_ykt_district_stat` 
> 基于 `hyd_origin_stadium_history`, `hyd_origin_order_history` 计算

### 性别统计
- `hyd_result_order_ykt_usersex_stat`  
> 基于  `hyd_origin_order_history` 计算

### 热门项目机构数量统计
- `hyd_result_order_ykt_project_stat`  
> 基于 `hyd_origin_order_history` ,`hyd_origin_stadium_item_history` 计算


### 培训场馆地图
- `hyd_origin_order_history`
- `hyd_origin_stadium_history`

`select DISTINCT b.* from hyd_origin_order_history a, hyd_origin_stadium_history b where a.stadiumId = b.id`

### 课程热度排行（TOP5）
- `hyd_result_order_ykt_course_stat`
> 基于 `hyd_origin_training_course_history`, `hyd_origin_order_history` 计算

### 年龄分布
- `hyd_result_order_ykt_userage_stat`
> 基于 `hyd_origin_order_history` 计算

### 培训场馆销售统计（top10）
- `hyd_result_order_ykt_stadium_stat`
> 基于 `hyd_origin_order_history`, `hyd_origin_stadium_history` 计算


## 校外培训机构
### 各区场馆数量统计
- `hyd_result_la_stadium_district`
-  基于 `hyd_origin_la_stadium_history` 计算

### 项目类型占比TOP10
- `hyd_result_la_stadium_sport_name_top`
-  基于 `hyd_origin_training_activity_item_history` 计算

### 校外培训机构分布图 - 接口同 `各区场馆数量统计`
- `hyd_result_la_stadium_district`

### 首页 - 项目类型占比Top5和其他
- `hyd_result_la_stadium_sport_name`
-  基于 `hyd_origin_training_activity_item_history` 计算


## 场馆预定
### 各区场馆数量统计
- `hyd_result_stadium_district`

### 项目消费券订单金额
- `hyd_result_order_sport`

### 场馆消费券订单金额TOP5
- `hyd_result_order_stadium`

### 订单数量(订单月份趋势统计 - 同 体育消费卷-订单趋势图一样)
- `hyd_result_order_month`

### 场馆分布地图
- `hyd_origin_stadium_history`


### 用户
- `hyd_result_user_channel` 
- `hyd_result_user_sex` -- 性别占比
- `hyd_result_user_age` -- 年龄占比


### 复购率
- `hyd_result_user_repurchase`


### 新增用户
- `hyd_result_user_register`




## 体育消费券

### 概览
- `hyd_result_coupon_amount`
> 汇聚多条记录

### 武汉市体育消费卷-领卷用卷
- `hyd_result_stock`

### 武汉市体育消费卷-用卷订单
- `hyd_result_stock`

### 项目消费卷订单金额TOP5
- `hyd_result_order_sport`

### 场馆消费卷订单金额TOP5
- `hyd_result_order_stadium`

### 订单总数统计
- `hyd_result_order`

### 订单月份趋势统计
- `hyd_result_order_month`

### 用户性别统计
- `hyd_result_coupon_user`

### 用户年龄分布统计
- `hyd_result_coupon_user_age`

### 用户领卷用卷统计
- `hyd_result_coupon_user`



## 体育产业
### 总览
- `hyd_excel_industry_core_indicators`

### 体育产业总规模
- `hyd_excel_industry_scale_trend`

### 体育产业市场主体数量
- `hyd_excel_industry_entity_count_ratio`

### 体育产业总增速和增加值
- `hyd_excel_industry_growth_value_trend`

### 体育产业从业人员数量
- `hyd_excel_industry_employee_count`

## 基础体育设施

### 健身点位
- `hyd_result_facility_year`

### 各类型体育设施数量及占比
- `hyd_result_facility`
> 汇聚多条记录

### 各区分布
- `hyd_result_facility_district`

### 基础设施点位地图
- `hyd_result_stadium_map_point`


### 巡检维修动态
- `hyd_result_facility_inspect`

### 各区巡检维修数据（月度）
- `hyd_result_facility_district_month`



## 社会体育指导员

计算 是 导入excel时 触发 计算

### 指导项目统计TOP15
- `hyd_result_instructor_service_project_top`
> 基于 `hyd_excel_instructor_info` 计算

### 性别统计
- `hyd_result_instructor_usersex`
> 基于 `hyd_excel_instructor_info` 计算


### 级别统计
- `hyd_result_instructor_level`
> 基于 `hyd_excel_instructor_info` 计算


### 年龄统计
- `hyd_excel_instructor_age_stats`


### 人数增长统计
- `hyd_excel_instructor_age_growth`


### 各区指导员人数
- `hyd_result_instructor_region`
> 基于 `hyd_excel_instructor_info` 计算


### 总览-首页用
- `hyd_result_instructor_overview`
> 基于 `hyd_excel_instructor_info` 计算


### 指导项目统计-首页用
- `hyd_result_instructor_service_project`
> 基于 `hyd_excel_instructor_info` 计算


## 大众赛事

计算 是 导入excel时 触发 计算

### 总览信息
- `hyd_result_events_overview_stat`
> 基于 `hyd_excel_public_events` 计算

### 各月办赛数据
- `hyd_result_events_month_count_stat`
> 基于 `hyd_excel_public_events` 计算


### 赛事数量TOP5项目
- `hyd_result_events_sport_item_top`
> 基于 `hyd_excel_public_events` 计算


### 赛事活动办赛地点热力图
- `hyd_excel_public_events`
> 基于 `hyd_excel_public_events` 计算


### 参赛人数人档
- `hyd_result_events_participant_level`
> 基于 `hyd_excel_public_events` 计算


### 最新赛事
- `hyd_excel_public_events`
> 基于 `hyd_excel_public_events` 计算



# 数据库表信息表
| 序号 | 表名                                  | 表注释                                  | 是否启用 |
| ---- | ------------------------------------- | --------------------------------------- | -------- |
| 1    | hyd_excel_industry_core_indicators    | 体育产业-核心指标总表                  | 0        |
| 2    | hyd_excel_industry_core_indicators_history | 体育产业（历史）-核心指标总表          | 0        |
| 3    | hyd_excel_industry_employee_count     | 体育产业-从业人员数量（分类统计）表    | 0        |
| 4    | hyd_excel_industry_employee_count_history | 体育产业（历史）-从业人员数量（分类统计）表 | 0        |
| 5    | hyd_excel_industry_entity_count_ratio | 体育产业-市场主体数量（分类占比）表    | 0        |
| 6    | hyd_excel_industry_entity_count_ratio_history | 体育产业（历史）-市场主体数量（分类占比）表 | 0        |
| 7    | hyd_excel_industry_goods_purchase_rate | 体育产业-居民体育用品购买率表          | 0        |
| 8    | hyd_excel_industry_goods_purchase_rate_history | 体育产业（历史）-居民体育用品购买率表  | 0        |
| 9    | hyd_excel_industry_growth_value_trend | 体育产业-总增速和增加值（年度趋势）表  | 0        |
| 10   | hyd_excel_industry_growth_value_trend_history | 体育产业（历史）-总增速和增加值（年度趋势）表 | 0        |
| 11   | hyd_excel_industry_scale_trend        | 体育产业-总规模（年度趋势）表          | 0        |
| 12   | hyd_excel_industry_scale_trend_history | 体育产业（历史）-总规模（年度趋势）表  | 0        |
| 13   | hyd_excel_industry_training_participation_rate | 体育产业-居民体育培训项目参与率表      | 0        |
| 14   | hyd_excel_industry_training_participation_rate_history | 体育产业（历史）-居民体育培训项目参与率表 | 0        |
| 15   | hyd_excel_instructor_age_growth       | 体育指导员-人数增长统计明细表          | 0        |
| 16   | hyd_excel_instructor_age_growth_history | 体育指导员（历史）-人数增长统计明细表  | 0        |
| 17   | hyd_excel_instructor_age_stats        | 体育指导员-年龄统计明细表              | 0        |
| 18   | hyd_excel_instructor_age_stats_history | 体育指导员（历史）-年龄统计明细表      | 0        |
| 19   | hyd_excel_instructor_info             | 体育指导员-汇总                        | 0        |
| 20   | hyd_excel_instructor_info_history     | 体育指导员（历史）-汇总                | 0        |
| 21   | hyd_excel_public_events               | 大众赛事-体育赛事信息表                | 0        |
| 22   | hyd_excel_public_events_history       | 大众赛事（历史）-体育赛事信息表        | 0        |
| 23   | hyd_excel_sports_organization         | 体育组织                                | 0        |
| 24   | hyd_excel_sports_organization_district_stat | 体育组织-区属统计                      | 0        |
| 25   | hyd_origin_la_stadium                 | 校外培训机构表                        | 0        |
| 26   | hyd_origin_la_stadium_file            | 校外培训机构附件表                    | 0        |
| 27   | hyd_origin_la_stadium_file_history    | 校外培训机构附件表（历史）            | 0        |
| 28   | hyd_origin_la_stadium_history         | 校外培训机构表（历史）                | 0        |
| 29   | hyd_origin_order                      | 订单表                                | 0        |
| 30   | hyd_origin_order_history              | 订单表（历史）                        | 0        |
| 31   | hyd_origin_stadium                    | 培训场馆表                            | 0        |
| 32   | hyd_origin_stadium_history            | 培训场馆表（历史）                    | 0        |
| 33   | hyd_origin_stadium_item               | 场馆培训项目表                        | 0        |
| 34   | hyd_origin_stadium_item_history       | 场馆培训项目表（历史）                | 0        |
| 35   | hyd_origin_tenant                     | 组织机构表                            | 0        |
| 36   | hyd_origin_tenant_history             | 组织机构表（历史）                    | 0        |
| 37   | hyd_origin_training_activity          | 培训活动表                            | 0        |
| 38   | hyd_origin_training_activity_history  | 培训活动表（历史）                    | 0        |
| 39   | hyd_origin_training_activity_item     | 培训活动支持的项目表                  | 0        |
| 40   | hyd_origin_training_activity_item_history | 培训活动支持的项目表（历史）          | 0        |
| 41   | hyd_origin_training_activity_item_stadium | 培训活动场馆支持的项目表              | 0        |
| 42   | hyd_origin_training_activity_item_stadium_history | 培训活动场馆支持的项目表（历史）      | 0        |
| 43   | hyd_origin_training_course            | 培训课程表                            | 0        |
| 44   | hyd_origin_training_course_history    | 培训课程表（历史）                    | 0        |
| 45   | hyd_result_coupon_amount              | 体育消费卷-消费券总金额-查询表        | 0        |
| 46   | hyd_result_coupon_amount_history      | 体育消费卷-消费券总金额-历史表        | 0        |
| 47   | hyd_result_coupon_stadium_top         | 场馆预定-消费券场馆预订Top            | 0        |
| 48   | hyd_result_coupon_stadium_top_history | 场馆预定-消费券场馆预订Top            | 0        |
| 49   | hyd_result_coupon_user                | 体育消费卷-券用户分析                  | 0        |
| 50   | hyd_result_coupon_user_age            | 体育消费卷-券用户年龄分布              | 0        |
| 51   | hyd_result_coupon_user_age_history    | 体育消费卷-券用户年龄分布              | 0        |
| 52   | hyd_result_coupon_user_history        | 体育消费卷-券用户分析                  | 0        |
| 53   | hyd_result_events_month_count_stat    | 大众赛事-各月办赛数据                  | 0        |
| 54   | hyd_result_events_overview_stat       | 大众赛事-总览信息                      | 0        |
| 55   | hyd_result_events_participant_level   | 大众赛事-参赛人数人档                  | 0        |
| 56   | hyd_result_events_sport_item_top      | 大众赛事-赛事数量TOP5项目              | 0        |
| 57   | hyd_result_facility                   | 体育基础设施-设施全貌-查询表          | 0        |
| 58   | hyd_result_facility_district          | 体育基础设施-设施各区分布-查询表      | 0        |
| 59   | hyd_result_facility_district_history  | 体育基础设施-设施各区分布-历史表      | 0        |
| 60   | hyd_result_facility_district_month    | 体育基础设施-设施各区月数据-查询表    | 0        |
| 61   | hyd_result_facility_district_month_history | 体育基础设施-设施各区月数据-历史表    | 0        |
| 62   | hyd_result_facility_history           | 体育基础设施-设施全貌-历史表          | 0        |
| 63   | hyd_result_facility_inspect           | 体育基础设施-巡检维修动态-查询表      | 0        |
| 64   | hyd_result_facility_inspect_history   | 体育基础设施-巡检维修动态-历史表      | 0        |
| 65   | hyd_result_facility_year              | 体育基础设施-健身点位年数据-查询表    | 0        |
| 66   | hyd_result_facility_year_history      | 体育基础设施-健身点位年数据-历史表    | 0        |
| 67   | hyd_result_instructor_level           | 社会体育指导员-级别统计                | 0        |
| 68   | hyd_result_instructor_overview        | 社会体育指导员-概览                    | 0        |
| 69   | hyd_result_instructor_region          | 社会体育指导员-各区指导人员统计        | 0        |
| 70   | hyd_result_instructor_service_project | 社会体育指导员-项目统计                | 0        |
| 71   | hyd_result_instructor_service_project_top | 社会体育指导员-指导项目统计TOP15      | 0        |
| 72   | hyd_result_instructor_usersex         | 社会体育指导员-性别统计                | 0        |
| 73   | hyd_result_la_stadium_district        | 校外培训机构-各区场馆数量统计          | 0        |
| 74   | hyd_result_la_stadium_sport_name      | 校外培训机构-项目类型                  | 0        |
| 75   | hyd_result_la_stadium_sport_name_top  | 校外培训机构-项目类型占比TOP10        | 0        |
| 76   | hyd_result_order                      | 体育消费卷-订单数量-查询表            | 0        |
| 77   | hyd_result_order_history              | 体育消费卷-订单数量-历史表            | 0        |
| 78   | hyd_result_order_month                | 体育消费卷-订单趋势-查询表            | 0        |
| 79   | hyd_result_order_month_history        | 体育消费卷-订单趋势-历史表            | 0        |
| 80   | hyd_result_order_sport                | 体育消费卷-项目消费券订单金额Top5-查询表 | 0        |
| 81   | hyd_result_order_sport_history        | 体育消费卷-项目消费券订单金额Top5-历史表 | 0        |
| 82   | hyd_result_order_stadium              | 体育消费卷-场馆消费券订单金额Top5-查询表 | 0        |
| 83   | hyd_result_order_stadium_history      | 体育消费卷-场馆消费券订单金额Top5-历史表 | 0        |
| 84   | hyd_result_order_ykt_course_stat      | 青少年技能培训-课程热度排行TOP5        | 0        |
| 85   | hyd_result_order_ykt_district_stat    | 青少年技能培训-各区机构数量统计        | 0        |
| 86   | hyd_result_order_ykt_project_stat     | 青少年技能培训-热门项目机构数量统计    | 0        |
| 87   | hyd_result_order_ykt_stadium_stat     | 青少年技能培训-培训场馆销售统计TOP10   | 0        |
| 88   | hyd_result_order_ykt_userage_stat     | 青少年技能培训-年龄分布                | 0        |
| 89   | hyd_result_order_ykt_usersex_stat     | 青少年技能培训-性别统计                | 0        |
| 90   | hyd_result_stadium                    | 场馆预定-在线场馆数量                  | 0        |
| 91   | hyd_result_stadium_district           | 场馆预定-在线场馆各区情况              | 0        |
| 92   | hyd_result_stadium_district_history   | 场馆预定-在线场馆各区情况              | 0        |
| 93   | hyd_result_stadium_history            | 场馆预定-在线场馆数量                  | 0        |
| 94   | hyd_result_stadium_map                | 体育基础设施-电子地图-查询表          | 0        |
| 95   | hyd_result_stadium_map_history        | 体育基础设施-电子地图-历史表          | 0        |
| 96   | hyd_result_stadium_map_point          | 体育基础设施-电子地图点位信息-查询表  | 0        |
| 97   | hyd_result_stadium_map_point_history  | 体育基础设施-电子地图点位信息-历史表  | 0        |
| 98   | hyd_result_stadium_sport_coupon       | 场馆预定-运动项目分布用券数占比        | 0        |
| 99   | hyd_result_stadium_sport_coupon_history | 场馆预定-运动项目分布用券数占比        | 0        |
| 100  | hyd_result_stock                      | 体育消费卷-消费券领卷用券-查询表      | 0        |
| 101  | hyd_result_stock_history              | 体育消费卷-消费券领卷用券-历史表      | 0        |
| 102  | hyd_result_user_age                   | 场馆预定-年龄占比                      | 0        |
| 103  | hyd_result_user_age_history           | 场馆预定-年龄占比                      | 0        |
| 104  | hyd_result_user_channel               | 场馆预定-用户来源渠道                  | 0        |
| 105  | hyd_result_user_channel_history       | 场馆预定-用户来源渠道                  | 0        |
| 106  | hyd_result_user_register              | 场馆预定-每月新增用户                  | 0        |
| 107  | hyd_result_user_register_history      | 场馆预定-每月新增用户                  | 0        |
| 108  | hyd_result_user_repurchase            | 场馆预定-复购率                        | 0        |
| 109  | hyd_result_user_repurchase_history    | 场馆预定-复购率                        | 0        |
| 110  | hyd_result_user_sex                   | 场馆预定-男女占比                      | 0        |
| 111  | hyd_result_user_sex_history           | 场馆预定-男女占比                      | 0        |
| 112  | hyd_sys_user                          | 系统用户                              | 0        |



24 张结果表

| 序号 | 表名                          | 表名中文注释                         | 新表名                        | 新表名中文注释                        |
| ---- |-----------------------------| ------------------------------------ |-----|---------|
| 1    | hyd_user_channel            | 驾驶舱-用户来源渠道                    | hyd_result_user_channel | 场馆预定-用户来源渠道        |
| 2    | hyd_user_register           | 驾驶舱-每月新增用户                    |  hyd_result_user_register   |  场馆预定-每月新增用户       |
| 3    | hyd_user_sex                | 驾驶舱-男女占比                       |  hyd_result_user_sex   |  场馆预定-男女占比       |
| 4    | hyd_user_age                | 驾驶舱-年龄占比                       |  hyd_result_user_age   |  场馆预定-年龄占比       |
| 5    | hyd_user_repurchase         | 驾驶舱-复购率                         |  hyd_result_user_repurchase   |  场馆预定-复购率       |
| 6    | hyd_order                   | 驾驶舱-订单数量                       |  hyd_result_order   | 体育消费卷-订单数量-查询表        |
| 7    | hyd_order_month             | 驾驶舱-订单趋势                       | hyd_result_order_month    | 体育消费卷-订单趋势-查询表        |
| 8    | hyd_order_sport             | 驾驶舱-项目消费券订单金额Top5          |  hyd_result_order_sport   | 体育消费卷-项目消费券订单金额Top5-查询表        |
| 9    | hyd_order_stadium           | 驾驶舱-场馆消费券订单金额Top5          |  hyd_result_order_stadium   | 体育消费卷-场馆消费券订单金额Top5-查询表        |
| 10   | hyd_stadium                 | 驾驶舱-在线场馆数量                    |  hyd_result_stadium   |  场馆预定-在线场馆数量       |
| 11   | hyd_stadium_district        | 驾驶舱-在线场馆各区情况                | hyd_result_stadium_district   |  场馆预定-在线场馆各区情况       |
| 12   | hyd_stadium_sport_coupon    | 驾驶舱-运动项目分布用券数占比          | hyd_result_stadium_sport_coupon |  场馆预定-运动项目分布用券数占比       |
| 13   | hyd_facility                | 驾驶舱-设施全貌                       | hyd_result_facility    |  体育基础设施-设施全貌-查询表       |
| 14   | hyd_facility_district       | 驾驶舱-设施各区分布                    | hyd_result_facility_district   |  体育基础设施-设施各区分布-查询表       |
| 15   | hyd_stadium_map             | 驾驶舱电子地图                        | hyd_result_stadium_map    |  体育基础设施-电子地图-查询表       |
| 16   | hyd_facility_year           | 驾驶舱-健身点位年数据                  | hyd_result_facility_year    |  体育基础设施-健身点位年数据-查询表       |
| 17   | hyd_facility_district_month | 驾驶舱-设施各区数据                    | hyd_result_facility_district_month | 体育基础设施-设施各区月数据-查询表        |
| 18   | hyd_facility_inspect        | 驾驶舱-巡维动态                       | hyd_result_facility_inspect   | 体育基础设施-巡检维修动态-查询表        |
| 19   | hyd_coupon_amount           | 驾驶舱-消费券总金额                    | hyd_result_coupon_amount    | 体育消费卷-消费券总金额-查询表        |
| 20   | hyd_stock                   | 驾驶舱-消费券领券用券                  | hyd_result_stock    | 体育消费卷-消费券领卷用券-查询表        |
| 21   | hyd_coupon_user             | 驾驶舱-券用户分析                      | hyd_result_coupon_user    |  体育消费卷-券用户分析       |
| 22   | hyd_coupon_user_age         | 驾驶舱-券用户年龄分布                  | hyd_result_coupon_user_age    | 体育消费卷-券用户年龄分布        |
| 23   | hyd_coupon_stadium_top      | 驾驶舱-消费券场馆预订top               | hyd_result_coupon_stadium_top  | 场馆预定-消费券场馆预订Top        |
| 24   | hyd_stadium_map_point       | 驾驶舱-电子地图点位信息                | hyd_result_stadium_map_point   |  体育基础设施-电子地图点位信息-查询表       |


10张原始表


| 序号 | 英文名称                          | 中文名称                   | 英文名称                           | 中文名称                  |
| ---- | --------------------------------- |------------------------|--------------------------------|------------------------- |
| 1    | tenant                            | 组织机构名                  | hyd_origin_tenant                         |组织机构名  |
| 2    | stadium                           | 培训场馆                    | hyd_origin_stadium                        |培训场馆 |
| 3    | stadium_item                      | 场馆培训项目                 | hyd_origin_stadium_item                   | 场馆培训项目 |
| 4    | training_activity                 | 培训活动                    | hyd_origin_training_activity              | 培训活动 |
| 5    | training_activity_item            | 培训活动支持的项目            | hyd_origin_training_activity_item         | 培训活动支持的项目 |
| 6    | training_activity_item_stadium    | 培训活动场馆支持的项目         | hyd_origin_training_activity_item_stadium | 培训活动场馆支持的项目 |
| 7    | training_course                   | 培训课程                     | hyd_origin_training_course                | 培训课程 |
| 8    | order                             | 订单                        | hyd_origin_order                          | 订单表 |
| 9    | la_stadium                        | 校外培训机构                 | hyd_origin_la_stadium                     | 校外培训机构表 |
| 10   | la_stadium_file                   | 校外培训机构附件              | hyd_origin_la_stadium_file                | 校外培训机构附件表 |





