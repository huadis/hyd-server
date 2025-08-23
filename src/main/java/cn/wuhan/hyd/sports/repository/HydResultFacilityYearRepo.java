package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultFacilityYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 体育基础设施-健身点位年数据 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultFacilityYearRepo extends JpaRepository<HydResultFacilityYear, Long> {

    /**
     * 健身点位
     *
     * @param year 年份
     * @return 健身点位
     */
    @Query(value = "SELECT * FROM hyd_result_facility_year WHERE YEAR(createdTime) = ?1 ORDER BY createdTime DESC LIMIT 1", nativeQuery = true)
    HydResultFacilityYear fitnessOverview(String year);
}
