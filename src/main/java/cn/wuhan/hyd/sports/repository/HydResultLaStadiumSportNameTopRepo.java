package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultLaStadiumSportNameTop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 校外培训机构-项目类型占比TOP10 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月27日 <br>
 */
@Repository
public interface HydResultLaStadiumSportNameTopRepo extends JpaRepository<HydResultLaStadiumSportNameTop, Long> {
    @Query(value = "SELECT * FROM hyd_result_la_stadium_sport_name_top WHERE statisticalYear = ?1 ORDER BY num DESC limit 10", nativeQuery = true)
    List<Map<String, Object>> itemCountTop10BySportName(String year);
}
