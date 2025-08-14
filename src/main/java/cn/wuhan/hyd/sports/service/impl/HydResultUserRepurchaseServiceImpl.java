package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultUserRepurchase;
import cn.wuhan.hyd.sports.repository.HydResultUserRepurchaseRepository;
import cn.wuhan.hyd.sports.service.IHydResultUserRepurchaseService;
import org.apache.commons.collections.MapUtils;
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
public class HydResultUserRepurchaseServiceImpl implements IHydResultUserRepurchaseService {

    @Resource
    private HydResultUserRepurchaseRepository hydResultUserRepurchaseRepository;

    @Override
    public PageResult<HydResultUserRepurchase> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultUserRepurchase> queryAll() {
        return hydResultUserRepurchaseRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultUserRepurchase save(HydResultUserRepurchase hydUserRegister) {
        return hydResultUserRepurchaseRepository.save(hydUserRegister);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultUserRepurchaseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultUserRepurchase update(HydResultUserRepurchase hydResultUserRepurchase) {
        if (hydResultUserRepurchase.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultUserRepurchaseRepository.save(hydResultUserRepurchase);
    }

    @Override
    public HydResultUserRepurchase findById(Long id) {
        return hydResultUserRepurchaseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> countStadiumUserRepurchaseStat() {
        Map<String, Object> data = hydResultUserRepurchaseRepository.countStadiumUserRepurchaseStat();
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
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultUserRepurchase> userRepurchases) {
        // 验证参数
        if (userRepurchases == null || userRepurchases.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (userRepurchases.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultUserRepurchase> savedList = hydResultUserRepurchaseRepository.saveAll(userRepurchases);
        return savedList.size();
    }
}
