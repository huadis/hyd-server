package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydTenant;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 功能说明：组织机构表服务接口 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月14日 <br>
 */
public interface IHydTenantService {

    /**
     * 分页查询
     * @param pageable 分页参数
     * @return 分页结果
     */
    PageResult<HydTenant> queryAll(Pageable pageable);

    /**
     * 查询全部
     * @return 租户列表
     */
    List<HydTenant> queryAll();

    /**
     * 根据ID查询
     * @param id 主键ID
     * @return 租户实体
     */
    HydTenant findById(String id);

    /**
     * 新增租户
     * @param hydTenant 租户实体
     * @return 保存后的实体
     */
    HydTenant save(HydTenant hydTenant);

    /**
     * 批量新增
     * @param tenants 租户列表
     * @return 新增数量
     */
    int batchSave(List<HydTenant> tenants);

    /**
     * 更新租户
     * @param hydTenant 租户实体
     * @return 更新后的实体
     */
    HydTenant update(HydTenant hydTenant);

    /**
     * 根据ID删除
     * @param id 主键ID
     */
    void deleteById(String id);

    /**
     * 根据省份编码查询
     * @param province 省份编码
     * @return 租户列表
     */
    List<HydTenant> findByProvince(String province);

    /**
     * 根据名称模糊查询
     * @param tenantName 租户名称
     * @return 租户列表
     */
    List<HydTenant> findByTenantNameLike(String tenantName);
}
