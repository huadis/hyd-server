package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydTenant;
import cn.wuhan.hyd.sports.repository.HydTenantRepository;
import cn.wuhan.hyd.sports.service.IHydTenantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明：组织机构表服务实现类 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月14日 <br>
 */
@Service
public class HydTenantServiceImpl implements IHydTenantService {

    @Resource
    private HydTenantRepository hydTenantRepository;

    @Override
    public PageResult<HydTenant> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydTenant> queryAll() {
        return hydTenantRepository.findAll();
    }

    @Override
    public HydTenant findById(String id) {
        return hydTenantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("租户不存在，ID: " + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydTenant save(HydTenant hydTenant) {
        return hydTenantRepository.save(hydTenant);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydTenant> tenants) {
        List<HydTenant> saved = hydTenantRepository.saveAll(tenants);
        return saved.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydTenant update(HydTenant hydTenant) {
        // 验证租户是否存在
        findById(hydTenant.getId());
        return hydTenantRepository.save(hydTenant);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        hydTenantRepository.deleteById(id);
    }

    @Override
    public List<HydTenant> findByProvince(String province) {
        return hydTenantRepository.findByProvince(province);
    }

    @Override
    public List<HydTenant> findByTenantNameLike(String tenantName) {
        return hydTenantRepository.findByTenantNameLike("%" + tenantName + "%");
    }
}
