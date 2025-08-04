package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydCouponStadiumTop;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定/体育消费卷-消费券场馆预订Top <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydCouponStadiumTopService {

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 实体对象列表
     */
    PageResult<HydCouponStadiumTop> queryAll(Pageable pageable);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydCouponStadiumTop> queryAll();


    /**
     * 新增数据
     *
     * @param hydCouponStadiumTop 实体对象
     * @return 保存后的实体对象
     */
    HydCouponStadiumTop save(HydCouponStadiumTop hydCouponStadiumTop);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydCouponStadiumTop 实体对象
     * @return 更新后的实体对象
     */
    HydCouponStadiumTop update(HydCouponStadiumTop hydCouponStadiumTop);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydCouponStadiumTop findById(Long id);

    List<Map<String, Object>> stadiumTop5();

    int batchSave(List<HydCouponStadiumTop> couponStadiumTops);
}
