package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.*;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 社会体育指导员统计统一服务（含级别、概览、各区、项目、项目TOP15、性别统计） <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月27日 <br>
 */
public interface IHydResultInstructorService {

    void syncResultData();

    // ========================== 1. 社会体育指导员-级别统计（HydResultInstructorLevel） ==========================

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultInstructorLevel> queryAllLevel(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultInstructorLevel> queryAllLevel();

    /**
     * 新增数据
     *
     * @param levelStat 实体对象
     * @return 保存后的实体对象
     */
    HydResultInstructorLevel save(HydResultInstructorLevel levelStat);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteLevelById(Long id);

    /**
     * 更新数据
     *
     * @param levelStat 实体对象
     * @return 更新后的实体对象
     */
    HydResultInstructorLevel update(HydResultInstructorLevel levelStat);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultInstructorLevel findLevelById(Long id);


    // ========================== 2. 社会体育指导员-概览（HydResultInstructorOverview） ==========================

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultInstructorOverview> queryAllOverview(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultInstructorOverview> queryAllOverview();

    /**
     * 新增数据
     *
     * @param overviewStat 实体对象
     * @return 保存后的实体对象
     */
    HydResultInstructorOverview save(HydResultInstructorOverview overviewStat);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteOverviewById(Long id);

    /**
     * 更新数据
     *
     * @param overviewStat 实体对象
     * @return 更新后的实体对象
     */
    HydResultInstructorOverview update(HydResultInstructorOverview overviewStat);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultInstructorOverview findOverviewById(Long id);


    // ========================== 3. 社会体育指导员-各区指导人员统计（HydResultInstructorRegion） ==========================

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultInstructorRegion> queryAllRegion(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultInstructorRegion> queryAllRegion();

    /**
     * 新增数据
     *
     * @param regionStat 实体对象
     * @return 保存后的实体对象
     */
    HydResultInstructorRegion save(HydResultInstructorRegion regionStat);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteRegionById(Long id);

    /**
     * 更新数据
     *
     * @param regionStat 实体对象
     * @return 更新后的实体对象
     */
    HydResultInstructorRegion update(HydResultInstructorRegion regionStat);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultInstructorRegion findRegionById(Long id);


    // ========================== 4. 社会体育指导员-项目统计（HydResultInstructorServiceProject） ==========================

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultInstructorServiceProject> queryAllServiceProject(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultInstructorServiceProject> queryAllServiceProject();

    /**
     * 新增数据
     *
     * @param serviceProjectStat 实体对象
     * @return 保存后的实体对象
     */
    HydResultInstructorServiceProject save(HydResultInstructorServiceProject serviceProjectStat);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteServiceProjectById(Long id);

    /**
     * 更新数据
     *
     * @param serviceProjectStat 实体对象
     * @return 更新后的实体对象
     */
    HydResultInstructorServiceProject update(HydResultInstructorServiceProject serviceProjectStat);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultInstructorServiceProject findServiceProjectById(Long id);


    // ========================== 5. 社会体育指导员-指导项目统计TOP15（HydResultInstructorServiceProjectTop） ==========================

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultInstructorServiceProjectTop> queryAllServiceProjectTop(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultInstructorServiceProjectTop> queryAllServiceProjectTop();

    /**
     * 新增数据
     *
     * @param serviceProjectTopStat 实体对象
     * @return 保存后的实体对象
     */
    HydResultInstructorServiceProjectTop save(HydResultInstructorServiceProjectTop serviceProjectTopStat);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteServiceProjectTopById(Long id);

    /**
     * 更新数据
     *
     * @param serviceProjectTopStat 实体对象
     * @return 更新后的实体对象
     */
    HydResultInstructorServiceProjectTop update(HydResultInstructorServiceProjectTop serviceProjectTopStat);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultInstructorServiceProjectTop findServiceProjectTopById(Long id);


    // ========================== 6. 社会体育指导员-性别统计（HydResultInstructorUserSex） ==========================

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultInstructorUserSex> queryAllUserSex(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultInstructorUserSex> queryAllUserSex();

    /**
     * 新增数据
     *
     * @param userSexStat 实体对象
     * @return 保存后的实体对象
     */
    HydResultInstructorUserSex save(HydResultInstructorUserSex userSexStat);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteUserSexById(Long id);

    /**
     * 更新数据
     *
     * @param userSexStat 实体对象
     * @return 更新后的实体对象
     */
    HydResultInstructorUserSex update(HydResultInstructorUserSex userSexStat);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultInstructorUserSex findUserSexById(Long id);

    /**
     * 指导项目top15
     */
    List<Map<String, Object>> serviceProjectTop15(String year);

    /**
     * 性别统计
     */
    List<Map<String, Object>> genderStat(String year);

    /**
     * 级别统计
     */
    List<Map<String, Object>> levelStat(String year);

    /**
     * 各区指导人员统计
     */
    List<Map<String, Object>> regionInstructorStat(String year);

    Map<String, Object> overview(String year);

    List<Map<String, Object>> serviceProject(String year);
}
