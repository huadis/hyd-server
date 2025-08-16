package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultOrderSportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 场馆预定/体育消费卷-项目消费券订单金额Top5 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultOrderSportHistoryRepo extends JpaRepository<HydResultOrderSportHistory, Long> {

}
