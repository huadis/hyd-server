package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydCouponUserAge;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 功能说明：体育消费卷-券用户年龄分布 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydCouponUserAgeService {

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 实体对象列表
     */
    PageResult<HydCouponUserAge> queryAll(Pageable pageable);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydCouponUserAge> queryAll();


    /**
     * 新增数据
     *
     * @param hydCouponUserAge 实体对象
     * @return 保存后的实体对象
     */
    HydCouponUserAge save(HydCouponUserAge hydCouponUserAge);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydCouponUserAge 实体对象
     * @return 更新后的实体对象
     */
    HydCouponUserAge update(HydCouponUserAge hydCouponUserAge);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydCouponUserAge findById(Long id);

    int batchSave(List<HydCouponUserAge> couponUserAges);
}
