package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultUserRepurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 场馆预定-复购率 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultUserRepurchaseHistoryRepo extends JpaRepository<HydResultUserRepurchaseHistory, Long> {

}
