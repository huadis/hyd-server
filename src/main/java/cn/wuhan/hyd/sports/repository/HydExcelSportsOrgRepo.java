package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydExcelSportsOrg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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

    @Query(value = "select districtName, count(*) as districtNum from hyd_excel_sports_organization WHERE districtName is not null and districtName != '' AND createdTime >= DATE_FORMAT(CURDATE(), '%Y-01-01 00:00:00') AND createdTime < DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 1 YEAR), '%Y-01-01 00:00:00') group by districtName ORDER BY districtNum DESC", nativeQuery = true)
    List<Map<String, Object>> districtStatCount();

    @Query(value = "SELECT * FROM hyd_excel_sports_organization WHERE (:startTime IS NULL OR createdTime >= :startTime) " +
            "AND (:endTime IS NULL OR createdTime <= :endTime)",
            countQuery = "SELECT COUNT(*) FROM hyd_excel_sports_organization " +
                    "WHERE (:startTime IS NULL OR createdTime >= :startTime) AND (:endTime IS NULL OR createdTime <= :endTime)", nativeQuery = true)
    Page<HydExcelSportsOrg> findAllByTimeRange(Pageable pageable, @Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);
}
