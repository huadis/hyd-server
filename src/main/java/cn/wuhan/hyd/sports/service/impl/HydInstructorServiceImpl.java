package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.MapUtil;
import cn.wuhan.hyd.sports.domain.HydInstructorAgeGrowth;
import cn.wuhan.hyd.sports.domain.HydInstructorAgeStats;
import cn.wuhan.hyd.sports.domain.HydInstructorInfo;
import cn.wuhan.hyd.sports.repository.HydInstructorAgeGrowthRepository;
import cn.wuhan.hyd.sports.repository.HydInstructorAgeStatsRepository;
import cn.wuhan.hyd.sports.repository.HydInstructorInfoRepository;
import cn.wuhan.hyd.sports.service.IHydInstructorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 功能说明： 社会体育指导员 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@Service
public class HydInstructorServiceImpl implements IHydInstructorService {

    @Resource
    private HydInstructorInfoRepository hydInstructorInfoRepository;
    @Resource
    private HydInstructorAgeStatsRepository hydInstructorAgeStatsRepository;
    @Resource
    private HydInstructorAgeGrowthRepository hydInstructorAgeGrowthRepository;

    public boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData) {
        List<HydInstructorInfo> instructorInfos = new ArrayList<>();
        List<HydInstructorAgeStats> instructorAgeStats = new ArrayList<>();
        List<HydInstructorAgeGrowth> instructorAgeGrowths = new ArrayList<>();
        sheetMapData.forEach((key, list) -> {
            Map<String, Object> rowData = new HashMap<>();
            switch (key) {
                case "汇总":
                    list.forEach(m -> {
                        instructorInfos.add(MapUtil.map2Object(HydInstructorInfo.class, m));
                    });
                    break;
                case "年龄统计明细表":
                    list.forEach(m -> {
                        instructorAgeStats.add(MapUtil.map2Object(HydInstructorAgeStats.class, m));
                    });
                    break;
                case "人数增长统计明细表":
                    list.forEach(m -> {
                        instructorAgeGrowths.add(MapUtil.map2Object(HydInstructorAgeGrowth.class, m));
                    });
                    break;
            }
        });
        // 批量保存
        hydInstructorInfoRepository.saveAll(instructorInfos);
        hydInstructorAgeStatsRepository.saveAll(instructorAgeStats);
        hydInstructorAgeGrowthRepository.saveAll(instructorAgeGrowths);
        return true;
    }

    /**
     * 指导项目top15
     */
    @Override
    public List<Map<String, Object>> serviceProjectTop15() {
        return hydInstructorInfoRepository.serviceProjectTop15();
    }

    /**
     * 性别统计
     */
    @Override
    public List<Map<String, Object>> genderStat() {
        return hydInstructorInfoRepository.genderStat();
    }

    /**
     * 级别统计
     */
    @Override
    public List<Map<String, Object>> levelStat() {
        return hydInstructorInfoRepository.levelStat();
    }

    /**
     * 各区指导人员统计
     */
    @Override
    public List<Map<String, Object>> regionInstructorStat() {
        return hydInstructorInfoRepository.regionInstructorStat();
    }

    /**
     * 年龄统计
     */
    @Override
    public List<Map<String, Object>> ageIntervalStat() {
        return hydInstructorAgeStatsRepository.ageIntervalStat();
    }

    /**
     * 人数增长统计
     */
    @Override
    public List<Map<String, Object>> ageGrowthStat() {
        return hydInstructorAgeGrowthRepository.ageGrowthStat();
    }


}
