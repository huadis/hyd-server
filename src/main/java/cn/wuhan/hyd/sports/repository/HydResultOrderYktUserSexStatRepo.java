package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultOrderYktUserSexStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月24日 <br>
 */
@Repository
public interface HydResultOrderYktUserSexStatRepo extends JpaRepository<HydResultOrderYktUserSexStat, Long> {

    @Query(value = "SELECT * FROM hyd_result_order_ykt_usersex_stat WHERE statisticalYear = ?1 ORDER BY createdTime DESC", nativeQuery = true)
    List<HydResultOrderYktUserSexStat> listUserSex(String year);
}
