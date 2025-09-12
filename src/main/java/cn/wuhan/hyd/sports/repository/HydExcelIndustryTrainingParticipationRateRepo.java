package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelIndustryTrainingParticipationRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 体育产业-居民体育培训项目参与率表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Repository
public interface HydExcelIndustryTrainingParticipationRateRepo extends JpaRepository<HydExcelIndustryTrainingParticipationRate, Long> {

    @Query(value = "SELECT entityType, growthRate FROM hyd_excel_industry_training_participation_rate " +
            "WHERE statisticalYear = ?1 ORDER BY growthRate DESC;", nativeQuery = true)
    List<Map<String, Object>> stat(String year);

    @Modifying
    @Query(value = "DELETE FROM hyd_excel_industry_training_participation_rate WHERE batchNo != ?1", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo);
}
