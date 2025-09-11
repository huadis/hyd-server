package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydOriginTrainingCourseHistory;
import cn.wuhan.hyd.sports.repository.HydOriginTrainingCourseHistoryRepo;
import cn.wuhan.hyd.sports.req.HydOriginTrainingCourseReq;
import cn.wuhan.hyd.sports.service.IHydOriginTrainingCourseService;
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
 * 功能说明： 培训课程表业务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Service
public class HydOriginTrainingCourseServiceImpl extends HydBaseServiceImpl implements IHydOriginTrainingCourseService {

    private final Logger logger = LoggerFactory.getLogger(HydOriginTrainingCourseServiceImpl.class);
    @Resource
    private HydOriginTrainingCourseHistoryRepo trainingCourseHistoryRepo;

    @Override
    public PageResult<HydOriginTrainingCourseHistory> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydOriginTrainingCourseHistory> pageResult = trainingCourseHistoryRepo.findAll(pageable);
        PageResult<HydOriginTrainingCourseHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydOriginTrainingCourseHistory> queryAll() {
        return trainingCourseHistoryRepo.findAll();
    }

    @Override
    public HydOriginTrainingCourseHistory findById(String id) {
        return trainingCourseHistoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("培训课程不存在，ID：" + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginTrainingCourseHistory save(HydOriginTrainingCourseHistory hydOriginTrainingCourse) {
        return trainingCourseHistoryRepo.save(hydOriginTrainingCourse);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        trainingCourseHistoryRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginTrainingCourseHistory update(HydOriginTrainingCourseHistory trainingCourse) {
        if (trainingCourse.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(trainingCourse.getId());
        return trainingCourseHistoryRepo.save(trainingCourse);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydOriginTrainingCourseReq> trainingCourses) {
        // 验证参数
        if (trainingCourses == null || trainingCourses.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydOriginTrainingCourseHistory> historyList = convert(logger, trainingCourses, HydOriginTrainingCourseHistory.class, batchNo);

        try {
            // 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    trainingCourseHistoryRepo::saveAll,
                    "HydOriginTrainingCourseHistory",
                    batchNo
            );

            // 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (historySaveCount != trainingCourses.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，历史表保存量：%d",
                                batchNo, trainingCourses.size(), historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, historySaveCount);
            return historySaveCount;
        } catch (Exception e) {
            // 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, trainingCourses.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
