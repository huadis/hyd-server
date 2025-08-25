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
    @Query(value = "SELECT COUNT(id) AS count FROM hyd_excel_public_events WHERE eventYear = ?1", nativeQuery = true)
    Long countAll(String year);

    /**
     * 总参与人数
     */
    @Query(value = "SELECT SUM(participantCount) AS totalParticipantCount FROM hyd_excel_public_events WHERE eventYear = ?1", nativeQuery = true)
    Long totalParticipantCount(String year);

    /**
     * 国际赛事场次
     */
    @Query(value = "SELECT COUNT(id) AS internationalEventCount FROM hyd_excel_public_events WHERE eventYear = ?1 and eventLevel = '国际级'", nativeQuery = true)
    Long internationalEventCount(String year);

    /**
     * 国家级赛事场次
     */
    @Query(value = "SELECT COUNT(id) AS nationalEventCount FROM hyd_excel_public_events WHERE eventYear = ?1 and eventLevel = '国家级'", nativeQuery = true)
    Long nationalEventCount(String year);

    /**
     * 省级
     */
    @Query(value = "SELECT COUNT(id) AS nationalEventCount FROM hyd_excel_public_events WHERE eventYear = ?1 and eventLevel = '省级'", nativeQuery = true)
    Long provinceCount(String year);

    /**
     * 市级
     */
    @Query(value = "SELECT COUNT(id) AS nationalEventCount FROM hyd_excel_public_events WHERE eventYear = ?1 and eventLevel = '市级'", nativeQuery = true)
    Long cityCount(String year);

    /**
     * 各月办赛数据
     */
    @Query(value = "SELECT " +
            "eventMonth, " +
            "COUNT(id) AS eventCount " +
            " FROM " +
            "hyd_excel_public_events WHERE eventYear = ?1 GROUP BY eventMonth ORDER BY " +
            "FIELD(eventMonth, '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月')", nativeQuery = true)
    List<Map<String, Object>> monthStat(String year);

    /**
     * 赛事数量 TOP5 项目
     */
    @Query(value = "SELECT " +
            "sportItem, " +
            "COUNT(id) AS count " +
            " FROM " +
            "hyd_excel_public_events WHERE eventYear = ?1 AND sportItem is not null AND sportItem != '' GROUP BY sportItem ORDER BY count DESC limit 5", nativeQuery = true)
    List<Map<String, Object>> sportItemTop5(String year);


    /**
     * 参赛人数人档
     */
    @Query(value = "SELECT\n" +
            "    CASE \n" +
            "        WHEN participantCount < 100 THEN '<100人'\n" +
            "        WHEN participantCount BETWEEN 100 AND 300 THEN '100-300人'\n" +
            "        WHEN participantCount BETWEEN 301 AND 1000 THEN '301-1000人'\n" +
            "        WHEN participantCount > 1000 THEN '>1000人'\n" +
            "        ELSE '其他' \n" +
            "    END AS participant_level,\n" +
            "    COUNT(*) AS count\n" +
            "FROM\n" +
            "    hyd_excel_public_events WHERE eventYear = ?1 \n" +
            "GROUP BY\n" +
            "    CASE \n" +
            "        WHEN participantCount < 100 THEN '<100人'\n" +
            "        WHEN participantCount BETWEEN 100 AND 300 THEN '100-300人'\n" +
            "        WHEN participantCount BETWEEN 301 AND 1000 THEN '301-1000人'\n" +
            "        WHEN participantCount > 1000 THEN '>1000人'\n" +
            "        ELSE '其他' \n" +
            "    END\n" +
            "ORDER BY\n" +
            "    FIELD(participant_level, '<100人', '100-300人', '301-1000人', '>1000人');", nativeQuery = true)
    List<Map<String, Object>> participantCountStat(String year);


    @Query(value = "SELECT \n" +
            "    eventName ,\n" +
            "    eventMonth ,\n" +
            "    eventDate \n" +
            "FROM \n" +
            "    hyd_excel_public_events\n" +
            "WHERE \n" +
            "    eventYear = ?1 and eventMonth = ?2 \n" +
            "ORDER BY \n" +
            "    eventMonth desc ;", nativeQuery = true)
    List<Map<String, Object>> currentMouthEvents(String year, String month);

    @Query(value = "SELECT district, count(*) as num from hyd_excel_public_events WHERE eventYear = ?1 and district is not null GROUP BY district order by num", nativeQuery = true)
    List<Map<String, Object>> districtCountByYear(String year);

}
