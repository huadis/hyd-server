package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydOriginTrainingActivityItemHistory;
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
 * 功能说明： 培训活动支持的项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Repository
public interface HydOriginTrainingActivityItemHistoryRepo extends JpaRepository<HydOriginTrainingActivityItemHistory, String> {

    @Query(value = "select sportName, count(*) as num from hyd_origin_training_activity_item_history WHERE sportName IS NOT NULL AND sportName != '' AND createdTime >= DATE_FORMAT(CURDATE(), '%Y-01-01 00:00:00') AND createdTime < DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 1 YEAR), '%Y-01-01 00:00:00') GROUP BY sportName order by num desc limit 10", nativeQuery = true)
    List<Map<String, Object>> itemCountTop10BySportName();

    @Query(value = "select sportName, count(*) as num from hyd_origin_training_activity_item_history WHERE sportName IS NOT NULL AND sportName != '' AND createdTime >= DATE_FORMAT(CURDATE(), '%Y-01-01 00:00:00') AND createdTime < DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 1 YEAR), '%Y-01-01 00:00:00') GROUP BY sportName order by num desc", nativeQuery = true)
    List<Map<String, Object>> itemCountBySportName();

    @Query(value = "SELECT * FROM hyd_origin_training_activity_item_history WHERE (:startTime IS NULL OR createdTime >= :startTime) " +
            "AND (:endTime IS NULL OR createdTime <= :endTime)",
            countQuery = "SELECT COUNT(*) FROM hyd_origin_training_activity_item_history " +
                    "WHERE (:startTime IS NULL OR createdTime >= :startTime) AND (:endTime IS NULL OR createdTime <= :endTime)", nativeQuery = true)
    Page<HydOriginTrainingActivityItemHistory> findAllByTimeRange(Pageable pageable, @Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);
}
