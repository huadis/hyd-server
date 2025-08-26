package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultEventsSportItemTop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明：大众赛事-赛事数量TOP5项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月26日 <br>
 */
@Repository
public interface HydResultEventsSportItemTopRepo extends JpaRepository<HydResultEventsSportItemTop, Long> {

    @Query(value = "SELECT * FROM hyd_result_events_sport_item_top WHERE statisticalYear = ?1 order by createdTime", nativeQuery = true)
    List<Map<String, Object>> sportItemTop5(String year);
}
