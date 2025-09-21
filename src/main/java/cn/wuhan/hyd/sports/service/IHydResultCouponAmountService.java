package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultCouponAmount;
import cn.wuhan.hyd.sports.req.HydResultCouponAmountReq;

import java.util.List;

/**
 * 功能说明： 体育消费卷-消费券总金额 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydResultCouponAmountService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultCouponAmount> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultCouponAmount> queryAll();


    /**
     * 新增数据
     *
     * @param hydResultCouponAmount 实体对象
     * @return 保存后的实体对象
     */
    HydResultCouponAmount save(HydResultCouponAmount hydResultCouponAmount);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydResultCouponAmount 实体对象
     * @return 更新后的实体对象
     */
    HydResultCouponAmount update(HydResultCouponAmount hydResultCouponAmount);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultCouponAmount findById(Long id);

    int batchSave(List<HydResultCouponAmountReq> couponAmounts);

    HydResultCouponAmount findCouponAmount(String year, String type, String activityName, String groupName);

    List<String> allType(String year);

    List<String> allActivityName(String year, String type);

    List<String> allGroupName(String year, String type, String activityName);
}
