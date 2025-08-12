package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydUserRepurchase;
import cn.wuhan.hyd.sports.repository.HydUserRepurchaseRepository;
import cn.wuhan.hyd.sports.service.IHydUserRepurchaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定-复购率 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydUserRepurchaseServiceImpl implements IHydUserRepurchaseService {

    @Resource
    private HydUserRepurchaseRepository hydUserRepurchaseRepository;

    @Override
    public PageResult<HydUserRepurchase> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydUserRepurchase> queryAll() {
        return hydUserRepurchaseRepository.findAll();
    }

    @Override
    @Transactional
    public HydUserRepurchase save(HydUserRepurchase hydUserRegister) {
        return hydUserRepurchaseRepository.save(hydUserRegister);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydUserRepurchaseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydUserRepurchase update(HydUserRepurchase hydUserRepurchase) {
        if (hydUserRepurchase.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydUserRepurchaseRepository.save(hydUserRepurchase);
    }

    @Override
    public HydUserRepurchase findById(Long id) {
        return hydUserRepurchaseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> countStadiumUserRepurchaseStat() {
        Map<String, Object> data = hydUserRepurchaseRepository.countStadiumUserRepurchaseStat();
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
        return result;
    }

    /**
     * 批量保存 复购率
     *
     * @param userRepurchases 复购率 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydUserRepurchase> userRepurchases) {
        // 验证参数
        if (userRepurchases == null || userRepurchases.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (userRepurchases.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydUserRepurchase> savedList = hydUserRepurchaseRepository.saveAll(userRepurchases);
        return savedList.size();
    }
}
