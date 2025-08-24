package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydOriginTrainingActivityItem;
import cn.wuhan.hyd.sports.domain.HydOriginTrainingActivityItemHistory;
import cn.wuhan.hyd.sports.repository.HydOriginTrainingActivityItemHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydOriginTrainingActivityItemRepo;
import cn.wuhan.hyd.sports.req.HydOriginTrainingActivityItemReq;
import cn.wuhan.hyd.sports.service.IHydOriginTrainingActivityItemService;
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
 * 功能说明： 培训活动支持的项目表业务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Service
public class HydOriginTrainingActivityItemServiceImpl extends HydBaseServiceImpl implements IHydOriginTrainingActivityItemService {

    private final Logger logger = LoggerFactory.getLogger(IHydOriginTrainingActivityItemService.class);

    @Resource
    private HydOriginTrainingActivityItemRepo trainingActivityItemRepo;
    @Resource
    private HydOriginTrainingActivityItemHistoryRepo trainingActivityItemHistoryRepo;

    @Override
    public PageResult<HydOriginTrainingActivityItem> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydOriginTrainingActivityItem> pageResult = trainingActivityItemRepo.findAll(pageable);
        PageResult<HydOriginTrainingActivityItem> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydOriginTrainingActivityItem> queryAll() {
        return trainingActivityItemRepo.findAll();
    }

    @Override
    public HydOriginTrainingActivityItem findById(String id) {
        return trainingActivityItemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("培训活动支持的项目不存在，ID：" + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginTrainingActivityItem save(HydOriginTrainingActivityItem hydOriginTrainingActivityItem) {
        findById(hydOriginTrainingActivityItem.getId());
        return trainingActivityItemRepo.save(hydOriginTrainingActivityItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        trainingActivityItemRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginTrainingActivityItem update(HydOriginTrainingActivityItem trainingActivityItem) {
        if (trainingActivityItem.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(trainingActivityItem.getId());
        return trainingActivityItemRepo.save(trainingActivityItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydOriginTrainingActivityItemReq> trainingActivityItems) {
        // 验证参数
        if (trainingActivityItems == null || trainingActivityItems.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (trainingActivityItems.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }
        String batchNo = UUIDUtil.getBatchNo();
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydOriginTrainingActivityItem> queryList = convert(logger, trainingActivityItems, HydOriginTrainingActivityItem.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydOriginTrainingActivityItemHistory> historyList = convert(logger, trainingActivityItems, HydOriginTrainingActivityItemHistory.class, batchNo);

        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydOriginTrainingActivityItem表，批次号：{}", batchNo);
            trainingActivityItemRepo.deleteAll();

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    trainingActivityItemRepo::saveAll,
                    "HydOriginTrainingActivityItem",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    trainingActivityItemHistoryRepo::saveAll,
                    "HydOriginTrainingActivityItemHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != trainingActivityItems.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, trainingActivityItems.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, trainingActivityItems.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
