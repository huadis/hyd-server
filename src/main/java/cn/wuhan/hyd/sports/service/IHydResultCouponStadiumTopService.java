package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultCouponStadiumTop;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定/体育消费卷-消费券场馆预订Top <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydResultCouponStadiumTopService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultCouponStadiumTop> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultCouponStadiumTop> queryAll();


    /**
     * 新增数据
     *
     * @param hydResultCouponStadiumTop 实体对象
     * @return 保存后的实体对象
     */
    HydResultCouponStadiumTop save(HydResultCouponStadiumTop hydResultCouponStadiumTop);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydResultCouponStadiumTop 实体对象
     * @return 更新后的实体对象
     */
    HydResultCouponStadiumTop update(HydResultCouponStadiumTop hydResultCouponStadiumTop);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultCouponStadiumTop findById(Long id);

    List<Map<String, Object>> stadiumTop5();

    int batchSave(List<HydResultCouponStadiumTop> couponStadiumTops);
}
