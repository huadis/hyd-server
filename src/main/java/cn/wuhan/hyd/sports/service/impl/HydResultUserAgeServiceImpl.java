package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultUserAge;
import cn.wuhan.hyd.sports.repository.HydResultUserAgeRepository;
import cn.wuhan.hyd.sports.service.IHydResultUserAgeService;
import org.apache.commons.collections.MapUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 功能说明： 场馆预定-年龄占比 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultUserAgeServiceImpl implements IHydResultUserAgeService {

    @Resource
    private HydResultUserAgeRepository hydResultUserAgeRepository;

    @Override
    public PageResult<HydResultUserAge> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultUserAge> queryAll() {
        return hydResultUserAgeRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultUserAge save(HydResultUserAge hydResultUserAge) {
        return hydResultUserAgeRepository.save(hydResultUserAge);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultUserAgeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultUserAge update(HydResultUserAge hydResultUserAge) {
        if (hydResultUserAge.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultUserAgeRepository.save(hydResultUserAge);
    }

    @Override
    public HydResultUserAge findById(Long id) {
        return hydResultUserAgeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> countStadiumUserAgeStat() {
        Map<String, Object> ageData = hydResultUserAgeRepository.countStadiumUserAgeStat();
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
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultUserAge> userAges) {
        // 验证参数
        if (userAges == null || userAges.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (userAges.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultUserAge> savedList = hydResultUserAgeRepository.saveAll(userAges);
        return savedList.size();
    }
}
