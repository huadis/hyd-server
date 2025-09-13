package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.DateUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultStadiumMapPoint;
import cn.wuhan.hyd.sports.domain.HydResultStadiumMapPointHistory;
import cn.wuhan.hyd.sports.repository.HydResultStadiumMapPointHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultStadiumMapPointRepo;
import cn.wuhan.hyd.sports.req.HydResultStadiumMapPointReq;
import cn.wuhan.hyd.sports.service.IHydResultStadiumMapPointService;
import cn.wuhan.hyd.sports.service.IHydSysConfigService;
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
    @Resource
    private IHydSysConfigService configService;

    @Override
    public PageResult<HydResultStadiumMapPoint> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultStadiumMapPoint> pageResult = stadiumMapPointRepo.findAll(pageable);
        PageResult<HydResultStadiumMapPoint> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultStadiumMapPoint> queryAll() {
        return stadiumMapPointRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultStadiumMapPoint save(HydResultStadiumMapPoint hydResultCouponStadiumTop) {
        return stadiumMapPointRepo.save(hydResultCouponStadiumTop);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        stadiumMapPointRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultStadiumMapPoint update(HydResultStadiumMapPoint couponStadiumTop) {
        if (couponStadiumTop.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(couponStadiumTop.getId());
        return stadiumMapPointRepo.save(couponStadiumTop);
    }

    @Override
    public HydResultStadiumMapPoint findById(Long id) {
        return stadiumMapPointRepo.findById(id)
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
        List<HydResultStadiumMapPointHistory> historyList = convert(logger, stadiumMapPoints, HydResultStadiumMapPointHistory.class, batchNo);
        try {
            int querySaveCount = 0;
            boolean refresh = !configService.notRefresh("体育基础设施");
            // 是否冻结，不允许更新查询表
            if (refresh) {
                List<HydResultStadiumMapPoint> queryList = convert(logger, stadiumMapPoints, HydResultStadiumMapPoint.class, batchNo);
                // 4. 清空查询表：日志记录操作意图，便于问题追溯
                logger.info("【批量保存】开始清空HydResultStadiumMapPoint表，批次号：{}", batchNo);
                stadiumMapPointRepo.deleteByNotBatchNo(batchNo, DateUtil.getPreviousDayYear());

                // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
                querySaveCount = saveAndLog(
                        logger,
                        queryList,
                        stadiumMapPointRepo::saveAll,
                        "HydResultStadiumMapPoint",
                        batchNo
                );
            }

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    stadiumMapPointHistoryRepo::saveAll,
                    "HydResultStadiumMapPointHistory",
                    batchNo
            );

            // 7. 校验保存结果：根据 refresh 状态区分校验逻辑，避免数据不一致
            checkSaveData(stadiumMapPoints, refresh, querySaveCount, historySaveCount, batchNo);

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return historySaveCount;
        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, stadiumMapPoints.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
