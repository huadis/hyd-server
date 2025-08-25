package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelIndustryEmployeeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 体育产业-从业人员数量（分类统计）表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Repository
public interface HydExcelIndustryEmployeeCountRepo extends JpaRepository<HydExcelIndustryEmployeeCount, Long> {

    @Query(value = "SELECT " +
            "    entityType, " +
            "    personCount " +
            "FROM " +
            "    hyd_excel_industry_employee_count " +
            "WHERE statisticalYear = ?1 " +
            "ORDER BY personCount DESC;", nativeQuery = true)
    List<Map<String,Object>> stat(String year);
}
