package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydUserAge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
     */
    @Query(value = "SELECT " +
            "under18Num AS '18岁以下', " +
            "bt18and25Num AS '19岁-25岁', " +
            "bt26and30Num AS '26岁-30岁', " +
            "bt31and35Num AS '31岁-35岁', " +
            "bt36and40Num AS '36岁-40岁', " +
            "bt41and45Num AS '41岁-45岁', " +
            "bt46and50Num AS '46岁-50岁', " +
            "over50Num AS '50岁以上' " +
            "FROM hyd_user_age " +
            "ORDER BY createdTime DESC LIMIT 1", nativeQuery = true)
    Map<String, Object> countStadiumUserAgeStat();
}
