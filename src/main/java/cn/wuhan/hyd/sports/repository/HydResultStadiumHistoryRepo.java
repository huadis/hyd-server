package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultStadiumHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 场馆预定-在线场馆数量 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultStadiumHistoryRepo extends JpaRepository<HydResultStadiumHistory, Long> {

}
