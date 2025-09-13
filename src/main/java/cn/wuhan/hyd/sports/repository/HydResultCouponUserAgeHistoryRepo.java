package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultCouponUserAgeHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * 功能说明： 体育消费卷-券用户年龄分布 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultCouponUserAgeHistoryRepo extends JpaRepository<HydResultCouponUserAgeHistory, Long> {

    @Query(value = "SELECT * FROM hyd_result_coupon_user_age_history WHERE (:startTime IS NULL OR createdTime >= :startTime) " +
            "AND (:endTime IS NULL OR createdTime <= :endTime)",
            countQuery = "SELECT COUNT(*) FROM hyd_result_coupon_user_age_history " +
                    "WHERE (:startTime IS NULL OR createdTime >= :startTime) AND (:endTime IS NULL OR createdTime <= :endTime)", nativeQuery = true)
    Page<HydResultCouponUserAgeHistory> findAllByTimeRange(Pageable pageable, @Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);
}
