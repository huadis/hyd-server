package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能说明： 体育基础设施-设施全貌 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultFacilityRepo extends JpaRepository<HydResultFacility, Long> {

    /**
     * 设施全貌
     *
     * @param year 年份
     * @return 设施全貌
     */
    @Query(value = "SELECT * FROM hyd_result_facility WHERE statisticalYear = ?1 ORDER BY createdTime DESC", nativeQuery = true)
    List<HydResultFacility> facilityOverview(String year);

    /**
     * 设施类型名称
     *
     * @param year 年份
     * @return 设施类型名称
     */
    @Query(value = "SELECT DISTINCT(facilityTypeName) FROM hyd_result_facility WHERE statisticalYear = ?1 ORDER BY createdTime DESC", nativeQuery = true)
    List<String> allTypeName(String year);

    @Modifying
    @Query(value = "DELETE FROM hyd_result_facility WHERE batchNo != ?1 AND statisticalYear = ?2", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo, int statisticalYear);
}
