package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.MapUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydExcelPublicEvents;
import cn.wuhan.hyd.sports.repository.HydExcelPublicEventsRepo;
import cn.wuhan.hyd.sports.service.IHydExcelPublicEventsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Service
public class HydExcelPublicEventsServiceImpl implements IHydExcelPublicEventsService {
    @Resource
    private HydExcelPublicEventsRepo hydExcelPublicEventsRepo;

    @Override
    public PageResult<HydExcelPublicEvents> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelPublicEvents> pageResult = hydExcelPublicEventsRepo.findAll(pageable);
        PageResult<HydExcelPublicEvents> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydExcelPublicEvents> queryAll() {
        return hydExcelPublicEventsRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelPublicEvents save(HydExcelPublicEvents publicEvents) {
        return hydExcelPublicEventsRepo.save(publicEvents);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        hydExcelPublicEventsRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelPublicEvents update(HydExcelPublicEvents publicEvents) {
        if (publicEvents.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findById(publicEvents.getId());
        return hydExcelPublicEventsRepo.save(publicEvents);
    }

    @Override
    public HydExcelPublicEvents findById(Long id) {
        return hydExcelPublicEventsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("大众赛事-体育赛事信息表，ID：" + id));
    }

    @Override
    public boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData) {
        List<HydExcelPublicEvents> publicEvents = new ArrayList<>();
        List<Map<String, Object>> list = sheetMapData.get("Sheet1");
        // 批量保存
        list.forEach(m -> {
            publicEvents.add(MapUtil.map2Object(HydExcelPublicEvents.class, m));
        });
        hydExcelPublicEventsRepo.saveAll(publicEvents);
        return true;
    }

    @Override
    public Map<String, Object> overview() {
        Map<String, Object> result = new HashMap<>();
        result.put("total", hydExcelPublicEventsRepo.countAll());
        result.put("participantCount", hydExcelPublicEventsRepo.totalParticipantCount());
        result.put("internationalCount", hydExcelPublicEventsRepo.internationalEventCount());
        result.put("nationalCount", hydExcelPublicEventsRepo.nationalEventCount());
        result.put("wuhanBrandCount", hydExcelPublicEventsRepo.wuhanBrandEventCount());
        result.put("nationalFitnessCount", hydExcelPublicEventsRepo.nationalFitnessEventCount());
        return result;
    }

    /**
     * 各月办赛数据
     */
    @Override
    public List<Map<String, Object>> latestMonthStat() {
        return hydExcelPublicEventsRepo.latestMonthStat();
    }

    @Override
    public List<Map<String, Object>> sportItemTop5() {
        return hydExcelPublicEventsRepo.sportItemTop5();
    }

    @Override
    public List<Map<String, Object>> participantCountStat() {
        return hydExcelPublicEventsRepo.participantCountStat();
    }

    @Override
    public List<Map<String, Object>> currentMouthEvents() {
        return hydExcelPublicEventsRepo.currentMouthEvents();
    }

}
