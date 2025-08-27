package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultStadium;
import cn.wuhan.hyd.sports.domain.HydResultStadiumHistory;
import cn.wuhan.hyd.sports.repository.HydResultStadiumHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultStadiumRepo;
import cn.wuhan.hyd.sports.req.HydResultStadiumReq;
import cn.wuhan.hyd.sports.service.IHydResultStadiumService;
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
 * 功能说明： 场馆预定-在线场馆数量 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultStadiumServiceImpl extends HydBaseServiceImpl implements IHydResultStadiumService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultStadiumService.class);

    @Resource
    private HydResultStadiumRepo stadiumRepo;
    @Resource
    private HydResultStadiumHistoryRepo stadiumHistoryRepo;

    @Override
    public PageResult<HydResultStadium> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultStadium> pageResult = stadiumRepo.findAll(pageable);
        PageResult<HydResultStadium> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultStadium> queryAll() {
        return stadiumRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultStadium save(HydResultStadium hydResultStadium) {
        return stadiumRepo.save(hydResultStadium);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        stadiumRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultStadium update(HydResultStadium stadium) {
        if (stadium.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(stadium.getId());
        return stadiumRepo.save(stadium);
    }

    @Override
    public HydResultStadium findById(Long id) {
        return stadiumRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 在线场馆数量
     *
     * @param stadiums 在线场馆数量 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultStadiumReq> stadiums) {
        // 验证参数
        if (stadiums == null || stadiums.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();

        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultStadium> queryList = convert(logger, stadiums, HydResultStadium.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultStadiumHistory> historyList = convert(logger, stadiums, HydResultStadiumHistory.class, batchNo);

        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydResultStadium表，批次号：{}", batchNo);
            stadiumRepo.deleteAll();

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    stadiumRepo::saveAll,
                    "HydResultStadium",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    stadiumHistoryRepo::saveAll,
                    "HydResultStadiumHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != stadiums.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, stadiums.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, stadiums.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
