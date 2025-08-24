package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultFacilityDistrict;
import cn.wuhan.hyd.sports.domain.HydResultFacilityDistrictHistory;
import cn.wuhan.hyd.sports.repository.HydResultFacilityDistrictHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultFacilityDistrictRepo;
import cn.wuhan.hyd.sports.req.HydResultFacilityDistrictReq;
import cn.wuhan.hyd.sports.service.IHydResultFacilityDistrictService;
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
 * 功能说明： 体育基础设施-设施各区分布 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultFacilityDistrictServiceImpl extends HydBaseServiceImpl implements IHydResultFacilityDistrictService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultFacilityDistrictService.class);

    @Resource
    private HydResultFacilityDistrictRepo facilityDistrictRepo;
    @Resource
    private HydResultFacilityDistrictHistoryRepo facilityDistrictHistoryRepo;

    @Override
    public PageResult<HydResultFacilityDistrict> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultFacilityDistrict> pageResult = facilityDistrictRepo.findAll(pageable);
        PageResult<HydResultFacilityDistrict> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultFacilityDistrict> queryAll() {
        return facilityDistrictRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultFacilityDistrict save(HydResultFacilityDistrict hydResultFacilityDistrict) {
        return facilityDistrictRepo.save(hydResultFacilityDistrict);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        facilityDistrictRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultFacilityDistrict update(HydResultFacilityDistrict facilityDistrict) {
        if (facilityDistrict.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(facilityDistrict.getId());
        return facilityDistrictRepo.save(facilityDistrict);
    }

    @Override
    public HydResultFacilityDistrict findById(Long id) {
        return facilityDistrictRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 设施各区分布
     *
     * @param facilityDistricts 设施各区分布 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultFacilityDistrictReq> facilityDistricts) {
        // 验证参数
        if (facilityDistricts == null || facilityDistricts.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (facilityDistricts.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }
        String batchNo = UUIDUtil.getBatchNo();
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultFacilityDistrict> queryList = convert(logger, facilityDistricts, HydResultFacilityDistrict.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultFacilityDistrictHistory> historyList = convert(logger, facilityDistricts, HydResultFacilityDistrictHistory.class, batchNo);
        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydResultFacilityDistrict表，批次号：{}", batchNo);
            facilityDistrictRepo.deleteAll();

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    facilityDistrictRepo::saveAll,
                    "HydResultFacilityDistrict",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    facilityDistrictHistoryRepo::saveAll,
                    "HydResultFacilityDistrictHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != facilityDistricts.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, facilityDistricts.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, facilityDistricts.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }

    @Override
    public List<HydResultFacilityDistrict> districtDistribution(String year, String typeName) {
        return facilityDistrictRepo.districtDistribution(year, typeName);
    }
}
