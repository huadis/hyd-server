package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelPublicEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 大众赛事-体育赛事信息访问接口 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Repository
public interface HydExcelPublicEventsRepo extends JpaRepository<HydExcelPublicEvents, Long> {

    /**
     * 总赛事场次
     */
    @Query(value = "SELECT COUNT(id) AS count FROM hyd_excel_public_events", nativeQuery = true)
    Long countAll();

    /**
     * 总参与人数
     */
    @Query(value = "SELECT SUM(participantCount) AS totalParticipantCount FROM hyd_excel_public_events", nativeQuery = true)
    Long totalParticipantCount();

    /**
     * 国际赛事场次
     */
    @Query(value = "SELECT COUNT(id) AS internationalEventCount FROM hyd_excel_public_events WHERE eventLevel = '国际级'", nativeQuery = true)
    Long internationalEventCount();

    /**
     * 国家级赛事场次
     */
    @Query(value = "SELECT COUNT(id) AS nationalEventCount FROM hyd_excel_public_events WHERE eventLevel = '国家级'", nativeQuery = true)
    Long nationalEventCount();

    /**
     * 武汉品牌赛事
     */
    @Query(value = "SELECT COUNT(id) AS wuhanBrandEventCount FROM hyd_excel_public_events WHERE eventName LIKE '%武汉品牌%'", nativeQuery = true)
    Long wuhanBrandEventCount();

    /**
     * 全民健身赛事
     */
    @Query(value = "SELECT COUNT(id) AS nationalFitnessEventCount FROM hyd_excel_public_events WHERE eventLevel = '区级'", nativeQuery = true)
    Long nationalFitnessEventCount();

    /**
     * 各月办赛数据
     */
    @Query(value = "SELECT " +
            "MONTH(eventDate) AS monthNum, " +
            "MONTHNAME(eventDate) AS monthName, " +
            "COUNT(id) AS eventCount " +
            " FROM " +
            "hyd_excel_public_events GROUP BY MONTH(eventDate) ORDER BY MONTH(eventDate)", nativeQuery = true)
    List<Map<String, Object>> latestMonthStat();

    /**
     * 赛事数量 TOP5 项目
     */
    @Query(value = "SELECT " +
            "sportItem, " +
            "COUNT(id) AS count " +
            " FROM " +
            "hyd_excel_public_events GROUP BY sportItem ORDER BY count DESC limit 5", nativeQuery = true)
    List<Map<String, Object>> sportItemTop5();


}
