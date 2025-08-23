package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultFacility;
import cn.wuhan.hyd.sports.req.HydResultFacilityReq;

import java.util.List;

/**
 * 功能说明： 驾驶舱-设施全貌 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydResultFacilityService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultFacility> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultFacility> queryAll();


    /**
     * 新增数据
     *
     * @param hydResultFacility 实体对象
     * @return 保存后的实体对象
     */
    HydResultFacility save(HydResultFacility hydResultFacility);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydResultFacility 实体对象
     * @return 更新后的实体对象
     */
    HydResultFacility update(HydResultFacility hydResultFacility);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultFacility findById(Long id);

    int batchSave(List<HydResultFacilityReq> facilities);

    List<HydResultFacility> facility();
}
