package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultFacilityDistrict;
import cn.wuhan.hyd.sports.repository.HydResultFacilityDistrictRepository;
import cn.wuhan.hyd.sports.service.IHydResultFacilityDistrictService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 体育基础设施-设施各区分布 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultFacilityDistrictServiceImpl implements IHydResultFacilityDistrictService {

    @Resource
    private HydResultFacilityDistrictRepository hydResultFacilityDistrictRepository;

    @Override
    public PageResult<HydResultFacilityDistrict> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultFacilityDistrict> queryAll() {
        return hydResultFacilityDistrictRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultFacilityDistrict save(HydResultFacilityDistrict hydResultFacilityDistrict) {
        return hydResultFacilityDistrictRepository.save(hydResultFacilityDistrict);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultFacilityDistrictRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultFacilityDistrict update(HydResultFacilityDistrict hydResultFacilityDistrict) {
        if (hydResultFacilityDistrict.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultFacilityDistrictRepository.save(hydResultFacilityDistrict);
    }

    @Override
    public HydResultFacilityDistrict findById(Long id) {
        return hydResultFacilityDistrictRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 设施各区分布
     *
     * @param facilityDistricts 设施各区分布 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultFacilityDistrict> facilityDistricts) {
        // 验证参数
        if (facilityDistricts == null || facilityDistricts.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (facilityDistricts.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultFacilityDistrict> savedList = hydResultFacilityDistrictRepository.saveAll(facilityDistricts);
        return savedList.size();
    }
}
