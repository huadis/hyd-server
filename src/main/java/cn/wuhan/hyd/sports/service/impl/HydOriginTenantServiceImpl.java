package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydOriginTenantHistory;
import cn.wuhan.hyd.sports.repository.HydOriginTenantHistoryRepo;
import cn.wuhan.hyd.sports.req.HydOriginTenantReq;
import cn.wuhan.hyd.sports.service.IHydOriginTenantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class HydOriginTenantServiceImpl extends HydBaseServiceImpl implements IHydOriginTenantService {

    private final Logger logger = LoggerFactory.getLogger(HydOriginTenantServiceImpl.class);
    @Resource
    private HydOriginTenantHistoryRepo tenantHistoryRepo;

    @Override
    public PageResult<HydOriginTenantHistory> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydOriginTenantHistory> pageResult = tenantHistoryRepo.findAll(pageable);
        PageResult<HydOriginTenantHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydOriginTenantHistory> queryAll() {
        return tenantHistoryRepo.findAll();
    }

    @Override
    public HydOriginTenantHistory findById(String id) {
        return tenantHistoryRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("租户不存在，ID: " + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginTenantHistory save(HydOriginTenantHistory hydOriginTenant) {
        return tenantHistoryRepo.save(hydOriginTenant);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginTenantHistory update(HydOriginTenantHistory tenant) {
        if (tenant.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 验证租户是否存在
        findById(tenant.getId());
        return tenantHistoryRepo.save(tenant);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        tenantHistoryRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydOriginTenantReq> tenants) {
        // 验证参数
        if (tenants == null || tenants.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydOriginTenantHistory> historyList = convert(logger, tenants, HydOriginTenantHistory.class, batchNo);

        try {
            // 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    tenantHistoryRepo::saveAll,
                    "HydOriginTenantHistory",
                    batchNo
            );

            // 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (historySaveCount != tenants.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，历史表保存量：%d",
                                batchNo, tenants.size(), historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, historySaveCount);
            return historySaveCount;
        } catch (Exception e) {
            // 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：", batchNo, tenants.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
