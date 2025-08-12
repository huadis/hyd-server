package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydIndustryCoreIndicators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 体育产业-核心指标总表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Repository
public interface HydIndustryCoreIndicatorsRepository extends JpaRepository<HydIndustryCoreIndicators, Long> {
}
