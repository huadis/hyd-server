package cn.wuhan.hyd.sports.service.impl;


import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydUserRegister;
import cn.wuhan.hyd.sports.repository.HydUserRegisterRepository;
import cn.wuhan.hyd.sports.service.IHydUserRegisterService;
import org.apache.commons.collections.MapUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 功能说明： 场馆预定-每月新增用户 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydUserRegisterServiceImpl implements IHydUserRegisterService {

    @Resource
    private HydUserRegisterRepository hydUserRegisterRepository;

    @Override
    public PageResult<HydUserRegister> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydUserRegister> queryAll() {
        return hydUserRegisterRepository.findAll();
    }

    @Override
    @Transactional
    public HydUserRegister save(HydUserRegister hydUserRegister) {
        return hydUserRegisterRepository.save(hydUserRegister);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydUserRegisterRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydUserRegister update(HydUserRegister hydUserRegister) {
        if (hydUserRegister.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydUserRegisterRepository.save(hydUserRegister);
    }

    @Override
    public HydUserRegister findById(Long id) {
        return hydUserRegisterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> countStadiumUserGrowthStat() {
        List<Map<String, Object>> list = hydUserRegisterRepository.countStadiumUserGrowthStat();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> tmpMap : list) {
            Map<String, Object> map = new HashMap<>(tmpMap);
            String monthName = MapUtils.getString(map, "monthName");
            if (!monthName.endsWith("月")) {
                map.put("monthName", monthName + "月");
            }
            result.add(map);
        }
        // 排序
        result.sort((o1, o2) -> {
            // 定义年龄段的正确顺序
            List<String> order = Arrays.asList(
                    "1月", "2月", "3月", "4月", "5月", "6月",
                    "7月", "8月", "9月", "10月", "11月", "12月"
            );
            // 根据在顺序列表中的索引进行比较
            return Integer.compare(
                    order.indexOf(MapUtils.getString(o1, "monthName")),
                    order.indexOf(MapUtils.getString(o2, "monthName"))
            );
        });
        return result;
    }

    /**
     * 批量保存 每月新增用户
     *
     * @param userRegisters 每月新增用户 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydUserRegister> userRegisters) {
        // 验证参数
        if (userRegisters == null || userRegisters.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (userRegisters.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydUserRegister> savedList = hydUserRegisterRepository.saveAll(userRegisters);
        return savedList.size();
    }
}
