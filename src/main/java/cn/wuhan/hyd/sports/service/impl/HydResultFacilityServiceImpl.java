package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultFacility;
import cn.wuhan.hyd.sports.repository.HydResultFacilityRepository;
import cn.wuhan.hyd.sports.service.IHydResultFacilityService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 功能说明： 体育基础设施-设施全貌 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultFacilityServiceImpl implements IHydResultFacilityService {

    @Resource
    private HydResultFacilityRepository hydResultFacilityRepository;

    @Override
    public PageResult<HydResultFacility> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultFacility> queryAll() {
        return hydResultFacilityRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultFacility save(HydResultFacility hydResultFacility) {
        return hydResultFacilityRepository.save(hydResultFacility);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultFacilityRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultFacility update(HydResultFacility hydResultFacility) {
        if (hydResultFacility.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultFacilityRepository.save(hydResultFacility);
    }

    @Override
    public HydResultFacility findById(Long id) {
        return hydResultFacilityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 设施全貌
     *
     * @param facilities 设施全貌 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultFacility> facilities) {
        // 验证参数
        if (facilities == null || facilities.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (facilities.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultFacility> savedList = hydResultFacilityRepository.saveAll(facilities);
        return savedList.size();
    }

    @Override
    public List<Map<String, Object>> facility() {
        List<HydResultFacility> hydFacilities = queryAll();
        int total = hydFacilities.stream()
                .map(HydResultFacility::getFacilityNum)
                .map(obj -> obj == null ? "0" : obj) // 处理null值
                .filter(str -> str.matches("\\d+"))       // 只保留数字字符串
                .mapToInt(val -> {
                    try {
                        return Integer.parseInt(val);
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                }).sum();
        List<Map<String, Object>> result = new ArrayList<>();
        hydFacilities.forEach(entity -> {
            String facilityNum = entity.getFacilityNum();
            String facilityTypeName = entity.getFacilityTypeName();
            String v = facilityNum == null ? "0" : facilityNum;
            double tmpV = 0;
            try {
                tmpV = Double.parseDouble(v);
            } catch (NumberFormatException e) {
                tmpV = 0;
            }
            double percentage = tmpV / total * 100;
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("key", facilityTypeName);
            tmp.put("count", tmpV);
            tmp.put("percent", percentage);
            result.add(tmp);
        });
        return result;
    }
}
