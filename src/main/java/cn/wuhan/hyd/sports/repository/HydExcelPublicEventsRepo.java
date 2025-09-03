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
    @Query(value = "SELECT eventYear as statisticalYear, COUNT(id) AS total FROM hyd_excel_public_events WHERE eventYear is not null and eventYear != '' GROUP BY eventYear", nativeQuery = true)
    List<Map<String, Object>> countAll();

    /**
     * 总参与人数
     */
    @Query(value = "SELECT eventYear as statisticalYear, SUM(participantCount) AS participantCount FROM hyd_excel_public_events WHERE eventYear is not null and eventYear != '' GROUP BY eventYear", nativeQuery = true)
    List<Map<String, Object>> totalParticipantCount();

    /**
     * 国际赛事场次
     */
    @Query(value = "SELECT eventYear as statisticalYear, COUNT(id) AS internationalCount FROM hyd_excel_public_events  WHERE eventYear is not null and eventYear != '' and eventLevel = '国际级' GROUP BY eventYear ", nativeQuery = true)
    List<Map<String, Object>> internationalEventCount();

    /**
     * 国家级赛事场次
     */
    @Query(value = "SELECT eventYear as statisticalYear, COUNT(id) AS nationalCount FROM hyd_excel_public_events WHERE eventYear is not null and eventYear != '' and  eventLevel = '国家级' GROUP BY eventYear ", nativeQuery = true)
    List<Map<String, Object>> nationalEventCount();

    /**
     * 省级
     */
    @Query(value = "SELECT eventYear as statisticalYear, COUNT(id) AS provinceCount FROM hyd_excel_public_events WHERE eventYear is not null and eventYear != '' and eventLevel = '省级' GROUP BY eventYear ", nativeQuery = true)
    List<Map<String, Object>> provinceCount();

    /**
     * 市级
     */
    @Query(value = "SELECT eventYear as statisticalYear, COUNT(id) AS cityCount FROM hyd_excel_public_events WHERE eventYear is not null and eventYear != '' and eventLevel = '市级' GROUP BY eventYear ", nativeQuery = true)
    List<Map<String, Object>> cityCount();

    /**
     * 各月办赛数据
     */
    @Query(value = "SELECT " +
            "eventYear as statisticalYear, " +
            "eventMonth, " +
            "COUNT(id) AS eventCount " +
            " FROM " +
            "hyd_excel_public_events WHERE eventYear is not null and eventYear != '' AND eventMonth != '' AND eventMonth is not null GROUP BY eventYear, eventMonth ORDER BY statisticalYear DESC, eventMonth DESC;", nativeQuery = true)
    List<Map<String, Object>> monthStat();

    /**
     * 赛事数量 TOP5 项目
     */
    @Query(value = "SELECT " +
            "  statisticalYear, " +
            "  sportItem, " +
            "  count " +
            "FROM ( " +
            "  SELECT " +
            "    eventYear AS statisticalYear, " +
            "    sportItem, " +
            "    COUNT(id) AS count, " +
            "    RANK() OVER ( " +
            "      PARTITION BY eventYear " +
            "      ORDER BY COUNT(id) DESC " +
            "    ) AS rn " +
            "  FROM hyd_excel_public_events " +
            "  WHERE " +
            "    eventYear IS NOT NULL AND eventYear != '' " +
            "    AND sportItem IS NOT NULL AND sportItem != '' " +
            "  GROUP BY eventYear, sportItem " +
            ") AS ranked_sports " +
            "WHERE rn <= 5 " +
            "ORDER BY statisticalYear DESC, count DESC;", nativeQuery = true)
    List<Map<String, Object>> sportItemTop5();


    /**
     * 参赛人数人档
     */
    @Query(value = "SELECT eventYear AS statisticalYear , " +
            "    CASE " +
            "        WHEN participantCount < 100 THEN '<100人' " +
            "        WHEN participantCount BETWEEN 100 AND 300 THEN '100-300人' " +
            "        WHEN participantCount BETWEEN 301 AND 1000 THEN '301-1000人' " +
            "        WHEN participantCount > 1000 THEN '>1000人' " +
            "        ELSE '其他' " +
            "    END AS participantLevel, " +
            "    COUNT(*) AS count " +
            "FROM " +
            "    hyd_excel_public_events WHERE eventYear is not null and eventYear != '' " +
            "GROUP BY eventYear, " +
            "    CASE " +
            "        WHEN participantCount < 100 THEN '<100人' " +
            "        WHEN participantCount BETWEEN 100 AND 300 THEN '100-300人' " +
            "        WHEN participantCount BETWEEN 301 AND 1000 THEN '301-1000人' " +
            "        WHEN participantCount > 1000 THEN '>1000人' " +
            "        ELSE '其他' " +
            "    END " +
            "ORDER BY statisticalYear DESC, participantLevel DESC;", nativeQuery = true)
    List<Map<String, Object>> participantCountStat();

    @Query(value = "SELECT district, count(*) as num from hyd_excel_public_events WHERE eventYear = ?1 and district is not null AND district != '' GROUP BY district order by num desc", nativeQuery = true)
    List<Map<String, Object>> districtCountByYear(String year);

    @Query(value = "SELECT " +
            "    eventName ," +
            "    eventMonth ," +
            "    eventDate " +
            "FROM " +
            "    hyd_excel_public_events " +
            "WHERE " +
            "    eventYear = ?1 " +
            "ORDER BY " +
            "    sequence desc limit 20;", nativeQuery = true)
    List<Map<String, Object>> currentMouthEvents(String year, String month);
}
