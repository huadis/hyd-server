package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydOriginStadiumItemHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆培训项目访问接口 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Repository
public interface HydOriginStadiumItemHistoryRepo extends JpaRepository<HydOriginStadiumItemHistory, String> {

    @Query(value = "select sportName, count(*) as num from hyd_origin_stadium_item_history WHERE sportName IS NOT NULL AND sportName != '' GROUP BY sportName order by num desc limit 10", nativeQuery = true)
    List<Map<String, Object>> itemCountTop10BySportName(String year);


    @Query(value = "select sportName, count(*) as num from hyd_origin_stadium_item_history WHERE sportName IS NOT NULL AND sportName != '' GROUP BY sportName order by num desc", nativeQuery = true)
    List<Map<String, Object>> itemCountBySportName(String year);

    @Query(value = "SELECT * FROM hyd_origin_stadium_item_history WHERE (:startTime IS NULL OR createdTime >= :startTime) " +
            "AND (:endTime IS NULL OR createdTime <= :endTime)",
            countQuery = "SELECT COUNT(*) FROM hyd_origin_stadium_item_history " +
                    "WHERE (:startTime IS NULL OR createdTime >= :startTime) AND (:endTime IS NULL OR createdTime <= :endTime)", nativeQuery = true)
    Page<HydOriginStadiumItemHistory> findAllByTimeRange(Pageable pageable, @Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);

}
