package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.MapUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.repository.*;
import cn.wuhan.hyd.sports.service.IHydExcelIndustryService;
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
 * 功能说明： 体育产业 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
@Service
public class HydExcelIndustryServiceImpl implements IHydExcelIndustryService {
    @Resource
    private HydExcelIndustryCoreIndicatorsRepo industryCoreIndicatorsRepo;
    @Resource
    private HydExcelIndustryEmployeeCountRepo industryEmployeeCountRepo;
    @Resource
    private HydExcelIndustryEntityCountRatioRepo industryEntityCountRatioRepo;
    @Resource
    private HydExcelIndustryGoodsPurchaseRateRepo industryGoodsPurchaseRateRepo;
    @Resource
    private HydExcelIndustryGrowthValueTrendRepo industryGrowthValueTrendRepo;
    @Resource
    private HydExcelIndustryScaleTrendRepo industryScaleTrendRepo;
    @Resource
    private HydExcelIndustryTrainingParticipationRateRepo industryTrainingParticipationRateRepo;

    @Override
    public PageResult<HydExcelIndustryCoreIndicators> queryAllIndustryCoreIndicators(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelIndustryCoreIndicators> pageResult = industryCoreIndicatorsRepo.findAll(pageable);
        PageResult<HydExcelIndustryCoreIndicators> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydExcelIndustryCoreIndicators> queryAllIndustryCoreIndicators() {
        return industryCoreIndicatorsRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelIndustryCoreIndicators save(HydExcelIndustryCoreIndicators industryCoreIndicators) {
        return industryCoreIndicatorsRepo.save(industryCoreIndicators);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIndustryCoreIndicatorsById(Long id) {
        industryCoreIndicatorsRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelIndustryCoreIndicators updateIndustryCoreIndicators(HydExcelIndustryCoreIndicators industryCoreIndicators) {
        if (industryCoreIndicators.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findIndustryCoreIndicatorsById(industryCoreIndicators.getId());
        return industryCoreIndicatorsRepo.save(industryCoreIndicators);
    }

    @Override
    public HydExcelIndustryCoreIndicators findIndustryCoreIndicatorsById(Long id) {
        return industryCoreIndicatorsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("体育产业-核心指标不存在，ID：" + id));
    }

    @Override
    public PageResult<HydExcelIndustryEmployeeCount> queryAllIndustryEmployeeCount(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelIndustryEmployeeCount> pageResult = industryEmployeeCountRepo.findAll(pageable);
        PageResult<HydExcelIndustryEmployeeCount> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydExcelIndustryEmployeeCount> queryAllIndustryEmployeeCount() {
        return industryEmployeeCountRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelIndustryEmployeeCount save(HydExcelIndustryEmployeeCount industryEmployeeCount) {
        return industryEmployeeCountRepo.save(industryEmployeeCount);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIndustryEmployeeCountById(Long id) {
        industryEmployeeCountRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelIndustryEmployeeCount updateIndustryEmployeeCount(HydExcelIndustryEmployeeCount industryEmployeeCount) {
        if (industryEmployeeCount.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findIndustryEmployeeCountById(industryEmployeeCount.getId());
        return industryEmployeeCountRepo.save(industryEmployeeCount);
    }

    @Override
    public HydExcelIndustryEmployeeCount findIndustryEmployeeCountById(Long id) {
        return industryEmployeeCountRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("体育产业-从业人员数量（分类统计）表，ID：" + id));
    }

    @Override
    public PageResult<HydExcelIndustryEntityCountRatio> queryAllIndustryEntityCountRatio(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelIndustryEntityCountRatio> pageResult = industryEntityCountRatioRepo.findAll(pageable);
        PageResult<HydExcelIndustryEntityCountRatio> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydExcelIndustryEntityCountRatio> queryAllIndustryEntityCountRatio() {
        return industryEntityCountRatioRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelIndustryEntityCountRatio save(HydExcelIndustryEntityCountRatio industryEntityCountRatio) {
        return industryEntityCountRatioRepo.save(industryEntityCountRatio);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIndustryEntityCountRatioById(Long id) {
        industryEntityCountRatioRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelIndustryEntityCountRatio updateIndustryEntityCountRatio(HydExcelIndustryEntityCountRatio industryEntityCountRatio) {
        if (industryEntityCountRatio.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findIndustryEntityCountRatioById(industryEntityCountRatio.getId());
        return industryEntityCountRatioRepo.save(industryEntityCountRatio);
    }

    @Override
    public HydExcelIndustryEntityCountRatio findIndustryEntityCountRatioById(Long id) {
        return industryEntityCountRatioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("体育产业-场主体数量（分类占比）表，ID：" + id));
    }

    @Override
    public PageResult<HydExcelIndustryGoodsPurchaseRate> queryAllIndustryGoodsPurchaseRate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelIndustryGoodsPurchaseRate> pageResult = industryGoodsPurchaseRateRepo.findAll(pageable);
        PageResult<HydExcelIndustryGoodsPurchaseRate> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydExcelIndustryGoodsPurchaseRate> queryAllIndustryGoodsPurchaseRate() {
        return industryGoodsPurchaseRateRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelIndustryGoodsPurchaseRate save(HydExcelIndustryGoodsPurchaseRate industryGoodsPurchaseRate) {
        return industryGoodsPurchaseRateRepo.save(industryGoodsPurchaseRate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIndustryGoodsPurchaseRateById(Long id) {
        industryGoodsPurchaseRateRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelIndustryGoodsPurchaseRate updateIndustryGoodsPurchaseRate(HydExcelIndustryGoodsPurchaseRate industryGoodsPurchaseRate) {
        if (industryGoodsPurchaseRate.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findIndustryGoodsPurchaseRateById(industryGoodsPurchaseRate.getId());
        return industryGoodsPurchaseRateRepo.save(industryGoodsPurchaseRate);
    }

    @Override
    public HydExcelIndustryGoodsPurchaseRate findIndustryGoodsPurchaseRateById(Long id) {
        return industryGoodsPurchaseRateRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("体育产业-居民体育用品购买率表，ID：" + id));
    }

    @Override
    public PageResult<HydExcelIndustryGrowthValueTrend> queryAllIndustryGrowthValueTrend(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelIndustryGrowthValueTrend> pageResult = industryGrowthValueTrendRepo.findAll(pageable);
        PageResult<HydExcelIndustryGrowthValueTrend> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydExcelIndustryGrowthValueTrend> queryAllIndustryGrowthValueTrend() {
        return industryGrowthValueTrendRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelIndustryGrowthValueTrend save(HydExcelIndustryGrowthValueTrend industryGrowthValueTrend) {
        return industryGrowthValueTrendRepo.save(industryGrowthValueTrend);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIndustryGrowthValueTrendById(Long id) {
        industryGrowthValueTrendRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelIndustryGrowthValueTrend updateIndustryGrowthValueTrend(HydExcelIndustryGrowthValueTrend industryGrowthValueTrend) {
        if (industryGrowthValueTrend.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findIndustryGrowthValueTrendById(industryGrowthValueTrend.getId());
        return industryGrowthValueTrendRepo.save(industryGrowthValueTrend);
    }

    @Override
    public HydExcelIndustryGrowthValueTrend findIndustryGrowthValueTrendById(Long id) {
        return industryGrowthValueTrendRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("体育产业-总增速和增加值（年度趋势）表，ID：" + id));
    }

    @Override
    public PageResult<HydExcelIndustryScaleTrend> queryAllIndustryScaleTrend(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelIndustryScaleTrend> pageResult = industryScaleTrendRepo.findAll(pageable);
        PageResult<HydExcelIndustryScaleTrend> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydExcelIndustryScaleTrend> queryAllIndustryScaleTrend() {
        return industryScaleTrendRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelIndustryScaleTrend save(HydExcelIndustryScaleTrend industryScaleTrend) {
        return industryScaleTrendRepo.save(industryScaleTrend);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIndustryScaleTrendById(Long id) {
        industryScaleTrendRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelIndustryScaleTrend updateIndustryScaleTrend(HydExcelIndustryScaleTrend industryScaleTrend) {
        if (industryScaleTrend.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findIndustryScaleTrendById(industryScaleTrend.getId());
        return industryScaleTrendRepo.save(industryScaleTrend);
    }

    @Override
    public HydExcelIndustryScaleTrend findIndustryScaleTrendById(Long id) {
        return industryScaleTrendRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("体育产业-总规模（年度趋势）表，ID：" + id));
    }

    @Override
    public PageResult<HydExcelIndustryTrainingParticipationRate> queryAllIndustryTrainingParticipationRate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelIndustryTrainingParticipationRate> pageResult = industryTrainingParticipationRateRepo.findAll(pageable);
        PageResult<HydExcelIndustryTrainingParticipationRate> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydExcelIndustryTrainingParticipationRate> queryAllIndustryTrainingParticipationRate() {
        return industryTrainingParticipationRateRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelIndustryTrainingParticipationRate save(HydExcelIndustryTrainingParticipationRate industryTrainingParticipationRate) {
        return industryTrainingParticipationRateRepo.save(industryTrainingParticipationRate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIndustryTrainingParticipationRateById(Long id) {
        industryTrainingParticipationRateRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelIndustryTrainingParticipationRate updateIndustryTrainingParticipationRate(HydExcelIndustryTrainingParticipationRate industryTrainingParticipationRate) {
        if (industryTrainingParticipationRate.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findIndustryTrainingParticipationRateById(industryTrainingParticipationRate.getId());
        return industryTrainingParticipationRateRepo.save(industryTrainingParticipationRate);
    }

    @Override
    public HydExcelIndustryTrainingParticipationRate findIndustryTrainingParticipationRateById(Long id) {
        return industryTrainingParticipationRateRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("体育产业-居民体育培训项目参与率表，ID：" + id));
    }

    @Override
    public boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData) {
        List<HydExcelIndustryCoreIndicators> coreIndicators = new ArrayList<>();
        List<HydExcelIndustryScaleTrend> scaleTrends = new ArrayList<>();
        List<HydExcelIndustryEntityCountRatio> entityCountRatios = new ArrayList<>();
        List<HydExcelIndustryGrowthValueTrend> growthValueTrends = new ArrayList<>();
        List<HydExcelIndustryTrainingParticipationRate> trainingParticipationRates = new ArrayList<>();
        List<HydExcelIndustryGoodsPurchaseRate> goodsPurchaseRates = new ArrayList<>();
        List<HydExcelIndustryEmployeeCount> employeeCounts = new ArrayList<>();
        sheetMapData.forEach((key, list) -> {
            Map<String, Object> rowData = new HashMap<>();
            switch (key) {
                case "核心指标总表":
                    list.forEach(m -> {
                        coreIndicators.add(MapUtil.map2Object(HydExcelIndustryCoreIndicators.class, m));
                    });
                    break;
                case "总规模（年度趋势）表":
                    list.forEach(m -> {
                        scaleTrends.add(MapUtil.map2Object(HydExcelIndustryScaleTrend.class, m));
                    });
                    break;
                case "场主体数量（分类占比）表":
                    list.forEach(m -> {
                        entityCountRatios.add(MapUtil.map2Object(HydExcelIndustryEntityCountRatio.class, m));
                    });
                    break;
                case "总增速和增加值（年度趋势）表":
                    list.forEach(m -> {
                        growthValueTrends.add(MapUtil.map2Object(HydExcelIndustryGrowthValueTrend.class, m));
                    });
                    break;
                case "居民体育培训项目参与率表":
                    list.forEach(m -> {
                        trainingParticipationRates.add(MapUtil.map2Object(HydExcelIndustryTrainingParticipationRate.class, m));
                    });
                    break;
                case "居民体育用品购买率表":
                    list.forEach(m -> {
                        goodsPurchaseRates.add(MapUtil.map2Object(HydExcelIndustryGoodsPurchaseRate.class, m));
                    });
                    break;
                case "从业人员数量（分类统计）表":
                    list.forEach(m -> {
                        employeeCounts.add(MapUtil.map2Object(HydExcelIndustryEmployeeCount.class, m));
                    });
                    break;
            }
        });
        // 批量保存
        industryCoreIndicatorsRepo.saveAll(coreIndicators);
        industryScaleTrendRepo.saveAll(scaleTrends);
        industryEntityCountRatioRepo.saveAll(entityCountRatios);
        industryGrowthValueTrendRepo.saveAll(growthValueTrends);
        industryTrainingParticipationRateRepo.saveAll(trainingParticipationRates);
        industryGoodsPurchaseRateRepo.saveAll(goodsPurchaseRates);
        industryEmployeeCountRepo.saveAll(employeeCounts);
        return true;
    }

    /**
     * 总览
     *
     * @return
     */
    @Override
    public HydExcelIndustryCoreIndicators overview() {
        return industryCoreIndicatorsRepo.overview();
    }

    /**
     * 体育产业总规模
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> industryScaleTrendStat() {
        return industryScaleTrendRepo.stat();
    }

    /**
     * 体育产业市场主体数量
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> industryEntityCountRatioStat() {
        return industryEntityCountRatioRepo.stat();
    }

    /**
     * 体育产业总增速和增加值
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> industryGrowthValueTrendStat() {
        return industryGrowthValueTrendRepo.stat();
    }

    /**
     * 居民体育用品购买率
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> industryGoodsPurchaseRateStat() {
        return industryGoodsPurchaseRateRepo.stat();
    }

    /**
     * 体育产业从业人员数量
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> industryEmployeeCountStat() {
        return industryEmployeeCountRepo.stat();
    }

    /**
     * 居民体育培训项目参与率
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> industryTrainingParticipationRateStat() {
        return industryTrainingParticipationRateRepo.stat();
    }
}
