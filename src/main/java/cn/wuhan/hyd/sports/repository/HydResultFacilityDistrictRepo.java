package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultFacilityDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能说明： 体育基础设施-设施各区分布 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultFacilityDistrictRepo extends JpaRepository<HydResultFacilityDistrict, Long> {

    /**
     * 设施全貌
     *
     * @param year 年份
     * @return 设施全貌
     */
    @Query(value = "SELECT * FROM hyd_result_facility_district WHERE YEAR(createdTime) = ?1 AND facilityTypeName = ?2 ORDER BY CAST(facilityNum AS UNSIGNED) DESC", nativeQuery = true)
    List<HydResultFacilityDistrict> districtDistribution(String year, String typeName);

    @Modifying
    @Query(value = "DELETE FROM hyd_result_facility_district WHERE batchNo != ?1", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo);
}
