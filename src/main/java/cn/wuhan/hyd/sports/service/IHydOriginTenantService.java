package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydOriginTenantHistory;
import cn.wuhan.hyd.sports.req.HydOriginTenantReq;

import java.util.List;

/**
 * 功能说明：组织机构表服务接口 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月14日 <br>
 */
public interface IHydOriginTenantService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 分页结果
     */
    PageResult<HydOriginTenantHistory> queryAll(int page, int size);

    /**
     * 查询全部
     * @return 租户列表
     */
    List<HydOriginTenantHistory> queryAll();

    /**
     * 根据ID查询
     * @param id 主键ID
     * @return 租户实体
     */
    HydOriginTenantHistory findById(String id);

    /**
     * 新增租户
     * @param hydOriginTenant 租户实体
     * @return 保存后的实体
     */
    HydOriginTenantHistory save(HydOriginTenantHistory hydOriginTenant);

    /**
     * 批量新增
     * @param tenants 租户列表
     * @return 新增数量
     */
    int batchSave(List<HydOriginTenantReq> tenants);

    /**
     * 更新租户
     * @param hydOriginTenant 租户实体
     * @return 更新后的实体
     */
    HydOriginTenantHistory update(HydOriginTenantHistory hydOriginTenant);

    /**
     * 根据ID删除
     * @param id 主键ID
     */
    void deleteById(String id);
}
