package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelInstructorAgeGrowthHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 体育指导员年龄区间及增长率统计信息 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@Repository
public interface HydExcelInstructorAgeGrowthHistoryRepo extends JpaRepository<HydExcelInstructorAgeGrowthHistory, Long> {

}
