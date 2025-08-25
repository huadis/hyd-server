package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultOrderYktCourseStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月24日 <br>
 */
@Repository
public interface HydResultOrderYktCourseStatRepo extends JpaRepository<HydResultOrderYktCourseStat, Long> {

    @Query(value = "SELECT * FROM hyd_result_order_ykt_course_stat WHERE YEAR(createdTime) = ?1 ORDER BY num DESC LIMIT 5", nativeQuery = true)
    List<HydResultOrderYktCourseStat> courseTop5(String year);
}
