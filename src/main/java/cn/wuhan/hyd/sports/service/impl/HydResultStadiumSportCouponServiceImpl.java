package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.DateUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultStadiumSportCoupon;
import cn.wuhan.hyd.sports.domain.HydResultStadiumSportCouponHistory;
import cn.wuhan.hyd.sports.repository.HydResultStadiumSportCouponHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultStadiumSportCouponRepo;
import cn.wuhan.hyd.sports.req.HydResultStadiumSportCouponReq;
import cn.wuhan.hyd.sports.service.IHydResultStadiumSportCouponService;
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
import java.util.List;

/**
 * 功能说明： 场馆预定/体育消费卷-运动项目分布用券数占比 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultStadiumSportCouponServiceImpl extends HydBaseServiceImpl implements IHydResultStadiumSportCouponService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultStadiumSportCouponService.class);

    @Resource
    private HydResultStadiumSportCouponRepo stadiumSportCouponRepo;
    @Resource
    private HydResultStadiumSportCouponHistoryRepo stadiumSportCouponHistoryRepo;
    @Resource
    private IHydSysConfigService configService;

    @Override
    public PageResult<HydResultStadiumSportCoupon> queryAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HydResultStadiumSportCoupon> pageResult = stadiumSportCouponRepo.findAll(pageable);
        PageResult<HydResultStadiumSportCoupon> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultStadiumSportCoupon> queryAll() {
        return stadiumSportCouponRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultStadiumSportCoupon save(HydResultStadiumSportCoupon hydResultStadiumSportCoupon) {
        return stadiumSportCouponRepo.save(hydResultStadiumSportCoupon);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        stadiumSportCouponRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultStadiumSportCoupon update(HydResultStadiumSportCoupon stadiumSportCoupon) {
        if (stadiumSportCoupon.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(stadiumSportCoupon.getId());
        return stadiumSportCouponRepo.save(stadiumSportCoupon);
    }

    @Override
    public HydResultStadiumSportCoupon findById(Long id) {
        return stadiumSportCouponRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 运动项目分布用券数占比
     *
     * @param stadiumSportCoupons 运动项目分布用券数占比 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultStadiumSportCouponReq> stadiumSportCoupons) {
        // 验证参数
        if (stadiumSportCoupons == null || stadiumSportCoupons.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        List<HydResultStadiumSportCouponHistory> historyList = convert(logger, stadiumSportCoupons, HydResultStadiumSportCouponHistory.class, batchNo);
        try {
            int querySaveCount = 0;
            boolean refresh = !configService.notRefresh("场馆预定");
            // 是否冻结，不允许更新查询表
            if (refresh) {
                List<HydResultStadiumSportCoupon> queryList = convert(logger, stadiumSportCoupons, HydResultStadiumSportCoupon.class, batchNo);
                // 4. 清空查询表：日志记录操作意图，便于问题追溯
                logger.info("【批量保存】开始清空HydResultStadiumSportCoupon表，批次号：{}", batchNo);
                stadiumSportCouponRepo.deleteByNotBatchNo(batchNo, DateUtil.getPreviousDayYear());

                // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
                querySaveCount = saveAndLog(
                        logger,
                        queryList,
                        stadiumSportCouponRepo::saveAll,
                        "HydResultStadiumSportCoupon",
                        batchNo
                );
            }

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    stadiumSportCouponHistoryRepo::saveAll,
                    "HydResultStadiumSportCouponHistory",
                    batchNo
            );

            // 7. 校验保存结果：根据 refresh 状态区分校验逻辑，避免数据不一致
            checkSaveData(stadiumSportCoupons, refresh, querySaveCount, historySaveCount, batchNo);

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return historySaveCount;
        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, stadiumSportCoupons.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
