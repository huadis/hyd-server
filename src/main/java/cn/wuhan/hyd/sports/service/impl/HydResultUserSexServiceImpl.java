package cn.wuhan.hyd.sports.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultUserSex;
import cn.wuhan.hyd.sports.repository.HydResultUserSexRepository;
import cn.wuhan.hyd.sports.service.IHydResultUserSexService;
import org.springframework.data.domain.Pageable;
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
public class HydResultUserSexServiceImpl implements IHydResultUserSexService {

    @Resource
    private HydResultUserSexRepository hydResultUserSexRepository;

    @Override
    public PageResult<HydResultUserSex> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultUserSex> queryAll() {
        return hydResultUserSexRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultUserSex save(HydResultUserSex hydResultUserSex) {
        return hydResultUserSexRepository.save(hydResultUserSex);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultUserSexRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultUserSex update(HydResultUserSex hydResultUserSex) {
        if (hydResultUserSex.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultUserSexRepository.save(hydResultUserSex);
    }

    @Override
    public HydResultUserSex findById(Long id) {
        return hydResultUserSexRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> countStadiumUserSexStat() {
        List<Map<String, Object>> userSexStat = hydResultUserSexRepository.countStadiumUserSexStat();
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
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultUserSex> userSexes) {
        // 验证参数
        if (userSexes == null || userSexes.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (userSexes.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultUserSex> savedList = hydResultUserSexRepository.saveAll(userSexes);
        return savedList.size();
    }
}
