package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelSportsOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 体育组织数据访问接口
 * 功能说明：提供体育组织相关的数据库操作方法 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月28日 <br>
 */
@Repository
public interface HydExcelSportsOrganizationRepo extends JpaRepository<HydExcelSportsOrganization, Long> {

}
