package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.DateUtil;
import cn.wuhan.hyd.framework.utils.MapUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.repository.*;
import cn.wuhan.hyd.sports.service.IHydExcelPublicEventsService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Service
public class HydExcelPublicEventsServiceImpl implements IHydExcelPublicEventsService {
    @Resource
    private HydExcelPublicEventsHistoryRepo publicEventsHistoryRepo;
    @Resource
    private HydExcelPublicEventsRepo publicEventsRepo;
    @Resource
    private HydResultEventsOverviewStatRepo overviewStatRepo;
    @Resource
    private HydResultEventsMonthCountStatRepo monthCountStatRepo;
    @Resource
    private HydResultEventsSportItemTopRepo sportItemTopRepo;
    @Resource
    private HydResultEventsParticipantLevelRepo participantLevelRepo;
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private Executor executor;

    public void syncResultData() {
        syncOverviewStatData();
        syncMonthCountStatData();
        syncSportItemTopData();
        syncParticipantLevelData();
    }

    public void syncOverviewStatData() {
        List<Map<String, Object>> totalList = publicEventsRepo.countAll();
        List<Map<String, Object>> participantCountList = publicEventsRepo.totalParticipantCount();
        List<Map<String, Object>> internationalList = publicEventsRepo.internationalEventCount();
        List<Map<String, Object>> nationalList = publicEventsRepo.nationalEventCount();
        List<Map<String, Object>> provinceList = publicEventsRepo.provinceCount();
        List<Map<String, Object>> cityList = publicEventsRepo.cityCount();

        // 2. 2. 预处理：将List<Map>转为“年份→统计值”的Map（Key：年份字符串，Value：统计值）
        Map<String, Object> yearToTotal = convertListToYearMap(totalList, "total"); // 年份→总赛事场次
        Map<String, Object> yearToParticipant = convertListToYearMap(participantCountList, "participantCount"); // 年份→总参与人数（注意：需替换为实际统计字段名）
        Map<String, Object> yearToInternational = convertListToYearMap(internationalList, "internationalCount"); // 年份→国际赛事数
        Map<String, Object> yearToNational = convertListToYearMap(nationalList, "nationalCount"); // 年份→国家级赛事数
        Map<String, Object> yearToProvince = convertListToYearMap(provinceList, "provinceCount"); // 年份→省级赛事数
        Map<String, Object> yearToCity = convertListToYearMap(cityList, "cityCount"); // 年份→市级赛事数

        // 3. 3. 提取所有年份（确保覆盖所有维度，避免遗漏某年份）
        Set<String> allYears = new HashSet<>();
        allYears.addAll(yearToTotal.keySet());
        allYears.addAll(yearToParticipant.keySet());
        allYears.addAll(yearToInternational.keySet());
        allYears.addAll(yearToNational.keySet());
        allYears.addAll(yearToProvince.keySet());
        allYears.addAll(yearToCity.keySet());

        if (CollectionUtils.isEmpty(allYears)) {
            throw new RuntimeException("无统计数据，无法聚合");
        }
        // 按年份聚合所有维度的统计值，转换为实体列表
        List<HydResultEventsOverviewStat> overviewStatList = allYears.stream()
                .map(year -> {
                    String yearInt = year.replaceAll("年", "");
                    HydResultEventsOverviewStat stat = new HydResultEventsOverviewStat();
                    stat.setStatisticalYear(Integer.parseInt(yearInt));
                    // 3.1 年份对应的各维度统计值（注意：从Map取数时需转为Long，避免类型转换错误）
                    stat.setTotal(MapUtils.getLong(yearToTotal, year)); // 总赛事场次
                    stat.setParticipantCount(MapUtils.getLong(yearToParticipant, year)); // 总参与人数
                    stat.setInternationalCount(MapUtils.getLong(yearToInternational, year)); // 国际赛事
                    stat.setNationalCount(MapUtils.getLong(yearToNational, year)); // 国家级赛事
                    stat.setProvinceCount(MapUtils.getLong(yearToProvince, year)); // 省级赛事
                    stat.setCityCount(MapUtils.getLong(yearToCity, year)); // 市级赛事
                    return stat;
                })
                .collect(Collectors.toList());
        overviewStatRepo.deleteAll();
        overviewStatRepo.saveAll(overviewStatList);
    }

