package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultOrderStadiumHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 体育消费卷-场馆消费券订单金额Top5 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultOrderStadiumHistoryRepo extends JpaRepository<HydResultOrderStadiumHistory, Long> {

}
