package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydOriginStadiumItem;
import cn.wuhan.hyd.sports.domain.HydOriginStadiumItemHistory;
import cn.wuhan.hyd.sports.repository.HydOriginStadiumItemHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydOriginStadiumItemRepo;
import cn.wuhan.hyd.sports.req.HydOriginStadiumItemReq;
import cn.wuhan.hyd.sports.service.IHydOriginStadiumItemService;
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
 * 功能说明： 场馆培训项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Service
public class HydOriginStadiumItemServiceImpl extends HydBaseServiceImpl implements IHydOriginStadiumItemService {

    private final Logger logger = LoggerFactory.getLogger(IHydOriginStadiumItemService.class);

    @Resource
    private HydOriginStadiumItemRepo stadiumItemRepo;
    @Resource
    private HydOriginStadiumItemHistoryRepo stadiumItemHistoryRepo;

    @Override
    public PageResult<HydOriginStadiumItem> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydOriginStadiumItem> pageResult = stadiumItemRepo.findAll(pageable);
        PageResult<HydOriginStadiumItem> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydOriginStadiumItem> queryAll() {
        return stadiumItemRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginStadiumItem save(HydOriginStadiumItem hydOriginStadiumItem) {
        return stadiumItemRepo.save(hydOriginStadiumItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        stadiumItemRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginStadiumItem update(HydOriginStadiumItem stadiumItem) {
        if (stadiumItem.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findById(stadiumItem.getId());
        return stadiumItemRepo.save(stadiumItem);
    }

    @Override
    public HydOriginStadiumItem findById(String id) {
        return stadiumItemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("订单不存在，ID：" + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydOriginStadiumItemReq> stadiumItems) {
        // 验证参数
        if (stadiumItems == null || stadiumItems.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (stadiumItems.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }
        String batchNo = UUIDUtil.getBatchNo();
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydOriginStadiumItem> queryList = convert(logger, stadiumItems, HydOriginStadiumItem.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydOriginStadiumItemHistory> historyList = convert(logger, stadiumItems, HydOriginStadiumItemHistory.class, batchNo);

        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydOriginStadiumItem表，批次号：{}", batchNo);
            stadiumItemRepo.deleteAll();

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    stadiumItemRepo::saveAll,
                    "HydOriginStadiumItem",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    stadiumItemHistoryRepo::saveAll,
                    "HydOriginStadiumItemHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != stadiumItems.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, stadiumItems.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, stadiumItems.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }

    public List<Map<String, Integer>> itemCountBySportName(String year) {
        return stadiumItemRepo.itemCountBySportName(year);
    }
}
