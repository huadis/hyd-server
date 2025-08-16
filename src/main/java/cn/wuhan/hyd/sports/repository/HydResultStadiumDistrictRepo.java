package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultStadiumDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定-在线场馆各区情况 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultStadiumDistrictRepo extends JpaRepository<HydResultStadiumDistrict, Long> {

    /**
     * 统计各区场馆数量
     *
     * @return 包含区场馆统计数据列表，包含区名称及场馆数量
     */
    @Query(value = "SELECT " +
            "d.districtName as districtName, " +
            "(SUM(CAST(IFNULL(d.couponStadiumNum, '0') AS UNSIGNED)) + " +
            "SUM(CAST(IFNULL(d.publicStadiumNum, '0') AS UNSIGNED)) + " +
            "SUM(CAST(IFNULL(d.socialStadiumNum, '0') AS UNSIGNED))) AS stadiumCount " +
            "FROM hyd_result_stadium_district d " +
            "GROUP BY d.districtName", nativeQuery = true)
    List<Map<String, Object>> countStadiumDistrict();
}
