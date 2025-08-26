package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelPublicEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 大众赛事-体育赛事信息访问接口 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Repository
public interface HydExcelPublicEventsRepo extends JpaRepository<HydExcelPublicEvents, Long> {

}
