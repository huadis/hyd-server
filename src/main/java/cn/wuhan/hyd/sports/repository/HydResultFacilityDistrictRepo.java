package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultFacilityDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 体育基础设施-设施各区分布 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydResultFacilityDistrictRepo extends JpaRepository<HydResultFacilityDistrict, Long> {
}
