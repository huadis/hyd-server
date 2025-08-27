package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultUserRepurchase;
import cn.wuhan.hyd.sports.domain.HydResultUserRepurchaseHistory;
import cn.wuhan.hyd.sports.repository.HydResultUserRepurchaseHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultUserRepurchaseRepo;
import cn.wuhan.hyd.sports.req.HydResultUserRepurchaseReq;
import cn.wuhan.hyd.sports.service.IHydResultUserRepurchaseService;
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
 * 功能说明： 场馆预定-复购率 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultUserRepurchaseServiceImpl extends HydBaseServiceImpl implements IHydResultUserRepurchaseService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultUserRepurchaseService.class);

    @Resource
    private HydResultUserRepurchaseRepo userRepurchaseRepo;
    @Resource
    private HydResultUserRepurchaseHistoryRepo userRepurchaseHistoryRepo;

    @Override
    public PageResult<HydResultUserRepurchase> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultUserRepurchase> pageResult = userRepurchaseRepo.findAll(pageable);
        PageResult<HydResultUserRepurchase> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultUserRepurchase> queryAll() {
        return userRepurchaseRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultUserRepurchase save(HydResultUserRepurchase hydUserRegister) {
        return userRepurchaseRepo.save(hydUserRegister);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepurchaseRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultUserRepurchase update(HydResultUserRepurchase userRepurchase) {
        if (userRepurchase.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(userRepurchase.getId());
        return userRepurchaseRepo.save(userRepurchase);
    }

    @Override
    public HydResultUserRepurchase findById(Long id) {
        return userRepurchaseRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> countStadiumUserRepurchaseStat(String year) {
        Map<String, Object> data = userRepurchaseRepo.countStadiumUserRepurchaseStat(year);
        // 计算总人数（安全处理）
        int total = data.values().stream()
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
        data.forEach((key, value) -> {
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
                    "1次",
                    "2-5次",
                    "5次-10次",
                    "10次-50次",
                    "50次以上"
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
     * 批量保存 复购率
     *
     * @param userRepurchases 复购率 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultUserRepurchaseReq> userRepurchases) {
        // 验证参数
        if (userRepurchases == null || userRepurchases.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();

        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultUserRepurchase> queryList = convert(logger, userRepurchases, HydResultUserRepurchase.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultUserRepurchaseHistory> historyList = convert(logger, userRepurchases, HydResultUserRepurchaseHistory.class, batchNo);

        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydResultUserRepurchase表，批次号：{}", batchNo);
            userRepurchaseRepo.deleteAll();

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    userRepurchaseRepo::saveAll,
                    "HydResultUserRepurchase",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    userRepurchaseHistoryRepo::saveAll,
                    "HydResultUserRepurchaseHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != userRepurchases.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, userRepurchases.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, userRepurchases.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
