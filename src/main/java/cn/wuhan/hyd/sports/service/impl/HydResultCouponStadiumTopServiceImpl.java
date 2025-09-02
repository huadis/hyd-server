package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultCouponStadiumTop;
import cn.wuhan.hyd.sports.domain.HydResultCouponStadiumTopHistory;
import cn.wuhan.hyd.sports.repository.HydResultCouponStadiumTopHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultCouponStadiumTopRepo;
import cn.wuhan.hyd.sports.req.HydResultCouponStadiumTopReq;
import cn.wuhan.hyd.sports.service.IHydResultCouponStadiumTopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定/体育消费卷-消费券场馆预订Top 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultCouponStadiumTopServiceImpl extends HydBaseServiceImpl implements IHydResultCouponStadiumTopService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultCouponStadiumTopService.class);

    @Resource
    private HydResultCouponStadiumTopRepo couponStadiumTopRepo;
    @Resource
    private HydResultCouponStadiumTopHistoryRepo couponStadiumTopHistoryRepo;

    @Override
    public PageResult<HydResultCouponStadiumTop> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultCouponStadiumTop> pageResult = couponStadiumTopRepo.findAll(pageable);
        PageResult<HydResultCouponStadiumTop> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultCouponStadiumTop> queryAll() {
        return couponStadiumTopRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultCouponStadiumTop save(HydResultCouponStadiumTop hydResultCouponStadiumTop) {
        return couponStadiumTopRepo.save(hydResultCouponStadiumTop);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        couponStadiumTopRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultCouponStadiumTop update(HydResultCouponStadiumTop couponStadiumTop) {
        if (couponStadiumTop.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(couponStadiumTop.getId());
        return couponStadiumTopRepo.save(couponStadiumTop);
    }

    @Override
    public HydResultCouponStadiumTop findById(Long id) {
        return couponStadiumTopRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> stadiumTop5() {
        return couponStadiumTopRepo.stadiumTop5();
    }

    /**
     * 批量保存 消费券场馆预订Top
     *
     * @param couponStadiumTops 消费券场馆预订Top 数据列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultCouponStadiumTopReq> couponStadiumTops) {
        // 验证参数
        if (couponStadiumTops == null || couponStadiumTops.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();

        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultCouponStadiumTop> queryList = convert(logger, couponStadiumTops, HydResultCouponStadiumTop.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultCouponStadiumTopHistory> historyList = convert(logger, couponStadiumTops, HydResultCouponStadiumTopHistory.class, batchNo);
        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydResultCouponStadiumTop表，批次号：{}", batchNo);
            couponStadiumTopRepo.deleteByNotBatchNo(batchNo);

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    couponStadiumTopRepo::saveAll,
                    "HydResultCouponStadiumTop",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    couponStadiumTopHistoryRepo::saveAll,
                    "HydResultCouponStadiumTopHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != couponStadiumTops.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, couponStadiumTops.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, couponStadiumTops.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }

}

