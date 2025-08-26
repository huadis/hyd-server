package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.MapUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.repository.*;
import cn.wuhan.hyd.sports.service.IHydExcelInstructorService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/**
 * 功能说明： 社会体育指导员 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@Service
public class HydExcelInstructorServiceImpl implements IHydExcelInstructorService {

    @Resource
    private HydExcelInstructorInfoRepo instructorInfoRepo;
    @Resource
    private HydExcelInstructorInfoHistoryRepo infoHistoryRepo;
    @Resource
    private HydExcelInstructorAgeStatsRepo instructorAgeStatsRepo;
    @Resource
    private HydExcelInstructorAgeStatsHistoryRepo ageStatsHistoryRepo;
    @Resource
    private HydExcelInstructorAgeGrowthRepo instructorAgeGrowthRepo;
    @Resource
    private HydExcelInstructorAgeGrowthHistoryRepo ageGrowthHistoryRepo;
    @Resource
    private Executor executor;

    @Override
    public PageResult<HydExcelInstructorInfo> queryAllInstructorInfo(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelInstructorInfo> pageResult = instructorInfoRepo.findAll(pageable);
        PageResult<HydExcelInstructorInfo> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydExcelInstructorInfo> queryAllInstructorInfo() {
        return instructorInfoRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelInstructorInfo save(HydExcelInstructorInfo instructorInfo) {
        return instructorInfoRepo.save(instructorInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteInstructorInfoById(Long id) {
        instructorInfoRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelInstructorInfo updateInstructorInfo(HydExcelInstructorInfo instructorInfo) {
        if (instructorInfo.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findInstructorInfoById(instructorInfo.getId());
        return instructorInfoRepo.save(instructorInfo);
    }

    @Override
    public HydExcelInstructorInfo findInstructorInfoById(Long id) {
        return instructorInfoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("社会体育指导员汇总信息，ID：" + id));
    }

    @Override
    public PageResult<HydExcelInstructorAgeStats> queryAllInstructorAgeStats(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelInstructorAgeStats> pageResult = instructorAgeStatsRepo.findAll(pageable);
        PageResult<HydExcelInstructorAgeStats> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydExcelInstructorAgeStats> queryAllInstructorAgeStats() {
        return instructorAgeStatsRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelInstructorAgeStats save(HydExcelInstructorAgeStats instructorAgeStats) {
        return instructorAgeStatsRepo.save(instructorAgeStats);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteInstructorAgeStatsById(Long id) {
        instructorAgeStatsRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelInstructorAgeStats updateInstructorAgeStats(HydExcelInstructorAgeStats instructorAgeStats) {
        if (instructorAgeStats.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findInstructorAgeStatsById(instructorAgeStats.getId());
        return instructorAgeStatsRepo.save(instructorAgeStats);
    }

    @Override
    public HydExcelInstructorAgeStats findInstructorAgeStatsById(Long id) {
        return instructorAgeStatsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("体育指导员 - 年龄统计明细表，ID：" + id));
    }

    @Override
    public PageResult<HydExcelInstructorAgeGrowth> queryAllInstructorAgeGrowth(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelInstructorAgeGrowth> pageResult = instructorAgeGrowthRepo.findAll(pageable);
        PageResult<HydExcelInstructorAgeGrowth> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydExcelInstructorAgeGrowth> queryAllInstructorAgeGrowth() {
        return instructorAgeGrowthRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelInstructorAgeGrowth save(HydExcelInstructorAgeGrowth instructorAgeGrowth) {
        return instructorAgeGrowthRepo.save(instructorAgeGrowth);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteInstructorAgeGrowthById(Long id) {
        instructorAgeGrowthRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelInstructorAgeGrowth updateInstructorAgeGrowth(HydExcelInstructorAgeGrowth instructorAgeGrowth) {
        if (instructorAgeGrowth.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findInstructorAgeGrowthById(instructorAgeGrowth.getId());
        return instructorAgeGrowthRepo.save(instructorAgeGrowth);
    }

    @Override
    public HydExcelInstructorAgeGrowth findInstructorAgeGrowthById(Long id) {
        return instructorAgeGrowthRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("体育指导员 - 人数增长统计明细表，ID：" + id));
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData) {
        try {
            // 2. 多线程并行处理不同sheet
            CompletableFuture<Void> summaryFuture = CompletableFuture.runAsync(() ->
                            processSheet(sheetMapData.get("汇总"),
                                    HydExcelInstructorInfo.class,
                                    HydExcelInstructorInfoHistory.class,
                                    instructorInfoRepo,
                                    infoHistoryRepo),
                    executor
            );

            CompletableFuture<Void> ageStatsFuture = CompletableFuture.runAsync(() ->
                            processSheet(sheetMapData.get("年龄统计明细表"),
                                    HydExcelInstructorAgeStats.class,
                                    HydExcelInstructorAgeStatsHistory.class,
                                    instructorAgeStatsRepo,
                                    ageStatsHistoryRepo),
                    executor
            );

            CompletableFuture<Void> growthFuture = CompletableFuture.runAsync(() ->
                            processSheet(sheetMapData.get("人数增长统计明细表"),
                                    HydExcelInstructorAgeGrowth.class,
                                    HydExcelInstructorAgeGrowthHistory.class,
                                    instructorAgeGrowthRepo,
                                    ageGrowthHistoryRepo),
                    executor
            );

            // 3. 等待所有线程完成
            CompletableFuture.allOf(summaryFuture, ageStatsFuture, growthFuture).get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            // 异常时事务回滚
            Thread.currentThread().interrupt();
            throw new RuntimeException("Excel导入失败", e);
        }
    }

    /**
     * 通用处理单个sheet的方法：转换+分批保存
     */
    private <T, H> void processSheet(List<Map<String, Object>> dataList,
                                     Class<T> mainClazz,
                                     Class<H> historyClazz,
                                     JpaRepository<T, Long> mainRepo,
                                     JpaRepository<H, Long> historyRepo) {
        if (dataList == null || dataList.isEmpty()) {
            return;
        }

        int batchSize = 500; // 每批插入数量（根据数据库性能调整）
        int totalSize = dataList.size();
        List<T> mainBatch = new ArrayList<>(batchSize);
        List<H> historyBatch = new ArrayList<>(batchSize);

        for (int i = 0; i < totalSize; i++) {
            Map<String, Object> dataMap = dataList.get(i);
            T mainEntity = MapUtil.map2Object(mainClazz, dataMap);
            H historyEntity = MapUtil.map2Object(historyClazz, dataMap);

            mainBatch.add(mainEntity);
            historyBatch.add(historyEntity);

            if ((i + 1) % batchSize == 0 || i == totalSize - 1) {
                mainRepo.saveAll(mainBatch);
                historyRepo.saveAll(historyBatch);
                // 清空批次列表释放内存
                mainBatch.clear();
                historyBatch.clear();
                // 手动刷新（可选，避免内存堆积）
                // entityManager.flush();
                // entityManager.clear();
            }
        }
    }

    /**
     * 指导项目top15
     */
    @Override
    public List<Map<String, Object>> serviceProjectTop15() {
        return instructorInfoRepo.serviceProjectTop15();
    }

    /**
     * 性别统计
     */
    @Override
    public List<Map<String, Object>> genderStat() {
        return instructorInfoRepo.genderStat();
    }

    /**
     * 级别统计
     */
    @Override
    public List<Map<String, Object>> levelStat() {
        return instructorInfoRepo.levelStat();
    }

    /**
     * 各区指导人员统计
     */
    @Override
    public List<Map<String, Object>> regionInstructorStat() {
        return instructorInfoRepo.regionInstructorStat();
    }

    /**
     * 年龄统计
     */
    @Override
    public List<Map<String, Object>> ageIntervalStat() {
        return instructorAgeStatsRepo.ageIntervalStat();
    }

    /**
     * 人数增长统计
     */
    @Override
    public List<Map<String, Object>> ageGrowthStat() {
        return instructorAgeGrowthRepo.ageGrowthStat();
    }

    /**
     * 人数增长统计
     */
    @Override
    public Map<String, Object> overview() {
        Long totalCount = instructorInfoRepo.countAll();
        Long newCount = instructorInfoRepo.newCount();
        List<Map<String, Object>> list = instructorInfoRepo.genderStat();
        Map<String, Object> result = new HashMap<>();
        result.put("total", totalCount);
        result.put("newCount", newCount);
        int man = 0;
        int woman = 0;
        for (Map<String, Object> map : list) {
            if (map.get("gender") != null) {
                String gender = MapUtils.getString(map, "gender");
                if (StringUtils.isNotBlank(gender) && StringUtils.equals(gender, "男")) {
                    man = MapUtils.getInteger(map, "personCount", 0);
                } else if (StringUtils.isNotBlank(gender) && StringUtils.equals(gender, "女")) {
                    woman = MapUtils.getInteger(map, "personCount", 0);
                }
            }
        }
        int total = man + woman;
        // 计算百分比并四舍五入为整数
        int manPercent = (int) Math.round(((double) man / total) * 100);
        int womanPercent = (int) Math.round(((double) woman / total) * 100);
        result.put("男:女", manPercent + ":" + womanPercent);
        return result;
    }


}
