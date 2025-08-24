package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultOrderMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能说明： 体育消费卷-订单趋势 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultOrderMonthRepo extends JpaRepository<HydResultOrderMonth, Long> {

    @Query(value = "SELECT * FROM hyd_result_order_month WHERE YEAR(createdTime) = ?1 ORDER BY createdTime DESC", nativeQuery = true)
    List<HydResultOrderMonth> list(String year);
}
