package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelIndustryGrowthValueTrend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 体育产业-总增速和增加值（年度趋势）表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Repository
public interface HydExcelIndustryGrowthValueTrendRepo extends JpaRepository<HydExcelIndustryGrowthValueTrend, Long> {

    @Query(value = "SELECT " +
            "    statisticalYear , " +
            "    totalOutputValue , " +
            "    growthRate " +
            "FROM " +
            "    hyd_excel_industry_growth_value_trend " +
            "WHERE " +
            "    statisticalYear BETWEEN ?1 AND ?2 " +
            "ORDER BY statisticalYear ASC ", nativeQuery = true)
    List<Map<String, Object>> stat(String from, String to);

    @Modifying
    @Query(value = "DELETE FROM hyd_excel_industry_growth_value_trend WHERE batchNo != ?1", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo);
}
