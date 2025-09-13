package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydOriginTenantHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * 功能说明：组织机构表数据访问接口 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月14日 <br>
 */
@Repository
public interface HydOriginTenantHistoryRepo extends JpaRepository<HydOriginTenantHistory, String> {

    @Query(value = "SELECT * FROM hyd_origin_tenant_history WHERE (:startTime IS NULL OR createdTime >= :startTime) " +
            "AND (:endTime IS NULL OR createdTime <= :endTime)",
            countQuery = "SELECT COUNT(*) FROM hyd_origin_tenant_history " +
                    "WHERE (:startTime IS NULL OR createdTime >= :startTime) AND (:endTime IS NULL OR createdTime <= :endTime)", nativeQuery = true)
    Page<HydOriginTenantHistory> findAllByTimeRange(Pageable pageable, @Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);
}
