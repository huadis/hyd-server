package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultStadiumMapPoint;
import cn.wuhan.hyd.sports.domain.HydResultStadiumMapPointHistory;
import cn.wuhan.hyd.sports.repository.HydResultStadiumMapPointHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultStadiumMapPointRepo;
import cn.wuhan.hyd.sports.req.HydResultStadiumMapPointReq;
import cn.wuhan.hyd.sports.service.IHydResultStadiumMapPointService;
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
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月21日 <br>
 */
@Service
public class HydResultStadiumMapPointServiceImpl extends HydBaseServiceImpl implements IHydResultStadiumMapPointService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultStadiumMapPointService.class);

    @Resource
    private HydResultStadiumMapPointRepo stadiumMapPointRepo;
    @Resource
    private HydResultStadiumMapPointHistoryRepo stadiumMapPointHistoryRepo;

    @Override
    public PageResult<HydResultStadiumMapPointHistory> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultStadiumMapPointHistory> pageResult = stadiumMapPointHistoryRepo.findAll(pageable);
        PageResult<HydResultStadiumMapPointHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultStadiumMapPointHistory> queryAll() {
        return stadiumMapPointHistoryRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultStadiumMapPointHistory save(HydResultStadiumMapPointHistory hydResultCouponStadiumTop) {
        return stadiumMapPointHistoryRepo.save(hydResultCouponStadiumTop);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        stadiumMapPointHistoryRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultStadiumMapPointHistory update(HydResultStadiumMapPointHistory couponStadiumTop) {
        if (couponStadiumTop.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(couponStadiumTop.getId());
        return stadiumMapPointHistoryRepo.save(couponStadiumTop);
    }

    @Override
    public HydResultStadiumMapPointHistory findById(Long id) {
        return stadiumMapPointHistoryRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 消费券场馆预订Top
     *
     * @param stadiumMapPoints 消费券场馆预订Top 数据列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultStadiumMapPointReq> stadiumMapPoints) {
        // 验证参数
        if (stadiumMapPoints == null || stadiumMapPoints.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultStadiumMapPoint> queryList = convert(logger, stadiumMapPoints, HydResultStadiumMapPoint.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultStadiumMapPointHistory> historyList = convert(logger, stadiumMapPoints, HydResultStadiumMapPointHistory.class, batchNo);

        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydResultStadiumMapPoint表，批次号：{}", batchNo);
            stadiumMapPointRepo.deleteByNotBatchNo(batchNo);

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    stadiumMapPointRepo::saveAll,
                    "HydResultStadiumMapPoint",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    stadiumMapPointHistoryRepo::saveAll,
                    "HydResultStadiumMapPointHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != stadiumMapPoints.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, stadiumMapPoints.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, stadiumMapPoints.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
