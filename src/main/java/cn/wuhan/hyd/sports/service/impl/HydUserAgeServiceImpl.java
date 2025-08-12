package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydUserAge;
import cn.wuhan.hyd.sports.repository.HydUserAgeRepository;
import cn.wuhan.hyd.sports.service.IHydUserAgeService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定-年龄占比 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydUserAgeServiceImpl implements IHydUserAgeService {

    @Resource
    private HydUserAgeRepository hydUserAgeRepository;

    @Override
    public PageResult<HydUserAge> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydUserAge> queryAll() {
        return hydUserAgeRepository.findAll();
    }

    @Override
    @Transactional
    public HydUserAge save(HydUserAge hydUserAge) {
        return hydUserAgeRepository.save(hydUserAge);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydUserAgeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydUserAge update(HydUserAge hydUserAge) {
        if (hydUserAge.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydUserAgeRepository.save(hydUserAge);
    }

    @Override
    public HydUserAge findById(Long id) {
        return hydUserAgeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> countStadiumUserAgeStat() {
        Map<String, Object> ageData = hydUserAgeRepository.countStadiumUserAgeStat();
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
        return result;
    }

    /**
     * 批量保存 年龄占比
     *
     * @param userAges 年龄占比 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydUserAge> userAges) {
        // 验证参数
        if (userAges == null || userAges.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (userAges.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydUserAge> savedList = hydUserAgeRepository.saveAll(userAges);
        return savedList.size();
    }
}
