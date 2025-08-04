package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydOrderMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 体育消费卷-订单趋势 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydOrderMonthRepository extends JpaRepository<HydOrderMonth, Long> {

}
