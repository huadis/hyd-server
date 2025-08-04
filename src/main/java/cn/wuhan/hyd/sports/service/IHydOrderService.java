package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydOrder;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 驾驶舱-订单数量 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydOrderService {

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 实体对象列表
     */
    PageResult<HydOrder> queryAll(Pageable pageable);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydOrder> queryAll();


    /**
     * 新增数据
     *
     * @param hydOrder 实体对象
     * @return 保存后的实体对象
     */
    HydOrder save(HydOrder hydOrder);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydOrder 实体对象
     * @return 更新后的实体对象
     */
    HydOrder update(HydOrder hydOrder);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydOrder findById(Long id);

    Map<String, Object> orderStat();

    int batchSave(List<HydOrder> orders);
}
