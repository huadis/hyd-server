package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydStadiumDistrict;
import cn.wuhan.hyd.sports.resp.StadiumDistrictCountResp;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 驾驶舱-在线场馆各区情况 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydStadiumDistrictService {

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 实体对象列表
     */
    PageResult<HydStadiumDistrict> queryAll(Pageable pageable);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydStadiumDistrict> queryAll();


    /**
     * 新增数据
     *
     * @param hydStadiumDistrict 实体对象
     * @return 保存后的实体对象
     */
    HydStadiumDistrict save(HydStadiumDistrict hydStadiumDistrict);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydStadiumDistrict 实体对象
     * @return 更新后的实体对象
     */
    HydStadiumDistrict update(HydStadiumDistrict hydStadiumDistrict);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydStadiumDistrict findById(Long id);

    /**
     * 统计各区场馆数量
     *
     * @return 包含区场馆统计数据列表，包含区名称及场馆数量
     */
    List<Map<String, Object>> countStadiumDistrict();

    int batchSave(List<HydStadiumDistrict> stadiumDistricts);
}
