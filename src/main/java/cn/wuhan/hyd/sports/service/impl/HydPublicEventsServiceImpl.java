package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.MapUtil;
import cn.wuhan.hyd.sports.domain.HydPublicEvents;
import cn.wuhan.hyd.sports.repository.HydPublicEventsRepository;
import cn.wuhan.hyd.sports.service.IHydPublicEventsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Service
public class HydPublicEventsServiceImpl implements IHydPublicEventsService {
    @Resource
    private HydPublicEventsRepository hydPublicEventsRepository;

    @Override
    public boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData) {
        List<HydPublicEvents> publicEvents = new ArrayList<>();
        List<Map<String, Object>> list = sheetMapData.get("Sheet1");
        // 批量保存
        list.forEach(m -> {
            publicEvents.add(MapUtil.map2Object(HydPublicEvents.class, m));
        });
        hydPublicEventsRepository.saveAll(publicEvents);
        return true;
    }

    @Override
    public Map<String, Object> overview() {
        Map<String, Object> result = new HashMap<>();
        result.put("total", hydPublicEventsRepository.countAll());
        result.put("participantCount", hydPublicEventsRepository.totalParticipantCount());
        result.put("internationalCount", hydPublicEventsRepository.internationalEventCount());
        result.put("nationalCount", hydPublicEventsRepository.nationalEventCount());
        result.put("wuhanBrandCount", hydPublicEventsRepository.wuhanBrandEventCount());
        result.put("nationalFitnessCount", hydPublicEventsRepository.nationalFitnessEventCount());
        return result;
    }

    /**
     * 各月办赛数据
     */
    @Override
    public List<Map<String, Object>> latestMonthStat(){
        return hydPublicEventsRepository.latestMonthStat();
    }

    @Override
    public List<Map<String, Object>> sportItemTop5() {
        return hydPublicEventsRepository.sportItemTop5();
    }

}
