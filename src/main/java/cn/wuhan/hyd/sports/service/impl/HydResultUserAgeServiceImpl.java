package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultOrder;
import cn.wuhan.hyd.sports.domain.HydResultOrderHistory;
import cn.wuhan.hyd.sports.domain.HydResultUserAge;
import cn.wuhan.hyd.sports.domain.HydResultUserAgeHistory;
import cn.wuhan.hyd.sports.repository.HydResultUserAgeHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultUserAgeRepo;
import cn.wuhan.hyd.sports.req.HydResultUserAgeReq;
import cn.wuhan.hyd.sports.service.IHydResultUserAgeService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能说明： 场馆预定-年龄占比 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultUserAgeServiceImpl extends HydBaseServiceImpl implements IHydResultUserAgeService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultUserAgeService.class);

    @Resource
    private HydResultUserAgeRepo userAgeRepo;
    @Resource
    private HydResultUserAgeHistoryRepo userAgeHistoryRepo;

    @Override
    public PageResult<HydResultUserAge> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultUserAge> pageResult = userAgeRepo.findAll(pageable);
        PageResult<HydResultUserAge> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultUserAge> queryAll() {
        return userAgeRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultUserAge save(HydResultUserAge hydResultUserAge) {
        return userAgeRepo.save(hydResultUserAge);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userAgeRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultUserAge update(HydResultUserAge userAge) {
        if (userAge.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(userAge.getId());
        return userAgeRepo.save(userAge);
    }

    @Override
    public HydResultUserAge findById(Long id) {
        return userAgeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> countStadiumUserAgeStat() {
        Map<String, Object> ageData = userAgeRepo.countStadiumUserAgeStat();
        // 计算总人数（安全处理）
        int total = ageData.values().stream()
                .map(obj -> obj == null ? "0" : obj.toString()) // 处理null值
                .filter(str -> str.matches("\\d+"))       // 只保留数字字符串
                .mapToInt(val -> {
                    try {
                        return Integer.parseInt(val);
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                }).sum();
        List<Map<String, Object>> result = new ArrayList<>();
        ageData.forEach((key, value) -> {
            String v = value == null ? "0" : value.toString();
            double tmpV = 0;
            try {
                tmpV = Double.parseDouble(v);
            } catch (NumberFormatException e) {
                tmpV = 0;
            }
            double percentage = tmpV / total * 100;
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("key", key);
            tmp.put("count", tmpV);
            tmp.put("percent", percentage);
            result.add(tmp);
        });
        // 排序
        result.sort((o1, o2) -> {
            // 定义年龄段的正确顺序
            List<String> order = Arrays.asList(
                    "18岁以下",
                    "19岁-25岁",
                    "26岁-30岁",
                    "31岁-35岁",
                    "36岁-40岁",
                    "41岁-45岁",
                    "46岁-50岁",
                    "50岁以上"
            );
            // 根据在顺序列表中的索引进行比较
            return Integer.compare(
                    order.indexOf(MapUtils.getString(o1, "key")),
                    order.indexOf(MapUtils.getString(o2, "key"))
            );
        });
        return result;
    }

    /**
     * 批量保存 年龄占比
     *
     * @param userAges 年龄占比 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultUserAgeReq> userAges) {
        // 验证参数
        if (userAges == null || userAges.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (userAges.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }
        String batchNo = UUIDUtil.getBatchNo();

        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultUserAge> queryList = convert(logger, userAges, HydResultUserAge.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultUserAgeHistory> historyList = convert(logger, userAges, HydResultUserAgeHistory.class, batchNo);

        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydResultUserAge表，批次号：{}", batchNo);
            userAgeRepo.deleteAll();

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    userAgeRepo::saveAll,
                    "HydResultUserAge",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    userAgeHistoryRepo::saveAll,
                    "HydResultUserAgeHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != userAges.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, userAges.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, userAges.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
