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

    @Query(value = "SELECT * FROM hyd_result_stock WHERE YEAR(createdTime) = ?1 ORDER BY createdTime DESC", nativeQuery = true)
    List<HydResultStock> list(String year);

    @Modifying
    @Query(value = "DELETE FROM hyd_result_stock WHERE batchNo != ?1", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo);
}
