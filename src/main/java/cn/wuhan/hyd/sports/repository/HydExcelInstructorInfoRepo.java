package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelInstructorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
public interface HydExcelInstructorInfoRepo extends JpaRepository<HydExcelInstructorInfo, Long> {
    /**
     * 指导项目top15
     */
    @Query(value = "SELECT serviceProject, COUNT(id) AS personCount, " +
            "ROUND(COUNT(id) / (SELECT COUNT(id) FROM hyd_excel_instructor_info) * 100, 2) AS proportion " +
            "FROM hyd_excel_instructor_info " +
            "WHERE createdTime >= DATE_FORMAT(CURDATE(), '%Y-01-01 00:00:00') AND createdTime < DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 1 YEAR), '%Y-01-01 00:00:00') " +
            "GROUP BY serviceProject ORDER BY personCount DESC LIMIT 15", nativeQuery = true)
    List<Map<String, Object>> serviceProjectTop15();

    /**
     * 性别统计
     */
    @Query(value = "SELECT gender, COUNT(id) AS personCount, CONCAT(ROUND(COUNT(id) / (SELECT COUNT(id) FROM hyd_excel_instructor_info) * 100, 2), '%') AS proportion " +
            "FROM hyd_excel_instructor_info " +
            "WHERE createdTime >= DATE_FORMAT(CURDATE(), '%Y-01-01 00:00:00') AND createdTime < DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 1 YEAR), '%Y-01-01 00:00:00') " +
            "GROUP BY gender ", nativeQuery = true)
    List<Map<String, Object>> genderStat();

    /**
     * 级别统计
     */
    @Query(value = "SELECT level, COUNT(id) AS personCount, CONCAT(ROUND(COUNT(id) / (SELECT COUNT(id) FROM hyd_excel_instructor_info) * 100, 2), '%') AS proportion " +
            "FROM hyd_excel_instructor_info " +
            "WHERE createdTime >= DATE_FORMAT(CURDATE(), '%Y-01-01 00:00:00') AND createdTime < DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 1 YEAR), '%Y-01-01 00:00:00') " +
            "GROUP BY level ", nativeQuery = true)
    List<Map<String, Object>> levelStat();

    /**
     * 各区指导人员统计
     */
    @Query(value = "SELECT region, COUNT(id) AS instructorCount FROM hyd_excel_instructor_info " +
            "WHERE createdTime >= DATE_FORMAT(CURDATE(), '%Y-01-01 00:00:00') AND createdTime < DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 1 YEAR), '%Y-01-01 00:00:00') " +
            "GROUP BY region ORDER BY instructorCount DESC", nativeQuery = true)
    List<Map<String, Object>> regionInstructorStat();

    /**
     * 总人数
     */
    @Query(value = "SELECT COUNT(id) AS count FROM hyd_excel_instructor_info WHERE createdTime >= DATE_FORMAT(CURDATE(), '%Y-01-01 00:00:00') AND createdTime < DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 1 YEAR), '%Y-01-01 00:00:00') ", nativeQuery = true)
    Long countAll();

    /**
     * 新增人数
     */
    @Query(value = "SELECT COUNT(id) AS count FROM hyd_excel_instructor_info where createdTime >= DATE_FORMAT(CURDATE(), '%Y-01-01 00:00:00') AND createdTime < DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 1 YEAR), '%Y-01-01 00:00:00') and certifyTime is not null and certifyTime != '' and STR_TO_DATE(certifyTime, '%Y-%m-%d') BETWEEN DATE_FORMAT(CURDATE(), '%Y-01-01') AND DATE_FORMAT(CURDATE(), '%Y-12-31')", nativeQuery = true)
    Long newCount();

    /**
     * 指导项目top15
     */
    @Query(value = "SELECT serviceProject, COUNT(id) AS personCount FROM hyd_excel_instructor_info WHERE createdTime >= DATE_FORMAT(CURDATE(), '%Y-01-01 00:00:00') AND createdTime < DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 1 YEAR), '%Y-01-01 00:00:00') and serviceProject is not null and serviceProject != '' GROUP BY serviceProject ORDER BY personCount DESC", nativeQuery = true)
    List<Map<String, Object>> serviceProject();

    @Query(value = "SELECT serviceProject, COUNT(id) AS personCount FROM hyd_excel_instructor_info where createdTime >= DATE_FORMAT(CURDATE(), '%Y-01-01 00:00:00') AND createdTime < DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 1 YEAR), '%Y-01-01 00:00:00') AND certifyTime is not null and certifyTime != '' and STR_TO_DATE(certifyTime, '%Y-%m-%d') BETWEEN DATE_FORMAT(CURDATE(), '%Y-01-01') AND DATE_FORMAT(CURDATE(), '%Y-12-31') and serviceProject is not null and serviceProject != '' GROUP BY serviceProject ORDER BY personCount DESC", nativeQuery = true)
    List<Map<String, Object>> serviceProjectWithCurrentYear();

    @Modifying
    @Query(value = "DELETE FROM hyd_excel_instructor_info WHERE batchNo != ?1", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo);
}
