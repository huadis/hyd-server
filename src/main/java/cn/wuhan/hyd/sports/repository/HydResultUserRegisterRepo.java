package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultUserRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定-每月新增用户 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultUserRegisterRepo extends JpaRepository<HydResultUserRegister, Long> {

    /**
     * 统计用户用户增长
     *
     * @return 包含 增长数、增长率
     */
    @Query(value = "SELECT " +
            "month AS monthName, " +
            "userNum AS newUserCount " +
            "FROM hyd_result_user_register WHERE YEAR(createdTime) = ?1 " +
            "ORDER BY createdTime limit 12", nativeQuery = true)
    List<Map<String, Object>> countStadiumUserGrowthStat(String year);

    @Modifying
    @Query(value = "DELETE FROM hyd_result_user_register WHERE batchNo != ?1", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo);
}
