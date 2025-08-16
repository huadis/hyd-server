package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydExcelPublicEvents;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 大众赛事-体育赛事信息表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
public interface IHydExcelPublicEventsService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydExcelPublicEvents> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydExcelPublicEvents> queryAll();


    /**
     * 新增数据
     *
     * @param publicEvents 实体对象
     * @return 保存后的实体对象
     */
    HydExcelPublicEvents save(HydExcelPublicEvents publicEvents);

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
    HydExcelPublicEvents update(HydExcelPublicEvents publicEvents);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydExcelPublicEvents findById(Long id);

    boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData);

    Map<String, Object> overview();

    /**
     * 各月办赛数据
     */
    List<Map<String, Object>> latestMonthStat();

    List<Map<String, Object>> sportItemTop5();

}
