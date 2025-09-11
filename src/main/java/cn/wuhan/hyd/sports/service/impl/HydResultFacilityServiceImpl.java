package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.DateUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultFacility;
import cn.wuhan.hyd.sports.domain.HydResultFacilityHistory;
import cn.wuhan.hyd.sports.repository.HydResultFacilityHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultFacilityRepo;
import cn.wuhan.hyd.sports.req.HydResultFacilityReq;
import cn.wuhan.hyd.sports.service.IHydResultFacilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明： 体育基础设施-设施全貌 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultFacilityServiceImpl extends HydBaseServiceImpl implements IHydResultFacilityService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultFacilityService.class);

    @Resource
    private HydResultFacilityRepo facilityRepo;
    @Resource
    private HydResultFacilityHistoryRepo facilityHistoryRepo;

    @Override
    public PageResult<HydResultFacility> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultFacility> pageResult = facilityRepo.findAll(pageable);
        PageResult<HydResultFacility> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultFacility> queryAll() {
        return facilityRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultFacility save(HydResultFacility hydResultFacility) {
        return facilityRepo.save(hydResultFacility);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        facilityRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultFacility update(HydResultFacility facility) {
        if (facility.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(facility.getId());
        return facilityRepo.save(facility);
    }

    @Override
    public HydResultFacility findById(Long id) {
        return facilityRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 设施全貌
     *
     * @param facilities 设施全貌 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultFacilityReq> facilities) {
        // 验证参数
        if (facilities == null || facilities.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        int total = facilities.stream()
                .map(HydResultFacilityReq::getFacilityNum)
                .map(obj -> obj == null ? "0" : obj) // 处理null值
                .filter(str -> str.matches("\\d+"))       // 只保留数字字符串
                .mapToInt(val -> {
                    try {
                        return Integer.parseInt(val);
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                }).sum();
        List<HydResultFacility> queryList = new ArrayList<>();
        facilities.forEach(entity -> {
            String facilityNum = entity.getFacilityNum();
            String facilityTypeName = entity.getFacilityTypeName();
            String v = facilityNum == null ? "0" : facilityNum;
            double tmpV = 0;
            try {
                tmpV = Double.parseDouble(v);
            } catch (NumberFormatException e) {
                tmpV = 0;
            }
            double percentage = tmpV / total * 100;
            HydResultFacility facility = new HydResultFacility();
            facility.setFacilityTypeName(facilityTypeName);
            facility.setFacilityNum(facilityNum);
            facility.setFacilityPercentage(percentage);
            facility.setBatchNo(batchNo);
            facility.setStatisticalYear(DateUtil.getPreviousDayYear());
            queryList.add(facility);
        });
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultFacilityHistory> historyList = convert(logger, facilities, HydResultFacilityHistory.class, batchNo);
        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydResultFacility表，批次号：{}", batchNo);
            facilityRepo.deleteByNotBatchNo(batchNo, DateUtil.getPreviousDayYear());

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    facilityRepo::saveAll,
                    "HydResultFacility",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    facilityHistoryRepo::saveAll,
                    "HydResultFacilityHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != facilities.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, facilities.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, facilities.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }

    @Override
    public List<HydResultFacility> facilityOverview(String year) {
        return facilityRepo.facilityOverview(year);
    }

    @Override
    public List<String> allTypeName(String year) {
        return facilityRepo.allTypeName(year);
    }
}
