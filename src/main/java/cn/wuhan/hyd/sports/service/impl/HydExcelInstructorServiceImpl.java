package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.MapUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.repository.*;
import cn.wuhan.hyd.sports.service.IHydExcelInstructorService;
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

    public boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData) {
        List<HydExcelInstructorInfo> infos = new ArrayList<>();
        List<HydExcelInstructorAgeStats> ageStats = new ArrayList<>();
        List<HydExcelInstructorAgeGrowth> ageGrowths = new ArrayList<>();

        List<HydExcelInstructorInfoHistory> infoHistories = new ArrayList<>();
        List<HydExcelInstructorAgeStatsHistory> ageStatsHistories = new ArrayList<>();
        List<HydExcelInstructorAgeGrowthHistory> ageGrowthHistories = new ArrayList<>();
        sheetMapData.forEach((key, list) -> {
            Map<String, Object> rowData = new HashMap<>();
            switch (key) {
                case "汇总":
                    list.forEach(m -> {
                        infos.add(MapUtil.map2Object(HydExcelInstructorInfo.class, m));
                        infoHistories.add(MapUtil.map2Object(HydExcelInstructorInfoHistory.class, m));
                    });
                    break;
                case "年龄统计明细表":
                    list.forEach(m -> {
                        ageStats.add(MapUtil.map2Object(HydExcelInstructorAgeStats.class, m));
                        ageStatsHistories.add(MapUtil.map2Object(HydExcelInstructorAgeStatsHistory.class, m));
                    });
                    break;
                case "人数增长统计明细表":
                    list.forEach(m -> {
                        ageGrowths.add(MapUtil.map2Object(HydExcelInstructorAgeGrowth.class, m));
                        ageGrowthHistories.add(MapUtil.map2Object(HydExcelInstructorAgeGrowthHistory.class, m));
                    });
                    break;
            }
        });
        // 批量保存
        instructorInfoRepo.saveAll(infos);
        infoHistoryRepo.saveAll(infoHistories);
        instructorAgeStatsRepo.saveAll(ageStats);
        ageStatsHistoryRepo.saveAll(ageStatsHistories);
        instructorAgeGrowthRepo.saveAll(ageGrowths);
        ageGrowthHistoryRepo.saveAll(ageGrowthHistories);
        return true;
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


}
