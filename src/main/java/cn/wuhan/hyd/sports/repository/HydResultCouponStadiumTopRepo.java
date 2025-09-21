package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultCouponStadiumTop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定/体育消费卷-消费券场馆预订Top <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultCouponStadiumTopRepo extends JpaRepository<HydResultCouponStadiumTop, Long> {

    @Query(value = "SELECT stadiumName, couponAmount FROM hyd_result_coupon_stadium_top WHERE statisticalYear = ?1 ORDER BY CAST(couponAmount AS UNSIGNED) DESC ", nativeQuery = true)
    List<Map<String, Object>> stadiumTop5(String statisticalYear);

    @Query(value = "SELECT stadiumName, couponAmount FROM hyd_result_coupon_stadium_top WHERE statisticalYear = ?1 and type = ?2 and activityName = ?3 and groupName = ?4 ORDER BY CAST(couponAmount AS UNSIGNED) DESC ", nativeQuery = true)
    List<Map<String, Object>> stadiumTop10(String statisticalYear, String type, String activityName, String groupName);

    @Modifying
    @Query(value = "DELETE FROM hyd_result_coupon_stadium_top WHERE batchNo != ?1 AND statisticalYear = ?2", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo, Integer statisticalYear);
}
