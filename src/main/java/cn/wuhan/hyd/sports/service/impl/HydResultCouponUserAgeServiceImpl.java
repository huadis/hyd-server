package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.DateUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultCouponUserAge;
import cn.wuhan.hyd.sports.domain.HydResultCouponUserAgeHistory;
import cn.wuhan.hyd.sports.repository.HydResultCouponUserAgeHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultCouponUserAgeRepo;
import cn.wuhan.hyd.sports.req.HydResultCouponUserAgeReq;
import cn.wuhan.hyd.sports.service.IHydResultCouponUserAgeService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明： 体育消费卷-券用户年龄分布 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultCouponUserAgeServiceImpl extends HydBaseServiceImpl implements IHydResultCouponUserAgeService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultCouponUserAgeService.class);

    @Resource
    private HydResultCouponUserAgeRepo couponUserAgeRepo;
    @Resource
    private HydResultCouponUserAgeHistoryRepo couponUserAgeHistoryRepo;
    @Resource
    private IHydSysConfigService configService;

    @Override
    public PageResult<HydResultCouponUserAge> queryAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HydResultCouponUserAge> pageResult = couponUserAgeRepo.findAll(pageable);
        PageResult<HydResultCouponUserAge> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultCouponUserAge> queryAll() {
        return couponUserAgeRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultCouponUserAge save(HydResultCouponUserAge hydResultCouponUserAge) {
        return couponUserAgeRepo.save(hydResultCouponUserAge);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        couponUserAgeRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultCouponUserAge update(HydResultCouponUserAge couponUserAge) {
        if (couponUserAge.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(couponUserAge.getId());
        return couponUserAgeRepo.save(couponUserAge);
    }

    @Override
    public HydResultCouponUserAge findById(Long id) {
        return couponUserAgeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 券用户年龄分布
     *
     * @param couponUserAges 券用户年龄分布 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultCouponUserAgeReq> couponUserAges) {
        // 验证参数
        if (couponUserAges == null || couponUserAges.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        List<HydResultCouponUserAgeHistory> historyList = convert(logger, couponUserAges, HydResultCouponUserAgeHistory.class, batchNo);
        try {
            int querySaveCount = 0;
            boolean refresh = !configService.notRefresh("体育消费卷");
            // 是否冻结，不允许更新查询表
            if (refresh) {
                List<HydResultCouponUserAge> queryList = computeQueryList(couponUserAges, batchNo);
                // 4. 清空查询表：日志记录操作意图，便于问题追溯
                logger.info("【批量保存】开始清空HydResultCouponUserAge表，批次号：{}", batchNo);
                couponUserAgeRepo.deleteByNotBatchNo(batchNo, DateUtil.getPreviousDayYear());

                // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
                querySaveCount = saveAndLog(
                        logger,
                        queryList,
                        couponUserAgeRepo::saveAll,
                        "HydResultCouponUserAge",
                        batchNo
                );
            }

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    couponUserAgeHistoryRepo::saveAll,
                    "HydResultCouponUserAgeHistory",
                    batchNo
            );

            // 7. 校验保存结果：根据 refresh 状态区分校验逻辑，避免数据不一致
            checkSaveData(couponUserAges, refresh, historySaveCount, historySaveCount, batchNo);

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return historySaveCount;
        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, couponUserAges.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }

    private static List<HydResultCouponUserAge> computeQueryList(List<HydResultCouponUserAgeReq> couponUserAges, String batchNo) {
        // 初始化各年龄段累加器为0
        BigDecimal under25 = BigDecimal.ZERO;
        BigDecimal bt26and30 = BigDecimal.ZERO;
        BigDecimal bt31and35 = BigDecimal.ZERO;
        BigDecimal bt36and40 = BigDecimal.ZERO;
        BigDecimal bt41and45 = BigDecimal.ZERO;
        BigDecimal bt46and50 = BigDecimal.ZERO;
        BigDecimal bt51and60 = BigDecimal.ZERO;
        BigDecimal over60 = BigDecimal.ZERO;

        // 遍历列表，累加各字段的有效数值
        for (HydResultCouponUserAgeReq item : couponUserAges) {
            // 处理25岁以下
            under25 = addValue(under25, item.getUnder25Num());
            bt26and30 = addValue(bt26and30, item.getBt26and30Num());
            bt31and35 = addValue(bt31and35, item.getBt31and35Num());
            bt36and40 = addValue(bt36and40, item.getBt36and40Num());
            bt41and45 = addValue(bt41and45, item.getBt41and45Num());
            bt46and50 = addValue(bt46and50, item.getBt46and50Num());
            bt51and60 = addValue(bt51and60, item.getBt51and60Num());
            over60 = addValue(over60, item.getOver60Num());
        }
        HydResultCouponUserAge age = new HydResultCouponUserAge();
        age.setUnder25Num(calculateSingleAverage(under25, couponUserAges.size()).toString());
        age.setBt26and30Num(calculateSingleAverage(bt26and30, couponUserAges.size()).toString());
        age.setBt31and35Num(calculateSingleAverage(bt31and35, couponUserAges.size()).toString());
        age.setBt36and40Num(calculateSingleAverage(bt36and40, couponUserAges.size()).toString());
        age.setBt41and45Num(calculateSingleAverage(bt41and45, couponUserAges.size()).toString());
        age.setBt46and50Num(calculateSingleAverage(bt46and50, couponUserAges.size()).toString());
        age.setBt51and60Num(calculateSingleAverage(bt51and60, couponUserAges.size()).toString());
        age.setOver60Num(calculateSingleAverage(over60, couponUserAges.size()).toString());
        age.setBatchNo(batchNo);
        age.setStatisticalYear(DateUtil.getPreviousDayYear());
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultCouponUserAge> queryList = new ArrayList<>();
        queryList.add(age);
        return queryList;
    }

    @Override
    public HydResultCouponUserAge latestCouponUserAge(String year) {
        return couponUserAgeRepo.latestCouponUserAge(year);
    }
}
