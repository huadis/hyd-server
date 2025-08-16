package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultOrderSport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定/体育消费卷-项目消费券订单金额Top5 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultOrderSportRepo extends JpaRepository<HydResultOrderSport, Long> {
    @Query(value = "SELECT " +
            "sportName, " +
            "orderAmount " +
            "FROM hyd_result_order_sport ", nativeQuery = true)
    List<Map<String, Object>> projectTop5();
}
