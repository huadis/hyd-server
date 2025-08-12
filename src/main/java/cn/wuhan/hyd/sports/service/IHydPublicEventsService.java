package cn.wuhan.hyd.sports.service;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 大众赛事-体育赛事信息表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
public interface IHydPublicEventsService {

    boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData);

    Map<String, Object> overview();

    /**
     * 各月办赛数据
     */
    List<Map<String, Object>> latestMonthStat();

    List<Map<String, Object>> sportItemTop5();

}
