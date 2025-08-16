package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydOriginTenantHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明：组织机构表数据访问接口 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月14日 <br>
 */
@Repository
public interface HydOriginTenantHistoryRepo extends JpaRepository<HydOriginTenantHistory, String> {

}
