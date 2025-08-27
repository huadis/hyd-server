package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultUserChannel;
import cn.wuhan.hyd.sports.domain.HydResultUserChannelHistory;
import cn.wuhan.hyd.sports.repository.HydResultUserChannelHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultUserChannelRepo;
import cn.wuhan.hyd.sports.req.HydResultUserChannelReq;
import cn.wuhan.hyd.sports.service.IHydResultUserChannelService;
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
 * 功能说明： 场馆预定-用户来源渠道 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultUserChannelServiceImpl extends HydBaseServiceImpl implements IHydResultUserChannelService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultUserChannelService.class);

    @Resource
    private HydResultUserChannelRepo userChannelRepo;
    @Resource
    private HydResultUserChannelHistoryRepo userChannelHistoryRepo;

    @Override
    public PageResult<HydResultUserChannel> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultUserChannel> pageResult = userChannelRepo.findAll(pageable);
        PageResult<HydResultUserChannel> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultUserChannel> queryAll() {
        return userChannelRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultUserChannel save(HydResultUserChannel hydResultUserChannel) {
        return userChannelRepo.save(hydResultUserChannel);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userChannelRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultUserChannel update(HydResultUserChannel userChannel) {
        if (userChannel.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(userChannel.getId());
        return userChannelRepo.save(userChannel);
    }

    @Override
    public HydResultUserChannel findById(Long id) {
        return userChannelRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> countStadiumUserChannelStat(String year) {
        return userChannelRepo.countStadiumUserChannelStat(year);
    }

    /**
     * 批量保存 用户来源渠道
     *
     * @param userChannels 用户来源渠道 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultUserChannelReq> userChannels) {
        // 验证参数
        if (userChannels == null || userChannels.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();

        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultUserChannel> queryList = convert(logger, userChannels, HydResultUserChannel.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultUserChannelHistory> historyList = convert(logger, userChannels, HydResultUserChannelHistory.class, batchNo);

        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydResultUserChannel表，批次号：{}", batchNo);
            userChannelRepo.deleteAll();

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    userChannelRepo::saveAll,
                    "HydResultUserChannel",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    userChannelHistoryRepo::saveAll,
                    "HydResultUserChannelHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != userChannels.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, userChannels.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, userChannels.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
