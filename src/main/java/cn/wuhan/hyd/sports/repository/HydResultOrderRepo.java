package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 功能说明： 场馆预定-订单数量 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultOrderRepo extends JpaRepository<HydResultOrder, Long> {

    @Query(value = "SELECT " +
            "orderNum, " +
            "orderAmount, " +
            "couponAmount " +
            "FROM hyd_result_order WHERE YEAR(createdTime) = ?1 ORDER BY createdTime DESC LIMIT 1", nativeQuery = true)
    Map<String, Object> overview(String year);

    @Query(value = "SELECT " +
            "sum(orderNum) orderNumSum " +
            "FROM hyd_result_order ", nativeQuery = true)
    Long sumOrderNum();

    @Query(value = "SELECT " +
            "sum(orderAmount) orderAmountSum " +
            "FROM hyd_result_order ", nativeQuery = true)
    Long sumOrderAmount();

    @Modifying
    @Query(value = "DELETE FROM hyd_result_order WHERE batchNo != ?1", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo);
}
