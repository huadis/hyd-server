package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 体育基础设施-设施全貌 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydFacilityRepository extends JpaRepository<HydFacility, Long> {
}
