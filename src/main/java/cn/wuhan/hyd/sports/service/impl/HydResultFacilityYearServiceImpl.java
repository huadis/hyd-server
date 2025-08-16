package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultFacilityYear;
import cn.wuhan.hyd.sports.domain.HydResultFacilityYearHistory;
import cn.wuhan.hyd.sports.repository.HydResultFacilityYearHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultFacilityYearRepo;
import cn.wuhan.hyd.sports.service.IHydResultFacilityYearService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能说明： 体育基础设施-健身点位年数据 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultFacilityYearServiceImpl implements IHydResultFacilityYearService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultFacilityYearService.class);

    @Resource
    private HydResultFacilityYearRepo facilityYearRepo;
    @Resource
    private HydResultFacilityYearHistoryRepo facilityYearHistoryRepo;

    @Override
    public PageResult<HydResultFacilityYear> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultFacilityYear> pageResult = facilityYearRepo.findAll(pageable);
        PageResult<HydResultFacilityYear> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultFacilityYear> queryAll() {
        return facilityYearRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultFacilityYear save(HydResultFacilityYear hydResultFacilityYear) {
        return facilityYearRepo.save(hydResultFacilityYear);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        facilityYearRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultFacilityYear update(HydResultFacilityYear facilityYear) {
        if (facilityYear.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(facilityYear.getId());
        return facilityYearRepo.save(facilityYear);
    }

    @Override
    public HydResultFacilityYear findById(Long id) {
        return facilityYearRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 健身点位年数据
     *
     * @param facilityYears 健身点位年数据 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultFacilityYear> facilityYears) {
        // 验证参数
        if (facilityYears == null || facilityYears.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (facilityYears.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }
        String batchNo = UUIDUtil.getBatchNo();
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultFacilityYearHistory> historyList = convertToHistoryList(facilityYears, batchNo);
        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydResultFacilityYear表，批次号：{}", batchNo);
            facilityYearRepo.deleteAll();

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    facilityYears,
                    facilityYearRepo::saveAll,
                    "HydResultFacilityYear",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    historyList,
                    facilityYearHistoryRepo::saveAll,
                    "HydResultFacilityYearHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != facilityYears.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, facilityYears.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, facilityYears.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }

    /**
     * 转换为历史表实体列表：统一处理属性拷贝，异常封装为RuntimeException
     */
    private List<HydResultFacilityYearHistory> convertToHistoryList(
            List<HydResultFacilityYear> sourceList,
            String batchNo) {
        try {
            return sourceList.stream()
                    .map(source -> {
                        HydResultFacilityYearHistory history = new HydResultFacilityYearHistory();
                        try {
                            BeanUtils.copyProperties(history, source);
                            return history;
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(
                                    String.format("【批量保存】数据转换失败，原数据ID：%s（若有），异常信息：%s",
                                            source.getId(), e.getMessage()), e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("【批量保存】数据转换为历史表实体失败，批次号：{}，异常信息：", batchNo, e);
            throw e;
        }
    }

    /**
     * 通用保存并日志记录方法：复用时间统计逻辑，减少代码冗余
     *
     * @param dataList     待保存数据列表
     * @param saveFunction 保存操作的函数式接口（Repository的saveAll方法）
     * @param tableName    表名（用于日志）
     * @param batchNo      批次号
     * @param <T>          数据类型
     * @return 实际保存的数量
     */
    private <T> int saveAndLog(
            List<T> dataList,
            java.util.function.Function<List<T>, List<T>> saveFunction,
            String tableName,
            String batchNo) {
        long startTime = System.currentTimeMillis();
        List<T> savedList = saveFunction.apply(dataList);
        long costTime = System.currentTimeMillis() - startTime;

        // 日志包含批次号、表名、数据量、耗时，便于问题定位和性能分析
        logger.info("【批量保存】{}表保存完成，批次号：{}，保存数量：{}，耗时：{} ms",
                tableName, batchNo, savedList.size(), costTime);

        return savedList.size();
    }

    @Override
    public HydResultFacilityYear findLatestFacilityYear() {
        return facilityYearRepo.findLatestFacilityYear();
    }
}
