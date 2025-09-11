package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydOriginStadiumItemHistory;
import cn.wuhan.hyd.sports.repository.HydOriginStadiumItemHistoryRepo;
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

/**
 * 功能说明： 场馆培训项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Service
public class HydOriginStadiumItemServiceImpl extends HydBaseServiceImpl implements IHydOriginStadiumItemService {

    private final Logger logger = LoggerFactory.getLogger(HydOriginStadiumItemServiceImpl.class);
    @Resource
    private HydOriginStadiumItemHistoryRepo stadiumItemHistoryRepo;

    @Override
    public PageResult<HydOriginStadiumItemHistory> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydOriginStadiumItemHistory> pageResult = stadiumItemHistoryRepo.findAll(pageable);
        PageResult<HydOriginStadiumItemHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydOriginStadiumItemHistory> queryAll() {
        return stadiumItemHistoryRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginStadiumItemHistory save(HydOriginStadiumItemHistory hydOriginStadiumItem) {
        return stadiumItemHistoryRepo.save(hydOriginStadiumItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        stadiumItemHistoryRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginStadiumItemHistory update(HydOriginStadiumItemHistory stadiumItem) {
        if (stadiumItem.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findById(stadiumItem.getId());
        return stadiumItemHistoryRepo.save(stadiumItem);
    }

    @Override
    public HydOriginStadiumItemHistory findById(String id) {
        return stadiumItemHistoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("订单不存在，ID：" + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydOriginStadiumItemReq> stadiumItems) {
        // 验证参数
        if (stadiumItems == null || stadiumItems.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydOriginStadiumItemHistory> historyList = convert(logger, stadiumItems, HydOriginStadiumItemHistory.class, batchNo);
        try {
            // 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    stadiumItemHistoryRepo::saveAll,
                    "HydOriginStadiumItemHistory",
                    batchNo
            );

            // 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (historySaveCount != stadiumItems.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，历史表保存量：%d",
                                batchNo, stadiumItems.size(), historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, historySaveCount);
            return historySaveCount;
        } catch (Exception e) {
            // 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：", batchNo, stadiumItems.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
