package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydOriginStadium;
import cn.wuhan.hyd.sports.domain.HydResultCouponAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 培训场馆表访问接口 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
public interface HydOriginStadiumRepo extends JpaRepository<HydOriginStadium, String> {

    @Query(value = "select districtName, count(*) as stadiumNum from hyd_origin_stadium GROUP BY districtName", nativeQuery = true)
    List<Map<String, Object>> stadiumCountByDistrict(String year);
}
