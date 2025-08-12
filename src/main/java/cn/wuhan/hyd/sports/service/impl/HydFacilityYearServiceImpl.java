package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydFacilityYear;
import cn.wuhan.hyd.sports.repository.HydFacilityYearRepository;
import cn.wuhan.hyd.sports.service.IHydFacilityYearService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 体育基础设施-健身点位年数据 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydFacilityYearServiceImpl implements IHydFacilityYearService {

    @Resource
    private HydFacilityYearRepository hydFacilityYearRepository;

    @Override
    public PageResult<HydFacilityYear> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydFacilityYear> queryAll() {
        return hydFacilityYearRepository.findAll();
    }

    @Override
    @Transactional
    public HydFacilityYear save(HydFacilityYear hydFacilityYear) {
        return hydFacilityYearRepository.save(hydFacilityYear);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydFacilityYearRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydFacilityYear update(HydFacilityYear hydFacilityYear) {
        if (hydFacilityYear.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydFacilityYearRepository.save(hydFacilityYear);
    }

    @Override
    public HydFacilityYear findById(Long id) {
        return hydFacilityYearRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 健身点位年数据
     *
     * @param facilityYears 健身点位年数据 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydFacilityYear> facilityYears) {
        // 验证参数
        if (facilityYears == null || facilityYears.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (facilityYears.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydFacilityYear> savedList = hydFacilityYearRepository.saveAll(facilityYears);
        return savedList.size();
    }

    @Override
    public HydFacilityYear findLatestFacilityYear() {
        return hydFacilityYearRepository.findLatestFacilityYear();
    }
}
