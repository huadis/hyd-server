package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydStadiumMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 驾驶舱-电子地图 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Repository
public interface HydStadiumMapRepository extends JpaRepository<HydStadiumMap, Long> {
}
