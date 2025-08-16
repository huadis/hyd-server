package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelInstructorInfoHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明：社会体育指导员汇总信息  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@Repository
public interface HydExcelInstructorInfoHistoryRepo extends JpaRepository<HydExcelInstructorInfoHistory, Long> {

}
