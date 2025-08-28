package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultLaStadiumDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 校外培训机构-各区场馆数量统计 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月27日 <br>
 */
@Repository
public interface HydResultLaStadiumDistrictRepo extends JpaRepository<HydResultLaStadiumDistrict, Long> {
    @Query(value = "SELECT * FROM hyd_result_la_stadium_district WHERE YEAR(createdTime) = ?1 ORDER BY stadiumNum DESC", nativeQuery = true)
    List<Map<String, Object>> stadiumCountByDistrict(String year);
}
