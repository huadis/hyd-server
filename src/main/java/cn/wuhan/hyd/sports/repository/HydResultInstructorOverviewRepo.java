package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultInstructorOverview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 功能说明： 社会体育指导员-概览 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月27日 <br>
 */
@Repository
public interface HydResultInstructorOverviewRepo extends JpaRepository<HydResultInstructorOverview, Long> {

    @Query(value = "SELECT * FROM hyd_result_instructor_overview WHERE statisticalYear = ?1 ORDER BY createdTime DESC limit 1", nativeQuery = true)
    Map<String, Object> overview(String year);

    @Modifying
    @Query(value = "DELETE FROM hyd_result_instructor_overview WHERE statisticalYear = ?1", nativeQuery = true)
    int deleteByStatisticalYear(Integer statisticalYear);
}
