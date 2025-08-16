package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelIndustryGrowthValueTrendHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 体育产业-总增速和增加值（年度趋势）表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Repository
public interface HydExcelIndustryGrowthValueTrendHistoryRepo extends JpaRepository<HydExcelIndustryGrowthValueTrendHistory, Long> {
}
