package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultCouponUserAge;
import cn.wuhan.hyd.sports.req.HydResultCouponUserAgeReq;

import java.util.List;

/**
 * 功能说明：体育消费卷-券用户年龄分布 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydResultCouponUserAgeService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultCouponUserAge> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultCouponUserAge> queryAll();


    /**
     * 新增数据
     *
     * @param hydResultCouponUserAge 实体对象
     * @return 保存后的实体对象
     */
    HydResultCouponUserAge save(HydResultCouponUserAge hydResultCouponUserAge);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydResultCouponUserAge 实体对象
     * @return 更新后的实体对象
     */
    HydResultCouponUserAge update(HydResultCouponUserAge hydResultCouponUserAge);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultCouponUserAge findById(Long id);

    int batchSave(List<HydResultCouponUserAgeReq> couponUserAges);

    HydResultCouponUserAge couponUserAge(String year, String type, String activityName, String groupName);
}
