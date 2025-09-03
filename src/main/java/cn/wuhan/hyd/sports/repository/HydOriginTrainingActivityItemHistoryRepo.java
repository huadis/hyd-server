package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydOriginTrainingActivityItemHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 培训活动支持的项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Repository
public interface HydOriginTrainingActivityItemHistoryRepo extends JpaRepository<HydOriginTrainingActivityItemHistory, String> {

    @Query(value = "select sportName, count(*) as num from hyd_origin_training_activity_item_history WHERE sportName IS NOT NULL AND sportName != '' GROUP BY sportName order by num desc limit 10", nativeQuery = true)
    List<Map<String, Object>> itemCountTop10BySportName(String year);

    @Query(value = "select sportName, count(*) as num from hyd_origin_training_activity_item_history WHERE sportName IS NOT NULL AND sportName != '' GROUP BY sportName order by num desc", nativeQuery = true)
    List<Map<String, Object>> itemCountBySportName(String year);
}
