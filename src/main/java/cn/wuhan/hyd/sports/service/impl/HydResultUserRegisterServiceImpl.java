package cn.wuhan.hyd.sports.service.impl;


import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultUserRegister;
import cn.wuhan.hyd.sports.domain.HydResultUserRegisterHistory;
import cn.wuhan.hyd.sports.repository.HydResultUserRegisterHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultUserRegisterRepo;
import cn.wuhan.hyd.sports.req.HydResultUserRegisterReq;
import cn.wuhan.hyd.sports.service.IHydResultUserRegisterService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 功能说明： 场馆预定-每月新增用户 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultUserRegisterServiceImpl extends HydBaseServiceImpl implements IHydResultUserRegisterService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultUserRegisterService.class);

    @Resource
    private HydResultUserRegisterRepo userRegisterRepo;
    @Resource
    private HydResultUserRegisterHistoryRepo userRegisterHistoryRepo;

    @Override
    public PageResult<HydResultUserRegister> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultUserRegister> pageResult = userRegisterRepo.findAll(pageable);
        PageResult<HydResultUserRegister> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultUserRegister> queryAll() {
        return userRegisterRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultUserRegister save(HydResultUserRegister hydResultUserRegister) {
        return userRegisterRepo.save(hydResultUserRegister);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRegisterRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultUserRegister update(HydResultUserRegister userRegister) {
        if (userRegister.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(userRegister.getId());
        return userRegisterRepo.save(userRegister);
    }

    @Override
    public HydResultUserRegister findById(Long id) {
        return userRegisterRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> countStadiumUserGrowthStat(String year) {
        List<Map<String, Object>> list = userRegisterRepo.countStadiumUserGrowthStat(year);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> tmpMap : list) {
            Map<String, Object> map = new HashMap<>(tmpMap);
            String monthName = MapUtils.getString(map, "monthName");
            if (!monthName.endsWith("月")) {
                map.put("monthName", monthName + "月");
            }
            result.add(map);
        }
        // 排序
        result.sort((o1, o2) -> {
            // 定义年龄段的正确顺序
            List<String> order = Arrays.asList(
                    "1月", "2月", "3月", "4月", "5月", "6月",
                    "7月", "8月", "9月", "10月", "11月", "12月"
            );
            // 根据在顺序列表中的索引进行比较
            return Integer.compare(
                    order.indexOf(MapUtils.getString(o1, "monthName")),
                    order.indexOf(MapUtils.getString(o2, "monthName"))
            );
        });
        return result;
    }

    /**
     * 批量保存 每月新增用户
     *
     * @param userRegisters 每月新增用户 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultUserRegisterReq> userRegisters) {
        // 验证参数
        if (userRegisters == null || userRegisters.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();

        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultUserRegister> queryList = convert(logger, userRegisters, HydResultUserRegister.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultUserRegisterHistory> historyList = convert(logger, userRegisters, HydResultUserRegisterHistory.class, batchNo);

        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydResultUserRegister表，批次号：{}", batchNo);
            userRegisterRepo.deleteByNotBatchNo(batchNo);

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    userRegisterRepo::saveAll,
                    "HydResultUserRegister",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    userRegisterHistoryRepo::saveAll,
                    "HydResultUserRegisterHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != userRegisters.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, userRegisters.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, userRegisters.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
