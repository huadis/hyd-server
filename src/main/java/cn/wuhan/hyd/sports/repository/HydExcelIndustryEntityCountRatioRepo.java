package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelIndustryEntityCountRatio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 体育产业-场主体数量（分类占比）表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Repository
public interface HydExcelIndustryEntityCountRatioRepo extends JpaRepository<HydExcelIndustryEntityCountRatio, Long> {

    @Query(value = "SELECT \n" +
            "    entityType, \n" +
            "    proportion \n" +
            "FROM \n" +
            "    hyd_excel_industry_entity_count_ratio\n" +
            "WHERE \n" +
            "    statisticalYear = ?1 \n" +
            "ORDER BY proportion DESC;", nativeQuery = true)
    List<Map<String,Object>> stat(String year);
}
