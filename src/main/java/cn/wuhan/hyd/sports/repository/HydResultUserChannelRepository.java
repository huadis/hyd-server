package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultUserChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定-用户来源渠道 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultUserChannelRepository extends JpaRepository<HydResultUserChannel, Long> {

    /**
     * 统计用户渠道
     *
     * @return 包含 实名用户数、领券用户数、用券用户数、下单用户数
     */
    @Query(value = "SELECT " +
            "realNameUserNum AS realNameUserCount, " +
            "receiveCouponUserNum AS receiveCouponUserCount, " +
            "useCouponUserNum AS useCouponUserCount, " +
            "orderUserNum AS orderUserCount " +
            "FROM hyd_result_user_channel " +
            "ORDER BY createdTime DESC LIMIT 1", nativeQuery = true)
    List<Map<String, Object>> countStadiumUserChannelStat();
}
