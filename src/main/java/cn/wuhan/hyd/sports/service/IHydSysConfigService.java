package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydSysConfig;

import java.util.List;

/**
 * 功能说明： 系统配置 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年09月13日 <br>
 */
public interface IHydSysConfigService {

    List<HydSysConfig> queryAll();

    PageResult<HydSysConfig> queryAll(int page, int size);

    HydSysConfig findById(Long id);

    HydSysConfig findByKey(String key);

    void deleteById(Long id);

    HydSysConfig update(HydSysConfig config);

    boolean notRefresh(String key);
}
