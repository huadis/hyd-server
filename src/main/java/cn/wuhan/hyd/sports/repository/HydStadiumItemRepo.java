package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydStadiumItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 场馆培训项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Repository
public interface HydStadiumItemRepo extends JpaRepository<HydStadiumItem, String> {

}
