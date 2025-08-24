package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultStock;
import cn.wuhan.hyd.sports.req.HydResultStockReq;

import java.util.List;

/**
 * 功能说明： 驾驶舱-消费券领用券 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydResultStockService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultStock> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultStock> queryAll();

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultStock> queryAll(String year);


    /**
     * 新增数据
     *
     * @param hydResultStock 实体对象
     * @return 保存后的实体对象
     */
    HydResultStock save(HydResultStock hydResultStock);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydResultStock 实体对象
     * @return 更新后的实体对象
     */
    HydResultStock update(HydResultStock hydResultStock);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultStock findById(Long id);

    int batchSave(List<HydResultStockReq> stocks);
}
