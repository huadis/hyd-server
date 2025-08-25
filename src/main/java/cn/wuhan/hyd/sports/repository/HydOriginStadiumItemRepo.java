package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydOriginStadiumItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆培训项目访问接口 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Repository
public interface HydOriginStadiumItemRepo extends JpaRepository<HydOriginStadiumItem, String> {

    @Query(value = "select sportName, count(*) as num from hyd_origin_stadium_item GROUP BY sportName order by num desc limit 10", nativeQuery = true)
    List<Map<String, Integer>> itemCountBySportName(String year);
}
