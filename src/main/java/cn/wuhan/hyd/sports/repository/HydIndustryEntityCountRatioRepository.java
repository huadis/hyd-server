package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydIndustryEntityCountRatio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 体育产业-场主体数量（分类占比）表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Repository
public interface HydIndustryEntityCountRatioRepository  extends JpaRepository<HydIndustryEntityCountRatio, Long> {
}
