package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelIndustryGoodsPurchaseRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 体育产业-居民体育用品购买率表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Repository
public interface HydExcelIndustryGoodsPurchaseRateRepo extends JpaRepository<HydExcelIndustryGoodsPurchaseRate, Long> {
}
