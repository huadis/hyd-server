package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydOriginLaStadiumHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 校外培训机构访问接口 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Repository
public interface HydOriginLaStadiumHistoryRepo extends JpaRepository<HydOriginLaStadiumHistory, Integer> {

    @Query(value = "select region as districtName, count(*) as stadiumNum from hyd_origin_la_stadium_history WHERE region is not null and region != '' GROUP BY region order by stadiumNum desc", nativeQuery = true)
    List<Map<String, Object>> stadiumCountByDistrict(String year);
}
