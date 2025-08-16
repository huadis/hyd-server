package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydOriginTenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能说明：组织机构表数据访问接口 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月14日 <br>
 */
@Repository
public interface HydOriginTenantRepo extends JpaRepository<HydOriginTenant, String> {

    /**
     * 根据省份编码查询租户
     * @param province 省份编码
     * @return 租户列表
     */
    List<HydOriginTenant> findByProvince(String province);

    /**
     * 根据名称模糊查询
     * @param tenantName 租户名称
     * @return 租户列表
     */
    List<HydOriginTenant> findByTenantNameLike(String tenantName);
}