    /**
     * 工具方法1：将List<Map>转为“年份→统计值”的Map
     *
     * @param list     原始统计列表（每个Map含"statisticalYear"和统计字段）
     * @param countKey 统计字段的Key（如"total"、"participantCount"）
     * @return 年份映射Map（Key：statisticalYear的值，Value：对应统计值）
     */
    private Map<String, Object> convertListToYearMap(List<Map<String, Object>> list, String countKey) {
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }
        Map<String, Object> yearMap = new HashMap<>();
        for (Map<String, Object> map : list) {
            if (map.containsKey("statisticalYear") && map.containsKey(countKey)) {
                String statisticalYear = MapUtils.getString(map, "statisticalYear");
                int count = MapUtils.getInteger(map, countKey, 0);
                yearMap.put(statisticalYear, count);
            }
        }
        return yearMap;
    }

    public void syncMonthCountStatData() {
        List<Map<String, Object>> list = publicEventsRepo.monthStat();
        List<HydResultEventsMonthCountStat> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            String statisticalYear = MapUtils.getString(map, "statisticalYear");
            String yearInt = statisticalYear.replaceAll("年", "");
            HydResultEventsMonthCountStat stat = new HydResultEventsMonthCountStat();
            stat.setStatisticalYear(Integer.parseInt(yearInt));
            stat.setEventCount(MapUtils.getLong(map, "eventCount"));
            stat.setEventMonth(MapUtils.getString(map, "eventMonth"));
            result.add(stat);
        }
        monthCountStatRepo.deleteAll();
        monthCountStatRepo.saveAll(result);
    }

    public void syncSportItemTopData() {
        List<Map<String, Object>> list = publicEventsRepo.sportItemTop5();
        List<HydResultEventsSportItemTop> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            String statisticalYear = MapUtils.getString(map, "statisticalYear");
            HydResultEventsSportItemTop stat = new HydResultEventsSportItemTop();
            String yearInt = statisticalYear.replaceAll("年", "");
            stat.setStatisticalYear(Integer.parseInt(yearInt));
            stat.setSportItem(MapUtils.getString(map, "sportItem"));
            stat.setCount(MapUtils.getLong(map, "count"));
            result.add(stat);
        }
        sportItemTopRepo.deleteAll();
        sportItemTopRepo.saveAll(result);
    }

    public void syncParticipantLevelData() {
        List<Map<String, Object>> list = publicEventsRepo.participantCountStat();
        List<HydResultEventsParticipantLevel> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            String statisticalYear = MapUtils.getString(map, "statisticalYear");
            String yearInt = statisticalYear.replaceAll("年", "");
            HydResultEventsParticipantLevel stat = new HydResultEventsParticipantLevel();
            stat.setStatisticalYear(Integer.parseInt(yearInt));
            stat.setParticipantLevel(MapUtils.getString(map, "participantLevel"));
            stat.setCount(MapUtils.getLong(map, "count"));
            result.add(stat);
        }
        participantLevelRepo.deleteAll();
        participantLevelRepo.saveAll(result);
    }


    @Override
    public PageResult<HydExcelPublicEvents> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelPublicEvents> pageResult = publicEventsRepo.findAll(pageable);
        PageResult<HydExcelPublicEvents> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydExcelPublicEvents> queryAll() {
        return publicEventsRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelPublicEvents save(HydExcelPublicEvents publicEvents) {
        return publicEventsRepo.save(publicEvents);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        publicEventsRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelPublicEvents update(HydExcelPublicEvents publicEvents) {
        if (publicEvents.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findById(publicEvents.getId());
        return publicEventsRepo.save(publicEvents);
    }

    @Override
    public HydExcelPublicEvents findById(Long id) {
        return publicEventsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("大众赛事-体育赛事信息表，ID：" + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData) {
        List<HydExcelPublicEvents> publicEvents = new ArrayList<>();
        List<HydExcelPublicEventsHistory> publicEventsHistories = new ArrayList<>();
        try {
            publicEventsRepo.deleteAll();
            CompletableFuture<Void> mainFuture = CompletableFuture.runAsync(new Runnable() {
                @Override
                public void run() {
                    sheetMapData.forEach((name, list) -> {
                        if (CollectionUtils.isNotEmpty(list)) {
                            // 批量保存
                            list.forEach(m -> {
                                publicEvents.add(MapUtil.map2Object(HydExcelPublicEvents.class, m));
                            });
                            publicEventsRepo.saveAll(publicEvents);
                        }
                    });
                }
            }, executor);
            CompletableFuture<Void> historyFuture = CompletableFuture.runAsync(new Runnable() {
                @Override
                public void run() {
                    sheetMapData.forEach((name, list) -> {
                        if (CollectionUtils.isNotEmpty(list)) {
                            // 批量保存
                            list.forEach(m -> {
                                publicEventsHistories.add(MapUtil.map2Object(HydExcelPublicEventsHistory.class, m));
                            });
                            batchInsertHistoryWithJdbc(publicEventsHistories);
                        }
                    });
                }
            }, executor);
            // 3. 等待所有线程完成
            CompletableFuture.allOf(mainFuture, historyFuture).get();
            syncResultData();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            // 异常时事务回滚
            Thread.currentThread().interrupt();
            throw new RuntimeException("Excel导入失败", e);
        }
    }

    // ------------------------- 大众赛事-总览信息表操作 -------------------------
    @Override
    public PageResult<HydResultEventsOverviewStat> queryAllOverview(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultEventsOverviewStat> pageResult = overviewStatRepo.findAll(pageable);
        PageResult<HydResultEventsOverviewStat> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultEventsOverviewStat> queryAllOverview() {
        return overviewStatRepo.findAll();
    }

    @Override
    public HydResultEventsOverviewStat saveOverview(HydResultEventsOverviewStat overviewStat) {
        return overviewStatRepo.save(overviewStat);
    }

    @Override
    public void deleteOverviewById(Long id) {
        overviewStatRepo.deleteById(id);
    }

    @Override
    public HydResultEventsOverviewStat updateOverview(HydResultEventsOverviewStat overviewStat) {
        return overviewStatRepo.save(overviewStat);
    }

    @Override
    public HydResultEventsOverviewStat findOverviewById(Long id) {
        return overviewStatRepo.findById(id).orElse(null);
    }


    // ------------------------- 大众赛事-各月办赛数据表操作 -------------------------
    @Override
    public PageResult<HydResultEventsMonthCountStat> queryAllMonthCount(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultEventsMonthCountStat> pageResult = monthCountStatRepo.findAll(pageable);
        PageResult<HydResultEventsMonthCountStat> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultEventsMonthCountStat> queryAllMonthCount() {
        return monthCountStatRepo.findAll();
    }

    @Override
    public HydResultEventsMonthCountStat saveMonthCount(HydResultEventsMonthCountStat monthCountStat) {
        return monthCountStatRepo.save(monthCountStat);
    }

    @Override
    public void deleteMonthCountById(Long id) {
        monthCountStatRepo.deleteById(id);
    }

    @Override
    public HydResultEventsMonthCountStat updateMonthCount(HydResultEventsMonthCountStat monthCountStat) {
        return monthCountStatRepo.save(monthCountStat);
    }

    @Override
    public HydResultEventsMonthCountStat findMonthCountById(Long id) {
        return monthCountStatRepo.findById(id).orElse(null);
    }


    // ------------------------- 大众赛事-赛事数量TOP5项目表操作 -------------------------
    @Override
    public PageResult<HydResultEventsSportItemTop> queryAllSportItemTop(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultEventsSportItemTop> pageResult = sportItemTopRepo.findAll(pageable);
        PageResult<HydResultEventsSportItemTop> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultEventsSportItemTop> queryAllSportItemTop() {
        return sportItemTopRepo.findAll();
    }

    @Override
    public HydResultEventsSportItemTop saveSportItemTop(HydResultEventsSportItemTop sportItemTop) {
        return sportItemTopRepo.save(sportItemTop);
    }

    @Override
    public void deleteSportItemTopById(Long id) {
        sportItemTopRepo.deleteById(id);
    }

    @Override
    public HydResultEventsSportItemTop updateSportItemTop(HydResultEventsSportItemTop sportItemTop) {
        return sportItemTopRepo.save(sportItemTop);
    }

    @Override
    public HydResultEventsSportItemTop findSportItemTopById(Long id) {
        return sportItemTopRepo.findById(id).orElse(null);
    }


    // ------------------------- 大众赛事-参赛人数人档表操作 -------------------------
    @Override
    public PageResult<HydResultEventsParticipantLevel> queryAllParticipantLevel(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultEventsParticipantLevel> pageResult = participantLevelRepo.findAll(pageable);
        PageResult<HydResultEventsParticipantLevel> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultEventsParticipantLevel> queryAllParticipantLevel() {
        return participantLevelRepo.findAll();
    }

    @Override
    public HydResultEventsParticipantLevel saveParticipantLevel(HydResultEventsParticipantLevel participantLevel) {
        return participantLevelRepo.save(participantLevel);
    }

    @Override
    public void deleteParticipantLevelById(Long id) {
        participantLevelRepo.deleteById(id);
    }

    @Override
    public HydResultEventsParticipantLevel updateParticipantLevel(HydResultEventsParticipantLevel participantLevel) {
        return participantLevelRepo.save(participantLevel);
    }

    @Override
    public HydResultEventsParticipantLevel findParticipantLevelById(Long id) {
        return participantLevelRepo.findById(id).orElse(null);
    }

    @Override
    public Map<String, Object> overview(String year) {
        return overviewStatRepo.overview(year);
    }

    /**
     * 各月办赛数据
     */
    @Override
    public List<Map<String, Object>> monthStat(String year) {
        return monthCountStatRepo.monthStat(year);
    }

    @Override
    public List<Map<String, Object>> sportItemTop5(String year) {
        return sportItemTopRepo.sportItemTop5(year);
    }

    @Override
    public List<Map<String, Object>> participantCountStat(String year) {
        return participantLevelRepo.participantCountStat(year);
    }

    @Override
    public List<Map<String, Object>> currentMouthEvents(String year) {
        return publicEventsHistoryRepo.currentMouthEvents(year + "年", DateUtil.getCurrentMonth() + "月");
    }

    @Override
    public List<Map<String, Object>> districtCountByYear(String year) {
        return publicEventsHistoryRepo.districtCountByYear(year + "年");
    }

    /**
     * 原生 SQL 批量插入（超大数据量优化）
     *
     * @param eventsList 待保存的实体列表
     * @param batchSize  单次批量保存的条数（建议1000-5000）
     * @return 保存成功的总条数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsertHistoryWithJdbc(List<HydExcelPublicEventsHistory> eventsList, int batchSize) {
        // 1. 校验入参
        if (CollectionUtils.isEmpty(eventsList)) {
            return 0;
        }
        batchSize = Math.max(batchSize, 1000);
        batchSize = Math.min(batchSize, 5000);

        // 2. 定义原生
        String sql = "INSERT INTO hyd_excel_public_events_history (" +
                "sequence, district, eventName, sportItem, hostUnit, organizerUnit, " +
                "eventYear, eventMonth, eventDate, eventLocation, participantCount, eventLevel, " +
                "createdTime, updateTime, importTime" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // 3. 分批次执行原生批量 SQL
        int totalCount = eventsList.size();
        int batchNum = (totalCount + batchSize - 1) / batchSize; // 总批次数

        for (int i = 0; i < batchNum; i++) {
            // 计算当前批次的起始和结束索引
            int startIdx = i * batchSize;
            int endIdx = Math.min((i + 1) * batchSize, totalCount);
            List<HydExcelPublicEventsHistory> subList = eventsList.subList(startIdx, endIdx);

            // 执行批量插入
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int index) throws SQLException {
                    // 为当前批次的第 index 条数据赋值（与 SQL 字段顺序对应）
                    HydExcelPublicEventsHistory event = subList.get(index);
                    Timestamp now = new Timestamp(System.currentTimeMillis());

                    // 序号（允许为null，数据库若有默认值可传null）
                    ps.setObject(1, event.getSequence());
                    // 区属
                    ps.setString(2, event.getDistrict());
                    // 赛事名称
                    ps.setString(3, event.getEventName());
                    // 运动项目
                    ps.setString(4, event.getSportItem());
                    // 主办单位
                    ps.setString(5, event.getHostUnit());
                    // 承办单位
                    ps.setString(6, event.getOrganizerUnit());
                    // 赛事年份
                    ps.setString(7, event.getEventYear());
                    // 赛事月份
                    ps.setString(8, event.getEventMonth());
                    // 赛事日期
                    ps.setString(9, event.getEventDate());
                    // 赛事地点
                    ps.setString(10, event.getEventLocation());
                    // 参与人数（允许为null）
                    ps.setObject(11, event.getParticipantCount());
                    // 赛事级别
                    ps.setString(12, event.getEventLevel());
                    // 创建时间（与 @PrePersist 逻辑一致）
                    ps.setTimestamp(13, event.getCreatedTime() == null ? now : event.getCreatedTime());
                    // 更新时间
                    ps.setTimestamp(14, event.getUpdateTime() == null ? now : event.getUpdateTime());
                    // 导入时间
                    ps.setTimestamp(15, event.getImportTime() == null ? now : event.getImportTime());
                }

                // 当前批次的总条数
                @Override
                public int getBatchSize() {
                    return subList.size();
                }
            });
        }

        return totalCount;
    }

    /**
     * 重载方法：默认批次大小（1000条/批）
     */
    public int batchInsertHistoryWithJdbc(List<HydExcelPublicEventsHistory> eventsList) {
        return batchInsertHistoryWithJdbc(eventsList, 1000);
    }
}
