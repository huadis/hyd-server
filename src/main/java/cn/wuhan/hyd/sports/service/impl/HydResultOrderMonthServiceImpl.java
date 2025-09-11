package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.DateUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultOrderMonth;
import cn.wuhan.hyd.sports.domain.HydResultOrderMonthHistory;
import cn.wuhan.hyd.sports.repository.HydResultOrderMonthHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultOrderMonthRepo;
import cn.wuhan.hyd.sports.req.HydResultOrderMonthReq;
import cn.wuhan.hyd.sports.service.IHydResultOrderMonthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 功能说明： 体育消费卷-订单趋势 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultOrderMonthServiceImpl extends HydBaseServiceImpl implements IHydResultOrderMonthService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultOrderMonthService.class);

    @Resource
    private HydResultOrderMonthRepo orderMonthRepo;
    @Resource
    private HydResultOrderMonthHistoryRepo orderMonthHistoryRepo;

    @Override
    public PageResult<HydResultOrderMonth> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultOrderMonth> pageResult = orderMonthRepo.findAll(pageable);
        PageResult<HydResultOrderMonth> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultOrderMonth> queryAll() {
        return orderMonthRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultOrderMonth save(HydResultOrderMonth hydResultOrderMonth) {
        return orderMonthRepo.save(hydResultOrderMonth);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        orderMonthRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultOrderMonth update(HydResultOrderMonth orderMonth) {
        if (orderMonth.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(orderMonth.getId());
        return orderMonthRepo.save(orderMonth);
    }

    @Override
    public HydResultOrderMonth findById(Long id) {
        return orderMonthRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 订单趋势
     *
     * @param orderMonths 订单趋势 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultOrderMonthReq> orderMonths) {
        // 验证参数
        if (orderMonths == null || orderMonths.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();

        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultOrderMonth> queryList = convert(logger, orderMonths, HydResultOrderMonth.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultOrderMonthHistory> historyList = convert(logger, orderMonths, HydResultOrderMonthHistory.class, batchNo);
        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydResultOrderMonth表，批次号：{}", batchNo);
            orderMonthRepo.deleteByNotBatchNo(batchNo, DateUtil.getPreviousDayYear());

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    orderMonthRepo::saveAll,
                    "HydResultOrderMonth",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    orderMonthHistoryRepo::saveAll,
                    "HydResultOrderMonthHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != orderMonths.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, orderMonths.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, orderMonths.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }

    @Override
    public List<HydResultOrderMonth> list(String year) {
        List<HydResultOrderMonth> list = orderMonthRepo.list(year);
        // 排序
        list.sort((o1, o2) -> {
            // 定义年龄段的正确顺序
            List<String> order = Arrays.asList(
                    "1", "2", "3", "4", "5", "6",
                    "7", "8", "9", "10", "11", "12"
            );
            // 根据在顺序列表中的索引进行比较
            return Integer.compare(
                    order.indexOf(o1.getMonth()),
                    order.indexOf(o2.getMonth())
            );
        });
        return list;
    }
}
