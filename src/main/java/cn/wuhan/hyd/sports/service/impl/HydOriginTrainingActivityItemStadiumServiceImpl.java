package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydOriginTrainingActivityItemStadiumHistory;
import cn.wuhan.hyd.sports.repository.HydOriginTrainingActivityItemStadiumHistoryRepo;
import cn.wuhan.hyd.sports.req.HydOriginTrainingActivityItemStadiumReq;
import cn.wuhan.hyd.sports.service.IHydOriginTrainingActivityItemStadiumService;
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
 * 功能说明： 培训活动场馆支持的项目表业务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Service
public class HydOriginTrainingActivityItemStadiumServiceImpl extends HydBaseServiceImpl implements IHydOriginTrainingActivityItemStadiumService {

    private final Logger logger = LoggerFactory.getLogger(HydOriginTrainingActivityItemStadiumServiceImpl.class);

    @Resource
    private HydOriginTrainingActivityItemStadiumHistoryRepo trainingActivityItemStadiumHistoryRepo;

    @Override
    public PageResult<HydOriginTrainingActivityItemStadiumHistory> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydOriginTrainingActivityItemStadiumHistory> pageResult = trainingActivityItemStadiumHistoryRepo.findAll(pageable);
        PageResult<HydOriginTrainingActivityItemStadiumHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydOriginTrainingActivityItemStadiumHistory> queryAll() {
        return trainingActivityItemStadiumHistoryRepo.findAll();
    }

    @Override
    public HydOriginTrainingActivityItemStadiumHistory findById(String id) {
        return trainingActivityItemStadiumHistoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("培训活动场馆支持的项目不存在，ID：" + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginTrainingActivityItemStadiumHistory save(HydOriginTrainingActivityItemStadiumHistory hydOriginTrainingActivityItemStadium) {
        return trainingActivityItemStadiumHistoryRepo.save(hydOriginTrainingActivityItemStadium);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        trainingActivityItemStadiumHistoryRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginTrainingActivityItemStadiumHistory update(HydOriginTrainingActivityItemStadiumHistory trainingActivityItemStadium) {
        if (trainingActivityItemStadium.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(trainingActivityItemStadium.getId());
        return trainingActivityItemStadiumHistoryRepo.save(trainingActivityItemStadium);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydOriginTrainingActivityItemStadiumReq> trainingActivityItemStadiums) {
        // 验证参数
        if (trainingActivityItemStadiums == null || trainingActivityItemStadiums.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydOriginTrainingActivityItemStadiumHistory> historyList = convert(logger, trainingActivityItemStadiums, HydOriginTrainingActivityItemStadiumHistory.class, batchNo);

        try {
            // 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    trainingActivityItemStadiumHistoryRepo::saveAll,
                    "HydOriginTrainingActivityItemStadiumHistory",
                    batchNo
            );

            // 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (historySaveCount != trainingActivityItemStadiums.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，历史表保存量：%d",
                                batchNo, trainingActivityItemStadiums.size(), historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, historySaveCount);
            return historySaveCount;
        } catch (Exception e) {
            // 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：", batchNo, trainingActivityItemStadiums.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
