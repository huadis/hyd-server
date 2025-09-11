package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultStadiumDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能说明： 场馆预定-在线场馆各区情况 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultStadiumDistrictRepo extends JpaRepository<HydResultStadiumDistrict, Long> {

    /**
     * 统计各区场馆数量
     *
     * @return 包含区场馆统计数据列表，包含区名称及场馆数量
     */
    @Query(value = "SELECT * FROM hyd_result_stadium_district WHERE YEAR(createdTime) = ?1 and districtName is not null and districtName != '' order by CAST(stadiumNum AS UNSIGNED) desc", nativeQuery = true)
    List<HydResultStadiumDistrict> countStadiumDistrict(String year);

    @Modifying
    @Query(value = "DELETE FROM hyd_result_stadium_district WHERE batchNo != ?1 AND statisticalYear = ?2", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo, Integer statisticalYear);
}
