package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultOrderSport;
import cn.wuhan.hyd.sports.domain.HydResultOrderSportHistory;
import cn.wuhan.hyd.sports.repository.HydResultOrderSportHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultOrderSportRepo;
import cn.wuhan.hyd.sports.req.HydResultOrderSportReq;
import cn.wuhan.hyd.sports.service.IHydResultOrderSportService;
import org.apache.commons.collections.MapUtils;
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
import java.util.stream.Collectors;

/**
 * 功能说明： 场馆预定/体育消费卷-项目消费券订单金额Top5 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultOrderSportServiceImpl extends HydBaseServiceImpl implements IHydResultOrderSportService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultOrderSportService.class);

    @Resource
    private HydResultOrderSportRepo orderSportRepo;
    @Resource
    private HydResultOrderSportHistoryRepo orderSportHistoryRepo;

    @Override
    public PageResult<HydResultOrderSport> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultOrderSport> pageResult = orderSportRepo.findAll(pageable);
        PageResult<HydResultOrderSport> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultOrderSport> queryAll() {
        return orderSportRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultOrderSport save(HydResultOrderSport hydResultOrderSport) {
        return orderSportRepo.save(hydResultOrderSport);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        orderSportRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultOrderSport update(HydResultOrderSport orderSport) {
        if (orderSport.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(orderSport.getId());
        return orderSportRepo.save(orderSport);
    }

    @Override
    public HydResultOrderSport findById(Long id) {
        return orderSportRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> projectTop5(String year) {
        List<Map<String, Object>> list = orderSportRepo.projectTop5(year);
        // 排序：按 orderAmount 数值降序
        return list.stream()
                .sorted((m1, m2) -> {
                    Integer amount1 = Integer.parseInt(MapUtils.getString(m1, "orderAmount"));
                    Integer amount2 = Integer.parseInt(MapUtils.getString(m2, "orderAmount"));
                    return amount2.compareTo(amount1); // 降序：大数在前
                })
                .collect(Collectors.toList());
    }

    /**
     * 批量保存 项目消费券订单金额Top5
     *
     * @param orderSports 项目消费券订单金额Top5 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultOrderSportReq> orderSports) {
        // 验证参数
        if (orderSports == null || orderSports.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();

        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultOrderSport> queryList = convert(logger, orderSports, HydResultOrderSport.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultOrderSportHistory> historyList = convert(logger, orderSports, HydResultOrderSportHistory.class, batchNo);
        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydResultOrderSport表，批次号：{}", batchNo);
            orderSportRepo.deleteByNotBatchNo(batchNo);

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    orderSportRepo::saveAll,
                    "HydResultOrderSport",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    orderSportHistoryRepo::saveAll,
                    "HydResultOrderSportHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != orderSports.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, orderSports.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, orderSports.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
