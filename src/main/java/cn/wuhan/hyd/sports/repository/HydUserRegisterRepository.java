package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydUserRegister;
import cn.wuhan.hyd.sports.resp.StadiumUserGrowthStatResp;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface HydUserRegisterRepository extends JpaRepository<HydUserRegister, Long> {

    /**
     * 统计用户用户增长
     *
     * @return 包含 增长数、增长率
     */
    @Query(value = "SELECT " +
            "month AS monthName, " +
            "userNum AS newUserCount " +
            "FROM hyd_user_register " +
            "ORDER BY month", nativeQuery = true)
    List<Map<String, Object>> countStadiumUserGrowthStat();
}
