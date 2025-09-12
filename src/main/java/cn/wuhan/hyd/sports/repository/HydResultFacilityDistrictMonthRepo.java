package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultFacilityDistrictMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能说明： 体育基础设施-设施各区月数据 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultFacilityDistrictMonthRepo extends JpaRepository<HydResultFacilityDistrictMonth, Long> {

    /**
     * 设施全貌
     *
     * @param year 年份
     * @return 设施全貌
     */
    @Query(value = "SELECT * FROM hyd_result_facility_district_month WHERE statisticalYear = ?1 ORDER BY createdTime DESC", nativeQuery = true)
    List<HydResultFacilityDistrictMonth> inspectMaintenance(String year);

    @Modifying
    @Query(value = "DELETE FROM hyd_result_facility_district_month WHERE batchNo != ?1 AND statisticalYear = ?2", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo, Integer statisticalYear);
}
