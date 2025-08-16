package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelIndustryScaleTrend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 体育产业-总规模（年度趋势）表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Repository
public interface HydExcelIndustryScaleTrendRepo extends JpaRepository<HydExcelIndustryScaleTrend, Long> {

    @Query(value = "SELECT \n" +
            "    statisticalYear ,\n" +
            "    totalOutputValue ,\n" +
            "    growthRate \n" +
            "FROM \n" +
            "    hyd_excel_industry_scale_trend\n" +
            "ORDER BY \n" +
            "    statisticalYear;", nativeQuery = true)
    List<Map<String,Object>> stat();
}
