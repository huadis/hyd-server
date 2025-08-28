package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydExcelSportsOrganization;

import java.util.List;
import java.util.Map;

/**
 * 体育组织服务接口
 * 功能说明：定义体育组织相关的业务逻辑方法 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月28日 <br>
 */
public interface IHydExcelSportsOrganizationService {

    /**
     * 分页查询体育组织
     * @param page 页码（从0开始）
     * @param size 每页条数
     * @return 分页结果
     */
    PageResult<HydExcelSportsOrganization> queryAll(int page, int size);

    /**
     * 查询所有体育组织
     * @return 体育组织列表
     */
    List<HydExcelSportsOrganization> queryAll();

    /**
     * 根据ID查询体育组织详情
     * @param id 主键ID
     * @return 体育组织详情
     */
    HydExcelSportsOrganization findById(Long id);

    /**
     * 新增体育组织
     * @param sportsOrganization 体育组织信息
     * @return 保存后的体育组织信息
     */
    HydExcelSportsOrganization save(HydExcelSportsOrganization sportsOrganization);

    /**
     * 批量新增体育组织
     * @param sportsOrganizations 体育组织列表
     * @return 保存后的体育组织列表
     */
    List<HydExcelSportsOrganization> saveBatch(List<HydExcelSportsOrganization> sportsOrganizations);

    /**
     * 更新体育组织
     * @param sportsOrganization 体育组织信息
     * @return 更新后的体育组织信息
     */
    HydExcelSportsOrganization update(HydExcelSportsOrganization sportsOrganization);

    /**
     * 根据ID删除体育组织
     * @param id 主键ID
     */
    void deleteById(Long id);

    boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData);
}
