package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.MapUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydExcelInstructorAgeGrowth;
import cn.wuhan.hyd.sports.domain.HydExcelInstructorAgeStats;
import cn.wuhan.hyd.sports.domain.HydExcelInstructorInfo;
import cn.wuhan.hyd.sports.repository.HydExcelInstructorAgeGrowthRepo;
import cn.wuhan.hyd.sports.repository.HydExcelInstructorAgeStatsRepo;
import cn.wuhan.hyd.sports.repository.HydExcelInstructorInfoRepo;
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
    private HydExcelInstructorInfoRepo hydExcelInstructorInfoRepo;
    @Resource
    private HydExcelInstructorAgeStatsRepo hydExcelInstructorAgeStatsRepo;
    @Resource
    private HydExcelInstructorAgeGrowthRepo hydExcelInstructorAgeGrowthRepo;

    @Override
    public PageResult<HydExcelInstructorInfo> queryAllInstructorInfo(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelInstructorInfo> pageResult = hydExcelInstructorInfoRepo.findAll(pageable);
        PageResult<HydExcelInstructorInfo> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydExcelInstructorInfo> queryAllInstructorInfo() {
        return hydExcelInstructorInfoRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelInstructorInfo save(HydExcelInstructorInfo instructorInfo) {
        return hydExcelInstructorInfoRepo.save(instructorInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteInstructorInfoById(Long id) {
        hydExcelInstructorInfoRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelInstructorInfo updateInstructorInfo(HydExcelInstructorInfo instructorInfo) {
        if (instructorInfo.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findInstructorInfoById(instructorInfo.getId());
        return hydExcelInstructorInfoRepo.save(instructorInfo);
    }

    @Override
    public HydExcelInstructorInfo findInstructorInfoById(Long id) {
        return hydExcelInstructorInfoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("社会体育指导员汇总信息，ID：" + id));
    }

    @Override
    public PageResult<HydExcelInstructorAgeStats> queryAllInstructorAgeStats(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelInstructorAgeStats> pageResult = hydExcelInstructorAgeStatsRepo.findAll(pageable);
        PageResult<HydExcelInstructorAgeStats> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydExcelInstructorAgeStats> queryAllInstructorAgeStats() {
        return hydExcelInstructorAgeStatsRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelInstructorAgeStats save(HydExcelInstructorAgeStats instructorAgeStats) {
        return hydExcelInstructorAgeStatsRepo.save(instructorAgeStats);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteInstructorAgeStatsById(Long id) {
        hydExcelInstructorAgeStatsRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelInstructorAgeStats updateInstructorAgeStats(HydExcelInstructorAgeStats instructorAgeStats) {
        if (instructorAgeStats.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findInstructorAgeStatsById(instructorAgeStats.getId());
        return hydExcelInstructorAgeStatsRepo.save(instructorAgeStats);
    }

    @Override
    public HydExcelInstructorAgeStats findInstructorAgeStatsById(Long id) {
        return hydExcelInstructorAgeStatsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("体育指导员 - 年龄统计明细表，ID：" + id));
    }

    @Override
    public PageResult<HydExcelInstructorAgeGrowth> queryAllInstructorAgeGrowth(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelInstructorAgeGrowth> pageResult = hydExcelInstructorAgeGrowthRepo.findAll(pageable);
        PageResult<HydExcelInstructorAgeGrowth> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydExcelInstructorAgeGrowth> queryAllInstructorAgeGrowth() {
        return hydExcelInstructorAgeGrowthRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelInstructorAgeGrowth save(HydExcelInstructorAgeGrowth instructorAgeGrowth) {
        return hydExcelInstructorAgeGrowthRepo.save(instructorAgeGrowth);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteInstructorAgeGrowthById(Long id) {
        hydExcelInstructorAgeGrowthRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydExcelInstructorAgeGrowth updateInstructorAgeGrowth(HydExcelInstructorAgeGrowth instructorAgeGrowth) {
        if (instructorAgeGrowth.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findInstructorAgeGrowthById(instructorAgeGrowth.getId());
        return hydExcelInstructorAgeGrowthRepo.save(instructorAgeGrowth);
    }

    @Override
    public HydExcelInstructorAgeGrowth findInstructorAgeGrowthById(Long id) {
        return hydExcelInstructorAgeGrowthRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("体育指导员 - 人数增长统计明细表，ID：" + id));
    }

    public boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData) {
        List<HydExcelInstructorInfo> instructorInfos = new ArrayList<>();
        List<HydExcelInstructorAgeStats> instructorAgeStats = new ArrayList<>();
        List<HydExcelInstructorAgeGrowth> instructorAgeGrowths = new ArrayList<>();
        sheetMapData.forEach((key, list) -> {
            Map<String, Object> rowData = new HashMap<>();
            switch (key) {
                case "汇总":
                    list.forEach(m -> {
                        instructorInfos.add(MapUtil.map2Object(HydExcelInstructorInfo.class, m));
                    });
                    break;
                case "年龄统计明细表":
                    list.forEach(m -> {
                        instructorAgeStats.add(MapUtil.map2Object(HydExcelInstructorAgeStats.class, m));
                    });
                    break;
                case "人数增长统计明细表":
                    list.forEach(m -> {
                        instructorAgeGrowths.add(MapUtil.map2Object(HydExcelInstructorAgeGrowth.class, m));
                    });
                    break;
            }
        });
        // 批量保存
        hydExcelInstructorInfoRepo.saveAll(instructorInfos);
        hydExcelInstructorAgeStatsRepo.saveAll(instructorAgeStats);
        hydExcelInstructorAgeGrowthRepo.saveAll(instructorAgeGrowths);
        return true;
    }

    /**
     * 指导项目top15
     */
    @Override
    public List<Map<String, Object>> serviceProjectTop15() {
        return hydExcelInstructorInfoRepo.serviceProjectTop15();
    }

    /**
     * 性别统计
     */
    @Override
    public List<Map<String, Object>> genderStat() {
        return hydExcelInstructorInfoRepo.genderStat();
    }

    /**
     * 级别统计
     */
    @Override
    public List<Map<String, Object>> levelStat() {
        return hydExcelInstructorInfoRepo.levelStat();
    }

    /**
     * 各区指导人员统计
     */
    @Override
    public List<Map<String, Object>> regionInstructorStat() {
        return hydExcelInstructorInfoRepo.regionInstructorStat();
    }

    /**
     * 年龄统计
     */
    @Override
    public List<Map<String, Object>> ageIntervalStat() {
        return hydExcelInstructorAgeStatsRepo.ageIntervalStat();
    }

    /**
     * 人数增长统计
     */
    @Override
    public List<Map<String, Object>> ageGrowthStat() {
        return hydExcelInstructorAgeGrowthRepo.ageGrowthStat();
    }


}
