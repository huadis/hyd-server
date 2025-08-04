package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydFacilityDistrict;
import cn.wuhan.hyd.sports.repository.HydFacilityDistrictRepository;
import cn.wuhan.hyd.sports.service.IHydFacilityDistrictService;
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
public class HydFacilityDistrictServiceImpl implements IHydFacilityDistrictService {

    @Resource
    private HydFacilityDistrictRepository hydFacilityDistrictRepository;

    @Override
    public PageResult<HydFacilityDistrict> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydFacilityDistrict> queryAll() {
        return hydFacilityDistrictRepository.findAll();
    }

    @Override
    @Transactional
    public HydFacilityDistrict save(HydFacilityDistrict hydFacilityDistrict) {
        return hydFacilityDistrictRepository.save(hydFacilityDistrict);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydFacilityDistrictRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydFacilityDistrict update(HydFacilityDistrict hydFacilityDistrict) {
        if (hydFacilityDistrict.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydFacilityDistrictRepository.save(hydFacilityDistrict);
    }

    @Override
    public HydFacilityDistrict findById(Long id) {
        return hydFacilityDistrictRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 设施各区分布
     *
     * @param facilityDistricts 设施各区分布 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydFacilityDistrict> facilityDistricts) {
        // 验证参数
        if (facilityDistricts == null || facilityDistricts.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (facilityDistricts.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydFacilityDistrict> savedList = hydFacilityDistrictRepository.saveAll(facilityDistricts);
        return savedList.size();
    }
}
