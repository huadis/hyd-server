package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydOriginOrder;
import cn.wuhan.hyd.sports.req.HydOriginOrderReq;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 订单表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
public interface IHydOriginOrderService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydOriginOrder> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydOriginOrder> queryAll();


    /**
     * 新增数据
     *
     * @param hydOriginOrder 实体对象
     * @return 保存后的实体对象
     */
    HydOriginOrder save(HydOriginOrder hydOriginOrder);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(String id);

    /**
     * 更新数据
     *
     * @param hydOriginOrder 实体对象
     * @return 更新后的实体对象
     */
    HydOriginOrder update(HydOriginOrder hydOriginOrder);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydOriginOrder findById(String id);

    Map<String, Object> orderStat();

    int batchSave(List<HydOriginOrderReq> orders);


}
