package cn.wuhan.hyd.sports.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydUserSex;
import cn.wuhan.hyd.sports.repository.HydUserSexRepository;
import cn.wuhan.hyd.sports.service.IHydUserSexService;
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
public class HydUserSexServiceImpl implements IHydUserSexService {

    @Resource
    private HydUserSexRepository hydUserSexRepository;

    @Override
    public PageResult<HydUserSex> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydUserSex> queryAll() {
        return hydUserSexRepository.findAll();
    }

    @Override
    @Transactional
    public HydUserSex save(HydUserSex hydUserSex) {
        return hydUserSexRepository.save(hydUserSex);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydUserSexRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydUserSex update(HydUserSex hydUserSex) {
        if (hydUserSex.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydUserSexRepository.save(hydUserSex);
    }

    @Override
    public HydUserSex findById(Long id) {
        return hydUserSexRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> countStadiumUserSexStat() {
        List<Map<String, Object>> userSexStat = hydUserSexRepository.countStadiumUserSexStat();
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
    public int batchSave(List<HydUserSex> userSexes) {
        // 验证参数
        if (userSexes == null || userSexes.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (userSexes.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydUserSex> savedList = hydUserSexRepository.saveAll(userSexes);
        return savedList.size();
    }
}
