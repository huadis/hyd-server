package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultCouponAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能说明： 体育消费卷-消费券总金额 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultCouponAmountRepo extends JpaRepository<HydResultCouponAmount, Long> {

    @Query(value = "SELECT * FROM hyd_result_coupon_amount WHERE statisticalYear = ?1 and type = ?2 and activityName = ?3 and groupName = ?4 ORDER BY createdTime DESC LIMIT 1", nativeQuery = true)
    HydResultCouponAmount findCouponAmount(String year, String type, String activityName, String groupName);

    @Query(value = "SELECT DISTINCT type from hyd_result_coupon_amount WHERE statisticalYear = ?1", nativeQuery = true)
    List<String> allType(String year);

    @Query(value = "SELECT DISTINCT activityName from hyd_result_coupon_amount WHERE statisticalYear = ?1 and type = ?2", nativeQuery = true)
    List<String> allActivityName(String year, String type);

    @Query(value = "SELECT DISTINCT groupName from hyd_result_coupon_amount WHERE statisticalYear = ?1 and type = ?2 and activityName = ?3", nativeQuery = true)
    List<String> allGroupName(String year, String type, String activityName);

    @Modifying
    @Query(value = "DELETE FROM hyd_result_coupon_amount WHERE batchNo != ?1 AND statisticalYear = ?2", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo, Integer statisticalYear);
}
