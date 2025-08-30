package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelSportsOrg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 体育组织数据访问接口
 * 功能说明：提供体育组织相关的数据库操作方法 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月28日 <br>
 */
@Repository
public interface HydExcelSportsOrgRepo extends JpaRepository<HydExcelSportsOrg, Long> {

    @Query(value = "select districtName, count(*) as districtNum from hyd_excel_sports_organization WHERE districtName is not null and districtName != '' group by districtName", nativeQuery = true)
    List<Map<String, Object>> districtStatCount();
}
