package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.MapUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.repository.*;
import cn.wuhan.hyd.sports.service.IHydExcelIndustryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
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
    private HydExcelIndustryCoreIndicatorsHistoryRepo industryCoreIndicatorsHistoryRepo;
    @Resource
    private HydExcelIndustryEmployeeCountRepo industryEmployeeCountRepo;
    @Resource
    private HydExcelIndustryEmployeeCountHistoryRepo industryEmployeeCountHistoryRepo;
    @Resource
    private HydExcelIndustryEntityCountRatioRepo industryEntityCountRatioRepo;
    @Resource
    private HydExcelIndustryEntityCountRatioHistoryRepo industryEntityCountRatioHistoryRepo;
    @Resource
    private HydExcelIndustryGoodsPurchaseRateRepo industryGoodsPurchaseRateRepo;
    @Resource
    private HydExcelIndustryGoodsPurchaseRateHistoryRepo industryGoodsPurchaseRateHistoryRepo;
    @Resource
    private HydExcelIndustryGrowthValueTrendRepo industryGrowthValueTrendRepo;
    @Resource
    private HydExcelIndustryGrowthValueTrendHistoryRepo industryGrowthValueTrendHistoryRepo;
    @Resource
    private HydExcelIndustryScaleTrendRepo industryScaleTrendRepo;
    @Resource
    private HydExcelIndustryScaleTrendHistoryRepo industryScaleTrendHistoryRepo;
    @Resource
    private HydExcelIndustryTrainingParticipationRateRepo industryTrainingParticipationRateRepo;
    @Resource
    private HydExcelIndustryTrainingParticipationRateHistoryRepo industryTrainingParticipationRateHistoryRepo;

    private static final Logger log = LoggerFactory.getLogger(IHydExcelIndustryService.class);


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
        String batchNo = UUIDUtil.getBatchNo();
        List<HydExcelIndustryCoreIndicators> coreIndicators = new ArrayList<>();
        List<HydExcelIndustryScaleTrend> scaleTrends = new ArrayList<>();
        List<HydExcelIndustryEntityCountRatio> entityCountRatios = new ArrayList<>();
        List<HydExcelIndustryGrowthValueTrend> growthValueTrends = new ArrayList<>();
        List<HydExcelIndustryTrainingParticipationRate> trainingParticipationRates = new ArrayList<>();
        List<HydExcelIndustryGoodsPurchaseRate> goodsPurchaseRates = new ArrayList<>();
        List<HydExcelIndustryEmployeeCount> employeeCounts = new ArrayList<>();

        List<HydExcelIndustryCoreIndicatorsHistory> coreIndicatorsHistories = new ArrayList<>();
        List<HydExcelIndustryScaleTrendHistory> scaleTrendHistories = new ArrayList<>();
        List<HydExcelIndustryEntityCountRatioHistory> entityCountRatioHistories = new ArrayList<>();
        List<HydExcelIndustryGrowthValueTrendHistory> growthValueTrendHistories = new ArrayList<>();
        List<HydExcelIndustryTrainingParticipationRateHistory> trainingParticipationRateHistories = new ArrayList<>();
        List<HydExcelIndustryGoodsPurchaseRateHistory> goodsPurchaseRateHistories = new ArrayList<>();
        List<HydExcelIndustryEmployeeCountHistory> employeeCountHistories = new ArrayList<>();
        sheetMapData.forEach((key, list) -> {
            switch (key) {
                case "核心指标总表":
                    list.forEach(m -> {
                        HydExcelIndustryCoreIndicators obj = MapUtil.map2Object(HydExcelIndustryCoreIndicators.class, m);
                        obj.setBatchNo(batchNo);
                        coreIndicators.add(obj);

                        HydExcelIndustryCoreIndicatorsHistory historyObj = MapUtil.map2Object(HydExcelIndustryCoreIndicatorsHistory.class, m);
                        historyObj.setBatchNo(batchNo);
                        coreIndicatorsHistories.add(historyObj);
                    });
                    break;
                case "总规模（年度趋势）表":
                    list.forEach(m -> {
                        HydExcelIndustryScaleTrend obj = MapUtil.map2Object(HydExcelIndustryScaleTrend.class, m);
                        obj.setBatchNo(batchNo);
                        scaleTrends.add(obj);

                        HydExcelIndustryScaleTrendHistory historyObj = MapUtil.map2Object(HydExcelIndustryScaleTrendHistory.class, m);
                        historyObj.setBatchNo(batchNo);
                        scaleTrendHistories.add(historyObj);
                    });
                    break;
                case "场主体数量（分类占比）表":
                    list.forEach(m -> {
                        HydExcelIndustryEntityCountRatio obj = MapUtil.map2Object(HydExcelIndustryEntityCountRatio.class, m);
                        obj.setBatchNo(batchNo);
                        entityCountRatios.add(obj);

                        HydExcelIndustryEntityCountRatioHistory historyObj = MapUtil.map2Object(HydExcelIndustryEntityCountRatioHistory.class, m);
                        historyObj.setBatchNo(batchNo);
                        entityCountRatioHistories.add(historyObj);
                    });
                    break;
                case "总增速和增加值（年度趋势）表":
                    list.forEach(m -> {
                        HydExcelIndustryGrowthValueTrend obj = MapUtil.map2Object(HydExcelIndustryGrowthValueTrend.class, m);
                        obj.setBatchNo(batchNo);
                        growthValueTrends.add(obj);

                        HydExcelIndustryGrowthValueTrendHistory historyObj = MapUtil.map2Object(HydExcelIndustryGrowthValueTrendHistory.class, m);
                        historyObj.setBatchNo(batchNo);
                        growthValueTrendHistories.add(historyObj);
                    });
                    break;
                case "居民体育培训项目参与率表":
                    list.forEach(m -> {
                        HydExcelIndustryTrainingParticipationRate obj = MapUtil.map2Object(HydExcelIndustryTrainingParticipationRate.class, m);
                        obj.setBatchNo(batchNo);
                        trainingParticipationRates.add(obj);

                        HydExcelIndustryTrainingParticipationRateHistory historyObj = MapUtil.map2Object(HydExcelIndustryTrainingParticipationRateHistory.class, m);
                        historyObj.setBatchNo(batchNo);
                        trainingParticipationRateHistories.add(historyObj);
                    });
                    break;
                case "居民体育用品购买率表":
                    list.forEach(m -> {
                        HydExcelIndustryGoodsPurchaseRate obj = MapUtil.map2Object(HydExcelIndustryGoodsPurchaseRate.class, m);
                        obj.setBatchNo(batchNo);
                        goodsPurchaseRates.add(obj);

                        HydExcelIndustryGoodsPurchaseRateHistory historyObj = MapUtil.map2Object(HydExcelIndustryGoodsPurchaseRateHistory.class, m);
                        historyObj.setBatchNo(batchNo);
                        goodsPurchaseRateHistories.add(historyObj);
                    });
                    break;
                case "从业人员数量（分类统计）表":
                    list.forEach(m -> {
                        HydExcelIndustryEmployeeCount obj = MapUtil.map2Object(HydExcelIndustryEmployeeCount.class, m);
                        obj.setBatchNo(batchNo);
                        employeeCounts.add(obj);

                        HydExcelIndustryEmployeeCountHistory historyObj = MapUtil.map2Object(HydExcelIndustryEmployeeCountHistory.class, m);
                        historyObj.setBatchNo(batchNo);
                        employeeCountHistories.add(historyObj);
                    });
                    break;
            }
        });
        // 批量保存
        Instant startTotal = Instant.now();
        log.info("开始执行批量保存操作，总共有{}个数据集需要保存", 14);

        // 1. 保存核心指标
        Instant start1 = Instant.now();
        industryCoreIndicatorsRepo.deleteAll();
        industryCoreIndicatorsRepo.saveAll(coreIndicators);
        long time1 = Duration.between(start1, Instant.now()).toMillis();
        log.info("1. 核心指标数据保存完成，条数：{}，耗时：{}ms",
                coreIndicators.size(), time1);

        // 2. 保存核心指标历史
        Instant start2 = Instant.now();
        //industryCoreIndicatorsHistoryRepo.deleteAll();
        industryCoreIndicatorsHistoryRepo.saveAll(coreIndicatorsHistories);
        long time2 = Duration.between(start2, Instant.now()).toMillis();
        log.info("2. 核心指标历史数据保存完成，条数：{}，耗时：{}ms",
                coreIndicatorsHistories.size(), time2);

        // 3. 保存行业规模趋势
        Instant start3 = Instant.now();
        industryScaleTrendRepo.deleteAll();
        industryScaleTrendRepo.saveAll(scaleTrends);
        long time3 = Duration.between(start3, Instant.now()).toMillis();
        log.info("3. 行业规模趋势数据保存完成，条数：{}，耗时：{}ms",
                scaleTrends.size(), time3);

        // 4. 保存行业规模趋势历史
        Instant start4 = Instant.now();
        //industryScaleTrendHistoryRepo.deleteAll();
        industryScaleTrendHistoryRepo.saveAll(scaleTrendHistories);
        long time4 = Duration.between(start4, Instant.now()).toMillis();
        log.info("4. 行业规模趋势历史数据保存完成，条数：{}，耗时：{}ms",
                scaleTrendHistories.size(), time4);

        // 5. 保存实体数量占比
        Instant start5 = Instant.now();
        industryEntityCountRatioRepo.deleteAll();
        industryEntityCountRatioRepo.saveAll(entityCountRatios);
        long time5 = Duration.between(start5, Instant.now()).toMillis();
        log.info("5. 实体数量占比数据保存完成，条数：{}，耗时：{}ms",
                entityCountRatios.size(), time5);

        // 6. 保存实体数量占比历史
        Instant start6 = Instant.now();
        //industryEntityCountRatioHistoryRepo.deleteAll();
        industryEntityCountRatioHistoryRepo.saveAll(entityCountRatioHistories);
        long time6 = Duration.between(start6, Instant.now()).toMillis();
        log.info("6. 实体数量占比历史数据保存完成，条数：{}，耗时：{}ms",
                entityCountRatioHistories.size(), time6);

        // 7. 保存增长值趋势
        Instant start7 = Instant.now();
        industryGrowthValueTrendRepo.deleteAll();
        industryGrowthValueTrendRepo.saveAll(growthValueTrends);
        long time7 = Duration.between(start7, Instant.now()).toMillis();
        log.info("7. 增长值趋势数据保存完成，条数：{}，耗时：{}ms",
                growthValueTrends.size(), time7);

        // 8. 保存增长值趋势历史
        Instant start8 = Instant.now();
        //industryGrowthValueTrendHistoryRepo.deleteAll();
        industryGrowthValueTrendHistoryRepo.saveAll(growthValueTrendHistories);
        long time8 = Duration.between(start8, Instant.now()).toMillis();
        log.info("8. 增长值趋势历史数据保存完成，条数：{}，耗时：{}ms",
                growthValueTrendHistories.size(), time8);

        // 9. 保存培训参与率
        Instant start9 = Instant.now();
        industryTrainingParticipationRateRepo.deleteAll();
        industryTrainingParticipationRateRepo.saveAll(trainingParticipationRates);
        long time9 = Duration.between(start9, Instant.now()).toMillis();
        log.info("9. 培训参与率数据保存完成，条数：{}，耗时：{}ms",
                trainingParticipationRates.size(), time9);

        // 10. 保存培训参与率历史
        Instant start10 = Instant.now();
        //industryTrainingParticipationRateHistoryRepo.deleteAll();
        industryTrainingParticipationRateHistoryRepo.saveAll(trainingParticipationRateHistories);
        long time10 = Duration.between(start10, Instant.now()).toMillis();
        log.info("10. 培训参与率历史数据保存完成，条数：{}，耗时：{}ms",
                trainingParticipationRateHistories.size(), time10);

        // 11. 保存商品采购率
        Instant start11 = Instant.now();
        industryGoodsPurchaseRateRepo.deleteAll();
        industryGoodsPurchaseRateRepo.saveAll(goodsPurchaseRates);
        long time11 = Duration.between(start11, Instant.now()).toMillis();
        log.info("11. 商品采购率数据保存完成，条数：{}，耗时：{}ms",
                goodsPurchaseRates.size(), time11);

        // 12. 保存商品采购率历史
        Instant start12 = Instant.now();
        //industryGoodsPurchaseRateHistoryRepo.deleteAll();
        industryGoodsPurchaseRateHistoryRepo.saveAll(goodsPurchaseRateHistories);
        long time12 = Duration.between(start12, Instant.now()).toMillis();
        log.info("12. 商品采购率历史数据保存完成，条数：{}，耗时：{}ms",
                goodsPurchaseRateHistories.size(), time12);

        // 13. 保存从业人员数量
        Instant start13 = Instant.now();
        industryEmployeeCountRepo.deleteAll();
        industryEmployeeCountRepo.saveAll(employeeCounts);
        long time13 = Duration.between(start13, Instant.now()).toMillis();
        log.info("13. 从业人员数量数据保存完成，条数：{}，耗时：{}ms",
                employeeCounts.size(), time13);

        // 14. 保存从业人员数量历史
        Instant start14 = Instant.now();
        //industryEmployeeCountHistoryRepo.deleteAll();
        industryEmployeeCountHistoryRepo.saveAll(employeeCountHistories);
        long time14 = Duration.between(start14, Instant.now()).toMillis();
        log.info("14. 从业人员数量历史数据保存完成，条数：{}，耗时：{}ms",
                employeeCountHistories.size(), time14);

        // 总耗时统计
        long totalTime = Duration.between(startTotal, Instant.now()).toMillis();
        log.info("所有批量保存操作完成，总耗时：{}ms，平均每条操作耗时：{}ms",
                totalTime, totalTime / 14);
        return true;
    }

    /**
     * 总览
     *
     * @return
     */
    @Override
    public HydExcelIndustryCoreIndicators overview(String year) {
        return industryCoreIndicatorsRepo.overview(year);
    }

    /**
     * 体育产业总规模
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> industryScaleTrendStat(String year) {
        Integer fromYear = Integer.parseInt(year) - 6;
        return industryScaleTrendRepo.stat(fromYear + "", year);
    }

    /**
     * 体育产业市场主体数量
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> industryEntityCountRatioStat(String year) {
        return industryEntityCountRatioRepo.stat(year);
    }

    /**
     * 体育产业总增速和增加值
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> industryGrowthValueTrendStat(String year) {
        Integer fromYear = Integer.parseInt(year) - 6;
        return industryGrowthValueTrendRepo.stat(fromYear + "", year);
    }

    /**
     * 居民体育用品购买率
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> industryGoodsPurchaseRateStat(String year) {
        return industryGoodsPurchaseRateRepo.stat(year);
    }

    /**
     * 体育产业从业人员数量
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> industryEmployeeCountStat(String year) {
        return industryEmployeeCountRepo.stat(year);
    }

    /**
     * 居民体育培训项目参与率
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> industryTrainingParticipationRateStat(String year) {
        return industryTrainingParticipationRateRepo.stat(year);
    }
}
