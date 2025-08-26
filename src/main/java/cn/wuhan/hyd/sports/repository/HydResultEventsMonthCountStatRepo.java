package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultEventsMonthCountStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明：大众赛事-各月办赛数据表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月26日 <br>
 */
@Repository
public interface HydResultEventsMonthCountStatRepo extends JpaRepository<HydResultEventsMonthCountStat, Long> {

    @Query(value = "SELECT eventMonth,eventCount FROM hyd_result_events_month_count_stat " +
            "WHERE statisticalYear = ?1 ORDER BY FIELD(eventMonth, '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月')", nativeQuery = true)
    List<Map<String, Object>> monthStat(String year);
}
