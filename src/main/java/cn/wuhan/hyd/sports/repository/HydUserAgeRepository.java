package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydUserAge;
import cn.wuhan.hyd.sports.resp.StadiumUserAgeStatResp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定-年龄占比 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydUserAgeRepository extends JpaRepository<HydUserAge, Long> {
    /**
     * 统计用户渠道
     *
     * @return 包含 实名用户数、领券用户数、用券用户数、下单用户数
     */
    @Query(value = "SELECT " +
            "under18Num AS under18Count, " +
            "bt18and25Num AS bt18and25Count, " +
            "bt26and30Num AS bt26and30Count, " +
            "bt31and35Num AS bt31and35Count, " +
            "bt36and40Num AS bt36and40Count, " +
            "bt41and45Num AS bt41and45Count, " +
            "bt46and50Num AS bt46and50Count, " +
            "over50Num AS over50Count " +
            "FROM hyd_user_age " +
            "ORDER BY createdTime DESC LIMIT 1", nativeQuery = true)
    List<Map<String, Object>> countStadiumUserAgeStat();
}
