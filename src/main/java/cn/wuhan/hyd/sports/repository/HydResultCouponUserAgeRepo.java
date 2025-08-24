package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultCouponUserAge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 体育消费卷-券用户年龄分布 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultCouponUserAgeRepo extends JpaRepository<HydResultCouponUserAge, Long> {

    @Query(value = "SELECT * FROM hyd_result_coupon_user_age WHERE YEAR(createdTime) = ?1 ORDER BY createdTime DESC limit 1", nativeQuery = true)
    HydResultCouponUserAge latestCouponUserAge(String year);
}
