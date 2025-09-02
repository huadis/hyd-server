package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultUserRepurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 功能说明： 场馆预定-复购率 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultUserRepurchaseRepo extends JpaRepository<HydResultUserRepurchase, Long> {
    /**
     * 统计用户复购率
     *
     * @return 包含
     */
    @Query(value = "SELECT " +
            "`1Num` AS '1次', " +
            "bt2and5Num AS '2-5次', " +
            "over5Num AS '5次-10次', " +
            "over10Num AS '10次-50次', " +
            "over50Num AS '50次以上' " +
            "FROM hyd_result_user_repurchase WHERE YEAR(createdTime) = ?1 " +
            "ORDER BY createdTime DESC LIMIT 1", nativeQuery = true)
    Map<String, Object> countStadiumUserRepurchaseStat(String year);

    @Modifying
    @Query(value = "DELETE FROM hyd_result_user_repurchase WHERE batchNo != ?1", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo);
}
