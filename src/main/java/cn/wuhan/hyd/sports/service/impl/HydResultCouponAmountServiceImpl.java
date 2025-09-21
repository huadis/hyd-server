package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.DateUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultCouponAmount;
import cn.wuhan.hyd.sports.domain.HydResultCouponAmountHistory;
import cn.wuhan.hyd.sports.repository.HydResultCouponAmountHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultCouponAmountRepo;
import cn.wuhan.hyd.sports.req.HydResultCouponAmountReq;
import cn.wuhan.hyd.sports.service.IHydResultCouponAmountService;
import cn.wuhan.hyd.sports.service.IHydSysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明： 体育消费卷-消费券总金额 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultCouponAmountServiceImpl extends HydBaseServiceImpl implements IHydResultCouponAmountService {

    private final Logger logger = LoggerFactory.getLogger(HydResultCouponAmountServiceImpl.class);

    @Resource
    private HydResultCouponAmountRepo couponAmountRepo;
    @Resource
    private HydResultCouponAmountHistoryRepo couponAmountHistoryRepo;
    @Resource
    private IHydSysConfigService configService;

    @Override
    public PageResult<HydResultCouponAmount> queryAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HydResultCouponAmount> pageResult = couponAmountRepo.findAll(pageable);
        PageResult<HydResultCouponAmount> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultCouponAmount> queryAll() {
        return couponAmountRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydResultCouponAmount save(HydResultCouponAmount hydResultCouponAmount) {
        return couponAmountRepo.save(hydResultCouponAmount);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        couponAmountRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydResultCouponAmount update(HydResultCouponAmount couponAmount) {
        if (couponAmount.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(couponAmount.getId());
        return couponAmountRepo.save(couponAmount);
    }

    @Override
    public HydResultCouponAmount findById(Long id) {
        return couponAmountRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 消费券总金额
     *
     * @param couponAmounts 消费券总金额 数据列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultCouponAmountReq> couponAmounts) {
        // 验证参数
        if (couponAmounts == null || couponAmounts.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        List<HydResultCouponAmountHistory> historyList = convert(logger, couponAmounts, HydResultCouponAmountHistory.class, batchNo);
        try {
            int querySaveCount = 0;
            boolean refresh = !configService.notRefresh("体育消费卷");
            // 是否冻结，不允许更新查询表
            if (refresh) {
                //List<HydResultCouponAmount> queryList = computeQueryList(couponAmounts, batchNo);
                List<HydResultCouponAmount> queryList = convert(logger, couponAmounts, HydResultCouponAmount.class, batchNo);
                // 4. 清空查询表：日志记录操作意图，便于问题追溯
                logger.info("【批量保存】开始清空HydResultCouponAmount表，批次号：{}", batchNo);
                couponAmountRepo.deleteByNotBatchNo(batchNo, DateUtil.getPreviousDayYear());

                // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
                querySaveCount = saveAndLog(
                        logger,
                        queryList,
                        couponAmountRepo::saveAll,
                        "HydResultCouponAmount",
                        batchNo
                );
            }

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    couponAmountHistoryRepo::saveAll,
                    "HydResultCouponAmountHistory",
                    batchNo
            );

            // 7. 校验保存结果：根据 refresh 状态区分校验逻辑，避免数据不一致
            checkSaveData(couponAmounts, refresh, querySaveCount, historySaveCount, batchNo);

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return historySaveCount;
        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, couponAmounts.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }

    private static List<HydResultCouponAmount> computeQueryList(List<HydResultCouponAmountReq> couponAmounts, String batchNo) {
        // 初始化求和变量
        BigDecimal totalSendAmount = BigDecimal.ZERO;
        BigDecimal totalReceiveCount = BigDecimal.ZERO;
        BigDecimal totalUsedCount = BigDecimal.ZERO;
        BigDecimal totalUseCouponAmount = BigDecimal.ZERO;
        BigDecimal totalOrderAmount = BigDecimal.ZERO;

        // 用于计算平均值的计数器
        int validUsedRatioCount = 0;
        int validOrderRatioCount = 0;
        BigDecimal totalUsedRatio = BigDecimal.ZERO;
        BigDecimal totalOrderRatio = BigDecimal.ZERO;

        // 遍历列表进行累加
        for (HydResultCouponAmountReq couponAmount : couponAmounts) {
            // 求和字段（String -> BigDecimal）
            totalSendAmount = parseAndAdd(totalSendAmount, couponAmount.getSendAmount());
            totalReceiveCount = parseAndAdd(totalReceiveCount, couponAmount.getReceiveCount());
            totalUsedCount = parseAndAdd(totalUsedCount, couponAmount.getUsedCount());
            totalUseCouponAmount = parseAndAdd(totalUseCouponAmount, couponAmount.getUseCouponAmount());
            totalOrderAmount = parseAndAdd(totalOrderAmount, couponAmount.getOrderAmount());

            // 平均值字段
            totalUsedRatio = parseAndAdd(totalUsedRatio, couponAmount.getUsedRatio());
            if (couponAmount.getUsedRatio() != null && !couponAmount.getUsedRatio().trim().isEmpty()) {
                validUsedRatioCount++;
            }

            totalOrderRatio = parseAndAdd(totalOrderRatio, couponAmount.getOrderRatio());
            if (couponAmount.getOrderRatio() != null && !couponAmount.getOrderRatio().trim().isEmpty()) {
                validOrderRatioCount++;
            }
        }

        // 计算平均值（保留4位小数，可根据需要调整）
        BigDecimal avgUsedRatio = validUsedRatioCount > 0
                ? totalUsedRatio.divide(BigDecimal.valueOf(validUsedRatioCount), 4, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        BigDecimal avgOrderRatio = validOrderRatioCount > 0
                ? totalOrderRatio.divide(BigDecimal.valueOf(validOrderRatioCount), 4, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;
        HydResultCouponAmount couponAmount = new HydResultCouponAmount();
        couponAmount.setSendAmount(totalSendAmount.toString());
        couponAmount.setReceiveCount(totalReceiveCount.toString());
        couponAmount.setUsedCount(totalUsedCount.toString());
        couponAmount.setUseCouponAmount(totalUseCouponAmount.toString());
        couponAmount.setOrderAmount(totalOrderAmount.toString());
        couponAmount.setUsedRatio(avgUsedRatio.toString());
        couponAmount.setOrderRatio(avgOrderRatio.toString());
        couponAmount.setBatchNo(batchNo);
        couponAmount.setStatisticalYear(DateUtil.getPreviousDayYear());
        List<HydResultCouponAmount> queryList = new ArrayList<>();
        queryList.add(couponAmount);
        return queryList;
    }

    // 工具方法：安全地将 String 转为 BigDecimal 并相加
    private static BigDecimal parseAndAdd(BigDecimal sum, String value) {
        if (value == null || value.trim().isEmpty()) {
            return sum;
        }
        try {
            return sum.add(new BigDecimal(value.trim()));
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + value);
            return sum;
        }
    }


    @Override
    public HydResultCouponAmount findCouponAmount(String year, String type, String activityName, String groupName) {
        return couponAmountRepo.findCouponAmount(year, type, activityName, groupName);
    }

    @Override
    public List<String> allType(String year) {
        return couponAmountRepo.allType(year);
    }

    @Override
    public List<String> allActivityName(String year, String type) {
        return couponAmountRepo.allActivityName(year, type);
    }

    @Override
    public List<String> allGroupName(String year, String type, String activityName) {
        return couponAmountRepo.allGroupName(year, type, activityName);
    }
}
