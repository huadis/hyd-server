package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultCouponAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 体育消费卷-消费券总金额 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultCouponAmountRepo extends JpaRepository<HydResultCouponAmount, Long> {

    /**
     * 统计用户渠道
     *
     * @return 包含 实名用户数、领券用户数、用券用户数、下单用户数
     */
    @Query(value = "SELECT * FROM hyd_result_coupon_amount WHERE YEAR(createdTime) = ?1 ORDER BY createdTime DESC LIMIT 1", nativeQuery = true)
    HydResultCouponAmount findLatestCouponAmount(String year);
}
