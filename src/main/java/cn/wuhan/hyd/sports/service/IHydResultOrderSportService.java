package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultOrderSport;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 驾驶舱-项目消费券订单金额Top5 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydResultOrderSportService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultOrderSport> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultOrderSport> queryAll();


    /**
     * 新增数据
     *
     * @param hydResultOrderSport 实体对象
     * @return 保存后的实体对象
     */
    HydResultOrderSport save(HydResultOrderSport hydResultOrderSport);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydResultOrderSport 实体对象
     * @return 更新后的实体对象
     */
    HydResultOrderSport update(HydResultOrderSport hydResultOrderSport);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultOrderSport findById(Long id);

    List<Map<String, Object>> projectTop5();

    int batchSave(List<HydResultOrderSport> orderSports);
}
