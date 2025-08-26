package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.DateUtil;
import cn.wuhan.hyd.framework.utils.MapUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.repository.*;
import cn.wuhan.hyd.sports.service.IHydExcelPublicEventsService;
import org.apache.commons.collections.CollectionUtils;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Service
public class HydExcelPublicEventsServiceImpl implements IHydExcelPublicEventsService {
    @Resource
    private HydExcelPublicEventsRepo publicEventsRepo;
    @Resource
    private HydExcelPublicEventsHistoryRepo publicEventsHistoryRepo;
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
        sheetMapData.forEach((name, list) -> {
            if (CollectionUtils.isNotEmpty(list)) {
                // 批量保存
                list.forEach(m -> {
                    publicEvents.add(MapUtil.map2Object(HydExcelPublicEvents.class, m));
                    publicEventsHistories.add(MapUtil.map2Object(HydExcelPublicEventsHistory.class, m));
                });
                batchInsertWithJdbc(publicEvents);
                batchInsertHistoryWithJdbc(publicEventsHistories);
            }
        });
        return true;
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
        Map<String, Object> result = new HashMap<>();
        result.put("total", publicEventsRepo.countAll(year + "年"));
        result.put("participantCount", publicEventsRepo.totalParticipantCount(year + "年"));
        result.put("internationalCount", publicEventsRepo.internationalEventCount(year + "年"));
        result.put("nationalCount", publicEventsRepo.nationalEventCount(year + "年"));
        result.put("provinceCount", publicEventsRepo.provinceCount(year + "年"));
        result.put("cityCount", publicEventsRepo.cityCount(year + "年"));
        return result;
    }

    /**
     * 各月办赛数据
     */
    @Override
    public List<Map<String, Object>> monthStat(String year) {
        return publicEventsRepo.monthStat(year + "年");
    }

    @Override
    public List<Map<String, Object>> sportItemTop5(String year) {
        return publicEventsRepo.sportItemTop5(year + "年");
    }

    @Override
    public List<Map<String, Object>> participantCountStat(String year) {
        return publicEventsRepo.participantCountStat(year + "年");
    }

    @Override
    public List<Map<String, Object>> currentMouthEvents(String year) {
        return publicEventsRepo.currentMouthEvents(year + "年", DateUtil.getCurrentMonth() + "月");
    }

    @Override
    public List<Map<String, Object>> districtCountByYear(String year) {
        return publicEventsRepo.districtCountByYear(year + "年");
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
    public int batchInsertWithJdbc(List<HydExcelPublicEvents> eventsList, int batchSize) {
        // 1. 校验入参
        if (CollectionUtils.isEmpty(eventsList)) {
            return 0;
        }
        batchSize = Math.max(batchSize, 1000);
        batchSize = Math.min(batchSize, 5000);

        // 2. 定义原生
        String sql = "INSERT INTO hyd_excel_public_events (" +
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
            List<HydExcelPublicEvents> subList = eventsList.subList(startIdx, endIdx);

            // 执行批量插入
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int index) throws SQLException {
                    // 为当前批次的第 index 条数据赋值（与 SQL 字段顺序对应）
                    HydExcelPublicEvents event = subList.get(index);
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
    public int batchInsertWithJdbc(List<HydExcelPublicEvents> eventsList) {
        return batchInsertWithJdbc(eventsList, 1000);
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
