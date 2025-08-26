package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.*;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 大众赛事-体育赛事信息表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
public interface IHydExcelPublicEventsService {

    void syncResultData();

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydExcelPublicEventsHistory> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydExcelPublicEventsHistory> queryAll();


    /**
     * 新增数据
     *
     * @param publicEvents 实体对象
     * @return 保存后的实体对象
     */
    HydExcelPublicEventsHistory save(HydExcelPublicEventsHistory publicEvents);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param publicEvents 实体对象
     * @return 更新后的实体对象
     */
    HydExcelPublicEventsHistory update(HydExcelPublicEventsHistory publicEvents);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydExcelPublicEventsHistory findById(Long id);


    // ------------------------- 大众赛事-总览信息表操作 -------------------------
    PageResult<HydResultEventsOverviewStat> queryAllOverview(int page, int size);

    List<HydResultEventsOverviewStat> queryAllOverview();

    HydResultEventsOverviewStat saveOverview(HydResultEventsOverviewStat overviewStat);

    void deleteOverviewById(Long id);

    HydResultEventsOverviewStat updateOverview(HydResultEventsOverviewStat overviewStat);

    HydResultEventsOverviewStat findOverviewById(Long id);


    // ------------------------- 大众赛事-各月办赛数据表操作 -------------------------
    PageResult<HydResultEventsMonthCountStat> queryAllMonthCount(int page, int size);

    List<HydResultEventsMonthCountStat> queryAllMonthCount();

    HydResultEventsMonthCountStat saveMonthCount(HydResultEventsMonthCountStat monthCountStat);

    void deleteMonthCountById(Long id);

    HydResultEventsMonthCountStat updateMonthCount(HydResultEventsMonthCountStat monthCountStat);

    HydResultEventsMonthCountStat findMonthCountById(Long id);


    // ------------------------- 大众赛事-赛事数量TOP5项目表操作 -------------------------
    PageResult<HydResultEventsSportItemTop> queryAllSportItemTop(int page, int size);

    List<HydResultEventsSportItemTop> queryAllSportItemTop();

    HydResultEventsSportItemTop saveSportItemTop(HydResultEventsSportItemTop sportItemTop);

    void deleteSportItemTopById(Long id);

    HydResultEventsSportItemTop updateSportItemTop(HydResultEventsSportItemTop sportItemTop);

    HydResultEventsSportItemTop findSportItemTopById(Long id);


    // ------------------------- 大众赛事-参赛人数人档表操作 -------------------------
    PageResult<HydResultEventsParticipantLevel> queryAllParticipantLevel(int page, int size);

    List<HydResultEventsParticipantLevel> queryAllParticipantLevel();

    HydResultEventsParticipantLevel saveParticipantLevel(HydResultEventsParticipantLevel participantLevel);

    void deleteParticipantLevelById(Long id);

    HydResultEventsParticipantLevel updateParticipantLevel(HydResultEventsParticipantLevel participantLevel);

    HydResultEventsParticipantLevel findParticipantLevelById(Long id);

    boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData);

    Map<String, Object> overview(String year);

    /**
     * 各月办赛数据
     */
    List<Map<String, Object>> monthStat(String year);

    List<Map<String, Object>> sportItemTop5(String year);

    List<Map<String, Object>> participantCountStat(String year);

    List<Map<String, Object>> currentMouthEvents(String year);

    List<Map<String, Object>> districtCountByYear(String year);

    int batchInsertHistoryWithJdbc(List<HydExcelPublicEventsHistory> eventsList, int batchSize);
}
