package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.DateUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultOrderStadium;
import cn.wuhan.hyd.sports.domain.HydResultOrderStadiumHistory;
import cn.wuhan.hyd.sports.repository.HydResultOrderStadiumHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultOrderStadiumRepo;
import cn.wuhan.hyd.sports.req.HydResultOrderStadiumReq;
import cn.wuhan.hyd.sports.service.IHydResultOrderStadiumService;
import cn.wuhan.hyd.sports.service.IHydSysConfigService;
import org.apache.commons.collections.MapUtils;
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
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能说明： 体育消费卷-场馆消费券订单金额Top5 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultOrderStadiumServiceImpl extends HydBaseServiceImpl implements IHydResultOrderStadiumService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultOrderStadiumService.class);

    @Resource
    private HydResultOrderStadiumRepo orderStadiumRepo;
    @Resource
    private HydResultOrderStadiumHistoryRepo orderStadiumHistoryRepo;
    @Resource
    private IHydSysConfigService configService;

    @Override
    public PageResult<HydResultOrderStadium> queryAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HydResultOrderStadium> pageResult = orderStadiumRepo.findAll(pageable);
        PageResult<HydResultOrderStadium> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultOrderStadium> queryAll() {
        return orderStadiumRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultOrderStadium save(HydResultOrderStadium hydResultOrderStadium) {
        return orderStadiumRepo.save(hydResultOrderStadium);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        orderStadiumRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultOrderStadium update(HydResultOrderStadium orderStadium) {
        if (orderStadium.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(orderStadium.getId());
        return orderStadiumRepo.save(orderStadium);
    }

    @Override
    public HydResultOrderStadium findById(Long id) {
        return orderStadiumRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 场馆消费券订单金额Top5
     *
     * @param orderStadiums 场馆消费券订单金额Top5 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultOrderStadiumReq> orderStadiums) {
        // 验证参数
        if (orderStadiums == null || orderStadiums.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        List<HydResultOrderStadiumHistory> historyList = convert(logger, orderStadiums, HydResultOrderStadiumHistory.class, batchNo);
        try {
            int querySaveCount = 0;
            boolean refresh = !configService.notRefresh("体育消费卷");
            // 是否冻结，不允许更新查询表
            if (refresh) {
                List<HydResultOrderStadium> queryList = convert(logger, orderStadiums, HydResultOrderStadium.class, batchNo);
                // 4. 清空查询表：日志记录操作意图，便于问题追溯
                logger.info("【批量保存】开始清空HydResultOrderStadium表，批次号：{}", batchNo);
                orderStadiumRepo.deleteByNotBatchNo(batchNo, DateUtil.getPreviousDayYear());

                // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
                querySaveCount = saveAndLog(
                        logger,
                        queryList,
                        orderStadiumRepo::saveAll,
                        "HydResultOrderStadium",
                        batchNo
                );
            }

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    orderStadiumHistoryRepo::saveAll,
                    "HydResultOrderStadiumHistory",
                    batchNo
            );

            // 7. 校验保存结果：根据 refresh 状态区分校验逻辑，避免数据不一致
            checkSaveData(orderStadiums, refresh, querySaveCount, historySaveCount, batchNo);

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return historySaveCount;
        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, orderStadiums.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }

    @Override
    public List<Map<String, Object>> stadiumTop5(String year) {
        List<Map<String, Object>> list = orderStadiumRepo.stadiumTop5(year);
        // 排序：按 orderAmount 数值降序
        return list.stream()
                .sorted((m1, m2) -> {
                    Integer amount1 = Integer.parseInt(MapUtils.getString(m1, "orderAmount"));
                    Integer amount2 = Integer.parseInt(MapUtils.getString(m2, "orderAmount"));
                    return amount2.compareTo(amount1); // 降序：大数在前
                })
                .collect(Collectors.toList());
    }


}
