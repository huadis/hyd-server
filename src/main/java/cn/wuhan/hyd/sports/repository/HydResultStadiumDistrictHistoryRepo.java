package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultStadiumDistrictHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 场馆预定-在线场馆各区情况 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultStadiumDistrictHistoryRepo extends JpaRepository<HydResultStadiumDistrictHistory, Long> {

}
