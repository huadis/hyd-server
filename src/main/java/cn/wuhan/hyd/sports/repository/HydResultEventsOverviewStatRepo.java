package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultEventsOverviewStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 功能说明：大众赛事-总览信息表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月26日 <br>
 */
@Repository
public interface HydResultEventsOverviewStatRepo extends JpaRepository<HydResultEventsOverviewStat, Long> {

    @Query(value = "SELECT total, participantCount, internationalCount, nationalCount, provinceCount, cityCount FROM hyd_result_events_overview_stat WHERE statisticalYear = ?1 order by createdTime limit 1", nativeQuery = true)
    Map<String, Object> overview(String year);
}
