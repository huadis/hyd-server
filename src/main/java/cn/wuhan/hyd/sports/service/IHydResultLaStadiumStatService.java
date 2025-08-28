package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultLaStadiumDistrict;
import cn.wuhan.hyd.sports.domain.HydResultLaStadiumSportName;
import cn.wuhan.hyd.sports.domain.HydResultLaStadiumSportNameTop;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 校外培训机构-场馆统计统一服务（含各区数量、项目类型、项目类型占比TOP10） <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月27日 <br>
 */
public interface IHydResultLaStadiumStatService {

    void syncResultData();

    // ========================== 1. 各区场馆数量统计（HydResultLaStadiumDistrict） ==========================
    /**
     * 分页查询各区场馆数量统计
     * @param page 页数
     * @param size 每页条数
     * @return 分页结果
     */
    PageResult<HydResultLaStadiumDistrict> queryAllDistrictStat(int page, int size);

    /**
     * 查询所有各区场馆数量统计
     * @return 全量数据列表
     */
    List<HydResultLaStadiumDistrict> queryAllDistrictStat();

    /**
     * 新增各区场馆数量统计
     * @param districtStat 统计实体
     * @return 保存后的实体（含自动生成的ID、时间戳）
     */
    HydResultLaStadiumDistrict saveDistrictStat(HydResultLaStadiumDistrict districtStat);

    /**
     * 更新各区场馆数量统计
     * @param districtStat 统计实体（需含主键ID）
     * @return 更新后的实体
     */
    HydResultLaStadiumDistrict updateDistrictStat(HydResultLaStadiumDistrict districtStat);

    /**
     * 根据ID删除各区场馆数量统计
     * @param id 主键ID
     */
    void deleteDistrictStatById(Long id);

    /**
     * 根据ID查询各区场馆数量统计
     * @param id 主键ID
     * @return 对应统计实体
     */
    HydResultLaStadiumDistrict getDistrictStatById(Long id);


    // ========================== 2. 项目类型统计（HydResultLaStadiumSportName） ==========================
    /**
     * 分页查询项目类型统计
     * @param page 页数
     * @param size 每页条数
     * @return 分页结果
     */
    PageResult<HydResultLaStadiumSportName> queryAllSportNameStat(int page, int size);

    /**
     * 查询所有项目类型统计
     * @return 全量数据列表
     */
    List<HydResultLaStadiumSportName> queryAllSportNameStat();

    /**
     * 新增项目类型统计
     * @param sportNameStat 统计实体
     * @return 保存后的实体
     */
    HydResultLaStadiumSportName saveSportNameStat(HydResultLaStadiumSportName sportNameStat);

    /**
     * 更新项目类型统计
     * @param sportNameStat 统计实体（需含主键ID）
     * @return 更新后的实体
     */
    HydResultLaStadiumSportName updateSportNameStat(HydResultLaStadiumSportName sportNameStat);

    /**
     * 根据ID删除项目类型统计
     * @param id 主键ID
     */
    void deleteSportNameStatById(Long id);

    /**
     * 根据ID查询项目类型统计
     * @param id 主键ID
     * @return 对应统计实体
     */
    HydResultLaStadiumSportName getSportNameStatById(Long id);


    // ========================== 3. 项目类型占比TOP10统计（HydResultLaStadiumSportNameTop） ==========================
    /**
     * 分页查询项目类型占比TOP10统计
     * @param page 页数
     * @param size 每页条数
     * @return 分页结果
     */
    PageResult<HydResultLaStadiumSportNameTop> queryAllSportNameTopStat(int page, int size);

    /**
     * 查询所有项目类型占比TOP10统计
     * @return 全量数据列表
     */
    List<HydResultLaStadiumSportNameTop> queryAllSportNameTopStat();

    /**
     * 新增项目类型占比TOP10统计
     * @param sportNameTopStat 统计实体
     * @return 保存后的实体
     */
    HydResultLaStadiumSportNameTop saveSportNameTopStat(HydResultLaStadiumSportNameTop sportNameTopStat);

    /**
     * 更新项目类型占比TOP10统计
     * @param sportNameTopStat 统计实体（需含主键ID）
     * @return 更新后的实体
     */
    HydResultLaStadiumSportNameTop updateSportNameTopStat(HydResultLaStadiumSportNameTop sportNameTopStat);

    /**
     * 根据ID删除项目类型占比TOP10统计
     * @param id 主键ID
     */
    void deleteSportNameTopStatById(Long id);

    /**
     * 根据ID查询项目类型占比TOP10统计
     * @param id 主键ID
     * @return 对应统计实体
     */
    HydResultLaStadiumSportNameTop getSportNameTopStatById(Long id);

    List<Map<String, Object>> stadiumCountByDistrict(String year);

    List<Map<String, Object>> itemCountTop10BySportName(String year);

    List<Map<String, Object>> itemCountBySportName(String year);
}
