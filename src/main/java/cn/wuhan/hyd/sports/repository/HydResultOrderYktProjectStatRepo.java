package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultOrderYktProjectStat;
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
public interface HydResultOrderYktProjectStatRepo extends JpaRepository<HydResultOrderYktProjectStat, Long> {

    @Query(value = "SELECT * FROM hyd_result_order_ykt_project_stat WHERE YEAR(createdTime) = ?1 ORDER BY num DESC", nativeQuery = true)
    List<HydResultOrderYktProjectStat> listProject(String year);
}
