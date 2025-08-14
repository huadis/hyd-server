package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultFacilityDistrictMonth;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 功能说明： 驾驶舱-设施各区月数据 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydResultFacilityDistrictMonthService {

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 实体对象列表
     */
    PageResult<HydResultFacilityDistrictMonth> queryAll(Pageable pageable);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultFacilityDistrictMonth> queryAll();


    /**
     * 新增数据
     *
     * @param hydResultFacilityDistrictMonth 实体对象
     * @return 保存后的实体对象
     */
    HydResultFacilityDistrictMonth save(HydResultFacilityDistrictMonth hydResultFacilityDistrictMonth);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydResultFacilityDistrictMonth 实体对象
     * @return 更新后的实体对象
     */
    HydResultFacilityDistrictMonth update(HydResultFacilityDistrictMonth hydResultFacilityDistrictMonth);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultFacilityDistrictMonth findById(Long id);

    int batchSave(List<HydResultFacilityDistrictMonth> facilityDistrictMonths);
}
