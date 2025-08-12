package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydUserRepurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 功能说明： 场馆预定-复购率 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydUserRepurchaseRepository extends JpaRepository<HydUserRepurchase, Long> {
    /**
     * 统计用户复购率
     *
     * @return 包含
     */
    @Query(value = "SELECT " +
            "`1Num` AS onceCount, " +
            "bt2and5Num AS bt2and5Count, " +
            "over5Num AS over5Count, " +
            "over10Num AS over10Count, " +
            "over50Num AS over50Count, " +
            "(`1Num` + `bt2and5Num` + `over5Num` + `over10Num` + `over50Num`) AS totalCount " +
            "FROM hyd_user_repurchase " +
            "ORDER BY createdTime DESC LIMIT 1", nativeQuery = true)
    Map<String, Object> countStadiumUserRepurchaseStat();
}
