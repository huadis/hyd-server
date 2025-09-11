package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultStadiumMapPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 资源名称：驾驶舱-电子地图点位信息 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月21日 <br>
 */
@Repository
public interface HydResultStadiumMapPointRepo extends JpaRepository<HydResultStadiumMapPoint, Long> {

    @Modifying
    @Query(value = "DELETE FROM hyd_result_stadium_map_point WHERE batchNo != ?1 AND statisticalYear = ?2", nativeQuery = true)
    int deleteByNotBatchNo(String batchNo, Integer statisticalYear);
}
