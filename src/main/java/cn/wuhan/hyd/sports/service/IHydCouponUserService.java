package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydCouponUser;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 功能说明： 驾驶舱-券用户分析 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydCouponUserService {

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 实体对象列表
     */
    PageResult<HydCouponUser> queryAll(Pageable pageable);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydCouponUser> queryAll();


    /**
     * 新增数据
     *
     * @param hydCouponUser 实体对象
     * @return 保存后的实体对象
     */
    HydCouponUser save(HydCouponUser hydCouponUser);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydCouponUser 实体对象
     * @return 更新后的后的实体对象
     */
    HydCouponUser update(HydCouponUser hydCouponUser);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydCouponUser findById(Long id);

    int batchSave(List<HydCouponUser> couponUsers);
}
