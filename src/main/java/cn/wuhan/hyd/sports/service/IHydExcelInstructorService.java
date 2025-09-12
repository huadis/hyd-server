package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydExcelInstructorAgeGrowth;
import cn.wuhan.hyd.sports.domain.HydExcelInstructorAgeStats;
import cn.wuhan.hyd.sports.domain.HydExcelInstructorInfo;

import java.util.List;
import java.util.Map;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
public interface IHydExcelInstructorService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydExcelInstructorInfo> queryAllInstructorInfo(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydExcelInstructorInfo> queryAllInstructorInfo();


    /**
     * 新增数据
     *
     * @param instructorInfo 实体对象
     * @return 保存后的实体对象
     */
    HydExcelInstructorInfo save(HydExcelInstructorInfo instructorInfo);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteInstructorInfoById(Long id);

    /**
     * 更新数据
     *
     * @param instructorInfo 实体对象
     * @return 更新后的实体对象
     */
    HydExcelInstructorInfo updateInstructorInfo(HydExcelInstructorInfo instructorInfo);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydExcelInstructorInfo findInstructorInfoById(Long id);

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydExcelInstructorAgeStats> queryAllInstructorAgeStats(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydExcelInstructorAgeStats> queryAllInstructorAgeStats();

    /**
     * 新增数据
     *
     * @param instructorAgeStats 实体对象
     * @return 保存后的实体对象
     */
    HydExcelInstructorAgeStats save(HydExcelInstructorAgeStats instructorAgeStats);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteInstructorAgeStatsById(Long id);

    /**
     * 更新数据
     *
     * @param instructorAgeStats 实体对象
     * @return 更新后的实体对象
     */
    HydExcelInstructorAgeStats updateInstructorAgeStats(HydExcelInstructorAgeStats instructorAgeStats);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydExcelInstructorAgeStats findInstructorAgeStatsById(Long id);

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydExcelInstructorAgeGrowth> queryAllInstructorAgeGrowth(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydExcelInstructorAgeGrowth> queryAllInstructorAgeGrowth();

    /**
     * 新增数据
     *
     * @param instructorAgeGrowth 实体对象
     * @return 保存后的实体对象
     */
    HydExcelInstructorAgeGrowth save(HydExcelInstructorAgeGrowth instructorAgeGrowth);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteInstructorAgeGrowthById(Long id);

    /**
     * 更新数据
     *
     * @param instructorAgeGrowth 实体对象
     * @return 更新后的实体对象
     */
    HydExcelInstructorAgeGrowth updateInstructorAgeGrowth(HydExcelInstructorAgeGrowth instructorAgeGrowth);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydExcelInstructorAgeGrowth findInstructorAgeGrowthById(Long id);

    boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData);

    /**
     * 指导项目top15
     */
    List<Map<String, Object>> serviceProjectTop15();

    /**
     * 性别统计
     */
    List<Map<String, Object>> genderStat();

    /**
     * 级别统计
     */
    List<Map<String, Object>> levelStat();

    /**
     * 各区指导人员统计
     */
    List<Map<String, Object>> regionInstructorStat();

    /**
     * 年龄统计
     */
    List<Map<String, Object>> ageIntervalStat(String year);

    /**
     * 人数增长统计
     */
    List<Map<String, Object>> ageGrowthStat(String year);

    Map<String, Object> overview();

    List<Map<String, Object>> serviceProject();
}
