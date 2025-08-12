package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydInstructorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明：社会体育指导员汇总信息  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@Repository
public interface HydInstructorInfoRepository extends JpaRepository<HydInstructorInfo, Long> {

    /**
     * 指导项目top15
     */
    @Query(value = "SELECT " +
            "serviceProject, " +
            "COUNT(id) AS personCount, " +
            "ROUND(COUNT(id) / (SELECT COUNT(id) FROM hyd_instructor_info) * 100, 2) AS proportion " +
            " FROM " +
            "hyd_instructor_info GROUP BY serviceProject " +
            "ORDER BY personCount DESC LIMIT 15", nativeQuery = true)
    List<Map<String, Object>> serviceProjectTop15();

    /**
     * 性别统计
     */
    @Query(value = "SELECT " +
            "gender, " +
            "COUNT(id) AS personCount, " +
            "CONCAT(ROUND(COUNT(id) / (SELECT COUNT(id) FROM hyd_instructor_info) * 100, 2), '%') AS proportion " +
            " FROM " +
            "hyd_instructor_info GROUP BY gender ", nativeQuery = true)
    List<Map<String, Object>> genderStat();

    /**
     * 级别统计
     */
    @Query(value = "SELECT " +
            "level, " +
            "COUNT(id) AS personCount, " +
            "CONCAT(ROUND(COUNT(id) / (SELECT COUNT(id) FROM hyd_instructor_info) * 100, 2), '%') AS proportion " +
            " FROM " +
            "hyd_instructor_info GROUP BY level ", nativeQuery = true)
    List<Map<String, Object>> levelStat();

    /**
     * 各区指导人员统计
     */
    @Query(value = "SELECT " +
            "region, " +
            "COUNT(id) AS instructorCount " +
            " FROM " +
            "hyd_instructor_info GROUP BY region ORDER BY instructorCount DESC", nativeQuery = true)
    List<Map<String, Object>> regionInstructorStat();

}
