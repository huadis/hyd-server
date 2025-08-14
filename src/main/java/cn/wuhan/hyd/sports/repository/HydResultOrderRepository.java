package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定-订单数量 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultOrderRepository extends JpaRepository<HydResultOrder, Long> {

    @Query(value = "SELECT " +
            "month, " +
            "orderNum, " +
            "orderAmount, " +
            "couponAmount " +
            "FROM hyd_result_order ", nativeQuery = true)
    List<Map<String, Object>> orderStat();

    @Query(value = "SELECT " +
            "sum(orderNum) orderNumSum " +
            "FROM hyd_result_order ", nativeQuery = true)
    Long sumOrderNum();

    @Query(value = "SELECT " +
            "sum(orderAmount) orderAmountSum " +
            "FROM hyd_result_order ", nativeQuery = true)
    Long sumOrderAmount();
}
