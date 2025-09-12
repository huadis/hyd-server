package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.DateUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultStadiumMap;
import cn.wuhan.hyd.sports.domain.HydResultStadiumMapHistory;
import cn.wuhan.hyd.sports.repository.HydResultStadiumMapHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultStadiumMapRepo;
import cn.wuhan.hyd.sports.req.HydResultStadiumMapReq;
import cn.wuhan.hyd.sports.service.IHydResultStadiumMapService;
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

/**
 * 功能说明： 驾驶舱-电子地图 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultStadiumMapServiceImpl extends HydBaseServiceImpl implements IHydResultStadiumMapService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultStadiumMapService.class);

    @Resource
    private HydResultStadiumMapRepo stadiumMapRepo;
    @Resource
    private HydResultStadiumMapHistoryRepo stadiumMapHistoryRepo;

    @Override
    public PageResult<HydResultStadiumMap> queryAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HydResultStadiumMap> pageResult = stadiumMapRepo.findAll(pageable);
        PageResult<HydResultStadiumMap> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultStadiumMap> queryAll() {
        return stadiumMapRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultStadiumMap save(HydResultStadiumMap hydResultStadiumMap) {
        return stadiumMapRepo.save(hydResultStadiumMap);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        stadiumMapRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultStadiumMap update(HydResultStadiumMap stadiumMap) {
        if (stadiumMap.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(stadiumMap.getId());
        return stadiumMapRepo.save(stadiumMap);
    }

    @Override
    public HydResultStadiumMap findById(Long id) {
        return stadiumMapRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 电子地图
     *
     * @param stadiumMaps 电子地图 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultStadiumMapReq> stadiumMaps) {
        // 验证参数
        if (stadiumMaps == null || stadiumMaps.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultStadiumMap> queryList = convert(logger, stadiumMaps, HydResultStadiumMap.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultStadiumMapHistory> historyList = convert(logger, stadiumMaps, HydResultStadiumMapHistory.class, batchNo);

        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydResultStadiumMap表，批次号：{}", batchNo);
            stadiumMapRepo.deleteByNotBatchNo(batchNo, DateUtil.getPreviousDayYear());

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    stadiumMapRepo::saveAll,
                    "HydResultStadiumMap",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    stadiumMapHistoryRepo::saveAll,
                    "HydResultStadiumMapHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != stadiumMaps.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, stadiumMaps.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, stadiumMaps.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
