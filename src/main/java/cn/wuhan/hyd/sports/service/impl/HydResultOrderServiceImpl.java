package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.DateUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultOrder;
import cn.wuhan.hyd.sports.domain.HydResultOrderHistory;
import cn.wuhan.hyd.sports.repository.HydResultOrderHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultOrderRepo;
import cn.wuhan.hyd.sports.req.HydResultOrderReq;
import cn.wuhan.hyd.sports.service.IHydResultOrderService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 体育消费卷-订单数量 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultOrderServiceImpl extends HydBaseServiceImpl implements IHydResultOrderService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultOrderService.class);

    @Resource
    private HydResultOrderRepo orderRepo;
    @Resource
    private HydResultOrderHistoryRepo orderHistoryRepo;
    @Resource
    private IHydSysConfigService configService;

    @Override
    public PageResult<HydResultOrder> queryAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HydResultOrder> pageResult = orderRepo.findAll(pageable);
        PageResult<HydResultOrder> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultOrder> queryAll() {
        return orderRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultOrder save(HydResultOrder hydResultOrder) {
        return orderRepo.save(hydResultOrder);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        orderRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultOrder update(HydResultOrder order) {
        if (order.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(order.getId());
        return orderRepo.save(order);
    }

    @Override
    public HydResultOrder findById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public Map<String, Object> orderStat(String year) {
        Map<String, Object> result = new HashMap<>();
        result.put("sumOrderNum", orderRepo.sumOrderNum());
        result.put("sumOrderAmount", orderRepo.sumOrderAmount());
        result.put("monthOrderStat", orderRepo.overview(year));
        return result;
    }

    @Override
    public Map<String, Object> overview(String year) {
        return orderRepo.overview(year);
    }

    /**
     * 批量保存 订单数量
     *
     * @param orders 订单数量 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultOrderReq> orders) {
        // 验证参数
        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        List<HydResultOrderHistory> historyList = convert(logger, orders, HydResultOrderHistory.class, batchNo);
        try {
            int querySaveCount = 0;
            boolean refresh = !configService.notRefresh("体育消费卷");
            // 是否冻结，不允许更新查询表
            if (refresh) {
                List<HydResultOrder> queryList = convert(logger, orders, HydResultOrder.class, batchNo);
                // 4. 清空查询表：日志记录操作意图，便于问题追溯
                logger.info("【批量保存】开始清空HydResultOrder表，批次号：{}", batchNo);
                orderRepo.deleteByNotBatchNo(batchNo, DateUtil.getPreviousDayYear());

                // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
                querySaveCount = saveAndLog(
                        logger,
                        queryList,
                        orderRepo::saveAll,
                        "HydResultOrder",
                        batchNo
                );
            }

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    orderHistoryRepo::saveAll,
                    "HydResultOrderHistory",
                    batchNo
            );

            // 7. 校验保存结果：根据 refresh 状态区分校验逻辑，避免数据不一致
            checkSaveData(orders, refresh, querySaveCount, historySaveCount, batchNo);

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return historySaveCount;
        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, orders.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
