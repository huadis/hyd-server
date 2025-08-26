package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydResultEventsParticipantLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能说明：大众赛事-参赛人数人档表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月26日 <br>
 */
@Repository
public interface HydResultEventsParticipantLevelRepo extends JpaRepository<HydResultEventsParticipantLevel, Long> {

    @Query(value = "SELECT * FROM hyd_result_events_participant_level WHERE statisticalYear = ?1 ORDER BY " +
            " FIELD(participantLevel, '<100人', '100-300人', '301-1000人', '>1000人');", nativeQuery = true)
    List<Map<String, Object>> participantCountStat(String year);
}
