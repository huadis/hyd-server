package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultOrderStadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 体育消费卷-场馆消费券订单金额Top5 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultOrderStadiumRepo extends JpaRepository<HydResultOrderStadium, Long> {

    @Query(value = "SELECT stadiumName, orderAmount FROM hyd_result_order_stadium WHERE statisticalYear = ?1 ORDER BY orderAmount DESC limit 5", nativeQuery = true)
    List<Map<String, Object>> stadiumTop5(String year);

    @Modifying
    @Query(value = "DELETE FROM hyd_result_order_stadium WHERE batchNo != ?1 AND statisticalYear = ?2", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo, Integer statisticalYear);
}
