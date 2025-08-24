package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.MapUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydExcelPublicEvents;
import cn.wuhan.hyd.sports.domain.HydExcelPublicEventsHistory;
import cn.wuhan.hyd.sports.repository.HydExcelPublicEventsHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydExcelPublicEventsRepo;
import cn.wuhan.hyd.sports.service.IHydExcelPublicEventsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Service
public class HydExcelPublicEventsServiceImpl implements IHydExcelPublicEventsService {
    @Resource
    private HydExcelPublicEventsRepo publicEventsRepo;
    @Resource
    private HydExcelPublicEventsHistoryRepo publicEventsHistoryRepo;

    @Override
    public PageResult<HydExcelPublicEvents> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelPublicEvents> pageResult = publicEventsRepo.findAll(pageable);
        PageResult<HydExcelPublicEvents> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydExcelPublicEvents> queryAll() {
        return publicEventsRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelPublicEvents save(HydExcelPublicEvents publicEvents) {
        return publicEventsRepo.save(publicEvents);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        publicEventsRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelPublicEvents update(HydExcelPublicEvents publicEvents) {
        if (publicEvents.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findById(publicEvents.getId());
        return publicEventsRepo.save(publicEvents);
    }

    @Override
    public HydExcelPublicEvents findById(Long id) {
        return publicEventsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("大众赛事-体育赛事信息表，ID：" + id));
    }

    @Override
    public boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData) {
        List<HydExcelPublicEvents> publicEvents = new ArrayList<>();
        List<HydExcelPublicEventsHistory> publicEventsHistories = new ArrayList<>();

        List<Map<String, Object>> list = sheetMapData.get("Sheet1");
        // 批量保存
        list.forEach(m -> {
            publicEvents.add(MapUtil.map2Object(HydExcelPublicEvents.class, m));
            publicEventsHistories.add(MapUtil.map2Object(HydExcelPublicEventsHistory.class, m));
        });
        publicEventsRepo.saveAll(publicEvents);
        publicEventsHistoryRepo.saveAll(publicEventsHistories);
        return true;
    }

    @Override
    public Map<String, Object> overview() {
        Map<String, Object> result = new HashMap<>();
        result.put("total", publicEventsRepo.countAll());
        result.put("participantCount", publicEventsRepo.totalParticipantCount());
        result.put("internationalCount", publicEventsRepo.internationalEventCount());
        result.put("nationalCount", publicEventsRepo.nationalEventCount());
        result.put("wuhanBrandCount", publicEventsRepo.wuhanBrandEventCount());
        result.put("nationalFitnessCount", publicEventsRepo.nationalFitnessEventCount());
        return result;
    }

    /**
     * 各月办赛数据
     */
    @Override
    public List<Map<String, Object>> latestMonthStat() {
        return publicEventsRepo.latestMonthStat();
    }

    @Override
    public List<Map<String, Object>> sportItemTop5() {
        return publicEventsRepo.sportItemTop5();
    }

    @Override
    public List<Map<String, Object>> participantCountStat() {
        return publicEventsRepo.participantCountStat();
    }

    @Override
    public List<Map<String, Object>> currentMouthEvents() {
        return publicEventsRepo.currentMouthEvents();
    }

}
