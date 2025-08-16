package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelIndustryScaleTrendHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 体育产业-总规模（年度趋势）表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Repository
public interface HydExcelIndustryScaleTrendHistoryRepo extends JpaRepository<HydExcelIndustryScaleTrendHistory, Long> {
}
