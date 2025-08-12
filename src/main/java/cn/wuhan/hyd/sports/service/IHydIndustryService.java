package cn.wuhan.hyd.sports.service;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 体育产业 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
public interface IHydIndustryService {

    boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData);
}
