package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelIndustryCoreIndicators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 体育产业-核心指标总表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Repository
public interface HydExcelIndustryCoreIndicatorsRepo extends JpaRepository<HydExcelIndustryCoreIndicators, Long> {

    /**
     * 总览
     */
    @Query(value = "SELECT " +
            " * " +
            "FROM " +
            "    hyd_excel_industry_core_indicators WHERE statisticalYear = ?1 " +
            "ORDER BY createdTime limit 1", nativeQuery = true)
    HydExcelIndustryCoreIndicators overview(String year);
}
