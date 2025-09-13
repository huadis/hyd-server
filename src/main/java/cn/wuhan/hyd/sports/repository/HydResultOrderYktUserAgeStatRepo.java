package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultOrderYktUserAgeStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月24日 <br>
 */
@Repository
public interface HydResultOrderYktUserAgeStatRepo extends JpaRepository<HydResultOrderYktUserAgeStat, Long> {

    @Query(value = "SELECT * FROM hyd_result_order_ykt_userage_stat WHERE statisticalYear = ?1 ORDER BY FIELD(ageGroup, '2岁', '3岁', '4岁', '5岁', '6岁', '7岁', '8岁', '9岁', '10岁', '11岁', '12岁', '13岁', '14岁')", nativeQuery = true)
    List<HydResultOrderYktUserAgeStat> listUserAge(String year);

    @Modifying
    @Query(value = "DELETE FROM hyd_result_order_ykt_userage_stat WHERE statisticalYear = ?1", nativeQuery = true)
    int deleteByStatisticalYear(Integer statisticalYear);
}
