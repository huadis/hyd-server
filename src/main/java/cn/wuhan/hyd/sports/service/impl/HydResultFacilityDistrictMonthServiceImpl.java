package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultFacilityDistrictMonth;
import cn.wuhan.hyd.sports.domain.HydResultFacilityDistrictMonthHistory;
import cn.wuhan.hyd.sports.repository.HydResultFacilityDistrictMonthHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultFacilityDistrictMonthRepo;
import cn.wuhan.hyd.sports.req.HydResultFacilityDistrictMonthReq;
import cn.wuhan.hyd.sports.service.IHydResultFacilityDistrictMonthService;
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
 * 功能说明： 体育基础设施-设施各区月数据 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultFacilityDistrictMonthServiceImpl extends HydBaseServiceImpl implements IHydResultFacilityDistrictMonthService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultFacilityDistrictMonthService.class);

    @Resource
    private HydResultFacilityDistrictMonthRepo facilityDistrictMonthRepo;
    @Resource
    private HydResultFacilityDistrictMonthHistoryRepo facilityDistrictMonthHistoryRepo;

    @Override
    public PageResult<HydResultFacilityDistrictMonth> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultFacilityDistrictMonth> pageResult = facilityDistrictMonthRepo.findAll(pageable);
        PageResult<HydResultFacilityDistrictMonth> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultFacilityDistrictMonth> queryAll() {
        return facilityDistrictMonthRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultFacilityDistrictMonth save(HydResultFacilityDistrictMonth hydResultFacilityDistrictMonth) {
        return facilityDistrictMonthRepo.save(hydResultFacilityDistrictMonth);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        facilityDistrictMonthRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultFacilityDistrictMonth update(HydResultFacilityDistrictMonth facilityDistrictMonth) {
        if (facilityDistrictMonth.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(facilityDistrictMonth.getId());
        return facilityDistrictMonthRepo.save(facilityDistrictMonth);
    }

    @Override
    public HydResultFacilityDistrictMonth findById(Long id) {
        return facilityDistrictMonthRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 设施各区月数据
     *
     * @param facilityDistrictMonths 设施各区月数据 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultFacilityDistrictMonthReq> facilityDistrictMonths) {
        // 验证参数
        if (facilityDistrictMonths == null || facilityDistrictMonths.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultFacilityDistrictMonth> queryList = convert(logger, facilityDistrictMonths, HydResultFacilityDistrictMonth.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultFacilityDistrictMonthHistory> historyList = convert(logger, facilityDistrictMonths, HydResultFacilityDistrictMonthHistory.class, batchNo);

        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydResultFacilityDistrictMonth表，批次号：{}", batchNo);
            facilityDistrictMonthRepo.deleteAll();

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    facilityDistrictMonthRepo::saveAll,
                    "HydResultFacilityDistrictMonth",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    facilityDistrictMonthHistoryRepo::saveAll,
                    "HydResultFacilityDistrictMonthHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != facilityDistrictMonths.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, facilityDistrictMonths.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, facilityDistrictMonths.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }

    @Override
    public List<HydResultFacilityDistrictMonth> inspectMaintenance(String year) {
        return facilityDistrictMonthRepo.inspectMaintenance(year);
    }
}
