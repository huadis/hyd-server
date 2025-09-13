package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultCouponUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 功能说明： 体育消费卷-券用户分析 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultCouponUserRepo extends JpaRepository<HydResultCouponUser, Long> {

    @Query(value = "SELECT receiveCouponNum, useCouponNum, maleNum, femaleNum " +
            "FROM hyd_result_coupon_user WHERE statisticalYear = ?1 ORDER BY createdTime DESC limit 1", nativeQuery = true)
    Map<String, Object> latestCouponUser(String year);

    @Modifying
    @Query(value = "DELETE FROM hyd_result_coupon_user WHERE batchNo != ?1 AND statisticalYear = ?2", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo, Integer statisticalYear);
}
