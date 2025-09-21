package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能说明： 体育消费卷-消费券领用券 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultStockRepo extends JpaRepository<HydResultStock, Long> {

    @Query(value = "SELECT * FROM hyd_result_stock WHERE statisticalYear = ?1 and type = ?2 and activityName = ?3 and groupName = ?4 ORDER BY createdTime DESC", nativeQuery = true)
    List<HydResultStock> list(String year, String type, String activityName, String groupName);

    @Modifying
    @Query(value = "DELETE FROM hyd_result_stock WHERE batchNo != ?1 AND statisticalYear = ?2", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo, Integer statisticalYear);

    @Query(value = "select DISTINCT groupName from hyd_result_stock WHERE statisticalYear = ?1 and groupName is not null and groupName != '' order by id", nativeQuery = true)
    List<String> allGroupName(String year);
}
