package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultCouponUser;
import cn.wuhan.hyd.sports.domain.HydResultCouponUserHistory;
import cn.wuhan.hyd.sports.domain.HydResultOrder;
import cn.wuhan.hyd.sports.domain.HydResultOrderHistory;
import cn.wuhan.hyd.sports.repository.HydResultCouponUserHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultCouponUserRepo;
import cn.wuhan.hyd.sports.req.HydResultCouponUserReq;
import cn.wuhan.hyd.sports.service.IHydResultCouponUserService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能说明： 体育消费卷-券用户分析 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultCouponUserServiceImpl extends HydBaseServiceImpl implements IHydResultCouponUserService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultCouponUserService.class);

    @Resource
    private HydResultCouponUserRepo couponUserRepo;
    @Resource
    private HydResultCouponUserHistoryRepo couponUserHistoryRepo;

    @Override
    public PageResult<HydResultCouponUser> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultCouponUser> pageResult = couponUserRepo.findAll(pageable);
        PageResult<HydResultCouponUser> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultCouponUser> queryAll() {
        return couponUserRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultCouponUser save(HydResultCouponUser hydResultCouponUser) {
        return couponUserRepo.save(hydResultCouponUser);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        couponUserRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultCouponUser update(HydResultCouponUser couponUser) {
        if (couponUser.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(couponUser.getId());
        return couponUserRepo.save(couponUser);
    }

    @Override
    public HydResultCouponUser findById(Long id) {
        return couponUserRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 券用户分析
     *
     * @param couponUsers 券用户分析 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultCouponUserReq> couponUsers) {
        // 验证参数
        if (couponUsers == null || couponUsers.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (couponUsers.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }
        String batchNo = UUIDUtil.getBatchNo();

        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultCouponUser> queryList = convert(logger, couponUsers, HydResultCouponUser.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultCouponUserHistory> historyList = convert(logger, couponUsers, HydResultCouponUserHistory.class, batchNo);

        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydResultCouponUser表，批次号：{}", batchNo);
            couponUserRepo.deleteAll();

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    couponUserRepo::saveAll,
                    "HydResultCouponUser",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    couponUserHistoryRepo::saveAll,
                    "HydResultCouponUserHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != couponUsers.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, couponUsers.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, couponUsers.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
