package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydOriginStadiumItem;
import cn.wuhan.hyd.sports.domain.HydOriginStadiumItemHistory;
import cn.wuhan.hyd.sports.repository.HydOriginStadiumItemHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydOriginStadiumItemRepo;
import cn.wuhan.hyd.sports.req.HydOriginStadiumItemReq;
import cn.wuhan.hyd.sports.service.IHydOriginStadiumItemService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能说明： 场馆培训项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Service
public class HydOriginStadiumItemServiceImpl extends HydBaseServiceImpl implements IHydOriginStadiumItemService {

    private final Logger logger = LoggerFactory.getLogger(IHydOriginStadiumItemService.class);

    @Resource
    private HydOriginStadiumItemRepo stadiumItemRepo;
    @Resource
    private HydOriginStadiumItemHistoryRepo stadiumItemHistoryRepo;

    @Override
    public PageResult<HydOriginStadiumItem> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydOriginStadiumItem> pageResult = stadiumItemRepo.findAll(pageable);
        PageResult<HydOriginStadiumItem> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydOriginStadiumItem> queryAll() {
        return stadiumItemRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginStadiumItem save(HydOriginStadiumItem hydOriginStadiumItem) {
        return stadiumItemRepo.save(hydOriginStadiumItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        stadiumItemRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginStadiumItem update(HydOriginStadiumItem stadiumItem) {
        if (stadiumItem.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        findById(stadiumItem.getId());
        return stadiumItemRepo.save(stadiumItem);
    }

    @Override
    public HydOriginStadiumItem findById(String id) {
        return stadiumItemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("订单不存在，ID：" + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydOriginStadiumItemReq> stadiumItems) {
        // 验证参数
        if (stadiumItems == null || stadiumItems.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (stadiumItems.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }
        String batchNo = UUIDUtil.getBatchNo();
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydOriginStadiumItem> queryList = convert(logger, stadiumItems, HydOriginStadiumItem.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydOriginStadiumItemHistory> historyList = convert(logger, stadiumItems, HydOriginStadiumItemHistory.class, batchNo);

        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydOriginStadiumItem表，批次号：{}", batchNo);
            stadiumItemRepo.deleteAll();

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    stadiumItemRepo::saveAll,
                    "HydOriginStadiumItem",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    stadiumItemHistoryRepo::saveAll,
                    "HydOriginStadiumItemHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != stadiumItems.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, stadiumItems.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, stadiumItems.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }

    public List<Map<String, Object>> itemCountTop10BySportName(String year) {
        return stadiumItemRepo.itemCountTop10BySportName(year);
    }

    public List<Map<String, Object>> itemCountBySportName(String year) {
        // 1. 第一步：查询所有sportName的完整统计结果
        List<Map<String, Object>> allSportStats = stadiumItemRepo.itemCountBySportName(year);
        if (CollectionUtils.isEmpty(allSportStats)) {
            return new ArrayList<>();  // 无数据时返回空列表
        }

        // 2. 第二步：按数量降序排序（数量相同按 sportName 升序）
        List<Map<String, Object>> sortedStats = allSportStats.stream()
                .sorted((map1, map2) -> {
                    // 提取两个 map 中的数量和名称（假设 map 的 key 为 "sportName" 和 "num"）
                    int num1 = MapUtils.getInteger(map1, "num");
                    int num2 = MapUtils.getInteger(map2, "num");
                    String name1 = map1.get("sportName").toString();
                    String name2 = map2.get("sportName").toString();

                    // 先按数量降序
                    if (num1 != num2) {
                        return Integer.compare(num2, num1); // 降序用 num2 - num1
                    }
                    // 数量相同则按名称升序
                    return name1.compareTo(name2);
                })
                .collect(Collectors.toList());

        // 3. 第三步：筛选 Top5 和剩余数据，聚合 “其他”
        List<Map<String, Object>> finalResult = new ArrayList<>();
        int totalSize = sortedStats.size();

        // 3.1 截取 Top5（若总数量不足 5，取全部）
        int top5EndIndex = Math.min(totalSize, 5);
        for (int i = 0; i < top5EndIndex; i++) {
            Map<String, Object> statMap = sortedStats.get(i);
            // 转换为结果 Map（保持 key 为 "sportName" 和 "num"，值类型适配 Object）
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("sportName", statMap.get("sportName"));
            resultMap.put("num", statMap.get("num"));
            finalResult.add(resultMap);
        }

        // 3.2 若有第 6 条及之后的数据，聚合为 “其他”
        if (totalSize > 5) {
            // 计算 “其他” 的总数量（累加第 6 条到最后一条的 num）
            int othersTotalNum = sortedStats.subList(5, totalSize).stream()
                    .mapToInt(map -> MapUtils.getInteger(map, "num"))
                    .sum();
            // 添加 “其他” 条目
            Map<String, Object> othersMap = new HashMap<>();
            othersMap.put("sportName", "其他");
            othersMap.put("num", othersTotalNum);
            finalResult.add(othersMap);
        }

        // 4. 确保 “其他” 始终排在最后
        finalResult.sort((map1, map2) -> {
            String name1 = map1.get("sportName").toString();
            String name2 = map2.get("sportName").toString();

            // 标记 “其他” 为 1，Top5 为 0，按 0→1 排序
            int flag1 = "其他".equals(name1) ? 1 : 0;
            int flag2 = "其他".equals(name2) ? 1 : 0;
            if (flag1 != flag2) {
                return Integer.compare(flag1, flag2);
            }

            // Top5 内部按数量降序
            BigInteger num1 = (BigInteger) map1.get("num");
            BigInteger num2 = (BigInteger) map2.get("num");
            // 注意：降序排列，所以是 num2.compareTo(num1)
            return num2.compareTo(num1);
        });
        return finalResult;
    }
}
