package cn.wuhan.hyd.sports.service;

import java.util.List;
import java.util.Map;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
public interface IHydInstructorService {

    boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData);

    /**
     * 指导项目top15
     */
    List<Map<String, Object>> serviceProjectTop15();

    /**
     * 性别统计
     */
    List<Map<String, Object>> genderStat();

    /**
     * 级别统计
     */
    List<Map<String, Object>> levelStat();

    /**
     * 各区指导人员统计
     */
    List<Map<String, Object>> regionInstructorStat();

    /**
     * 年龄统计
     */
    List<Map<String, Object>> ageIntervalStat();

    /**
     * 人数增长统计
     */
    List<Map<String, Object>> ageGrowthStat();
}
