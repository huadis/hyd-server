package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydOriginOrderHistory;
import cn.wuhan.hyd.sports.repository.HydOriginOrderHistoryRepo;
import cn.wuhan.hyd.sports.req.HydOriginOrderReq;
import cn.wuhan.hyd.sports.service.IHydOriginOrderService;
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
 * 功能说明： 订单表业务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Service
public class HydOriginOrderServiceImpl extends HydBaseServiceImpl implements IHydOriginOrderService {

    private final Logger logger = LoggerFactory.getLogger(IHydOriginOrderService.class);
    @Resource
    private HydOriginOrderHistoryRepo orderHistoryRepo;

    @Override
    public PageResult<HydOriginOrderHistory> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydOriginOrderHistory> pageResult = orderHistoryRepo.findAll(pageable);
        PageResult<HydOriginOrderHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydOriginOrderHistory> queryAll() {
        return orderHistoryRepo.findAll();
    }

    @Override
    public HydOriginOrderHistory findById(String id) {
        return orderHistoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("订单不存在，ID：" + id));
    }

    @Override
    public Map<String, Object> orderStat() {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginOrderHistory save(HydOriginOrderHistory hydOriginOrder) {
        return orderHistoryRepo.save(hydOriginOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        orderHistoryRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginOrderHistory update(HydOriginOrderHistory order) {
        if (order.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 校验订单是否存在
        findById(order.getId());
        return orderHistoryRepo.save(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydOriginOrderReq> orders) {
        // 验证参数
        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydOriginOrderHistory> historyList = convert(logger, orders, HydOriginOrderHistory.class, batchNo);

        try {
            // 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    orderHistoryRepo::saveAll,
                    "HydOriginOrderHistory",
                    batchNo
            );

            // 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (historySaveCount != orders.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，历史表保存量：%d",
                                batchNo, orders.size(), historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, historySaveCount);
            return historySaveCount;
        } catch (Exception e) {
            // 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：", batchNo, orders.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
