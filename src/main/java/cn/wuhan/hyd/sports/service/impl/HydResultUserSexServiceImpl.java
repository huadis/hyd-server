package cn.wuhan.hyd.sports.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.wuhan.hyd.framework.utils.DateUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultUserSex;
import cn.wuhan.hyd.sports.domain.HydResultUserSexHistory;
import cn.wuhan.hyd.sports.repository.HydResultUserSexHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultUserSexRepo;
import cn.wuhan.hyd.sports.req.HydResultUserSexReq;
import cn.wuhan.hyd.sports.service.IHydResultUserSexService;
import cn.wuhan.hyd.sports.service.IHydSysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定-男女占比 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultUserSexServiceImpl extends HydBaseServiceImpl implements IHydResultUserSexService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultUserSexService.class);

    @Resource
    private HydResultUserSexRepo userSexRepo;
    @Resource
    private HydResultUserSexHistoryRepo userSexHistoryRepo;
    @Resource
    private IHydSysConfigService configService;

    @Override
    public PageResult<HydResultUserSex> queryAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HydResultUserSex> pageResult = userSexRepo.findAll(pageable);
        PageResult<HydResultUserSex> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultUserSex> queryAll() {
        return userSexRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultUserSex save(HydResultUserSex hydResultUserSex) {
        return userSexRepo.save(hydResultUserSex);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userSexRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultUserSex update(HydResultUserSex userSex) {
        if (userSex.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(userSex.getId());
        return userSexRepo.save(userSex);
    }

    @Override
    public HydResultUserSex findById(Long id) {
        return userSexRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> countStadiumUserSexStat(String year) {
        List<Map<String, Object>> userSexStat = userSexRepo.countStadiumUserSexStat(year);
        // 计算总人数
        int total = userSexStat.stream()
                .mapToInt(map -> Integer.parseInt(MapUtil.getStr(map, "genderCount")))
                .sum();
        List<Map<String, Object>> result = new ArrayList<>();
        // 计算并输出占比
        userSexStat.forEach(map -> {
            int count = Integer.parseInt(MapUtil.getStr(map, "genderCount"));
            double percentage = (double) count / total * 100;
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("gender", MapUtil.getStr(map, "gender"));
            tmp.put("genderCount", count);
            tmp.put("genderPercent", percentage);
            result.add(tmp);
        });
        return result;
    }

    /**
     * 批量保存 男女占比
     *
     * @param userSexes 男女占比 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultUserSexReq> userSexes) {
        // 验证参数
        if (userSexes == null || userSexes.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        List<HydResultUserSexHistory> historyList = convert(logger, userSexes, HydResultUserSexHistory.class, batchNo);
        try {
            int querySaveCount = 0;
            boolean refresh = !configService.notRefresh("场馆预定");
            // 是否冻结，不允许更新查询表
            if (refresh) {
                List<HydResultUserSex> queryList = convert(logger, userSexes, HydResultUserSex.class, batchNo);
                // 4. 清空查询表：日志记录操作意图，便于问题追溯
                logger.info("【批量保存】开始清空HydResultUserSex表，批次号：{}", batchNo);
                userSexRepo.deleteByNotBatchNo(batchNo, DateUtil.getPreviousDayYear());

                // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
                querySaveCount = saveAndLog(
                        logger,
                        queryList,
                        userSexRepo::saveAll,
                        "HydResultUserSex",
                        batchNo
                );
            }

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    userSexHistoryRepo::saveAll,
                    "HydResultUserSexHistory",
                    batchNo
            );

            // 7. 校验保存结果：根据 refresh 状态区分校验逻辑，避免数据不一致
            checkSaveData(userSexes, refresh, querySaveCount, historySaveCount, batchNo);

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return historySaveCount;
        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, userSexes.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
