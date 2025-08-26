package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultEventsParticipantLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明：大众赛事-参赛人数人档表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月26日 <br>
 */
@Repository
public interface HydResultEventsParticipantLevelRepo extends JpaRepository<HydResultEventsParticipantLevel, Long> {

}
