package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.MapUtil;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.repository.*;
import cn.wuhan.hyd.sports.service.IHydIndustryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 体育产业 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Service
public class HydIndustryServiceImpl implements IHydIndustryService {
    @Resource
    private HydIndustryCoreIndicatorsRepository hydIndustryCoreIndicatorsRepository;
    @Resource
    private HydIndustryEmployeeCountRepository hydIndustryEmployeeCountRepository;
    @Resource
    private HydIndustryEntityCountRatioRepository hydIndustryEntityCountRatioRepository;
    @Resource
    private HydIndustryGoodsPurchaseRateRepository hydIndustryGoodsPurchaseRateRepository;
    @Resource
    private HydIndustryGrowthValueTrendRepository hydIndustryGrowthValueTrendRepository;
    @Resource
    private HydIndustryScaleTrendRepository hydIndustryScaleTrendRepository;
    @Resource
    private HydIndustryTrainingParticipationRateRepository hydIndustryTrainingParticipationRateRepository;

    @Override
    public boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData) {
        List<HydIndustryCoreIndicators> coreIndicators = new ArrayList<>();
        List<HydIndustryScaleTrend> scaleTrends = new ArrayList<>();
        List<HydIndustryEntityCountRatio> entityCountRatios = new ArrayList<>();
        List<HydIndustryGrowthValueTrend> growthValueTrends = new ArrayList<>();
        List<HydIndustryTrainingParticipationRate> trainingParticipationRates = new ArrayList<>();
        List<HydIndustryGoodsPurchaseRate> goodsPurchaseRates = new ArrayList<>();
        List<HydIndustryEmployeeCount> employeeCounts = new ArrayList<>();
        sheetMapData.forEach((key, list) -> {
            Map<String, Object> rowData = new HashMap<>();
            switch (key) {
                case "核心指标总表":
                    list.forEach(m -> {
                        coreIndicators.add(MapUtil.map2Object(HydIndustryCoreIndicators.class, m));
                    });
                    break;
                case "总规模（年度趋势）表":
                    list.forEach(m -> {
                        scaleTrends.add(MapUtil.map2Object(HydIndustryScaleTrend.class, m));
                    });
                    break;
                case "场主体数量（分类占比）表":
                    list.forEach(m -> {
                        entityCountRatios.add(MapUtil.map2Object(HydIndustryEntityCountRatio.class, m));
                    });
                    break;
                case "总增速和增加值（年度趋势）表":
                    list.forEach(m -> {
                        growthValueTrends.add(MapUtil.map2Object(HydIndustryGrowthValueTrend.class, m));
                    });
                    break;
                case "居民体育培训项目参与率表":
                    list.forEach(m -> {
                        trainingParticipationRates.add(MapUtil.map2Object(HydIndustryTrainingParticipationRate.class, m));
                    });
                    break;
                case "居民体育用品购买率表":
                    list.forEach(m -> {
                        goodsPurchaseRates.add(MapUtil.map2Object(HydIndustryGoodsPurchaseRate.class, m));
                    });
                    break;
                case "从业人员数量（分类统计）表":
                    list.forEach(m -> {
                        employeeCounts.add(MapUtil.map2Object(HydIndustryEmployeeCount.class, m));
                    });
                    break;
            }
        });
        // 批量保存
        hydIndustryCoreIndicatorsRepository.saveAll(coreIndicators);
        hydIndustryScaleTrendRepository.saveAll(scaleTrends);
        hydIndustryEntityCountRatioRepository.saveAll(entityCountRatios);
        hydIndustryGrowthValueTrendRepository.saveAll(growthValueTrends);
        hydIndustryTrainingParticipationRateRepository.saveAll(trainingParticipationRates);
        hydIndustryGoodsPurchaseRateRepository.saveAll(goodsPurchaseRates);
        hydIndustryEmployeeCountRepository.saveAll(employeeCounts);
        return true;
    }
}
