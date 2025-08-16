package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultStadiumDistrict;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 驾驶舱-在线场馆各区情况 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydResultStadiumDistrictService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultStadiumDistrict> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultStadiumDistrict> queryAll();


    /**
     * 新增数据
     *
     * @param hydResultStadiumDistrict 实体对象
     * @return 保存后的实体对象
     */
    HydResultStadiumDistrict save(HydResultStadiumDistrict hydResultStadiumDistrict);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydResultStadiumDistrict 实体对象
     * @return 更新后的实体对象
     */
    HydResultStadiumDistrict update(HydResultStadiumDistrict hydResultStadiumDistrict);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultStadiumDistrict findById(Long id);

    /**
     * 统计各区场馆数量
     *
     * @return 包含区场馆统计数据列表，包含区名称及场馆数量
     */
    List<Map<String, Object>> countStadiumDistrict();

    int batchSave(List<HydResultStadiumDistrict> stadiumDistricts);
}
