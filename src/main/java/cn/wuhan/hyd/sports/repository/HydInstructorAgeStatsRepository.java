package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydInstructorAgeStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 体育指导员年龄区间统计信息 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@Repository
public interface HydInstructorAgeStatsRepository extends JpaRepository<HydInstructorAgeStats, Long> {

    /**
     * 年龄统计
     */
    @Query(value = "SELECT " +
            "    ageInterval, " +
            "    personCount " +
            "FROM " +
            "    hyd_excel_instructor_age_stats " +
            "ORDER BY " +
            "    FIELD(ageInterval, '20岁以下', '20-30岁', '31-40岁', '41-50岁', '51-60岁', '61-70岁', '71-80岁', '80岁以上');", nativeQuery = true)
    List<Map<String, Object>> ageIntervalStat();
}
