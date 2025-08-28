package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultInstructorRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 社会体育指导员-各区指导人员统计 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月27日 <br>
 */
@Repository
public interface HydResultInstructorRegionRepo extends JpaRepository<HydResultInstructorRegion, Long> {

    @Query(value = "SELECT * FROM hyd_result_instructor_region WHERE YEAR(createdTime) = ?1 ORDER BY instructorCount DESC", nativeQuery = true)
    List<Map<String, Object>> regionInstructorStat(String year);
}
