package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultOrder;
import cn.wuhan.hyd.sports.req.HydResultOrderReq;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 驾驶舱-订单数量 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydResultOrderService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultOrder> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultOrder> queryAll();


    /**
     * 新增数据
     *
     * @param hydResultOrder 实体对象
     * @return 保存后的实体对象
     */
    HydResultOrder save(HydResultOrder hydResultOrder);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydResultOrder 实体对象
     * @return 更新后的实体对象
     */
    HydResultOrder update(HydResultOrder hydResultOrder);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultOrder findById(Long id);

    Map<String, Object> orderStat(String year);

    int batchSave(List<HydResultOrderReq> orders);

    Map<String, Object> overview(String year);
}
