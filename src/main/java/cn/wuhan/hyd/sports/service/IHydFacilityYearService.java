package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydFacilityYear;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 功能说明： 驾驶舱-健身点位年数据 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydFacilityYearService {

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 实体对象列表
     */
    PageResult<HydFacilityYear> queryAll(Pageable pageable);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydFacilityYear> queryAll();


    /**
     * 新增数据
     *
     * @param hydFacilityYear 实体对象
     * @return 保存后的实体对象
     */
    HydFacilityYear save(HydFacilityYear hydFacilityYear);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydFacilityYear 实体对象
     * @return 更新后的实体对象
     */
    HydFacilityYear update(HydFacilityYear hydFacilityYear);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydFacilityYear findById(Long id);

    int batchSave(List<HydFacilityYear> facilityYears);

    HydFacilityYear findLatestFacilityYear();
}
