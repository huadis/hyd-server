package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelSportsOrgDistrictStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 体育组织-区属统计数据访问接口
 * 功能说明：提供体育组织区属统计数据的数据库操作 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月28日 <br>
 */
@Repository
public interface HydExcelSportsOrgDistrictStatRepo extends JpaRepository<HydExcelSportsOrgDistrictStat, Long> {

    @Query(value = "SELECT districtName, districtNum from hyd_excel_sports_organization_district_stat WHERE districtName is not null AND districtName != '' order by districtNum", nativeQuery = true)
    List<Map<String, Object>> districtCountByYear(String year);

}
